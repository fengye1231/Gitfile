<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>日志主界面</title>
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
    }
    #outBound_main_div{
        padding-right: 0;
        width: 782px;
        height: 530px;
        border:1px solid black;
        overflow-x: hidden;
    }
    #outBound{
        height: 434px;
        margin-top: 100px;
        border: 1px solid blue;
    }
    #outBound_main_div_p1{
        font-size: 36px;
        font-weight: 700;
        text-shadow: black;
    }
    #outBound_two  Head{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #outBound_two {

        height: 172px;
        overflow-y: scroll;
        overflow-x: hidden;
    }


    #outBound_two_table{
        width: 782px;
    }
    #outBound_two_one{
        margin: 0;
        padding: 0;
    }
    #outBound_two_table body{
        overflow: auto;
    }
    #outBound_three{
        margin-top: 30px;
    }
    #outBound_three_left_button{
        margin-top: 20px;
    }
    #outBound_three_left{
        margin-top: 10px;
    }
    #outBound_three_right1{
        border-radius:2em;
        height: 227px;
        width: 357px;
        text-align: center;
    }
    #outBound_three_right1 p{
        font-family: STKaiti;
        font-size: 21px;
    }
    #outBound_three_right1_body_text2 #drugDevice-three_right1_body_text3{
        width: 74px;
    }
    .outBound_tableHead{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #outBound_two table body{

    }
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
        width: 98%;
    }
    .outBound_two_table_p1{
        width: 15%;
    }
    .outBound_two_table_p2{
        width: 30%;
    }
    .outBound_two_table_p3{
        width: 20%;
    }
</style>

<body>
<div class="container-fluid" id="outBound_main_div">

    <div class="row" id="outBound_one">
        <div class="col-sm-6 col-sm-offset-3">
            <p id="outBound_main_div_p1">日志信息</p>
        </div>
    </div>

    <div class="row" id="outBound_two">
        <div class="container" id="outBound_two_one">
            <table class="table table-bordered table-striped table-hover table-condensed" id="outBound_two_table">
                <thead>
                <tr class="outBound_tableHead"><th class="p1">日志内容</th><th class="p2">日期</th></tr>
                </thead>
                <tbody id="tbody1">

                </tbody>
            </table>

        </div>
    </div>
    <div class="row" id="outBound_three">
        <div class="col-sm-5 col-md-offset-1" id="outBound_three_left">
            <ul id="cutPageUL" class="pagination"></ul>
        </div>
        <div class="col-sm-6" id="outBound_three_right">
            <div class="row">
                <div class="col-sm-7" id="outBound_three_right1">
                    <p>查询日志信息</p><hr/>
                    <div id="outBound_three_right1_body">
                        <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <div class="row">
                                                <label for="outBound_three_right1_body_startDay" class="col-sm-4">起始日期</label>
                                                <input type="text" name="outBound_three_right1_body_startDay" class="col-sm-6" id="startDay" class="form-control" placeholder="请输入起始日期"/>
                                                <img src="../../../js/DatePicker/skin/datePicker.gif" alt="" style="cursor: pointer"  onclick="WdatePicker({el:'startDay'})"/>
                                            </div>
                                            <br/>
                                            <div class="row">
                                                <label for="outBound_three_right1_body_endDay" class="col-sm-4">结束日期</label>
                                                <input type="text" class="col-sm-6" name="outBound_three_right1_body_endDay" id="endDay" class="form-control" placeholder="请输入起始日期"/>
                                                <img src="../../../js/DatePicker/skin/datePicker.gif" alt="" style="cursor: pointer"  onclick="WdatePicker({el:'endDay'})"/>
                                            </div> <br/>
                                            <div class="row">
                                                <input type="button" onclick="find()"  value="查找" class="btn btn-success btn btn-primary btn-lg"/>
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
        totalPages:5,//总页数
        numberOfPages:5,//页面最多显示的页码数
        pageSize:5,
        pageList: [10, 25, 50, 100],
        onPageClicked:function(e,ev,type,page){//page为点中的页码
            check(page);
        }
    };
    
    $(function(){
    	check(1);
    });
    
    var starDate;
    var endDate;
    
    function find(){
    	starDate = $("#startDay").val();
    	endDate = $("#endDay").val();
    	check(1,starDate,endDate);
    	console.log(starDate+'       '+endDate);
    }
    
	   //ajax查询
	   function check(num,stardate,enddate){
		   var str="";
		   $.post("/project2/log",
				   "pageNo=" + num + "&starDate=" + starDate + "&endDate=" + endDate,
				   function(info){
			   		for(var i = 0;i<info.list.length;i++){
			   			str += "<tr onclick='change("+ info.list[i].id +")'><td class='p1'>"+info.list[i].log+
						"</td><td class='p2'>"+info.list[i].date+
						"</td></tr>";
			   		}
			   		$("#tbody1").html(str);

			   		
			   		//给bootstrap赋值
			   		ops.totalPages = info.totalPageNum;
			   		ops.currentPage = num;
			   		$("#cutPageUL").bootstrapPaginator(ops);
		
		   		},"json");	   
	   }


</script >
</html>