function deleteItem(name) {
	bootbox.confirm("是否删除?", function(result){
		if (result) {
			var url = DMS.basePath + "admin/permission/servlet/PermissionServlet?service=delPermission";
			$.ajax({
				url: url,
				type: "post",
				dataType: 'json',
				data: {perName: name}
			})
			.done(function(resp) {
				if (resp.sc == DMS.state.OK) {
					$("#table").bootstrapTable('refresh');
				} else {
					bootbox.alert("删除失败");
				}
			})
			.fail(function() {
				bootbox.alert("不能删除有子权限的父权限");
			});
		}
	});
}

$(function(){

	function operateFormatter(value, row, index) {
		var url = DMS.basePath + "admin/permission/servlet/PermissionServlet?service=";
		if (row.perPath == "*") {
			return "此权限不可修改";
		} else {
			return [
			    '<a class="opr-link" href="' + url + 'getPermission&perName=' + row.perName + '">',
			    '修改',
			    '</a> ',
				'<a class="opr-link" href="javascript:void(0)" onclick="deleteItem(\''+row.perName+'\')">',
				'删除',
			].join('');			
		}
	}
	
	var table = DMS.table("#table").set({
		method: "post",
		columns: [{
			field: 'perName',
			title: '权限名称'
		}, {
			field: 'perPath',
			title: '权限路径'
		}, {
			field: 'leaderPermissionId',
			title: '上级权限'
		},{
			field: 'operation',
			title: '操作',
			formatter: operateFormatter,
		}]
	}).show();

	var queryURL = DMS.basePath + "admin/permission/servlet/PermissionServlet?service=queryPermission";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

 $.ajax({
 	   type:"post",
 	   url:DMS.basePath + "admin/permission/servlet/PermissionServlet?service=getLeader",
 	   dataType:"json",
 	   success:function(data){
 	var perName = document.getElementById("perName");
 		   $.each(data.perList,function(ket,val){
 			   if((perName.value!=(val.perName))){
 				   $("#sel").append('<option value="' + val.perId + '" ' + 
 				   			(parentId == val.perId? 'selected="selected"': '') + '>' + 
 				   			val.perName + '</option>');
 			  }
 		   });
 	   }
 });

});




function validateName(perName){
	  var xmlHttp;
	  if(perName.length==0){
		  document.getElementById("name").innerHTML="";
		  return;
	  }
	  if(window.XMLHttpRequest){
		  xmlHttp = new XMLHttpRequest;
		  
	  }else{
		  xmlHttp = ActiveXObject("Microsoft.XMLHTTP");
	  }
	  
	  xmlHttp.onreadystatechange=function(){
		  if(xmlHttp.readyState==4&&xmlHttp.status==200){
			 var bool = xmlHttp.responseText;
			 if(bool=="true"){
				 document.getElementById("name").innerHTML = "<font color='green'>权限名可用</font>";
			 }else{
				 document.getElementById("name").innerHTML = "<font color='red'>权限名重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("POST",DMS.basePath + "admin/permission/servlet/PermissionServlet?service=checkPerName&perName="+perName,true);
	  xmlHttp.send();
	  
}


function goBack(){
	javascript :history.back(-1);
};

function getChange(){
	var sel = document.getElementById("sel");
    var p = document.getElementById("leaderPermissionId");
    p.value = sel.options[sel.selectedIndex].value;

}

function validateName(prpName){
	  var xmlHttp;
	  if(prpName.length==0){
		  document.getElementById("name").innerHTML="";
		  return;
	  }
	  if(window.XMLHttpRequest){
		  xmlHttp = new XMLHttpRequest;
		  
	  }else{
		  xmlHttp = ActiveXObject("Microsoft.XMLHTTP");
	  }
	  
	  xmlHttp.onreadystatechange=function(){
		  if(xmlHttp.readyState==4&&xmlHttp.status==200){
			 var bool = xmlHttp.responseText;
			 if(bool=="true"){
				 document.getElementById("name").innerHTML = "<font color='green'>权限名可用</font>";
			 }else{
				 document.getElementById("name").innerHTML = "<font color='red'>权限名重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("POST",DMS.basePath + "admin/permission/servlet/PermissionServlet?service=checkprpName&prpName="+prpName,true);
	  xmlHttp.send();
	  
}
