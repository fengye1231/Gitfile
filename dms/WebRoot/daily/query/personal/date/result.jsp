<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>


      <div class="table-responsive">
  		<table border="0" class="table table-hover" style="background-color: #fff">
			<tbody>
			<tr>
			<th>项目</th>
			<th>阶段/模块</th>
			<th>任务描述</th>
			<th>工作量</th>
			<th>加班工作量</th>
			<th>审核状态</th>
			</tr>
			<tr>
			<c:forEach items="${dailys}" var="daily">
				<td>${daily.prjName}</td>
				<td>${daily.prpName}</td>
				<td>${daily.desc}</td>
				<td>${daily.totalWorkload}</td>
				<td>${daily.overTimeLoad}</td>
				<td>${daily.status}</td>
			</c:forEach>
			</tr>
	</tbody></table>
		</div>