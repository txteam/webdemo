/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年4月27日
 * <修改描述:>
 */
package com.tx.votes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
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
public class GNProxyIpAddressFetcherBak {
    
    public static List<ProxyIpAddressInfo> ipAddressInfoList = new ArrayList<>();
    
    private static HttpClient httpClient;
    
    private static HttpConnectionManager httpConnectionManager;
    
    private static int timout = 3000;
    
    private static boolean statleCheckingEnabled = true;
    
    private static int maxConnectionsPerHost = 40;
    
    private static int max_error_times = 10;
    
    private static String cacheFlushFlagKey = "";
    
    private static Timer timer;
    
    public GNProxyIpAddressFetcherBak(){
        try {
            parseProxyIpAddressInfo();
            
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        parseProxyIpAddressInfo();
                    } catch (HttpException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },5 * 60 * 1000);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    private synchronized static HttpConnectionManager getHttpConnectionManager() {
        if (GNProxyIpAddressFetcherBak.httpConnectionManager != null) {
            return GNProxyIpAddressFetcherBak.httpConnectionManager;
        }
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        GNProxyIpAddressFetcherBak.httpConnectionManager = connectionManager;
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
    
    public static String get(String url) throws HttpException, IOException{
        return get(url,0);
    }
    
    public static String get(String url, int currentReRequest)
            throws HttpException, IOException {
        InputStream responseIn = null;
        String htmlContent = null;
        GetMethod getMethod = new GetMethod(url);
        try {
            HttpClient httpClientTemp = getHttpClient();
            httpClient.getParams()
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
            
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
            htmlContent = IOUtils.toString(responseIn, "GBK");
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
    
    public static void parseProxyIpAddressInfo() throws HttpException,
            IOException {
        Map<String, String> params = new HashMap<>();
        //params.put("id", viewId);
        String htmlContent = get("http://www.proxy.com.ru/gaoni/list_1.html");
        if (htmlContent == null) {
            return;
        }
        
        Document doc = Jsoup.parse(htmlContent);
        Element cacheKeyEl = doc.select("body > center > table:eq(0) tr td:eq(1) > b > font")
                .get(0);
        String newCacheFlushFlagKey = cacheKeyEl.text();
        if (cacheFlushFlagKey.equals(newCacheFlushFlagKey)) {
            return;
        }
        
        List<ProxyIpAddressInfo> tempList = new ArrayList<ProxyIpAddressInfo>();
        Elements dataEls = doc.select("body > center > font > table td:eq(1) font > table tr:gt(0)");
        tempList.addAll(parseIpAddressInfoList(dataEls));
        
        Elements pagedAEls = doc.select("body > center > font > table > tbody > tr > td:eq(1) table:eq(1) td a");
        for (Element el : pagedAEls) {
            String url = el.attr("href");

            String newHtmlContent = get("http://www.proxy.com.ru/gaoni/" + url);
            Document newDoc = Jsoup.parse(newHtmlContent);
            Elements newDataEls = newDoc.select("body > center > font > table td:eq(1) font > table tr:gt(0)");
            
            tempList.addAll(parseIpAddressInfoList(newDataEls));
        }
        
        
        List<ProxyIpAddressInfo> scTempList = new ArrayList<>();
        for(ProxyIpAddressInfo piaInfo : tempList){
            if(piaInfo.getArea().indexOf("四川") >= 0
                    || piaInfo.getArea().indexOf("北京") >= 0
                    || piaInfo.getArea().indexOf("广州") >= 0
                    || piaInfo.getArea().indexOf("移动") >= 0
                    || piaInfo.getArea().indexOf("联通") >= 0
                    || piaInfo.getArea().indexOf("电信") >= 0){
                scTempList.add(piaInfo);
            }
            
        }
        
        cacheFlushFlagKey = newCacheFlushFlagKey;
        ipAddressInfoList = scTempList;
    }
    
    public static List<ProxyIpAddressInfo> parseIpAddressInfoList(Elements dataEls)
            throws HttpException, IOException {
        List<ProxyIpAddressInfo> resAddressInfos = new ArrayList<>();
        for (Element el : dataEls) {
            ProxyIpAddressInfo p = new ProxyIpAddressInfo();
            p.setArea(el.child(4).text());
            p.setIpAddress(el.child(1).text());
            p.setPort(el.child(2).text());
            
            resAddressInfos.add(p);
        }
        return resAddressInfos;
    }
}
