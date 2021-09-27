<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>药剂器械出库管理主界面</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/bootstrap-paginator.min.js"></script>
    <script src="../../../js/DatePicker/WdatePicker.js"></script>
</head>
<style>
    *{
        margin: 0px;
        padding: 0%;
    }
    #outBound_main_div{
        padding-right: 0;
        width: 100%;
        height: 530px;
        border:1px solid black;
        overflow-x: hidden;
        margin: 0px;
        padding: 0%;
    }
    #outBound_one{
        margin: 0px;
        width: 100%;
    }
    #outBound{

        height: 434px;
        /*background-color: #006699;*/
        margin-top: 100px;
        border: 1px solid blue;
    }
   #outBound_main_div_p1{

       font-size: 36px;
       font-weight: 700;
       font-family: STKaiti;
       text-shadow: black;
   }
    #outBound_two  Head{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #outBound_two {
        width: 100%;
        /*height: 172px;*/
        /*overflow-y: scroll;*/
        overflow-x: hidden;
        margin: 0%;
    }


    #outBound_two_table{
        width: 100%;
    }
    #outBound_two_one{
        width: 100%;
        margin: 0;
        padding: 0;
    }
    #outBound_two_table body{
        overflow: auto;
    }
    #outBound_three{
        margin-top: 3%;
        margin-left: 3%;
    }
    #outBound_three_left_button{
        margin-top: 3%;
    }
    #outBound_three_left{
    margin-top: 3%;
    }
    #outBound_three_right1{
        border: 1px solid rgba(0, 0, 0, 0.65);
       /* outline:rgba(43, 41, 60, 0.27) dotted thick;*/
        border-radius:2em;
        height: 227px;
        width: 94%;
        text-align: center;
        font-size: 15px;
    }
    #outBound_three_right1_hr{
        margin-top: 5%;
        margin-bottom: 3%;
    }
    #outBound_three_right1 p{
        font-family: STKaiti;
        font-size: 30px;
    }
    #outBound_three_right1_body_text2 #drugDevice-three_right1_body_text3{
        width: 10%;
    }
    .outBound_tableHead{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #outBound_two table body{

    }
    /*固定表头*/
    #outBound_two_table thead, #outBound_two_table tr {
        display:table;
        width:100%;
        table-layout:fixed;
        text-align: left;
    }
    #outBound_two_table tbody{
        display: block;
        height:127px;
        overflow:auto;
    }
    #outBound_two_table{
        border:2px solid gray;
    }
    #outBound_two_table thead {
        width: 100%;
    }
    .outBound_two_table_p1{
        width: 20%;
    }
    .outBound_two_table_p2{
        width: 30%;
    }
    .outBound_two_table_p3{
        width: 20%;
    }
    #outBound_two_table_p3_hr{
        width:20%;
    }
</style>

