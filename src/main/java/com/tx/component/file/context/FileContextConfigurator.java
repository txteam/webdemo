/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月11日
 * <修改描述:>
 */
package com.tx.component.file.context;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tx.component.file.dao.impl.FileDefinitionDaoImpl;
import com.tx.component.file.service.FileDefinitionService;
import com.tx.core.dbscript.context.DBScriptExecutorContext;
import com.tx.core.dbscript.model.DataSourceTypeEnum;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;

/**
 * 文件容器配置器<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
public class FileContextConfigurator implements InitializingBean {
    
    protected Logger logger = LoggerFactory.getLogger(FileContext.class);
    
    @Bean(name = "fileDefinitionMyBatisDaoSupport")
    public MyBatisDaoSupport fileDefinitionMyBatisDaoSupport() throws Exception {
        // FIXME Rain 这里有问题. 如果 dataSource 为空.(这个方法的调用在自动注入参数之前)就会报错,询问彭总后再修改
        if (this.dataSource == null) {
            return null;
        }
        MyBatisDaoSupport res = MyBatisDaoSupportHelper.buildMyBatisDaoSupport(this.mybatisConfigLocation,
                new String[] { "classpath*:com/tx/component/file/**/*SqlMap.xml" },
                this.dataSourceType,
                this.dataSource);
        return res;
    }
    
    @Bean(name = "fileDefinitionDao")
    public FileDefinitionDaoImpl fileDefinitionDao() {
        FileDefinitionDaoImpl res = new FileDefinitionDaoImpl();
        return res;
    }
    
    @Bean(name = "fileDefinitionService")
    public FileDefinitionService fileDefinitionService() {
        FileDefinitionService res = new FileDefinitionService();
        return res;
    }
    
    /** 数据源 */
    private DataSource dataSource;
    
    /** 数据源类型 */
    private DataSourceTypeEnum dataSourceType;
    
    /** mybatis的配置文件所在目录 */
    private String mybatisConfigLocation = "classpath:context/mybatis-config.xml";
    
    /** 数据脚本自动执行器 */
    protected DBScriptExecutorContext dbScriptExecutorContext;
    
    /** 是否自动执行数据脚本 */
    protected boolean databaseSchemaUpdate = false;
    
    /** 如果没有指定系统，则默认的系统id */
    protected String system = "default";
    
    /** 如果没有指定系统，则默认的系统id */
    protected String module = "default";
    
    /** 本地文件存储容器的基础路径: */
    //    protected String location;
    
    /** 如果存在指定的driver则使用指定的driver,如果不存在，则使用defaultFileDefitionResourceDriver */
    protected FileDefinitionResourceDriver driver;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        AssertUtils.notNull(system, "system is null.");
        AssertUtils.notNull(module, "module is null.");
        //        AssertUtils.notEmpty(location, "location is empty.");
    }
    
    /**
     * @param 对dataSource进行赋值
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * @param 对dataSourceType进行赋值
     */
    public void setDataSourceType(DataSourceTypeEnum dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
    
    /**
     * @param 对mybatisConfigLocation进行赋值
     */
    public void setMybatisConfigLocation(String mybatisConfigLocation) {
        this.mybatisConfigLocation = mybatisConfigLocation;
    }
    
}
