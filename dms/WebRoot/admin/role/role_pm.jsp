<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp"></jsp:include>



<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
			<form action="<%=basePath%>admin/role/servlet/RoleServlet"
				method="post" data-toggle="validator" role="form"
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						${role}权限
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-12">
								<ul class="list-group">
									<c:forEach items="${permList}" var="perm" varStatus="status">
										<div class="list-group-item">
											<div class="checkbox" style="padding-top: 3px;">
												<label>
													<input type="checkbox" name="perm" id="${perm.perId}">
													${perm.perName}
												</label>
											</div>
										</div>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					<input type="hidden" id="arrp" name="arrp" />
					<input type="hidden" name="role"
						value="${role}">
					<input type="hidden" name="service" value="daoPerm" />
					<input id="check" type="hidden" value="${checklist}">
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block"
									href="<%=basePath%>admin/role"> 返 回 </a>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-primary btn-block" type=button onclick="chk()">
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

<script>
$(function(){
	hascheck();
});
</script>

<jsp:include page="/include/footer.jsp"></jsp:include>