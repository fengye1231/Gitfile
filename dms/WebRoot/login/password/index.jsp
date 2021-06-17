<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header-nothing.jsp"></jsp:include>

<%-- 找回密码 --%>
<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">找回密码</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" class="form-horizontal" data-toggle="validator"
						action="<%= basePath %>login/login.servlet?service=forgetPassword" method="post">
                            <fieldset>
                                <div class="form-group">
                                <div class="col-sm-12">
                                    <input class="form-control" placeholder="员工编号" name="username" type="text" 
                                     maxlength="10"  id="username" required>
                                    <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                 <div class="form-group input-group">
                                 <div class="col-sm-12">
                                   <span class="input-group-addon">安全问题</span>
                                    <select class="form-control" name="question" id="question">
                                              <option value ="你父亲的名字是什么？"  >你父亲的名字是什么？</option>
                                              <option value ="你母亲的名字是什么？"  >你母亲的名字是什么？</option>
                                              <option value="你的高中的名字是什么？" >你的高中的名字是什么？</option>
                                    </select>
                                    </div>
                                </div>  
                               
                                <div class="form-group">
                                <div class="col-sm-12">
                                    <input class="form-control" placeholder="答案" name="answer" id="answer" type="text" required>
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