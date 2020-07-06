/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月11日
 * <修改描述:>
 */
package com.tx.local.qxb.client;

/**
 * 企信宝结果枚举<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum QxbResultStatusEnum {
    
    S_200(200, "查询成功"),
    
    S_201(201, "查询无结果"),
    
    S_207(207, "查询错误，请联系技术人员"),
    
    S_208(208, "参数名错误或参数为空"),
    
    S_209(209, "接口查询异常，请联系技术人员"),
    
    S_213(213, "调用次数超过今日额度限制"),
    
    S_101(101, "AppKey无效"),
    
    S_102(102, "账户余额不足"),
    
    S_103(103, "AppKey被停用"),
    
    S_104(104, "IP未授权"),
    
    S_105(105, "未授权调用该接口"),
    
    S_109(105, "接口被停用，等其他状态");
    
    /** 状态 */
    private final int status;
    
    /** 错误消息 */
    private final String message;
    
    /** 企信宝结果枚举 */
    private QxbResultStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    /**
     * @return 返回 status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * @return 返回 message
     */
    public String getMessage() {
        return message;
    }
}
