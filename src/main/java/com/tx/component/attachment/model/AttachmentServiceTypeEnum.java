/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.attachment.model;

/**
 * 单据附件类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum AttachmentServiceTypeEnum {
    
    INCOME_PROOF("INCOME_PROOF","收入证明"),
    
    LOAN_BILL("INCOME_PROOF","申请单"),
    
    IDENTITY_PROOF("IDENTITY_PROOF","身份证明");
    
    private String code;
    
    private String name;

    /** <默认构造函数> */
    private AttachmentServiceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
}
