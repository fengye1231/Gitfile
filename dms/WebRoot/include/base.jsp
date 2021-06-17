<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

initParamContainer(session);

String path = request.getContextPath();
String host = request.getScheme() + "://" + request.getServerName();
String basePath = host + ":" + request.getServerPort() + path + "/";
String requestPath = host + ":" + request.getServerPort() + request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf("/") + 1);
String uriWithQuery = request.getServletPath() + (request.getQueryString() != null? "?" + request.getQueryString(): "");
String realUrl = host + ":" + request.getServerPort() + path + uriWithQuery;

%>

<%!

final static String PARAM_NAME = "PARAM";

final static String TITLE = "日报管理系统";
final static String VERSION = "Ver 1.0";
final static String COPYRIGHT = "© 2001-" + Calendar.getInstance().get(Calendar.YEAR) + " Neusoft";

%>

<%!

void initParamContainer(HttpSession session) {
	if (session.getAttribute(PARAM_NAME) == null) {
		session.setAttribute(PARAM_NAME, new HashMap<String, Object>());
	}
}

void setParam(String name, Object value, HttpSession session) {
	Map<String, Object> param = (Map<String, Object>) session.getAttribute(PARAM_NAME);
	param.put(name, value);
}

Object getParam(String name, HttpSession session) {
	Map<String, Object> param = (Map<String, Object>) session.getAttribute(PARAM_NAME);
	return param.get(name);
}

String getTitle(String name) {
	return (name == null || name.length() == 0? TITLE: name + " | " + TITLE);
}

String getTitle() {
	return getTitle(null);
}

%>