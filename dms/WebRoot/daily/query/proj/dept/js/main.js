$(function(){

	var table = DMS.table("#table").set({
		method: "post",
		columns: [{
			field: 'departmentName',
			title: '部门'
		}, {
			field: 'load',
			title: '工作量'
		}, {
			field: 'extraLoad',
			title: '加班工作量'
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/proj/ProjectLoadServlet?service=dept";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

});
