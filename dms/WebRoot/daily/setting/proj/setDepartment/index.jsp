<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>
<link
	href="/dms/daily/setting/proj/setDepartment/css/bootstrap-treeview.min.css"
	rel="stylesheet">
<script
	src="/dms/daily/setting/proj/setDepartment/js/bootstrap-treeview.js">
</script>

<%-- 内容开始 --%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
			<form action="#" method="post" data-toggle="validator"
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						部门设置
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-12">
								<jsp:include page="/daily/setting/dept/treeMenu.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<input type="hidden" id="prjid" value="${prjID}" />
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block" href="/dms/daily/setting/proj/index.jsp">
									返 回
								</a>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-primary btn-block" id="set-btn"
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