<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<div class="container-fluid">
<div class="widget-content">

	<div class="container-fluid">
	         <div class="col-lg-12">
	         <c:set var="deptModify" value="${requestScope.deptModify}"/>
	         	<form action="/dms/Dept/Dept.servlet?deptAction=modifyDept&deptId=${deptModify.deptId}" method="post" data-toggle="validator" role="form" novalidate="true">
	    			<div class="panel panel-info">
	                        <div class="panel-heading">
	                            <b>修改部门信息（<font color="#ff0000">*</font>必填）</b>
	                        </div>
							<div class="panel-body">
								<div class="table-responsive">
								<c:if test="${!empty deptModify}">
	                                <table class="table table-striped">                 
	                                    <tbody>
	                                    	<tr>
	                                    		<td>
	                                    		<div class="col-sm-12">
	  												<div class="col-sm-6">
                  										<div class="input-group">
  															<span class="input-group-addon">子部门编号<font color="#ff0000">*</font></span>
         													<input onkeyup="validateCode(this.value)" value="${deptModify.deptCode}" class="form-control" placeholder="项目编码最多有8位" name="deptCode" type="text" maxlength="10" data-error="项目编码最多10位,最小4位" required="">
      													</div>
      					 							</div>
      					 							<div class="col-sm-6 check_top" align="left">
											        	<span id="code"></span>
											        </div>
      					 						</div>
	      					 					</td>
			 								</tr>
			 								<tr>
			 									<td>
			 									<div class="col-sm-12">
			 										<div class="col-sm-6">
                  										<div class="input-group">
  															<span class="input-group-addon">子部门名称<font color="#ff0000">*</font></span>
         													<input onkeyup="validateName(this.value)" value="${deptModify.deptName}" type="text" class="form-control" name="deptName" maxlength="20" placeholder="项目名称最多有20位" required="">
      													</div>
      													
	      					 						</div>
	      					 						<div class="col-sm-6 check_top" align="left">
												        <span id="name" ></span>
												    </div>
												</div>
			 									</td>
											</tr>
											<tr>
	                                    		<td>
	                                    		<div class="col-sm-12">
	  												<div class="col-sm-6">
	                  									<div class="input-group">
	  														<span class="input-group-addon">上级部门ID<font color="#ff0000">*</font></span>
	         												<input value="${deptModify.seniorDeptId}" class="form-control" placeholder="项目编码最多有8位" name="seniorDeptId" type="text" maxlength="10" data-error="项目编码最多10位,最小4位" required="">
	      												</div>
	      											</div>
	      					 					</div>
	      					 					</td>
		 									</tr>
											<tr>
												<td>
												<div class="col-sm-12">
													<div class="col-sm-10">
                  										<div class="input-group" style="height:80px">
  															<span class="input-group-addon">备注</span>
         													<input value="${deptModify.deptRemark}" type="text" class="form-control" name="deptRemark" maxlength="200" placeholder="备注最多有200位" style="height:80px">
      													</div>
	      					 						</div>
	      					 					</div>
												</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td>
													<div class="col-sm-12" align="right">
														<button type="submit" class="btn btn-primary" onsubmit="" style="pointer-events: all; cursor: pointer;">更新</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="reset" class="btn btn-primary">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<a type="button" class="btn btn-primary" href=".." onClick="goBack()">返回</a>
													</div>
												</td>
											</tr>
	              						</tfoot>
	                           		</table>
	                           		</c:if>
	                            </div>
	                       </div>
	                  </div> 
	            </form>
	         </div>
	</div>

</div>
</div>

<jsp:include page="/include/footer.jsp"></jsp:include>