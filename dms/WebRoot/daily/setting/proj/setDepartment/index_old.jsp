<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<link href="/dms/daily/setting/proj/setDepartment/css/bootstrap-treeview.min.css" rel="stylesheet">
<script src="/dms/daily/setting/proj/setDepartment/js/bootstrap-treeview.js"></script>
  	
<%-- 内容开始 --%>
<div class="container-fluid">
<div class="row">
<div class="panel panel-info">
	<div class="panel-heading">
		<b>部门设置</b>
	</div>
	<div class="panel-body">
		<form action="#" data-toggle="validator" method="post">
			<div class="col-sm-3"></div>
			<div class="col-md-6">
	    		<jsp:include page="/daily/setting/dept/treeMenu.jsp"></jsp:include>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-12">
			<div align="center">
				<input type="hidden" id="prjid" value="${prjID}"/>
				<button type="submit" id="set-btn" class="btn btn-primary">设置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a type="button" class="btn btn-primary" onClick="goBack()">返回</a>
			</div>
			</div>
		</form>
	</div>
	</div>
</div>
</div>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>