/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import javax.persistence.Column;

import com.tx.component.servicelogger.annotation.ServiceLog;
import com.tx.component.servicelogger.model.AbstractServiceLogger;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 操作人员敏感操作日志<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ServiceLog("CL_SEC_OPERATE_LOG")
public class ClientSecOperateLog extends AbstractServiceLogger {
    
    //:操作人员id
    @ApiModelProperty(value = "客户ID", hidden = true, position = 0)
    @Column(nullable = false, length = 64)
    private String clientId;
    
    //:操作人员id
    @ApiModelProperty(value = "用户名", position = 1)
    @Column(nullable = false, length = 64)
    private String clientUsername;
    
    //:操作记录
    @ApiModelProperty(value = "操作记录", position = 2)
    @Column(nullable = false, length = 64)
    private String message;
    
    //:客户端ip地址
    @ApiModelProperty(value = "IP地址", position = 3)
    @Column(nullable = false, length = 64)
    private String ipAddress;
}
