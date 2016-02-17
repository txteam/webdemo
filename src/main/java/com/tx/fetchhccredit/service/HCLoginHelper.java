/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年2月17日
 * <修改描述:>
 */
package com.tx.fetchhccredit.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年2月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HCLoginHelper {
    
    private static int max_error_times = 3;
    
    private String serverContentPath = "http://crm6xs.credithc.com/";
    
    private String loginUrl = "/index.php/Public/loging";
    
    private String verifyUrl = "/index.php/Public/verify/";
    
    private String loginName = "13684936153";
    
    private String password = "lyw123";
    
    private HttpClient httpClient;
    
    /** <默认构造函数> */
    public HCLoginHelper(HttpClient httpClient) {
        super();
        this.httpClient = httpClient;
    }
    
    private String parseVerifyCode() throws HttpException, IOException {
        String verifyCodeUrl = this.serverContentPath + this.verifyUrl
                + ((int) (Math.random() * 1000));
        
        GetMethod getVerifyCodeMethod = new GetMethod(verifyCodeUrl);
        
        String inputValue1;
        try {
            int statusCode = this.httpClient.executeMethod(getVerifyCodeMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getVerifyCodeMethod.getStatusLine());
            }
            InputStream responseIn = getVerifyCodeMethod.getResponseBodyAsStream();
            BufferedImage newBi = ImageIO.read(responseIn);
            BufferedImage newBiCopy = new BufferedImage(newBi.getWidth(),
                    newBi.getHeight(), newBi.getType());
            newBiCopy.setData(newBi.getData());
            
            inputValue1 = JOptionPane.showInputDialog(new ImageIcon(newBiCopy),
                    "");
        } finally {
            getVerifyCodeMethod.releaseConnection();
        }
        return inputValue1;
    }
    
    public boolean login() throws HttpException, IOException, JSONException {
        String verifyCode = parseVerifyCode();
        System.out.println("verifyCode:" + verifyCode);
        String loginUrl = this.serverContentPath + this.loginUrl;
        
        Map<String, String> params = new HashMap<>();
        params.put("staff_login_name", this.loginName);
        params.put("staff_pwd", this.password);
        params.put("verify", verifyCode);
        
        String result = post(this.httpClient, loginUrl, params, 1);
        System.out.println("result:" + result);
        JSONObject jsonObject = new JSONObject(result);
        if("1".equals(jsonObject.get("status").toString()) ){
            return true;
        }else{
            System.out.println("loginError:" + jsonObject.get("info").toString());
            return false;
        }
    }
    
    private String post(HttpClient httpClient, String url,
            Map<String, String> params, int currentReRequest)
            throws HttpException, IOException {
        InputStream responseIn = null;
        String htmlContent = null;
        PostMethod postMethod = new PostMethod(url);
        try {
            params = params == null ? new HashMap<String, String>() : params;
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
            
            int statusCode = httpClient.executeMethod(postMethod);
            if (HttpStatus.SC_OK != statusCode) {
                System.out.println("error.");
            }
            
            responseIn = postMethod.getResponseBodyAsStream();
            htmlContent = IOUtils.toString(responseIn, "UTF-8");
        } catch (Exception e) {
            if (currentReRequest < max_error_times) {
                return post(httpClient, url, params, currentReRequest++);
            } else {
                return null;
            }
        } finally {
            IOUtils.closeQuietly(responseIn);
            postMethod.releaseConnection();
        }
        return htmlContent;
    }
    
    public static void main(String[] args) throws HttpException, IOException, JSONException {
        HttpClient hc = new HttpClient();
        HCLoginHelper t = new HCLoginHelper(hc);
        boolean isLogin = t.login();
        System.out.println(isLogin);
    }
}
