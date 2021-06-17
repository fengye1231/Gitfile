<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<div class="container-fluid">
	<form name="f2" id="f2"
		action="/dms/servlet.DailyServlet?service=changeDaily&DailyID=${daily.dailyId}"
		method="post">
		<table border="0" class="table table-hover">
			<tbody>
				<tr>
					<td>
						日期
					</td>
					<td>
						<input type="text" readonly id="date" name="date"
							class="form-datetime form-control" data-lte="#end-date"
							data-max="today" data-default="${daily.submitDate}"
							placeholder="请选择">
					</td>
					<td>
						填写人
					</td>
					<td>
						<textarea id="writor" name="writor" class="form-control" rows="1"
							placeholder="填写人" disabled="">${daily.empId}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						任务
					</td>
					<td colspan="3">
						<textarea id="test" name="test" class="form-control" rows="3"
							placeholder="填写任务详情">${daily.desc}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						工作量
					</td>
					<td>
						<input name="workload" id="workload" class="form-control"
							placeholder="不超过16小时" value="${daily.totalWorkload}">
					</td>
					<td>
						加班量
					</td>
					<td>
						<input name="overtime" id="overtime" class="form-control"
							placeholder="工作量中属于加班的部分" value="${daily.overTimeLoad}">
					</td>
				</tr>
				<tr>
					<td>
						项目
					</td>
					<td>
						<select name="item" id="item" class="form-control"
							name="combobox1"">
							<option value="1">
								项目
							</option>
							<option value="2">
								项目2
							</option>
							<option value="3">
								项目3
							</option>
						</select>
					</td>
					<td>
						PRP阶段
					</td>
					<td>
						<select name="model" id="model" class="form-control"
							name="combobox2">
							<option value="${daily.prpId}">
								模块${daily.prpId}
							</option>
							<option value="1">
								模块
							</option>
							<option value="2">
								模块2
							</option>
							<option value="3">
								模块3
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						审批日期
					</td>
					<td>
						<input type="text" readonly class="form-datetime form-control"
							data-lte="#end-date" data-max="today" data-default="today"
							placeholder="请选择" disabled="">
					</td>
					<td>
						审批人
					</td>
					<td>
						<textarea class="form-control" rows="1" placeholder="填写人"
							disabled="">${daily.reviewEmpId}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						拒绝原因
					</td>
					<td colspan="3">
						<textarea class="form-control" rows="3" placeholder="填写拒绝原因"
							disabled="">${daily.reason}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						审核状态
					</td>
					<td colspan="3" class="alert alert-danger">
						${daily.status}
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
						<button type="submit" class="btn btn-primary">
							修改
						</button>
						<input class="btn btn-primary" type="button" value="返回"
							onclick="window.history.back(-1);" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>