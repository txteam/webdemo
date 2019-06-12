/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年6月13日
 * <修改描述:>
 */
package com.tx.local.clientinfo.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.tx.local.clientinfo.facade.ClientPromotionChannelFacade;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年6月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@FeignClient(name = "${feign.local.module}/clientPromotionChannel", url = "${feign.local.url}", path = "/api/clientPromotionChannel")
public interface ClientPromotionChannelAPIClient
        extends ClientPromotionChannelFacade {
    
}
