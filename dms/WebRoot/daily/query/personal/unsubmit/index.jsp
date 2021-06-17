<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.personal.service.*,com.neusoft.dms.employee.domain.*"%>
<%@ include file="/include/base.jsp"  %>

<%
    EmployeeVo employeeVo = (EmployeeVo)session.getAttribute("employee");
    if (employeeVo != null) {
	    int empId = employeeVo.getEmpId();
		List<EmployeeVo> employee = PersonalServiceImpl.getInstance().getEmployee(empId);
		request.setAttribute("employee", employee);
		request.getRequestDispatcher("/daily/query/personal/servlet/PersonalServlet?service=unsubmit").forward(request, response);
    }

%>



