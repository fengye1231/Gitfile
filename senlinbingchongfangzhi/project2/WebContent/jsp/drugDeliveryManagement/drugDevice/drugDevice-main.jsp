<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>药剂/器械一览</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>
    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <script src="../../../js/bootstrap-paginator.min.js"></script>

</head>
<style>
  *{
      padding: 0;
      margin: 0;
  }
  #drugDevice_main{
      /*background-image: url("../../../img/drugDevice.jpg");*/
      /*background-size:782px 530px;*/
     padding-right: 0;
      width: 100%;
      height: 530px;
      border:1px solid black;

      overflow-x: hidden;
  }
    #drugDevice_one{
        margin-top: 2%;
        text-align: center;
    }
    #drugDevice_one_p1{
        font-size: 36px;
        font-weight: 700;
        font-family: STKaiti;
        text-shadow: black;
    }

  #drugDevice-two{
      width: 100%;
      height: 172px;
      /*overflow-y: scroll;*/
      margin: 0;

  }
  #drugDevice-two-1{
      margin: 0;
      padding: 0;
  }
  .tableHead{
      background-color: rgba(43, 41, 60, 0.27);
  }
    #drugDevice-two-table{
      border: 1px solid black;
        margin: 0;
        padding-right: 0;
    }
    #drugDevice-two-1{
        margin: 0;
    }
  #drugDevice-two-table body{
      overflow: auto;
  }
  #drugDevice-three{
      margin-top: 7px;
  }
    #drugDevice-three_left{
        margin-top: 20px;
    }
    #drugDevice-three_right1{
        margin-top: 29px;
        border: 1px solid rgba(0, 0, 0, 0.65);
        /*outline:rgba(43, 41, 60, 0.27) dotted thick;*/
        border-radius:2em;
        width: 89%;
        height: 219px;
    }
    #drugDevice-three_right{
        margin-top: 3%;
    }
    #drugDevice-three_right1_p2{
        font-weight: 700;
        font-family: STKaiti;
        font-size: 16px;

    }
    #drugDevice-three_right1{
        text-align: center;
    }
    #drugDevice-three_right1_body_row1{
         margin-top: 10px;

    }
    #drugDevice-three_right1_body_text2{
        margin-left: 6%;
        height: 36px;
        width: 48%;
        font-size: 17px;
        text-align: center;
    }
  #drugDevice-three_right1_body_text2 option{

  }
    #drugDevice-three_right1_body_text3{
        margin-left: 6%;
        height: 36px;
        width: 48%;
        font-size: 17px;

    }

    #drugDevice-three_right1_body_label2{
        margin-top: 2%;
    }
    #drugDevice-three_foot_button{
        margin-top: 2%;
    }
    #drugDevice-three_left_button{
        margin-top: 46px;
        width: 48%;

    }
    #drugDevice-three_foot_button{
        margin-top: 12px;
        width: 40%;

    }
    /*固定表头*/

  #drugDevice-two-table thead, #drugDevice-two-table tr {
      display:table;
      width:100%;
      table-layout:fixed;
      text-align: left;
  }
 #drugDevice-two-table tbody{
      display: block;
      height:127px;
      overflow:auto;
  }
  #drugDevice-two-table{
      width:98%;
      border:2px solid gray;
  }
  #drugDevice-two-table thead {
      width: 100%;
  }
  .drugDevice-two-table_p1{
      width: 7%;
  }
  .drugDevice-two-table_p2{
      width: 4%;
  }
  .drugDevice-two-table_p3{
      width: 4%;
  }
  .drugDevice-two-table_p4{
      width: 15%;
  }
    #drugDevice-two-table_p4_hr{
        width: 15%;
    }
</style>

