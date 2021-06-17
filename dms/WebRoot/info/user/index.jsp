<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<%@ page import="com.neusoft.dms.employee.service.*, com.neusoft.dms.employee.domain.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 个人信息 --%>
<%

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

EmployeeVo emp = (EmployeeVo) session.getAttribute("employee");
String birth = sdf.format(emp.getBirth());
String graduateDate = sdf.format(emp.getGraduateDate());

request.setAttribute("birth", birth);
request.setAttribute("graduateDate", graduateDate);

 %>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<form role="form" class="form-horizontal"
				action="<%= basePath %>info/employee/employee.servlet?service=updateEmployeeInfo&username=${employee.username}"
				method="post" data-toggle="validator" >
				<div class="panel panel-default">
					<div class="panel-heading">
						修改个人信息
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">
								员工编号
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="username"
									id="username" value="${employee.username}" readonly disabled>
							</div>
							<label for="empName" class="col-sm-2 control-label">
								员工姓名
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="empName"
									id="empName" value="${employee.empName}" disabled>
							</div>
						</div>

						<div class="form-group">
							<label for="joinDate" class="col-sm-2 control-label">
								入职时间
							</label>
							<div class="col-sm-4">
								<input class="form-datetime form-control" data-max="today"
									type="text" placeholder="请选择" data-lte="#end-date"
									data-name="joinDate" id="joinDate" data-default="${employee.joinDate}"
									disabled>
							</div>
							<label for="isleader" class="col-sm-2 control-label">
								是否是领导
							</label>
							<div class="col-sm-4">
								<input class="form-control" name="isleader" id="isleader"
									type="text" 
									<c:if test="${employee.isleader eq 0}">value="否"</c:if>
									<c:if test="${employee.isleader eq 1}">value="是"</c:if>
								 disabled>	
							</div>
						</div>

						<div class="form-group">
							<label for="englishName" class="col-sm-2 control-label">
								英文名
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="englishName"
									value="${employee.englishName}" id="englishName">
							</div>
							<label for="sex" class="col-sm-2 control-label">
								性别
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="sex"
									value="${employee.sex}" id="sex" disabled>
							</div>
						</div>

						<div class="form-group">
							<label for="birth" class="col-sm-2 control-label">
								出生日期
							</label>
							<div class="col-sm-4">
								<input type="text" readonly data-name="birth" id="birth"
									 class="form-datetime form-control"
									data-max="today" placeholder="请选择" data-default="${birth}">
							</div>
							<label for="nativePlace" class="col-sm-2 control-label">
								籍贯
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="nativePlace"
									id="nativePlace" value="${employee.nativePlace}">
							</div>
						</div>

						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label">
								办公电话
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="phone" id="phone"
									pattern="^([0-9]{1,})$" maxlength="12"
									value="${employee.phone}">
							</div>
							<label for="email" class="col-sm-2 control-label">
								电子邮箱
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="email" name="email" id="email"
									readonly disabled value="${employee.email}">
							</div>
						</div>

						<div class="form-group">
							<label for="qq" class="col-sm-2 control-label">
								QQ号码
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="qq" id="qq"
									pattern="^([0-9]{1,})$" maxlength="12" value="${employee.qq}">
							</div>
							<label for="fax" class="col-sm-2 control-label">
								传真号码
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="fax" id="fax"
									pattern="^([0-9]{1,})$" maxlength="12" value="${employee.fax}">
							</div>
						</div>

						<div class="form-group">
							<label for="homeTel" class="col-sm-2 control-label">
								家庭电话
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="homeTel"
									id="homeTel" pattern="^([0-9]{1,})$" maxlength="12"
									value="${employee.homeTel}">
							</div>
							<label for="address" class="col-sm-2 control-label">
								家庭住址
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="address"
									id="address" value="${employee.address}">
							</div>
						</div>

						<div class="form-group">
							<label for="school" class="col-sm-2 control-label">
								毕业院校
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="school"
									id="school" value="${employee.school}">
							</div>
							<label for="graduateDate" class="col-sm-2 control-label">
								毕业时间
							</label>
							<div class="col-sm-4">
								<input type="text" readonly id="graduateDate"
									data-name="graduateDate" class="form-datetime form-control"
									data-max="today" placeholder="请选择"
									data-default="${graduateDate}">
							</div>
						</div>

						<div class="form-group">
							<label for="question" class="col-sm-2 control-label">
								安全问题
							</label>
							<div class="col-sm-4">
								<select class="form-control" name="question" id="question">
									<option value="你父亲的名字是什么？" <c:if test="${employee.question eq '你父亲的名字是什么？'}">selected="selected"</c:if>>你父亲的名字是什么？
									</option>
									<option value="你母亲的名字是什么？" <c:if test="${employee.question eq '你母亲的名字是什么？'}">selected="selected"</c:if>>你母亲的名字是什么？
									</option>
									<option value="你的高中的名字是什么？" <c:if test="${employee.question eq '你的高中的名字是什么？'}">selected="selected"</c:if>>你的高中的名字是什么？
									</option>
								</select>
							</div>
							<label for="answer" class="col-sm-2 control-label">
								答案
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="answer"
									id="answer" value="${employee.answer}">
							</div>
						</div>

						<div class="form-group">
							<label for="roleId" class="col-sm-2 control-label">
								角色
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="roleId"
									id="roleId" value="${employee.roleName}" disabled>
							</div>
							<label for="superiorId" class="col-sm-2 control-label">
								上级领导
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="superiorId"
									id="superiorId" value="${employee.superiorName}" disabled>
							</div>
						</div>

						<div class="form-group">
							<label for="deptId" class="col-sm-2 control-label">
								所属部门
							</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="deptId"
									id="deptId" value="${employee.deptName}" disabled>
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
								<a href="<%=basePath %>info/user/modifypassword/index.jsp" class="btn btn-default btn-block">
									修改密码 </a>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-default btn-block"
									onclick="window.location.href='index.jsp'">
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