<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css">

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script type="javascript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap-paginator.min.js"></script>
</head>
<style>

      label{
          line-height: 25px;
          float: right;
      }

      form select{
          height: 35px;
      }
      .span{
             font-size:15px;
             color:red;
             display:none;
             width:auto;
      }
</style>
<body>
    <div class="container-fluid">
        <div class="col-sm-12">
            <span class="col-sm-12" style="text-align: center;line-height: 60px;font-size: 30px">
                添加事件信息
            </span>
        </div>
        <br>
        <form class="form-horizontal" enctype="multipart/form-data" action="/project2/EventAdd" method="post" id="form">
        <div class="col-sm-6">
            <div class="col-sm-4">
            <label for="input1" class="control-label" >事件名称:</label>
            <span class="span" id="spanName">请输入中文加数字格式</span>
            </div>
            <div class="col-sm-8">
            <input type="text" class="form-control" name="name" placeholder="" id="name">
            </div>
            <div class="col-sm-4">
                <label for="input1" class="control-label" >时间</label>
                <span class="span" id="spanDate">请输入时间格式</span>
            </div>
            <div class="col-sm-8">
                <input type="date" class="form-control"  name="date" placeholder="" id="date">
            </div>
            <div class="col-sm-4">
                <label for="input1" class="control-label" >灾情阶段:</label>
                
            </div>
            <div class="col-sm-8">
                <select id="cars1"  name="phase">
                <option >已经得到控制</option>
                <option  selected>防治中</option>
                <option>无法解决，申请专家会商</option>
            </select>
            </div>
            <div class="col-sm-4">
                <label for="input1" class="control-label" >灾情描述:</label>
                <span class="span" id="spanDescribe">请输入正确描述</span>
            </div>
            <div class="col-sm-8">
                <input type="text"  class="form-control"   name="describe" id="describe">
                
            </div>
            <div class="col-sm-4">
                <label for="input1" class="control-label" >发生位置:</label>
            </div>
            <div class="col-sm-8">
                <select id="areas"  name="area">
                    <option>雅安地区</option>
                    <option selected>宜宾地区</option>
                    <option >攀枝花三号地区</option>
                </select>
            </div>
            <div class="col-sm-4">
               <span class="span" id="spanLose">请输入数字</span>
                <label for="input1" class="control-label" >初步损失:</label>
            </div>
            <div class="col-sm-8">
                <input type="text"  name="lose"  class="form-control"  id="lose">
            </div>
            <div class="col-sm-4">
                <label for="input1" class="control-label" >防治方案:</label>
                <span class="span" id="spanPlan">请输入正确的格式</span>
            </div>
            <div class="col-sm-8">
                <input  type="text" name="plan"  class="form-control"  id="plan">
            </div>
        </div>
            <div class="col-sm-6">
                <div class="col-sm-4">
                   <span class="span" id="spanPic">请上传,jpg,img,bmp的格式</span>
                    <label for="input1" class="control-label" >灾区图片:</label>
                </div>
                <div class="col-sm-8" style="height: auto">
                    <input type="file" class="btn btn-default"  style="display: inline-block" name="img"  id="img">
                </div>
                <div class="col-sm-4">
                    <label for="input1" class="control-label" >灾害类型:</label>
                </div>
                <div class="col-sm-8">

                    <select class="cars2" name="type">
                        <option>虫害</option>
                        <option selected>鼠害         </option>
                        <option>病害</option>
                    </select>
                </div>
                <div class="col-sm-4">
                    <label for="input1" class="control-label" >发现途径:</label>
                </div>
                <div class="col-sm-8">
                    <select class="cars3" name="findPath">
                        <option >小班巡查发现</option>
                        <option selected>公众发现</option>
                        <option>上级部门通报</option>
                    </select>
                </div>
                <div class="col-sm-4">
                    <label for="input1" class="control-label" >所在小班:</label>
                </div>
                <div class="col-sm-8">
                    <span class="col-sm-8"  style="height:35px"></span>
                </div>
                <div class="col-sm-4">
                    <label for="input1" class="control-label" >影响面积:</label>
                      <span class="span" id="spanAffect">请输入正确的数字</span>
                </div>
                <div class="col-sm-8">
                    <input type="text" name="affectArea"  class="form-control" id="affect">
                </div>
            </div>
        <div class="col-sm-12">
            <br>
            <br>
            <span style="text-align: center" class="col-sm-12"  >
             <input  class="btn btn-default" type="submit"  value="添加" id="button"></span>
        </div>
        </form>

    </div>

</body>
         <script>
                     var boo=false;
                     var boo1=false;
                     var boo2=false;
                     var boo3=false;
                     var boo4=false;
                     var boo5=false;
                     
                     $("#name").blur(function(){
                    	 var regex=/^[0-9\u4e00-\u9fa5\s·]+$/
                    	 var name=$("#name").val();
                    	 if(regex.test(name)){
                    		 boo1=true;
                    		 $("#spanName").css({"display":"none"})
                    	 }else{
                    		 $("#spanName").css({"display":"inline"})
                    	 }
                     });
                     $("#date").blur(function(){
                    	 var regex1= /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/
                    	 var date=$("#date").val();
                    	 if(regex1.test(date)){
                    		 boo2=true;
                    		 $("#spanDate").css({"display":"none"})
                    	 }else{
                    		   $("#spanDate").css({"display":"inline"})
                    	 }
                     })
                       $("#describe").blur(function(){
                    	 var regex3=/^[0-9\u4e00-\u9fa5\s·]+$/
                    	 var describe=$("#describe").val();
                    	boo=regex3.test(describe)
                    	if(regex3.test(describe)){
                    		boo3=true;
                    		 $("#spanDescribe").css({"display":"none"})
                    	}else{
                    		 $("#spanDescribe").css({"display":"inline"})
                    	}
                     })
                     $("#lose").blur(function(){
                    	 var regex3=/^[0-9]+$/
                    	 var lose=$("#lose").val();
                     if(regex3.test(lose)){
                    	  boo4=true;
                    	  $("#spanLose").css({"display":"none"})
                     }else{
                     	 $("#spanLose").css({"display":"inline"})
                     }
                     })
                    $("#plan").blur(function(){
                    	 var regex4=/^[\u4e00-\u9fa5\s·]+$/
                    	var plan=$("#plan").val();
                    	 console.info(plan)
                        if(regex4.test(plan)){
                        	 boo5=true
                          	 $("#spanPlan").css({"display":"none"})
                        }else{
                       	 $("#spanPlan").css({"display":"inline"})
                        }
                    	
                    })
                   $("#form").submit(function(){
                	   $("#name").blur()
                	   $("#lose").blur()
                	   $("#describe").blur()
                	   $("#plan").blur()
                	   $("#date").blur()
                	   console.log(boo1)
                	   return boo1&&boo2&&boo3&&boo4&&boo5
                   })
                    
                    $(function(){
                        	 var str=""
                        	 $.post(
                        			 "/project2/FindArea",
                        			 "id="+1,
                        			 function(info){
                        				for(var i=0;i<info.length;i++){
                        					    str+="<option>"+info[i]+"</option>";
                        				}
                        				$("#areas").html(str);
                        			 },"json"
                        	 )
                    })
         </script>
</html>