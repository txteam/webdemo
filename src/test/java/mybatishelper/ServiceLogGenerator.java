/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-8
 * <修改描述:>
 */
package mybatishelper;

import com.tx.component.mainframe.servicelog.LoginLog;
import com.tx.component.servicelog.defaultimpl.TXServiceLogDBScriptHelper;
import com.tx.core.dbscript.model.DataSourceTypeEnum;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ServiceLogGenerator {
    
    public static void main(String[] args) {
        String dbScript = TXServiceLogDBScriptHelper.generateDBScriptContent(LoginLog.class,
                DataSourceTypeEnum.ORACLE,
                "UTF-8");
        System.out.println(dbScript);
    }
}
