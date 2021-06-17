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

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<form role="form" class="form-horizontal"
				action="<%= basePath %>info/employee/employee.servlet?service=addEmployee"
				method="post" data-toggle="validator" >
				<div class="panel panel-default">
					<div class="panel-heading">
						添加新员工(<font color="#f00"> * </font>为必填项)
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">
								员工编号
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="username"
									id="username" maxlength="8"  placeholder="不超过八个字符" data-error="不超过八个字符" required>
							</div>
							<label for="empName" class="col-sm-2 control-label">
								员工姓名
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="empName"
									id="empName" maxlength="5"  placeholder="不超过五个字符" data-error="不超过五个字符" required>
							</div>
						</div>

						<div class="form-group">
							<label for="joinDate" class="col-sm-2 control-label">
								入职时间
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-datetime form-control" data-max="today"
									type="text" placeholder="请选择" data-lte="#end-date"
									data-name="joinDate" id="joinDate" required>
							</div>
							<label for="isleader" class="col-sm-2 control-label">
								是否是领导
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<select class="form-control" name="isleader" id="isleader" required>
								  <option value="0">否</option>
								  <option value="1">是</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							
							<label for="sex" class="col-sm-2 control-label">
								性别
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<select class="form-control" name="sex" id="sex" required>  
								    <option value="男">男</option>
							        <option value="女">女</option>      
							        <option value="保密">保密</option>       
                                </select>
							</div>
							<label for="password" class="col-sm-2 control-label">
								默认密码
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input type="password" name="password" id="password"
									 class="form-control" value="emp" required>
							</div>
						</div>

						<div class="form-group">							
							<label for="email" class="col-sm-2 control-label">
								电子邮箱
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="email" name="email" id="email"
									required>
							</div>
							<label for="roleId" class="col-sm-2 control-label">
								角色
								<font color="#f00"> *</font>
							</label>
							<div class="col-sm-4">
							<select class="form-control" name="roleId" id="roleId" required> 
                                 <c:forEach items="${roles}" var="role">
								 <option value="${role.roleId}">
								    ${role.roleName}
							     </option>
								 </c:forEach>
                            </select>
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
                                    <option value="${leader.empId}">
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
								 <option value="${dept.id}">
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
									id="remark"></textarea>
							</div>
						</div>

					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-2 col-sm-offset-8">
								<button type="button" class="btn btn-default btn-block"
									onclick="window.location.href='../'">
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