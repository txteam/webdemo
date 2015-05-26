/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年4月27日
 * <修改描述:>
 */
package com.tx.votes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.SimpleConnectionHandle;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年4月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Votes1 {
    
    private static String cacheFlushKey;
    
    private static List<String> useAbleProxyIpAddressList = new ArrayList<>();
    
    private static Set<String> usedIpSet = new HashSet<>();
    
    private static int timout = 1000 * 5;
    
    private static boolean statleCheckingEnabled = true;
    
    private static int maxConnectionsPerHost = 40;
    
    private static int maxTaskPoolSize = 20;
    
    private static int max_error_times = 3;
    
    private static TaskExecutor taskExecutor = null;
    
    private static Set<String> usedUrl = new HashSet<>();
    
    private synchronized static HttpConnectionManager getHttpConnectionManager() {
        SimpleHttpConnectionManager connectionManager = new SimpleHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(timout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        return connectionManager;
    }
    
    private synchronized static HttpConnectionManager getHttpConnectionManager(
            int timeout) {
        SimpleHttpConnectionManager connectionManager = new SimpleHttpConnectionManager();
        connectionManager.getParams()
                .setConnectionTimeout(timeout <= 0 ? Votes1.timout : timeout);
        connectionManager.getParams()
                .setStaleCheckingEnabled(statleCheckingEnabled);
        connectionManager.getParams()
                .setMaxTotalConnections(maxConnectionsPerHost);
        connectionManager.getParams()
                .setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        return connectionManager;
    }
    
    private synchronized static HttpClient getHttpClient(int timeout) {
        HttpClient httpClientTemp = new HttpClient();
        
        HttpConnectionManager connectionManager = getHttpConnectionManager();
        httpClientTemp = new HttpClient(connectionManager);
        httpClientTemp.getParams()
                .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        
        httpClientTemp.setTimeout(timeout <= 0 ? Votes1.timout : timeout);
        
        return httpClientTemp;
    }
    
    private synchronized static HttpClient getHttpClient(String proxyUrl,
            String port, int timeout) {
        HttpClient httpClientTemp = new HttpClient();
        
        HttpConnectionManager connectionManager = getHttpConnectionManager(timeout);
        httpClientTemp = new HttpClient(connectionManager);
        httpClientTemp.getParams()
                .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        
        httpClientTemp.getHostConfiguration().setProxy(proxyUrl,
                NumberUtils.toInt(port));
        
        httpClientTemp.setTimeout(timeout <= 0 ? Votes1.timout : timeout);
        /*
        //需要验证
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("chenlb", "123456");
        httpClient.getState().setProxyCredentials(AuthScope.ANY, creds);
        */
        return httpClientTemp;
    }
    
    public static boolean validateHttpClient(HttpClient httpClient, String url)
            throws HttpException, IOException {
        InputStream responseIn = null;
        GetMethod getMethod = new GetMethod(url);
        try {
            httpClient.getParams()
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
                            "UTF-8");
            
            getMethod.setRequestHeader("accept", "*/*");
            //getMethod.setRequestHeader("connection", "Keep-Alive");
            getMethod.setRequestHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            
            int statusCode = httpClient.executeMethod(getMethod);
            if (HttpStatus.SC_OK != statusCode) {
                System.out.println("error.statusCode:" + statusCode);
                return false;
            }
            
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            IOUtils.closeQuietly(responseIn);
            getMethod.releaseConnection();
        }
    }
    
    public static BufferedImage getImage(HttpClient httpClient, String url,
            int currentReRequest) throws HttpException, IOException {
        InputStream responseIn = null;
        GetMethod getMethod = new GetMethod(url);
        BufferedImage bufferedImage = null;
        try {
            httpClient.getParams()
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
                            "UTF-8");
            
            getMethod.setRequestHeader("accept", "*/*");
            getMethod.setRequestHeader("connection", "Keep-Alive");
            getMethod.setRequestHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            
            int statusCode = httpClient.executeMethod(getMethod);
            if (HttpStatus.SC_OK != statusCode) {
                System.out.println("error.statusCode:" + statusCode);
            }
            
            responseIn = getMethod.getResponseBodyAsStream();
            bufferedImage = ImageIO.read(responseIn);
        } catch (Exception e) {
            e.printStackTrace();
            //            if (currentReRequest < max_error_times) {
            //                return getImage(httpClient,url, currentReRequest++);
            //            } else {
            //                return null;
            //            }
        } finally {
            IOUtils.closeQuietly(responseIn);
            getMethod.releaseConnection();
        }
        return bufferedImage;
    }
    
    public static String post(HttpClient httpClientTemp, String url,
            Map<String, String> params, int currentReRequest)
            throws HttpException, IOException {
        InputStream responseIn = null;
        String htmlContent = null;
        PostMethod postMethod = new PostMethod(url);
        try {
            params = params == null ? new HashMap<String, String>() : params;
            httpClientTemp.getParams()
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
                System.out.println("error.statusCode:" + statusCode);
                return "";
            }
            
            responseIn = postMethod.getResponseBodyAsStream();
            htmlContent = IOUtils.toString(responseIn, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            if (currentReRequest < max_error_times) {
                return post(httpClientTemp, url, params, ++currentReRequest);
            } else {
                return null;
            }
        } finally {
            IOUtils.closeQuietly(responseIn);
            postMethod.releaseConnection();
        }
        return htmlContent;
    }
    
    public static String get(HttpClient httpClientTemp, String url,
            int currentReRequest) throws HttpException, IOException {
        InputStream responseIn = null;
        String htmlContent = null;
        GetMethod getMethod = new GetMethod(url);
        try {
            httpClientTemp.getParams()
                    .setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
                            "UTF-8");
            
            getMethod.setRequestHeader("accept", "*/*");
            getMethod.setRequestHeader("connection", "Keep-Alive");
            getMethod.setRequestHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            
            List<NameValuePair> requestParamList = new ArrayList<>();
            // postMethod.set
            
            int statusCode = httpClientTemp.executeMethod(getMethod);
            if (HttpStatus.SC_OK != statusCode) {
                System.out.println("error.statusCode:" + statusCode);
                return "";
            }
            
            responseIn = getMethod.getResponseBodyAsStream();
            htmlContent = IOUtils.toString(responseIn, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            if (currentReRequest < max_error_times) {
                return get(httpClientTemp, url, ++currentReRequest);
            } else {
                return null;
            }
        } finally {
            IOUtils.closeQuietly(responseIn);
            getMethod.releaseConnection();
        }
        return htmlContent;
    }
    
    public static class Reminder{
        
        Timer timer;

        /** <默认构造函数> */
        public Reminder() {
            timer = new Timer();
            
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("redo timerTask");
                    for (ProxyIpAddressInfo temp : GNProxyIpAddressFetcher.getIpAddressInfoList()) {
                        if (usedUrl.contains(temp.getIpAddress())) {
                            System.out.println("ipAddress:" + temp.getIpAddress() + "isUsed.");
                            continue;
                        }
                        System.out.println("ipAddress:" + temp.getIpAddress());
                        String proxyUrl = temp.getIpAddress();
                        String proxyPort = temp.getPort();
                        try {
                            //"61.135.179.52","80"
                            HttpClient hcTemp = getHttpClient(proxyUrl,
                                    proxyPort,
                                    300);
                            hcTemp.setTimeout(300);
                            if (!validateHttpClient(hcTemp, "http://www.baidu.com")) {
                                System.out.println("validate false.");
                                continue;
                            }
                            System.out.println("validate success.");
                            
                            HttpClient hc = getHttpClient(proxyUrl, proxyPort, 0);
                            //HttpClient hc = getHttpClient(temp.getIpAddress(),temp.getPort());
                            //HttpClient hc = getHttpClient();
                            
                            BufferedImage bi1 = getImage(hc,
                                    "http://220.166.61.168:89/Code.asp",
                                    0);
                            ImageIcon im1 = new ImageIcon(bi1);
                            
                            String inputValue1 = JOptionPane.showInputDialog(im1,
                                    "验证码1");
                            
                            BufferedImage bi2 = getImage(hc,
                                    "http://220.166.61.168:89/Code_num.asp",
                                    0);
                            ImageIcon im2 = new ImageIcon(bi2);
                            
                            String inputValue2 = JOptionPane.showInputDialog(im2,
                                    "验证码2");
                            
                            HashMap<String, String> params = new HashMap<>();
                            params.put("gid_17", "489");
                            params.put("action", "save");
                            params.put("User_Code", inputValue1);
                            params.put("User_Code2", inputValue2);
                            //http://220.166.61.168:89/Index.asp
                            String html = post(hc,
                                    "http://220.166.61.168:89/Index.asp",
                                    params,
                                    0);
                            
                            JOptionPane.showMessageDialog(null, html);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            },0,5 * 1000);
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println("---------start--------------");
        //final GNProxyIpAddressFetcherBak t = new GNProxyIpAddressFetcherBak();
        new Reminder();
        System.out.println("---------end--------------");
    }
}
