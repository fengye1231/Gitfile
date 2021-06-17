$(function(){

	var table = DMS.table("#table").show();

	var queryURL = DMS.basePath + "daily/query/dept/DeptLoadServlet?service=summary";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

});