/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月25日
 * <修改描述:>
 */
package com.tx.component.remote.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tx.component.remote.model.RemoteResult;
import com.tx.core.exceptions.SILException;

/**
 * 远程调用处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class RemoteHandler {
    
    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(RemoteHandler.class);
    
    /**
      * 处理Http请求<br/>
      * <功能详细描述>
      * @param request
      * @param msgType
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public final RemoteResult handle() {
        RemoteResult remoteResult = null;
        try {
            //处理业务逻辑
            Object data = doHandle();
            remoteResult = new RemoteResult(data);
        } catch (SILException e) {
            logger.error(e.getMessage(), e);
            
            remoteResult = new RemoteResult(e.getErrorCode(),
                    e.getErrorMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            
            remoteResult = new RemoteResult(999, e.getMessage());
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            
            remoteResult = new RemoteResult(999, e.getMessage());
        }
        return remoteResult;
    }
    
    /**
      * 远程调用处理器<br/>
      * <功能详细描述>
      * @param request
      * @param response
      * @return [参数说明]
      * 
      * @return RemoteResult [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected abstract RemoteResult doHandle();
}
