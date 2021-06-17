<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.neusoft.dms.prj.domain.*,com.neusoft.dms.daily.entry.service.*,com.neusoft.dms.employee.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<%
	PrjEmpVo prj = DailyServiceImpl.getInstance().getPrjEmp(Integer.valueOf(((EmployeeVo)session.getAttribute("employee")).getEmpId()));
	if (prj == null) {
	request.setAttribute("msg", "请参加一个项目");
	request.getRequestDispatcher("/daily/entry/manage/").forward(request, response);
	} else {
		request.setAttribute("prj", prj);
		List<PrjPrpVo> prps = DailyServiceImpl.getInstance().getPrp(
				prj.getPrjID());
		request.setAttribute("prps", prps);
	}
%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<init-msg>${msg}</init-msg>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<form name="f3" id="f3" class="form-horizontal"
				action="<%=basePath%>daily/entry/manage/servlet.DailyServlet?service=addDaily"
				method="post">
				<div class="panel panel-default">
					<div class="panel-heading">新增日报</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="date" class="col-sm-2 control-label">日期</label>
							<div class="col-sm-4">
								<input type="text" readonly id="date" name="date" data-max="today"
									class="form-datetime form-control" data-default="today"
									placeholder="请选择">
							</div>
							<label for="writor" class="col-sm-2 control-label">填写人 </label>
							<div class="col-sm-4">
								<input class="form-control" disabled=""
									placeholder="填写人" value="${employee.username}">
								<input id="writor" name="writor" class="form-control" type="hidden"
									placeholder="填写人" value="${employee.empId}">
							</div>
						</div>
						<div class="form-group">
							<label for="test" class="col-sm-2 control-label">任务 </label>
							<div class="col-sm-10">
								<textarea id="test" name="test" class="form-control" rows="3"
									placeholder="不可超过100个字。" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="workload" class="col-sm-2 control-label">工作量</label>
							<div class="col-sm-4">
								<input name="workload" id="workload" class="form-control"
									placeholder="不超过16小时" required autofocus></input>
							</div>
							<label for="overtime" class="col-sm-2 control-label">加班量</label>
							<div class="col-sm-4">
								<input name="overtime" id="overtime" class="form-control"
									placeholder="工作量中属于加班的部分"></input>
							</div>
						</div>
						<div class="form-group">
							<label for="item" class="col-sm-2 control-label">项目</label>
							<div class="col-sm-4">
								<input type=hidden name="item" id="item" class="form-control"
									value="${prj.prjID}"> <input class="form-control"
									disabled="" value="${prj.prjName}">
							</div>
							<label for="model" class="col-sm-2 control-label">PRP阶段</label>
							<div class="col-sm-4">
								<select name="model" id="model" class="form-control"
									name="combobox2">
									<c:forEach items="${prps}" var="prps">
										<option value="${prps.prpID}">${prps.prpName}</option>
									</c:forEach>
<!-- 									<script> $(".model").find("option[value='pxx']").attr("selected",true);</script> -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="test" class="col-sm-2 control-label">明日计划</label>
							<div class="col-sm-10">
								<textarea name="tomorrow" id="tomorrow" class="form-control"
									rows="3" placeholder="可选填明天计划的工作"></textarea>
							</div>
						</div>
						<div class="panel-footer">
							<div class="form-group no-margin-bottom">
								<div class="col-sm-2 col-sm-offset-8">
									<input class="btn btn-default btn-block" type="button" value="返回"
										onclick="location.href='/dms/daily/entry/manage/'" />
								</div>
								<div class="col-sm-2">
									<button type="submit" class="btn btn-primary btn-block">提交</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>
