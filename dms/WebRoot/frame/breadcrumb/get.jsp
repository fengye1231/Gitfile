<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.menu.dao.*, com.neusoft.dms.domain.MenuItem" %>
<%@ page import="com.neusoft.dms.menu.service.*" %>
<%@ include file="/include/base.jsp"  %>

<%!

String generateBreadcrumb(List<MenuItem> items, String currentURI, String basePath) {
	MenuService menuService = MenuServiceImpl.getInstance();
	MenuItem bestMatchMenuItem = menuService.getBestMatchMenuItem(items, currentURI);
	List<MenuItem> parents = menuService.getParentMenuItems(bestMatchMenuItem, items);
	return toHTML(parents, bestMatchMenuItem, basePath);
}


String toHTML(List<MenuItem> parents, MenuItem item, String basePath) {
	StringBuilder sb = new StringBuilder();
	if (item != null) {
		for (MenuItem parent : parents) {
			sb.append("<li><a href=\"")
				.append(parent.getLink() == null? "javascript:void(0);": basePath + parent.getLink())
				.append("\">")
				.append(parent.getName())
				.append("</a></li>");
		}
		sb.append("<li class=\"active\">")
			.append(item.getName())
			.append("</li>");
	}
	return sb.toString();
}

%>

<%

List<MenuItem> items = (List<MenuItem>) getParam("menuItems", session);

%>

<%-- ************************************************************* --%>

<ol class="breadcrumb">
	<%= generateBreadcrumb(items, uriWithQuery, basePath) %>
</ol>