/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.model;

import java.util.Date;


 /**
  * <demo模型>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class Demo {
    
    private String name;
    
    private String passowrd;
    
    private String email;
    
    private Date createDate;

    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 返回 passowrd
     */
    public String getPassowrd() {
        return passowrd;
    }

    /**
     * @param 对passowrd进行赋值
     */
    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    /**
     * @return 返回 email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param 对email进行赋值
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
