/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年4月27日
 * <修改描述:>
 */
package com.tx.votes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;

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
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 高度匿名ip地址抓取器
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年4月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class GNProxyIpAddressFetcher {
    
    public static List<ProxyIpAddressInfo> ipAddressInfoList = new ArrayList<>();
    
    private static HttpClient httpClient;
    
    private static HttpConnectionManager httpConnectionManager;
    
    private static int timout = 3000;
    
    private static boolean statleCheckingEnabled = true;
    
    private static int maxConnectionsPerHost = 40;
    
    private static int max_error_times = 10;
    
    private static String cacheFlushFlagKey = "";
    
    private static Timer timer;
    
    private synchronized static HttpConnectionManager getHttpConnectionManager() {
        if (GNProxyIpAddressFetcher.httpConnectionManager != null) {
            return GNProxyIpAddressFetcher.httpConnectionManager;
        }
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        GNProxyIpAddressFetcher.httpConnectionManager = connectionManager;
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
    
    public static String post(String url, Map<String, String> params)
            throws HttpException, IOException {
        return post(url, params, 0);
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
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
            
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
            htmlContent = IOUtils.toString(responseIn, "GBK");
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
    
    public static String get(String url) throws HttpException, IOException {
        return get(url, 0);
    }
    
    public static String get(String url, int currentReRequest)
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
    
    //    public static void parseProxyIpAddressInfo() throws HttpException,
    //            IOException {
    //        Map<String, String> params = new HashMap<>();
    //        //params.put("id", viewId);
    //        
    //        if (htmlContent == null) {
    //            return;
    //        }
    //        
    //        String 
    //        
    //        cacheFlushFlagKey = newCacheFlushFlagKey;
    //        ipAddressInfoList = scTempList;
    //    }
    
    public static void main(String[] args) {
        System.out.println(StringUtils.split("testt \t\n test").length);
    }
    
    public static List<ProxyIpAddressInfo> getIpAddressInfoList(){
        String htmlContent;
        List<ProxyIpAddressInfo> resAddressInfos = new ArrayList<>();
        try {
            htmlContent = get("http://www.kuaidaili.com/api/getproxy/?orderid=913018738538460&num=50&area=%E4%B8%AD%E5%9B%BD&browser=1&protocol=1&method=2&an_ha=1&sp1=1&sort=0&dedup=1&format=text&sep=1");
            String[] ips = StringUtils.split(htmlContent);
            System.out.println("getIpList:size:" + ips.length);
            
            for (String ipTemp : ips) {
                ProxyIpAddressInfo p;
                try {
                    String[] ipArray = StringUtils.split(ipTemp, ':');
                    
                    p = new ProxyIpAddressInfo();
                    p.setIpAddress(ipArray[0]);
                    p.setPort(ipArray[1]);
                } catch (Exception e) {
                    continue;
                }
                
                resAddressInfos.add(p);
            }
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resAddressInfos;
    }
    
    //    public static void main(String[] args) throws Exception{
    //        Map<String, String> params = new HashMap<String, String>();
    //        params.put("tid", "559049375375844");
    //        params.put("num", "1");
    //        params.put("area", "四川");
    //        params.put("filter", "on");
    //        String resStr = get("http://verx.daili666.com/ip/?tid=559049375375844&num=1&area=" + URLEncoder.encode("四川","utf-8")+"&filter=on");
    //        
    //        
    //        System.out.println(resStr);
    //    }
}
