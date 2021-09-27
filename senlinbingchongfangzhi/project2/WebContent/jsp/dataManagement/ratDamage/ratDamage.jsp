<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ratDamage</title>
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../../css/bootstrap-theme.min.css" />
<script src="../../../js/jQuery-2.2.2-min.js"></script>
<script src="../../../js/bootstrap.min.js"></script>
<script src="../../../js/bootstrap-paginator.min.js"></script>


<style>
#main {
	width: 782px;
	height: 530px;
	border: 1px solid black;
}

.page-header {
	text-align: center;
	margin: 0;
}

h1 {
	font-weight: 700;
	font-family: STKaiti;
}

table thead, tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
	text-align: left;
}

table tbody {
	display: block;
	height: 127px;
	overflow: auto;
}

.table-bordered {
	border: 2px solid gray;
}

table thead {
	width: 98%;
}

.p1 {
	width: 8%;
}

.p2 {
	width: 10%;
}

.p3 {
	width: 30%;
}

.navi {
	margin-left: 20px;
	font-size: 10px;
	float: left;
	width: 500px;
}

button {
	width: 150px;
	margin-left: 5%;
}

#form-top {
	height: 50px;
	line-height: 50px;
}

form {
	text-align: center;
}

#span1 {
	font-size: 20px;
}

.control-group {
	margin-top: 20px;
}

.control-label {
	width: 100px;
}

.form-horizontal .control-label {
	text-align: left;
}

.search {
	margin-left: 40%;
}

.bottom-div {
	float: left;
	width: 365px;
	height: 260px;
}
</style>
</head>
<body>
	<div id="main">
		<div class="page-header">
			<h1>鼠害一览</h1>
		</div>
		<div class="table-1">
			<table
				class="table table-bordered table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th class="p1">名称</th>
						<th class="p2">食物</th>
						<th class="p3">主要危害</th>

					</tr>
				</thead>
				<tbody id="table">
				
				</tbody>
			</table>

		</div>
		<div class="bottom-div">
			<div class="navi">
				<ul id="cutPageUL" class="pagination"></ul>
			</div>
			<div>
				<button type="button" class="btn btn-primary" id="add1">添加新鼠害</button>
				<button type="button" class="btn btn-info" id="check">查看详细信息</button>
				<br /> <br />
			</div>
		</div>
		<div class="bottom-div">
			<form class="form-horizontal" role="form" action="#" method="post"
				id="form1">
				<div id="form-top">
					<legend id="">
						<span class="label label-default" id="span1">查询鼠害的信息</span>
					</legend>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">鼠害名称</label> <input
						type="text" placeholder="请输入鼠害名称" class="input-xlarge"
						id="ratDamageName">
				</div>
			</form>
			<br />
			<button type="submit" class="btn btn-primary search" onclick="find()">查询</button>
		</div>

	</div>
</body>
<script type="text/javascript">
	var ratDamageName;
	var currentTr = null;
	function cutPage(pageNo) {
		var str = "";
		var str1 = "";
		$
				.post(
						"/project2/ratDamageshowAll",
						"pageNo=" + pageNo + "&ratDamageName=" + ratDamageName,

						function(info) {

							for (var i = 0; i < info.list.length; i++) {
								str += "<tr class='proTr' id='tr"+info.list[i].id+"'><td class='p1'>"
										+ info.list[i].name
										+ "</td><td class='p2'>"
										+ info.list[i].food
										+ "</td><td class='p3'>"
										+ info.list[i].harm + "</td></tr>";
							}
							$("#table").html(str);

							for (var i = 1; i <= info.totalPageNum; i++) {
								str1 += "<li><a onclick='cutPage(" + i + ")'>"
										+ i + "</a></li>";
							}
							$("#cutPageUL").html(str1);
							init();
						}, "json");
	}
	//页面加载时的ajax请求
	(function() {
		cutPage(1);
	})();
	//查询按钮的ajax请求
	function find() {
		ratDamageName = $("#ratDamageName").val();
		cutPage(1);
	};

	function init() {
		var trArr = document.getElementsByClassName("proTr");
		for (var i = 0; i < trArr.length; i++) {
			trArr[i].onclick = function() {
				if (currentTr != null) {
					currentTr.style.backgroundColor = 'white';
				}
				this.style.backgroundColor = 'gainsboro';
				currentTr = this;
			}
		}
	}
	var ops = {
		bootstrapMajorVersion : 3,//bootstrap版本
		currentPage : 1,//当前页码
		totalPages : 8,//总页数
		numberOfPages : 5,//页面最多显示的页码数
		pageSize : 5,
		pageList : [ 10, 25, 50, 100 ],
		onPageClicked : function(e, ev, type, page) {//page为点中的页码
			alert(page);
		}
	};
	//初始化分页组件
	$("#cutPageUL").bootstrapPaginator(ops);

	$("#add1").click(function() {
		window.location = 'add.html';
	})

	//查看鼠害信息
	$("#check").click(function() {
		if (currentTr == null) {
			alert("请先选择查看鼠害信息");
			return;
		}
		location.href = "/project2/ratmamagecheck?id=" + currentTr.id;
	});
    $("#add1").click(function(){
        window.location='/project2/jsp/dataManagement/ratDamage/addRatDamage.jsp';
    })
</script>
</html>