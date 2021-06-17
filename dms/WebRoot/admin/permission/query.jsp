<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<%-- 内容开始 --%>

<div class="container-fluid">
  <div class="row row_bottom">
    <div class="col-sm-12"><strong class="font">查询条件</strong></div>
  </div>
  <form action="/dms/servlet/PermissionServlet?service=queryPermission" method="post">
  <div class="row row_bottom">
    <div class="col-sm-3">
       <div class="input-group form-group">
      <span class="input-group-addon">权限名称</span>
        <input class="form-control" type="text" name="perName" />
       </div>
      </div>
      <div class="col-sm-3">
       <div class="input-group form-group">
      <span class="input-group-addon">权限路径</span>
        <input class="form-control" type="text" name="perPath" />
       </div>
      </div>
    <div class="row row_bottom">
      <div class="col-sm-1">
        <input class="btn btn-primary btn-block" type="submit" value="查询" />
      </div>
      <div class="col-sm-1">
        <input class="btn btn-primary btn-block" type="button" value="增加" onclick="location.href='/dms/admin/permission/addpermission.jsp'"/>
      </div>
      <div class="col-sm-1">
        <input class="btn btn-primary btn-block" type="reset" value="重置" />
      </div>
      <div class="col-sm-3"></div>
      </div>
    </div>
  </form>
  <div class="row">
    <div class="col-sm-12">
      <table class="table table-bordered table-striped">
        <tr>
          <td>权限名称</td>
          <td>权限路径</td>
          <td>上级权限</td>
          <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${perList }" var="permission">
          <tr>
            <td>${permission.perName}</td>
            <td>${permission.perPath}</td>
            <td>${permission.leaderPermissionId}</td>
            <td><a href="/dms/servlet/PermissionServlet?service=getPermission&perName=${permission.perName}">修改</a></td>
            <td><a href="/dms/servlet/PermissionServlet?service=delPermission&perName=${permission.perName}">删除</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>
<%-- 内容结束 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
