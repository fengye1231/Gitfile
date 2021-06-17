<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header-nothing.jsp"></jsp:include>

<% 
    if(session.getAttribute("employee") != null){
    	response.sendRedirect(response.encodeRedirectURL("/dms/daily/entry/manage"));
    }
%>


<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						用户登录
					</h3>
				</div>
				<div class="panel-body" >
					<form role="form" class="form-horizontal" data-toggle="validator"
						action="<%= basePath %>login/login.servlet?service=login" method="post">
							<div class="form-group">
								<div class="col-sm-12">
									<input class="form-control" placeholder="员工编号" name="username" id="username"
										type="text"  maxlength="10"  required autofocus>
										<div class="help-block with-errors"></div>
								</div>
								
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<input class="form-control" placeholder="密码" name="password" id="password"
										type="password"  required >
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-8">
									<input class="form-control" placeholder="验证码(不区分大小写)" name="rand" id="rand" 
										type="text"  maxlength="4" required> 
							
									<div class="help-block with-errors"></div>
								</div>
								<div class="col-sm-4">
									<img title="点击更换" onclick="refresh()" src="<%= basePath %>login/check.servlet" id="authImg">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<div class="checkbox">
										<label>
											<input name="checkbox" id = "checkbox" type="checkbox">
											记住我
										</label>
									</div>
								</div>
								<div class="col-sm-6">
									<a href="password/index.jsp" class="forget">忘记密码？</a>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<button type="submit"  class="btn btn-lg btn-primary btn-block" onclick="check()">
										登 录
									</button>
								</div>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<%-- 内容结束 --%>

<jsp:include page="/include/footer-nothing.jsp"></jsp:include>