/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月8日
 * <修改描述:>
 */
package com.tx.component.mainframe.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tx.component.auth.context.loaderprocessor.childauth.ChildAuthRegister;
import com.tx.component.auth.model.AbstractAuthItemAdapter;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthItemAdapter;
import com.tx.component.auth.model.AuthItemWraperByAdapter;
import com.tx.component.operator.model.VirtualCenter;
import com.tx.component.operator.service.VirtualCenterService;

/**
 * 虚中子集权限注册器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年11月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("auth_register.virtual_center")
public class VirtualCenterChildAuthRegister implements ChildAuthRegister {
    
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /** 虚中心转换为数据权限的适配器 */
    private final AuthItemAdapter<VirtualCenter> adapter = new AbstractAuthItemAdapter<VirtualCenter>() {
        @Override
        public String getParentId(VirtualCenter object, AuthItem parentAuthItem) {
            if (StringUtils.isEmpty(object.getParentId())
                    || object.getParentId().equals(object.getId())) {
                return parentAuthItem.getId();
            } else {
                return parentAuthItem.getId() + "_" + object.getParentId();
            }
        }
        
        /**
         * @param object
         * @param parentAuthItem
         * @return
         */
        @Override
        public String getRefType(VirtualCenter object, AuthItem parentAuthItem) {
            return "VirtualCenter";
        }
    };
    
    /**
     * @param authItem
     * @return
     */
    @Override
    public Set<AuthItem> loadAuthItems(AuthItem parentAuthItem) {
        List<VirtualCenter> vcList = this.virtualCenterService.queryVirtualCenter(null);
        Set<AuthItem> resSet = new HashSet<>();
        if (CollectionUtils.isEmpty(vcList)) {
            return resSet;
        }
        for (VirtualCenter vcTemp : vcList) {
            resSet.add(new AuthItemWraperByAdapter<VirtualCenter>(this.adapter,
                    vcTemp, parentAuthItem));
        }
        return resSet;
    }
}
