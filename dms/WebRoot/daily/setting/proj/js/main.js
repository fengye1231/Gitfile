function operateFormatter(value, row, index) {
	var url = DMS.basePath + "daily/setting/proj/ProjectServlet?service=";
	return [
	    '<a class="opr-link" href="' + url + 'modifyProject&prjID=' + row.prjID + '">',
	    '修改项目',
	    '</a> ',
	    '<a class="opr-link" href="' + url + 'setPRP&prjID=' + row.prjID + '">',
	    '配置PRP阶段',
	    '</a> ',
		'<a class="opr-link" href="' + url + 'setDept&prjID=' + row.prjID + '">',
		'配置部门',
		'</a> ',
		'<a class="opr-link" href="' + url + 'setStaff&prjID=' + row.prjID + '">',
		'配置人员',
		'</a> ',
	].join('');
}

function dateFormatter(value, row, index) {
	return new Date(value.time).format("yyyy-MM-dd");
}

$(function(){

	var table = DMS.table("#table").set({
		//列定义，注意field要和服务器返回的field相对应
		method: "post",
		columns: [{
			field: 'choose',
			checkbox: true
		}, {
			field:'prjID',
			visible:false
		}, {
			field: 'prjCode',
			title: '项目编码'
		}, {
			field: 'prjName',
			title: '项目名称'
		},{
			field: 'startDate',
			title: '开始时间',
			formatter: dateFormatter
		},{
			field: 'endDate',
			title: '结束时间',
			formatter: dateFormatter
		},{
			field: 'status',
			title: '项目状态'
		},{
			field: 'operate',
			title: '操作',
			formatter: operateFormatter
		}]
	}).show();	//初始时显示

	var queryURL = "/dms/daily/setting/proj/ProjectServlet?service=searchPrj";
	var $form = $("#form-query");
	var $delBtn = $("#del-btn");
	
	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			var pID = $("#projectCode").val();
			var pName = $("#projectName").val();
			var sDate = $("#start-date").val();
			var eDate = $("#end-date").val();
			if(pID || pName || sDate || eDate){
				table.setDataSource(queryURL, $form).loadData();		
			} else {
				bootbox.alert("请输入至少一个查询条件");
			}
			return false;
		}
	});
	
	$delBtn.on("click.del", deleteItem);
	
	function deleteItem() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function (row) {
			return row.prjID;
		});
		var names = $.map($("#table").bootstrapTable('getSelections'), function (row) {
			return row.prjName;
		});
		bootbox.confirm("确认删除[" + names.join(",") + "]？", function(result){
			if (result) {
				var url = DMS.basePath + "daily/setting/proj/ProjectServlet?service=deletePrj";
				$.ajax({
					url: url,
					type: 'get',
					dataType: 'json',
					data: {ids: ids.join(",")},
				})
				.done(function(res) {
					if (res.sc === DMS.state.OK) {
						bootbox.alert("删除成功");
						$("#table").bootstrapTable('refresh');
					} else {
						bootbox.alert("删除失败，请确认要删除的项目是否配置");	
					}
				})
				.fail(function() {
					bootbox.alert("与服务器的通信失败");
				})
				.always(function() {
					$warnBtn.removeAttr("disabled");
				});
			}
		});
	}
	
});
