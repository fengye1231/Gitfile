<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加物品</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/bootstrap-paginator.min.js"></script>

</head>
<style>
    *{
        margin: 0;
        padding: 0;
        font-family: STKaiti;
        font-size: 16px;
    }
    #addOutBoundadd_Items {
        padding-right: 0;
       width: 100%;
        height: 530px;
        border:1px solid black;
        overflow-x: hidden;
    }
    #addOutBound_addItems_one {
        /*margin-top: 50px;*/
        width: 100%;
        margin: 0px;
        /*height: 172px;*/
        /*overflow-y: scroll;*/
    }
    #addOutBound_addItems_one_container{
        width: 100%;
        margin: 0px;
        padding: 0px;

    }
    #addOutBound_addItems_one_table{
        width: 100%;
        margin: 0px;
        height: 169px;
    }

    .addOutBound_addItems_tableHead{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #addOutBound_addItems_three_text1{
        margin-left: 8px;
    }
    #addOutBound_addItems_three_row1{
    margin-top: 20px;

    }
    #addOutBound_addItems_three_row2{
        margin-top: 20px;

    }
    #addOutBound_addItems_three_row3{
        margin-top: 36px;

    }
    #addOutBound_addItems_three_sel1{
        margin-left: 6%;
        height: 39px;
        width: 100%;
        font-size: 22px;

    }
    #addOutBound_addItems_three_sel2{
        margin-left: 6%;
        height: 39px;
        width: 100%;
        font-size: 22px;
    }
    #addOutBound_addItems_three_button1{
        width: 100px;
        height: 48px;
        margin-left: 30px;
        font-size: 29px;
    }
    #addOutBound_addItems_three_button2{
        width: 100px;
        height: 48px;
        margin-left: 30px;
        font-size: 29px;
    }
    /*固定表头*/
    #addOutBound_addItems_one_table thead, tbody tr {
        display:table;
        width:100%;
        table-layout:fixed;
        text-align: left;
    }
    #addOutBound_addItems_one_table tbody{
        display: block;
        height:140px;
        overflow:auto;
    }
    #addOutBound_addItems_one_table{
        border:2px solid gray;
        width: 100%;
        margin-top: 3%;
    }
    #addOutBound_addItems_one_table thead {
        width: 100%;
    }
    .addOutBound_addItems_one_table_p0{
        width: 4%;
    }
    .addOutBound_addItems_one_table_p1{
        width: 12%;
    }
    .addOutBound_addItems_one_table_p2{
        width: 6%;
    }
    .addOutBound_addItems_one_table_p3{
        width: 7%;
    }
    .addOutBound_addItems_one_table_p4{
        width: 35%;
    }

    #addOutBound_addItems_one_container{
          margin: 0;
            padding-right: 0px;

    }
   #addOutBound_addItems_one_p4_hr{
        width: 35%;
    }

</style>

