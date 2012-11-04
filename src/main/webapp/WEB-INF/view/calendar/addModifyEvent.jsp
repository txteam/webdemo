<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils" %>
<%
pageContext.setAttribute("currentDate", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../skins/default/main.css" rel="stylesheet" type="text/css" />
<link href="../js/zs/skins/default/zs-base.css"/>
<link href="../js/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../dwr/engine.js"></script>
<script type="text/javascript" src="../dwr/interface/calendarDWRBean.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/zs/seed-min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/utils/dialogUtils.js"></script>
<script type="text/javascript" src="js/addModifyEvent.js"></script>
<script type="text/javascript">
var eventId = "${param.id}";
var operId = "${Operator.operatorid}";
var operKind = "${Operator.kind}";
var operGroupId = "${Operator.groupId}";
var operVcid = "${Operator.vcid}";
var toSelectPrompt = "<spring:message code='calendar.event.staff.query' />";
var operateFailed = "<spring:message code='calendar.page.operate.failed' />";
var group = "<spring:message code='calendar.event.staff.group' />";
var descValidatePrompt = "<spring:message code='calendar.validate.description' />";
var weekValidatePrompt = "<spring:message code='calendar.validate.weekDay' />";
var needRefresh = ("${param.from}" == "");
var clientLocale = "${localeBean.clientLocale}";
</script>
</head>
<body>
<div class="popwin">
    <div class="tabForm">
        <table>
            <tr>
                <th class="narrowMin" style="white-space: nowrap; width: 70px"><spring:message code='calendar.event.scope' /></span></th>
                <td>
                    <input name="eventtype" type="radio" id="eventtype0" value="0" checked="checked"/>
                    <label for="eventtype0"><spring:message code='calendar.event.scope.0' /></label>
                    <c:choose>
                        <c:when test="${Operator.kind == 2 || Operator.kind == 3}">
                            <input type="radio" name="eventtype" id="eventtype1" value="1" />
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="eventtype" id="eventtype1" value="1" disabled="disabled" />
                        </c:otherwise>
                    </c:choose>
                    <label for="eventtype1"><spring:message code='calendar.event.scope.1' /></label>
                </td>
            </tr>
            <tr>
                <th><spring:message code='calendar.event.category' /></th>
                <td>
                    <select name="catid" id="catid"  class="select">
                        <option value="1"><spring:message code='calendar.event.category.1' /></option>
                        <option value="2"><spring:message code='calendar.event.category.2' /></option>
                        <option value="3"><spring:message code='calendar.event.category.3' /></option>
                        <option value="4"><spring:message code='calendar.event.category.4' /></option>            
                        <option value="5"><spring:message code='calendar.event.category.5' /></option>
                    </select>
                </td>
            </tr>
            <tr>
                <th><spring:message code='calendar.event.description' />&nbsp;<span class="tRed">*</span></th>
                <td>
                    <input name="description" id="description" type="text" class="text longText" maxlength="200"/>
                </td>
            </tr>
            <tr>
                <th class="narrowMin" style="white-space: nowrap;"><spring:message code='calendar.event.policy' /></th>
                <td>
                    <input type="radio" name="datetype" id="datetype0" value="0" onclick="$('#week').hide()" checked="checked"/>
                    <label for="datetype0"><spring:message code='calendar.event.policy.0' /></label>
                    <input name="datetype" type="radio" id="datetype1" value="1" onclick="$('#week').show()"/>
                    <label for="datetype1"><spring:message code='calendar.event.policy.1' /></label>
                </td>
            </tr>
            <tr id="day">
                <th>&nbsp;</th>
                <td>
                    <input id="startdate2" type="text" class="Wdate" value="${currentDate}" />
                    -
                    <input id="enddate2" type="text" class="Wdate" value="${currentDate}"/>
                </td>
              </tr>
      <tr id="week" style="display: none;">
        <th>&nbsp;</th>
        <td>
            <input name="ck" type="checkbox" id="ck1" value="1"/>
            <label for="ck1"><spring:message code='mainframe.common.monday' /></label>
            <input name="ck" type="checkbox" id="ck2" value="2"/>
            <label for="ck2"><spring:message code='mainframe.common.tuesday' /></label>
            <input name="ck" type="checkbox" id="ck3" value="3"/>
            <label for="ck3"/><spring:message code='mainframe.common.wednesday' /></label>
            <input name="ck" type="checkbox" id="ck4" value="4"/>
            <label for="ck4"/><spring:message code='mainframe.common.thursday' /></label>
            <input name="ck" type="checkbox" id="ck5" value="5"/>
            <label for="ck5"/><spring:message code='mainframe.common.friday' /></label>
            <input name="ck" type="checkbox" id="ck6" value="6"/>
            <label for="ck6"/><spring:message code='mainframe.common.saturday' /></label>
            <input name="ck" type="checkbox" id="ck7" value="7"/>
            <label for="ck7"/><spring:message code='mainframe.common.sunday' /></label>
        </td>
      </tr>
        <tr>
            <th style="white-space: nowrap;"><spring:message code='calendar.page.beginEndTime' /></th>
            <td>
                <input type="text" id="startdate1" class="Wdate" value="09:00" style="width: 70px"/> - <input type="text" id="enddate1" class="Wdate" value="18:30" style="width: 70px"/>
                <input name="checkbox" type="checkbox" id="allday" />
                <label for="allday"><spring:message code='calendar.event.allDay' /></label> 
            </td>   
        </tr>
        <tr>
            <th style="white-space: nowrap;"><spring:message code='calendar.event.notifyType' /></span>
            </th>
            <td>
                <select name="notifyhours" id="notifyhours" class="select" onchange="notifyhoursChange(this);">
                    <option value="0"><spring:message code='calendar.event.notifyType.0' /></option>
                    <option value="15"><spring:message code='calendar.event.notifyType.x' arguments="15" /></option>
                    <option value="30"><spring:message code='calendar.event.notifyType.x' arguments="30" /></option>
                    <option value="60"><spring:message code='calendar.event.notifyType.x' arguments="60" /></option>
                </select>
            </td>
        </tr>
        <tr id="TRNotifyInterval">
            <th style="white-space: nowrap;"><spring:message code='calendar.event.interval' /></th>
            <td><select name="nofityInterval" id="nofityInterval" class="select">
                    <option value="5"><spring:message code='calendar.event.interval.x' arguments="5" /></option>
                    <option value="10" selected="selected"><spring:message code='calendar.event.interval.x' arguments="10" /></option>
                    <option value="15"><spring:message code='calendar.event.interval.x' arguments="15" /></option>
                    <option value="20"><spring:message code='calendar.event.interval.x' arguments="20" /></option>
                    <option value="25"><spring:message code='calendar.event.interval.x' arguments="25" /></option>
                    <option value="30"><spring:message code='calendar.event.interval.x' arguments="30" /></option>
                </select></td>
          </tr>
          <tr id="TRNotifyAmount">
            <th style="white-space: nowrap;"><spring:message code='calendar.event.notifyAmoumt' /></th>
            <td><select name="notifyAmount" id="notifyAmount" class="select">
                    <option value="1"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="1" /></option>
                    <option value="2"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="2" /></option>
                    <option value="3" selected="selected"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="3" /></option>
                    <option value="4"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="4" /></option>
                    <option value="5"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="5" /></option>
                    <option value="6"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="6" /></option>
                    <option value="7"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="7" /></option>
                    <option value="8"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="8" /></option>
                    <option value="9"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="9" /></option>
                    <option value="10"><spring:message code='calendar.event.notifyAmoumt.unit' arguments="10" /></option>
                </select></td>
          </tr>
        <tr id="TRPublicEvent">
            <th style="white-space: nowrap;"><spring:message code='calendar.event.staff'/>&nbsp;<span class="tRed">*</span></th>
            <td>
                <div class="optionList floatLeft">
                    <p><spring:message code='calendar.event.staff.toSelect' />&nbsp;<input class="text longText" id="searchkey" type="text" initColor="true" value="<spring:message code='calendar.event.staff.query' />"/></p>
                    <select name="selectlist" id="selectlist" multiple="multiple">
                    </select>
                </div>
                <div id="moveReceiverDiv" class="selectBtn floatLeft">
                    <a id="addSingle" href="###" title="<spring:message code='calendar.event.staff.add.single' />">
                        <img alt="<spring:message code='calendar.event.staff.add.single' />" src="../skins/default/images/iconSelectSingleR.gif" width="20" height="20" />
                    </a>
                    <a id="removeSingle" href="###" title="<spring:message code='calendar.event.staff.remove.single' />">
                        <img alt="<spring:message code='calendar.event.staff.remove.single' />" src="../skins/default/images/iconSelectSingleL.gif" width="20" height="20" />
                    </a>
                    <a id="addAll" href="###" title="<spring:message code='calendar.event.staff.add.all' />">
                        <img alt="<spring:message code='calendar.event.staff.add.all' />" src="../skins/default/images/iconSelectAllR.gif" width="20" height="20" />
                    </a>
                    <a id="removeAll" href="###" title="<spring:message code='calendar.event.staff.remove.all' />">
                        <img alt="<spring:message code='calendar.event.staff.remove.all' />" src="../skins/default/images/iconSelectAllL.gif" width="20" height="20" />
                    </a>
                </div>
                <div class="selectedList">
                    <p><spring:message code='calendar.event.staff.selected' /></p>
                    <select name="selectedlist" id="selectedlist" multiple="multiple">
                    </select>
                </div><div id="groupTree" style="display: none;"></div>
            </td>
        </tr>
    </table>
  </div>
    <div class="doClose">
        <input id="butOK" type="button" class="button" value="<spring:message code='mainframe.common.ok' />"  />
    </div>
</div>
</body>
</html>