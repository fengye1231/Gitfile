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
            <h2>????????????</h2>
        </div>
        <div class="table-1">
            <table class="table table-bordered table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th class="p1">??????</th>
                    <th class="p2">????????????</th>
                    <th class="p3">??????</th>
                    <th class="p4">??????</th>
                    <th class="p5">??????</th>
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
                    <button type="button" class="btn btn-success" id="add1">????????????</button>
                    <button type="button" class="btn btn-default" id="check">??????????????????</button>
                    <br/>
                    <br/>
                    <button type="button" class="btn btn-default" id="update1">??????????????????</button>
                    <button type="button" class="btn btn-danger" id="del1">????????????</button>
                </div>
            </div>
            <div class="bottom-div">
                <form class="form-horizontal" id="form1">
                    <div id="form-top">
                        <legend><span class="label label-default" id="span1">?????????????????????</span></legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input01">????????????</label>
                        <input type="text" placeholder="?????????????????????" class="input-xlarge" id="name" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" >
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input02">??????</label>
                        <input type="text" placeholder="?????????????????????" class="input-xlarge" id="special" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" >
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input03">????????????</label>
                        <input type="text" placeholder="?????????????????????" class="input-xlarge" id="workPlace" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" >
                    </div>
	                <br/>
	                <input type="button" class="btn btn-primary search" onclick="find()" value="??????" />
	            </form>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">

	  //bootstrap??????????????????
	  var ops = {
	      bootstrapMajorVersion:3,//bootstrap??????
	      currentPage:1,//????????????
	      totalPages:8,//?????????
	      numberOfPages:5,//??????????????????????????????
	      pageSize:5,
	      pageList: [10, 25, 50, 100],
	      onPageClicked:function(e,ev,type,page){//page??????????????????
	          check(page);
	      }
	  };
	  
	 
	  //?????????????????????????????????
	  (function(){
	   check(1);
	  })();

	  
	  //?????????????????????
	  function find(){
	  var name = $("#name").val();
	  var special = $("#special").val();
	  var workPlace = $("#workPlace").val();
	  check(1,name,special,workPlace);
	  }
	  
	  
	  //ajax?????????????????????
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
		
			   		
		   		//???bootstrap??????????????????
		   		ops.totalPages = info.totalPageNum;
		   		ops.currentPage = num;
		   		$("#cutPageUL").bootstrapPaginator(ops);		
			},"json");	   
	  }
	  
	  
		//?????????????????????????????????id
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
	
	
	  //??????????????????
	  $("#add1").click(function(){
	      location.href='add.jsp';
	  })
	
	  //??????????????????
	  $("#check").click(function(){
	   if(thisId == 0){
		   alert("?????????????????????????????????????????????")
	   }else{
		   location.href="/project2/findExpert?id=" + thisId;
	   }       
	  })
	
	  //??????????????????
	  $("#update1").click(function(){
	   if(thisId == 0){
		   alert("?????????????????????????????????????????????")
	   }else{
		   location.href="/project2/updateExpert1?id=" + thisId;
	   }
	      
	  })
	  
	  //??????????????????
	  $("#del1").click(function(){
		  if(thisId == 0){
			   alert("?????????????????????????????????????????????")
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