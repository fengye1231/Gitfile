<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<form id="form-query" action="#" method="post" data-toggle="validator"
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						选择人员
					</div>
					<div class="panel-body">
						<div class="panel panel-default">
							<div class="panel-body no-padding-bottom">
								<div class="form-group ">
									<div class="col-sm-9 col-md-10">
										<div class="input-group">
											<span class="input-group-addon">部门</span>
											<select class="form-control" id="seltdept" name="seltdept">
												<c:forEach items="${pds}" var="pd">
													<option value="${pd.deptID}">
														${pd.deptName}
													</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-sm-3 col-md-2">
										<button type="submit" class="btn btn-primary btn-block">
											查询
										</button>
									</div>
								</div>
							</div>
						</div>
					<c:if test="${prID==12}">
						<div class="row">
							<div class="col-sm-12">
								<table id="table" data-dms-table></table>
							</div>
						</div>
					</c:if>
					<c:if test="${prID != 12 }">
						<div class="row">
							<div class="col-sm-12">
								<table id="stable" data-dms-table></table>
							</div>
						</div>
					</c:if>
					</div>
					<input type="hidden" id="prjid" value="${prjID}" />
					<input type="hidden" id="prid" value="${prID}" />
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3 col-md-2 col-md-offset-6">
								<a type="button" class="btn btn-default btn-block" href="/dms/daily/setting/proj/ProjectServlet?service=setStaff&prjID=${prjID}">
									返 回
								</a>
							</div>
							<div class="col-sm-3 col-md-2">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3 col-md-2">
								<button class="btn btn-primary btn-block" id="set-btn"
									type="button">
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