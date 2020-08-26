/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月4日
 * <修改描述:>
 */
package generator;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.tx.core.util.FreeMarkerUtils;

/**
 * 项目构建器<br/>
 * <功能详细描述>
 *
 * @author Administrator
 * @version [版本号, 2019年12月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProjectGenerator {
    /**
     * 项目构建类<br/>
     * <功能详细描述>
     * @param args
     * @throws IOException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args) throws IOException {
        String projectName = "tbdemo";
        
        boolean overwrite = true;//如果存在是否抛出异常
        boolean clearBeforeBuild = true;
        String workspace = "D:/develop/txworkspace";//目标项目目录
        String packageName = "com/tx";
        
        //原项目路径
        String sourceProjectPath = org.springframework.util.StringUtils
                .cleanPath(ProjectGenerator.class.getResource("/").getPath()
                        + "../..");
        
        String targetProjectPath = workspace + "/" + projectName;
        File targetProjectFolder = new File(targetProjectPath);
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
        if (clearBeforeBuild) {
            FileUtils.cleanDirectory(targetProjectFolder);
            System.out.println("清空目录");
        }
        
        //拷贝文件夹
        //pom文件，替换项目名写入
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("project_name", projectName);
        params.put("project_description", projectName);
        
        //pom文件
        FreeMarkerUtils.fprint(ProjectGenerator.class,
                "generator/template/pom.ftl",
                params,
                targetProjectFolder + "/pom.xml");
        //deploy目录
        FreeMarkerUtils.fprint(ProjectGenerator.class,
                "generator/template/deploy/dev.properties.ftl",
                params,
                targetProjectFolder + "/deploy/dev.properties");
        FreeMarkerUtils.fprint(ProjectGenerator.class,
                "generator/template/deploy/prod.properties.ftl",
                params,
                targetProjectFolder + "/deploy/prod.properties");
        FreeMarkerUtils.fprint(ProjectGenerator.class,
                "generator/template/deploy/test.properties.ftl",
                params,
                targetProjectFolder + "/deploy/test.properties");
        //项目目录创建
        File mainJavaFolder = new File(targetProjectFolder, "src/main/java/");
        File mainResFolder = new File(targetProjectFolder,
                "src/main/resources/");
        File testJavaFolder = new File(targetProjectFolder, "src/test/java/");
        File testResFolder = new File(targetProjectFolder,
                "src/test/resources/");
        if (!mainJavaFolder.exists()) {
            mainJavaFolder.mkdirs();
        }
        if (!mainResFolder.exists()) {
            mainResFolder.mkdirs();
        }
        if (!testJavaFolder.exists()) {
            testJavaFolder.mkdirs();
        }
        if (!testResFolder.exists()) {
            testResFolder.mkdirs();
        }
        
        //拷贝资源目录
        //test/resources
        File sourceFolderTemp = new File(sourceProjectPath,
                "src/test/resources/");
        File targetFolderTemp = new File(targetProjectFolder,
                "src/test/resources/");
        FileUtils.copyDirectory(sourceFolderTemp, targetFolderTemp);
        //拷贝资源目录
        //main/resources
        sourceFolderTemp = new File(sourceProjectPath, "src/main/resources/");
        targetFolderTemp = new File(targetProjectFolder, "src/main/resources/");
        FileUtils.copyDirectory(sourceFolderTemp,
                targetFolderTemp,
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        if (pathname.getPath().indexOf("\\templates") >= 0) {
                            return false;
                        } else if (pathname.getPath()
                                .indexOf("\\templates4client") >= 0) {
                            return false;
                        } else if (pathname.getPath()
                                .indexOf("\\templates4wapclient") >= 0) {
                            return false;
                        }
                        return true;
                    }
                });
        
        //必须拷贝目录的set
        Set<Module> baseModuleSet = new HashSet<Module>();
        baseModuleSet.add(
                new Module("plugin", "/com/tx/plugin", "/templates/plugin"));//主框架中启动模块
        
        baseModuleSet.add(new Module("boot", "/com/tx/local/boot"));//主框架中启动模块
        baseModuleSet.add(new Module("springmvc", "/com/tx/local/springmvc"));//主框架中启动模块
        baseModuleSet.add(new Module("menu", "/com/tx/local/menu"));
        baseModuleSet.add(new Module("mainframe", "/com/tx/local/mainframe",
                "/templates/mainframe", "/templates/rule", "/templates/portal",
                "/templates/helper"));
        baseModuleSet.add(new Module("servicelog", "/com/tx/local/servicelog",
                "/templates/servicelog"));
        
        //主框架虚中心模块
        baseModuleSet.add(new Module("vitualcenter",
                "/com/tx/local/vitualcenter", "/templates/vitualcenter"));
        baseModuleSet.add(new Module("organization",
                "/com/tx/local/organization", "/templates/organization"));
        baseModuleSet.add(new Module("operator", "/com/tx/local/operator",
                "/templates/operator"));
        
        //必须（建议选取模块,否则需要调整页面及菜单）
        baseModuleSet.add(new Module("captcha", "/com/tx/local/captcha"));
        baseModuleSet.add(new Module("basicdata", "/com/tx/local/basicdata",
                "/templates/basicdata"));//主框架中基础数据必须的内容
        baseModuleSet.add(new Module("calendar", "/com/tx/local/calendar",
                "/templates/calendar"));//主框架中行事历模块
        baseModuleSet.add(new Module("message", "/com/tx/local/message",
                "/templates/message"));//主框架消息模块
        baseModuleSet.add(new Module("notepad", "/com/tx/local/notepad",
                "/templates/notepad"));//主框架记事本模块
        baseModuleSet.add(new Module("documentation",
                "/com/tx/local/documentation", "/templates/documentation"));
        
        baseModuleSet.add(new Module("clientinfo", "/com/tx/local/clientinfo",
                "/templates/clientinfo"));
        //登陆
        baseModuleSet.add(new Module("security", "/com/tx/local/security",
                "/templates/security"));
        
        baseModuleSet.stream().forEach(m -> {
            try {
                copyModule(sourceProjectPath, targetProjectPath, m);
            } catch (IOException e) {
                System.out.println("copy module exception." + e.getMessage());
            }
        });
        
        //        //根据是否需要客户信息决定是否迁移
        //        baseModuleSet.add("personal");
        //        baseModuleSet.add("institution");
        //        baseModuleSet.add("creditinfo");
        //        
        //        //拷贝项目文件
        //        //写入src/main/java目录内容
        //        File targetJavaFolder = new File(targetProjectFolder,
        //                "src/main/java/" + packageName);
        //        File sourceJavaFolder = new File(sourceProjectPath,
        //                "src/main/java/" + packageName);
        //        copySrcMainJava(sourceJavaFolder, targetJavaFolder, baseModuleSet);
        //        
        //        //写入src/test/java目录内容
        //        baseModuleSet = new HashSet();
        //        baseModuleSet.add("generator");
        //        sourceJavaFolder = new File(sourceProjectPath, "src/test/java/");
        //        targetJavaFolder = new File(targetProjectFolder, "src/test/java/");
        //copyProjectFile1(sourceJavaFolder, targetJavaFolder, copySet);
        
    }
    
    /**
     * 拷贝：copySrcMainJava
     * <功能详细描述>
     * @param sourceJavaFolder
     * @param targetJavaFolder
     * @param module
     * @throws IOException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static void copyModule(String sourceProjectPath,
            String targetProjectPath, Module module) throws IOException {
        //module -> com/tx/local/boot
        //copy /src/main/java
        //拷贝java代码
        String codePath = module.getCodePath();
        FileUtils.copyDirectory(
                new File(sourceProjectPath, "/src/main/java" + codePath),
                new File(targetProjectPath, "/src/main/java" + codePath));
        
        //拷贝template页面  pagePath
        if (!ArrayUtils.isEmpty(module.getPagePath())) {
            for (String pagePathTemp : module.getPagePath()) {
                FileUtils.copyDirectory(
                        new File(sourceProjectPath,
                                "/src/main/resources" + pagePathTemp),
                        new File(targetProjectPath,
                                "/src/main/resources" + pagePathTemp));
            }
        }
        
        //拷贝dbscript
        String finalModule = "\\" + module.getModule();
        FileUtils.copyDirectory(new File(sourceProjectPath, "dbscript"),
                new File(targetProjectPath, "dbscript"),
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        if (pathname.getPath().indexOf(finalModule) < 0) {
                            return false;
                        }
                        return true;
                    }
                });
    }
    
    private static class Module {
        
        private String module;
        
        private String codePath;
        
        private String[] pagePath;
        
        public Module(String module, String codePath, String... pagePath) {
            super();
            this.module = module;
            this.codePath = codePath;
            this.pagePath = pagePath;
        }
        
        /**
         * @return 返回 module
         */
        public String getModule() {
            return module;
        }
        
        /**
         * @return 返回 codePath
         */
        public String getCodePath() {
            return codePath;
        }
        
        /**
         * @return 返回 pagePath
         */
        public String[] getPagePath() {
            return pagePath;
        }
    }
}
