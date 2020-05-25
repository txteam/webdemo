/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月4日
 * <修改描述:>
 */
package builder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.tx.core.util.FreeMarkerUtils;

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
        boolean overwrite = true;
        String workspace = "D:/develop/txworkspace";
        
        //项目强依赖模块
        Set<String> moduleSet = new HashSet<>();
        moduleSet.add("");
        
        //原项目路径
        String sourceProjectPath = org.springframework.util.StringUtils
                .cleanPath(ProjectBuilder.class.getResource("/").getPath()
                        + "../..");
        //System.out.println(sourceProjectPath);
        ///D:/develop/txworkspace/webdemo
        
        File targetProjectFolder = new File(workspace + "/" + projectName);
        if (targetProjectFolder.exists() && !overwrite) {
            System.out
                    .println("项目文件已经存在.path:" + targetProjectFolder.getPath());
            return;
        }
        
        if (!targetProjectFolder.exists()) {
            File sourceProjectFolder = new File(sourceProjectPath);
            //创建项目文件夹
            FileUtils.forceMkdir(targetProjectFolder);
        }
        //拷贝文件夹
        //pom文件，替换项目名写入
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("project_name", projectName);
        params.put("project_description", projectName);
        FreeMarkerUtils.fprint(ProjectBuilder.class,
                "builder/pom.ftl",
                params,
                targetProjectFolder + "/pom.xml");
        
        //写入src/main/java目录内容
        
        //写入src/main/resources目录内容
        
        //写入src/test/java目录内容
        
        //写入src/test/resources目录内容
        
        //根据项目名覆盖相关文件
    }
}
