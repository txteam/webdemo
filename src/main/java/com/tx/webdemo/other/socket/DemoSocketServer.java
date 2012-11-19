/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-29
 * <修改描述:>
 */
package com.tx.webdemo.other.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.tx.core.TxConstants;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DemoSocketServer implements Runnable {
    
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
    
    public DemoSocketServer(String readLineContext) {
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
                
                Thread t = new Thread(new DemoSocketServer(sb.toString()));
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
