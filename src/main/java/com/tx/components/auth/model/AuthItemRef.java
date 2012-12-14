package com.tx.components.auth.model;

import java.util.Date;

public interface AuthItemRef {
    
    /**
     * 权限引用项的类型
     * 利用该类型
     * 实现            人员权限   AUTHREFTYPE_OPERATOR
     *         临时权限   AUTHREFTYPE_OPERATOR_TEMP
     *         角色权限   AUTHREFTYPE_ROLE
     *         职位权限   ...
     * 这里用String虽没有int查询快，但能让sql可读性增强
     * @return 返回 authRefType
     */
    public abstract String getAuthRefType();
    
    /**
     * 权限关联项id 
     * 可以是角色的id,
     * 可以是职位的id
     * ....
     * @return 返回 refId
     */
    public abstract String getRefId();
    
    /**
     * @return 返回 authId
     */
    public abstract String getAuthId();
    
    /**
     * @return 返回 createOperId
     */
    public abstract String getCreateOperId();
    
    /**
     * @return 返回 createDate
     */
    public abstract Date getCreateDate();
    
    /**
     * @return 返回 endDate
     */
    public abstract String getEndDate();
    
}