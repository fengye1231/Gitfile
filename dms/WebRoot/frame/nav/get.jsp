<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.dept.service.*,com.neusoft.dms.dept.domain.*,com.neusoft.dms.employee.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"  %>

<%
	boolean hasUnsubmitWarning = false;
	boolean hasUncheckWarning = false;
	EmployeeVo employee = (EmployeeVo) session.getAttribute("employee");
	if (employee != null) {
		int empId = employee.getEmpId();
		hasUnsubmitWarning = DepartmentDailyWarningServiceImpl.getInstance().hasUnsubmitWarning(empId);
		hasUncheckWarning = DepartmentDailyWarningServiceImpl.getInstance().hasUncheckWarning(empId);
	}
	request.setAttribute("hasUnsubmitWarning", hasUnsubmitWarning);
	request.setAttribute("hasUncheckWarning", hasUncheckWarning);

%>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="javascript:void(0);"><%= TITLE %><sup><%= VERSION %></sup></a>
</div>
<!-- /.navbar-header -->

<ul class="nav navbar-top-links navbar-right" role="navigation">
    <!-- /.dropdown -->
    <c:if test="${hasUnsubmitWarning || hasUncheckWarning}">
	    <li class="dropdown warning-displayer">
	        <a class="dropdown-toggle warning" data-toggle="dropdown" href="#" aria-expanded="false">
	            <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
	        </a>
	        <ul class="dropdown-menu dropdown-alerts">
	        	<c:if test="${hasUnsubmitWarning}">
		            <li>
		                <a href="<%= basePath %>daily/entry/DeptLoadServlet?service=clearUnsubmitWarning">
		                    <div>
		                        <i class="fa fa-warning fa-fw warning-static"></i> 您有未提交日报提醒
		                        <span class="pull-right text-muted">点击进入</span>
		                    </div>
		                </a>
		            </li>
		            <li class="divider"></li>
	            </c:if>
	        	<c:if test="${hasUncheckWarning}">
		            <li>
		                <a href="<%= basePath %>daily/query/dept/DeptLoadServlet?service=clearUncheckWarning">
		                    <div>
		                        <i class="fa fa-warning fa-fw warning-static"></i> 您有未审核日报提醒
		                        <span class="pull-right text-muted">点击进入</span>
		                    </div>
		                </a>
		            </li>
	            </c:if>
	        </ul>
	        <!-- /.dropdown-alerts -->
	    </li>
    </c:if>
    <!-- /.dropdown -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);" aria-expanded="false">
            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
            <li><a href="<%= basePath %>info/user"><i class="fa fa-user fa-fw"></i> 账号信息</a>
            </li>
            <li class="divider"></li>
            <li><a href="<%= basePath %>login/login.servlet?service=logout"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
            </li>
        </ul>
        <!-- /.dropdown-user -->
    </li>
    <!-- /.dropdown -->
</ul>
<!-- /.navbar-top-links -->