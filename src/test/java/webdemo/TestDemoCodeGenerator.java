/*
DataDictTransferRecord * 描 述: <描述> 修 改 人: Administrator 修改时间: 2014年3月2日 <修改描述:>
 */
package webdemo;

import java.io.IOException;

import com.tx.core.generator2.CodeGenerator;
import com.tx.core.generator2.model.ViewTypeEnum;
import com.tx.local.demo.model.TestDemo;
import com.tx.local.operator.model.OperatorRoleCatalog;

/**
 * 基础数据生成类<br/>
 *
 * @author Administrator
 * @version [版本号, 2014年3月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestDemoCodeGenerator {
    
    public static void main(String[] args) throws IOException {
        boolean toProjectPath = true;//是否生成覆盖到项目代码中，如果设置为false则会写入D盘的目录中
        Class<?> entityType = TestDemo.class;
        boolean needConfirmOverwriteFile = false;//覆盖文件前是否需要提示
        
        //基础数据逻辑代码生成存放目录com.tx.component.basicdata.generator.
        if (toProjectPath) {
            String project_path = org.springframework.util.StringUtils
                    .cleanPath(entityType.getResource("/").getPath() + "../..");
            String codeBaseFolder = project_path;
            TestDemoCodeGenerator.BASE_CODE_FOLDER = codeBaseFolder;
        }
        
        //基础数据生成逻辑代码对应的数据库类型(mysql与oracle)在sqlMap中组装like条件是不一致的
        TestDemoCodeGenerator.NEED_CONFIRM_WHEN_EXSITS = needConfirmOverwriteFile;
        //        CodeGenerator.generateDBScript(entityType);
        //        CodeGenerator.generateSqlMap(entityType);
        //        CodeGenerator.generateDao(entityType);
        //        CodeGenerator.generateService(entityType);
        //生成控制层逻辑
        //CodeGenerator.generateController(entityType, viewType);
        
        //生成页面
        TestDemoCodeGenerator.generateView(entityType, ViewTypeEnum.LIST);
        TestDemoCodeGenerator.generateView(entityType, ViewTypeEnum.TREELIST);
        TestDemoCodeGenerator.generateView(entityType, ViewTypeEnum.PAGEDLIST);
        
        System.out.println("success");
    }
}