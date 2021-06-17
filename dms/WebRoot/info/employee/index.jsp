<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.neusoft.dms.employee.service.*,com.neusoft.dms.employee.domain.*"%>
<%@page import="com.neusoft.dms.dept.domain.DepartmentVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%
	List<DepartmentVo> depts = EmployeeServiceImpl.getInstance()
			.getRunningDept();
	request.setAttribute("depts", depts);
%>


<%-- 员工页面 --%>

<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-body no-padding-bottom">
			<div class="row">
				<form action="#" method="post" id="form-query"
					data-toggle="validator">
					<div class="col-sm-4">
						<div class="form-group input-group">
							<span class="input-group-addon">姓名</span>
							<input type="text" class="form-control" name="empName"
								id="empName">
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group input-group">
							<span class="input-group-addon">编号</span>
							<input type="text" class="form-control" name="username"
								id="username">
						</div>
					</div>
					
					<div class="col-sm-4">
						<div class="form-group input-group">
							<span class="input-group-addon">部门</span>
							<select class="form-control" name="deptId" id="deptId">
							<option value="0">无</option>
								<c:forEach items="${depts}" var="dept">
									<option value="${dept.id}">
										${dept.name}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group input-group">
							<span class="input-group-addon">入职</span>
							<input type="text" readonly id="joinDate1" data-name="joinDate1"
								class="form-datetime form-control" data-lte="#joinDate2"
								placeholder="请选择">
							<span class="input-group-addon no-border-lr">至</span>
							<input type="text" readonly id="joinDate2" data-name="joinDate2"
								class="form-datetime form-control" data-max="today"
								data-default="today" placeholder="请选择">

						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<a href="adduser" class="btn btn-primary btn-block">添 加 </a>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<button type="button" id="del-btn"
								class="btn btn-primary btn-block">
								删 除
							</button>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<button type="reset" class="btn btn-default btn-block">
								重 置
							</button>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								查 询
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<table id="table" data-dms-table></table>
		</div>
	</div>
</div>







<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>