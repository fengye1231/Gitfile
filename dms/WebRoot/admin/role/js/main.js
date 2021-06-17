
//$(function(){
//	
//getTree();
//function getTree(){
//    $.ajax({
//      type: "post", //请求方式
//      url: "/dms/servlet/RoleServlet?service=ajaxData",//请求路径
//      cache: false,
//      //data: {tree:"jsonString"}, //传参
//      dataType: "json",//返回值类型
//      success:function(data){
//    	  $('#treeview').treeview({
//    			bootstrap2: true, 
//    			showTags: true,
//    			enableLinks: true,
//    			data: data
//    		});
//      },
//      error:function(){
//    	  bootbox.alert("获取数据时发生错误");
//      }
//    });
//}
//
//
//});
function chk(){
	bootbox.confirm("是否确定提交？", function(result){
		if(result){
			var check=document.getElementsByName("perm");
			var id=[];
			for(i=0;i<check.length;i++){
				if(check[i].checked)
					{
					  id.push(check[i].id);
					}
			}
		    var p=document.getElementById("arrp");
		    var str=id.toString();
		    p.setAttribute("value",str);
		    $("form").submit();
	    }
	});
    return false;
}

function hascheck(){
	 var a =document.getElementById("check").value;
	 var reg=new RegExp(" ","g");
	 a = a.replace("[","").replace("]","").replace(reg,"").split(",");
	 for(i=0;i<a.length;i++){
		 if (a[i].length > 0) {
			 document.getElementById(a[i]).checked=true;
		 }
	 }
}


//
//function isNull( ){
//var str=document.getElementById("name");
//if ( str.value.length == 0) return false; 
//else return true;
//} 
//
//function isDelete(){
//	var r=confirm("是否删除");
//    
//}

$.ajax({
			   type:"post",
			   url:"/dms/admin/role/servlet/RoleServlet?service=listRole",
			   dataType:"json",
			   success:function(data){
				   $.each(data.roleList,function(ket,val){
//					   if(val.roleCode){
//						   $("#sel").append('<option value="' + val.menuId + '">' + val.menuName + '</option>');
//					   }else{
//						   var a = "请选择";
//						   $("#sel").append('<option value="' + val.menuId + '">' + a + '</option>');
//					   }
					   if(val.roleName=="管理员"){
						   $("#rl").append(
									"<tr><td>"+val.roleCode+"</td><td>"+val.roleName+"</td><td colspan=\'3\'>无法修改管理员角色</td></tr>"
								   );
					   }else{
					   $("#rl").append(
						"<tr><td>"+val.roleCode+"</td><td>"+val.roleName+"</td><td><a href='/dms/admin/role/role_modify.jsp?roleCode="+val.roleCode
						+"&roleName="+val.roleName+"'>修改角色</a></td><td><a href='/dms/admin/role/servlet/RoleServlet?roleName="+val.roleName+"&service=role_perm'" +
						">修改权限</a></td><td><a href='/dms/admin/role/servlet/RoleServlet?roleName="+val.roleName+"&service=del'>删除角色</a></td></tr>"
					   )
					   }
				   });
			   }
		   });
