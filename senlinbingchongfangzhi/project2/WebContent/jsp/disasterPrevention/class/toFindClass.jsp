<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
    </style>
</head>
<body>
<div class="container-fluid">
        <div class="col-sm-12">
            <span style="text-align: center;font-size: 30px" class="col-sm-12">查看小班信息</span>
            <form class="col-sm-12">
                <table   cellspacing="0" class="col-sm-12">
                    <tr>
                        <th class="col-sm-2"></th>
                        <th class="col-sm-4"></th>
                        <th class="col-sm-2"></th>
                        <th class="col-sm-4"></th>
                    </tr>
                    <tr>
                        <td>名称</td>
                        <td><c:out value="${classBean.name}"></c:out></td>
                        <td>负责人</td>
                        <td><c:out value="${classBean.principal}"></c:out></td>
                    </tr>
                    <tr>
                        <td>负责人电话</td>
                        <td><c:out value="${classBean.phoneNum}"></c:out></td>
                        <td>人员数量</td>
                        <td><c:out value="${classBean.num}"></c:out></td>
                    </tr>
                    <tr>
                        <td>负责区域</td>
                        <td><c:out value="${areaBean.name}"></c:out></td>
                        <td>林种</td>
                        <td><c:out value="${areaBean.great}"></c:out></td>
                    </tr>
                    <tr>
                        <td>地类</td>
                        <td><c:out value="${areaBean.gentle}"></c:out></td>
                        <td>优势树种</td>
                        <td><c:out value="${areaBean.species}"></c:out></td>
                    </tr>
                    <tr>
                        <td>创建时间</td>
                        <td><c:out value="${classBean.date}"></c:out></td>

                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <div class="col-md-12" style="text-align: center">
                    <input type="button" value="返回" class="btn btn-default" onclick="back()">
                </div>
            </form>
        </div>
    </div>

</body>
         <script>
          function back(){
        	  window.location.href="/project2/jsp/disasterPrevention/class/class.jsp";
          }
         </script>
</html>