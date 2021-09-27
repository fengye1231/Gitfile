<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>添加出库信息</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/bootstrap-paginator.min.js"></script>
</head>
<style>
    *{
        margin: 0px;
        padding: 0px;
    }
    #addOutBound_main{
        margin: 0px;
        padding: 0px;
    }
    #addOutBound{
        margin: 0px;
        padding: 0px;

        width: 100%;
        height: 530px;
        border:1px solid black;
        overflow-x: hidden;
    }
    #addOutBound_head{
        width: 100%;
        font-size: 36px;
        font-weight: 700;
        font-family: STKaiti;
        text-shadow: black;
    }
    #addOutBound_head1{
        width: 100%;
        font-weight: 400;
        font-family: STKaiti;
        text-shadow: black;
        font-size: 17px;
    }
    .outBound_tableHead{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #addOutBound_head1_left_label{
        padding: 0px;
    }
    #addOutBound_body_container{
        width: 100%;
        margin: 0;
        padding: 0;
    }
    #addOutBound_body{
        margin-top: 40px;
    }
    #addOutBound_body_table{

        border-top: 1px;
        border-bottom: 2px;
        /*height:200px;*/
        width: 100%;
        overflow-y: scroll;
        overflow-x: hidden;
        margin: 0;
        padding: 0;
    }
    #addOutBound_foot{
        margin-top: 80px;
        text-align: center;
    }
    .addOutBound_foot_button{
        display: inline-block;
    }
    #addOutBound_foot_button1{

      margin-left: 2px;
    }
    #addOutBound_foot_button2{
        margin-left: 94px;
    }
    #addOutBound_foot_button3{
        margin-left: 148px;
    }
    /*固定表头*/

    #addOutBound_body_table thead, tbody tr {
        display:table;
        width:100%;
        table-layout:fixed;
        text-align: left;
    }
    #addOutBound_body_table tbody{
        display: block;
        height:143px;
        overflow:auto;
    }
    #addOutBound_body_table{
        border:2px solid gray;
    }
    #addOutBound_body_table thead {
        width: 100%;
    }
    #addOutBound_body_table_p4_hr{
        width: 17%;
    }
    .addOutBound_body_table_p1{
        width: 15%;
    }
    .addOutBound_body_table_p2{
        width: 30%;
    }
    .addOutBound_body_table_p3{
        width: 20%;
    }
    .addOutBound_body_table_p4{
        width: 22%;
    }
      #cutPageUL{
       margin-left: 40px;
    }
    #addOutBound_body_table_p4_hr{
    width: 22%;
    }

</style>
<body>
    <div class="container-fluid" id="addOutBound_main">

                <div class="row" id="addOutBound">
                        <div class="row" id="addOutBound_head">
                            <p class="col-sm-4 col-sm-offset-4">添加出库信息</p>
                        </div>
                        <div class="row" id="addOutBound_head1">
                                <div class="col-sm-offset-1">
                                    <form action="#">
                                        <label for="addOutBound_head1_left" class="col-sm-2" id="addOutBound_head1_left_label">领用小班</label>
                                        <select name="sel" id="addOutBound_head1_left" class="col-sm-3  btn btn-default btn-sm btn-primary" onchange="recordings()" >
                           
								
                                        </select>
                                    </form>
                                </div>

                                <div class="row" >
                                    <div class="col-sm-2 col-sm-offset-2">
                                        <span>出库人</span>
                                    </div>
                                    <div class="col-sm-2">
                                    
                                        <span id="addOutBound_head1_span"><j:out value="${sessionScope.user.name}"></j:out></span>
                                    </div>

                                </div>

                            </div>




                        <div class="row" id="addOutBound_body">
                            <div class="col-sm-12">
                                <form action="#" method="get">
                                <div class="container" id="addOutBound_body_container" >

                                    <table class="table table-bordered table-striped table-hover table-condensed" id="addOutBound_body_table">
                                        <thead>
                                        <tr class="outBound_tableHead"><th class="addOutBound_body_table_p1">物品名称</th><th class="addOutBound_body_table_p2">类型</th><th class="addOutBound_body_table_p3">防治类型</th><th id="addOutBound_body_table_p4_hr" class="addOutBound_body_table_p4">领用数量</th></tr>
                                        </thead>
                                            <tbody id="outBound_tableHead_body">
                                         	</tbody>
                                    </table>
                                </div>
                                 <div>
		                         	<ul id="cutPageUL" class="pagination"></ul>
		                         </div>
                                </form>
                            </div>
                        </div>
                        <div class="row" id="addOutBound_foot">
                            <div class="addOutBound_foot_button">
                              	<input id="addOutBound_foot_button1"  class="btn btn-info btn btn-primary btn-lg" value="添加物品" type="button" />
                            </div>
                            <div class="addOutBound_foot_button">
                            	<input id="addOutBound_foot_button2"  class="btn btn-info btn btn-primary btn-lg" value="移除物品" type="button" />
                            </div>
                            <div class="addOutBound_foot_button">
                               <input id="addOutBound_foot_button3"  class="btn btn-info btn btn-primary btn-lg" value=" 出库  " type="button" />
                            </div>
                        </div>


                    </div>
                </div>

