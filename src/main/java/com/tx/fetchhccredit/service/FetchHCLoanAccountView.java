package com.tx.fetchhccredit.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
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
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.tx.core.dbscript.model.DataSourceTypeEnum;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;
import com.tx.core.paged.model.PagedList;
import com.tx.fetch.dao.PersonInfoDao;
import com.tx.fetch.dao.impl.PersonInfoDaoImpl;
import com.tx.fetch.model.PersonInfo;
import com.tx.fetch.service.PersonInfoService;
import com.tx.fetchhccredit.dao.HCLoanAccountViewDao;
import com.tx.fetchhccredit.dao.impl.HCLoanAccountViewDaoImpl;
import com.tx.fetchhccredit.model.HCLoanAccountView;

/**
  * 提取贷款账户信息<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2016年2月17日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class FetchHCLoanAccountView {
    
    private static String createHCLoanAccountViewSql = "CREATE TABLE hc_la_loanaccount ( id VARCHAR (64) NOT NULL, loanBillNumber VARCHAR (64), STATUS VARCHAR (64), idCardNumber VARCHAR (64), clientName VARCHAR (64), districtName VARCHAR (64), branchName VARCHAR (64), phoneNumber VARCHAR (64), applyDate VARCHAR (64), creditProductName VARCHAR (64), customerServiceOfficer VARCHAR (64), customerServiceManager VARCHAR (64), customerServiceTeamManager VARCHAR (64), PRIMARY KEY (id))";
    
    private static String queryUrl = "http://crm6xs.credithc.com/index.php/historyData/historyList/pagesize/{}/p/{}";
    
    private static HttpClient httpClient;
    
    private static HttpConnectionManager httpConnectionManager;
    
    private static int timout = 3000;
    
    private static boolean statleCheckingEnabled = true;
    
    private static int maxConnectionsPerHost = 40;
    
    private static int maxTaskPoolSize = 20;
    
    private static int max_error_times = 10;
    
    private static TaskExecutor taskExecutor = null;
    
    private static DataSource dataSource = null;
    
    private static JdbcTemplate jdbcTemplate = null;
    
    private static HCLoanAccountViewService hcLoanAccountViewService;
    
    private synchronized static TaskExecutor getTaskExecutor() {
        if (FetchHCLoanAccountView.taskExecutor != null) {
            return FetchHCLoanAccountView.taskExecutor;
        }
        ThreadPoolTaskExecutor taskExecutorTemp = new ThreadPoolTaskExecutor();
        taskExecutorTemp.setCorePoolSize(maxTaskPoolSize);
        taskExecutorTemp.setMaxPoolSize(maxTaskPoolSize);
        taskExecutorTemp.afterPropertiesSet();
        
        FetchHCLoanAccountView.taskExecutor = taskExecutorTemp;
        return taskExecutorTemp;
    }
    
    private synchronized static HttpConnectionManager getHttpConnectionManager() {
        if (FetchHCLoanAccountView.httpConnectionManager != null) {
            return FetchHCLoanAccountView.httpConnectionManager;
        }
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        FetchHCLoanAccountView.httpConnectionManager = connectionManager;
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
    
    private static String get(String url, int currentReRequest)
            throws HttpException, IOException {
        InputStream responseIn = null;
        String htmlContent = null;
        GetMethod getMethod = new GetMethod(url);
        try {
            HttpClient httpClientTemp = getHttpClient();
            httpClient.getParams()
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
                            "UTF-8");
            getMethod.setRequestHeader("accept", "*/*");
            getMethod.setRequestHeader("connection", "Keep-Alive");
            getMethod.setRequestHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            
            int statusCode = httpClientTemp.executeMethod(getMethod);
            if (HttpStatus.SC_OK != statusCode) {
                System.out.println("error.");
            }
            
            responseIn = getMethod.getResponseBodyAsStream();
            htmlContent = IOUtils.toString(responseIn, "UTF-8");
        } catch (Exception e) {
            if (currentReRequest < max_error_times) {
                return get(url, currentReRequest++);
            } else {
                return null;
            }
        } finally {
            IOUtils.closeQuietly(responseIn);
            getMethod.releaseConnection();
        }
        return htmlContent;
    }
    
    private static String post(String url, Map<String, String> params,
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
    
    private static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate != null) {
            return jdbcTemplate;
        }
        jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }
    
    private static DataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }
        BasicDataSource bds = new BasicDataSource();
        // 设置驱动程序
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        // 设置连接用户名
        bds.setUsername("root");
        // 设置连接密码
        bds.setPassword("root");
        // 设置连接地址
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/fetch_data?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
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
        
        dataSource = bds;
        return bds;
    }
    
    private static MyBatisDaoSupport getMyBatisDaoSupport() throws Exception {
        MyBatisDaoSupport res = MyBatisDaoSupportHelper.buildMyBatisDaoSupport("classpath:context/mybatis-config.xml",
                new String[] { "classpath*:com/tx/fetchhccredit/**/*SqlMap.xml" },
                DataSourceTypeEnum.MYSQL,
                getDataSource());
        return res;
    }
    
    private static HCLoanAccountViewDao getHCLoanAccountViewDao() {
        HCLoanAccountViewDaoImpl dao = new HCLoanAccountViewDaoImpl();
        try {
            dao.setMyBatisDaoSupport(getMyBatisDaoSupport());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }
    
    private static HCLoanAccountViewService getHCLoanAccountViewService() {
        if (hcLoanAccountViewService != null) {
            return hcLoanAccountViewService;
        }
        HCLoanAccountViewService res = new HCLoanAccountViewService(
                getHCLoanAccountViewDao());
        hcLoanAccountViewService = res;
        return res;
    }
    
    public static String get(String url) throws HttpException, IOException {
        return get(url, 0);
    }
    
    public static String post(String url, Map<String, String> params)
            throws HttpException, IOException {
        return post(url, params, 0);
    }
    
    public static PagedList<HCLoanAccountView> parseHCLoanAccountView(
            int pageSize, int pageIndex) throws HttpException, IOException {
        PagedList<HCLoanAccountView> pagedList = new PagedList<>();
        pagedList.setPageSize(pageSize);
        pagedList.setPageIndex(pageIndex);
        
        String postUrl = MessageFormatter.arrayFormat(queryUrl,
                new Object[] { pageSize, pageIndex }).getMessage();
        String htmlContent = get(postUrl);
        if (htmlContent == null) {
            return null;
        }
        
        Document doc = Jsoup.parse(htmlContent);
        Elements personMoreEles = doc.select("div.relativeBOX");
        Element personMoreEle = personMoreEles.get(0);
        Elements trTags = personMoreEle.getElementsByTag("tr");
        if (trTags.size() == 0) {
            return pagedList;
        }
        List<HCLoanAccountView> laList = new ArrayList<>();
        for (Element trTemp : trTags) {
            Elements tdEles = trTemp.getElementsByTag("td");
            if (tdEles.size() == 0) {
                // 跳过th
                continue;
            }
            String ahref = tdEles.get(12)
                    .getElementsByTag("a")
                    .get(0)
                    .attr("href");
            String[] hrefArr = ahref.split("/");
            String id = hrefArr[hrefArr.length - 1];
            String districtName = tdEles.get(0).text();
            String status = tdEles.get(1).text();
            String branchName = tdEles.get(2).text();
            String clientName = tdEles.get(3).text();
            String loanBillNumber = tdEles.get(4).text();
            String idCardNumber = tdEles.get(5).text();
            String phoneNumber = tdEles.get(6).text();
            String applyDate = tdEles.get(7).text();
            String creditProductName = tdEles.get(8).text();
            String customerServiceOfficer = tdEles.get(9).text();
            String customerServiceManager = tdEles.get(10).text();
            String customerServiceTeamManager = tdEles.get(11).text();
            
            HCLoanAccountView la = new HCLoanAccountView(id, status,
                    districtName, branchName, clientName, loanBillNumber,
                    idCardNumber, phoneNumber, applyDate, creditProductName,
                    customerServiceOfficer, customerServiceManager,
                    customerServiceTeamManager);
            
            laList.add(la);
        }
        pagedList.setList(laList);
        
        Element pagedEle = personMoreEles.get(1);
        String text = pagedEle.text();
        
        System.out.println(text);
        //376385 条记录 1/37639 页 下一页  1  2   3   4   5  下5页 最后一页 跳转至第 页 每页显示 10203060120150180200 条数据
        //String test = "376385 条记录 1/376 页 下一页  1  2   3   4   5  下5页 最后一页 跳转至第 页 每页显示 10203060120150180200 条数据";
        Matcher m = pagedInfoPattern.matcher(text);
        if (m.matches()) {
            pagedList.setCount(NumberUtils.toInt(m.group(1)));
        }
        return pagedList;
    }
    
    private static Pattern pagedInfoPattern = Pattern.compile("^\\D*?(\\d+?)\\D.+?(\\d+?)/(\\d+?)\\D.+?页.+?$");
    
    public static void main11(String[] args) {
        String test = "376385 条记录 1/376 页 下一页  1  2   3   4   5  下5页 最后一页 跳转至第 页 每页显示 10203060120150180200 条数据";
        Matcher m = pagedInfoPattern.matcher(test);
        System.out.println(m.matches());
        System.out.println(m.group(1));
        System.out.println(m.group(2));
        System.out.println(m.group(3));
    }
    
    public static void startFetch() throws HttpException, IOException,
            JSONException {
        HttpClient hc = getHttpClient();
        
        HCLoginHelper loginHelper = new HCLoginHelper(hc);
        
        if (loginHelper.login()) {
            JdbcTemplate jt = getJdbcTemplate();
            try {
                jt.execute(createHCLoanAccountViewSql);
            } catch (DataAccessException e) {
                //donothing
            }
            HCLoanAccountViewService service = getHCLoanAccountViewService();
            
            PagedList<HCLoanAccountView> resPagedList = null;
            boolean hasNext = true;
            int pageIndex = 1;
            int pageSize = 10000;
            do {
                resPagedList = parseHCLoanAccountView(pageSize, pageIndex);
                insertToHcLoanAccountView(resPagedList.getList());
                
                pageIndex++;
                int pageCount = ((resPagedList.getCount() % resPagedList.getPageSize()) == 0) ? (int) (resPagedList.getCount() / resPagedList.getPageSize())
                        : (int) (resPagedList.getCount() / resPagedList.getPageSize()) + 1;
                if(pageIndex <= pageCount){
                    hasNext = true;
                }else{
                    hasNext = false;
                }
            } while (hasNext);
        }
    }
    
    private static void insertToHcLoanAccountView(List<HCLoanAccountView> laList) {
        if (CollectionUtils.isEmpty(laList)) {
            return;
        }
        HCLoanAccountViewService service = getHCLoanAccountViewService();
        for (HCLoanAccountView laTemp : laList) {
            
//            HCLoanAccountView findLa = service.findHCLoanAccountViewById(laTemp.getId());
//            if (findLa == null) {
//                service.insertHCLoanAccountView(laTemp);
//            }
        }
        service.batchInsertHCLoanAccountView(laList);
    }
    
    public static void main(String[] args) throws HttpException, IOException,
            JSONException {
        try {
            FetchHCLoanAccountView fetch = new FetchHCLoanAccountView();
            fetch.startFetch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
