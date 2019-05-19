///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2014年6月12日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.model;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.tx.component.basicdata.model.BasicDataEnum;
//import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;
//
///**
// * 还款渠道类型<br/>
// * 在不同的还款渠道类型中，平息的最后一步分配的费用项都不太相同<br/>
// * ZS: 有且仅有一种还款渠道类型XEDK_GLZX<br/>
// * CQ: 有三种渠道类型 XEDK_GLZX,XEXD,GLZX<br/>
// * 
// * @author Tim.peng
// * @version [版本号, 2014年6月12日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
//public enum RepayChannelTypeEnum implements BasicDataEnum {
//    
//    /** 咨询公司_TO_贷款公司 */
//    ZX_DK("ZX_DK", "咨询公司_TO_贷款公司"),
//    
//    /** 贷款公司_TO_咨询公司 */
//    DK_ZX("DK_ZX", "贷款公司_TO_咨询公司"),
//    
//    /** 贷款公司_TO_咨询公司 */
//    DK_FOR_ZX("DK_ZX", "贷款公司_TO_咨询公司"),
//    
//    /** 贷款公司 */
//    DK("DK", "贷款公司"),
//    
//    /** 咨询公司 */
//    ZX("ZX", "咨询公司");
//    
//    /** code */
//    private String code;
//    
//    /** name */
//    private String name;
//    
//    private RepayChannelTypeEnum(String code, String name) {
//        this.code = code;
//        this.name = name;
//    }
//    
//    public String getName() {
//        return name;
//    }
//    
//    public void setName(String name) {
//        this.name = name;
//    }
//    
//    public String getCode() {
//        return code;
//    }
//    
//    public void setCode(String code) {
//        this.code = code;
//    }
//}
