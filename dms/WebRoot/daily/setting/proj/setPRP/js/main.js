function goBack(){
	history.back(-1);
};

$(function(){
	var table = DMS.table("#table").set({
		//列定义，注意field要和服务器返回的field相对应
		columns: [{
			field: 'choose',
			checkbox: true
		}, {
			field:'prpID',
			visible:false
		},{
			field: 'prpAbbr',
			title: 'PRP缩写'
		}, {
			field: 'prpName',
			title: '阶段名称'
		},{
			field: 'remark',
			title: '备注'
		}],
	}).show();	//初始时显示
	
	var queryURL = DMS.basePath + "daily/setting/proj/ProjectServlet?service=getAllPrp";
	table.setDataSource(queryURL).loadData();
	
	var $table = $("#table");
	$table.on("load-success.bs.table.export", function(e, data) {
		$table.bootstrapTable('checkBy', {field:'prpID', values:prps});
	});
	
	
	var $setBtn = $("#set-btn");
	$setBtn.on("click.set", updatePRP);
	
	function updatePRP() {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function (row) {
			return row.prpID;
		});
		var abbrs = $.map($("#table").bootstrapTable('getSelections'), function (row) {
			return row.prpAbbr;
		});
		bootbox.alert("配置PRP[" + abbrs.join(",") + "]");
		var prjid = $("#prjid").val(); 
		var url = DMS.basePath + "daily/setting/proj/ProjectServlet?service=updatePRP&prjID="+prjid;
		$.getJSON(url, {ids: ids.join(",")}, function(json, textStatus) {
				
		});
	}

});

