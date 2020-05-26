/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月4日
 * <修改描述:>
 */
package builder;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;

import com.tx.core.util.FreeMarkerUtils;

import javax.swing.*;

/**
 * 项目构建器<br/>
 * <功能详细描述>
 *
 * @author Administrator
 * @version [版本号, 2019年12月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProjectBuilder {

    public static void main(String[] args) throws IOException {
        String projectName = "report_support";
        boolean overwrite = true;
        String workspace = "D:/ideaproject";
        String packageName = "com/tx";

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
            System.out.println("项目文件已经存在.path:" + targetProjectFolder.getPath());
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
        File targetJavaFolder = new File(targetProjectFolder, "src/main/java/" + packageName);
        File sourceJavaFolder = new File(sourceProjectPath, "src/main/java/" + packageName);
        if (!targetJavaFolder.exists()) {
            //创建项目文件夹
            FileUtils.forceMkdir(targetJavaFolder);
        }
        File[] sourceJavaFile = sourceJavaFolder.listFiles();
        File sourceFile = null;
        File targetFile = null;
        HashMap<File, File> sourceFileMap = new HashMap();
        for (File file : sourceJavaFile) {
            String fileName = file.getName();
            sourceFile = new File(sourceJavaFolder, fileName);
            targetFile = new File(targetJavaFolder, fileName);
            if (file.isFile()) {
                FileUtils.copyFile(sourceFile, targetFile);
            } else {
                sourceFileMap.put(sourceFile, targetFile);
            }
        }
        int res=1;
        File[] sourceFiles= null;
        HashMap<String,File> copyMap=new HashMap<>();
        for (File file : sourceFileMap.keySet()) {
            targetFile = sourceFileMap.get(file);
            if (file.isFile()) {
                FileUtils.copyFile(file, targetFile);
            } else {
                System.out.println(targetFile.getPath());
                sourceFiles = file.listFiles();
                for (File sourceFile1 : sourceFiles) {
                    res = JOptionPane.showConfirmDialog(null, "是否拷贝", "是否拷贝", JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        System.out.println("选择是后执行的代码"); // 点击“是”后执行这个代码块
                    } else {
                        System.out.println("选择否后执行的代码"); // 点击“否”后执行这个代码块
                        return;
                    }
                }
            }
        }
        /*int res = JOptionPane.showConfirmDialog(null, "是否继续操作", "是否继续", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            System.out.println("选择是后执行的代码"); // 点击“是”后执行这个代码块
        } else {
            System.out.println("选择否后执行的代码"); // 点击“否”后执行这个代码块
            return;
        }*/

        //写入src/main/resources目录内容

        //写入src/test/java目录内容

        //写入src/test/resources目录内容

        //根据项目名覆盖相关文件
    }

}
