$(function() {
	window.onload = function() {
		var url = DMS.basePath
		var id = 3;
		+ "servlet.DailyServlet?service=Check&s=showDaily&DailyId="
		+ id;
		table.setDataSource(url, $form).loadData();
	}

	var table = DMS.table("#table").set({
		method : "post",
		columns : [ {
			field : 'choose',
			checkbox : true
		}, {
			field : 'submitDate',
			title : '日期',
			formatter : dateFormatter
		}, {
			field : 'prjName',
			title : '项目'
		}, {
			field : 'prpName',
			title : 'PRP阶段'
		}, {
			field : 'desc',
			title : '任务',
			formatter : detailFormatter
		}, {
			field : 'totalWorkload',
			title : '工作量'
		}, {
			field : 'overTimeLoad',
			title : '加班'
		}, {
			field : 'status',
			title : '状态',
			formatter : statusFormatter
		} ]
	}).show();

	var queryURL = DMS.basePath
			+ "daily/entry/check/servlet.DailyServlet?service=Check&s=listCheckDaily";
	var $form = $("#form-query");
	var $failBtn = $("#fail-btn");
	var $passBtn = $("#pass-btn");
	var $reasonBtn = $("#reason-btn");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});
	$failBtn.on("click.fail", failItem);
	$passBtn.on("click.pass", passItem);
	$reasonBtn.on("click.reason", reasonItem);
	function dateFormatter(value, row, index) {
		if (value) {
			var dateStr = new Date(value.time).format("yyyy-MM-dd");
			return dateStr;
		}
		return "";
	}
	function dateFormatter(value, row, index) {
		if (value) {
			var dateStr = new Date(value.time).format("yyyy-MM-dd");
			return dateStr;
		}
		return "";
	}
	function detailFormatter(value, row, index) {
		return '<a data-toggle="modal" data-target="#myModal" '
				+ 'href="'+DMS.basePath+'daily/entry/check/servlet.DailyServlet?service=Check&s=detailCheckDaily&DailyId='
				+ row.dailyId + '">' + value + '</a>';
	}
	function statusFormatter(value, row, index) {
		if (value == "已通过") {
			return '<div  class="btn status-btn btn-success disabled">' + value
					+ '</div>';
		} else if (value == "未通过") {
			return '<div class="btn status-btn btn-danger disabled">' + value
					+ '</div>';
		} else {
			return '<div class="btn status-btn btn-default disabled">' + value
					+ '</div>';
		}
	}
	function failItem() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(
				row) {
			return row.dailyId;
		});
//		bootbox.alert("确认不通过[" + ids.join(",") + "]？");
		var url = DMS.basePath
				+ "daily/entry/check/servlet.DailyServlet?service=Check&s=failDaily";
		$.getJSON(url, {
			ids : ids.join(",")
		}, function(json, textStatus) {
		});
	}
	function passItem() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(
				row) {
			return row.dailyId;
		});
//		bootbox.alert("确认通过[" + ids.join(",") + "]？");
		var url = DMS.basePath
				+ "daily/entry/check/servlet.DailyServlet?service=Check&s=passDaily";
		$.getJSON(url, {
			ids : ids.join(",")
		}, function(json, textStatus) {
		});
	}
	function reasonItem() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(
				row) {
			return row.dailyId;
		});
		if (ids.length === 0) {
			bootbox.alert("请选择一个日报");
		} else if (ids.length === 1) {
			var url = DMS.basePath
					+ "daily/entry/check/servlet.DailyServlet?service=Check&s=showDaily&DailyId="
					+ ids[0];
			window.location.href = url;
		} else {
			bootbox.alert("只能选择一个日报");
		}
	}
	//	
	$('#myModal').on('hide.bs.modal', function(e) {
		$(this).removeData();
	});
});