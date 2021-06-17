<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<%
String deptId = request.getParameter("deptId");
//System.out.println(deptId);
%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
			<form action="<%= basePath %>daily/setting/dept/DeptServlet?deptAction=addChild&deptId=<%=deptId%>" method="post" data-toggle="validator" role="form" 
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						添加子部门
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="deptCode" class="col-sm-3 control-label">
								子部门编号
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input data-remote="/dms/daily/setting/dept/DeptServlet?deptAction=validateCode&deptCode=this.value" onkeyup="validateCode(this.value)" class="form-control" placeholder="项目编码最多有8位" name="deptCode" id="deptCode" type="text" maxlength="10" data-error="项目编码最多10位,最小4位" required>
								<div id="deptCodeEerors" class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="deptName" class="col-sm-3 control-label">
								子部门名称
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input onkeyup="validateName(this.value)" type="text" class="form-control" name="deptName" id="deptName" maxlength="20" placeholder="项目名称最多有20位" required>
								<div id="deptNameEerors" class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="end-date" class="col-sm-3 control-label">
								备注
							</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="remark"
									maxlength="200" placeholder="备注最多有200位" rows="3"
									name="deptRemark"></textarea>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block" href="../" onClick="goBack()">
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