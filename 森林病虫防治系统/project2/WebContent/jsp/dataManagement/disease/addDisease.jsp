<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addDisease</title>
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../../css/bootstrap-theme.min.css" />
<script src="../../../js/jQuery-2.2.2-min.js"></script>
<script src="../../../js/bootstrap.min.js"></script>

<style>
#main {
	width: 782px;
	height: 530px;
	border: 1px solid black;
}

.page-header {
	text-align: center;
	margin: 0px;
}

h1 {
	font-weight: 700;
	font-family: STKaiti;
}

.control-group {
    margin-bottom: 5%;
    margin-left: 3%;
	width:357px;
    height:30px;
	display: inline-block;
	float: left;
}

span {
	margin-left: 80px;
	color: red;
}

.control-label {
	width: 80px;
}

.form-horizontal .control-label {
	text-align: left;
}

.button-add {
	text-align: center;
}

button {
	width: 200px;
}

input[type=file] {
	display: inline-block;
}

.input-file {
	width: 171px;
}

.textarea {
	vertical-align: top;
	height: 116px;
	width: 234px;
}
#span1,#span2,#span3,#span4,#span5,#span6{
   display: none;
}
#addB{
   margin-left: 8%;
}
</style>

</head>
<body>
	<div id="main">
		<div class="page-header">
			<h1>添加病害</h1>
		</div>
		<form class="form-horizontal" role="form"
			action="/project2/AddDisease" method="post"
			enctype="multipart/form-data" id="form1">
			<div class="control-group">
				<span id="span1">请输入2个到20个字的名称</span>
				<br/> <label class="control-label"
					for="input01">名称</label> <input name="diseaseName" type="text"
					name="" placeholder="名字" class="input-xlarge" id="input01">
			</div>
			<div class="control-group">
				<span id="span2">请输入2个到20个字的病原</span> <br/> <label class="control-label"
					for="input02">病原</label> <input name="pathogen" type="text"
					placeholder="寄主名" class="input-xlarge" id="input02">
			</div>
			<div class="control-group">
				<span id="span3">请输入2个到100个字的发病症状</span> <br/> <label class="control-label"
					for="input03">发病症状</label> <input name="sysmptom" type="text"
					placeholder="aaaaaa" class="input-xlarge" id="input03">
			</div>
			<div class="control-group">
				<span id="span4">请输入2个到100个字的发病规律</span><br/>  <label class="control-label"
					for="input04">发病规律</label> <input name="law" type="text"
					placeholder="aaaaaaar" class="input-xlarge" id="input04">
			</div>
			<div class="control-group" style="
    margin-top: 22px;
    margin-bottom: 17px;">
				<label class="control-label">图片</label> <input name="fileImg"
					class="input-file" id="fileInput1" type="file">
			</div>
			<div class="control-group">
				<span id="span5">请输入2个到200个字的主要危害</span><br/>  <label class="control-label"
					for="input05">主要危害</label> <input name="harm" type="text"
					placeholder="aaaaaaar" class="input-xlarge" id="input05">
			</div>
			<div class="control-group">
				<span id="span6">请输入2个到200个字的防范措施</span> <br/> <label class="itemTitle">防范措施</label>
				<textarea name="prevention" rows="9" cols="20"
					style="vertical-align: top" id="input06" class="textarea"></textarea>
			</div>

			<hr />
			<div class="button-add" id="addB">
				<button type="submit" class="btn btn-success" style="margin-top: 100px;">添加</button>
			</div>
		</form>
	</div>

</body>
<script>
	var boo = false;
	$("#input01").blur(function() {
		var regex = /^[\u4e00-\u9fa5]{2,20}$/;
		var a = regex.test(this.value);
		if (a) {
			$("#span1").css({
				display : "none"
			});
		} else {
			$("#span1").css({
				display : "inline"
			});
		}
		boo = a;
	})
	$("#input02").blur(function() {
		var regex = /^[\u4e00-\u9fa5]{2,20}$/;
		var a = regex.test(this.value);
		if (a) {
			$("#span2").css({
				display : "none"
			});
		} else {
			$("#span2").css({
				display : "inline"
			});
		}
		boo = a;
	})
	$("#input03").blur(function() {
		var regex = /^[\u4e00-\u9fa5]{2,100}$/;
		var a = regex.test(this.value);
		if (a) {
			$("#span3").css({
				display : "none"
			});
		} else {
			$("#span3").css({
				display : "inline"
			});
		}
		boo = a;
	})
	$("#input04").blur(function() {
		var regex = /^[\u4e00-\u9fa5]{2,100}$/;
		var a = regex.test(this.value);
		if (a) {
			$("#span4").css({
				display : "none"
			});
		} else {
			$("#span4").css({
				display : "inline"
			});
		}
		boo = a;
	})
	$("#input05").blur(function() {
		var regex = /^[\u4e00-\u9fa5]{2,200}$/;
		var a = regex.test(this.value);
		if (a) {
			$("#span5").css({
				display : "none"
			});
		} else {
			$("#span5").css({
				display : "inline"
			});
		}
		boo = a;
	})
	$("#input06").blur(function() {
		var regex = /^[\u4e00-\u9fa5]{2,200}$/;
		var a = regex.test(this.value);
		if (a) {
			$("#span6").css({
				display : "none"
			});
		} else {
			$("#span6").css({
				display : "inline"
			});
		}
		boo = a;
	})

	var form = $("#form1");
	form.submit(function() {
		$("#input01").blur();
		$("#input02").blur();
		$("#input03").blur();
		$("#input04").blur();
		$("#input05").blur();
		$("#input06").blur();
		return boo;
	})
</script>

</html>