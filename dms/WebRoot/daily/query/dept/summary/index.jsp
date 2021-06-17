<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.dept.service.*, com.neusoft.dms.dept.domain.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%

	List<DepartmentVo> departments = DepartmentLoadServiceImpl.getInstance().getLowestLevelDepartments();
	request.setAttribute("departments", departments);

%>

<%-- 内容开始 --%>

<!-- 部门工作量查询/项目投入汇总 -->

<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-body no-padding-bottom">
			<div class="row">
				<form action="#" method="post" id="form-query"
					data-toggle="validator">
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">开始日期</span>
							<input type="text" readonly id="start-date"
								class="form-datetime form-control" data-lte="#end-date"
								data-max="today" data-default="today" data-name="startDate"
								placeholder="请选择" required>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">结束日期</span>
							<input type="text" readonly id="end-date"
								class="form-datetime form-control" data-max="today"
								data-default="today" data-name="endDate" placeholder="请选择"
								required>
						</div>
					</div>
					<div class="col-sm-6 col-md-4">
						<div class="form-group input-group">
							<span class="input-group-addon">职能部门</span>
							<select class="form-control" name="deptId" required>
								<c:forEach items="${departments}" var="department">
									<option value="${department.id}">
										${department.name}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-6 col-md-2">
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