<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp"></jsp:include>



<div class="container-fluid">
  <div class="row">
    <div class="col-md-12">
      <label>${role }</label>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="table-responsive">
      <table class="table table-bordered table-striped">
        <tr>
          <td>
          <c:forEach items="${permList }" var="perm" varStatus="status">
          <div class="col-md-2">
              <label><input type="checkbox" name="perm" id="${status.count}">${perm.perName}</label>
          </div>
          </c:forEach>
          </td>
        </tr>
      </table>
      </div>
    </div>
</div>
  <div class="row">
    <div class="col-md-10"></div>
    <div class="col-md-2">
    <form action="/dms/admin/role/servlet/RoleServlet" method="post">
      
      <input class="btn btn-primary btn-block" type="button" onclick="chk()" value="提 交" />
      <input type="hidden"  id="arrp" name="arrp" />
      <input type="hidden"  name="role" value=<%=request.getParameter("roleName")%>>
      <input type="hidden"   name="service" value="daoPerm"/>
      <input id="check" type="hidden" value="${checklist}">
     </form>
    </div>
  </div>
  <script>
  window.onload=hascheck;
  </script>
</div>  
<jsp:include page="/include/footer.jsp"></jsp:include>