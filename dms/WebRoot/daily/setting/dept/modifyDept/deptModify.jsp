<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<c:if test="${!empty deptModify}">
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
			<form action="<%= basePath %>daily/setting/dept/DeptServlet?deptAction=modifyDept&deptId=${deptModify.deptId}" method="post" data-toggle="validator" role="form" 
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						修改部门信息
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="deptCode" class="col-sm-3 control-label">
								部门编号
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input value="${deptModify.deptCode}" class="form-control" placeholder="项目编码最多有8位" name="deptCode" id="deptCode" type="text" maxlength="10" data-error="项目编码最多10位,最小4位" required>
								<div id="deptCodeEerors" class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="deptName" class="col-sm-3 control-label">
								部门名称
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input value="${deptModify.deptName}" type="text" class="form-control" name="deptName" id="deptName" maxlength="20" placeholder="项目名称最多有20位" required>
								<div id="deptNameEerors" class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="seniorDeptId" class="col-sm-3 control-label">
								上级部门ID
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input value="${deptModify.seniorDeptId}" class="form-control" placeholder="项目编码最多有8位" name="seniorDeptId" id="seniorDeptId" type="text" maxlength="10" data-error="项目编码最多10位,最小4位" required>
							</div>
						</div>
						<div class="form-group">
							<label for="end-date" class="col-sm-3 control-label">
								备注
							</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="remark" name="deptRemark" 
									maxlength="200" placeholder="备注最多有200位" rows="3"
									>${deptModify.deptRemark}</textarea>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block" href="../">
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
</c:if>

<jsp:include page="/include/footer.jsp"></jsp:include>