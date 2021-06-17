<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
		* {
			font-family: "Microsoft Yahei","Helvetica Neue",Helvetica,Arial,sans-serif;
			font-weight: 500;
			line-height: 1.1;
		}
		body {
			background-color: #f8f8f8;
		}
		h1 {
			margin-bottom: 10px;
			margin-top: 4em;
			font-size: 36px;
		}
		h4 {
			font-size: 18px;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		hr {
			margin-top: 20px;
			margin-bottom: 20px;
			border: 0;
			border-top: 1px solid #eee;
			height: 0;
		}
	</style>
	<script type="text/javascript">
		if (typeof Worker !== "undefined") {
			window.location.href = "<%= basePath %>";
		}
	</script>
    <title>不支持的浏览器</title>
</head>
<body>

<center><h1>请升级您的浏览器</h1></center>
<hr>
<center><h4>本系统仅支持现代浏览器访问</h4></center>

</body>
</html>