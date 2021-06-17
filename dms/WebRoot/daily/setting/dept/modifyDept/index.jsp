<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String deptId = request.getParameter("deptId");
if(deptId == null || "".equals(deptId)){
	deptId="1";
}
request.setAttribute("deptId",deptId);
%>

    <jsp:forward page="/daily/setting/dept/DeptServlet?deptAction=viewModify">
    	<jsp:param name="deptId" value="${requestScope.deptId}"/>
    </jsp:forward>

