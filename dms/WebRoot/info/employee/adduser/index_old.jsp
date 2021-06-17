<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.employee.service.*, com.neusoft.dms.employee.domain.*" %>
<%@page import="com.neusoft.dms.dept.domain.DepartmentVo"%>
<%@page import="com.neusoft.dms.role.domain.RoleVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 添加新员工 --%>
<%
	List<DepartmentVo> depts = EmployeeServiceImpl.getInstance()
			.getRunningDept();
	request.setAttribute("depts", depts);
%>
<%
    List<RoleVo> roles = EmployeeServiceImpl.getInstance()
              .getRunningRole();
    request.setAttribute("roles", roles);
%>
<%
    List<EmployeeVo> leaders = EmployeeServiceImpl.getInstance()
              .getRunningLeader();
    request.setAttribute("leaders", leaders);
%>
  <div class="container">
               <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           <strong>添加新员工（<font color="#ff0000">*</font>为必填项）</strong> 
                        </div>
                        
                      
                        <div class="panel-body">
                          <form role="form" class="form-horizontal" data-toggle="validator" 
                          action="/dms/employee.servlet?service=addEmployee" method="post">
                         
                            <div class="form-group"> 
                                    <div class="col-md-6 col-md-offset-3">                                       
                                                                                                                      员工编号 &nbsp;<font color="#ff0000">*</font>
                                            <input type="text" class="form-control" name="username" id="username" 
                                             maxlength="10" 
                                            data-error="不能超过十个字符" required>
                                            <div class="help-block with-errors"></div>
                                    </div>
                            </div>
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">                                 
                                                                                                                     员工姓名&nbsp;<font color="#ff0000">*</font>
                                            <input type="text" class="form-control" name="empName" id="empName" 
                                            maxlength="4" required>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>                                      
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">                 
                                                                                                                     默认密码
                                            <input class="form-control" type="password" name="password" id="password"
                                            value="123456" data-maxlength="10" data-error="最少六个字符" required>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>                                            
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                     电子邮箱&nbsp;<font color="#ff0000">*</font>
                                            <input class="form-control" type="email" name="email" id="email" required>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
                             <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                     入职时间&nbsp;<font color="#ff0000">*</font>
                                            	<input class="form-datetime form-control" data-max="today" type="text" placeholder="请选择"
												data-lte="#end-date" data-name="joinDate" id="joinDate" readonly required>
										
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                      角色&nbsp;<font color="#ff0000">*</font>
                                            <select class="form-control" name="roleId" id="roleId" required>
                                               <c:forEach items="${roles}" var="role">
									        <option value="${role.roleId}">
										       ${role.roleName}
									        </option>
								             </c:forEach>
                                            </select>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                      是否是领导&nbsp;<font color="#ff0000">*</font>
                                            <select class="form-control" name="isleader" id="isleader" required>
                                              <option value ="0">否 </option>
                                              <option value ="1">是 </option>
                                            </select>
                
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                      上级领导&nbsp;<font color="#ff0000">*</font>
                                            <select class="form-control" name="superiorId" id="superiorId">
                                               <c:forEach items="${leaders}" var="leader">
                                              <option value="${leader.empId}">
                                                ${leader.username}
                                              </option>
                                              </c:forEach>                                         
                                            </select>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                     所属部门&nbsp;<font color="#ff0000">*</font>
                                            <select class="form-control" name="deptId" id="deptId">
                                               <c:forEach items="${depts}" var="dept">
									        <option value="${dept.id}">
										       ${dept.name}
									        </option>
								</c:forEach> 
                                            </select>
                                            <div class="help-block with-errors"></div>
                                    </div>       
                            </div>             
                            <div class="form-group">        
                                    <div class="col-md-6 col-md-offset-3">     
                                                                                                                      备注                                                                         
                                            <textarea class="form-control" cols=100% rows="4" name="remark"></textarea>
                                    </div>       
                            </div>                
                             <div class="form-group">        
                                    <div class="row" align="center">    
                                    <button type="submit" class="btn btn-primary">添加</button>
                                    <button type="button" class="btn btn-primary" onclick="window.location.href='../'">取消</button> 
                                    </div>
                            </div> 
                                                                        
                       </form>
                     </div>     
                   </div>  
                </div>
         </div>
 </div>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>