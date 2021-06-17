<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<%-- 内容开始 --%>

<div class="container-fluid">
  <div class="row row_bottom">
    <div class="col-sm-12"><strong class="font">查询条件</strong></div>
  </div>
  <form action="/dms/servlet/MenuServlet?service=queryMenu" method="post">
    <div class="row">
      <div class="col-sm-3">
       <div class="input-group form-group">
      <span class="input-group-addon">菜单名</span>
        <input class="form-control" type="text" name="menuName" />
       </div>
      </div>
       <div class="col-sm-3">
        <div class="form-group input-group"> <span class="input-group-addon">父菜单</span>
          <select id="sel" class="form-control" onchange="getChange()">
          </select>
            <input type="hidden" name="parentMenuId" id="parentMenuId"/>
        </div>
      </div>
      <div class="col-sm-1 ">
        <input class="btn btn-primary btn-block" type="submit" value="查询" />
        </div>
        <div class="col-sm-1 ">
        <input class="btn btn-primary btn-block" type="button" value="增加" onclick="location.href='/dms/admin/menu/addmenu.jsp'"/>
       </div>
       <div class="col-sm-1 ">
        <input class="btn btn-primary btn-block" type="reset" value="重置" />
        </div>
        <div class="col-sm-3 "></div>
      </div>
        </form>


  <div class="row">
    <div class="col-sm-12">
      <table class="table table-bordered table-striped">
        <tr>
          <td>菜单名</td>
          <td>菜单编号</td>
          <td>上级菜单</td>
          <td>链接</td>
          <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${menuList }" var="menu">
          <tr>
            <td>${menu.menuName}</td>
            <td>${menu.menuCode}</td>
            <td>${menu.parentMenuId}</td>
            <td>${menu.menuPath}</td>
            <td><a href="/dms/servlet/MenuServlet?service=getMenu&menuName=${menu.menuName}">修改</a></td>
            <td><a href="/dms/servlet/MenuServlet?service=delMenu&menuName=${menu.menuName}">删除</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>

<%-- 内容结束 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
