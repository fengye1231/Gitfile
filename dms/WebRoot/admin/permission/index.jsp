<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<%-- 内容开始 --%>

<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-body no-padding-bottom">
			<div class="row">
				<form action="#" method="post" id="form-query"
					data-toggle="validator">
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">权限名称</span>
							<input class="form-control" type="text" name="perName" />
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">权限路径</span>
							<input class="form-control" type="text" name="perPath" />
						</div>
					</div>
					<div class="col-sm-4 col-md-2">
						<div class="form-group">
							<button type="reset" class="btn btn-default btn-block">
								重 置
							</button>
						</div>
					</div>
					<div class="col-sm-4 col-md-2">
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								查 询
							</button>
						</div>
					</div>
					<div class="col-sm-4 col-md-2">
						<div class="form-group">
							<a class="btn btn-primary btn-block"
								href="<%=basePath%>admin/permission/addpermission.jsp">增 加 </a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table id="table" data-dms-table></table>
		</div>
	</div>
</div>


<%-- 内容结束 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>