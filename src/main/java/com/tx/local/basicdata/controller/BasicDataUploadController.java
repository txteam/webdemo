///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年1月23日
// * <修改描述:>
// */
//package com.tx.local.basicdata.controller;
//
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.io.IOUtils;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import com.tx.component.file.context.FileContext;
//import com.tx.component.file.model.FileDefinition;
//import com.tx.core.TxConstants;
//import com.tx.core.exceptions.util.AssertUtils;
//
///**
// * 基础数据上传控制层<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年1月23日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Controller("basicDataUploadController")
//@RequestMapping("/basicdata/uploader")
//public class BasicDataUploadController {
//    
//    //日志记录器
//    private Logger logger = LoggerFactory.getLogger(BasicDataUploadController.class);
//    
//    //文件容器
//    @Resource(name = "fileContext")
//    private FileContext fileContext;
//    
//    /**
//      * 上传文件<br/>
//      * <功能详细描述>
//      * @param request
//      * @param basicDataType
//      * @param relativePath
//      * @param uploadFile
//      * @return [参数说明]
//      * 
//      * @return FileDefinition [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/save")
//    public Map<String, Object> save(HttpServletRequest request,
//            @RequestParam(value = "basicDataType") String basicDataType,
//            @RequestParam(value = "relativePath") String relativePath,
//            @RequestParam(value = "byDate", required = false) Boolean byDate,
//            @RequestParam("file") CommonsMultipartFile uploadFile) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        if (uploadFile == null || uploadFile.getFileItem() == null) {
//            resMap.put("result", false);
//            resMap.put("errorMessage", "上传文件不能为空.");
//            return resMap;
//        }
//        if (StringUtils.isEmpty(relativePath)) {
//            //存储相对路径，或文件名至少传入一个
//            resMap.put("result", false);
//            resMap.put("errorMessage", "上传文件存储路径不能为空.");
//            return resMap;
//        }
//        
//        //获取对应的存储相对
//        String fileExtenstion = StringUtils.getFilenameExtension(uploadFile.getFileItem()
//                .getName());
//        relativePath = handleRelativePath(relativePath,
//                basicDataType,
//                fileExtenstion,
//                byDate == null ? false : true);
//        InputStream input = null;
//        FileDefinition fileDefinition = null;
//        try {
//            input = uploadFile.getFileItem().getInputStream();
//            fileDefinition = fileContext.save("basicdata", relativePath, input);
//            
//            resMap.put("result", true);
//            resMap.put("fileDefinition", fileDefinition);
//        } catch (Exception e) {
//            logger.info(e.getMessage(), e);
//            
//            resMap.put("result", false);
//            resMap.put("errorMessage", e.getMessage());
//        } finally {
//            IOUtils.closeQuietly(input);
//        }
//        return resMap;
//    }
//    
//    /**
//      * 处理请求中存储的相对路径<br/>
//      * <功能详细描述>
//      * @param relativePath
//      * @param filename
//      * @param basicDataType
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private String handleRelativePath(String relativePath,
//            String basicDataType, String fileExtenstion, boolean byDate) {
//        AssertUtils.notEmpty(relativePath, "relativePath is empty.");
//        AssertUtils.notEmpty(basicDataType, "basicDataType is empty.");
//        
//        //整理存储的relativePath相对路径
//        relativePath = StringUtils.cleanPath(relativePath);
//        while (relativePath.startsWith("/")) {
//            //去除相对路径前面的"/"
//            relativePath = relativePath.substring(1, relativePath.length());
//        }
//        if (!relativePath.endsWith(fileExtenstion)) {
//            //为存储相对路径追加后缀
//            relativePath = relativePath + "." + fileExtenstion;
//        }
//        
//        //整理基础数据类型
//        basicDataType = StringUtils.cleanPath(basicDataType);
//        while (basicDataType.startsWith("/")) {
//            //去除相对路径前面的"/"
//            basicDataType = basicDataType.substring(1, basicDataType.length());
//        }
//        
//        //截取relativePath中的basicDataType
//        if (relativePath.startsWith(basicDataType + "/")) {
//            relativePath = relativePath.substring((basicDataType + "/").length(),
//                    relativePath.length());
//        }
//        
//        //拼接存储路径
//        StringBuilder relativePathSb = new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH);
//        DateTime now = DateTime.now();
//        if (byDate) {
//            //:  {basicDataType}/{year}/{month}
//            relativePathSb.append(basicDataType)
//                    .append("/")
//                    .append(now.getYear())
//                    .append("/")
//                    .append(now.getMonthOfYear())
//                    .append("/");
//        } else {
//            //:  {basicDataType}
//            relativePathSb.append(basicDataType).append("/");
//        }
//        
//        //: {basicDataType}/.../{relativePath}
//        relativePathSb.append(relativePath);
//        
//        return relativePathSb.toString();
//    }
//    
//    //    /**
//    //     * 根据相对路径删除对应的文件<br/>
//    //     * <功能详细描述>
//    //     * @param request
//    //     * @param relativePath
//    //     * @return [参数说明]
//    //     * 
//    //     * @return boolean [返回类型说明]
//    //     * @exception throws [异常类型] [异常说明]
//    //     * @see [类、类#方法、类#成员]
//    //    */
//    //   @ResponseBody
//    //   @RequestMapping("/deleteByRelativePath")
//    //   public boolean deleteByRelativePath(HttpServletRequest request,
//    //           @RequestParam(value = "relativePath") String relativePath) {
//    //       boolean flag = false;
//    //       try {
//    //           //flag = fileContext.deleteByByRelativePath("basicdata", relativePath);
//    //       } catch (Exception e) {
//    //           logger.info(e.getMessage(), e);
//    //       }
//    //       return flag;
//    //   }
//    //   
//    //   /**
//    //    * 根据文件id删除对应的文件<br/>
//    //    * <功能详细描述>
//    //    * @param request
//    //    * @param relativePath
//    //    * @return [参数说明]
//    //    * 
//    //    * @return boolean [返回类型说明]
//    //    * @exception throws [异常类型] [异常说明]
//    //    * @see [类、类#方法、类#成员]
//    //   */
//    //   @ResponseBody
//    //   @RequestMapping("/deleteById")
//    //   public boolean deleteById(HttpServletRequest request,
//    //           @RequestParam(value = "fileId") String fileId) {
//    //       boolean flag = true;
//    //       try {
//    //           fileContext.deleteById(fileId);
//    //       } catch (Exception e) {
//    //           logger.info(e.getMessage(), e);
//    //           return false;
//    //       }
//    //       return flag;
//    //   }
//}
