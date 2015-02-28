/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.file.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tx.component.file.dao.FileDefinitionDao;
import com.tx.component.file.model.FileDefinition;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * FileDefinition的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("fileDefinitionService")
public class FileDefinitionService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(FileDefinitionService.class);
    
    @Resource(name = "fileDefinitionDao")
    private FileDefinitionDao fileDefinitionDao;
    
    /**
      * 将fileDefinition实例插入数据库中保存
      * 1、如果fileDefinition为空时抛出参数为空异常
      * 2、如果fileDefinition中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertFileDefinition(FileDefinition fileDefinition) {
        AssertUtils.notNull(fileDefinition, "fileDefinition is null.");
        AssertUtils.notEmpty(fileDefinition.getFilename(),
                "fileDefinition.filename is null.");
        AssertUtils.notEmpty(fileDefinition.getRelativePath(),
                "fileDefinition.filename is null.");
        
        Date now = new Date();
        fileDefinition.setCreateDate(now);
        fileDefinition.setLastUpdateDate(now);
        if (StringUtils.isEmpty(fileDefinition.getFilenameExtension())) {
            fileDefinition.setFilenameExtension(StringUtils.getFilenameExtension(fileDefinition.getFilename()));
        }
        
        //设置默认数据
        this.fileDefinitionDao.insertFileDefinition(fileDefinition);
    }
    
    /**
      * 将对应的记录移除到历史表<br/>
      * <功能详细描述>
      * @param fileDefinitionId [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void moveToHisByFileDefinitionId(String fileDefinitionId) {
        AssertUtils.notEmpty(fileDefinitionId, "fileDefinitionId is empty.");
        FileDefinition fileDefinition = findFileDefinitionById(fileDefinitionId);
        
        Date now = new Date();
        fileDefinition.setDeleteDate(now);
        
        this.fileDefinitionDao.insertFileDefinitionToHis(fileDefinition);
        deleteById(fileDefinitionId);
    }
    
    /**
     * 根据id删除fileDefinition实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        FileDefinition condition = new FileDefinition();
        condition.setId(id);
        return this.fileDefinitionDao.deleteFileDefinition(condition);
    }
    
    /**
      * 根据Id查询FileDefinition实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return FileDefinition [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public FileDefinition findFileDefinitionById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        FileDefinition condition = new FileDefinition();
        condition.setId(id);
        
        FileDefinition res = this.fileDefinitionDao.findFileDefinition(condition);
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param fileDefinition
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(FileDefinition fileDefinition) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(fileDefinition, "fileDefinition is null.");
        AssertUtils.notEmpty(fileDefinition.getId(),
                "fileDefinition.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", fileDefinition.getId());
        
        //需要更新的字段
        updateRowMap.put("deleteDate", fileDefinition.getDeleteDate());
        updateRowMap.put("relativePath", fileDefinition.getRelativePath());
        updateRowMap.put("filenameExtension",
                fileDefinition.getFilenameExtension());
        updateRowMap.put("filename", fileDefinition.getFilename());
        updateRowMap.put("createDate", fileDefinition.getCreateDate());
        updateRowMap.put("lastUpdateDate", fileDefinition.getLastUpdateDate());
        
        int updateRowCount = this.fileDefinitionDao.updateFileDefinition(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
