package com.tx.local.loanaccount.service;
///*
// * 描          述:  <描述>
// * 修  改   人:  
// * 修改时间:  
// * <修改描述:>
// */
//package com.tx.local.deduct.task.service;
//
//import java.util.Date;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.mainframe.context.WebContextUtils;
//import com.tx.component.operator.model.Operator;
//import com.tx.component.operator.model.Organization;
//import com.tx.component.operator.service.OrganizationService;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.UUIDUtils;
//import com.tx.lms.support.deduct.dao.DeductFileMessageRecordDao;
//import com.tx.lms.support.deduct.model.DeductFileMessageRecord;
//import com.tx.lms.support.deduct.model.FileRecordTypeEnum;
//
///**
// * ChinapayFileMessageRecord的业务层 <功能详细描述>
// * 
// * @author
// * @version [版本号, ]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component("chinapayFileMessageRecordService")
//public class ChinapayFileMessageRecordService {
//    
//    @SuppressWarnings("unused")
//    private Logger logger = LoggerFactory.getLogger(ChinapayFileMessageRecordService.class);
//    
//    @Resource(name = "deductFileMessageRecordDao")
//    private DeductFileMessageRecordDao deductFileMessageRecordDao;
//    
//    @Resource(name = "organizationService")
//    private OrganizationService organizationService;
//    
//    /**
//     * 构建导入导出记录实体 <br/>
//     * <功能详细描述>
//     * 
//     * @param recordTypeEnum
//     * @param filePath
//     * @return [参数说明]
//     * 
//     * @return ChinapayFileMessageRecord [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public DeductFileMessageRecord bulidFileRecord(String vcid,
//            FileRecordTypeEnum recordTypeEnum, String filePath) {
//        // 获取当前操作人
//        Operator op = WebContextUtils.getCurrentOperator();
//        
//        AssertUtils.notNull(op,
//                "session 丢失，WebContextUtils.getCurrentOperator() 获取不到当前操作人.");
//        Organization organization = organizationService.findOrganizationById(op.getOrganization()
//                .getId());
//        DeductFileMessageRecord cfr = new DeductFileMessageRecord();
//        if (null != organization) {
//            cfr.setOrganizationId(organization.getId());
//        }
//        cfr.setId(UUIDUtils.generateUUID());
//        cfr.setCreateDate(new Date());
//        cfr.setFilePath(filePath);
//        cfr.setOperatorId(op.getId());
//        cfr.setOperatorName(op.getUserName());
//        cfr.setRecordType(recordTypeEnum);
//        cfr.setVcid(vcid);
//        return cfr;
//    }
//    
//    /**
//     * 根据Id查询ChinapayFileMessageRecord实体 1、当id为empty时抛出异常 <功能详细描述>
//     * 
//     * @param id
//     * @return [参数说明]
//     * 
//     * @return ChinapayFileMessageRecord [返回类型说明]
//     * @exception throws 可能存在数据库访问异常DataAccessException
//     * @see [类、类#方法、类#成员]
//     */
//    public DeductFileMessageRecord findChinapayFileMessageRecordById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        DeductFileMessageRecord condition = new DeductFileMessageRecord();
//        condition.setId(id);
//        
//        DeductFileMessageRecord res = this.deductFileMessageRecordDao.findDeductFileMessageRecord(condition);
//        return res;
//    }
//    
//    /**
//     * 将chinapayFileMessageRecord实例插入数据库中保存 1、如果chinapayFileMessageRecord为空时抛出参数为空异常 2、如果chinapayFileMessageRecord中部分必要参数为非法值时抛出参数不合法异常 <功能详细描述>
//     * 
//     * @param district [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public void insertChinapayFileMessageRecord(
//            DeductFileMessageRecord chinapayFileMessageRecord) {
//        AssertUtils.notNull(chinapayFileMessageRecord,
//                "chinapayFileMessageRecord is null.");
//        AssertUtils.notEmpty(chinapayFileMessageRecord.getId(),
//                "chinapayFileMessageRecord.id is empty.");
//        
//        this.deductFileMessageRecordDao.insertDeductFileMessageRecord(chinapayFileMessageRecord);
//    }
//}
