<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ page
	import="com.neusoft.dms.employee.domain.*,com.neusoft.dms.employee.service.*,com.neusoft.dms.employee.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	List<EmployeeVo> emps = EmployeeServiceImpl.getInstance()
			.leaderSearchEmp(Integer.valueOf(((EmployeeVo)session.getAttribute("employee")).getEmpId()));
	request.setAttribute("emps", emps);
%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<init-msg>${msg}</init-msg>
<div class="container-fluid">
	<form action="#" method="post" id="form-query" data-toggle="validator">
		<div class="panel panel-default">
			<div class="panel-body no-padding-bottom">
				<div class="row">
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
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">人员</span>
							<select class="form-control" name="empId">
								<option value="0">
									全部
								</option>
								<c:forEach items="${emps}" var="emps">
									<option value="${emps.empId}">
										${emps.username}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">审核状态</span>
							<select class="form-control" name="status">
								<option value="">
									全部
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
								<option value="未审核">
									未审核
								</option>
							</select>
						</div>
					</div>
					<div class="col-md-9 col-md-offset-3">
						<div class="row">

							<div class="col-sm-2">
								<div class="form-group">
									<button id="fail-btn" class="btn btn-primary btn-block">
										不通过
									</button>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<button id="pass-btn" class="btn btn-primary btn-block">
										通 过
									</button>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<button id="reason-btn" class="btn btn-primary btn-block">
										修 改
									</button>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<button class="btn btn-default btn-block" type="reset">
										重 置
									</button>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block">
										查 询
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table id="table" data-dms-table></table>
			</div>
		</div>
	</form>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content"></div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script>
$(function() {
	$('#myModal').on('hide.bs.modal', function(e) {
		$(this).removeData();
	});
});
</script>
<!-- /.modal -->
<jsp:include page="/include/footer.jsp"></jsp:include>