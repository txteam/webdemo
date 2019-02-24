/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年2月13日
 * <修改描述:>
 */
package com.tx.component.remote.helper;

import com.tx.component.remote.model.RemoteResult;


 /**
  * Remote辅助类<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年2月13日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public abstract class RemoteHelper {
    
    /**
      * Remote调用辅助处理类<br/>
      * <功能详细描述>
      * @param handler
      * @return [参数说明]
      * 
      * @return RemoteResult [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static RemoteResult handle(RemoteHandler handler){
        if(handler == null){
            return new RemoteResult(999, "handler is null");
        }
        RemoteResult result = handler.handle();
        return result;
    }
}
