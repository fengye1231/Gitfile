$(function(){

	var table = DMS.table("#table").set({
		columns: [{
			field: 'projectName',
			title: '项目'
		}, {
			field: 'load',
			title: '工作量'
		}, {
			field: 'extraLoad',
			title: '加班工作量'
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/personal/servlet/PersonalServlet?service=prj";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

});
