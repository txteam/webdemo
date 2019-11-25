/*
DataDictTransferRecord * 描 述: <描述> 修 改 人: Administrator 修改时间: 2014年3月2日 <修改描述:>
 */
package generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 基础数据生成类<br/>
 *
 * @author Administrator
 * @version [版本号, 2014年3月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DBScriptAssembler {
    
    public static void main(String[] args) throws IOException {
        boolean skipCreateDBAndUser = true;
        //windows下的文本文件换行符:\r\n
        //linux/unix下的文本文件换行符:\r
        //Mac下的文本文件换行符:\n
        String lineSeparator = "\r\n";
        
        String project_path = org.springframework.util.StringUtils.cleanPath(
                DBScriptAssembler.class.getResource("/").getPath() + "../..");
        String codeBaseFolder = project_path;
        
        String scriptFilePath = codeBaseFolder
                + "/dbscript/mysql/script/script.sql";
        File scriptFile = new File(scriptFilePath);
        if (!scriptFile.exists()) {
            FileUtils.forceMkdirParent(scriptFile);
            scriptFile.createNewFile();
        }
        FileWriter fw = new FileWriter(scriptFile);
        
        String basicscriptFolder = codeBaseFolder
                + "/dbscript/mysql/01basisscript";
        File folder = new File(basicscriptFolder);
        if (!folder.exists()) {
            fw.close();
            return;
        }
        if (!skipCreateDBAndUser) {
            fw.append("-- create_database" + lineSeparator);
            File createDBFile = new File(
                    basicscriptFolder + "/01_create_database.sql");
            if (createDBFile.exists()) {
                fw.append(FileUtils.readFileToString(createDBFile, "UTF-8"));
            }
            fw.append(lineSeparator);
            
            fw.append("-- create_user" + lineSeparator);
            File createUserFile = new File(
                    basicscriptFolder + "/02_create_user.sql");
            if (createUserFile.exists()) {
                fw.append(FileUtils.readFileToString(createUserFile, "UTF-8"));
            }
            fw.append(lineSeparator);
        }
        
        fw.append("-- tables" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "tables".equals(name);
            })) {
                
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- sequences" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "sequences".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- initdata" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "initdata".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    //System.out.println(sqlFile.getName());
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    //System.out.println(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- foreignkey" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "foreignkey".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- functions" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "functions".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- procedures" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "procedures".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- triggers" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "triggers".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.append("-- jobs" + lineSeparator);
        for (File folderTemp : folder.listFiles()) {
            if (folderTemp.isFile()) {
                continue;
            }
            for (File tablesFile : folderTemp.listFiles((dir, name) -> {
                return "jobs".equals(name);
            })) {
                for (File sqlFile : tablesFile.listFiles((dir, name) -> {
                    return name.endsWith(".sql");
                })) {
                    fw.append(FileUtils.readFileToString(sqlFile, "UTF-8"));
                    fw.append(lineSeparator);
                    fw.flush();
                }
            }
        }
        
        fw.flush();
        fw.close();
        
        System.out.println("success");
    }
}
