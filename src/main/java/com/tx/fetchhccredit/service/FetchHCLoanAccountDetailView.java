/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年2月18日
 * <修改描述:>
 */
package com.tx.fetchhccredit.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.tx.core.dbscript.model.DataSourceTypeEnum;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;
import com.tx.core.paged.model.PagedList;
import com.tx.fetchhccredit.dao.HCLoanAccountDetailViewDao;
import com.tx.fetchhccredit.dao.impl.HCLoanAccountDetailViewDaoImpl;
import com.tx.fetchhccredit.model.HCLoanAccountDetailView;
import com.tx.fetchhccredit.model.HCLoanAccountView;

/**
 * 提取贷款账户详情视图<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年2月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FetchHCLoanAccountDetailView {
    
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    
    private static final String userName = "root";
    
    private static final String password = "root";
    
    private static final String url = "jdbc:mysql://127.0.0.1:3306/fetch_data?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    
    private static String createHCLoanAccountDetailViewSql = "CREATE TABLE hc_la_loanaccount_detail ( id VARCHAR (64) NOT NULL, loanBillNumber VARCHAR (64), STATUS VARCHAR (64), createOperatorName VARCHAR (64), createDate VARCHAR (64), creditProductName VARCHAR (64), totalPeriod VARCHAR (64), interestRate VARCHAR (64), uses VARCHAR (64), loanAmount VARCHAR (64), maxMonthlyRepayAmount VARCHAR (64), spouseName VARCHAR (64), spouseWorkUnitPhoneNumber VARCHAR (32), spouseTelePhoneNumber VARCHAR (32), idCardType VARCHAR (64), idCardNumber VARCHAR (64), clientName VARCHAR (32), hasHouse VARCHAR (32), applyDate VARCHAR (32), refuseOtherReason VARCHAR (255), linkInfo VARCHAR (255), districtName VARCHAR (64), patchReason VARCHAR (255), linkManPhoneNumber1 VARCHAR (32), linkManPhoneNumber2 VARCHAR (32), linkManPhoneNumber3 VARCHAR (32), linkManPhoneNumber4 VARCHAR (32), kinPhoneNumber1 VARCHAR (32), kinPhoneNumber2 VARCHAR (32), kinPhoneNumber3 VARCHAR (32), kinPhoneNumber4 VARCHAR (32), firstTrialOperator VARCHAR (32), firstTrialDate VARCHAR (32), finalTrialOperator VARCHAR (32), finalTrialDate VARCHAR (32), workUnitInfo VARCHAR (255), workUnitPhoneNumber VARCHAR (64), workUnitIndustry VARCHAR (255), workInfo VARCHAR (128), refuseReason VARCHAR (255), sex VARCHAR (32), hasMortgage VARCHAR (32), patchDate VARCHAR (32), urgent varchar(32), housePhoneNumber VARCHAR (32), telePhoneNumber VARCHAR (32), startLiveDate VARCHAR (32), customerServiceOfficer VARCHAR (64), customerServiceTeamManager VARCHAR (64), customerServiceManager VARCHAR (64), serviceClientName VARCHAR (64), remark VARCHAR (2000), MONTH VARCHAR (32), PRIMARY KEY (ID))";
    
    private static String queryDetailUrl = "http://crm6xs.credithc.com/index.php/historyData/showMes/his_id/{}";
    
    private static String queryDetailIdSql = "SELECT t.id FROM hc_la_loanaccount t WHERE NOT EXISTS ( SELECT 1 FROM hc_la_loanaccount_detail td WHERE td.id = td.id ) LIMIT 10";
    
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
    
    private static HCLoanAccountDetailViewService hcLoanAccountDetailViewService;
    
    private synchronized static TaskExecutor getTaskExecutor() {
        if (taskExecutor != null) {
            return taskExecutor;
        }
        ThreadPoolTaskExecutor taskExecutorTemp = new ThreadPoolTaskExecutor();
        taskExecutorTemp.setCorePoolSize(maxTaskPoolSize);
        taskExecutorTemp.setMaxPoolSize(maxTaskPoolSize);
        taskExecutorTemp.afterPropertiesSet();
        
        taskExecutor = taskExecutorTemp;
        return taskExecutorTemp;
    }
    
    private synchronized static HttpConnectionManager getHttpConnectionManager() {
        if (httpConnectionManager != null) {
            return httpConnectionManager;
        }
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        httpConnectionManager = connectionManager;
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
        bds.setDriverClassName(driverClassName);
        // 设置连接用户名
        bds.setUsername(userName);
        // 设置连接密码
        bds.setPassword(password);
        // 设置连接地址
        bds.setUrl(url);
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
    
    private static HCLoanAccountDetailViewDao getHCLoanAccountDetailViewDao() {
        HCLoanAccountDetailViewDaoImpl dao = new HCLoanAccountDetailViewDaoImpl();
        try {
            dao.setMyBatisDaoSupport(getMyBatisDaoSupport());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }
    
    private static HCLoanAccountDetailViewService getHCLoanAccountDetailViewService() {
        if (hcLoanAccountDetailViewService != null) {
            return hcLoanAccountDetailViewService;
        }
        HCLoanAccountDetailViewService res = new HCLoanAccountDetailViewService(
                getHCLoanAccountDetailViewDao());
        hcLoanAccountDetailViewService = res;
        return res;
    }
    
    public static String get(String url) throws HttpException, IOException {
        return get(url, 0);
    }
    
    public static String post(String url, Map<String, String> params)
            throws HttpException, IOException {
        return post(url, params, 0);
    }
    
    public static HCLoanAccountDetailView parseHCLoanAccountDetailViews(
            String clientId) throws HttpException, IOException {
        String detailUrl = MessageFormatter.arrayFormat(queryDetailUrl,
                new Object[] { clientId }).getMessage();
        
        String detailHtmlContent = get(detailUrl);
        
        HCLoanAccountDetailView detail = HCLoanAccountDetailParser.parse(clientId,
                detailHtmlContent);
        
        return detail;
    }
    
    public static void startFetch() throws HttpException, IOException,
            JSONException {
        JdbcTemplate jt = getJdbcTemplate();
        try {
            jt.execute(createHCLoanAccountDetailViewSql);
        } catch (DataAccessException e) {
            //donothing
        }
        
        HttpClient hc = getHttpClient();
        HCLoginHelper loginHelper = new HCLoginHelper(hc);
        if (loginHelper.login()) {
            //以下部分重复即可
            List<String> idList = jt.queryForList(queryDetailIdSql,
                    String.class);
            for (String idTemp : idList) {
                HCLoanAccountDetailView detail = parseHCLoanAccountDetailViews(idTemp);
                
                insertToHcLoanAccountDetailView(detail);
            }
        }
    }
    
    private static void insertToHcLoanAccountDetailView(
            HCLoanAccountDetailView laTemp) {
        HCLoanAccountDetailViewService service = getHCLoanAccountDetailViewService();
        
        HCLoanAccountDetailView findLa = service.findHCLoanAccountDetailViewById(laTemp.getId());
        if (findLa == null) {
            service.insertHCLoanAccountDetailView(laTemp);
        }
    }
    
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws HttpException, IOException,
            JSONException {
        try {
            FetchHCLoanAccountDetailView.startFetch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
