<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css">

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script type="javascript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap-paginator.min.js"></script>
    <style>
        div{
            /*border: 1px solid;*/
        }
        tr{
            /*border: 1px solid;*/
            height: 60px;
        }
        td{
            width: 60px;
            line-height: 20px;
        }
        .span{
             font-size:18px;
             color:red;
             display:none;
        }
    </style>
</head>
<body>
<div class="container-fluid">
        <div class="col-sm-12">
            <span style="text-align: center;font-size: 30px" class="col-sm-12">修改小班信息</span>
            <form class="col-sm-12"  method="post"  action="project2/UpdateClass">
                 <input type="hidden"  id="input1" value="${classBean.id}">
                <table   cellspacing="0" class="col-sm-12">
                    <tr>
                        <th class="col-sm-2"></th>
                        <th class="col-sm-4"></th>
                        <th class="col-sm-2"></th>
                        <th class="col-sm-4"></th>
                    </tr>
                    <tr>
                        <td>名称</td>
                        <td><c:out value="${classBean.name}"></c:out></td>
                        <td>负责人</td>
                        <td><input type="text" name="duty" id="input2"><span class="span" id="spanDuty">请输入正确的格式</span></td>
                    </tr>
                    <tr>
                        <td>负责人电话</td>
                        <td><input type="text" name="phoneNum" id="input3"><span class="span" id="spanPhone">请输入正确手机格式</span></td>
                        <td>人员数量</td>
                        <td><c:out value="${classBean.num}"></c:out></td>
                    </tr>
                    <tr>
                        <td>负责区域</td>
                        <td><c:out value="${areaBean.name}"></c:out></td>
                        <td>林种</td>
                        <td><c:out value="${areaBean.great}"></c:out></td>
                    </tr>
                    <tr>
                        <td>地类</td>
                        <td><c:out value="${areaBean.gentle}"></c:out></td>
                        <td>优势树种</td>
                        <td><c:out value="${areaBean.species}"></c:out></td>
                    </tr>
                    <tr>
                        <td>创建时间</td>
                        <td><c:out value="${classBean.date}"></c:out></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <div class="col-md-12" style="text-align: center">
                    <input type="button" value="修改" class="btn btn-default" onclick="update()">
                </div>
            </form>
        </div>
    </div>
</body>
	     <script>
	                	function update(){
	                		var id=$("#input1").val()
	     	                var duty=$("#input2").val()
	     	                var phoneNum=$("#input3").val()
	     	                var regex1=/^[0-9\u4e00-\u9fa5\s·]+$/
	     	               
	     	                var regex2=/^[1[3|5|7|8|]\d[0-9]{9}$/
	     	                var boo1=regex1.test(duty);
	     	                var boo2=regex2.test(phoneNum);
	     	                if(!boo1){
	     	                	$("#spanDuty").css({"diplay":"inline"})
	     	                	return;
	     	                }else if(!boo2){
	     	                	$("#spanNum").css("display":"inline")
	     	                	return;
	     	                }
	                	  $.ajax({
	   	                   url:"/project2/EventUpdate",
	   	            	   type:"post",
	   	            	   dataType:"json",
	   	            	   data:{"id":id,"duty":duty,"phoneNum":phoneNum},
	   	                   success:function(data){
	   	                	    window.location.href="/project2/jsp/disasterPrevention/class/class.jsp?id="+1;
	   	                   }});
	                   }
	     </script>
</html>