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
			 if(bool == "true"){
			 }else{
				 document.getElementById("deptCodeEerors").innerHTML = "<font color='#a94442'>编号重复</font>";
			 }
		  }
	  }
	  xmlHttp.open("POST","/dms/daily/setting/dept/DeptServlet?deptAction=validateCode&deptCode="+deptCode,true);
	  xmlHttp.send();
}