<body>
<div class="container-fluid" id="drugDevice_main">
    <div class="row">
                <div class="row" id="drugDevice">
                        <div class="col-sm-6 col-sm-offset-2" id="drugDevice_one">
                                    <p id="drugDevice_one_p1">药剂/器械一览</p>
                        </div>
                </div>
                    <div class="row" id="drugDevice-two">
                        <div class="container col-sm-12" id="drugDevice-two-1">
                            <table class="table table-bordered table-striped table-hover table-condensed" id="drugDevice-two-table">
                                <thead>
                                    <tr class="tableHead"><th class="drugDevice-two-table_p1">名称</th><th class="drugDevice-two-table_p2">防治类型</th><th class="drugDevice-two-table_p3">类别</th><th id="drugDevice-two-table_p4_hr" class="drugDevice-two-table_p4">主要用途</th></tr>
                                </thead>
                                <tbody id="body1">
                                   
                                </tbody>
                            </table>

                        </div>
                    </div>

                        <div class="row" id="drugDevice-three">

                            <div class="col-sm-5 col-sm-offset-1" id="drugDevice-three_left">
                                <ul id="cutPageUL" class="pagination"></ul>

                            <div class="row" id="drugDevice-three_right">
                                    <button class="col-sm-4 col-sm-offset-3 btn btn-success btn btn-primary btn-lg" id="drugDevice-three_left_button">添加药剂</button>
                            </div>

                            </div>
                            <div class="col-sm-6" >
                                <div class="row">
                                 <div class="col-sm-10 " id="drugDevice-three_right1">
                                     <div class="row" id="drugDevice-three_right1_body_row1">
                                         <p id="drugDevice-three_right1_p2">查询药剂信息</p>
                                     </div>

                                    <div id="drugDevice-three_right1_body">
                                        <form action="#">
                                            <div class="row"  >
                                                <label class="col-sm-4" for="drugDevice-three_right1_body_text1">药品名称</label>
                                                <input type="text" class="col-md-6" id="drugDevice-three_right1_body_text1"/>
                                            </div>
                                            <br/>
                                            <div class="row">

                                                <label class="col-sm-4" for="drugDevice-three_right1_body_text2">防治类型</label>

                                                <select name="sel" class="col-sm-4  btn btn-default btn-sm btn-primary" id="drugDevice-three_right1_body_text2">
                                                    <option value="鼠害">鼠害</option>
                                                    <option value="虫害">虫害</option>
                                                    <option value="病害">病害</option>
                                                </select>
                                            </div>

                                            <div class="row"  id="drugDevice-three_right1_body_label2">
                                                <label class="col-sm-4" for="drugDevice-three_right1_body_text3">类别</label>
                                                <select name="sel" class="col-sm-4 btn btn-default btn-sm btn-primary" id="drugDevice-three_right1_body_text3">
                                                    <option value="药剂">药剂</option>
                                                    <option value="器械">器械</option>
                                                </select>
                                            </div>
                                            <div class="row">
                                            	 <input type="button" class="btn btn-info" id="drugDevice-three_foot_button" value="查询" />
                                            </div>
                                        </form>
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
        numberOfPages:4,//页面最多显示的页码数
        pageSize:4,
        pageList: [10, 25, 50, 100],
        onPageClicked:function(e,ev,type,page){//page为点中的页码
        	check(page);
        }
    };
    //初始化分页组件
    $("#cutPageUL").bootstrapPaginator(ops);
    $("#drugDevice-three_left_button").click(function(){
        window.location = 'addDrugDevice.jsp';
    })
    //获取输入框内容
    var nameMachine;
   	var defeat;
	var kind;
    //按钮查询事件
    
    $("#drugDevice-three_foot_button").click(function(){
    	nameMachine = $("#drugDevice-three_right1_body_text1").val();
    	 defeat = $("#drugDevice-three_right1_body_text2").val();
    	 kind = $("#drugDevice-three_right1_body_text3").val();
    	 check(1);
    });
    
    function check(num){
    	var str = "";
    	$.post("/project2/machine","pageNo="+num+"&nameMachine="+nameMachine+"&defeat="+defeat+
    			"&kind="+kind,function(info){
				    		if(info.list.length==0){
				    			$("#body1").html("");
				    			   return;
				    		}
    		
    		for(var i=0;i<info.list.length;i++){
    			str+="<tr><td class='drugDevice-two-table_p1' >"+info.list[i].name+
				"</td><td class='drugDevice-two-table_p2' >"+info.list[i].defeat+
				"</td><td class='drugDevice-two-table_p3' >"+info.list[i].kind+
				"</td><td class='drugDevice-two-table_p4' >"+info.list[i].use+
				"</td></tr>"
    		}
    		
    		$("#body1").html(str);
    		//赋值给分页对象
    		ops.totalPages = info.totalPageNum;
    		ops.currentPage = num;
    		$("#cutPageUL").bootstrapPaginator(ops);
    			},"json");
    	
    	
    	
    }
  //页面加载时查询全部
	$(function(){
		check(1);
		
	});
    
    
</script >
</html>