/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.file.context;

import java.util.Map;

/**
 * 文件容器会话插件<br/>
 *    用于支持容器，方便的提取当前会话中的值，便于兼容各大容器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SessionPlugin {
    
    /**
      * 获取会话中的数据值<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Map<String,Object> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    Map<String, Object> getSessionMap();
}
