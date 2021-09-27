<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
        .page-header {
            margin-top: 0;
            text-align: center;
        }
        #main{
            width: 100%;
            height: 530px;
            border:1px solid black
        }
        #top{
            font-size: 30px;
            font-weight: 700;
            font-family: STKaiti;
        }
        .form-horizontal{
            width: 100%;
            height: 310px;
        }
        .control-group{
            margin-top: 10px;
            margin-left: 15%;
            width: 100%;
            float: left;
        }
        .control-group2{
            margin-top: 10px;
            margin-left: 5%;
            width: 95%;
            float: left;
        }
        .control-label{
            width: 70px;
        }
        .form-horizontal .control-label {
            text-align: left;
            margin-bottom: 5px;
        }

        .select-box{
            float: left;
            width: 70px;
            font-size: 10px;
        }
        #choose button{
            float: left;
            width: 30px;
            height: 20px;
            line-height: 20px;
        }
        #choose{
            display: inline-block;
            width: 100%;
        }
        #div1{
            float: left;
        }
        select[multiple], select[size] {
            height: 80px;
        }
        #choose label{
            margin-top: 5%;
            margin-left: 6%;
            float: left;
        }

        .form-control{
            float: right;
            margin-right: 15%;
            width: 60%;
        }

        .table-1{
            width: 80%;
            margin: 0 auto;
        }

        table thead, tbody tr {
            display:table;
            width:100%;
            table-layout:fixed;           
        }
        table tbody{
            display: block;
            height:95px;
            overflow-y:scroll;
            text-align: left;
        }
        .table-bordered{
            border:2px solid gray;
        }
        table thead {
            width: calc( 100% - 1.1em );
        }
        .p1{
            width: 20%;
        }
        .p2{
            width: 40%;
        }
        .p3{
            width: 40%;
        }
        #add1{
            margin-top: 10px;
            width: 100px;
            margin-left: 17%;
        }
        #return1{
            margin-top: 10px;
            width: 50px;
            margin-left: 5%;
        }
        #span1{
            float: right;
            margin-right: 18%;
            width: 184px;
            height: 60px;
        }

        .table {
            margin-bottom: 5px;
        }

        #img1{
            display: inline-block;
            width: 200px;
            height: 100px;
            border:1px solid black
        }
        
        
        #false1{
        	float:left;
        	margin-left:25%;
        	color:red;
        	display: none;
        }
        #false2{
        	float:left;
        	color:red;
        	display: none;
        }

    </style>
</head>
<body>
<div id="main">
        <div class="page-header">
            <span1 id="top">专家会商</span1>
        </div>
        <form class="form-horizontal" action="#" method="post" id="form1">
            <div class="col-sm-5">
                <div class="control-group">
                    <label class="control-label">事件名称</label>
                    <c:out value="${bean.name}" default="无法显示"></c:out>
                </div>
                <div class="control-group">
                    <label class="control-label">时间</label>
                    <c:out value="${bean.date}" default="无法显示"></c:out>
                </div>
                <div class="control-group">
                    <label class="control-label">发生位置</label>
                    <c:out value="${bean.area}" default="无法显示"></c:out>
                </div>
                <div class="control-group">
                    <label class="control-label">灾情描述</label>
                    <c:out value="${bean.describe}" default="无法显示"></c:out>
                </div>
                <div class="control-group">
                    <label class="control-label" for="input01">会商结果</label>
                    <textarea class="form-control" rows="3" name="textarea" placeholder="请输入会商结果" id="resultInput"></textarea>
                    <span id="false1">请输入5-20个汉字</span>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="control-group2">
                    <label class="control-label">灾区图片</label>
                    <img alt="无法显示" id="img1" src="/project2/img/<c:out value="${bean.imgPath}"></c:out>">
                </div>
                <div class="control-group2">
                    <label class="control-label">影响面积</label>
                    <c:out value="${bean.affectArea}" default="无法显示"></c:out>
                </div>
                <div id="choose">
                    <label class="control-label" for="input02">会商人员</label>
                    <select name="box1" id="box1" multiple size="5" class="select-box"></select>
                    <div id="div1">
                        <button type="button" id="button1">></button>
                        <br/>
                        <button type="button" id="button2"><</button>
                        <br/>
                        <button type="button" id="button3">>></button>
                        <br/>
                        <button type="button" id="button4"><<</button>
                    </div>
                    <select name="box2" id="box2" multiple size="5" class="select-box"></select>
                    <span id="false2">请至少选择一名专家！</span>
                    <input type="hidden" name="id" value="${bean.id}" id="eventId"/>
                </div>
                <div class="control-group2">
                    <input type="button" class="btn btn-primary btn-sm" id="add1" value="添加会商信息">
                    <input type="button" class="btn btn-default btn-sm" id="return1" value="返回">
                </div>
            </div>
        </form>

        <div class="table-1">
            <table class="table table-bordered table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th class="p1">日期</th>
                    <th class="p2">会商人员</th>
                    <th class="p3">会商结果</th>

                </tr>
                </thead>
                <tbody id="tbody1">
                </tbody>
            </table>
        </div>
    </div>
