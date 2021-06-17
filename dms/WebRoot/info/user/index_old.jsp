<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 个人信息 --%>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>修改个人信息（<font color="#ff0000">*</font>为不可修改项，如果有错误，请联系管理员处理)</strong>
					<a href="modifypassword" class="reset">修改密码</a>
				</div>
				<form role="form" class="form-horizontal" action="/dms/employee.servlet?service=updateEmployeeInfo" method="post">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered">
								<tbody>
									<tr>
										<td align="center">
											员工编号 &nbsp;
											<font color="#ff0000">*</font>
										</td>
										<td>
											<input class="form-control" type="text" name="username"
												id="username" value="${employee.username}" readonly disabled>   
										</td>
										<td align="center">
											员工姓名&nbsp;
											<font color="#ff0000">*</font>
										</td>
										<td>
											<input class="form-control" type="text" name="empName" id="empName"
												value="${employee.empName}" disabled>
										</td>
									</tr>
									<tr>
										<td align="center">
											入职时间
										</td>
										<td>
											<input class="form-datetime form-control" data-max="today" type="text" placeholder="请选择"
												data-lte="#end-date" data-name="joinDate" id="joinDate"  value="${employee.joinDate}" disabled>
										</td>
										<td align="center">
											是否是领导&nbsp;
											<font color="#ff0000">*</font>
										</td>
										<td>
										    <input class="form-control" name="isleader"  type="text" value="${employee.isleader}" disabled>
										    
										</td>						
									</tr>
									<tr>
										<td align="center">
											英文名
										</td>
										<td>
											<input class="form-control" type="text" name="englishName" value="${employee.englishName}"
												id="englishName">
										</td>
										<td align="center">
											性别											
										</td>
										<td>
											<input class="form-control" type="text" name="sex" value="${employee.sex}"
												id="sex"  data-maxlength="1" placeholder="男  或   女">
										</td>
									</tr>
									<tr>
										<td align="center">
											出生日期					
										</td>
										<td>
											<input type="text" readonly data-name="birth" id="birth" value="${employee.birth}"
					class="form-datetime form-control" data-lte="#end-date"
					data-max="today" placeholder="请选择">
										</td>
										<td align="center">
											籍贯
										</td>
										<td>
											<input class="form-control" type="text" name="nativePlace"
												id="nativePlace" value="${employee.nativePlace}">
										</td>
									</tr>
									<tr>
										<td align="center">
											办公电话
										</td>
										<td>
											<input class="form-control" type="text" name="phone"
												id="phone" pattern="^([0-9]{1,})$" maxlength="10" value="${employee.phone}">
										</td>
										<td align="center">
											电子邮箱&nbsp;
											<font color="#ff0000">*</font>
										</td>
										<td>
											<input class="form-control" type="email" name="email"
												id="email" readonly disabled value="${employee.email}">
										</td>
									</tr>
									<tr>
										<td align="center">
											QQ号码
										</td>
										<td>
											<input class="form-control" type="text" name="qq" id="qq"
											pattern="^([0-9]{1,})$" maxlength="10" value="${employee.qq}">
											
										</td>
										<td align="center">
											传真号码
										</td>
										<td>
											<input class="form-control" type="text" name="fax" id="fax"
											pattern="^([0-9]{1,})$" maxlength="10" value="${employee.fax}">
											
										</td>
									</tr>
									<tr>
										<td align="center">
											家庭电话
										</td>
										<td>
											<input class="form-control" type="text" name="homeTel"
												id="homeTel" pattern="^([0-9]{1,})$" maxlength="10" value="${employee.homeTel}">
										</td>
										<td align="center">
											家庭住址
										</td>
										<td>
											<input class="form-control" type="text" name="address"
												id="address" value="${employee.address}">
										</td>
									</tr>
									<tr>
										<td align="center">
											毕业院校
										</td>
										<td>
											<input class="form-control" type="text" name="school"
												id="school" value="${employee.school}">
										</td>
										<td align="center">
											毕业时间
										</td>
										<td>
											<input type="text" readonly id="graduateDate" data-name="graduateDate"
					class="form-datetime form-control" data-lte="#end-date"
					data-max="today" placeholder="请选择" value="${employee.graduateDate}">
										</td>
									</tr>
									<tr>
										<td align="center">
											安全问题
										</td>
										<td>
											<select class="form-control" name="question" value="${employee.question}">
												<option value="你父亲的名字是什么？">
													你父亲的名字是什么？
												</option>
												<option value="你母亲的名字是什么？">
													你母亲的名字是什么？
												</option>
												<option value="你的高中的名字是什么？">
													你的高中的名字是什么？
												</option>
											</select>
										</td>
										<td align="center">
											答案
										</td>
										<td>
											<input class="form-control" type="text" name="answer"
												id="answer" value="${employee.answer}">
										</td>
									</tr>
									<tr>
										<td align="center">
											角色&nbsp;
											<font color="#ff0000">*</font>
										</td>
										<td>
											<input class="form-control" type="text" name="roleId" id="roleId" value="${employee.roleName}" disabled>
                               
										</td>
										<td align="center">
											上级领导&nbsp;
											<font color="#ff0000">*</font>										
										</td>
										<td>
											<input class="form-control" type="text" name="superiorId" id="superiorId" value="${employee.superiorName}" disabled>
                                              
										</td>
									</tr>
									<tr>
										<td align="center">
											所属部门&nbsp;
											<font color="#ff0000">*</font>
										</td>
										<td>
											<input class="form-control" type="text" name="deptId"
												id="deptId" value="${employee.deptName}" disabled>
										</td>
										<td></td>
										<td></td>
								
									</tr>
									<tr>
										<td colspan="1" align="center">
											备注
										</td>
										<td colspan="3">
											<textarea class="form-control"  cols=100% rows="4" name="remark"  id="remark">${employee.remark}</textarea>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="right">
											<button type="submit" class="btn btn-primary">
												提交
											</button>
										</td>

										<td colspan="2">
											<button type="button" class="btn btn-primary"
												onclick="window.location.href=''">
												取消
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>




<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>