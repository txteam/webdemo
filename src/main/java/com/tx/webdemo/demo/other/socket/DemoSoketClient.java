/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-29
 * <修改描述:>
 */
package com.tx.webdemo.demo.other.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DemoSoketClient {
    
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);  
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));  
        
        writer.write("hello\ndasdfa");
        writer.flush();
        
        System.out.println("writer flush.");
        
        System.out.println(reader.readLine());
        
        writer.close();
        reader.close();
        s.close(); 
    }
    
}
