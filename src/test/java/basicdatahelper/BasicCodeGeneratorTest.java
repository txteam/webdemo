/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年3月2日
 * <修改描述:>
 */
package basicdatahelper;

import com.tx.component.basicdata.generator.BasicDataCodeGenerator;
import com.tx.component.operator.model.PostType;
import com.tx.core.dbscript.model.DataSourceTypeEnum;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年3月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BasicCodeGeneratorTest {
    
    public static void main(String[] args) {
        //基础数据类
        Class<?> basicDataType = PostType.class;
        //基础数据逻辑代码生成存放目录
        String codeBaseFolder = "d:/basicdata";
        //基础数据生成逻辑代码对应的数据库类型(mysql与oracle)在sqlMap中组装like条件是不一致的
        DataSourceTypeEnum dataSourceType = DataSourceTypeEnum.MYSQL;
        //基础数据唯一键数组uniqueGetterNamesArray
        String[][] uniqueGetterNamesArray = new String[][] { new String[] { "name" },
                new String[] {"code" } };
        //是否有效的字段名
        String validFieldName = "valid";
        //是否需要生成分页查询列表
        boolean isPaged = false;
        
        
        BasicDataCodeGenerator.generate(basicDataType,
                dataSourceType,
                codeBaseFolder,
                uniqueGetterNamesArray,
                validFieldName,
                isPaged);
        
        
        System.out.println("success");
    }
}
