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
        //必须拷贝目录的set
        Set copySet = new HashSet();
        copySet.add("login");
        copySet.add("payment");
        //拷贝项目文件
        copyProjectFile(sourceJavaFolder, targetJavaFolder, copySet);

        //写入src/main/resources目录内容
        copySet = new HashSet();
        sourceJavaFolder = new File(sourceProjectPath, "src/main/resources/");
        targetJavaFolder = new File(targetProjectFolder, "src/main/resources/");
        copyProjectFile(sourceJavaFolder, targetJavaFolder);

        //写入src/test/java目录内容
        copySet = new HashSet();
        sourceJavaFolder = new File(sourceProjectPath, "src/test/java/");
        targetJavaFolder = new File(targetProjectFolder, "src/test/java/");
        copyProjectFile(sourceJavaFolder, targetJavaFolder);

        //写入src/test/resources目录内容
        copySet = new HashSet();
        sourceJavaFolder = new File(sourceProjectPath, "src/test/resources/");
        targetJavaFolder = new File(targetProjectFolder, "src/test/resources/");
        copyProjectFile(sourceJavaFolder, targetJavaFolder);

        //拷贝dbscript、deploy、doc
        FileUtils.copyDirectory(new File(sourceProjectPath, "dbscript"), new File(targetProjectFolder, "dbscript"));
        FileUtils.copyDirectory(new File(sourceProjectPath, "deploy"), new File(targetProjectFolder, "deploy"));
        FileUtils.copyDirectory(new File(sourceProjectPath, "doc"), new File(targetProjectFolder, "doc"));
        //根据项目名覆盖相关文件


        /*if (!targetJavaFolder.exists()) {
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
        int res = 1;
        File[] sourceFiles = null;
        Set copySet = new HashSet();
        for (File file : sourceFileMap.keySet()) {
            targetFile = sourceFileMap.get(file);
            if (!targetFile.exists()) {
                FileUtils.forceMkdir(targetFile);
            }
            if (file.isFile()) {
                FileUtils.copyFile(file, targetFile);
            } else {
                sourceFiles = file.listFiles();
                File targetFile1 = null;
                for (File sourceFile1 : sourceFiles) {
                    targetFile1 = new File(targetFile, sourceFile1.getName());
                    if (targetFile1.isFile()) {
                        FileUtils.copyFile(sourceFile1, targetFile1);
                    } else {
                        if (sourceFile1.isFile()) {
                            FileUtils.copyFile(sourceFile1, targetFile1);
                        } else {
                            if (copySet.contains(sourceFile1.getName())) {
                                FileUtils.copyDirectory(sourceFile1, targetFile1);
                            } else {
                                res = JOptionPane.showConfirmDialog(null, "是否拷贝" + sourceFile1.getName(), "是否拷贝", JOptionPane.YES_NO_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    FileUtils.copyDirectory(sourceFile1, targetFile1);
                                }
                            }
                        }
                    }
                }
            }
        }*/

    }

    private static void copyProjectFile(File sourceJavaFolder, File targetJavaFolder, Set copySet) throws IOException {
        if (!targetJavaFolder.exists()) {
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
        int res = 1;
        File[] sourceFiles = null;
        for (File file : sourceFileMap.keySet()) {
            targetFile = sourceFileMap.get(file);
            if (!targetFile.exists()) {
                FileUtils.forceMkdir(targetFile);
            }
            if (file.isFile()) {
                FileUtils.copyFile(file, targetFile);
            } else {
                sourceFiles = file.listFiles();
                File targetFile1 = null;
                for (File sourceFile1 : sourceFiles) {
                    targetFile1 = new File(targetFile, sourceFile1.getName());
                    if (targetFile1.isFile()) {
                        FileUtils.copyFile(sourceFile1, targetFile1);
                    } else {
                        if (sourceFile1.isFile()) {
                            FileUtils.copyFile(sourceFile1, targetFile1);
                        } else {
                            if (copySet.contains(sourceFile1.getName())) {
                                FileUtils.copyDirectory(sourceFile1, targetFile1);
                            } else {
                                res = JOptionPane.showConfirmDialog(null, "是否拷贝" + sourceFile1.getName(), "是否拷贝", JOptionPane.YES_NO_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    FileUtils.copyDirectory(sourceFile1, targetFile1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void copyProjectFile(File sourceJavaFolder, File targetJavaFolder) throws IOException {
        if (!targetJavaFolder.exists()) {
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
        int res = 1;
        File[] sourceFiles = null;
        for (File file : sourceFileMap.keySet()) {
            targetFile = sourceFileMap.get(file);
            if (!targetFile.exists()) {
                FileUtils.forceMkdir(targetFile);
            }
            if (file.isFile()) {
                FileUtils.copyFile(file, targetFile);
            } else {
                sourceFiles = file.listFiles();
                File targetFile1 = null;
                for (File sourceFile1 : sourceFiles) {
                    targetFile1 = new File(targetFile, sourceFile1.getName());
                    if (targetFile1.isFile()) {
                        FileUtils.copyFile(sourceFile1, targetFile1);
                    } else {
                        if (sourceFile1.isFile()) {
                            FileUtils.copyFile(sourceFile1, targetFile1);
                        } else {
                            FileUtils.copyDirectory(sourceFile1, targetFile1);
                        }
                    }
                }
            }
        }
    }
}
