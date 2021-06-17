function operateFormatter(value, row, index) {
	var url = DMS.basePath + "info/employee/employee.servlet?service=";
	return [
	    '<a class="opr-link" href="' + url + 'modifyEmployee&username=' + row.username + '">',
	    ' 修改信息    ',
	    '</a> ',
	    
	    
	    '<a class="opr-link" href="' + url + 'lookEmployee&username=' + row.username + '">',
	    '    查询个人信息',
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
			field: 'username',
			title: '员工编号'
		}, {
			field: 'empName',
			title: '员工姓名'
		},{
			field: 'deptName',
			title: '部门'
		},{
			field: 'sex',
			title: '性别'
		},{
			field: 'joinDate',
			title: '加入时间',	
			formatter: dateFormatter
		},{
			field: 'operate',
			title: '操作',
			formatter: operateFormatter  	
		}],
	}).show();	//初始时显示

	var queryURL = DMS.basePath + "info/employee/employee.servlet?service=searchEmp";
	var $form = $("#form-query");
	var $delBtn = $("#del-btn");
	
	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			var uName = $("#username").val();
			var eName = $("#empName").val();
			var dName = $("#deptId").val();
			var Date1 = $("#joinDate1").val();
			var Date2 = $("#joinDate2").val();
			if(eName || uName || dName || Date1 || Date2){
				table.setDataSource(queryURL, $form).loadData();		
			} else {
				bootbox.alert("请输入至少一个查询条件");
			}
			return false;
		}
	});
	
	$delBtn.on("click.del", deleteItem);
	
	function deleteItem() {
		var username = $.map($("#table").bootstrapTable('getSelections'), function (row) {
			return row.username;
		});
		bootbox.confirm("确认删除[" + username.join(",") + "]？", function(result){
			if (result) {
				var url = DMS.basePath + "info/employee/employee.servlet?service=deleteEmployee";
				$.ajax({
					url: url,
					type: 'get',
					dataType: 'json',
					data: {username: username.join(",")},
				})
				.done(function(res) {
					if (res.sc === DMS.state.OK) {
						bootbox.alert("删除成功");
						$("#table").bootstrapTable('refresh');
					} else {
						bootbox.alert("删除失败，请确认该员工是否有下属员工或者加入了项目。");	
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



