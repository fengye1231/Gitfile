<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户主界面</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/bootstrap-paginator.min.js"></script>


    <style>
        #main{
            width: 782px;
            height: 530px;
            border:1px solid black;
        }
        .page-header{
            text-align: center;
            margin: 0;
        }
        h1{
            font-weight: 700;
            font-family: STKaiti;
        }
        table thead, tbody tr {
            display:table;
            width:100%;
            table-layout:fixed;
            text-align: left;
        }
        table tbody{
            display: block;
            height:127px;
            overflow:auto;
        }
        .table-bordered{
            border:2px solid gray;
        }
        table thead {
            width: 98%;
        }
        .p1{
            width: 15%;
        }
        .p2{
            width: 30%;
        }
        .p3{
            width: 20%;
        }
        .p4{
            width: 15%;
        }
        .navi{
            margin-left: 20px;
            font-size: 10px;
            float: left;
            width: 500px;
        }
        button{
            width: 150px;
            margin-left:5%;
        }
        #form-top{
            height: 50px;
            line-height: 50px;
        }
        form{
            text-align: center;
        }
        #span1{
            font-size: 20px;
        }

        .form-horizontal {
            text-align: left;
        }
        .bottom-div{
            float: left;
            width: 365px;
            height: 260px;
        }
        #form1{
            text-align: center;
        }
        #level{
            font-size: 18px;
        }
        #f_className{
        	width: 100px;
            height: 30px;
        }

    </style>
</head>
<body>
<div id="main">
    <div class="page-header">
        <h1>用户管理</h1>
    </div>
    <div class="table-1">
        <table class="table table-bordered table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th class="p1">用户名</th>
                <th class="p2">密码</th>
                <th class="p3">等级</th>
                <th class="p4">真实姓名</th>
            </tr>
            </thead>
            <tbody id="tbody1">
            
            </tbody>
        </table>

    </div>
    <div class="bottom-div">
        <div class="navi">
            <ul id="cutPageUL" class="pagination"></ul>
        </div>
        <div>
            <button type="button" class="btn btn-success" id="add1">添加用户</button>
            <button type="button" class="btn btn-danger" id="del1">删除用户</button>
            <br/>
            <br/>
            <button type="button" class="btn btn-default" id="update1">修改用户信息</button>
        </div>
    </div>
    <div class="bottom-div">
        <form class="form-horizontal" role="form" method="post" id="form1">
            <div id="form-top">
                <legend id=""><span class="label label-default" id="span1">查询用户信息</span></legend>
            </div>
            <br/>
            <br/>
            <br/>
            <p>
                <select name="level" class="btn btn-default btn-sm btn-primary " id="level">
                    <option value="super">超级管理员</option>
                    <option value="data">资料管理员</option>
                    <option value="disaster">灾情管理员</option>
                    <option value="expert">专家管理员</option>
                    <option value="warehouse">库房管理员</option>
                </select>
                <button type="submit" class="btn btn-primary">查询</button>
            </p>

        </form>
    </div>

</div>
</body>
<script>
//bootstrap分页页码组件
var ops = {
    bootstrapMajorVersion:3,//bootstrap版本
    currentPage:1,//当前页码
    totalPages:8,//总页数
    numberOfPages:5,//页面最多显示的页码数
    pageSize:5,
    pageList: [10, 25, 50, 100],
    onPageClicked:function(e,ev,type,page){//page为点中的页码
        check(page);
    }
};
    
    $(function(){
    	check(1,'');
    });
    
    var level;
    
    function find(){
    	level = $("#level").val();
    	check(1,level);
    }
    
    
	   //ajax查询
	   function check(num,level){
	   var str="";
	   $.post("/project2/UserShowAll",
			   "pageNo=" + num + "&level=" + level,
			   function(info){
		   		for(var i = 0;i<info.list.length;i++){
		   			str += "<tr onclick='change("+ info.list[i].id +")'><td class='p1'>"+info.list[i].userName+
					"</td><td class='p2'>"+info.list[i].pwd+
					"</td><td class='p3'>"+info.list[i].level+
					"</td><td class='p4'>"+info.list[i].name+
					"</td></tr>";
		   		}
		 		$("#tbody1").html(str);

			  		//给bootstrap赋值
			   	ops.totalPages = info.totalPageNum;
			   	ops.currentPage = num;
			   	$("#cutPageUL").bootstrapPaginator(ops);
		
		   	},"json");	   
	   }
	   
		var thisId = 0;
		function change(id){
			thisId = id;
			console.log(id);
		}
	
	$("#add1").click(function(){
		location.href='addUser.jsp';
	})

    $("#del1").click(function(){
    	if(thisId == 0){
		   alert("请在表格中点击需要删除的用户！")
	   }else{
		   location.href='/project2/UserDel?id=' + thisId;
	   }
    })

    $("#update1").click(function(){
		   if(thisId == 0){
			   alert("请在表格中点击需要修改的用户！")
		   }else{
			   location.href='/project2/FindUser?id=' + thisId;
		   }
   });
</script >
</html>>