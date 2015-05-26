/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年4月27日
 * <修改描述:>
 */
package com.tx.votes;


 /**
  * 代理ip地址信息
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2015年4月27日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ProxyIpAddressInfo {
    
    private String ipAddress;
    
    private String port;
    
    private String area;

    /**
     * @return 返回 ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param 对ipAddress进行赋值
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return 返回 port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param 对port进行赋值
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return 返回 area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param 对area进行赋值
     */
    public void setArea(String area) {
        this.area = area;
    }
}
