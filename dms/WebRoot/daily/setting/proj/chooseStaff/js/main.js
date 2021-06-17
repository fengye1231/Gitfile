$(function(){
	
	var table = DMS.table("#table").set({
		//列定义，注意field要和服务器返回的field相对应
		columns: [{
			field: 'choose',
			checkbox: true,
		}, {
			field:'empId',
			visible:false,
		}, {
			field: 'username',
			title: '用户名'
		}, {
			field: 'empName',
			title: '姓名'
		},{
			field: 'sex',
			title: '性别'
		},{
			field: 'remark',
			title: '其他信息'
		}],
	}).show();	//初始时显示
	var stable = DMS.table("#stable").set({
		//列定义，注意field要和服务器返回的field相对应
		columns: [{
			field: 'choose',
			radio: true,
		}, {
			field:'empId',
			visible:false,
		}, {
			field: 'username',
			title: '用户名'
		}, {
			field: 'empName',
			title: '姓名'
		},{
			field: 'sex',
			title: '性别'
		},{
			field: 'remark',
			title: '其他信息'
		}],
	}).show();	//初始时显示
	var $form = $("#form-query");
	var queryURL = DMS.basePath + "daily/setting/proj/ProjectServlet?service=getStaff";
	var $setBtn = $("#set-btn");
	var prid = $("#prid").val();
	$form.validator().on("submit.query", function(e) {
		if(prid!=12){
			stable.setDataSource(queryURL, $form).loadData();
		}
		if(prid==12){
			table.setDataSource(queryURL, $form).loadData();	
		}
		return false;
	});
	
	$setBtn.on("click.set", updateStaff);
	
	function updateStaff() {
		var ids="";
		var users="";
		if(prid!=12){
			ids = $.map($("#stable").bootstrapTable('getSelections'), function (row) {
				return row.empId;
			});
			users = $.map($("#stable").bootstrapTable('getSelections'), function (row) {
				return row.username;
			});
		}else{
			ids = $.map($("#table").bootstrapTable('getSelections'), function (row) {
				return row.empId;
			});
			users = $.map($("#table").bootstrapTable('getSelections'), function (row) {
				return row.username;
			});
		}
		bootbox.alert("配置人员[" + users.join(",") + "]");
		var prjid = $("#prjid").val(); 
		var url = DMS.basePath + "daily/setting/proj/ProjectServlet?service=updateStaff&prjID="+prjid+"&prID="+prid;
		$.getJSON(url, {ids: ids.join(",")}, function(json, textStatus) {
				
		});
	}	
});