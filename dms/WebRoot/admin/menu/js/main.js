function deleteItem(name) {
	bootbox.confirm("是否删除?", function(result){
		if (result) {
			var url = DMS.basePath + "admin/menu/servlet/MenuServlet?service=delMenu";
			$.ajax({
				url: url,
				type: "post",
				dataType: 'json',
				data: {menuName: name}
			})
			.done(function(resp) {
				if (resp.sc == DMS.state.OK) {
					$("#table").bootstrapTable('refresh');
				} else {
					bootbox.alert("删除失败");
				}
			})
			.fail(function() {
				bootbox.alert("不能删除有子菜单的父菜单");
			});
		}
	});
}

$(function(){

	function operateFormatter(value, row, index) {
		var url = DMS.basePath + "admin/menu/servlet/MenuServlet?service=";
		return [
		    '<a class="opr-link" href="' + url + 'getMenu&menuName=' + row.menuName + '">',
		    '修改',
		    '</a> ',
			'<a class="opr-link" href="javascript:void(0)" onclick="deleteItem(\''+row.menuName+'\')">',
			'删除',
		].join('');
	}
	var table = DMS.table("#table").set({
		method: "post",
		columns: [{
			field: 'menuName',
			title: '菜单名'
		}, {
			field: 'menuCode',
			title: '菜单编号'
		}, {
			field: 'parentMenuId',
			title: '上级菜单'
		},{
			field: 'menuPath',
			title: '链接'
		},{
			field: 'operation',
			title: '操作',
			formatter: operateFormatter,
		}]
	}).show();

	var queryURL = DMS.basePath + "admin/menu/servlet/MenuServlet?service=queryMenu";
	var $form = $("#form-query");

	$form.validator().on("submit.query", function(e) {
		if (!e.isDefaultPrevented()) {
			table.setDataSource(queryURL, $form).loadData();
			return false;
		}
	});

});

  function validateName(menuName){
	  var xmlHttp;
	  if(menuName.length==0){
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
				 document.getElementById("name").innerHTML = "<font color='green'>菜单名可用</font>";
			 }else{
				 document.getElementById("name").innerHTML = "<font color='red'>菜单名重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("POST",DMS.basePath + "admin/menu/servlet/MenuServlet?service=checkMenuName&menuName="+menuName,true);
	  xmlHttp.send();
  }

   function validateCode(menuCode){
	  var xmlHttp;
	  
	  if(menuCode.length==0){
		  document.getElementById("code").innerHTML="";
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
				 document.getElementById("code").innerHTML = "<font color='green'>菜单编号可用</font>";
			 }else{
				 document.getElementById("code").innerHTML = "<font color='red'>菜单编号重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("POST",DMS.basePath + "admin/menu/servlet/MenuServlet?service=checkMenuCode&menuCode="+menuCode,true);
	  xmlHttp.send();
  }
    $.ajax({
			   type:"post",
			   url:DMS.basePath + "admin/menu/servlet/MenuServlet?service=getParent",
			   dataType:"json",
			   success:function(data){
    		var menuName = document.getElementById("menuName");
				   $.each(data.parentList,function(ket,val){
					   if((menuName.value!=(val.menuName))){
						   $("#sel").append('<option value="' + val.menuId + '">' + val.menuName + '</option>');
						   }
				   });
			   }
		   });
    
   function goBack(){
	javascript :history.back(-1);
};

   function getChange(){
	   var sel = document.getElementById("sel");
       var p = document.getElementById("parentMenuId");
       p.value = sel.options[sel.selectedIndex].value;
   }