</body>
<script>
    var ops = {
        bootstrapMajorVersion:3,//bootstrap版本
        currentPage:1,//当前页码
        totalPages:1,//总页数
        numberOfPages:5,//页面最多显示的页码数
        pageSize:5,
        pageList: [10, 25, 50, 100],
        onPageClicked:function(e,ev,type,page){//page为点中的页码
        	
        	console.log(page);
        	check(page);
       
        }
    };
    $("#cutPageUL").bootstrapPaginator(ops);
    
    $(function(){
 	   check(1);
 	   
    });
    //得到移除按钮的节点
    var delMachine= document.getElementById("addOutBound_foot_button2");	
    //进行按钮不可用操作设置
    delMachine.disabled = true;
    
  //按钮点击事件
	//获取节点
	var beforeObj = null;

	function checkAA(){
		$("#outBound_tableHead_body tr").click(function(){
			if(beforeObj != null){
				beforeObj.css({background: "white" });
			}
			$(this).css({background: "gainsboro" })
			beforeObj = $(this);      
	       
	    
			if(beforeObj != null){
				delMachine.disabled = false;
			}
			
	    });
		
	}
	//移除按钮的点击事件
	$("#addOutBound_foot_button2").click(function(){
		addNum();
		location.href = "/project2/delOutMachine?id="+beforeObj.attr("id");
	});

	
	//添加物品点击事件
	$("#addOutBound_foot_button1").click(function(){
		addNum();
		location.href="/project2/jsp/drugDeliveryManagement/outBound/addOutBound_addItems.jsp";
	});
	
	//添加数量事件
	function addNum(){
		var body1 = $("#outBound_tableHead_body");
		var trArray = body1.children();
		for(var i=0;i<trArray.size();i++){
			var num = trArray.eq(i).children().last().children().last().val();
			var id = trArray.eq(i).attr("id");
			$.post("/project2/addOutMachineNum","num="+num+"&id="+id,function(info){
				
			});
			//location.href="/project2/addOutMachineNum?num="+num+"&id="+id;
			//console.log(trArray.eq(i).children().last().children().last().val());
		}
	}
	
	//出库按钮点击事件
	$("#addOutBound_foot_button3").click(function(){
		addNum();
		//获取选中班级
		var classId = $("#addOutBound_head1_left").find("option:selected").val();
		//alert(classId);
		
		location.href="/project2/outbound?classId="+classId;
	});
	
    //初始化分页组件
    $("#cutPageUL").bootstrapPaginator(ops);
    $("#addOutBound_foot_button1").click(function(){
        window.location = 'addOutBound_addItems.jsp';
    });
   
    //加载所有班级
    $(function(){
    	$.ajax({  
    	    url: "/project2/addOut",  
    	    dataType: "json",  
    	    type: "POST",  
    	    success: function (data) {  
						var str = "";
        		
        		for(var i=0;i<data.length;i++){  			 
        		str += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
        		}
        		$("#addOutBound_head1_left").html(str);
        		recording();
    	    }  
    	});  
    })	
  
    //加载物品
    function check(num){
    	$.post("/project2/addOutMachine","num="+num,function(info){
				    		if(info.list.length==0){
				    			$("#outBound_tableHead_body").html("");
				    			   return;
				    		}
    		
    		var str = "";
    		for(var i=0;i<info.list.length;i++){  			 
       			str += "<tr id='tr"+info.list[i].machineId+"'><td class='addOutBound_body_table_p1' >"+info.list[i].name+
        		"</td><td class='addOutBound_body_table_p2' >"+info.list[i].type+
        		"</td><td class='addOutBound_body_table_p3' >"+info.list[i].prevent+       		
        		"</td><td class='addOutBound_body_table_p4' ><input type='text'  value='"+info.list[i].num+"' /></td></tr>"        		
       		}
    		$("#outBound_tableHead_body").html(str);
       		checkAA();
       		addNum();
       		ifNum();
       		//给分页对象赋值
    		ops.totalPages = info.totalPageNum;
    		ops.currentPage = num;
    		$("#cutPageUL").bootstrapPaginator(ops);
    	},"json");
    }
    
    

    //判断数字的正则
   function ifNum(){
    	   //得到出库按钮的节点进行操作不可用设置
       var OutB = $("#addOutBound_foot_button3");
        //进行操作不可用设置
       
    	//得到所有的tr值
    	$("#outBound_tableHead_body tr input").blur(function(){
    		var numRegex = /^[0-9]*[1-9][0-9]*$/;
    		var bool1 = numRegex.test($(this).val());
    		
    		if(!bool1){
    			 OutB.attr("disabled","true");
    		}
    	});
    	
    }

    function recording(){
        if (localStorage.getItem('addOutBound_head1_left')) {
            $("#addOutBound_head1_left option").eq(localStorage.getItem('addOutBound_head1_left')).prop('selected', true);
        }

        $("#addOutBound_head1_left").on('change', function() {
            localStorage.setItem('addOutBound_head1_left', $('option:selected', this).index());
        });
    }
    
</script>
</html>