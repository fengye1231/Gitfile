<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>

<init-msg>${tip}</init-msg>

<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-body no-padding-bottom">
			<div class="row">
				<div class="col-sm-12">
					<form id="form-query" action="#" data-toggle="validator"
						method="post" class="no-margin-bottom">
						<div class="row">
							<div class="col-md-3 col-sm-6">
								<div class="form-group">
									<div class="input-group">
										<span class="input-group-addon">项目编码</span>
										<input type="text" class="form-control" id="projectCode"
											name="projectCode" maxlength="8">
										<div class="help-block with-errors"></div>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="form-group">
									<div class="input-group">
										<span class="input-group-addon">项目名称</span>
										<input type="text" class="form-control" id="projectName"
											name="projectName" maxlength="20">
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="form-group input-group">
									<span class="input-group-addon">开始日期</span>
									<input type="text" readonly id="start-date"
										class="form-datetime form-control" data-max="today"
										placeholder="请选择" data-name="start-date">
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="form-group input-group">
									<span class="input-group-addon">结束日期</span>
									<input type="text" readonly id="end-date"
										class="form-datetime form-control" data-max="today"
										placeholder="请选择" data-name="end-date">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-6 col-md-offset-6">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<a href="addProject" class="btn btn-primary btn-block">添加</a>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<button type="button" id="del-btn"
												class="btn btn-primary btn-block">
												删除
											</button>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<button type="reset"
												class="btn btn-default btn-block">
												重置
											</button>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-block">
												查询
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="table" data-dms-table></table>
		</div>
	</div>
</div>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>