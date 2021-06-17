<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp" %>
<jsp:include page="/include/header.jsp"></jsp:include>
    
    <link href="css/bootstrap-treeview.min.css" rel="stylesheet">
    <script src="js/bootstrap-treeview.js"></script>
  	
<%-- 内容开始 --%>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-6">
		    <jsp:include page="treeMenu.jsp"></jsp:include>
		</div>
	  	<div class="col-md-6">
			<jsp:include page="detailsDept.jsp"></jsp:include>
		</div>
	</div>
</div>
<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>