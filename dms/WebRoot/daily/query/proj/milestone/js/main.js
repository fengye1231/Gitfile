$(function(){

	var table = DMS.table("#table").set({
		columns: [{
			field: 'prpName',
			title: '项目阶段'
		}, {
			field: 'load',
			title: '工作量'
		}, {
			field: 'extraLoad',
			title: '加班工作量'
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/proj/ProjectLoadServlet?service=prp";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

});
