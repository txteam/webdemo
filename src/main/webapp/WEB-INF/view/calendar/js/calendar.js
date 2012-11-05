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

jQuery(function()
{   
//    jQuery("#butMng").click(function()
//    {
//        location.href = "calendarList.jsp";
//    });
    
    jQuery.fullCalendar.setDefaults({
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
            month: clientLocale == "zh-CN" ? "yyyy'" + message.year + "'MMM'" + message.month + "'" : "MMMM yyyy" ,
            week: clientLocale == "zh-CN" ? "yyyy'" + message.year + "'MMM'" + message.month + "'d'" + message.day + "'{ '&#8212;' yyyy'" + message.year + "'MMM'" + message.month + "'d'" + message.day + "'}" : "MMMM d[ yyyy]{ '&#8212;'[ MMMM] d yyyy}",
            day: clientLocale == "zh-CN" ? "yyyy'" + message.year + "'MMM'" + message.month + "'d'" + message.day + "' dddd" : "dddd, MMMM d, yyyy"
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
    
    jQuery('#calendar').fullCalendar({
        theme: true,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        events: function(start, end, callback)
        {
            calendarDWRBean.queryCalendarEventList({
                startdate: "" + start.getTime(),
                enddate: "" + end.getTime(),
                operid: operId
            }, {
                callback: function(data)
                {
                    var result = (new Function("return " + data))();
                    for (var i = 0; i < result.length; i++) 
                    {
                        result[i].start = new Date(result[i].start);
                        result[i].end = new Date(result[i].end);
                    }
                    callback(result);
                },
                errorHandler: function()
                {
                    dialogUtils.openTip(message.queryCalendarError, 2)
                }
            });
        },
        eventMouseover: function(ev)
        {
            if (ev.eventtype == 1) 
            {
                jQuery("#eventtype").text(message.eventScope.publicEvent);
                jQuery("#updateoper").text(ev.updateoper);
                jQuery("#trupdateoper").show();
            }
            else 
            {
                jQuery("#eventtype").text(message.eventScope.personalEvent);
                jQuery("#trupdateoper").hide();
            }
            
            if (ev.description.length < 100) 
            {
                jQuery("#description").text(ev.description);
            }
            else
            {
                jQuery("#description").text(ev.description.substring(0, 94) + "...");
            }
        },
        eventClick: function(ev)
        {
            if (ev.eventtype == 0) 
            {
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
        eventRender: function(calEvent, element)
        {
            function initToolTip()
            {
                addToolTip.apply(element);
            }
            
            setTimeout(initToolTip, 1);
        }
    });
    
});
