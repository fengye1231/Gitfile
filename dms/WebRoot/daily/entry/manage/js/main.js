$(function() {

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
			+ "daily/entry/manage/servlet.DailyServlet?service=Daily&s=listDaily";
	var $form = $("#form-query");
	var $delBtn = $("#del-btn");
	var $changeBtn = $("#change-btn");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});
	$delBtn.on("click.del", deleteItem);
	$changeBtn.on("click.change", changeItem);
	function dateFormatter(value, row, index) {
		if (value) {
			var dateStr = new Date(value.time).format("yyyy-MM-dd");
			return dateStr;
		}
		return "";
	}
	function detailFormatter(value, row, index) {
		return '<a data-toggle="modal" data-target="#myModal" '
				+ 'href="'+DMS.basePath+'daily/entry/manage/servlet.DailyServlet?service=Daily&s=detailDaily&DailyId='
				+ row.dailyId + '">' + value + '</a>';
	}
	function statusFormatter(value, row, index) {
		if(value=="已通过"){
			return '<div  class="btn status-btn btn-success disabled">' + value + '</div>';
		}else if(value=="未通过"){
			return '<div class="btn status-btn btn-danger disabled">' + value + '</div>';
		}else{
			return '<div class="btn status-btn btn-default disabled">' + value + '</div>';
		}
	}
	function deleteItem() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(
				row) {
			return row.dailyId;
		});
//		bootbox.alert("确认删除[" + ids.join(",") + "]？");
		var url = DMS.basePath
				+ "daily/entry/manage/servlet.DailyServlet?service=Daily&s=delDaily";
		$.getJSON(url, {
			ids : ids.join(",")
		}, function(json, textStatus) {
		});
	}
	function changeItem() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(
				row) {
			return row.dailyId;
		});
		if (ids.length === 0) {
			bootbox.alert("请选择一个日报");
		} else if (ids.length === 1) {
			var url = DMS.basePath
					+ "daily/entry/manage/servlet.DailyServlet?service=Daily&s=getDaily&ids="
					+ ids[0];
			window.location.href = url;
		} else {
			bootbox.alert("只能选择一个日报");
		}
	}

	/** **************************************************************** */

	$('#myModal').on('hide.bs.modal', function(e) {
		$(this).removeData();
	});

	$("#projId").on(
			"change.prp",
			function() {
				$("#prpId").empty();
				var prpList = projectPrp[$(this).val()];
				$("#prpId").append($("<option></option>").text("全部"));
				$.each(prpList, function(i, prp) {
					$("#prpId").append(
							$("<option></option>").val(prp.prpID).text(
									prp.prpName));
				});
			});

});