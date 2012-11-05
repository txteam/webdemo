<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- 行事历 -->
<title><spring:message code="calendar.page.title" /></title>
<link rel="stylesheet" type="text/css" href="${contextPath }/css/commons.css"  />
<link rel='stylesheet' type='text/css' href='${contextPath }/calendar/skins/redmond/theme.css' />
<link rel='stylesheet' type='text/css' href='${contextPath }/calendar/skins/redmond/fullcalendar.css' />

<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/operamasks/js/operamasks-ui.min.js"></script>
<script type="text/javascript" src="${contextPath }/jquery-ui/js/jquery-ui.js"></script>

<script type='text/javascript' src='${contextPath }/calendar/js/fullcalendar.min.js'></script>
<!--  script type="text/javascript" src="${contextPath }/calendar/js/calendar.js"></script> -->
<style type='text/css'> 
html,body {
    overflow-y: hidden;
    padding-top:5px;
    padding-left: 30px;
}
#calendar {
    margin-top:5px;
}
.tooltip {
    display:none;
    background:url(skins/images/black_arrow_big.gif) no-repeat;
    height:163px;
    width:310px;
    padding:25px;
    font-size:11px;
    color:#fff;
    z-index: 255
}

.tooltip .label {
    color:yellow;
    width:68px;
}
   
.tooltip .desc {
     width:260px;
     height:86px;
     overflow:auto;
}

.tooltip a {
    color:#ad4;
    font-size:11px;
    font-weight:bold;
}

.tooltipupward {
    display:none;
    background:url(skins/images/black_arrow_big_up.gif) no-repeat;
    height:163px;
    width:310px;
    padding:50px 25px 25px 25px;
    font-size:11px;
    color:#fff;
    z-index: 255
}

.tooltipupward .label {
    color:yellow;
    width:68px;
}
   
.tooltipupward .desc {
     width:260px;
     height:86px;
     overflow:auto;
}

.tooltipupward a {
    color:#ad4;
    font-size:11px;
    font-weight:bold;
}        
</style>
<script type="text/javascript">
var operId = "${Operator.operatorid}";
var message = {
    detailInfo : "<spring:message code='mainframe.common.detail' />",
    beginDate : "<spring:message code='calendar.event.beginDate' />",
    endDate : "<spring:message code='calendar.event.endDate' />",
    candel : "<spring:message code='mainframe.common.cancel' />",
    months : {
        january : "<spring:message code='mainframe.common.january' />",
        february : "<spring:message code='mainframe.common.february' />",
        march : "<spring:message code='mainframe.common.march' />",
        april : "<spring:message code='mainframe.common.april' />",
        may : "<spring:message code='mainframe.common.may' />",
        june : "<spring:message code='mainframe.common.june' />",
        july : "<spring:message code='mainframe.common.july' />",
        august : "<spring:message code='mainframe.common.august' />",
        september : "<spring:message code='mainframe.common.september' />",
        octorber : "<spring:message code='mainframe.common.octorber' />",
        november : "<spring:message code='mainframe.common.november' />",
        december : "<spring:message code='mainframe.common.december' />"
    },
    daysOfWeek : {
        monday : "<spring:message code='mainframe.common.monday' />",
        mondayShort : "<spring:message code='mainframe.common.monday.short' />",
        tuesday : "<spring:message code='mainframe.common.tuesday' />",
        tuesdayShort : "<spring:message code='mainframe.common.tuesday.short' />",
        wednesday : "<spring:message code='mainframe.common.wednesday' />",
        wednesdayShort : "<spring:message code='mainframe.common.wednesday.short' />",
        thursday : "<spring:message code='mainframe.common.thursday' />",
        thursdayShort : "<spring:message code='mainframe.common.thursday.short' />",
        friday : "<spring:message code='mainframe.common.friday' />",
        fridayShort : "<spring:message code='mainframe.common.friday.short' />",
        saturday : "<spring:message code='mainframe.common.saturday' />",
        saturdayShort : "<spring:message code='mainframe.common.saturday.short' />",
        sunday : "<spring:message code='mainframe.common.sunday' />",
        sundayShort : "<spring:message code='mainframe.common.sunday.short' />"
    },
    allDay : "<spring:message code='calendar.event.allDay' />",
    today : "<spring:message code='mainframe.common.today' />",
    day : "<spring:message code='mainframe.common.day' />",
    week : "<spring:message code='mainframe.common.week' />",
    month : "<spring:message code='mainframe.common.month' />",
    year : "<spring:message code='mainframe.common.year' />",
    eventScope : {
        personalEvent : "<spring:message code='calendar.event.scope.0' />",
        publicEvent : "<spring:message code='calendar.event.scope.1' />"
    },
    queryCalendarError : "<spring:message code='calendar.page.queryCalendarError' />",
    modifyEventDlgTitle : "<spring:message code='calendar.page.modifyTitle' />"
};
var clientLocale = "${localeBean.clientLocale}";

function addToolTip()
{
    var arrowUp = this.offset().top < 163;
    
    this.tooltip({
        effect: 'slide',
        tip: '#toolTipDiv',
        position: arrowUp ? 'bottom center' : 'top center',
        bounce: true,
        delay: 0,
        predelay: 250,
        onBeforeShow: function(event)        
        {
            if (arrowUp)
            {
                $("#toolTipDiv").removeClass("tooltip").addClass("tooltipupward");
            }
            else
            {
                $("#toolTipDiv").removeClass("tooltipupward").addClass("tooltip");
            }
        }
    });
}