<body>
<div class="container-fluid" id="outBound_main_div">

                <div class="row" id="outBound_one">
                        <div class="col-sm-6 col-sm-offset-3">
                            <p id="outBound_main_div_p1">药剂器械出库管理</p>
                         </div>
                </div>

                <div class="row" id="outBound_two">
                    <div class="container" id="outBound_two_one">
                        <table class="table table-bordered table-striped table-hover table-condensed" id="outBound_two_table">
                            <thead>
                            <tr class="outBound_tableHead"><th class="outBound_two_table_p1">日期</th><th class="outBound_two_table_p2">领用小班</th><th id="outBound_two_table_p3_hr" class="outBound_two_table_p3">出库人</th></tr>
                            </thead>

                                <tbody id="toby1">
                                    
                                </tbody>
                        </table>

                    </div>
                </div>
                <div class="row" id="outBound_three">
                    <div class="col-sm-6" id="outBound_three_left">
                        <ul id="cutPageUL" class="pagination"></ul>
                        <div id="outBound_three_left_button">
                            <a id="outBound_three_left_button1" class="btn btn-success btn btn-primary btn-lg">添加出库信息</a>
                            <input type="button" class="btn btn-success btn btn-primary btn-lg" id="outBound_three_left_button2"  value="查看出库信息"/>
                        </div>

                    </div>
                    <div class="col-sm-6" id="outBound_three_right">
                        <div class="row">
                            <div class="col-sm-7" id="outBound_three_right1">
                                <p>查询出库信息</p><hr id="outBound_three_right1_hr"/>
                                    <div id="outBound_three_right1_body">
                                        <div class="container-fluid">
                                           
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div class="form-group">
                                                            <div class="row">
                                                            <label for="outBound_three_right1_body_startDay" class="col-sm-4">起始日期</label>
                                                                <input type="text"  style="pointer-events: none; " name="outBound_three_right1_body_startDay" class="col-sm-6" id="outBound_three_right1_body_startDay" class="form-control" placeholder="请输入起始日期"/>
                                                            <img src="../../../js/DatePicker/skin/datePicker.gif" alt="" style="cursor: pointer" onclick="WdatePicker({el:'outBound_three_right1_body_startDay'})"/>
                                                            </div>
                                                            <br/>
                                                            <div class="row">
                                                                <label for="outBound_three_right1_body_endDay" class="col-sm-4">结束日期</label>
                                                                    <input type="text"  style="pointer-events: none; "  class="col-sm-6" name="outBound_three_right1_body_endDay" id="outBound_three_right1_body_endDay" class="form-control" placeholder="请输入起始日期"/>
                                                                <img src="../../../js/DatePicker/skin/datePicker.gif" alt="" style="cursor: pointer" onclick="WdatePicker({el:'outBound_three_right1_body_endDay'})"/>
                                                            </div> <br/>
                                                            <div class="row">
                                                                <label for="outBound_three_right1_body_endText" class="col-sm-4">领用小班</label>
                                                                <input type="text" id="outBound_three_right1_body_endText" class="col-sm-6"/>

                                                            </div>

                                                              <div class="row">
                                                                <input type="button" class="btn btn-info" id="outBound_three_right1_body_button" value="查询"/>
                                                            </div>


                                                        </div>
                                                    </div>
                                                </div>
                                        
                                        </div>
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
    //初始化分页组件
    $("#cutPageUL").bootstrapPaginator(ops);
    //获取输入框内容
    var starDate;
    var endDate;
    var className;
	  //得到按钮的节点
    var buttonByFind = document.getElementById("outBound_three_left_button2");
    	
    //进行按钮不可用操作设置
    buttonByFind.disabled = true;
    //加载页面自启动
    $(function(){   	
    	check(1);
    });
    //点击查询按钮时动作
    $("#outBound_three_right1_body_button").click(function(){
    	starDate = $("#outBound_three_right1_body_startDay").val();
    	endDate = $("#outBound_three_right1_body_endDay").val()
    	className = $("#outBound_three_right1_body_endText").val();
    	check(1);
    });
    
    //提交动态事件
 	function check(num){
       	
    	$.post("/project2/outServlet","num="+num+"&starDate="+starDate+"&endDate="
    			+endDate+"&className="+className,function(info){
				    		if(info.list.length==0){
				    			$("#toby1").html("");
				    			   return;
				    		}
    		
    		var str = "";
    		for(var i=0;i<info.list.length;i++){
    		str += "<tr id='tr"+info.list[i].id+"'><td class='outBound_two_table_p1' >"+info.list[i].date+
    		"</td><td class='outBound_two_table_p2' >"+info.list[i].className+
    		"</td><td class='outBound_two_table_p3' >"+info.list[i].userName+
    		"</td></tr>"
    		}
    		$("#toby1").html(str);
    		checkAA();
    		//给分页对象赋值
    		ops.totalPages = info.totalPageNum;
    		ops.currentPage = num;
    		$("#cutPageUL").bootstrapPaginator(ops);
    	},"json");
    }
    
 	//按钮点击事件
	//获取节点
	var beforeObj = null;
	var idd = null;
	function checkAA(){
		$("#toby1 tr").click(function(){
			if(beforeObj != null){
				beforeObj.css({background: "white" });
			}
			$(this).css({background: "gainsboro" })
			beforeObj = $(this);
	       //获取Id;
	       
	    
			if(beforeObj != null){
				buttonByFind.disabled = false;
			}
			
	    });
		
	}
    $("#outBound_three_left_button1").click(function(){
    	location.href="/project2/jsp/drugDeliveryManagement/outBound/addOutBound.jsp";
    });
    $("#outBound_three_left_button2").click(function(){
   	 	location.href="/project2/outFindOne?id="+beforeObj.attr("id");
    });

</script >
</html>