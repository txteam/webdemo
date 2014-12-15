/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年10月16日
 * <修改描述:>
 */
package com.tx.webdemo.upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tx.component.configuration.context.ConfigContext;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年10月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("testUploadController")
@RequestMapping("/testUpload")
public class TestUploadController {
    
    private String savePath = ConfigContext.getContext()
            .getConfigPropertyValueByKey("test.upload.local.savePath");
    
    @RequestMapping("toTestUpload")
    public String toTestUpload() {
        return "/upload/testUpload";
    }
    
    @ResponseBody
    @RequestMapping("/upload")
    public boolean upload(
            @RequestParam(value = "requestId") String requestId,
            @RequestParam(value = "processDefFile") CommonsMultipartFile processDefFile) {
        String path = savePath;
        try {
            FileUtils.forceMkdir(new File(path));
            
            String fileName = processDefFile.getFileItem().getName();
            File saveFile = new File(path + "/" + fileName);
            
            IOUtils.copy(processDefFile.getFileItem().getInputStream(),
                    new FileOutputStream(saveFile));
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e,
                    "make dir error.",
                    path);
        }
        return true;
    }
    
    @ResponseBody
    @RequestMapping("/multiUpload")
    public boolean multiUpload(@RequestParam(value = "loanBillId") String loanBillId,
            @RequestParam(value = "processDefFile") CommonsMultipartFile[] processDefFiles) {
        String path = savePath;
        try {
            FileUtils.forceMkdir(new File(path));
            
            for(CommonsMultipartFile processDefFileTemp : processDefFiles){
                String fileName = processDefFileTemp.getFileItem().getName();
                File saveFile = new File(path + "/" + fileName);
                
                IOUtils.copy(processDefFileTemp.getFileItem().getInputStream(),
                        new FileOutputStream(saveFile));
            }
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e,
                    "make dir error.",
                    path);
        }
        return true;
    }
}
