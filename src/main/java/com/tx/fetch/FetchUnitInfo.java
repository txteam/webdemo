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
import com.tx.fetch.dao.UnitInfoDao;
import com.tx.fetch.dao.impl.UnitInfoDaoImpl;
import com.tx.fetch.model.UnitInfo;
import com.tx.fetch.service.UnitInfoService;

/**
  * 构建post请求获取身份证号码<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月10日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class FetchUnitInfo {
    
    private static HttpClient httpClient;
    
    private static HttpConnectionManager httpConnectionManager;
    
    private static int timout = 3000;
    
    private static boolean statleCheckingEnabled = true;
    
    private static int maxConnectionsPerHost = 40;
    
    private static int maxTaskPoolSize = 20;
    
    private static int max_error_times = 10;
    
    private static TaskExecutor taskExecutor = null;
    
    
    
    private synchronized static TaskExecutor getTaskExecutor() {
        if (FetchUnitInfo.taskExecutor != null) {
            return FetchUnitInfo.taskExecutor;
        }
        ThreadPoolTaskExecutor taskExecutorTemp = new ThreadPoolTaskExecutor();
        taskExecutorTemp.setCorePoolSize(maxTaskPoolSize);
        taskExecutorTemp.setMaxPoolSize(maxTaskPoolSize);
        taskExecutorTemp.afterPropertiesSet();
        
        FetchUnitInfo.taskExecutor = taskExecutorTemp;
        return taskExecutorTemp;
    }
    
    private synchronized static HttpConnectionManager getHttpConnectionManager() {
        if (FetchUnitInfo.httpConnectionManager != null) {
            return FetchUnitInfo.httpConnectionManager;
        }
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        FetchUnitInfo.httpConnectionManager = connectionManager;
        return connectionManager;
    }
    
    private synchronized static HttpClient getHttpClient() {
        if (httpClient != null) {
            return httpClient;
        }
        HttpClient httpClientTemp = new HttpClient();
        
        HttpConnectionManager connectionManager = getHttpConnectionManager();
        httpClientTemp = new HttpClient(connectionManager);
        httpClientTemp.getParams()
                .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
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
            httpClient.getParams()
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
                            "UTF-8");
            
            postMethod.setRequestHeader("accept", "*/*");
            postMethod.setRequestHeader("connection", "Keep-Alive");
            postMethod.setRequestHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            postMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            
            List<NameValuePair> requestParamList = new ArrayList<>();
            //int index = 0;
            for (Entry<String, String> entryTemp : params.entrySet()) {
                requestParamList.add(new NameValuePair(entryTemp.getKey(),
                        entryTemp.getValue()));
            }
            NameValuePair[] data = new NameValuePair[requestParamList.size()];
            requestParamList.toArray(data);
            postMethod.setRequestBody(data);
            //postMethod.set
            
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
        //设置驱动程序
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        //设置连接用户名
        bds.setUsername("lms");
        //设置连接密码
        bds.setPassword("zzxx1122");
        //设置连接地址
        bds.setUrl("jdbc:mysql://192.168.80.206:3306/lms_data_source?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
        //设置初始化连接总数
        bds.setInitialSize(5);
        //设置同时应用的连接总数
        bds.setMaxActive(-1);
        //设置在缓冲池的最大连接数
        bds.setMaxIdle(5);
        //设置在缓冲池的最小连接数
        bds.setMinIdle(0);
        //设置最长的等待时间
        bds.setMaxWait(5000);
        return bds;
    }
    
    private static MyBatisDaoSupport getMyBatisDaoSupport() throws Exception {
        MyBatisDaoSupport res = MyBatisDaoSupportHelper.buildMyBatisDaoSupport("classpath:context/mybatis-config.xml",
                new String[] { "classpath*:com/tx/fetch/**/*SqlMap.xml" },
                DataSourceTypeEnum.MYSQL,
                getDataSource());
        return res;
    }
    
    private static UnitInfoDao getUnitInfoDao() {
        UnitInfoDaoImpl dao = new UnitInfoDaoImpl();
        try {
            dao.setMyBatisDaoSupport(getMyBatisDaoSupport());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }
    
    private static UnitInfoService getUnitInfoService() {
        UnitInfoService res = new UnitInfoService(getUnitInfoDao());
        return res;
    }
    
    public static String post(String url, Map<String, String> params)
            throws HttpException, IOException {
        return post(url, params, 0);
    }
    
    public static List<Map<String, String>> parseUnitList(int pageIndex)
            throws HttpException, IOException {
        List<Map<String, String>> resMapList = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("currentPage", String.valueOf(pageIndex));
        String htmlContent = post("http://shixin.court.gov.cn/unitMore.do",
                params);
        if (htmlContent == null) {
            return null;
        }
        //System.out.println(htmlContent);
        
        Document doc = Jsoup.parse(htmlContent);
        Elements personMoreEles = doc.select("div.unit_more");
        Element personMoreEle = personMoreEles.get(0);
        Elements trTags = personMoreEle.getElementsByTag("tr");
        
        if (trTags.size() == 0) {
            return resMapList;
        }
        int currentPageIndex = 1;
        for (Element trTemp : trTags) {
            Elements tdEles = trTemp.getElementsByTag("td");
            if (tdEles.size() == 0) {
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
    
    public static UnitInfo parseUnitDetail(String viewId, int pageIndex,
            int currentPageIndex) throws HttpException, IOException {
        //Map<String, String> resMap = new HashMap<>();
        //{"id":833957,"iname":"广昌青湖钢厂","caseCode":"(2014)广法执字第00232号","
        //focusNumber":95,"cardNum":"680926658","businessEntity":"万长荣",
        //"courtName":"江西省广昌县人民法院","areaName":"江西","partyTypeName":"581","gistId":"（2014）广民初字第501号民事判决书",
        //"regDate":"2014年11月28日","gistUnit":"江西省广昌县人民法院",
        //"duty":"1.被告广昌青湖钢厂在本判决生效之日起七日内支付原告揭孝勇水泥款53300元；2.本案诉讼费1133元，财产保全费620元，由被告承担。",
        //"performance":"全部未履行","disruptTypeName":"其它规避执行","publishDate":"2014年12月11日"}
        Map<String, String> params = new HashMap<>();
        params.put("id", viewId);
        String htmlContent = post("http://shixin.court.gov.cn/detail", params);
        
        if (htmlContent == null) {
            return null;
        }
        //System.out.println(htmlContent);
        UnitInfo res = new UnitInfo();
        //System.out.println(htmlContent);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(htmlContent);
            System.out.println(jsonObject.get("id").toString());
            res.setId(jsonObject.get("id").toString());
            res.setIname(jsonObject.get("iname").toString());
            res.setCaseCode(jsonObject.get("caseCode").toString());
            res.setFocusNumber(jsonObject.get("focusNumber").toString());
            res.setCardNum(jsonObject.get("cardNum").toString());
            res.setBusinessEntity(jsonObject.get("businessEntity").toString());
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
    
    public static void fetch() {
        final UnitInfoService unitInfoService = getUnitInfoService();
        TaskExecutor taskExecutor = getTaskExecutor();
        for (int i = 1; i < 6751; i++) {
            final int pageIndex = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Map<String, String>> resMapList = parseUnitList(pageIndex);
                        if (resMapList == null) {
                            return;
                        }
                        
                        for (Map<String, String> mapTemp : resMapList) {
                            System.out.println(mapTemp.get("ziNumber"));
                            String viewId = mapTemp.get("viewId");
                            int currentPageIndex = NumberUtils.toInt(mapTemp.get("currentPageIndex"),
                                    -1);
                            UnitInfo unitInfoDetail = unitInfoService.findUnitInfoById(viewId);
                            if (unitInfoDetail != null) {
                                unitInfoDetail.setFetchDate(new Date());
                                unitInfoDetail.setPageIndex(pageIndex);
                                unitInfoDetail.setCurrentPageIndex(currentPageIndex);
                                unitInfoService.updateById(unitInfoDetail);
                                continue;
                            }
                            
                            UnitInfo unitInfo = parseUnitDetail(viewId,
                                    pageIndex,
                                    currentPageIndex);
                            if (unitInfo != null) {
                                unitInfoService.insertUnitInfo(unitInfo);
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
        FetchUnitInfo.fetch();
    }
}
