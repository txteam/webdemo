/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年5月31日
 * <修改描述:>
 */
package com.tx.local.creditinfo.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tx.local.clientinfo.facade.ClientInfoFacade;
import com.tx.local.clientinfo.model.Client;
import com.tx.local.creditinfo.model.CreditInfoRecord;
import com.tx.local.creditinfo.model.CreditSingleLinked;
import com.tx.local.creditinfo.service.CreditInfoRecordService;

/**
 * 信用信息控制层<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/creditinfo")
public class CreditInfoController {
    
    /** 信用信息记录业务层 */
    @Resource(name = "creditInfoRecordService")
    private CreditInfoRecordService creditInfoRecordService;
    
    /** 客户信息门面层 */
    @Resource
    private ClientInfoFacade clientInfoFacade;
    
    /**
     * 跳转到信用信息详情页面<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String toDetail(String creditInfoId) {
        //根据信用信息，客户信息动态判断需要进入哪个页面，不同的页面可以定制哪些信息是可以被更新的
        CreditInfoRecord creditInfo = this.creditInfoRecordService
                .findById(creditInfoId);
        //
        Client client = this.clientInfoFacade
                .findById(creditInfo.getClientId());
        
        //以后有需要的重构扩展点即可
        String page = "creditinfo/detail";
        switch (client.getType()) {
            case INSTITUTION:
                page = "institution/institutionCreditInfoDetail";
                break;
            case PERSONAL:
                page = "personal/personalCreditInfoDetail";
                break;
            case ADM_INS:
                page = "adminstitution/admInstitutionCreditInfoDetail";
                break;
            case COO_INS:
                page = "cooinstitution/cooInstitutionCreditInfoDetail";
                break;
            default:
                page = "creditinfo/detail";
                break;
        }
        return page;
    }
    
    /**
     * 查询1：1的信用信息<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return CreditSingleLinked [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/single/{type}/{creditInfoId}", method = RequestMethod.GET)
    public CreditSingleLinked find(
            @PathVariable(value = "type", required = true) String type,
            @PathVariable(value = "creditInfoId", required = true) String creditInfoId) {
        return null;
    }
    
    /**
     * 插入1:1的信用信息<br/>
     * <功能详细描述>
     * @param type
     * @param data [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/single/{type}", method = RequestMethod.POST)
    public void save(@PathVariable(value = "type", required = true) String type,
            @RequestBody Map<String, String> data) {
        //需要考虑上传的数据进行字段过滤，再调用业务层，哪些字段可以进行更新扩展点从controller进行切入
        
        return;
    }
    
    /**
     * 查询1：1的信用信息<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return CreditSingleLinked [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/multip/{type}/{creditInfoId}", method = RequestMethod.GET)
    public CreditSingleLinked query(
            @PathVariable(value = "type", required = true) String type,
            @PathVariable(value = "creditInfoId", required = true) String creditInfoId) {
        return null;
    }
}
