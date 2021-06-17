<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-responsive">
<c:set var="deptSenior" value="${sessionScope.deptSenior}"/>
<c:set var="deptCurr" value="${sessionScope.deptCurr}"/>
<c:set var="childList" value="${sessionScope.childList}"/>
	<table class="table table-bordered table-striped">
	        <thead>
	        	<tr>
	            	<th colspan="4"><center>职能部门设定-查看部门</center></th>
	          	</tr>
	          	<c:if test="${empty deptSenior}">
	        		<tr>
		         		<td height="200px" colspan="4"><center>没有部门</center></td>
		        	</tr>
				</c:if>
				
				<c:if test="${!empty deptSenior}">
		          <tr>
		            <th>上级部门</th>
		            <td colspan="3"><a href="/dms/daily/setting/dept/?deptId=${deptSenior.deptId}"><c:out value="${deptSenior.deptName}" escapeXml="false"/></a></td>
		          </tr>
		        </c:if>
	        </thead>
	        
	        <tbody>
				<c:if test="${!empty deptCurr}">
		          <tr>
		         	<th>部门编号</th>
			 		<td><c:out value="${deptCurr.deptCode}" escapeXml="false"/></td>
			 		<th>部门名称</th>
			 		<td><c:out value="${deptCurr.deptName}" escapeXml="false"/></td>
		          </tr>
		          <tr>
		            <th>备注</th>
					<td colspan="3"><c:out value="${deptCurr.deptRemark}" escapeXml="false"/></td>
		          </tr>
		        </c:if>
	        </tbody>
			<tfoot>
			  <tr>
	            <td align="right" colspan="4">
	            	<div class="col-sm-3">
			  		</div>
			  		<div class="col-sm-3">
			  		</div>
		            <div class="col-sm-2">
						<a href="/dms/daily/setting/dept/addChildDept.jsp?deptId=${deptCurr.deptId}">
							<button type="button" class="btn btn-primary btn-block">添加</button>
						</a>
			  		</div>
			  		<div class="col-sm-2">
			  			<%-- 
						<a href="/dms/daily/setting/dept/DeptServlet?deptAction=viewModify&deptId=${deptCurr.deptId}">
							<button type="button" id="modify-btn" class="btn btn-primary btn-block">修改</button>
						</a>
						--%>
						<a href="modifyDept?deptId=${deptCurr.deptId}">
							<button type="button" id="modify-btn" class="btn btn-primary btn-block">修改</button>
						</a>
			  		</div>
			  		<div class="col-sm-2">
			  			<c:if test="${!empty childList}">
							<button type="button" class="btn btn-primary btn-block" disabled="true">删除</button>
						</c:if>
						<c:if test="${empty childList}">
							<button type="button" onclick="deleteItem(${deptCurr.deptId})" id="del-btn" class="btn btn-primary btn-block">删除</button>
							
							<%--
							<a href="/dms/daily/setting/dept/DeptServlet?deptAction=deleteDept&deptId=${deptCurr.deptId}">
								<button type="button" id="del-btn" class="btn btn-primary btn-block">删除</button>
							</a>
							 --%>
						</c:if>
			  		</div>
			  	</td>
			  </tr>
			</tfoot>
	</table>
</div>
<div class="table-responsive">

	<table id="table" data-dms-table></table>
<!--  
	<table class="table table-bordered table-striped">
	        <thead>
	          <tr>
	            <th>子部门编号</th>
			  	<th>子部门名称</th>
			  	<th colspan="2">操作</th>
	          </tr>
	        </thead>
	        <tbody>
	        
	        <c:if test="${empty childList}">
	        	<tr>
		         	<th height="200px" colspan="4"><center>没有子部门</center></th>
		        </tr>
			</c:if>
			
			
			<c:if test="${!empty childList}">
		        <c:forEach var="deptBean" items="${childList}">
			        <tr>
				  		<td><c:out value="${deptBean.deptCode}" escapeXml="false"/></td>
				  		<td><a href="/dms/daily/setting/dept/?deptId=${deptBean.deptId}"><c:out value="${deptBean.deptName}" escapeXml="false"/></a></td>
				  		<td>
					  		<a href="/dms/servlet/DeptServlet?deptAction=viewModify&deptId=${deptBean.deptId}">修改</a>&nbsp;&nbsp;
					  		<a href="/dms/servlet/DeptServlet?deptAction=deleteChild&deptId=${deptBean.deptId}">删除</a>
				  		</td>
				  	</tr>
				</c:forEach>
			</c:if>
		  	</tbody>
	</table>
	-->
	<center>
		<div id="Pagination" class="pagination"></div> 
		<!-- 
			<ul id="pagination" class="pagination" data-total="5" data-visible="7" data-start="1"></ul>
		 -->
	</center>
</div>