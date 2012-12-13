/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-5
 * <修改描述:>
 */
package com.tx.webdemo.demo.other.reflect;

import com.tx.core.datasource.DataSourceFactoryBean;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-11-5]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class Test {
    public static void main(String[] args) throws Exception{
        try {
            Class c = Class.forName("com.tx.core.datasource.DataSourceFactoryBean");
            DataSourceFactoryBean d = (DataSourceFactoryBean)c.newInstance();
            System.out.println(d.getObjectType());
            
            //System.out.println(TestC.class.getDeclaredField("ccc"));
            //System.out.println(TestC.class.getDeclaredField("bbb"));
            //System.out.println(TestC.class.getDeclaredField("aaa"));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
