<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css">

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script type="javascript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap-paginator.min.js"></script>
    <style>
        div{
            /*border: 1px solid;*/
        }
        tr{
            /*border: 1px solid;*/
            height: 60px;
        }
        td{
            width: 60px;
            line-height: 20px;
        }
        .span{
             font-size:18px;
             color:red;
             display:none;
           
        }
    </style>
</head>
<body>
<div class="container-fluid">
        <div class="col-sm-12">
            <span style="text-align: center;font-size: 30px" class="col-md-12">添加小班</span>
            <form class="col-md-12">
                 <table   cellspacing="0" class="col-sm-12" >
                        <tr>
                            <th class="col-sm-2"></th>
                            <th class="col-sm-4"></th>
                            <th class="col-sm-2"></th>
                            <th class="col-sm-4"></th>
                        </tr>
                        <tr>
                            <td>名称</td>
                            <td><input type="text" id="name"><span class="span" id="spanClass">请输入正确格式</span></td>
                            <td>负责人</td>
                            <td><input type="text" id="duty"><span class="span" id="spanDuty">请输入中文格式</span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                     <tr>
                         <td>负责人电话</td>
                         <td><input type="text" id="tel"><span id="spanPhone" class="span">请输入正确的手机号</span></td>
                         <td>人员数量</td>
                         <td><input type="text" id="manNum"><span id="spanNum" class="span">请输入正确的数量</span></td>
                     </tr>
                     <tr>
                         <td></td>
                         <td></td>
                         <td></td>
                         <td></td>
                     </tr>
                     <tr>
                         <td>负责区域</td>
                         <td>
                             <select style="width: 100px" id="select">
                                 
                             </select>
                         </td>
                         <td></td>
                         <td></td>
                     </tr>
                    </table>
                <div class="col-md-12" style="text-align: center">
                    <input type="button" value="添加" class="btn btn-default" onclick="addClass()">
                </div>
            </form>
        </div>
    </div>
</body>
       <script >
                     function addClass(){
                    	 var phoneNum=$("#tel").val()
                    	 var principal=$("#duty").val()
                    	 var selectText=$("#select option:selected").text();
                    	 var num=$("#manNum").val()
                    	 var classname=$("#name").val()
                    	 
                    	 var regex1=/^[1[3|5|7|8|]\d[0-9]{9}$/
                    	var flag=regex1.test(phoneNum)
                       var regex2=/^[0-9\u4e00-\u9fa5\s·]+$/
                       var flag2=regex2.test(principal);
                    	 var regex3=/^[0-9]+$/
                    	 var flag3=regex3.test(num)
                    	 var regex4=/^[0-9\u4e00-\u9fa5\s·]+$/
                    	 var flag4=regex4.test(classname)
                    	 if(!flag){
	                    		 $("#spanPhone").css({"display":"inline"})
	                    		 return
                    	 }else if(!flag2){
                    			 $("#spanDuty").css({"display":"inline"})
                    			 return
                    	 }else if(!flag3){
                    		    $("#spanNum").css({"display":"inline"})
                    		    return
                    	 }else if(!flag4){
                    		     $("#spanClass").css({"display":"inline"})
                    		     return
                    	 }	 
                    	   $.ajax({
                    		      url:"/project2/ClassAdd",
                    		      type:"POST",
                    		      data:{"name":$("#name").val(),"principal":$("#duty").val(),
                    		    	  "phoneNum":$("#tel").val(),"num":$("#manNum").val(),
                    		    	  "areaName":selectText},
                    		      dataType:"json",
                    		      success:function (data){
                    		    	  if(data==0){
                    		    		  return;
                    		    	  }
                    		    	  window.location.href="/project2/jsp/disasterPrevention/class/class.jsp";
                    		      },
                    		     error:function(){
                    		    	  alert("添加失败")
                    		      }	  
                    	   });
                     }
                     (function(){
                    	 var str=""
                    	 $.post(
                    			 "/project2/FindArea",
                    			 "id="+1,
                    			 function(info){
                    				for(var i=0;i<info.length;i++){
                    					    str+="<option value=‘"+info[i]+"’>"+info[i]+"</option>";
                    				}
                    				$("#select").html(str);
                    			 },"json"
                    	 )
                     })();
                  
                     
       </script>
</html>