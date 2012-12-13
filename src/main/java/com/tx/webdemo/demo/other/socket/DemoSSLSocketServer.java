/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-29
 * <修改描述:>
 */
package com.tx.webdemo.demo.other.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.cxf.common.util.StringUtils;

import com.tx.core.TxConstants;

/*
http://ln-ydc.iteye.com/blog/1335213
keytool -genkeypair -alias "test1" -keyalg "RSA" -keystore "test.keystore" 
-genkeypair：生成一对非对称密钥;
-alias：指定密钥对的别名，该别名是公开的;
-keyalg：指定加密算法，本例中的采用通用的RAS加密算法;
-keystore:密钥库的路径及名称，不指定的话，默认在操作系统的用户目录下生成一个".keystore"的文件

1.创建证书
keytool -genkeypair -alias "test1" -keyalg "RSA" -keystore "test.keystore"  
2.查看证书库
keytool -list -keystore test.keystore  
3.导出到证书文件
keytool -export -alias test1 -file test.crt -keystore test.keystore
4.导入证书的信息
keytool -import -keystore test_cacerts -file test.crt  
5.查看证书信息
keytool -printcert -file "test.crt"   
6.删除密钥库中的条目
删除前查看密钥库test.keysote中的证书条目
keytool -list -keystore test.keystore  
删除密钥库test.keystore中别名为test2的证书条目
keytool -delete -keystore test.keystore -alias test2  
7.修改证书条目的口令
交互的方式
keytool -keypasswd -alias test1 -keystore test.keystore  

http://www.iteye.com/topic/1125183
 */
/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DemoSSLSocketServer implements Runnable {
    
    private static ServerSocket serverSoket = null;
    
    static {
        try {
            serverSoket = new ServerSocket(8080);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String readLineContext = "";
    
    public DemoSSLSocketServer(String readLineContext) {
        this.readLineContext = readLineContext;
    }
    
    public void run() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.readLineContext);
    }
    
    public static void main(String[] args) {
        try {
            
            while (true) {
                System.out.println("listener 8080 start........");
                Socket socket = serverSoket.accept();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                
                StringBuilder sb = new StringBuilder(
                        TxConstants.INITIAL_STR_LENGTH);
                String data = null;
                
                //do{
                    data = reader.readLine();
                    sb.append(data);
                    writer.print(data);
                //}while(!StringUtils.isEmpty(data));
                
                Thread t = new Thread(new DemoSSLSocketServer(sb.toString()));
                t.start();
                
                writer.close();
                if (!socket.isClosed()) {
                    socket.close();
                }
                
                System.out.println("socket server isClosed: "
                        + socket.isClosed());
                System.out.println("socket server isBound: " + socket.isBound());
                System.out.println("listener 8080 end........");
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
}
