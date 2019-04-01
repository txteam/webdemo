<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务日志编写帮助</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript">

</script>
<style type="text/css">

</style>	
<body>
<div style="padding: 5px">
<pre>
	业务日志的定位：
		业务日志非系统日志
	系统日志：用于辅助定位系统问题，分析系统性能功能。
	业务日志：用户记录系统中的业务过程，可以用于反映系统中数据变更过程，
		系统在什么时候发生了什么业务操作。
		某业务在某个时间段内发生了什么。等等。侧重于与业务相关。
		
	在本框架中，认为业务日志基本元素为：什么人在什么时候做了什么
		其他的业务日志，就是在这个基础上多记录了一些业务信息。

	一、添加业务日志定义
		定义日志类：XXXLog 使其继承于 TXBaseServiceLog
		如果该类，后续需要在界面上查询并显示，则这里必须提供一个无参构造函数。
		在该类中，添加需要记录的其他字段，在该类中，添加jpa注解，说明与数据库表之间的关系
			字段与属性之间的关系。
		例：SystemOperateLog.java
			LoginLog.java
		
	二、业务日志的记录
		//生成建表语句
		ServiceLogGenerator.java
		将 Class serviceLogType = SystemOperateLog.class;修改为新增加的业务日志类
  		即可得到
  		对应类的建表语句，根据当前基本情况修改即可。
  		因默认生成的字段长度，以及精度不一定与实际情况一致，这里需要注意自动生成脚本是否正确。
  		
  		在业务逻辑中添加业务日志记录逻辑，可如下编写：
		//记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "新增组织", "新增组织", null));
         	编写参考类：OrganizationService.java中业务日志的添加       
         
  	三、业务日志显示逻辑生成
  		如果业务日志需要进行显示
  		打开 ServiceLogGenerator.java
  		将 Class serviceLogType = SystemOperateLog.class;修改为新增加的业务日志类
  		运行后即可得到，对应的业务逻辑显示的controller以及jsp逻辑代码，放入项目即可。
  		
</pre>
</div>
</body>
</html>