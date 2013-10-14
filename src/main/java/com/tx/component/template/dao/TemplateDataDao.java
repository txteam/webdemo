/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-14
 * <修改描述:>
 */
package com.tx.component.template.dao;

import java.util.Map;

import com.tx.component.template.model.TemplateTable;


 /**
  * 模板数据持久层
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface TemplateDataDao {
    
    public String insert(TemplateTable templateTable,Map<String, Object> rowMap);
}
