<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header-nothing.jsp"></jsp:include>

<%-- 内容开始 --%>





<center><h1>服务器出错了</h1></center>
<hr/>
<center><h4>
	<c:if test="${!empty msg}">${msg}</c:if>
	<c:if test="${empty msg}">服务器无法正确处理您的请求</c:if>
</h4></center>
<hr/>
<center><a href="<%= basePath %>${ url }"><h5><%= basePath %>${ url }</h5></a></center>






<%-- 内容结束 --%>

<jsp:include page="/include/footer-nothing.jsp"></jsp:include>