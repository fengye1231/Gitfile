<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 密码改为默认密码 --%>

  <div class="container">
               <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           <strong>密码修改</strong> 
                        </div>
                        
                      
                        <div class="panel-body">
                          <form role="form" class="form-horizontal" data-toggle="validator" 
                          action="/dms/employee.servlet?service=defaultPassword&username=${username}" method="post">
                         
                                                                                                            
                            
                            <div class="form-group">        
                                    <div class="col-md-4 col-md-offset-4">                 
                                                                                                                     新密码
                                            <input class="form-control" type="password" name="password1" id="password1"
                                            data-maxlength="10" value="123456" required>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
                            
                            <div class="form-group">        
                                    <div class="col-md-4 col-md-offset-4">                 
                                                                                                                     确认密码
                                            <input class="form-control" type="password" name="password2" id="password2" value="123456"
                                            data-maxlength="10" data-match="#password1" data-match-error="两次密码不一致" required>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
               
                             <div class="form-group">       
                                    
                                        
                                    <center>
                                    <button type="submit" class="btn btn-primary">确定</button>
                                    <button type="button" align="right" class="btn btn-primary" onclick="window.location.href='../'">取消</button> 
                                    </center>
                                    
                            </div> 
                                                                        
                       </form>
                     </div>     
                   </div>  
                </div>
         </div>
 </div>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>