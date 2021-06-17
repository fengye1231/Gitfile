<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.employee.service.*, com.neusoft.dms.employee.domain.*" %>
<%@page import="com.neusoft.dms.dept.domain.DepartmentVo"%>
<%@page import="com.neusoft.dms.role.domain.RoleVo"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 修改员工信息 --%>
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

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<form role="form" class="form-horizontal"
				action="<%= basePath %>info/employee/employee.servlet?service=updateEmployee&username=${employee.username}"
				method="post" data-toggle="validator" >
				<div class="panel panel-default">
					<div class="panel-heading">
						修改员工信息(<font color="#f00"> * </font>为必填项)
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">
								员工编号
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="username"
									id="username" required value="${employee.username}" disabled>
							</div>
							<label for="empName" class="col-sm-2 control-label">
								员工姓名
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="empName"
									id="empName" value="${employee.empName}" required>
							</div>
						</div>

						<div class="form-group">
							<label for="sex" class="col-sm-2 control-label">
								性别
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<select class="form-control" name="sex" id="sex" required>  
								    <option value="男" <c:if test="${employee.sex eq '男'}">selected="selected"</c:if>>男</option>
							        <option value="女" <c:if test="${employee.sex eq '女'}">selected="selected"</c:if>>女</option>      
							        <option value="保密" <c:if test="${employee.sex eq '保密'}">selected="selected"</c:if>>保密</option>       
                                </select>
							</div>
							<label for="isleader" class="col-sm-2 control-label">
								是否是领导
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<select class="form-control" name="isleader" id="isleader" required>
								  <option value="0" <c:if test="${employee.isleader eq 0}">selected="selected"</c:if>>否</option>
								  <option value="1" <c:if test="${employee.isleader eq 1}">selected="selected"</c:if>>是</option>
								</select>
							</div>
						</div>

						<div class="form-group">							
							<label for="email" class="col-sm-2 control-label">
								电子邮箱
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="email" name="email" id="email"
								value="${employee.email}"	required>
							</div>
							<label for="roleId" class="col-sm-2 control-label">
								角色
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
							<!--<c:if test="${1 eq employee.roleId}">disabled</c:if>-->
							<!--<c:if test="${sessionScope.employee.username eq employee.username}">disabled</c:if>-->
							<c:if test="${sessionScope.employee.username eq employee.username}">
							<input type="text" class="form-control" name="roleId" id="roleId" value="${employee.roleName}" disabled>
							<input type="hidden" name="roleId" id="roleId" value="${employee.roleId}">
							</c:if>
							<c:if test="${sessionScope.employee.username!= employee.username}">
							<select class="form-control" name="roleId" id="roleId" required> 
                                 <c:forEach items="${roles}" var="role">
							     <option value="${role.roleId}" <c:if test="${role.roleId eq employee.roleId}">selected="selected"</c:if>>
								    ${role.roleName}
							     </option>
								 </c:forEach>
                            </select>
                            </c:if>
							</div>
						</div>

						<div class="form-group">
							
							<label for="superiorId" class="col-sm-2 control-label">
								上级领导
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
							    <select class="form-control" name="superiorId" required>
                                    <c:forEach items="${leaders}" var="leader">
                                    <option value="${leader.empId}" <c:if test="${leader.empId eq employee.superiorId}">selected="selected"</c:if>>
                                       ${leader.username}
                                    </option>
                                    </c:forEach>
                                </select>
							</div>
							<label for="deptId" class="col-sm-2 control-label">
								所属部门
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
							 <select class="form-control" name="deptId" required>
                                 <c:forEach items="${depts}" var="dept">
								 <option value="${dept.id}" <c:if test="${dept.id eq employee.deptId}">selected="selected"</c:if>>
								    ${dept.name}
							     </option>
							</c:forEach> 
                            </select>
                                            
							</div>
						</div>

						<div class="form-group">
							<label for="remark" class="col-sm-2 control-label">
								备注
							</label>
							<div class="col-sm-10">
								<textarea class="form-control" cols=100% rows="4" name="remark"
									id="remark">${employee.remark}</textarea>
							</div>
						</div>

					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
						    <div class="col-sm-2 col-sm-offset-6">
								<a href="<%= basePath %>info/employee/employee.servlet?service=changePassword&username=${employee.username}" class="btn btn-default btn-block">
									修改密码 </a>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-default btn-block"
									onclick="window.location.href='../employee/index.jsp'">
									取 消
								</button>
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn btn-primary btn-block">
									提 交
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