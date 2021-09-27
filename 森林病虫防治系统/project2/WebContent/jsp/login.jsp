<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="../css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="../js/jQuery-2.2.2-min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap-paginator.min.js"></script>

    <style>
        #main{
            margin-top: 200px;
        }
        .top{
            text-align: center;
        }
        #title1{
            font-size: 30px;
        }
        .input-group{
            width: 70%;
            margin: 0 auto;
            height:50px;
        }
        .input-group .form-control {
            width: 100%;
        }
        #checkDiv{
            height: 60px;
            text-align: center;
        }
        #checkCode{
            margin-left: 10%;
        }
        #imgCode{
            margin: 0 auto;
        }
        #false1{
        	display:none;
        }
        #false2{
        	display:none;
        }
        .form_down{
        	text-align: center;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-md-offset-4" id="main">
            <form action="/project2/login" class="form-horizontal" id="form1" method="post">
                <div class="top">
                    <span id="title1">用户登录</span>
                </div>
                </br>
                <div class="input-group">
                    <input type="text" class="form-control" name="userName" placeholder="请输入你的账号" id="userName1"/>
                    <span id="false1">账号应为4-10位字母！</span>                 	            
                </div>
				</br>
                <div class="input-group">
                    <input type="password" class="form-control" name="pwd" placeholder="请输入你的密码" id="pwd1">
                    <span id="false2">密码应为3位数字！</span>                 	
                </div>
				</br>
                <div id="checkDiv">
                    <input type="text" name="code" placeholder="请输入验证码" id="checkCode"/>
                    <img src = "/project2/code" width="130" height="53" id="img1"/>
                    <a onclick="change()">换一张！</a>
                </div>
				</br>
                <div class="form_down">
                    <button type="submit" class="btn btn-primary">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var img1 = document.getElementById("img1");
    function change() {
        img1.src="/project2/code?a="+Math.random();
    }
    
    var userName = $("#userName1");
    var pwd = $("#pwd1");

    var form = $("#form1");

    var nameSpanFalse = $("#false1");
    var pwdSpanFalse = $("#false2");


    var boo1 = false;
    userName.blur(function(){
        var nameRegex = /^[a-zA-Z]{4,10}$/;
        var a = nameRegex.test(this.value);
        if(a){a
            nameSpanFalse.css({display:"none"});
        }else{
            nameSpanFalse.css({display:"inline"});
        }
        boo1=a;
    })

    var boo2 = false;
    pwd.blur(function(){
        var pwdRegex = /^[0-9]{3}$/;
        var a = pwdRegex.test(this.value);
        if(a){
            pwdSpanFalse.css({display:"none"});
        }else{
            pwdSpanFalse.css({display:"inline"});
        }
        boo2=a;
    })
    
    form.submit(function(){
        userName.blur();
        pwd.blur();
        return boo1&&boo2;
    })
    


</script>
</html>