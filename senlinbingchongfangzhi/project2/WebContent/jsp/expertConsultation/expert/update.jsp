<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
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
        	margin-top:1px;
        	margin-left:5%;
            width:300px;
            height:53px;
            display: inline-block;
            float:left;
        }
        .control-label{
            width: 80px;
        }
        .form-horizontal .control-label {
            text-align: left;
       
        }
        button{
            margin-top: 30px;
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
        .form-horizontal{
            text-align: left;
        }
        .input-xlarge{
            width: 60%;
        }
        span{
        	display:none;
        	color:red;
        }
    </style>
<body>
<div id="main">
        <div class="page-header">
            <h1>修改专家信息</h1>
        </div>
        <div class="col-md-10 col-md-offset-1">
            <form class="form-horizontal" role="form" action="/project2/updateExpert2" method="post" id="form1">
            	<input type="hidden" name="id" value="${bean.id}">
                <div class="control-group">
                    <label class="control-label">姓名</label>
                    <c:out value="${bean.name}" default="无法显示"></c:out>
                    </br>
                </div>
                <img src="../img/1.gif" alt="...">
                <div class="control-group">
                    <label class="control-label">出生年月</label>
                    <c:out value="${bean.birthday}" default="无法显示"></c:out>
                    </br>
                </div>
                <div class="control-group">
                    <label class="control-label">性别</label>
                    <c:out value="${bean.gender}" default="无法显示"></c:out>
                    </br>
                </div>
                <div class="control-group">
                    <label class="control-label">专长</label>
                    <c:out value="${bean.special}" default="无法显示"></c:out>
                    </br>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input01">职务</label>
                    <input type="text" name="position"  id="input01" value="<c:out value="${bean.position}" default="无法显示"></c:out>" class="input-xlarge"/>
                    </br><span id="false1">职务必须是2-10位汉字</span>	
                </div>
                <div class="control-group">
                    <label class="control-label" for="input02">电话</label>
                    <input type="text" name="phoneNum"  id="input02" value="<c:out value="${bean.phoneNum}" default="无法显示"></c:out>" class="input-xlarge"/>
                    </br><span id="false2">电话号码必须为13,15,18开头的11位数字</span>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input03">工作单位</label>
                    <input type="text" name="workPlace"  id="input03" value="<c:out value="${bean.workPlace}" default="无法显示"></c:out>" class="input-xlarge"/>
                    </br><span id="false3">工作单位必须是4-20位汉字</span>
          
                </div>
                <div class="control-group">
                    <label class="control-label">通讯地址</label>
                    <c:out value="${bean.address}" default="无法显示"></c:out>
                    </br>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input04">邮箱</label>
                    <input type="text" name="email"  id="input04" value="<c:out value="${bean.email}" default="无法显示"></c:out>" class="input-xlarge"/>
                    </br><span id="false4">邮箱格式必须是xx@xx.com</span>
                </div>
                <div class="button-return">
                    <button type="submit" class="btn btn-warning">修改</button>
                </div>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
	var form = $("#form1");
	var boo1 = false;
	var boo2 = false;
	var boo3 = false;
	var boo4 = false;
	$("#input01").blur(function(){
		var regex = /^[\u4e00-\u9fa5]{2,10}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false1").css({display:"none"});
	    }else{
	    	$("#false1").css({display:"inline"});
	    }
	    boo1=a;
	})
	
	$("#input02").blur(function(){
		var regex = /^((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+(\d{8})$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false2").css({display:"none"});
	    }else{
	    	$("#false2").css({display:"inline"});
	    }
	    boo2=a;
	})
	
	$("#input03").blur(function(){
		var regex = /^[\u4e00-\u9fa5]{2,10}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false3").css({display:"none"});
	    }else{
	    	$("#false3").css({display:"inline"});
	    }
	    boo3=a;
	})
	
	$("#input04").blur(function(){
		var regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false4").css({display:"none"});
	    }else{
	    	$("#false4").css({display:"inline"});
	    }
	    boo4=a;
	})
	
	form.submit(function(){
	$("#input01").blur();
	$("#input02").blur();
	$("#input03").blur();
	$("#input04").blur();
    return boo1&&boo2&&boo3&&boo4;
})




</script>
</html>