function dateFormatter(value, row, index) {
	return new Date(value.time).format("yyyy-MM-dd");
}


$(function(){

	var table = DMS.table("#table").set({
		columns: [{
			field: 'submitDate',
			title: '日期',
			formatter: dateFormatter
		}, {
			field: 'totalWorkload',
			title: '工作量'
		}, {
			field: 'overTimeLoad',
			title: '加班工作量'
		},{
			field: 'prjName',
			title: '项目'
		},{
			field: 'prpName',
			title: '阶段'
		},{
			field: 'desc',
			title: '工作内容'
		}]
	}).show();

	var queryURL = DMS.basePath + "daily/query/personal/servlet/PersonalServlet?service=weekDaily";
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
		var url = DMS.basePath + "daily/qurey/personal/servlet/PersonalServlet?service=exportWeekDaily";
		url += "&" + $form.serialize();
		window.open(url);
	});
	
	$form.validator("validate");


});