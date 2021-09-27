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

    <style>
        #main{
            width: 100%;
            height: 500px;
        }
        .page-header{
            text-align: center;
            margin: 0px;
        }
        h1{
            font-weight: 700;
            font-family: STKaiti;
        }

        .control-group{
            margin-top: 2%;
            margin-left: 11%;
        
            width: 247px;
            display: inline-block;
        }
        .control-label{
            width: 70px;
        }
        .form-horizontal .control-label {
            text-align: left;
            margin-bottom:17px;
        }
        #button{
            width: 200px;
        }
        .button-return{
            text-align: center;
        }
        img{
            margin-top: 2%;
            width: 150px;
            height: 150px;
            float: right;
            margin-right: 30%;
            border:1px solid black;
        }
    </style>
</head>
<body>
<div id="main">
       <div class="page-header">
           <h1>查看专家信息</h1>
       </div>
       <div class="col-md-10 col-md-offset-1">
               <div class="control-group">
                   <label class="control-label" for="input01">姓名</label>
                   <c:out value="${bean.name}" default="无法显示"></c:out>
               </div>
                   <img alt="无法显示" width="300" src="/project2/img/<c:out value="${bean.imgPath}"></c:out>">
               <div class="control-group">
                   <label class="control-label" for="input02">出生年月</label>
                   <c:out value="${bean.birthday}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input03">性别</label>
                   <c:out value="${bean.gender}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input04">专长</label>
                   <c:out value="${bean.special}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input05">职务</label>
                   <c:out value="${bean.position}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input06">电话</label>
                   <c:out value="${bean.phoneNum}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input07">工作单位</label>
                   <c:out value="${bean.workPlace}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input08">通讯地址</label>
                   <c:out value="${bean.address}" default="无法显示"></c:out>
               </div>
               <div class="control-group">
                   <label class="control-label" for="input09">邮箱</label>
                   <c:out value="${bean.email}" default="无法显示"></c:out>
               </div>
               <hr/>
               <div class="button-return">
                   <input type="button" id="button" class="btn btn-primary btn-lg" onclick="return1()" value="返回" />
               </div>
       </div>
   </div>
</body>
<script type="text/javascript">
	function return1() {
		location.href="/project2/jsp/expertConsultation/expert/main.jsp";
	}
</script>
</html>