/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-15
 * <修改描述:>
 */
package com.tx.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.cxf.common.util.StringUtils;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Ognl {
    
    /**
     * 判断段对象是否为空
     *<功能详细描述>
     * @param obj
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static boolean isEmpty(Object obj) {
        if (obj instanceof Array) {
            return ArrayUtils.isEmpty((Object[]) obj);
        }
        else if (obj instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) obj);
        }
        else if (obj instanceof Map<?, ?>) {
            return MapUtils.isEmpty((Map<?, ?>) obj);
        }
        else if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        }
        else {
            return obj == null;
        }
    }
    
    /**
      * 判断某对象是否非空
      * <功能详细描述>
      * @param obj
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
