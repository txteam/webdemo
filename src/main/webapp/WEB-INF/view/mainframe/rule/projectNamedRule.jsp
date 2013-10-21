<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目命名规范</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript">

</script>
<style type="text/css">

</style>	
<body>
<div style="padding: 5px">
<pre>
0前言:
主要包括排版规范；

术语和定义
	规则：必须遵守的原则
	建议：必须加以考虑得原则
	格式：格式规范
	示例：例子
	
持久层调用规范：
	1、持久层不能被模块以外的任何业务逻辑调用
	2、持久层仅负责对象持久的逻辑
	3、持久层理论上不对入参做合法性验证，或仅做有限的验证，不对业务逻辑验证负责
	4、持久层不负责事务逻辑处理。
	5、持久层逻辑在调度过程中如果，是批量操作，应当考虑是否调用批量逻辑。
	6、在业务逻辑中应该了解到持久层实际提交时间。
	7、持久层对象，需要在具体编码过程中，考虑其缓存问题，并添加进用户故事和说明

持久层命名规范：
	1、增加：insert*
	2、更新: update*
	3、查询实体：find*
	4、条件查询：query*
	5、查询所有：query*/list*
	6、删除：delete*
	7、批量处理: batch*
	8、拷贝到其他表：copy*
	9、移动到其他表：move*
	10、保存或更新：save*

业务层命名规范：
	1、增加：insert*
	2、更新: update*
	3、查询实体：find*
	4、条件查询：query*
	5、查询所有：query*/list*
	6、删除：delete*
	7、批量处理: batch*
	8、拷贝到其他表：copy*
	9、移动到其他表：move*
	10、保存或更新：save*
	
	11、查询 query*(Set/List)(By)(XXXX)
	
业务层编码规范：
	12、判断参数合法调用类AssertUtils.isXXXX();
	13、异常捕获处理ExceptionWrapperUtils.XXXX
	14、自定义业务逻辑异常可根据实际情况选择继承于SILException(System inner Logic exception);
	15、业务逻辑层单个方法理论上不允许超过一屏。
	16、业务逻辑层逻辑代码一般由三个部分组成，入参合法性校验，调用持久逻辑实现业务逻辑，构建返回值返回。
	
	17、业务逻辑理论上不允许存在灵活入参的情况如入参数为Map String String.
			如果存在，则需要明确在注释上对入参进行详细描述。
	18、如果业务层逻辑采用注解形式，Component中必须指定组件名
	19、如果业务逻辑层中组件引用，采用Resource(name="xxx")的形式添加，禁止采用AutoWare,Resource等通过类型添加引用的形式
	20、如果业务逻辑涉及数据变更时，需要明确使用@Transaction指定事务传播逻辑。

控制层命名规范：
	1、增加：insert*/add*
	2、更新: update*
	3、查询实体：find*
	4、条件查询：query*
	5、查询所有：query*/list*
	6、删除：delete*/del*
	7、批量处理: batch*
	8、拷贝到其他表：copy*
	9、移动到其他表：move*
	10、保存或更新：save*
	
控制层逻辑
	1、控制层逻辑，不允许出现，业务逻辑处理代码，
	2、控制层逻辑仅对入参转换，返回值控制，进行负责。
	

	
</pre>
</div>
</body>
</html>