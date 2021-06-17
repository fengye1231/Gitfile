

var detailUrl = "result.jsp";
var empId = "";



function dateFormatter(value, row, index) {
/*	return '<a data-toggle="modal" data-target=".bs-example-modal-lg">' + 
	(new Date(value.time).format("yyyy-MM-dd")) + '</a>';*/
	return '<a data-trigger="modal" href="'+
	DMS.basePath + 'daily/query/personal/servlet/PersonalServlet?service=dailyReport&empId='+ empId +'&date=' + value.time + '" data-title="日报记录">' + 
	(new Date(value.time).format("yyyy-MM-dd")) + '</a>';
}

$(function(){
	
	empId = $("#emp-sel").val();
	$("#emp-sel").on("change", function(){
		empId = $(this).val();
	})

	var table = DMS.table("#table").set({
		columns: [{
			field: 'submitDate',
			title: '日期',
			formatter: dateFormatter
		}, {
			field: 'load',
			title: '工作量'
		}, {
			field: 'extraLoad',
			title: '加班工作量'
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/personal/servlet/PersonalServlet?service=submitDate";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

});
