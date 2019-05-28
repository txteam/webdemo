/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-11
 * <修改描述:>
 */
package generator;

import java.io.File;
import java.io.IOException;

import com.tx.core.generator.table.TableCodeGenerator;
import com.tx.core.util.MD5Utils;
import com.tx.local.client.model.ClientInfo;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WTPTableCodeGenerator {
    
    public static void main(String[] args) throws IOException {
        Class<?> type = ClientInfo.class;
        String folderPath = "d:/generator/tablecode";
        (new File(folderPath)).mkdirs();
        
        TableCodeGenerator factory = new TableCodeGenerator();
        //factory.setLoadTemplateClass(WTPTableCodeGenerator.class);
        
        //生成后在自己指定的文件夹中去找即可
        factory.generate(type, folderPath, true);
        factory.generateScript(type, folderPath, "GBK");
        
        System.out.println("success");
        
        System.out.println(MD5Utils.encode("123321qQ"));
    }
}
