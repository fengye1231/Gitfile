<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看出库信息</title>
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

    }
    #findByOutBound{

        padding-right: 0;
        width: 100%;
        height: 530px;
        /*border:1px solid black;*/
        overflow-x: hidden;
    }
    #findByOutBound_one{
        font-family: STKaiti;
    }
    #findByOutBound_one_p1{
        font-size: 36px;
        font-weight: 700;
        text-shadow: black;
       text-align: center;
    }
    #findByOutBound_one_foot{
        margin-top: 40px;
        font-size: 17px;
    }
    #findByOutBound_two{
        margin: 0;
        padding-right: 0;
        margin-top: 40px;
        width: 98%;
        height: 172px;
        /*overflow-y: scroll;*/

    }
    .findByOutBound_tableHead{
        background-color: rgba(43, 41, 60, 0.27);
    }
    #findByOutBound_button{
    margin-top: 60px;
        width: 20%;
        height: 75px;
    }
    /*固定表头*/
    #findByOutBound_two_table thead, tbody tr {
        display:table;
        width:100%;
        table-layout:fixed;
        text-align: left;
    }
    #findByOutBound_two_table tbody{
        display: block;
        height:127px;
        overflow:auto;
    }
    #findByOutBound_two_table{
        border:2px solid gray;
        width: 100%;
    }
    #findByOutBound_two_table thead {
        width: 100%;
    }

    .findByOutBound_two_table_p1{
        width: 15%;
    }
    .findByOutBound_two_table_p2{
        width: 30%;
    }
    .findByOutBound_two_table_p3{
        width: 20%;
    }
    .findByOutBound_two_table_p4{
        width: 22%;
    }

    #findByOutBound_two{
        margin: 0;
        padding-right: 0px;

    }
    #findByOutBound_two_table_p4_hr{
        width: 17%;
    }
    #cutPageUL{
       margin-left: 40px;
    }

</style>

<body>
<div class="container-fluid">
    <div class="row">
                <div id="findByOutBound">
               
                    <div class="outBound-one" id="findByOutBound_one">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3" id="findByOutBound_one_p1">
                                <p>查看出库信息</p>
                            </div>
                        </div>
                           <input type="hidden" value="${bean.id}"/>
                        <div class="row" id="findByOutBound_one_foot">
                            <div class="col-sm-3 col-sm-offset-1">
                                <span>领用小班</span>
                                <span class="col-sm-offset-1"><j:out value="${bean.className}"></j:out></span>
                            </div>
                            <div class="col-sm-3 col-sm-offset-1">
                                <span>出库人</span>
                                <span class="col-sm-offset-1">
									<j:out value="${bean.userName}"></j:out>
								</span>
                            </div>
                            <div class="col-sm-3 col-sm-offset-1">
                                <span>领用日期</span>
                                <span class="col-sm-offset-1">
									<j:out value="${bean.date}"></j:out>
								</span>
                            </div>
                        </div>

                    </div>

                    <div class="row" >
                        <div class="container" id="findByOutBound_two">
                            <table class="table table-bordered table-striped table-hover table-condensed" id="findByOutBound_two_table">
                                <thead>
                                <tr class="findByOutBound_tableHead"><th class="findByOutBound_two_table_p1">物品名称</th><th class="findByOutBound_two_table_p2">类型</th><th class="findByOutBound_two_table_p3">防治类型</th><th id="findByOutBound_two_table_p4_hr" class="findByOutBound_two_table_p4">领用</th></tr>
                                </thead>
                                <tbody id="body1">
                         
                                </tbody>
                            </table>
                        </div>
                        <div>
                         	<ul id="cutPageUL" class="pagination"></ul>
                         </div>
                    </div>
                    <div class="row">
                        <button class="col-sm-offset-5 btn btn-info"  id="findByOutBound_button">确定</button>
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
    $("#findByOutBound_button").click(function(){
        window.location = "outBound_main.jsp"
    });
    //加载页面自启动
    $(function(){   	
    	check(1);
    });
    
    //提交动态事件
 	function check(num){
 	    	//得到id
	 	   var id = $("input").val();
	    	$.post("/project2/outFindTwo","num="+num+"&id="+id,function(info){
    		var str = "";
    		for(var i=0;i<info.list.length;i++){
    		str += "<tr id='tr"+info.list[i].id+"'><td>"+info.list[i].name+
    		"</td><td>"+info.list[i].type+
    		"</td><td>"+info.list[i].prevent+
    		"</td><td>"+info.list[i].num+
    		"</td></tr>"
    		}
    		$("#body1").html(str);
    		
    		//给分页对象赋值
    		ops.totalPages = info.totalPageNum;
    		ops.currentPage = num;
    		$("#cutPageUL").bootstrapPaginator(ops);
 
    	},"json");
    }

</script >
</html>