$(document).ready(function(){
    $.fullCalendar.setDefaults({
        monthNames: [message.months.january, message.months.february, message.months.march, message.months.april, message.months.may, message.months.june, message.months.july, message.months.august, message.months.september, message.months.octorber, message.months.november, message.months.december],
        monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        dayNames: [message.daysOfWeek.sunday, message.daysOfWeek.monday, message.daysOfWeek.tuesday, message.daysOfWeek.wednesday, message.daysOfWeek.thursday, message.daysOfWeek.friday, message.daysOfWeek.saturday],
        dayNamesShort: [message.daysOfWeek.sundayShort, message.daysOfWeek.mondayShort, message.daysOfWeek.tuesdayShort, message.daysOfWeek.wednesdayShort, message.daysOfWeek.thursdayShort, message.daysOfWeek.fridayShort, message.daysOfWeek.saturdayShort],
        buttonText: {
            prev: '&nbsp;&#9668;&nbsp;',
            next: '&nbsp;&#9658;&nbsp;',
            prevYear: '&nbsp;&lt;&lt;&nbsp;',
            nextYear: '&nbsp;&gt;&gt;&nbsp;',
            today: message.today,
            month: message.month,
            week: message.week,
            day: message.day
        },
        allDayText: message.allDay,
        titleFormat: {
            month: "yyyy'" + message.year + "'MMM'" + message.month + "'",
            week: "yyyy'" + message.year + "'MMM'" + message.month + "'d'" + message.day + "'{ '&#8212;' yyyy'" + message.year + "'MMM'" + message.month + "'d'" + message.day + "'}",
            day: "yyyy'" + message.year + "'MMM'" + message.month + "'d'" + message.day + "' dddd"
        },
        columnFormat: {
            month: 'ddd',
            week: clientLocale == "zh-CN" ? "dddd M'" + message.month + "'d'" + message.day + "'" : "dddd, MMMM d",
            day: clientLocale == "zh-CN" ? "dddd M'" + message.month + "'d'" + message.day + "'" : "dddd, MMMM d"
        },
        timeFormat: { // for event elements
            '': 'HH:mm', // default
            agenda: 'HH:mm{ - HH:mm}'
        },
        axisFormat: 'HH:mm'
    });
    
    $("#calendar").fullCalendar({
        theme: true,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        defaultView:'agendaWeek',
        events:function(start, end, callback){
        	jQuery.ajax( {  
            	type : "POST",
            	cache : false,
            	contentType : "application/json",  
                url : "${contextPath}/canlendar/queryCalendarEventList",  
                dataType : 'json', 
                data:{
                    startdate: "2012-11-04",
                    enddate: "2012-11-09"
                },
                success : function(data) {  
                	for (var i = 0; i < result.length; i++){
                        result[i].start = new Date(result[i].start);
                        result[i].end = new Date(result[i].end);
                    }
                    callback(result);
                },  
                error : function() {  
                  alert("error");  
                }  
            });
        },
        eventMouseover: function(ev){
            if (ev.eventtype == 1){
                jQuery("#eventtype").text(message.eventScope.publicEvent);
                jQuery("#updateoper").text(ev.updateoper);
                jQuery("#trupdateoper").show();
            }
            else {
                jQuery("#eventtype").text(message.eventScope.personalEvent);
                jQuery("#trupdateoper").hide();
            }
            
            if (ev.description.length < 100){
                jQuery("#description").text(ev.description);
            }
            else{
                jQuery("#description").text(ev.description.substring(0, 94) + "...");
            }
        },
        eventClick: function(ev){
            if (ev.eventtype == 0){
                dialogUtils.openWin({
                    id: "modifyCalendarEventDlg",
                    url: "addModifyEvent.jsp?id=" + ev.id + "&from=calendar",
                    title: message.modifyEventDlgTitle,
                    limit: true,
                    width: 750,
                    height: 540
                });
            }
        },
        eventRender: function(calEvent, element){
            function initToolTip(){
                addToolTip.apply(element);
            }
            setTimeout(initToolTip, 1);
        }
    });
    
    $("#managerCalendar").button();
});
</script>
</head>
<body>
<div style="float: left;margin-top: 5px">
    <input id="managerCalendar"
    	type="button" class="button" value="<spring:message code='calendar.page.manage' />" 
    	onClick="window.location='calendarList.jsp'"/>
    &nbsp;&nbsp;&nbsp;&nbsp;
</div>

<div id="clendarContent" style="width: 50%;float: left;">
	<div id='calendar'></div>
</div>


<div id="toolTipDiv" style="display: none;float: left">
    <table style="margin:0">
        <tbody>     
        <tr>
            <td class="label"><spring:message code='calendar.event.scope' /></td>
            <td><span id="eventtype"></span></td>
        </tr>
        <tr>
            <td class="label" valign="top"><spring:message code='calendar.event.description' /></td>
            <td><div class="desc"><span id="description"></span></div></td>
        </tr>
        <tr id="trupdateoper">  
            <td class="label"><spring:message code='mainframe.common.lastModify' /></td>
            <td><span id="updateoper">50000</span></td>         
        </tr>   
        </tbody>            
    </table>
</div>
</body>
</html>