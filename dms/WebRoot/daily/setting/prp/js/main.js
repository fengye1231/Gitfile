$(function(){

	
	function operateFormatter(value, row, index) {
		var updateurl = DMS.basePath + "daily/setting/prp/prp_modify.jsp?prpId=";
		var url = DMS.basePath + "daily/setting/prp/servlet/PrpServlet?service=";
		
		return [
		    '<a class="opr-link" href="' + updateurl + row.prpId + '&prpName='+row.prpName+'&prpAbbr='+row.prpAbbr+'">',
		    '修改',
		    '</a> ',
		    '<a class="opr-link" href="#" onclick="javascript:bootbox.setLocale(\'zh_CN\');bootbox.confirm(\'是否删除\',' +
		    'function(result){if(result)window.location.href=\'/dms/daily/setting/prp/servlet/PrpServlet?service=del&prpName='+row.prpName+'\'})">',
			'删除',
		].join('');
	}
	
	
	var table = DMS.table("#table").set({
		//列定义，注意field要和服务器返回的field相对应
		columns: [{
			field: 'prpId',
			title: '序号'
		}, {
			field: 'prpAbbr',
			title: 'PRP缩写'
		},
		{
			field: 'prpName',
			title: 'PRP名称'
		},{
			field: 'operation',
			title: '操作',
			formatter: operateFormatter,
		}]
	}).show();	//初始时显示
	
	var queryURL = DMS.basePath + "daily/setting/prp/servlet/PrpServlet?service=queryPrp";
	table.setDataSource(queryURL).loadData();
	
	var $form = $("#form-query");
	var $delBtn = $("#del-btn");
	
	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			var prpAbbr = $("#prpAbbr").val();
			var prpName = $("#prpName").val();
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});
	
	
});