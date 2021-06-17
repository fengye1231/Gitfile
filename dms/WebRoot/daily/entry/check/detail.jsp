<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 align="center" class="modal-title" id="myModalLabel">日报信息</h4>
</div>

<div class="container-fluid " style="padding-top: 15px;">
	<form name="f4" id="f4" class="form-horizontal"
		action="<%=basePath%>daily/entry/check/servlet.DailyServlet?service=reasonDaily&DailyID=${daily.dailyId}"
		method="post">
		<div class="form-group">
			<label for="date" class="col-sm-2 control-label"> 姓名 </label>
			<div class="col-sm-4">
				<input class="form-control" placeholder="填写人" disabled=""
					value="${daily.empName}">
			</div>
			<label for="writor" class="col-sm-2 control-label">日期 </label>
			<div class="col-sm-4">
				<input type="text" readonly class="form-datetime form-control"
					data-lte="#end-date" data-max="today"
					data-default="" value="${daily.submitD}" placeholder="请选择" disabled="">
			</div>
		</div>
		<div class="form-group">
			<label for="workload" class="col-sm-2 control-label"> 工作量 </label>
			<div class="col-sm-4">
				<input class="form-control" disabled=""
					value="${daily.totalWorkload}">
			</div>
			<label for="overtime" class="col-sm-2 control-label"> 加班量 </label>
			<div class="col-sm-4">
				<input class="form-control" disabled=""
					value="${daily.overTimeLoad}">
			</div>
		</div>
		<div class="form-group">
			<label for="item" class="col-sm-2 control-label"> 项目 </label>
			<div class="col-sm-4">
				<select name="item" id="item" class="form-control" disabled="">
					<option value="${daily.prpId}">${daily.prjName}</option>
				</select>
			</div>
			<label for="model" class="col-sm-2 control-label"> PRP阶段 </label>
			<div class="col-sm-4">
				<select name="model" id="model" class="form-control" disabled="">
					<option value="${daily.prpId}">${daily.prpName}</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="test" class="col-sm-2 control-label">工作描述 </label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="3" placeholder="填写任务详情"
					disabled="">${daily.desc}</textarea>
			</div>
		</div>
		<hr>
		<div class="form-group">
			<h4 align="center" class="modal-title" id="myModalLabel">审核信息</h4>
		</div>
		<div class="form-group">
			<label for="appr-date" class="col-sm-2 control-label"> 审批日期 </label>
			<div class="col-sm-4">
				<input type="text" readonly class="form-datetime form-control"
					id="appr-date" data-max="today" placeholder="请选择"
					disabled="" value="${daily.reviewD}" disable="">
			</div>
			<label for="status" class="col-sm-2 control-label"> 审核状态 </label>
			<div class="col-sm-4">
				<select name="status" id="status" class="form-control">
				    <option value="${daily.status}">${daily.status}</option>
					<option value="未通过">未通过</option>
					<option value="已通过">已通过</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="reason" class="col-sm-2 control-label"> 原因 </label>
			<div class="col-sm-10">
				<textarea id="reason" class="form-control" rows="3"
					placeholder="填写拒绝原因">${daily.reason}</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 col-sm-offset-6">
				<button type="submit" class="btn btn-primary btn-block">修 改</button>
			</div>
			<div class="col-sm-3">
				<button type="button" class="btn btn-default btn-block" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</form>
</div>
<script>
	DMS.date.init($("#date"));
</script>