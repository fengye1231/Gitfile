<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/project2/css/bootstrap.min.css"/>
   <link rel="stylesheet" href="/project2/css/bootstrap-theme.min.css"/>
   <script src="/project2/js/jQuery-2.2.2-min.js"></script>
   <script src="/project2/js/bootstrap.min.js"></script>
   <script src="/project2/js/bootstrap-paginator.min.js"></script>


   <style>
       #main{
           width: 100%;
           height: 530px;
           border:1px solid black;
       }
       .page-header{
           text-align: center;
           margin: 0px;
       }
       h1{
           font-weight: 700;
           font-family: STKaiti;
       }

       .table-1{
           width: 80%;
           margin: 0 auto;
       }

       table thead, tbody tr {
           display:table;
           width:100%;
           table-layout:fixed;         
       }
       table tbody{
           display: block;
           height:190px;
           overflow-y:scroll;
           text-align: left;
       }
       .table-bordered{
           border:2px solid gray;
       }
       table thead {
           width: calc( 100% - 1.1em );
       }
       .p1{
           width: 20%;
       }
       .p2{
           width: 20%;
       }
       .p3{
           width: 15%;
       }
       .p4{
           width: 15%;
       }
       .p5{
           width: 30%;
       }
       button{
           width: 150px;
           margin-left:10%;
       }
       form{
           text-align: center;
       }
       .form-horizontal .control-label {
           text-align: left;
       }
       .button-return{
           width: 150px;
           height: 46px;
           margin: 0 auto;
       }
       th{
       		text-align: center; 
       }
   </style>
</head>
<body>
	<c:if test="${list == null}">
		<c:redirect url="/eventNeedExpertShow">
		</c:redirect>
	</c:if>
	<div id="main">
       <div class="page-header">
           <h1>事件记录</h1>
       </div>
       <div class="table-1">
           <table class="table table-bordered table-striped table-hover table-condensed">
               <thead>
               <tr>
                   <th class="p1">事件名称</th>
                   <th class="p2">日期</th>
                   <th class="p3">发生位置</th>
                   <th class="p4">防治方案</th>
                   <th class="p5">灾情状态</th>
               </tr>
               </thead>
               <tbody id="tbody1">
               		<c:forEach items="${list}" var="item">
                <tr onclick="add(this,${item.id})">
                	<td class="p1">${item.name}</td>
	                <td class="p2">${item.date}</td>
	                <td class="p3">${item.area}</td>
	                <td class="p4">${item.plan}</td>
	                <td class="p5">${item.phase}</td>
                </tr>
                	 </c:forEach>
               </tbody>
           </table>
       </div>
       <br/>
       <br/>
       <div class="button-return">
           <button type="button" class="btn btn-primary btn-lg">专家会商</button>
       </div>
	</div>
</body>
<script type="text/javascript">

	//bootstrap分页组件
	var sel = null; 
	var thisId = 0;
	function add(tr,id){
		var sel1 = $(tr);
		sel1.css("background-color","lightGreen");
		if(sel != null){
            sel.css("background-color","white");
        }
		sel = sel1;
		thisId = id;
	}
	
	//会商点击事件，传事件id值给servlet
    $("button").click(function(){
    	if(thisId == 0){
    		alert("请先选择需要专家会商的事件！");
    	}else{
    		window.location='/project2/consultationShow?id=' + thisId;
    	}       
    })
    
    
</script >
</html>