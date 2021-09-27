<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
     登录失败，将在<span id="span1">3</span>秒后返回登录界面
    <button onclick="run()">快速返回</button>
</body>
    <script>
        var span1 = document.getElementById("span1");
        var a = 3;
        var time = setInterval(function(){
            a--;
            span1.innerHTML = a;
            if(a==0){
                location.href="/project2/jsp/login.jsp";
            }
        },1000);
        function run(){
            clearInterval(time);
            location.href="/project2/jsp/login.jsp";
        }
    </script>
</html>