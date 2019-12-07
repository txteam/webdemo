/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月4日
 * <修改描述:>
 */
package builder;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 项目构建器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProjectBuilder {
    
    public static void main(String[] args) throws IOException {
        String projectName = "newwebdemo";
        String workspace = "D:/develop/txworkspace";
        
        //原项目路径
        String sourceProjectPath = org.springframework.util.StringUtils
                .cleanPath(ProjectBuilder.class.getResource("/").getPath()
                        + "../..");
        //System.out.println(sourceProjectPath);
        ///D:/develop/txworkspace/webdemo
        
        File targetProjectFolder = new File(workspace + "/" + projectName);
        if (targetProjectFolder.exists()) {
            System.out
                    .println("项目文件已经存在.path:" + targetProjectFolder.getPath());
            return;
        }
        File sourceProjectFolder = new File(sourceProjectPath);
        
        //创建项目文件夹
        FileUtils.forceMkdir(targetProjectFolder);
        
        //拷贝文件夹
        
        //根据项目名覆盖相关文件
    }
}
