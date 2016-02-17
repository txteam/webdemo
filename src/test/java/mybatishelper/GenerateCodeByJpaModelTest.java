/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-11
 * <修改描述:>
 */
package mybatishelper;

import com.tx.core.generator.JpaEntityFreeMarkerGenerator;
import com.tx.core.util.MD5Utils;
import com.tx.fetchhccredit.model.HCLoanAccountView;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-12-11]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class GenerateCodeByJpaModelTest {
    
    public static void main(String[] args) {
        Class<?> type = HCLoanAccountView.class;
        String folderPath = "d:/mybatis";
        
        JpaEntityFreeMarkerGenerator factory = new JpaEntityFreeMarkerGenerator();
        factory.setLoadTemplateClass(GenerateCodeByJpaModelTest.class);
        
        //生成后在自己指定的文件夹中去找即可
        factory.generate(type, folderPath,true);
        factory.generateScript(type, folderPath,"GBK");
        
        System.out.println("success");
        
        System.out.println(MD5Utils.encode("123321qQ"));
    }
}
