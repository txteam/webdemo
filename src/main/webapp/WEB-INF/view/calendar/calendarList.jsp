<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code='calendar.page.title' /></title>
<link rel="stylesheet" type="text/css" href="../skins/default/main.css" />
<link href="../js/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="../js/zs/skins/default/zs-base.css" />
<script type="text/javascript" src="../dwr/engine.js"></script>
<script type="text/javascript" src="../dwr/interface/calendarDWRBean.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/zs/seed-min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/Paginator.js"></script>
<script type="text/javascript" src="../js/utils/dialogUtils.js"></script>
<script type="text/javascript" src="js/calendarList.js"></script>
<script type="text/javascript">
var vcid = "${Operator.vcid}";
var operId = "${Operator.operatorid}";
var operKind = "${Operator.kind}";
var operGroupId = "${Operator.groupId}";
var noData = "<spring:message code='mainframe.dataGrid.noData' />";
var eventType = ["<spring:message code='calendar.event.scope.0' />", "<spring:message code='calendar.event.scope.1' />"];
var eventPolicy = ["<spring:message code='calendar.event.policy.0' />", "<spring:message code='calendar.event.policy.1' />"];
var notifyType = ["<spring:message code='calendar.event.notifyType.0' />", "<spring:message code='calendar.event.notifyType.x' />"];
var modify = "<spring:message code='mainframe.common.modify' />";
var deleteSucceeded = "<spring:message code='mainframe.common.delete.succeeded' />";
var deleteFailed = "<spring:message code='mainframe.common.delete.failed' />";
var deleteConfirm = "<spring:message code='mainframe.common.delete.confirm' />";
var addEventDlgTitle = "<spring:message code='calendar.page.addTitle' />";
var modifyEventDlgTitle = "<spring:message code='calendar.page.modifyTitle' />";
var clientLocale = "${calendarListBean.clientLocale}";
</script>
</head>
<body>
    <div class="dataQuery">
        <table class="fullWidth">
            <colgroup>
                <col width="60">
                <col width="150">
                <col width="80">
                <col width="150">
                <col width="80">
                <col width="150">
                <col>
            </colgroup>
            <tr>
                <td><spring:message code='calendar.event.description' /></td>
                <td><input name="description" id="description" type="text" class="text"/></td>
                <th style="white-space: nowrap;"><spring:message code='calendar.event.scope' /></th>
                <td>
                    <select id="eventtype" name="eventtype" class="select">
                        <option value="0"><spring:message code='calendar.event.scope.0' /></option>
                        <c:if test="${Operator.kind==2||operKind==3}">
                        <option value="1"><spring:message code='calendar.event.scope.1' /></option>
                        </c:if>
                    </select>
                </td>
                <th style="white-space: nowrap;"><spring:message code='calendar.event.policy' /></th>
                <td>
                    <select id="datetype" name="datetype" class="select">
                        <option value="">--<spring:message code='mainframe.common.select' />--</option>
                        <option value="0"><spring:message code='calendar.event.policy.0' /></option>    
                        <option value="1"><spring:message code='calendar.event.policy.1' /></option>
                    </select>
                </td>
                <td>
                    <input name="" type="button" class="button floatRight" value="<spring:message code='calendar.page.returnToPrevious' />" onclick="window.location='calendar.jsp';"/>
                </td>
            </tr>
            <tr>
                <td style="white-space: nowrap;"><spring:message code='calendar.event.createDate' /></td>
                <td colspan="3">
                    <input name="startdate" id="startdate" type="text" value="${calendarListBean.beginDate}" onfocus="WdatePicker({lang:'${calendarListBean.clientLocale}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
                    -
                    <input name="enddate" id="enddate" type="text" value="${calendarListBean.endDate}" onfocus="WdatePicker({lang:'${calendarListBean.clientLocale}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
                    <input id="butQuery" type="button" class="button floatRight" value="<spring:message code='mainframe.common.query' />"/>
                </td>
                <td colspan="3">
                    <input type="button" class="button floatRight" value="<spring:message code='mainframe.common.add' />" id="butAdd"/>
                </td>
            </tr>
        </table>
  </div>
  <div class="dataGrid">
    <table id="dataTable">
      <colgroup>
      <col width="15"/>
      <col/>
      <col width="60"/>
      <col width="60"/>
      <col/>
      <col/>
      <col/>
      <col/>
      <col/>
      <col width="70"/>
      </colgroup>
      <thead>
        <tr>
          <th><input name="" type="checkbox" value="" /></th>
          <th><spring:message code="calendar.event.description"/></th>
          <th><spring:message code="calendar.event.scope"/></th>
          <th><spring:message code="calendar.event.policy"/></th>
          <th><spring:message code="calendar.event.query.beginDate"/></th>
          <th><spring:message code="calendar.event.query.endDate"/></th>
          <th><spring:message code="calendar.event.notifyType"/></th>
          <th><spring:message code="calendar.event.creator"/></th>
          <th><spring:message code="calendar.event.createDate"/></th>
          <th class="tCenter"><spring:message code="calendar.event.query.operate"/></th>
        </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
    </div>
    <div class="paginationContainer">
        <div class="pagination floatRight">
            <span class="total"><spring:message code="mainframe.dataGrid.total"/><span id="rowCountSpan" style="display: inline; float: none;">0</span><spring:message code="mainframe.dataGrid.recordUnit"/></span>
            <span class="current"><spring:message code="mainframe.dataGrid.ordinary"/><span id="pageIndexSpan" style="display: inline; float: none;">0</span>/<span id="pageCountSpan" style="display: inline; float: none;">0</span><spring:message code="mainframe.dataGrid.pageUnit"/></span>
            <a id="toFirstPage" href="#" class="first disable" title="<spring:message code="mainframe.dataGrid.firstPage"/>"></a>
            <a id="toPreviousPage" href="#" class="prev disable" title="<spring:message code="mainframe.dataGrid.previousPage"/>"></a>
            <a id="toNextPage" href="#" class="next" title="<spring:message code="mainframe.dataGrid.nextPage"/>"></a>
            <a id="toLastPage" href="#" class="last" title="<spring:message code="mainframe.dataGrid.lastPage"/>"></a>
            <span class="total"><spring:message code="mainframe.dataGrid.perPage"/></span>
            <select id="pageSizeSelector">
                <option selected="selected">10</option>
                <option>15</option>
                <option>20</option>
                <option>50</option>
            </select>
            <span><spring:message code="mainframe.dataGrid.pageUnit"/></span>
        </div>
        <input id="butDel" type="button" class="button floatLeft" value="<spring:message code='mainframe.common.delete' />" />
    </div>
</body>
</html>