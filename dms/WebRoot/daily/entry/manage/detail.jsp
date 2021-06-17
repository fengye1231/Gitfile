<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 align="center" class="modal-title" id="myModalLabel">日报信息</h4>
</div>

<div class="container-fluid" style="padding-top: 15px;">
	<form name="f2" id="f2" class="form-horizontal"
		action="<%= basePath %>daily/entry/manage/servlet.DailyServlet?service=changeDaily&DailyID=${daily.dailyId}"
		method="post">

		<div class="form-group">
			<label for="date" class="col-sm-2 control-label"> 日期 </label>
			<div class="col-sm-4">
				<input type="text" readonly id="date" name="date"
					class="form-datetime form-control" data-lte="#end-date"
					data-max="today" data-default="" value="${daily.submitD}"
					placeholder="请选择">
			</div>
			<label for="writor" class="col-sm-2 control-label"> 填写人 </label>
			<div class="col-sm-4">
				<input type="hidden" id="writor" name="writor" class="form-control"
					placeholder="填写人" value="${daily.empId}"></input> <input
					type="text" class="form-control" disabled="" placeholder="填写人"
					value="${daily.empName}"></input>
			</div>
		</div>

		<div class="form-group">
			<label for="test" class="col-sm-2 control-label"> 任务 </label>
			<div class="col-sm-10">
				<textarea id="test" name="test" class="form-control" rows="3"
					placeholder="填写任务详情">${daily.desc}</textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="workload" class="col-sm-2 control-label"> 工作量 </label>
			<div class="col-sm-4">
				<input name="workload" id="workload" class="form-control"
					placeholder="不超过16小时" value="${daily.totalWorkload}">
			</div>
			<label for="overtime" class="col-sm-2 control-label"> 加班量 </label>
			<div class="col-sm-4">
				<input name="overtime" id="overtime" class="form-control"
					placeholder="工作量中属于加班的部分" value="${daily.overTimeLoad}">
			</div>
		</div>

		<div class="form-group">
			<label for="item" class="col-sm-2 control-label"> 项目 </label>
			<div class="col-sm-4">
				<select name="item" id="item" class="form-control" name="combobox1"">
					<option value="${daily.prpId}">${daily.prjName}</option>
				</select>
			</div>
			<label for="model" class="col-sm-2 control-label"> PRP阶段 </label>
			<div class="col-sm-4">
				<select name="model" id="model" class="form-control"
					name="combobox2">
					<option value="${daily.prpId}">${daily.prpName}</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="modal-header">
			<h4 align="center" class="modal-title" id="myModalLabel">审核信息</h4>
		</div>
		<div class="form-group">
			<label for="appr-date" class="col-sm-2 control-label"> 审批日期 </label>
			<div class="col-sm-4">
				<input type="text" readonly class="form-datetime form-control"
					id="appr-date" data-max="today" placeholder="请选择"
					disabled="" value="${daily.reviewD}">
			</div>
			<label for="appr" class="col-sm-2 control-label"> 审批人 </label>
			<div class="col-sm-4">
				<input type="hidden" id="reviewer" class="form-control"
					placeholder="审批人" value="${daily.reviewEmpId}"> <input
					type="text" class="form-control" disabled="" placeholder="审批人"
					value="${daily.reviewEmpName}">
			</div>
		</div>

		<div class="form-group">
			<label for="reason" class="col-sm-2 control-label"> 拒绝原因 </label>
			<div class="col-sm-10">
				<textarea id="reason" class="form-control" rows="3"
					placeholder="填写拒绝原因" disabled="">${daily.reason}</textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="status" class="col-sm-2 control-label"> 审核状态 </label>
			<div class="col-sm-10">
				<select name="status" id="status" class="form-control" disable="">
					<option value="${daily.status}">${daily.status}</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 col-sm-offset-6">
				<button type="submit" class="btn btn-primary btn-block">修 改
				</button>
			</div>
			<div class="col-sm-3">
				<button class="btn btn-default btn-block" data-dismiss="modal">
					关 闭</button>
			</div>
		</div>

	</form>
</div>
<script>
	DMS.date.init($("#date"));
</script>