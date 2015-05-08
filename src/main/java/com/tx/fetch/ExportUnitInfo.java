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
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.tx.core.dbscript.model.DataSourceTypeEnum;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;
import com.tx.core.paged.model.PagedList;
import com.tx.core.support.poi.excel.model.ExportExcelModel;
import com.tx.fetch.dao.UnitInfoDao;
import com.tx.fetch.dao.impl.UnitInfoDaoImpl;
import com.tx.fetch.model.UnitInfo;
import com.tx.fetch.service.UnitInfoService;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月24日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@SuppressWarnings("unused")
public class ExportUnitInfo {
    
    private static boolean statleCheckingEnabled = true;

    private static int maxConnectionsPerHost = 40;

    private static int maxTaskPoolSize = 20;

    private static int max_error_times = 10;

    private static TaskExecutor taskExecutor = null;

    private synchronized static TaskExecutor getTaskExecutor() {
        if (ExportUnitInfo.taskExecutor != null) {
            return ExportUnitInfo.taskExecutor;
        }
        ThreadPoolTaskExecutor taskExecutorTemp = new ThreadPoolTaskExecutor();
        taskExecutorTemp.setCorePoolSize(maxTaskPoolSize);
        taskExecutorTemp.setMaxPoolSize(maxTaskPoolSize);
        taskExecutorTemp.afterPropertiesSet();

        ExportUnitInfo.taskExecutor = taskExecutorTemp;
        return taskExecutorTemp;
    }
    
    private static DataSource getDataSource() {
        BasicDataSource bds = new BasicDataSource();
        //设置驱动程序
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        //设置连接用户名
        bds.setUsername("lms");
        //设置连接密码
        bds.setPassword("zzxx1122");
        //设置连接地址
        bds.setUrl("jdbc:mysql://192.168.80.206:3306/lms_data_source?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
        //设置初始化连接总数
        bds.setInitialSize(5);
        //设置同时应用的连接总数
        bds.setMaxActive(-1);
        //设置在缓冲池的最大连接数
        bds.setMaxIdle(5);
        //设置在缓冲池的最小连接数
        bds.setMinIdle(0);
        //设置最长的等待时间
        bds.setMaxWait(5000);
        return bds;
    }
    
    private static MyBatisDaoSupport getMyBatisDaoSupport() throws Exception {
        MyBatisDaoSupport res = MyBatisDaoSupportHelper.buildMyBatisDaoSupport("classpath:context/mybatis-config.xml",
                new String[] { "classpath*:com/tx/fetch/**/*SqlMap.xml" },
                DataSourceTypeEnum.MYSQL,
                getDataSource());
        return res;
    }
    
    private static UnitInfoDao getUnitInfoDao() {
        UnitInfoDaoImpl dao = new UnitInfoDaoImpl();
        try {
            dao.setMyBatisDaoSupport(getMyBatisDaoSupport());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }
    
    private static UnitInfoService getUnitInfoService() {
        UnitInfoService res = new UnitInfoService(getUnitInfoDao());
        return res;
    }
    
    public static void main(String[] args) throws IOException {
        File personInfoExportFoler = new File("/Users/rain/Develop/export/unitinfo");
        if(!personInfoExportFoler.exists()){
            FileUtils.forceMkdir(personInfoExportFoler);
        }
        FileUtils.cleanDirectory(personInfoExportFoler);
        UnitInfoService unitInfoService = getUnitInfoService();
        
        //TaskExecutor taskExecutor = getTaskExecutor();
        
        //taskExecutor.execute(task);
        
        boolean hasNext = true;
        int pageIndex = 1;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exported", Boolean.FALSE);
        do{
            System.out.println("query." + pageIndex);
            PagedList<UnitInfo> resPagedList = unitInfoService.queryUnitInfoPagedList(params,pageIndex, 10000);
            List<UnitInfo> resList = resPagedList.getList();
            if(resList == null){
                hasNext = false;
                break;
            }
            
            System.out.println("buildExcel." + pageIndex);
            ExportExcelModel<UnitInfo> excel = unitInfoService.exporteExcel(resList);
           
            File excelFile = new File(personInfoExportFoler, "export_unit_info_" + pageIndex + ".xlsx");
            excelFile.createNewFile();
            OutputStream output = new FileOutputStream(excelFile);
            
            excel.getWorkbook().write(output);
            
            IOUtils.closeQuietly(output);
            
            System.out.println("build_unit_info_" + pageIndex + " resList: " + resList.size() + "count:" + resPagedList.getCount() + " pageIndex: " + resPagedList.getPageIndex());
            if(resList == null || resList.size() == 0 || resList.size() < 10000){
                hasNext = false;
                break;
            }
            pageIndex++;
        }
        while(hasNext);
        
    }
}
