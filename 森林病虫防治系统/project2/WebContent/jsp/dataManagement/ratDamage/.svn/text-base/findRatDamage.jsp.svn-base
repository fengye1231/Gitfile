<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>find</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>

    <style>
        #main{
            width: 782px;
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

        .control-group{
            margin-top: 2%;
            margin-left: 11%;
            width: 220px;
            display: inline-block;
        }
        .control-label{
            width: 70px;
        }
        .form-horizontal .control-label {
            text-align: left;
            margin-bottom:17px;
        }
        button{
            width: 200px;
        }
        .button-return{
            text-align: center;
            height: 320px;
        }
        .img{
            width: 100%;
            height: 160px;
            float: left;
        }
        img{
            margin-top: 0%;
            width: 150px;
            height: 150px;
            float: right;
            margin-right: 19%;
            border:1px solid black;
        }
        .md{
            width: 50%;
            height: 300px;
            float: left;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="page-header">
        <h1>查看鼠害信息</h1>
    </div>
    <div class="col-md-10 col-md-offset-1">
        <form class="form-horizontal" role="form" action="/project2/jsp/dataManagement/ratDamage/ratDamage.jsp" method="post" id="form1">
            <div class="md">
                <div class="control-group">
                    <label class="control-label" for="input01">名称：</label>
                    <span><c:out value="${RatDamage.name}"></c:out></span>
                </div >
                <div class="control-group">
                    <label class="control-label" for="input02">繁殖：</label>
                    <span><c:out value="${RatDamage.duction}"></c:out></span>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input03">食物：</label>
                    <span><c:out value="${RatDamage.food}"></c:out></span>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input04">天敌：</label>
                    <span><c:out value="${RatDamage.enemy}"></c:out></span>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input05">主要危害：</label>
                    <span><c:out value="${RatDamage.harm}"></c:out></span>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input06">防范措施：</label>
                    <span><c:out value="${RatDamage.prevention}"></c:out></span>
                </div>
            </div>
            <div class="md">
                <div class="img">
                    <span>图片:</span>
                   <img src="/project2/img/<c:out value="${RatDamage.imgPath}"></c:out>" alt="...">
                </div>
            </div>
            <hr/>
            <hr/>
            <br/>
            <br/>
            <div class="button-return">
                <button style="margin-top: 60px;" type="submit" class="btn btn-primary">返回</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>

</script>
</html>