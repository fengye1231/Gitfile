<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header-nothing.jsp"></jsp:include>

<%-- 重设密码 --%>
<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">重置密码</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" class="form-horizontal" data-toggle="validator"
						method="post" action="<%= basePath %>login/login.servlet?service=resetPassword">
                            <fieldset>
                                <div class="form-group">
                                   <div class="col-sm-12">
                                    <input class="form-control" placeholder="新密码" name="password1" type="password" 
                                    id="password1" maxlength="10"  pattern="^[A-Za-z0-9]+$" placeholder="仅由字母和数字组成，不超过10个字符"
                                    data-error="您的密码格式不正确" required>
                                    <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                <div class="col-sm-12">
                                    <input class="form-control" placeholder="确认密码" name="password2" pattern="^[A-Za-z0-9]+$"
                                    type="password" id="password2" maxlength="10" data-match="#password1" 
                                    data-match-error="两次密码不一致"
                                    required>
                                    <div class="help-block with-errors"></div>
                                    </div>
                                </div> 
                         
                                <button type="submit" class="btn btn-lg btn-primary btn-block">确认</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>







<%-- 内容结束 --%>

<jsp:include page="/include/footer-nothing.jsp"></jsp:include>