<body>
<div class="container-fluid">
            <div class="row">
                <div id="addOutBoundadd_Items">
                    <div class="row" id="addOutBound_addItems_one">
                        <div class="container" id="addOutBound_addItems_one_container">
                            <table class="table table-bordered table-striped table-hover table-condensed"  id="addOutBound_addItems_one_table">
                                <thead>
                                    <tr class="addOutBound_addItems_tableHead"><th class="addOutBound_addItems_one_table_p0"></th><th class="addOutBound_addItems_one_table_p1">物品名称</th><th class="addOutBound_addItems_one_table_p2">类型</th><th class="addOutBound_addItems_one_table_p3">防治类型</th><th id="addOutBound_addItems_one_p4_hr" class="addOutBound_addItems_one_table_p4">主要用途</th></tr>
                                </thead>
                                <tbody id="addOutBound_addItems_tableHead_body">
                                    <!--  <tr><td><input type="checkbox" name="hobby" value="bushu" class="addOutBound_addItems_one_p0"/></td><td class="addOutBound_addItems_one_p1">捕鼠笼</td><td class="addOutBound_addItems_one_p2">器械</td><td class="addOutBound_addItems_one_p3">鼠害</td><td class="addOutBound_addItems_one_p4">将老鼠关进笼中</td></tr>
                                    <tr><td><input type="checkbox" name="hobby" value="boerduoye" class="addOutBound_addItems_one_p0"/></td><td class="addOutBound_addItems_one_p1">波尔朵液</td><td class="addOutBound_addItems_one_p2">药剂</td><td class="addOutBound_addItems_one_p3">病害</td><td class="addOutBound_addItems_one_p4">治疗枯萎病</td></tr>
                                    <tr><td><input type="checkbox" name="hobby" value="boerduoye" class="addOutBound_addItems_one_p0"/></td><td class="addOutBound_addItems_one_p1">波尔朵液</td><td class="addOutBound_addItems_one_p2">药剂</td><td class="addOutBound_addItems_one_p3">病害</td><td class="addOutBound_addItems_one_p4">治疗枯萎病</td></tr>
                                    <tr><td><input type="checkbox" name="hobby"  value="boerduoye" class="addOutBound_addItems_one_p0"/></td><td class="addOutBound_addItems_one_p1">波尔朵液</td><td class="addOutBound_addItems_one_p2">药剂</td><td class="addOutBound_addItems_one_p3">病害</td><td class="addOutBound_addItems_one_p4">治疗枯萎病</td></tr>
                                    <tr><td><input type="checkbox" name="hobby" value="boerduoye" class="addOutBound_addItems_one_p0"/></td><td class="addOutBound_addItems_one_p1">波尔朵液</td><td class="addOutBound_addItems_one_p2">药剂</td><td class="addOutBound_addItems_one_p3">病害</td><td class="addOutBound_addItems_one_p4">治疗枯萎病</td></tr>
                                    <tr><td><input type="checkbox" name="hobby" value="boerduoye" class="addOutBound_addItems_one_p0"/></td><td class="addOutBound_addItems_one_p1">波尔朵液</td><td class="addOutBound_addItems_one_p2">药剂</td><td class="addOutBound_addItems_one_p3">病害</td><td class="addOutBound_addItems_one_p4">治疗枯萎病</td></tr>
                                -->
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-2" id="addOutBound_body_foot">
                            <ul id="cutPageUL" class="pagination"></ul>
                        </div>
                    </div>

                        <div id="addOutBound_addItems_three">
                            <form action="#">
                                <div class="row" id="addOutBound_addItems_three_row1">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label for="addOutBound_addItems_three_text1">物品名称</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" id="addOutBound_addItems_three_text1"/>
                                    </div>
                                    <div class="col-sm-1 col-sm-offset-1">
                                         <label for="addOutBound_addItems_three_sel1">类型</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="sel" id="addOutBound_addItems_three_sel1"  class="col-sm-4  btn btn-default btn-sm btn-primary">
                                            <option value="药剂">药品</option>
                                            <option value="器械">器械</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row"id="addOutBound_addItems_three_row2">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label for="addOutBound_addItems_three_sel2">防治类型</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="sel" id="addOutBound_addItems_three_sel2" class="col-sm-4  btn btn-default btn-sm btn-primary">
                                            <option value="鼠害">鼠害</option>
                                            <option value="虫害">虫害</option>
                                            <option value="病害">病害</option>
                                        </select>
                                    </div>
                                </div>
                            </form>

                                <div class="row" id="addOutBound_addItems_three_row3">
                                    <div class="col-sm-2 col-sm-offset-2">
                                    	<input type="button" class="btn btn-info" id="addOutBound_addItems_three_button1" value="查询" />
                                    </div>

                                    <div class="col-sm-2 col-sm-offset-3">
                                    	<input type="button" class="btn btn-info" id="addOutBound_addItems_three_button2" value="领取" />
                                    </div>
                                </div>

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
        	check(page);
        }
    };
    $("#cutPageUL").bootstrapPaginator(ops);

 
    //获取输入框内容
    var nameMachine;
   	var defeat;
	var kind;
    //按钮查询事件    
    $("#addOutBound_addItems_three_button1").click(function(){
    	nameMachine="";
    	defeat="";
    	kind="";
    	nameMachine = $("#addOutBound_addItems_three_text1").val();
    	 defeat = $("#addOutBound_addItems_three_sel2").val();
    	 kind = $("#addOutBound_addItems_three_sel1").val();
    	 check(1);
    });
    
    function check(num){
    	var str = "";
    	$.post("/project2/machine","pageNo="+num+"&nameMachine="+nameMachine+"&defeat="+defeat+
    			"&kind="+kind,function(info){
				    		if(info.list.length==0){
				    			$("#addOutBound_addItems_tableHead_body").html("");
				    			   return;
				    		}
    		for(var i=0;i<info.list.length;i++){
    			str+="<tr><td class='addOutBound_addItems_one_table_p0' ><input  type='checkbox' name='hobby' value='"+info.list[i].id+"'  /></td><td class='addOutBound_addItems_one_table_p1' >"+info.list[i].name+
				"</td><td class='addOutBound_addItems_one_table_p2' >"+info.list[i].defeat+
				"</td><td class='addOutBound_addItems_one_table_p3' >"+info.list[i].kind+
				"</td><td class='addOutBound_addItems_one_table_p4' >"+info.list[i].use+
				"</td></tr>"
    		}
    		
    		$("#addOutBound_addItems_tableHead_body").html(str);
    		//赋值给分页对象
    		ops.totalPages = info.totalPageNum;
    		ops.currentPage = num;
    		$("#cutPageUL").bootstrapPaginator(ops);
    	},"json");
    	
    	
    }

    var str="";
    //领取按钮事件
    $("#addOutBound_addItems_three_button2").click(function(){
    	$("input[name='hobby']:checked").each(function(){
    		str += $(this).val()+"j";
    		
    	});
    	
    	if(str==""){
    		alert("你还没有选中任何内容");
    	}else{
    		location.href="/project2/addOutBound_addItems?id="+str;
    	}
    	
    	
    		 //str += $(".addOutBound_addItems_one_p0_in :checked").attr("value");
    		
    		
    });
    
  //页面加载时查询全部
	$(function(){
		check(1);
		
	});

  
    
</script>
</html>