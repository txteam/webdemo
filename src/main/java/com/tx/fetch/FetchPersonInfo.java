package com.tx.fetch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.tx.core.dbscript.model.DataSourceTypeEnum;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;
import com.tx.fetch.dao.PersonInfoDao;
import com.tx.fetch.dao.impl.PersonInfoDaoImpl;
import com.tx.fetch.model.PersonInfo;
import com.tx.fetch.service.PersonInfoService;

/**
 * 构建post请求获取身份证号码<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年12月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FetchPersonInfo {

	private static HttpClient httpClient;

	private static HttpConnectionManager httpConnectionManager;

	private static int timout = 3000;

	private static boolean statleCheckingEnabled = true;

	private static int maxConnectionsPerHost = 40;

	private static int maxTaskPoolSize = 20;

	private static int max_error_times = 10;

	private static TaskExecutor taskExecutor = null;

	private synchronized static TaskExecutor getTaskExecutor() {
		if (FetchPersonInfo.taskExecutor != null) {
			return FetchPersonInfo.taskExecutor;
		}
		ThreadPoolTaskExecutor taskExecutorTemp = new ThreadPoolTaskExecutor();
		taskExecutorTemp.setCorePoolSize(maxTaskPoolSize);
		taskExecutorTemp.setMaxPoolSize(maxTaskPoolSize);
		taskExecutorTemp.afterPropertiesSet();

		FetchPersonInfo.taskExecutor = taskExecutorTemp;
		return taskExecutorTemp;
	}

	private synchronized static HttpConnectionManager getHttpConnectionManager() {
		if (FetchPersonInfo.httpConnectionManager != null) {
			return FetchPersonInfo.httpConnectionManager;
		}
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(timout);
		connectionManager.getParams().setStaleCheckingEnabled(
				statleCheckingEnabled);
		connectionManager.getParams().setMaxTotalConnections(
				maxConnectionsPerHost);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(
				maxConnectionsPerHost);

		FetchPersonInfo.httpConnectionManager = connectionManager;
		return connectionManager;
	}

	private synchronized static HttpClient getHttpClient() {
		if (httpClient != null) {
			return httpClient;
		}
		HttpClient httpClientTemp = new HttpClient();

		HttpConnectionManager connectionManager = getHttpConnectionManager();
		httpClientTemp = new HttpClient(connectionManager);
		httpClientTemp.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient = httpClientTemp;
		return httpClient;
	}

	public static String post(String url, Map<String, String> params,
			int currentReRequest) throws HttpException, IOException {
		InputStream responseIn = null;
		String htmlContent = null;
		PostMethod postMethod = new PostMethod(url);
		try {
			params = params == null ? new HashMap<String, String>() : params;
			HttpClient httpClientTemp = getHttpClient();
			httpClient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

			postMethod.setRequestHeader("accept", "*/*");
			postMethod.setRequestHeader("connection", "Keep-Alive");
			postMethod.setRequestHeader("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			postMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");

			List<NameValuePair> requestParamList = new ArrayList<>();
			// int index = 0;
			for (Entry<String, String> entryTemp : params.entrySet()) {
				requestParamList.add(new NameValuePair(entryTemp.getKey(),
						entryTemp.getValue()));
			}
			NameValuePair[] data = new NameValuePair[requestParamList.size()];
			requestParamList.toArray(data);
			postMethod.setRequestBody(data);
			// postMethod.set

			int statusCode = httpClientTemp.executeMethod(postMethod);
			if (HttpStatus.SC_OK != statusCode) {
				System.out.println("error.");
			}

			responseIn = postMethod.getResponseBodyAsStream();
			htmlContent = IOUtils.toString(responseIn, "UTF-8");
		} catch (Exception e) {
			if (currentReRequest < max_error_times) {
				return post(url, params, currentReRequest++);
			} else {
				return null;
			}
		} finally {
			IOUtils.closeQuietly(responseIn);
			postMethod.releaseConnection();
		}
		return htmlContent;
	}

	private static DataSource getDataSource() {
		BasicDataSource bds = new BasicDataSource();
		// 设置驱动程序
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		// 设置连接用户名
		bds.setUsername("lms");
		// 设置连接密码
		bds.setPassword("zzxx1122");
		// 设置连接地址
		bds.setUrl("jdbc:mysql://192.168.80.206:3306/lms_data_source?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
		// 设置初始化连接总数
		bds.setInitialSize(5);
		// 设置同时应用的连接总数
		bds.setMaxActive(-1);
		// 设置在缓冲池的最大连接数
		bds.setMaxIdle(5);
		// 设置在缓冲池的最小连接数
		bds.setMinIdle(0);
		// 设置最长的等待时间
		bds.setMaxWait(5000);
		return bds;
	}

	private static MyBatisDaoSupport getMyBatisDaoSupport() throws Exception {
		MyBatisDaoSupport res = MyBatisDaoSupportHelper.buildMyBatisDaoSupport(
				"classpath:context/mybatis-config.xml",
				new String[] { "classpath*:com/tx/fetch/**/*SqlMap.xml" },
				DataSourceTypeEnum.MYSQL, getDataSource());
		return res;
	}

	private static PersonInfoDao getPersonInfoDao() {
		PersonInfoDaoImpl dao = new PersonInfoDaoImpl();
		try {
			dao.setMyBatisDaoSupport(getMyBatisDaoSupport());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}

	private static PersonInfoService getPersonInfoService() {
		PersonInfoService res = new PersonInfoService(getPersonInfoDao());
		return res;
	}

	public static String post(String url, Map<String, String> params)
			throws HttpException, IOException {
		return post(url, params, 0);
	}

	public static List<Map<String, String>> parsePersonList(int pageIndex)
			throws HttpException, IOException {
		List<Map<String, String>> resMapList = new ArrayList<>();
		Map<String, String> params = new HashMap<>();
		params.put("currentPage", String.valueOf(pageIndex));
		String htmlContent = post("http://shixin.court.gov.cn/personMore.do",
				params);
		if (htmlContent == null) {
			return null;
		}
		// System.out.println(htmlContent);

		Document doc = Jsoup.parse(htmlContent);
		Elements personMoreEles = doc.select("div.person_more");
		Element personMoreEle = personMoreEles.get(0);
		Elements trTags = personMoreEle.getElementsByTag("tr");

		if (trTags.size() == 0) {
			return resMapList;
		}
		int currentPageIndex = 1;
		for (Element trTemp : trTags) {
			Elements tdEles = trTemp.getElementsByTag("td");
			if (tdEles.size() == 0) {
				// 跳过th
				continue;
			}
			currentPageIndex++;
			String name = tdEles.get(1).text();
			String ziNumber = tdEles.get(2).text();
			String date = tdEles.get(3).text();
			Elements aView = tdEles.get(5).select("a.iView");
			String viewId = aView.get(0).attr("id");

			Map<String, String> row = new HashMap<>();
			row.put("currentPageIndex", String.valueOf(currentPageIndex));
			row.put("name", name);
			row.put("ziNumber", ziNumber);
			row.put("date", date);
			row.put("viewId", viewId);

			resMapList.add(row);
		}
		return resMapList;
	}

	public static PersonInfo parsePersonDetail(String viewId, int pageIndex,
			int currentPageIndex) throws HttpException, IOException {
		Map<String, String> params = new HashMap<>();
		params.put("id", viewId);
		String htmlContent = post("http://shixin.court.gov.cn/detail", params);
		if (htmlContent == null) {
			return null;
		}

		PersonInfo res = new PersonInfo();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(htmlContent);
			// System.out.println(jsonObject.get("id").toString());
			res.setId(jsonObject.get("id").toString());
			res.setIname(jsonObject.get("iname").toString());
			res.setCaseCode(jsonObject.get("caseCode").toString());
			res.setAge(jsonObject.get("age").toString());
			res.setSexy(jsonObject.get("sexy").toString());
			res.setFocusNumber(jsonObject.get("focusNumber").toString());
			res.setCardNum(jsonObject.get("cardNum").toString());
			res.setCourtName(jsonObject.get("courtName").toString());
			res.setAreaName(jsonObject.get("areaName").toString());
			res.setPartyTypeName(jsonObject.get("partyTypeName").toString());
			res.setGistId(jsonObject.get("gistId").toString());
			res.setRegDate(jsonObject.get("regDate").toString());
			res.setGistUnit(jsonObject.get("gistUnit").toString());
			res.setDuty(jsonObject.get("duty").toString());
			res.setPerformance(jsonObject.get("performance").toString());
			res.setDisruptTypeName(jsonObject.get("disruptTypeName").toString());
			res.setPublishDate(jsonObject.get("publishDate").toString());

			res.setPageIndex(pageIndex);
			res.setCurrentPageIndex(currentPageIndex);
			return res;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String parseRealIdCardNumber(String name, String idCardNumber)
			throws HttpException, IOException {
		String[] cardNumParts = StringUtils.splitByWholeSeparator(idCardNumber,
				"*");

		String carNumParts1 = cardNumParts[0];
		String month = "";
		for (int i = 1; i <= 12; i++) {
			String testMonth = "";
			String carNumberTestTemp = "";
			if (i < 10) {
				testMonth = "0" + i;
			} else {
				testMonth = "" + i;
			}

			carNumberTestTemp = carNumParts1 + testMonth;
			if (checkIdCardNumber(name, carNumberTestTemp)) {
				month = testMonth;
				break;
			}
		}

		if (StringUtils.isEmpty(month)) {
			return null;
		}

		String day = "";
		for (int i = 1; i <= 31; i++) {
			String testDay = "";
			String carNumberTestTemp = "";
			if (i < 10) {
				testDay = "0" + i;
			} else {
				testDay = "" + i;
			}

			carNumberTestTemp = carNumParts1 + month + testDay;
			if (checkIdCardNumber(name, carNumberTestTemp)) {
				day = testDay;
				break;
			}
		}

		if (StringUtils.isEmpty(day)) {
			return null;
		}

		String realIdCardNumber = carNumParts1 + month + day + cardNumParts[1];
		return realIdCardNumber;
	}

	public static boolean checkIdCardNumber(String name, String idCardNumber)
			throws HttpException, IOException {
		Map<String, String> params = new HashMap<>();
		// params.put("pName", "冉静");
		// params.put("pCardNum", "510221197801");
		params.put("pName", name);
		params.put("pCardNum", idCardNumber);
		params.put("pProvince", "0");
		String htmlContent = post("http://shixin.court.gov.cn/search", params);
		if (htmlContent == null) {
			return false;
		}
		// System.out.println(htmlContent);
		Document doc = Jsoup.parse(htmlContent);
		Elements trTags = doc.select("body div table tr");
		// System.out.println(trTags.size());
		return trTags.size() > 1;
	}

	public static void fetch() {
		final PersonInfoService personInfoService = getPersonInfoService();
		TaskExecutor taskExecutor = getTaskExecutor();
		for (int i = 1; i < 44439; i++) {
			final int pageIndex = i;
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						List<Map<String, String>> resMapList = parsePersonList(pageIndex);
						if (resMapList == null) {
							return;
						}

						for (Map<String, String> mapTemp : resMapList) {
							System.out.println(mapTemp.get("ziNumber"));
							String viewId = mapTemp.get("viewId");
							int currentPageIndex = NumberUtils.toInt(
									mapTemp.get("currentPageIndex"), -1);
							PersonInfo personInfoDetail = personInfoService
									.findPersonInfoById(viewId);
							if (personInfoDetail != null
									&& !StringUtils.isEmpty(personInfoDetail
											.getIdCardNumber())) {
								personInfoDetail.setFetchDate(new Date());
								personInfoDetail.setPageIndex(pageIndex);
								personInfoDetail
										.setCurrentPageIndex(currentPageIndex);
								personInfoService.updateById(personInfoDetail);
								continue;
							}

							if (personInfoDetail == null) {
								personInfoDetail = parsePersonDetail(viewId,
										pageIndex, currentPageIndex);
								personInfoService
										.insertPersonInfo(personInfoDetail);
							}

							if (StringUtils.isEmpty(personInfoDetail
									.getIdCardNumber())) {
								String realIdCardNumber = parseRealIdCardNumber(
										personInfoDetail.getIname(),
										personInfoDetail.getCardNum());

								System.out.println(personInfoDetail
										.getCardNum()
										+ " : "
										+ personInfoDetail.getIname());
								personInfoDetail
										.setIdCardNumber(realIdCardNumber);
								personInfoService.updateById(personInfoDetail);
							}
						}
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	public static void main(String[] args) throws HttpException, IOException {
		FetchPersonInfo.fetch();
	}
}
