function LinkedSelect() {
	return this;
}





/**
 * 
 * 省市区数据初始化
 * 
 */
function initDistrictSelect() {
	var hprovinceId=$("#hprovinceId").val();
	var hcountyDistrictId=$("#hcountyDistrictId").val();
	var hcityDistrictId=$("#hcityDistrictId").val();
	var array = [
   	{
  		id : "provinceId",
  		url : _contextPath + '/district/queryProvinceDistrictList.action',
  		nullable : true,
  		defaultValue:hprovinceId
  		
   	},
	{
		id : "cityDistrictId",
		url : _contextPath + '/district/queryDistrictListIncludeInvalid.action',
		nullable : true,
		defaultValue:hcityDistrictId 
		
	}, {
		id : "countyDistrictId",
		url : _contextPath + '/district/queryDistrictListIncludeInvalid.action',
		nullable : true,
		defaultValue:hcountyDistrictId
	}]
	var select = new LinkedSelect();
	select.initNextSelect(array,null, 0);
}

/**
 * 
 * 基础数据联动使用
 * 
 * 
 */
LinkedSelect.prototype = {
	_initselectInputs : ([ 
	{
		id : "cityDistrictId",
		url : _contextPath + '/district/queryDistrictListIncludeInvalid.action',
		nullable : true,
		defaultValue:$("#cityDistrictId").val()
		
	}, {
		id : "countyDistrictId",
		url : _contextPath + '/district/queryDistrictListIncludeInvalid.action',
		nullable : true
	} ]),

	initNextSelect : function(config, parentId, currentIndex) {
		if (null == config || undefined == config) {

			config = new Array();
		}

		this._initselectInputs = $.extend(this._initselectInputs, config);

		if (this._initselectInputs.length <= currentIndex) {

			return;
		}

		var currentSelect = this._initselectInputs[currentIndex];

		if (currentSelect == undefined) {

			return;
		}

		$("#" + currentSelect.id).empty();

		if (this._initselectInputs.length > currentIndex + 1) {
			
			$("#" + currentSelect.id).unbind("change");
			
			$("#" + currentSelect.id).bind("change", function() {
			
				var select = new LinkedSelect();
			
				select.initNextSelect(config, $(this).val(), currentIndex + 1);
			});
		}

		if (currentIndex != 0) {

			if (!parentId || parentId == '') {

				$("#" + currentSelect.id).append(
						"<option value=''>--请选择--</option>");

				var select = new LinkedSelect();

				select.initNextSelect(config, $("#" + currentSelect.id).val(),
						currentIndex + 1);

				return;
			}
		}

		$.post(currentSelect.url,

		{
			parentId : parentId
		},

		function(data) {
			if (currentSelect.nullable) {

				$("#" + currentSelect.id).append(
						"<option value=''>--请选择--</option>");
			}
            
			$.each(data, function(index, selectItem) {
				if (selectItem.id == currentSelect.defaultValue) {

					$("#" + currentSelect.id).append(
							"<option selected='selected' value='"
									+ selectItem.id + "'>"
									+ selectItem.name + "</option>");
				} else {

					$("#" + currentSelect.id).append(
							"<option value='" + selectItem.id + "'>"
									+ selectItem.name + "</option>");

				}
			});

			var select = new LinkedSelect();

			select.initNextSelect(config, $("#" + currentSelect.id).val(),
					currentIndex + 1);

		}, "json");
	}
};

/**
 * 小数,整数加法
 * 
 * @param arg1
 *            被加数
 * @param arg2
 *            加数
 * @returns {Number}
 */
function addNum(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split('.')[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split('.')[1].length;
	} catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m + arg2 * m) / m;
}

/**
 * 小数,整数减法
 * 
 * @param arg1
 *            被减数
 * @param arg2
 *            减数
 * @returns {Number}
 */
function subtractNum(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split('.')[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split('.')[1].length;
	} catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m - arg2 * m) / m;
}
/**
 * 用于checkbox的多选和取消
 * 
 * @param check
 * @param name
 */
function checkboxchecked(check, name) {
	var table = check.parentNode.parentNode.parentNode.parentNode;
	var inputs = table.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox" && inputs[i].name == name) {
			inputs[i].checked = check.checked;
		}
	}
}

/**
 * 用于检查选中和取消全部阻止推广信息中的复选框
 * 
 * @param check
 *            点击的对象
 * @param id
 *            全选选项的id
 */
function checkboxAllchecked(check, id) {
	var $td = $(check).parent();
	if ($(check).attr("checked") == "checked") {
		var $checkboxs = $td.find("input[type='checkbox']");
		var isAllSelect = true;
		$checkboxs.each(function() {
			if ($(this).val() != -1) {
				if ($(this).attr("checked") == undefined) {
					isAllSelect = false;
				}
			}
		});
		if (isAllSelect) {
			$("#" + id).attr("checked", 'true');
		}
	} else {
		$("#" + id).removeAttr("checked");
	}
}

/**
 * jqGrid表格的表格全选事件
 * 
 * @param checkbox
 *            全选复选框
 * @param tableId
 *            需要全选的表格的id
 * @param subCheckboxId
 *            表格中复选框的id
 */
function allSelectRowOfJqgrid(checkbox, tableId, subCheckboxId) {
	if ($(checkbox).is(":checked")) {
		// 点击前未选中
		$(checkbox).attr("checked", "checked");
		$('#' + tableId).find('tbody tr').each(function(index, tr) {
			$(tr).find('#' + subCheckboxId).attr("checked", "checked");
		});
	} else {
		// 点击前已选中
		$(checkbox).removeAttr("checked");
		$('#' + tableId).find('tbody tr').each(function(index, tr) {
			$(tr).find('#' + subCheckboxId).removeAttr("checked");
		});
	}
}
