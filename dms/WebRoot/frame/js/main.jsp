<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.neusoft.dms.util.Constant" %>
<%@ page contentType="application/javascript;charset=UTF-8" %>
<%@ include file="/include/base.jsp"  %>

if (typeof Worker === "undefined") {
	window.location.href = "<%= basePath %>error/support";
}

var DMS = {
	title: "<%= TITLE %>",
	basePath: "<%= basePath %>",
	pageSize : <%= Constant.PAGE_SIZE %>
};

DMS.state = {
	FAILED : 0,	// 执行失败
	OK : 200,	// 成功
	BAD_REQUEST : 400,	// 错误的请求
	INTERNAL_SERVER_ERROR : 500,	// 服务器内部错误
	NOT_FOUND : 404,	// 未找到
	CONFLICT : 409	// 冲突
};

<%@ include file="/frame/js/main.js"  %>
