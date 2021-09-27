<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
        div{
            /*border: 1px solid;*/
        }
        .mytable{
            /*border: black 1px solid;*/

        }
        tr{
            /*border: 1px solid;*/
        }
        td{
            width: 60px;
            line-height: 30px;
        }
    </style>

</head>
<body>
<div class="container-fluid">

    <div class="col-sm-12">
        <span style="text-align: center;font-size: 30px" class="col-md-12">查看事件信息</span>
        <table class="mytable">
            <tr>
                <th class="col-sm-2"></th>
                <th class="col-sm-4"></th>
                <th class="col-sm-2"></th>
                <th  class="col-sm-4"></th>
            </tr>
            <tr>
              
                <td class="label1">事件名称</td>
                <td><c:out  value="${eventBean.name } "></c:out><input type="hidden"  value=""  id="beanId"></td>
                <td >灾区图片 </td>
                <td rowspan="3"><img width="300px" height="200px" src="../../img/${eventBean.imgPath }"></td>
            </tr>
            <tr>
                <td>时间</td>
               <td><c:out  value="${eventBean.date} "></c:out> </td>
                <td > </td>
                <td> </td>
            </tr>
            <tr>
                <td>灾情阶段</td>
                <td>  
                 <select id="select">
                            <option value="1" >已得到控制</option>
                            <option value="2" selected>防治中</option>
                            <option value="3" >无法解决，申请专家会商</option>
                        </select> </td>
                <td> </td>
                <td > </td>
            </tr>
            <tr>
                <td  rowspan="2">灾情描述</td>
                <td rowspan="2"><c:out  value="${eventBean.describe} "></c:out> </td>
                <td>发现途径</td>
                <td><c:out  value="${eventBean.findPath } "></c:out> </td>
            </tr>
            <tr>

                <td>发生位置</td>
                <td><c:out  value="${eventBean.area} "></c:out> </td>
            </tr>
            <tr>
                <td>灾害类型 </td>
             <td><c:out  value="${eventBean.type} "></c:out> </td>
                <td>所在小班 </td>
                <td><c:out value="${classBean}"></c:out></td>
            </tr>
            <tr>
                <td>初步损失</td>
               <td><c:out  value="${eventBean.lose} ">万元</c:out> </td>
                <td>影响面积</td>
                <td><c:out  value="${eventBean.affectArea} ">亩</c:out> </td>
            </tr>
            <tr>
                <td>专家建议 </td>
            <td><c:out  value="${eventBean.opinon} "></c:out> </td>
                <td>防治方案</td>
               <td><input type="text"  placeholder="" id="plan"></td>
            </tr>
        </table>
        <table class="table table-bordered table-striped table-hover table-condensed">
            <thead>
            <tr><th>事件名称</th><th>日期</th><th>发生位置</th></tr>
            </thead>
            <tbody>
            <c:forEach var="event"  items="${eventBean.list}">
                <tr><td><c:out value="${event.content}"></c:out></td><td><c:out value="${event.date}"></c:out></td><td><c:out value="${event.result}"></c:out></td></tr>
            </c:forEach>
            </tbody>
        </table>
        <input value="${eventBean.id}" type="hidden" id="bean"> 
            <div class="co-lsm-12" style="text-align: center">
                <input type="button" class="btn btn-default" value="修改" onclick="update()">
            </div>
        </div>
</div>
</body>
     <script>
     function update(){
    	 var selectText=$("#select option:selected").text();
         var plan=$("#plan").val();
         var id=$("#bean").val()
        // console.info(id)
         console.info(selectText)
    	  $.ajax({
        	  url:"/project2/EventUpdate",
              type:"post",
              dataType:"json",
              data:{"phase":selectText,"plan":plan,"id":id},
              success:function(data){
            	  window.location.href="/project2/jsp/disasterPrevention/event/Event.jsp"
              },
              error:function(){
            	   alert("修改失败")
              }
              
         })
     }
             
              
           
             
            
     </script>
</html>