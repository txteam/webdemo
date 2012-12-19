/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-11
 * <修改描述:>
 */
package mybatishelper;

import com.tx.core.mybatis.generator.JpaEntityFreeMarkerGenerator;
import com.tx.webdemo.demo.model.Demo;


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
        
        JpaEntityFreeMarkerGenerator factory = new JpaEntityFreeMarkerGenerator();
        factory.setLoadTemplateClass(GenerateCodeByJpaModelTest.class);
        
        factory.setDaoImplTemplateFilePath("com/boda/mybatishelper/daoImpl.ftl");
        factory.setDaoTemplateFilePath("com/boda/mybatishelper/dao.ftl");
        factory.setServiceTemplateFilePath("com/boda/mybatishelper/service.ftl");
        factory.setServiceTestTemplateFilePath("com/boda/mybatishelper/serviceTest.ftl");
        factory.setSqlMapTemplateFilePath("com/boda/mybatishelper/sqlMap.ftl");
        
        //生成后在自己指定的文件夹中去找即可
        factory.generate(Demo.class, "d:/mybatis");
        
        System.out.println("success");
    }
}