</body>
<script>
    var box1 = $("#box1");
    var box2 = $("#box2");
    
    //获得当前事件id
    var eventId = $("#eventId").val();
    
    
    //判断会商结果
    var boo1 = false;
    var boo2 = false;
    $("#resultInput").blur(function(){
		var regex = /^[\u4e00-\u9fa5]{2,10}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false1").css({display:"none"});
	    }else{
	    	$("#false1").css({display:"inline"});
	    }
	    boo1=a;
	})
    
    
    //加载页面时加载所有专家姓名和会商信息
    $(function(){
    	$.ajax({
    		type:"post",
    		url:"/project2/expertNameShow",
    		dataType:"json",   		
    		success:function(info){
    			var str = "";
    			for(var i=0;i<info.length;i++){
    				str += "<option value='"+ info[i] +"'>"+ info[i] +"</option>";
    			}
    			$("#box1").html(str);
    		}
    	});
    	
    	//加载会商信息
    	showAll();   	
    })
    
    //选择框组件
    $("#button1").click(function(){
        var a = $("#box1 option:selected");
        a.appendTo(box2);
    })

    $("#button2").click(function(){
        var a = $("#box2 option:selected");
        a.appendTo(box1);
    })

    $("#button3").click(function(){
        box1.children().appendTo(box2);
    })

    $("#button4").click(function(){
        box2.children().appendTo(box1);
    })
    
    
    
    //获取选择的专家名，拼接字符串
    var nameStr = "";
    var result = "";
    function getName(){  	
    	var channel = $("#box2").find("option");
    	for(var i=0;i<channel.length;i++){
    		nameStr += channel.eq(i).val();
    		if(i < channel.length -1){
    			nameStr += "、";
    		}
    	} 	 	
    }
    
    
    box2.blur(function(){
    	getName();
    	var a = (nameStr == "");
    	console.log(a);
    	if(a){
    		$("#false2").css({display:"inline"});
    	}else{
    		$("#false2").css({display:"none"});
    	}
    	boo2 = a;
    	
    })
    
    
    //添加会商按钮事件：ajax添加会商
    $("#add1").click(function(){
    	
    	$("#resultInput").blur();
    	box2.blur();
    	
    	if(!boo1 || boo2){
    		return;
    	}
    	
    	result = $("#resultInput").val();
    	
    	$.ajax({
    		type:"post",
    		url:"/project2/addConsultation",
    		data:"result=" + result + "&content=" + nameStr + "&eventId=" + eventId,
    		success:function(info){
    			alert("添加成功！");
    			showAll();
    			nameStr = "";   			
    		}
    	});
	
    })
    
    
    //ajax获取会商记录
    function showAll(){
    	 $.ajax({
    		type:"post",
     		url:"/project2/consultationShowAll",
     		dataType:"json",     		
     		data:"eventId=" + eventId,
     		success:function(info){
     			str = "";
     			for(var i=0;i<info.length;i++){
     				str += "<tr><td class='p1'>"+info[i].date+
 					"</td><td class='p2'>"+info[i].content+
 					"</td><td class='p3'>"+info[i].result+
 					"</td></tr>";
     			}
     			$("#tbody1").html(str);
     		}
     	});
    }
   
	//返回按钮事件
    $("#return1").click(function(){
        location.href="/project2/jsp/expertConsultation/consultation/main.jsp";
    })
</script>
</html>