/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月24日
 * <修改描述:>
 */
package com.tx.fetch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.tx.core.dbscript.model.DataSourceTypeEnum;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;
import com.tx.core.paged.model.PagedList;
import com.tx.core.support.poi.excel.model.ExportExcelModel;
import com.tx.fetch.dao.PersonInfoDao;
import com.tx.fetch.dao.impl.PersonInfoDaoImpl;
import com.tx.fetch.model.PersonInfo;
import com.tx.fetch.service.PersonInfoService;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月24日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ExportPersonInfo {
    
    private static DataSource getDataSource() {
        BasicDataSource bds = new BasicDataSource();
        // 设置驱动程序
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        // 设置连接用户名
        bds.setUsername("lms");
        // 设置连接密码
        bds.setPassword("zzxx1122");
        // 设置连接地址
        bds.setUrl("jdbc:mysql://192.168.80.206:3306/lms_data_source?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
        // 设置初始化连接总数
        bds.setInitialSize(5);
        // 设置同时应用的连接总数
        bds.setMaxActive(-1);
        // 设置在缓冲池的最大连接数
        bds.setMaxIdle(5);
        // 设置在缓冲池的最小连接数
        bds.setMinIdle(0);
        // 设置最长的等待时间
        bds.setMaxWait(5000);
        return bds;
    }

    private static MyBatisDaoSupport getMyBatisDaoSupport() throws Exception {
        MyBatisDaoSupport res = MyBatisDaoSupportHelper.buildMyBatisDaoSupport(
                "classpath:context/mybatis-config.xml",
                new String[] { "classpath*:com/tx/fetch/**/*SqlMap.xml" },
                DataSourceTypeEnum.MYSQL, getDataSource());
        return res;
    }

    private static PersonInfoDao getPersonInfoDao() {
        PersonInfoDaoImpl dao = new PersonInfoDaoImpl();
        try {
            dao.setMyBatisDaoSupport(getMyBatisDaoSupport());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }

    private static PersonInfoService getPersonInfoService() {
        PersonInfoService res = new PersonInfoService(getPersonInfoDao());
        return res;
    }
    
    public static void main(String[] args) throws IOException {
        File personInfoExportFoler = new File("/Users/rain/Develop/export/personinfo");
        if(!personInfoExportFoler.exists()){
            FileUtils.forceMkdir(personInfoExportFoler);
        }
        FileUtils.cleanDirectory(personInfoExportFoler);
        PersonInfoService personInfoService = getPersonInfoService();
        
        boolean hasNext = true;
        int pageIndex = 1;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exported", Boolean.FALSE);
        params.put("idCardNumberIsNotNull", Boolean.TRUE);
        do{
            System.out.println("query." + pageIndex);
            PagedList<PersonInfo> resPagedList = personInfoService.queryPersonInfoPagedList(params,pageIndex, 10000);
            List<PersonInfo> resList = resPagedList.getList();
            if(resList == null){
                hasNext = false;
                break;
            }
            
            System.out.println("buildExcel." + pageIndex);
            ExportExcelModel<PersonInfo> excel = personInfoService.exporteExcel(resList);
           
            File excelFile = new File(personInfoExportFoler, "export_person_info_" + pageIndex + ".xlsx");
            excelFile.createNewFile();
            OutputStream output = new FileOutputStream(excelFile);
            
            excel.getWorkbook().write(output);
            
            IOUtils.closeQuietly(output);
            
            System.out.println("build_person_info_" + pageIndex);
            if(resList == null || resList.size() == 0 || resList.size() < 10000){
                hasNext = false;
                break;
            }
            pageIndex++;
        }
        while(hasNext);
        
    }
}
