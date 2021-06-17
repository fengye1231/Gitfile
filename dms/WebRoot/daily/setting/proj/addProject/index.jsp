<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>

<init-msg>${tip}</init-msg>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
			<form action="<%= basePath %>/daily/setting/proj/ProjectServlet?service=addPrj" method="post" data-toggle="validator" role="form" 
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						添加项目
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="projectCode" class="col-sm-3 control-label">
								项目编码
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="projectCode"
									name="projectCode" maxlength="8" required
									value="${prj.prjCode}">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="projectName" class="col-sm-3 control-label">
								项目名称
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="projectName"
									name="projectName" maxlength="20" required
									value="${prj.prjName}">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="start-date" class="col-sm-3 control-label">
								开始时间
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input type="text" id="start-date" readonly
									class="form-datetime form-control" data-lte="#end-date"
									data-name="start-Date" placeholder="请选择" required
									data-default="${prj.startDate}">
							</div>
						</div>
						<div class="form-group">
							<label for="end-date" class="col-sm-3 control-label">
								结束时间
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input type="text" id="end-date" readonly
									class="form-datetime form-control" data-name="end-Date"
									placeholder="请选择" required
									data-default="${prj.endDate}">
							</div>
						</div>
						<div class="form-group">
							<label for="projectStatus" class="col-sm-3 control-label">
								项目状态
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<select id="projectStatus" class="form-control"
									name="projectStatus">
									<option value="运行中">
										运行中
									</option>
									<option value="挂起">
										挂起
									</option>
									<option value="关闭">
										关闭
									</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="end-date" class="col-sm-3 control-label">
								备注
							</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="remark"
									maxlength="200" placeholder="备注最多有200位"
									style="height: 80px" name="remark">${prj.remark}</textarea>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a type="button" class="btn btn-default btn-block" onclick="goBack()">
									返 回
								</a>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-primary btn-block"
									type="submit">
									确 认
								</button>
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