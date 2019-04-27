<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面编码规范</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript">

</script>
<style type="text/css">

</style>	
<body>
<div style="padding: 5px">
<pre>
1、	主业务逻辑可通过
	@include file="../includes/commonHead.jsp"
	@include file="../../includes/commonHead.jsp"...
	
	引入公共头
	如果需要顶哈子的样式表单以及javascript可以直接写入当前jsp中。
	
2、 统一为无需要指定为<![CDATA[ <!DOCTYPE html> ]]>不添加其他的
	页面title中，根据当前页面进行修改

3、查询页面命名
	queryXXXList.jsp
	queryXXX.jsp
	queryXXXSet.jsp
	...
	
4、增加页面，及修改页面根据页面复杂度
	可以独立提供页面或通过hidden的div提供在查询页面中。
	addXXX.jsp/insertXXX.jsp
	updateXXX.jsp/updateXXX.jsp
	尽量让页面名与controller中方法名一致，便于后期定位

5、页面验证逻辑，要求在前段页面提供足够的js校验，尽量不要依赖提交以后后台出现的异常去显示校验结果
		在部分业务逻辑，在做好页面验证的同时需要在对应controller中，应当提供对应的校验逻辑。
	 	在添加，审批等业务逻辑中，应当对重复提交情况进行考虑，尽量利用one,等jquery原生函数进行解决。
	 	
6、在贷款单详细信息页面中的几个子页面间相互触发全局事件，触发全局事件的函数应该尽量将本页面的loanbillid
	作为参数进行触发，而接受全局事件的函数应该首先从params中取出
	loanbillid进行验证，看是否和本页面的loanbillid相等，若相等则进行后续工作，否则不予处理。此规
	定的用意在于防止全局事件被多个loanbill详细页面监听，造成bug。例子如下：
	//将loanbillid作为参数进行传递
	$.triggerge('flushClientinfoHeaderDiv', {loanBillId : $('#loanBillId').val()});
	//判断loanbillid是否相等
	$.bindge('addLoanApplyTablePrintTab', function(event, params){
			var loanBillId = params.loanBillId;
			if(loanBillId == $('#loanBillId').val()) {
				.........处理后续工作
			}
		});
	
...
后面补充 
javascript
prototype
widget
等内容
	
</pre>
</div>
</body>
</html>