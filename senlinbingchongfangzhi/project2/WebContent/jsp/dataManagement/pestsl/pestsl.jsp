<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>pestsl</title>
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
            width: 8%;
        }
        .p2{
            width: 10%;
        }
        .p3{
            width: 30%;
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
            margin-top: 20px;
        }
        .control-label{
            width: 100px;
        }
        .form-horizontal .control-label {
            text-align: left;
        }
        .search{
            margin-left: 40%;
        }
        .bottom-div{
            float: left;
            width: 365px;
            height: 260px;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="page-header">
        <h1>????????????</h1>
    </div>
    <div class="table-1">
        <table class="table table-bordered table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th class="p1">??????</th>
                <th class="p2">??????</th>
                <th class="p3">????????????</th>

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
            <button type="button" class="btn btn-primary" id="add1">???????????????</button>
            <button type="button" class="btn btn-info" id="check">??????????????????</button>
            <br/>
            <br/>
        </div>
    </div>
    <div class="bottom-div">
        <form class="form-horizontal" role="form" action="#" method="post" id="form1">
            <div id="form-top">
                <legend id=""><span class="label label-default" id="span1">?????????????????????</span></legend>
            </div>
            <div class="control-group">
                <label class="control-label" for="input01">?????????</label>
                <input type="text" placeholder="??????????????????" class="input-xlarge" id="pestslName">
            </div>
            <div class="control-group">
                <label class="control-label" for="input02">???   ???</label>
                <input type="text" placeholder="??????????????????" class="input-xlarge" id="hostName">
            </div>
        </form>
        <br/>
        <button type="submit" class="btn btn-primary search" onclick="find()">??????</button>
    </div>

</div>
</body>
<script type="text/javascript">

var pestslName;
var hostName;
var currentTr = null;
function cutPage(pageNo) {
	var str = "";
	var str1 = "";
	$.post("/project2/pestslshowAll",
"pageNo="+pageNo+"&pestslName="+pestslName+"&hostName="+hostName,

function(info){
		
	for(var i = 0 ; i<info.list.length;i++){
		str+="<tr class='proTr' id='tr"+info.list[i].id+"'><td class='p1'>"+info.list[i].name+"</td><td class='p2'>"+info.list[i].host+"</td><td class='p3'>"+info.list[i].harm+"</td></tr>";	
	}
	$("#table").html(str);
	
	for(var i = 1;i<=info.totalPageNum;i++){
		str1+="<li><a onclick='cutPage("+i+")'>"+i+"</a></li>";
	}		
	$("#cutPageUL").html(str1);
	init();
	},"json");
}
//??????????????????ajax??????
(function () {
	cutPage(1);
})();
//???????????????ajax??????
function find() {
	pestslName = $("#pestslName").val();
	hostName = $("#hostName").val();	
	cutPage(1);
};

 function init(){
    	var trArr = document.getElementsByClassName("proTr");
        for(var i = 0;i<trArr.length;i++){
            trArr[i].onclick=function(){
                if(currentTr != null){
                    currentTr.style.backgroundColor='white';
                }
                this.style.backgroundColor='gainsboro';
                currentTr=this;
            }
        }
    }
    var ops = {
        bootstrapMajorVersion:3,//bootstrap??????
        currentPage:1,//????????????
        totalPages:8,//?????????
        numberOfPages:5,//??????????????????????????????
        pageSize:5,
        pageList: [10, 25, 50, 100],
        onPageClicked:function(e,ev,type,page){//page??????????????????
            alert(page);
        }
    };
    //?????????????????????
    $("#cutPageUL").bootstrapPaginator(ops);


    $("#add1").click(function(){
        window.location='/project2/jsp/dataManagement/pestsl/addPestsl.jsp';
    })

  	   //??????????????????
	    $("#check").click(function(){
	    	if(currentTr == null){
	    		alert("??????????????????????????????");
	    		return;
	    	}
	        location.href="/project2/pestslcheck?id="+currentTr.id;
	    });

</script >

</html>