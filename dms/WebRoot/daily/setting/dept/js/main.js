$(function(){

	getTree();
	function getTree(){
		$.ajax({
			type: "post", //请求方式
	      	url: "/dms/daily/setting/dept/DeptServlet?deptAction=ajaxTree",//请求路径
	      	cache: false,
	      	//data: {tree:"jsonString"}, //传参
	      	dataType: "json",//返回值类型
	      	success:function(data){
	    	  	$('#treeview').treeview({
	    			bootstrap2: true, 
	    			showTags: true,
	    			enableLinks: true,
	    			levels:10,
	    			//checkedIcon: 'glyphicon glyphicon-check',
	    			//showCheckbox:true,
	    			data: data
	    		});
	      	},
	      	error:function(){
	    	  bootbox.alert("getTree()获取数据时发生错误");
	      	}
	    });
	}
	
	function operateFormatter(value, row, index) {
		var url = DMS.basePath + "daily/setting/dept/DeptServlet?deptAction=";
		var modifyUrl = DMS.basePath + "daily/setting/dept/modifyDept?deptId=";
		return [
		    '<a class="opr-link" href="' + modifyUrl + row.deptId + '">',
		    '修改',
		    '</a> ',
//		    '<a href="modifyDept?deptId='+ row.deptId +'">',
//				'<button type="button" id="modify-btn" class="btn btn-primary btn-block">',
//		    		'修改',
//		    	'</button>',
//			'</a>',
//			'<a class="opr-link" id="del-link" href="' + url + 'deleteChild&deptId=' + row.deptId + '">',
//			'删除',
//			'<a class="opr-link" href="#" onclick="javascript:bootbox.setLocale(\'zh_CN\');bootbox.confirm(\'是否删除\',' +
//		    'function(result){if(result)window.location.href=\'/dms/daily/setting/dept/DeptServlet?deptAction=deleteChild&deptId='+row.deptId+'\'})">',
//			'删除',
			//'<button type="button" id="del" class="btn btn-primary btn-block">删除</button>'
			
			'<a class="opr-link" id="del-link" href="#" onclick="deleteItem(' + row.deptId + ')">',
			'删除',
		].join('');
	}
	
	
	function detailFormatter(value, row, index) {
		var url = DMS.basePath + "daily/setting/dept/?deptId=";
		return '<a href="' + url + row.deptId + '">' + value + '</a>';
	}
	var table = DMS.table("#table").set({
		//列定义，注意field要和服务器返回的field相对应
		columns: [{
			field: 'deptCode',
			title: '子部门编号'
		}, {
			field: 'deptName',
			title: '子部门名称',
			formatter: detailFormatter
		},{
			field: 'operation',
			title: '操作',
			formatter: operateFormatter,
		}]
	}).show();	//初始时显示
	var queryURL = DMS.basePath + "daily/setting/dept/DeptServlet?deptAction=ajaxChildListPage";
	table.setDataSource(queryURL).loadData();
	
	
	
//	var $delBtn = $("#del-link");
//	$delBtn.on("click", deleteChild);
//	function deleteChild() {
////		var ids = $.map($("#table").bootstrapTable('getSelections'), function (row) {
////			return row.deptId;
////		});
////		var names = $.map($("#table").bootstrapTable('getSelections'), function (row) {
////			return row.deptName;
////		});
//		
//		bootbox.confirm("确认删除" + row.deptName + "？", function(result){
//			if (result) {
//				var url = DMS.basePath + "daily/setting/dept/DeptServlet?deptAction=deleteChild";
//				$.ajax({
//					url: url,
//					type: 'get',
//					dataType: 'json',
//					data: {ids:row.deptId },
//				})
//				.done(function(res) {
//					if (res.sc === DMS.state.OK) {
//						bootbox.alert("删除成功");
//						$("#table").bootstrapTable('refresh');
//					} else {
//						bootbox.alert("删除失败");	
//					}
//				})
//				.fail(function() {
//					bootbox.alert("与服务器的通信失败");
//				})
//				.always(function() {
//					$warnBtn.removeAttr("disabled");
//				});
//			}
//		});
//	}
	
	
});


	function deleteItem(deptId) {
		bootbox.confirm("确认删除？", function(result){
			if (result) {
				var url = DMS.basePath + "daily/setting/dept/DeptServlet?deptAction=deleteChild&deptId="+deptId;
				$.ajax({
					url: url,
					type: 'post',
					cache: false,
					dataType: 'json',
					success:function(data){
						if(data.sc == DMS.state.OK){
							bootbox.alert("删除成功!");
							location.href="http://localhost:8080/dms/daily/setting/dept/";
							//location.reload() 
						} else {
							bootbox.alert("删除失败，请联系管理员!");
//							if (result) {
//								location.reload() 
//							}
						}
				    },
				    error:function(){
				    	bootbox.alert("发生错误，请联系管理员!");
				    }
				})
			}
		});
	}

function validateName(deptName){
	  var xmlHttp;
	  if(deptName.length == 0){
		  return;
	  }
	  if(window.XMLHttpRequest){
		  xmlHttp = new XMLHttpRequest;
		  
	  }else{
		  xmlHttp = ActiveXObject("Microsoft.XMLHTTP");
	  }
	  
	  xmlHttp.onreadystatechange=function(){
		  if(xmlHttp.readyState==4 && xmlHttp.status==200){
			 var bool = xmlHttp.responseText;
			 if(bool == "true"){
				 document.getElementById("deptNameEerors").innerHTML = "";
			 }else{
				 document.getElementById("deptNameEerors").innerHTML = "<font color='#a94442'>名称重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("GET","/dms/daily/setting/dept/DeptServlet?deptAction=validateName&deptName="+deptName,true);
	  xmlHttp.send();
}

function validateCode(deptCode){
	 
	  var xmlHttp;
	  
	  if(deptCode.length == 0){
		  return;
	  }
	  if(window.XMLHttpRequest){
		  xmlHttp = new XMLHttpRequest;
		  
	  }else{
		  xmlHttp = ActiveXObject("Microsoft.XMLHTTP");
	  }
	  
	  
	  xmlHttp.onreadystatechange=function(){
		  if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			 var bool = xmlHttp.responseText;
			 console.log("bool:%s",bool);
			 if(bool == "true"){
				 document.getElementById("deptCodeEerors").innerHTML = "";
			 }else{
				 document.getElementById("deptCodeEerors").innerHTML = "<font color='#a94442'>编号重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("POST","/dms/daily/setting/dept/DeptServlet?deptAction=validateCode&deptCode="+deptCode,true);
	  xmlHttp.send();
}

function goBack(){
	javascript :history.back(-1);
};

//function doDeleteCheck(deptId){
//	javascript:bootbox.setLocale('zh_CN');
//	bootbox.confirm('是否删除',function(result){
//		if(result){
//			window.location.href='/dms/daily/setting/dept/DeptServlet?deptAction=deleteChild&deptId=' + deptId;
//			del = true;
//		}
//	});
//};


//function doValidateDel(){
//	
//	$.ajax({
//		type: "post", //请求方式
//		url: "/dms/daily/setting/dept/DeptServlet?deptAction=deleteChild",//请求路径
//		cache: false,
//	    dataType: "json",//返回值类型
//	    success:function(data){
//			if(data.sc == DMS.state.OK){
//				bootbox.alert("删除成功!");
//			} else {
//				bootbox.alert("删除失败，请联系管理员!");
//			}
//	    },
//	    error:function(){
//	    	bootbox.alert("发生错误，请联系管理员!");
//	    }
//	});
//};
