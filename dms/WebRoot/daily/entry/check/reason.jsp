<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<form name="f4" id="f4" class="form-horizontal"
				action="<%=basePath%>daily/entry/check/servlet.DailyServlet?service=reasonDaily&DailyID=${daily.dailyId}"
				method="post">
				<div class="panel panel-default">
					<div class="panel-heading">审核日报</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="date" class="col-sm-2 control-label"> 日期 </label>
							<div class="col-sm-4">
								<input type="text" readonly id="date" name="date"
									class="form-datetime form-control" disabled="" data-max="today"
									data-default="${daily.submitD}" value="${daily.submitD}"
									placeholder="请选择">
							</div>
							<label for="writor" class="col-sm-2 control-label"> 填写人 </label>
							<div class="col-sm-4">
								<input type="hidden" id="writor" name="writor"
									class="form-control" placeholder="填写人" value="${daily.empId}"></input>
								<input type="text" class="form-control" disabled=""
									placeholder="填写人" value="${daily.empName}"></input>
							</div>
						</div>

						<div class="form-group">
							<label for="test" class="col-sm-2 control-label"> 任务 </label>
							<div class="col-sm-10">
								<textarea id="test" name="test" class="form-control" rows="3"
									placeholder="填写任务详情" disabled="">${daily.desc}</textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="workload" class="col-sm-2 control-label"> 工作量
							</label>
							<div class="col-sm-4">
								<input name="workload" id="workload" class="form-control"
									disabled="" placeholder="不超过16小时"
									value="${daily.totalWorkload}">
							</div>
							<label for="overtime" class="col-sm-2 control-label"> 加班量
							</label>
							<div class="col-sm-4">
								<input name="overtime" id="overtime" class="form-control"
									disabled="" placeholder="工作量中属于加班的部分"
									value="${daily.overTimeLoad}">
							</div>
						</div>

						<div class="form-group">
							<label for="item" class="col-sm-2 control-label"> 项目 </label>
							<div class="col-sm-4">
								<select name="item" id="item" class="form-control"
									name="combobox1" " disabled="">
									<option value="${daily.prpId}">${daily.prjName}</option>
								</select>
							</div>
							<label for="model" class="col-sm-2 control-label"> PRP阶段
							</label>
							<div class="col-sm-4">
								<select name="model" id="model" class="form-control" disabled=""
									name="combobox2">
									<option value="${daily.prpId}">${daily.prpName}</option>
								</select>
							</div>
						</div>
						<hr>
						<div class="form-group">
							<label for="appr-date" class="col-sm-2 control-label">
								审批日期 </label>
							<div class="col-sm-4">
								<input type="text" readonly class="form-datetime form-control"
									id="appr-date" data-max="today" placeholder="请选择"
									disabled="" value="${daily.reviewD}">
							</div>
							<label for="appr" class="col-sm-2 control-label"> 审批人 </label>
							<div class="col-sm-4">
								<input type="hidden" id="reviewer" name="reviewer"
									class="form-control" placeholder="审批人"
									value="${employee.empId}"> <input type="text"
									class="form-control" disabled="" placeholder="审批人"
									value="${daily.reviewEmpName}">
							</div>
						</div>

						<div class="form-group">
							<label for="reason" class="col-sm-2 control-label"> 拒绝原因
							</label>
							<div class="col-sm-10">
								<textarea id="reason" name="reason" class="form-control" rows="3"
									placeholder="填写拒绝原因">${daily.reason}</textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="status" class="col-sm-2 control-label"> 审核状态
							</label>
							<div class="col-sm-10">
								<select name="status" id="status" class="form-control">
								    <option value="${daily.status}">${daily.status}</option>
									<option value="未通过">未通过</option>
									<option value="已通过">已通过</option>
								</select>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-2 col-sm-offset-8">
								<button type="submit" class="btn btn-primary btn-block">
									修 改</button>
							</div>
							<div class="col-sm-2">
								<button class="btn btn-default btn-block"
									onclick="window.history.back(-1);">关 闭</button>
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