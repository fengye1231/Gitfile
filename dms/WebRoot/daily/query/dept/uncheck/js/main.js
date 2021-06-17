function dateFormatter(value, row, index) {
	return new Date(value.time).format("yyyy-MM-dd");
}

$(function(){

	var table = DMS.table("#table").set({
		columns: [{
			field: 'departmentName',
			title: '部门'
		}, {
			field: 'apprName',
			title: '审核人'
		}, {
			field: 'apprEmail',
			title: '电子邮件'
		}, {
			field: 'employeeName',
			title: '被审核人'
		}, {
			field: 'date',
			title: '日期',
			formatter: dateFormatter
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/dept/DeptLoadServlet?service=uncheck";
	var $form = $("#form-query");
	var $warnBtn = $("#warn-btn");
	
	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});
	
	$form.validator().on("invalid.bs.validator.warning", function(e) {
		$warnBtn.attr("disabled", "disabled");
	});
	$("#table").on("load-success.bs.table.warning", function(e, data) {
		if ($form.find(".has-error").length == 0 && data.rows.length > 0) {
			$warnBtn.removeAttr("disabled");
		} else {
			$warnBtn.attr("disabled", "disabled");
		}
	});
	
	$warnBtn.on("click.warn", function() {
		var url = DMS.basePath + "daily/query/dept/DeptLoadServlet?service=warnUncheck";
		$warnBtn.attr("disabled", "disabled");
		$.ajax({
			url: url,
			type: 'get',
			dataType: 'json',
			data: DMS.queryStringToObj($form.serialize()),
		})
		.done(function(res) {
			bootbox.alert(res.sc === DMS.state.OK? "提醒成功": "提醒失败");
		})
		.fail(function() {
			bootbox.alert("与服务器的通信失败");
		})
		.always(function() {
			$warnBtn.removeAttr("disabled");
		});
	});
	
	$form.validator("validate");
});