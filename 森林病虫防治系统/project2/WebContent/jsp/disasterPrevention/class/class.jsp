<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css">

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script type="javascript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap-paginator.min.js"></script>
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
    <script>
     

    </script>
<body>
</head>
<div class="container-fluid">
        <div class="col-md-12">
            <span class="span1">小班管理</span>
            <table class="table table-bordered table-striped table-hover table-condensed">
                <thead>
                <tr><th>小班名称</th><th>负责人</th><th>负责人电话</th><th>负责区域</th></tr>
                </thead>
                <tbody id="tbody1">
                
                </tbody>
            </table>
            
            <div class="col-md-12">
                <div class="col-sm-6 div1" >
                <div style="text-align: left;font-size: 15px;height: 50px;" class="">
                    <ul id="cutPageUL" class="pagination" style="margin: 0px">
                    </ul>
                </div>
                <!--<input type="button" class="btn btn-default up" value="添加小班">-->
                    <a class="btn btn-default up" href="addClass.jsp" role="button" target="iframe1" >添加小班</a>

                    <a class="btn btn-default up" onclick="findOne()" role="button" target="iframe1" >查看小班信息</a>
                    <br>
                    <a class="btn btn-default up" onclick="update()"role="button" target="iframe1" >修改小班信息</a>

                    <!--<input type="button" class="btn btn-default up" value="查看小班信息">-->

                <!--<input type="button" class="btn btn-default down" value="修改小班信息">-->


                </div>
                <div class="col-sm-6">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="input3" class="col-sm-4 control-label">小班名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="input3" placeholder=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="input4" class="col-sm-4 control-label">负责区域</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="input4" placeholder=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-8 col-sm-4">
                                <button type="button" class="btn btn-default"  onclick="find()">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
</div>
</body>
   <script>
		     var page=1;
		     var name="";
		     var area="";
		     var totalPageNum=1;
       function cutPage(page) {
	       var str=""
	       var str1=""
	       $.ajax({
                type:"POST",
                url:"/project2/ClassShow",
                data:{"page":page,"name":name,"area":area},
	    	    dataType:"json",
	    	    success:function(data){
	    	    	
	                    for(var i=0;i<data.list.length;i++){
                        	str+="<tr onclick='changeColor(this)'><input type='hidden' value='"+data.list[i].id+"'><td>"+data.list[i].name+"</td><td>"+data.list[i].principal+"</td><td>"+data.list[i].phoneNum+"</td><td>"+data.list[i].areaBean.name+"</td></tr>"
	                    }
	                    $("#tbody1").html(str)
	                    totalPageNum=data.totalPageNum
	    	    } });
	       
       }
    var ops = {
               bootstrapMajorVersion:3,//bootstrap版本
               currentPage:1,//当前页码
               totalPages:5,//总页数
               numberOfPages:2,//页面最多显示的页码数
               onPageClicked:function(e,ev,type,page){//page为点中的页码
                     cutPage(page)
                      e.totalPages=totalPageNum;
               }
    }
           $(function(){
               //初始化分页组件
               cutPage(1);
               $("#cutPageUL").bootstrapPaginator(ops);
           });
           
           function find(){
        	   name=$("#input3").val()
        	   area=$("#input4").val()
        	   cutPage(1,name,area)
           }
            var beforeObj=null;
           function changeColor(obj) {
               obj.style.backgroundColor="grey"

               if(beforeObj!=null){
                   beforeObj.style.backgroundColor="white"
               }
               beforeObj=obj;

           }
           function findOne(){
        	   if(beforeObj==null){
        		   alert("请选择你要查看的班级")
        		   return
        	   }
        		   var id=beforeObj.firstChild.value
        		   window.location.href="/project2/ClassFind?id="+id;
           };
           function update(){
        	   if(beforeObj==null){
        		   alert("请选择你要修改的班级")
        		   return
        	   }
        	   var id=beforeObj.firstChild.value
    		   window.location.href="/project2/UpdateClassFind?id="+id;
           }
   </script>
</html>