$(function(){

	var table = DMS.table("#table").show();

	var queryURL = DMS.basePath + "daily/query/dept/DeptLoadServlet?service=unsubmit";
	var $form = $("#form-query");
	var $warnBtn = $("#warn-btn");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});
	
	$form.validator().on("invalid.bs.validator.warn", function(e) {
		$warnBtn.attr("disabled", "disabled");
	});
	$("#table").on("load-success.bs.table", function(e, data) {
		if ($form.find(".has-error").length == 0 && data.rows.length > 0) {
			$warnBtn.removeAttr("disabled");
		} else {
			$warnBtn.attr("disabled", "disabled");
		}
		$("#table").find("tr > td:not(:first-child)").each(function() {
			var $that = $(this);
			if ($that.text().indexOf("/") !== -1) {
				$that.addClass("cell-red");
			} else if ($that.text() != "") {
				$that.addClass("cell-green");
			} else {
				$that.addClass("cell-gray");
			}
		});
	});
	$("#table").on("load-error.bs.table.warn", function() {
		$warnBtn.attr("disabled", "disabled");
	});
	
	$warnBtn.on("click.warn", function() {
		var url = DMS.basePath + "daily/query/dept/DeptLoadServlet?service=warnUnsubmit";
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