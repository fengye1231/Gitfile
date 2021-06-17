<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<body onload="tipMsg('${tip}')">
<init-msg>${tip}</init-msg>

<div class="container-fluid">

<div class="panel panel-default">
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12">
				<form id="form-query" action="#" data-toggle="validator" method="get">
					<div class="row">
					</div>
					<div class="row">
						<div class="col-sm-4"></div>
                		<div class="col-sm-3">
							<div class="form-group input-group">
								<span class="input-group-addon">部门</span>
								<select class="form-control" id="seltdept">
									<c:forEach items="${pds}" var="pd">
										<option value="${pd.deptID}">${pd.deptName}</option>
          							</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-sm-1">
							<button type="submit" class="btn btn-primary btn-block">查询</button>
						</div>
					</div>
					<c:if test="${prID != 12 }">
						<div class="row">
							<div class="col-sm-12">
								<table id="table" data-dms-table></table>
							</div>
						</div>
					</c:if>
					<c:if test="${prID == 12 }">
						<div class="row">
							<div class="col-sm-12">
								<table id="stable" data-dms-table></table>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-sm-12" >
							<div class="row">
								<div class="col-sm-2">
								</div>
								<div class="col-sm-6" align="center">
								    <input type="hidden" id="prjid" value="${prjID}"/>
								    <input type="hidden" id="prid" value="${prID}"/>
									<button type="button" id="set-btn" class="btn btn-primary ">设置</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" class="btn btn-primary btn-outline">重置</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-primary" onClick="goBack()">返回</a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</div>

<div class="row">
	<div class="col-sm-12">
		<table id="table" data-dms-table></table>
	</div>
</div>

<div class="row">

</div>


<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>