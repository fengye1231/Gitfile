<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
         height: 650px;
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
        }

        form .form-control{
            width: 200px;

        }
        }
        #table{
          height:280px
        }
        
</style>

</head>
<body>

    <div class="container-fluid">
    <div class="col-md-12" id="table">
    <span class="span1">事件记录</span>
           <table class="table table-bordered table-striped table-hover table-condensed">
               <thead>
               <tr><th>事件名称</th><th>日期</th><th>发生位置</th><th>防治方案</th><th>灾情状态</th></tr>
               </thead>
               <tbody id="tbody1">
               <tr><td>张三</td><td>1980-02-02</td><td>13456789123</td><td>cbuia@qq.com</td><td></td></tr>
               <tr><td>李四</td><td>1982-04-22</td><td>13456789124</td><td>cbuib@qq.com</td><td></td></tr>
               </tbody>
           </table>
           </div>
        <div class="col-md-12" id="table">
                       <div class="col-sm-6">
                           <div style="text-align: left;font-size: 15px;height: 50px;" class="">
                               <ul id="cutPageUL" class="pagination" style="margin: 0px"></ul>
                           </div>
               <!--<input type="button" class="btn btn-default up" value="添加事件" href="" formtarget="iframe1">-->
                           <a class="btn btn-default up" onclick="addEvent()" role="button" target="iframe1" >添加事件</a>
                           <a class="btn btn-default up" onclick="findEvent()"role="button" target="iframe1" >查看事件信息</a>
                           <br>
                           <a class="btn btn-default up" onclick="apply()" role="button" target="iframe1" >申请专家会审</a>
                           <a class="btn btn-default up" onclick="updateEvent()"role="button" target="iframe1" >修改事件信息</a>

               <!--<input type="button" class="btn btn-default up" value="查看事件信息">-->
               <!--<br>-->
               <!--<input type="button" class="btn btn-default down" value="申请专家会审">-->
               <!--<input type="button" class="btn btn-default down" value="修改事件信息">-->
               <%-- this is look--%>
                       </div>
                  <div class="col-sm-6">
                           <form class="form-horizontal" role="form">
                               <div class="form-group">
                                   <label for="input3" class="col-sm-4 control-label">事件名称</label>
                                   <div class="col-sm-8">
                                       <input type="email" class="form-control" id="input3" placeholder="">
                                   </div>
                               </div>
                               <div class="form-group">
                                   <label for="input4" class="col-sm-4 control-label">灾情状态</label>
                                   <div class="col-sm-8">
                                       <input type="email" class="form-control" id="input4" placeholder="">
                                   </div>
                               </div>
                               <div class="form-group">
                                   <label for="input5" class="col-sm-4 control-label">发生位置</label>
                                   <div class="col-sm-8">
                                       <input type="email" class="form-control" id="input5" placeholder="">
                                   </div>
                               </div>
                               <div class="form-group">
                                   <label for="input1" class="col-sm-4 control-label">起始日期</label>
                                   <div class="col-sm-8">
                                       <input type="date" class="form-control" id="input1" placeholder="">
                                   </div>
                               </div>
                               <div class="form-group">
                                   <label for="input2" class="col-sm-4 control-label">结束日期</label>
                                   <div class="col-sm-8">
                                       <input type="date" class="form-control" id="input2" placeholder="">
                                   </div>
                               </div>
                               <div class="form-group">
                                   <div class="col-sm-offset-9 col-sm-3">
                                       <button type="button" class="btn btn-default" onclick="findOne()">查询</button>
                                   </div>
                               </div>
                           </form>
            </div>
           </div>
   </div>
</body>
      <script>
       var page=1
       var name=""
       var phase="";
       var area=""
       var start=""
       var end=''
       var totalPageNum=1;
       var ops = {
               bootstrapMajorVersion:3,//bootstrap版本
               currentPage:1,//当前页码
               totalPages:5,//总页数
               numberOfPages:1,//页面最多显示的页码数
               onPageClicked:function(e,ev,type,page){//page为点中的页码
            	    e.totalPages=totalPageNum
                    cutPage(page)
               }
           };
       
           $(function(){
               //初始化分页组件
               cutPage(1)
               $("#cutPageUL").bootstrapPaginator(ops);
           });
           
      function cutPage(page) {
	       var str=""
	       $.ajax({
               type:"POST",
               url:"/project2/EventShow",
               data:{"page":page,"name":name,"phase":phase,"area":area,"start":start,"end":end},
	    	   dataType:"json",
	    	   success:function(data){
	    	    	   console.info(data.list);
	                    for(var i=0;i<data.list.length;i++){
	                        str+="<tr onclick='changeColor(this)'><input type='hidden' value='"+data.list[i].id+"'><td>"+data.list[i].name+"</td><td>"+data.list[i].date+"</td>"
	                        +"<td>"+data.list[i].area+"</td><td>"+data.list[i].plan+"</td><td>"+data.list[i].phase+"</td></tr>"
	                    }
	                    console.info(str);
	                    $("#tbody1").html(str);
	                    totalPageNum=data.totalPageNum
	    	    } });
      }
         function findOne(){
        	 	
        	    
        	       name=$("#input3").val()
        	       phase=$("#input4").val()
        	       area=$("#input5").val()
        	       start=$("#input1").val()
        	       end=$("#input2").val()
        	       
        	      cutPage(1,name,phase,area,start,end)
         }
         var beforeObj=null;
         function changeColor(obj) {
             obj.style.backgroundColor="grey"

             if(beforeObj!=null){
                 beforeObj.style.backgroundColor="white"
             }
             beforeObj=obj;

         }
         
         function findEvent(){
        	 if(beforeObj==null){
        		 alert("请选择你要查看的行")
        		 return;
        	 }
        	 window.location.href="/project2/EventFind?id="+beforeObj.firstChild.value
        	 
         }
         function updateEvent(){
        	  if(beforeObj==null){
        		  alert("请选择你要修改的行")
        		  return;
        	  }
        		 window.location.href="/project2/UpdateEventFind?id="+beforeObj.firstChild.value
         }
         function apply(){
        	  if(beforeObj==null){
        		  alert("请选择你要修改的行")
        		  return;
        	  }
        	  console.info(beforeObj.firstChild.value)
        	   $.post(
        		  "/project2/EventApply"	,
        		  "id="+beforeObj.firstChild.value,
        		   function(){
        			  alert(1)
        			  cutPage(1)
        		  },"json")
         }
         
         function addEvent(){
        	 window.location.href="AddEvent.jsp";
         }
         
      </script>
</html>
