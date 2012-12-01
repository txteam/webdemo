/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.webdemo.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.demo.dao.OperatorDao;
import com.tx.webdemo.demo.model.Operator;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-1]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class OperatorDaoImpl implements OperatorDao{
    
    @Resource(name="myBatisDaoSupport")
    private MyBatisDaoSupport mybatisDaoSupport;

    /**
     * @param condition
     * @return
     */
    @Override
    public Operator findOperator(Operator condition) {
        return (Operator)this.mybatisDaoSupport.find("operator.findOperator", condition);
    }

    /**
     * @param operator
     */
    @Override
    public void addOperator(Operator operator) {
        this.mybatisDaoSupport.insert("operator.addOperator", operator);
    }

    /**
     * @param queryCondition
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Operator> queryOperatorList(Map<String, Object> queryCondition) {
        return (List<Operator>)this.mybatisDaoSupport.queryList("operator.queryOperatorList", queryCondition);
    }

    /**
     * @param paraObj
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public PagedList<Operator> queryOperatorPagedList(
            Map<String, Object> paraObj, int pageIndex, int pageSize) {
        return (PagedList<Operator>)this.mybatisDaoSupport.queryPagedList("operator.queryOperatorList", paraObj, pageIndex, pageSize);
    }

    /**
     * @param condition
     * @return
     */
    @Override
    public int delOperator(Operator condition) {
        return this.mybatisDaoSupport.delete("operator.delOperator", condition);
    }

    /**
     * @param condition
     * @return
     */
    @Override
    public int updateOperator(Operator condition) {
        // TODO Auto-generated method stub
        return 0;
    }
}
