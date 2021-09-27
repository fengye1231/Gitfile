<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/bootstrap-paginator.min.js"></script>


    <style>
        #main{
            width: 100%;
            height: 500px;
        }
        .page-header{
            text-align: center;
            margin: 0;
        }
        h1{
            font-weight: 700;
            font-family: STKaiti;
        }
        h2{
        	margin-top:20px;
        }
        .table-1{
            width: 80%;
            margin: 0 auto;
        }
        table thead, tbody tr {
            display:table;
            width:100%;
            table-layout:fixed;
            text-align: left;
        }
        table tbody{
            display: block;
            height:160px;
            overflow:auto;
        }
        .table-bordered{
            border:2px solid gray;
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
        .p5{
            width: 20%;
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
        .control-group{
            margin-top: 5px;
        }
        .control-label{
            width: 100px;
        }
        .form-horizontal .control-label {
            text-align: left;
        }
        .search{
        	width: 150px;
            margin-left: 22%;
        }
        .bottom-div{
            float: left;
            width: 365px;
            height: 240px;
        }
        .div_down{
            margin: 0 auto;
        }
        .navi{
        	margin-left:30%;
        }
    </style>
</head>
<body>
<div id="main">
        <div class="page-header">
            <h2>专家一览</h2>
        </div>
        <div class="table-1">
            <table class="table table-bordered table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th class="p1">姓名</th>
                    <th class="p2">工作单位</th>
                    <th class="p3">专长</th>
                    <th class="p4">职务</th>
                    <th class="p5">电话</th>
                </tr>
                </thead>
                <tbody id="tbody1">
                    
                </tbody>
            </table>

        </div>
        <div class="div_down">
            <div class="bottom-div">
            
                <div class="navi">
                    <ul id="cutPageUL" class="pagination"></ul>
                </div>
                
                <div>
                    <button type="button" class="btn btn-success" id="add1">添加专家</button>
                    <button type="button" class="btn btn-default" id="check">查看专家信息</button>
                    <br/>
                    <br/>
                    <button type="button" class="btn btn-default" id="update1">修改专家信息</button>
                    <button type="button" class="btn btn-danger" id="del1">删除专家</button>
                </div>
            </div>
            <div class="bottom-div">
                <form class="form-horizontal" id="form1">
                    <div id="form-top">
                        <legend><span class="label label-default" id="span1">查询专家的信息</span></legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input01">专家姓名</label>
                        <input type="text" placeholder="请输入专家姓名" class="input-xlarge" id="name" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" >
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input02">专长</label>
                        <input type="text" placeholder="请输入专家专长" class="input-xlarge" id="special" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" >
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input03">工作单位</label>
                        <input type="text" placeholder="请输入工作单位" class="input-xlarge" id="workPlace" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" >
                    </div>
	                <br/>
	                <input type="button" class="btn btn-primary search" onclick="find()" value="查询" />
	            </form>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">

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
	  
	 
	  //加载页面时自动查询全部
	  (function(){
	   check(1);
	  })();

	  
	  //查询按钮的方法
	  function find(){
	  var name = $("#name").val();
	  var special = $("#special").val();
	  var workPlace = $("#workPlace").val();
	  check(1,name,special,workPlace);
	  }
	  
	  
	  //ajax主页面表格显示
	  function check(num,name,special,workPlace){
	  var str="";
	  $.post("/project2/expertShowAll",
		   "pageNo=" + num + "&name=" + name + "&special=" + special + "&workPlace=" + workPlace,
		   function(info){
		   		console.log(info);
		   		for(var i = 0;i<info.list.length;i++){
		   			str += "<tr onclick='change(this,"+ info.list[i].id +")'><td class='p1'>"+info.list[i].name+
					"</td><td class='p2'>"+info.list[i].workPlace+
					"</td><td class='p3'>"+info.list[i].special+
					"</td><td class='p4'>"+info.list[i].position+
					"</td><td class='p5'>"+info.list[i].phoneNum+
					"</td></tr>";
		   		}
		   		$("#tbody1").html(str);
		
			   		
		   		//给bootstrap分页组件赋值
		   		ops.totalPages = info.totalPageNum;
		   		ops.currentPage = num;
		   		$("#cutPageUL").bootstrapPaginator(ops);		
			},"json");	   
	  }
	  
	  
		//选中行改变颜色，并传递id
		var sel = null;
		var thisId = 0;
		function change(tr,id){
			var sel1 = $(tr);
			sel1.css("background-color","lightGreen");
			if(sel != null){
		           sel.css("background-color","white");
		       }
			sel = sel1;
			thisId = id;
		}
	
	
	  //添加按钮事件
	  $("#add1").click(function(){
	      location.href='add.jsp';
	  })
	
	  //查看按钮事件
	  $("#check").click(function(){
	   if(thisId == 0){
		   alert("请在表格中点击需要查看的专家！")
	   }else{
		   location.href="/project2/findExpert?id=" + thisId;
	   }       
	  })
	
	  //修改按钮事件
	  $("#update1").click(function(){
	   if(thisId == 0){
		   alert("请在表格中点击需要修改的专家！")
	   }else{
		   location.href="/project2/updateExpert1?id=" + thisId;
	   }
	      
	  })
	  
	  //删除按钮事件
	  $("#del1").click(function(){
		  if(thisId == 0){
			   alert("请在表格中点击需要删除的专家！")
		   }else{
			   $.post("/project2/delExpert",
					   "id=" + thisId,
					   function(info){
				   			check(1);
			   		}
			   );
		   }   
	  })
</script>
</html>