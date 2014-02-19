/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.operator.model;

import javax.persistence.Id;


 /**
  * 职位组<br/>
  *     作为职位的补充<br/>
  *     因业务需要（需要构建销售分组，催收分组等，用于绩效计算）<br/>
  *     
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年2月19日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class PostGroup {
    
    /** 职位分组id */
    @Id
    private String id;
    
    /** 职位分组编码 */
    private String code;
    
    /** 职位分组名 */
    private String name;
    
    /** 职位分组是否有效 */
    private boolean valid;
    
    /** 备注 */
    private String remark;

    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
