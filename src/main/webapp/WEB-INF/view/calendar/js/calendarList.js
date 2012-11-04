var theEventsQuery = {};
var theEventsQueryResult = {};

var lpad = function(str, vlength, vchar)
{
    var str1 = str;
    if (vchar == "") 
    {
        return str;
    }
    while (str1.length < vlength) 
    {
        str1 = vchar + str1;
    }
    return str1;
};

//yyyy-MM-dd HH:mm:ss
var toDateString = function(vDate)
{
    var strTmp = vDate.getFullYear().toString();
    strTmp += "-";
    strTmp += lpad((vDate.getMonth() + 1).toString(), 2, "0");
    strTmp += "-";
    strTmp += lpad(vDate.getDate().toString(), 2, "0");
    strTmp += " ";
    strTmp += lpad(vDate.getHours().toString(), 2, "0");
    strTmp += ":";
    strTmp += lpad(vDate.getMinutes().toString(), 2, "0");
    strTmp += ":";
    strTmp += lpad(vDate.getSeconds().toString(), 2, "0");
    return strTmp;
};

function dataFetcher(pageSize, pageIndex, fnCallBack)
{
    theEventsQuery.inputParams.pagesize = pageSize;
    theEventsQuery.inputParams.pageindex = pageIndex;
    calendarDWRBean.queryCalendarEventListForUpdate(theEventsQuery.inputParams, {
        callback: function(data)
        {
            theEventsQueryResult = data;
            fnCallBack({
                pageIndex: theEventsQueryResult.pageindex,
                rowCount: theEventsQueryResult.resultCount,
                data: theEventsQueryResult.list
            });
        }
    });
}

function rowInteceptor(row)
{
    var $tr = jQuery("<tr></tr>");
    var $chk = jQuery("<input type='checkbox' name='ck'/>");
    $chk.val(row.id);
    jQuery("<td></td>").append($chk).appendTo($tr);
    jQuery("<td></td>").text(row.description).appendTo($tr);
    jQuery("<td></td>").text(eventType[row.eventtype]).appendTo($tr);
    jQuery("<td></td>").text(eventPolicy[row.datetype]).appendTo($tr);
    jQuery("<td></td>").text(toDateString(row.startdate)).appendTo($tr);
    jQuery("<td></td>").text(toDateString(row.enddate)).appendTo($tr);
    jQuery("<td></td>").text(row.notifyhours == 0 ? notifyType[0] : notifyType[1].replace("\{0\}", row.notifyhours)).appendTo($tr);
    jQuery("<td></td>").text("" + row.createoper).appendTo($tr);
    jQuery("<td></td>").text(toDateString(row.createdate)).appendTo($tr);
    var $a = jQuery("<a href=\'javascript:void(0);'></a>").text(modify).click(function()
    {
        dialogUtils.openWin({
            id: "modifyCalendarEventDlg",
            url: "addModifyEvent.jsp?id=" + row.id,
            title: modifyEventDlgTitle,
            limit: true,
            width: 750,
            height: 540,
            onInit: function()
            {
                this.getContentWindowEl().openerWindow = window;
            }
        });
    });
    jQuery("<td class=\"tCenter\"></td>").append($a).appendTo($tr);
    return $tr;
}

function initQueryParam()
{
    theEventsQuery.inputParams = {
        eventtype: jQuery("#eventtype").val(),
        datetype: jQuery("#datetype").val(),
        operid: operId,
        kind: operKind,
        vcid: vcid,
        groupid: operGroupId,
        description: jQuery.trim(jQuery("#description").val()),
        startdate: jQuery("#startdate").val(),
        enddate: jQuery("#enddate").val(),
        pageindex: 0,
        pagesize: jQuery("#pagesize").val()
    };
}

var paginator = null;
ZS.setSkin("unitView");
jQuery(function()
{
    dialogUtils.setLocale(clientLocale);
    ZS.use("dialog");
    
    jQuery("#butAdd").click(function()
    {
        dialogUtils.openWin({
            id: "addCalendarEventDlg",
            url: "addModifyEvent.jsp",
            title: addEventDlgTitle,
            limit: true,
            width: 750,
            height: 540,
            onInit: function()
            {
                this.getContentWindowEl().openerWindow = window;
            }
        });
    });
    
    
    jQuery("#butQuery").click(function()
    {
        initQueryParam();
        paginator.refresh();
    });
    
    jQuery("#butDel").click(function()
    {
        dialogUtils.confirm(deleteConfirm, function()
        {
            var eventids = [];
            jQuery(":checkbox[name='ck']:checked").each(function(n)
            {
                eventids.push(jQuery(this).val());
            });
            if (eventids.length == 0) 
            {
                return;
            }
            
            calendarDWRBean.deleteCalendarEvents(eventids.join(","), {
                callback: function(data)
                {
                    if (data.err == 0) 
                    {
                        dialogUtils.openTip(deleteSucceeded, 2);
                        jQuery("#butQuery").click();
                    }
                    else 
                    {
                        dialogUtils.openTip(deleteFailed, 2);
                    }
                },
                errorHandler: function()
                {
                    dialogUtils.openTip(deleteFailed, 2);
                }
            });
        });
    });
    
    initQueryParam();
    paginator = new Paginator({
        dataFetcher: dataFetcher,
        rowInterpreter: rowInteceptor,
        nullPrompt: noData
    });
});
