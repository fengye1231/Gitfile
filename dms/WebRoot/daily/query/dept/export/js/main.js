$(function(){

	var table = DMS.table("#table").set({
		columns: [{
			field: 'departmentName',
			title: '子部门'
		}, {
			field: 'dailyNum',
			title: '有效日报'
		}, {
			field: 'load',
			title: '有效工作时间'
		}, {
			field: 'extraLoad',
			title: '加班工作量'
		}, {
			field: 'unacceptedLoad',
			title: '未纳入项目工作量'
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/dept/DeptLoadServlet?service=deptLoad";
	var $form = $("#form-query");
	var $exportBtn = $("#export-btn");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});
	
	$form.validator().on("invalid.bs.validator.export", function(e) {
		$exportBtn.attr("disabled", "disabled");
	});
	$("#table").on("load-success.bs.table.export", function(e, data) {
		if ($form.find(".has-error").length == 0 && data.rows.length > 0) {
			$exportBtn.removeAttr("disabled");			
		} else {
			$exportBtn.attr("disabled", "disabled");
		}
	});
	$("#table").on("load-error.bs.table.export", function() {
		$exportBtn.attr("disabled", "disabled");
	});
	
	$exportBtn.on("click.export", function() {
		var url = DMS.basePath + "DeptLoadServlet?service=exportDeptLoad";
		url += "&" + $form.serialize();
		window.open(url);
	});
	
	$form.validator("validate");

});