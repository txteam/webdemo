// 工号数据
var operdata = {};
// 当前事件的信息
var calEvent = {};

var selectedopers = [];
var selectedgroups = [];
var validator = null;

ZS.setSkin("unitView");

function notifyhoursChange(ctrl)
{
    if (parseInt($(ctrl).val()) == 0)
    {
        $("#TRNotifyInterval").hide();
        $("#TRNotifyAmount").hide();
    }
    else
    {
        $("#TRNotifyInterval").show();
        $("#TRNotifyAmount").show();
    }
}

jQuery(function()
{
    dialogUtils.setLocale(clientLocale);
    
    ZS.use("dialog, validator", function()
    {
        validator = new ZS.Validator({
            rules: [{
                id: "#description",
                require: true,
                warn: descValidatePrompt
            }, {
                id: "#enddate2",
                warn: weekValidatePrompt,
                require: false,
                vtype: function()
                {
                    calEvent.datetype = jQuery(":radio[name='datetype']:checked").val();
                    if (calEvent.datetype == "1") 
                    {
                        var chks = jQuery(":checkbox[name='ck']:checked");
                        if (chks.size() == 0) 
                        {
                            return false;
                        }
                        else 
                        {
                            var days = [];
                            chks.each(function(n)
                            {
                                days.push(jQuery(this).val());
                            });
                            calEvent.days = days.join(",");
                        }
                    }
                    return true;
                }
            }]
        });
    });
    
    // 默认公共事件不显示
    jQuery("#TRPublicEvent select").hide();
    jQuery("#TRPublicEvent").hide();
    
    // 如果不是公共事件，则不显示工号选择区域
    jQuery(":radio[name='eventtype']").click(function()
    {
        var $this = jQuery(this);
        if ($this.val() == "1") 
        {
        
            jQuery("#TRPublicEvent").show();
            jQuery("#TRPublicEvent select").show();
            
            // 有无加载班组和工号数据
            if (!$this.attr("subsdata")) 
            {
                $this.attr("subsdata", "true");
                // 获取班组数据
                // if kind==2 or 3 質檢和班長
                calendarDWRBean.queryGroupsAndOperators(operKind, operGroupId, operVcid, function(data)
                {
                    setTimeout(function()
                    {
                    
                        operdata = window["eval"]("(" + data + ")");
                        
                        var $selectlist = jQuery("#selectlist");
                        if (operdata.groups) 
                        {
                            var $div = jQuery("#groupTree");
                            
                            jQuery.each(operdata.groups, function(i, n)
                            {
                                var $opt = jQuery("<option></option>");
                                $opt.text(n.name + "(" + group + ")").val("G" + n.groupid).appendTo($selectlist);
                                
                                // 保存组的层次关系
                                var $parent = $div.find("ul[gid='" + n.parent + "']");
                                if ($parent.size() == 0) 
                                {
                                    $parent = jQuery("<ul></ul>").attr("gid", "" + n.parent);
                                    $parent.appendTo($div);
                                }
                                
                                $thisUl = jQuery("<ul></ul>").attr("gid", "" + n.groupid);
                                $thisUl.appendTo($parent);
                            });
                        }
                        if (operdata.opers) 
                        {
                            jQuery.each(operdata.opers, function(i, n)
                            {
                                var $opt = jQuery("<option></option>");
                                $opt.text("" + n.id + "-" + n.name).val("O" + n.id).appendTo($selectlist);
                            });
                        }
                        
                        orgoptions = $selectlist.children();
                    }, 200);
                    
                });
            }
            
        }
        else 
        {
            jQuery("#TRPublicEvent select").hide();
            jQuery("#TRPublicEvent").hide();
        }
    });
    
    // 初始化时间控件
    jQuery("#startdate2").focus(function()
    {
        var inenddate = jQuery('#enddate2')[0];
        WdatePicker({
            skin: 'whyGreen',
            dateFmt: 'yyyy-MM-dd',
            lang: clientLocale,
            maxDate: "#F{$dp.$D('enddate2')}",
            onpicked: function()
            {
                try 
                {
                    inenddate.focus();
                } 
                catch (e) 
                {
                }
            }
        });
    });
    
    jQuery("#enddate2").focus(function()
    {
        WdatePicker({
            skin: 'whyGreen',
            dateFmt: 'yyyy-MM-dd',
            lang: clientLocale,
            minDate: "#F{$dp.$D('startdate2')}"
        });
    });
    
    $("#startdate1").focus(function()
    {
        WdatePicker({
            skin: 'whyGreen',
            dateFmt: 'HH:mm',
            lang: clientLocale,
            maxDate: "#F{$dp.$D('enddate1')}"
        });
    });
    
    $("#enddate1").focus(function()
    {
        WdatePicker({
            skin: 'whyGreen',
            dateFmt: 'HH:mm',
            lang: clientLocale,
            minDate: "#F{$dp.$D('startdate1')}"
        });
    });
    
    // 全天开关
    jQuery("#allday").click(function()
    {
        if (jQuery(this).is(":checked")) 
        {
            jQuery("#startdate1,#enddate1").attr("disabled", "disabled");
        }
        else 
        {
            jQuery("#startdate1,#enddate1").removeAttr("disabled");
        }
    });
    
    jQuery("#startdate2").change(function()
    {
        var val = jQuery(this).val();
        if (jQuery("#enddate2").val() < val) 
        {
            jQuery("#enddate2").val(val);
        }
    });
    
    var parseDate = function(s)
    {
        var r = s.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
        return new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
    };
    
    // 输入查询时 进行搜寻处理
    jQuery("#searchkey").keyup(function(ev)
    {
        // press enter
        var $this = jQuery(this);
        var $selectlist = jQuery("#selectlist");
        if (ev.keyCode == 13) 
        {
            var val = $this.val();
            if (val == "") 
            {
                $selectlist.empty().append(orgoptions);
            }
            else 
            {
                $selectlist.empty();
                jQuery.each(orgoptions, function(i, n)
                {
                    var $n = jQuery(n);
                    if ($n.text().indexOf(val) != -1) 
                    {
                        $selectlist.append($n);
                    }
                });
            }
        }
    }).focus(function()
    {
        var $this = $(this);
        if ($this.val() == toSelectPrompt) 
        {
            $this.val("");
        }
    });
    
    // 添加选择项目
    var addOptions = function(opts)
    {
        var $selectlist = jQuery("#selectlist");
        var $selectedlist = jQuery("#selectedlist");
        opts.each(function(i, n)
        {
            if ($selectedlist.find("option[value='" + n.value + "']").size() == 0) 
            {
                var $optNew = jQuery("<option></option>");
                $optNew.text(n.text).val(n.value).appendTo($selectedlist);
            }
        });
    };
    
    // 选入选出
    jQuery("#moveReceiverDiv a").click(function()
    {
        var $this = jQuery(this);
        var $selectlist = jQuery("#selectlist");
        if ($this.attr("id") == "addSingle") 
        {
            addOptions($selectlist.find("option:selected"));
        }
        else if ($this.attr("id") == "removeSingle") 
        {
            jQuery("#selectedlist").find("option:selected").remove();
        }
        else if ($this.attr("id") == "addAll") 
        {
            addOptions($selectlist.find("option"));
        }
        else 
        {
            jQuery("#selectedlist").find("option").remove();
        }
    });
    
    // 计算最后选中了那些操作员
    var createOpers = function()
    {
        if (selectedgroups.length > 0) 
        {
            selectedgroups.splice(0, selectedgroups.length);
        }
        
        if (selectedopers.length > 0) 
        {
            selectedopers.splice(0, selectedopers.length);
        }
        
        var $selectedlist = jQuery("#selectedlist");
        var $div = jQuery("#groupTree");
        $selectedlist.find("option").each(function(n)
        {
            var val = this.value;
            if (val.indexOf("G") != -1) 
            {
                var gid = val.substr(1);
                addSelectedGroup(gid);
                var children = $div.find("ul[gid='" + gid + "']").find("ul");
                children.each(function(n)
                {
                    $n = jQuery(this);
                    addSelectedGroup($n.attr("gid"));
                });
            }
            else 
            {
                // 添加客服代表
                addSelectedOper(val.substr(1));
            }
        });
        // 选中了那些组
        if (operdata.opers) 
        {
            jQuery.each(selectedgroups, function(i, n)
            {
                jQuery.each(operdata.opers, function(i1, n1)
                {
                    if ("" + n1.groupid == n) 
                    {
                        addSelectedOper("" + n1.id);
                    }
                });
            });
        }
    };
    
    var addSelectedGroup = function(grpId)
    {
        if (jQuery.inArray(grpId, selectedgroups) == -1) 
        {
            selectedgroups.push(grpId);
        }
    };
    var addSelectedOper = function(operId)
    {
        if (jQuery.inArray(operId, selectedopers) == -1) 
        {
            selectedopers.push(operId);
        }
    };
    
    // 保存按钮
    jQuery("#butOK").click(function()
    {
    
        jQuery("#butOK").attr("disabled", "disabled").removeClass("button").addClass("buttonDisabled");
        if (!validator.validate()) 
        {
            jQuery("#butOK").removeAttr("disabled").removeClass("buttonDisabled").addClass("button");
            return;
        }
        
        calEvent.description = jQuery("#description").val();
        
        calEvent.eventtype = jQuery(":radio[name='eventtype']:checked").val();
        calEvent.catid = jQuery("#catid").val();
        
        // 提醒
        calEvent.notify = 0;
        calEvent.notifyhours = 0;
        calEvent.interval = 0;
        calEvent.notifyAmount = 0;
        var notify = jQuery("#notifyhours").val();
        if ("0" != notify) 
        {
            calEvent.notify = 1;
            calEvent.notifyhours = parseInt(notify);
            calEvent.interval = parseInt(jQuery("#nofityInterval").val());
            calEvent.notifyAmount = parseInt(jQuery("#notifyAmount").val());
        }
        
        calEvent.allday = 0;
        if (jQuery("#allday").is(":checked")) 
        {
            calEvent.allday = 1;
        }
        calEvent.startdate = parseDate(jQuery("#startdate2").val() + " " + jQuery("#startdate1").val() + ":00");
        calEvent.enddate = parseDate(jQuery("#enddate2").val() + " " + jQuery("#enddate1").val() + ":00");
        calEvent.updateoper = operId;
        
        // 如果是公共事件
        if (calEvent.eventtype == "1") 
        {
            createOpers();
        }
        
        calendarDWRBean.insertCalendarEvent(calEvent, selectedopers, {
            callback: function(data)
            {
                if (data && data.err == 0) 
                {
                    if (needRefresh) 
                    {
                        parent.paginator.refreshCurrent();
                    }
                    parent.dialogUtils.closeDialogById(eventId == "" ? "addCalendarEventDlg" : "modifyCalendarEventDlg");
                }
                else 
                {
                    dialogUtils.openTip(operateFailed, 2);
                    jQuery("#butOK").removeAttr("disabled").removeClass("buttonDisabled").addClass("button");
                }
            },
            errorHandler: function(data)
            {
                dialogUtils.openTip(operateFailed, 2);
                jQuery("#butOK").removeAttr("disabled").removeClass("buttonDisabled").addClass("button");
            }
        });
        
    });
    
    // 修改的初始化
    if (eventId != "") 
    {
        calendarDWRBean.queryCalendarEventById(eventId, {
            callback: function(data)
            {
                if (data.calEvent) 
                {
                    calEvent = data.calEvent;
                    
                    if (calEvent.eventtype == 0) 
                    {
                        jQuery("#eventtype0").attr("checked", "checked");
                        jQuery("#eventtype1").attr("disabled", "disabled");
                    }
                    else 
                    {
                        jQuery("#eventtype0").attr("disabled", "disabled");
                        jQuery("#eventtype1").attr("checked", "checked").triggerHandler("click");
                    }
                    
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
                    
                    // yyyy-MM-dd
                    var toDateString = function(vDate)
                    {
                        var strTmp = vDate.getFullYear().toString();
                        strTmp += "-";
                        strTmp += lpad((vDate.getMonth() + 1).toString(), 2, "0");
                        strTmp += "-";
                        strTmp += lpad(vDate.getDate().toString(), 2, "0");
                        return strTmp;
                    };
                    
                    // HH:mm
                    var toTimeString = function(vDate)
                    {
                        var strTmp = lpad(vDate.getHours().toString(), 2, "0");
                        strTmp += ":";
                        strTmp += lpad(vDate.getMinutes().toString(), 2, "0");
                        return strTmp;
                    };
                    
                    jQuery("#catid").val("" + calEvent.catid);
                    jQuery("#description").val(calEvent.description);
                    if (calEvent.datetype == 0) 
                    {
                        jQuery("#startdate2").val(toDateString(calEvent.startdate));
                        jQuery("#enddate2").val(toDateString(calEvent.enddate));
                        
                        jQuery("#datetype0").click();
                    }
                    else 
                    {
                        jQuery("#startdate2").val(toDateString(calEvent.startdate));
                        jQuery("#enddate2").val(toDateString(calEvent.enddate));
                        
                        // 按周？
                        var days = calEvent.days.split(",");
                        
                        jQuery.each(days, function(i, n)
                        {
                            jQuery(":checkbox[name='ck'][value='" + n +
                            "']").attr("checked", "checked");
                        });
                        jQuery("#datetype1").click();
                    }
                    
                    // 初始化时间
                    
                    jQuery("#startdate1").val(toTimeString(calEvent.startdate));
                    jQuery("#enddate1").val(toTimeString(calEvent.enddate));
                    
                    if (calEvent.allday == 1) 
                    {
                        jQuery("#allday").attr("checked", "checked").triggerHandler("click");
                    }
                    
                    jQuery("#notifyhours").val(calEvent.notifyhours);
                    if (calEvent.notifyhours == 0) 
                    {
                        jQuery("#TRNotifyInterval").hide();
                        jQuery("#TRNotifyAmount").hide();
                    }
                    else 
                    {
                        jQuery("#nofityInterval").val(calEvent.interval);
                        jQuery("#notifyAmount").val(calEvent.notifyAmount);
                    }
                    
                    // 如果是公共事件
                    if (calEvent.eventtype == 1 &&
                    data.selectedoperlist) 
                    {
                        var $selectedlist = jQuery("#selectedlist");
                        jQuery.each(data.selectedoperlist, function(i, n)
                        {
                            var $opt = jQuery("<option></option>");
                            $opt.text("" + n.operid + "-" + n.name).val("O" + n.operid).appendTo($selectedlist);
                        });
                    }
                }
            },
            errorHandler: function()
            {
            }
        });
    }
    
});
