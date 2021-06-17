<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 个人密码修改 --%>

<div class="container">
	<div class="row">;
		<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
			<div class="panel-body">
				<form role="form" class="form-horizontal" data-toggle="validator"
					action="<%= basePath %>info/employee/employee.servlet?service=updatePassword" method="post">

					<div class="panel panel-default">
						<div class="panel-heading">
							个人密码修改
						</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="password1" class="col-sm-3 control-label">
									新密码
									<font color="#f00"> *</font>
								</label>
								<div class="col-sm-9">
									<input class="form-control" type="password" name="password1" pattern="^[A-Za-z0-9]+$"
										id="password1" data-maxlength="10" placeholder="仅由字母和数字组成，不超过10个字符" 
										data-error="您的密码格式不正确"
										required>
									<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group">
								<label for="password2" class="col-sm-3 control-label">
									确认密码
									<font color="#f00"> *</font>
								</label>
								<div class="col-sm-9">
									<input class="form-control" type="password" name="password2" pattern="^[A-Za-z0-9]+$"
										id="password2" data-maxlength="10" data-match="#password1"
										data-match-error="两次密码不一致" required>
									<div class="help-block with-errors"></div>
								</div>
							</div>

						</div>
						<div class="panel-footer">
							<div class="form-group no-margin-bottom">
								<div class="col-sm-3 col-sm-offset-6">
									<a href="../" class="btn btn-default btn-block">
										取 消 </a>
								</div>
								<div class="col-sm-3">
									<button type="submit" class="btn btn-primary btn-block" onclick="check()">
										确 定
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>