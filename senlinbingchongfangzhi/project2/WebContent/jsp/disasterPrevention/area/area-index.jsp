<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css">

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script type="javascript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap-paginator.min.js"></script>


</head>


 <style>
        *{
            margin: 0px;
            padding: 0px;
        }
        div{

            border: none;
        }
        .span1{
            display: inline-block;
            line-height: 70px;
            width: 100%;
            text-align:center ;
            font-size: 30px;

        }
        .up{
            margin-top: 20px;
            margin-left: 15px;
            width: 120px;
        }
        .down{

            margin-top: 20px;
            margin-left: 15px;
            width: 120px;
        }
        #cutPageUL{
            font-size: 10px;

        }
        .form-horizontal div{
            height: auto;
            border: none;
        }
        label{
            font-size: 15px;
        }
        .form-control{
            height:30px;
            width: auto;
        }
        form{
            display: inline-block;
            float: right;
        }
        .div1{
            display: block;
           
        }
    </style>
    
<body>
<div class="container-fluid">
    <div class="col-md-12">
        <span class="span1">区域一览</span>
        <table class="table table-bordered table-striped table-hover table-condensed">
            <thead>
            <tr><th>区域名称</th><th>林种</th><th>地类</th><th>优势树种</th><th>负责小班</th></tr>
            </thead>
            <tbody id="tbody1">
            
            </tbody>
        </table>
        <div class="col-md-12">
            <div class="col-sm-6 div1" >
                <div style="text-align: left;font-size: 15px;height: 50px;" class="">
                    <ul id="cutPageUL" class="pagination" style="margin: 0px"></ul>
                </div>
                <a class="btn btn-default up" href="addArea.jsp" role="button" target="iframe1" >添加区域</a>
            </div>
            <div class="col-sm-6">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="input3" class="col-sm-4 control-label">区域名称</label>
                        <div class="col-sm-8">
                            <input  class="form-control" name="name" id="input3" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"
                 placeholder="请输入中文区域名称"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="input4" class="col-sm-4 control-label">林种</label>
                        <div class="col-sm-8">
                            <input  class="form-control" name="species" id="input4" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"
                 placeholder="请输入中文林种"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="input4" class="col-sm-4 control-label">负责小班</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="calssName" id="input5" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"
                 placeholder="请输入存在的小班"  >
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-8 col-sm-4">
                            <input type="button" class="btn btn-default" id="button" value="查询" onclick="find()">
                        </div>
                    </div>
                </form>
            </div>

        </div>


    </div>


</div>

</body>
<script>

//页面加载时显示第一页
(function(){
	cutPage(1);
})();
       

var ops = {
    bootstrapMajorVersion:3,//bootstrap版本
    currentPage:1,//当前页码
    totalPages:5,//总页数
    numberOfPages:4,//页面最多显示的页码数
    onPageClicked:function(e,ev,type,page){//page为点中的页码
        //操作 --点中页码后的操作
        cutPage(page);
    }
};        
        

        
        //按钮的查询方法
        function find() {
        	var name1=$("#input3").val();
        	var species=$("#input4").val();
        	var className=$("#input5").val();
        	//默认为第一页
        	cutPage(1,name1,species,className);			
		}
        
      

        //分页
        function cutPage(num,name1,species,className) {
            var str = "";
            $.post("/project2/areaindex",
            		"pageNo=" + num + "&name1="+name1+"&species="+species+"&className="+className,
           		function(info){
	               for(var i = 0 ; i<info.list.length;i++){
	                   str+="<tr onclick='change(this,"+info.list[i].pk_id+")'><td>"+info.list[i].name+"</td><td>"+info.list[i].great+"</td><td>"+info.list[i].gentle+"</td><td>"+info.list[i].species+"</td>";
	                   if(info.list[i].className==null){
	                	   str+="<td>暂无负责班级</td></tr>";
	                   }else{
	                	   str+="<td>"+info.list[i].className+"</td></tr>";
	                   }
	                   };
	               
	               $("#tbody1").html(str);
	                
               ops.totalPages = info.totalPageNum;
                ops.currentPage = num;
                $("#cutPageUL").bootstrapPaginator(ops);

            },"json");

        
        
        }
        

        

        
        
        
        

    </script>




</html>