/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.basicdata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tx.local.basicdata.service.BankInfoServiceNew;
import com.tx.local.common.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.tx.component.basicdata.helper.BasicDataFileHelper;
//import com.tx.component.file.context.FileContext;
//import com.tx.component.file.model.FileDefinition;
//import com.tx.component.remote.model.RemoteResult;
import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.model.BankInfo;

/**
 * BankInfo显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/bankinfo")
public class BankInfoController {
    
    //银行信息业务cen 
    @Resource
    private BankInfoServiceNew bankInfoServiceNew;
    
    /**
     * 跳转到查询BankInfo列表页面<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryBankInfoList")
    public String toQueryBankInfoList(ModelMap response) {
        return "/basicdata/queryBankInfoList";
    }

    @RequestMapping("/toQueryBankInfoPagedList")
    public String toQueryBankInfoPagedList(ModelMap response) {
        return "/basicdata/queryBankInfoPagedList";
    }
    
    /**
     * 跳转到添加BankInfo页面<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddBankInfo")
    public String toAddBankInfo(ModelMap response) {
        BankInfo bankInfo = new BankInfo();
        response.put("bankInfo", bankInfo);
        
        return "/basicdata/addBankInfo";
    }
    
    /**
     * 跳转到编辑BankInfo页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toUpdateBankInfo")
    public String toUpdateBankInfo(
            @RequestParam("bankInfoId") String bankInfoId, ModelMap response) {
        BankInfo resBankInfo = this.bankInfoServiceNew.findById(bankInfoId);
        response.put("bankInfo", resBankInfo);
        
        return "/basicdata/updateBankInfo";
    }
    
    /**
     * 判断BankInfo:
     *  code
     *
     * 是否已经被使用
     * @param uniqueGetterName
     * @param excludeBankInfoId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateCodeIsExist")
    public Map<String, String> validateCodeIsExist(
            @RequestParam("code") String code,
            @RequestParam(value = "id", required = false) String excludeBankInfoId,
            @RequestParam MultiValueMap<String, String> request) {
        
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("code", code);
        
        boolean flag = this.bankInfoServiceNew.exist(key2valueMap,
                excludeBankInfoId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "可用的银行编码.");
        } else {
            resMap.put("error", "重复的银行编码.");
        }
        return resMap;
    }
    
    /**
     * 判断BankInfo:
     *  code
     *
     * 是否已经被使用
     * @param uniqueGetterName
     * @param excludeBankInfoId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateNameIsExist")
    public Map<String, String> validateNameIsExist(
            @RequestParam("name") String name,
            @RequestParam(value = "id", required = false) String excludeBankInfoId,
            @RequestParam MultiValueMap<String, String> request) {
        
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("name", name);
        
        boolean flag = this.bankInfoServiceNew.exist(key2valueMap,
                excludeBankInfoId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "可用的银行名.");
        } else {
            resMap.put("error", "重复的银行名.");
        }
        return resMap;
    }
    
    /**
     * 查询BankInfo列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<BankInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<BankInfo> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "modifyAble", required = false) Boolean modifyAble,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        
        params.put("modifyAble", modifyAble);
        params.put("code", request.getFirst("code"));
        params.put("aliases", request.getFirst("aliases"));
        params.put("name", request.getFirst("name"));
        
        List<BankInfo> resList = this.bankInfoServiceNew.queryList(valid, params);
        
        return resList;
    }
    
    /**
     * 查询BankInfo分页列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<BankInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<BankInfo> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "modifyAble", required = false) Boolean modifyAble,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Pageable pageable,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        
        params.put("modifyAble", modifyAble);
        params.put("code", request.getFirst("code"));
        params.put("aliases", request.getFirst("aliases"));
        params.put("name", request.getFirst("name"));
        
        PagedList<BankInfo> resPagedList = this.bankInfoServiceNew
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(BankInfo bankInfo) {
        this.bankInfoServiceNew.insert(bankInfo);
        return true;
    }
    
    /**
     * 更新组织<br/>
     *<功能详细描述>
     * @param bankInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(BankInfo bankInfo) {
        this.bankInfoServiceNew.updateById(bankInfo);
        
        return true;
    }
    
    /**
     * 删除指定BankInfo<br/> 
     *<功能详细描述>
     * @param bankInfoId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(
            @RequestParam(value = "bankInfoId") String bankInfoId) {
        boolean resFlag = this.bankInfoServiceNew.deleteById(bankInfoId);
        return resFlag;
    }
    
    /**
     * 禁用BankInfo
     * @param bankInfoId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(
            @RequestParam(value = "bankInfoId") String bankInfoId) {
        boolean resFlag = this.bankInfoServiceNew.disableById(bankInfoId);
        return resFlag;
    }
    
    /**
     * 启用BankInfo<br/>
     *<功能详细描述>
     * @param bankInfoId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(
            @RequestParam(value = "bankInfoId") String bankInfoId) {
        boolean resFlag = this.bankInfoServiceNew.enableById(bankInfoId);
        return resFlag;
    }
    
    //    /**
    //     * 更新银行信息中文件信息<br/>
    //     * <功能详细描述>
    //     * @param bankInfo
    //     * @return [参数说明]
    //     * 
    //     * @return boolean [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    @RequestMapping("/saveLogoFile")
    //    @ResponseBody
    //    public RemoteResult saveLogoFile(
    //            @RequestParam(value = "bankInfoId") String bankInfoId,
    //            @RequestParam(value = "file", required = false) CommonsMultipartFile uploadFile) {
    //        RemoteResult res = new RemoteResult();
    //        BankInfo bankInfo = this.bankInfoServiceNew.findById(bankInfoId);
    //        if (bankInfo == null) {
    //            res.setResult(false);
    //            res.setErrorMessage(MessageUtils.format("指定的银行信息不存在.bankInfoId:{}",
    //                    bankInfoId));
    //            return res;
    //        }
    //        if (uploadFile == null) {
    //            //删除附件
    //            if (!StringUtils.isEmpty(bankInfo.getLogoFileId())) {
    //                FileContext.getContext().deleteById(bankInfo.getLogoFileId());
    //            }
    //            bankInfo.setLogoFileId(null);
    //            bankInfo.setLogoUrl(null);
    //            this.bankInfoServiceNew.updateById(bankInfo);
    //            res.setResult(true);
    //            res.setData(bankInfo);
    //        } else {
    //            //添加附件
    //            String fileExtenstion = StringUtils.getFilenameExtension(uploadFile.getFileItem()
    //                    .getName());
    //            String relativePath = BasicDataFileHelper.generateRelativePath(MessageUtils.format("{}/logo/{}.{}",
    //                    "BankInfo",
    //                    bankInfo.getCode(),
    //                    fileExtenstion),
    //                    fileExtenstion);
    //            InputStream input = null;
    //            FileDefinition fileDefinition = null;
    //            try {
    //                input = uploadFile.getFileItem().getInputStream();
    //                fileDefinition = FileContext.getContext().save("basicdata",
    //                        relativePath,
    //                        input);
    //                
    //                bankInfo.setLogoFileId(fileDefinition.getId());
    //                bankInfo.setLogoUrl(fileDefinition.getViewUrl());
    //                
    //                res.setResult(true);
    //                res.setData(fileDefinition);
    //            } catch (Exception e) {
    //                logger.info(e.getMessage(), e);
    //                
    //                res.setResult(false);
    //                res.setErrorMessage(e.getMessage());
    //            } finally {
    //                IOUtils.closeQuietly(input);
    //            }
    //            this.bankInfoServiceNew.updateById(bankInfo);
    //        }
    //        
    //        return res;
    //    }
}