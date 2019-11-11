/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年5月27日
 * <修改描述:>
 */
package generator2;

import com.tx.core.generator2.CodeGenerator;
import com.tx.local.basicdata.model.District;
import com.tx.local.operator.model.Organization;
import com.tx.local.vitualcenter.model.VirtualCenter;

/**
 * 编码生成测试 <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2019年5月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CodeGeneratorTest {

	public static void main(String[] args) {
		Class<?> entityType = Organization.class;

		CodeGenerator.generateDBScript(entityType);
		CodeGenerator.generateSqlMap(entityType);
		CodeGenerator.generateDao(entityType);
		CodeGenerator.generateService(entityType);
		CodeGenerator.generateController(entityType);
	}
}
