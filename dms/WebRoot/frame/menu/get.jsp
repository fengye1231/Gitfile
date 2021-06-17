<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.menu.dao.*, com.neusoft.dms.domain.MenuItem" %>
<%@ page import="com.neusoft.dms.menu.service.*" %>
<%@ include file="/include/base.jsp" %>

<%!

String toHTML(List<MenuItem> menuItems, String basePath) {
	StringBuilder sb = new StringBuilder();
	int len = menuItems.size();
	boolean[] processeds = new boolean[len];
	Arrays.fill(processeds, false);
	_toHTML(menuItems, processeds, MenuItem.NO_PARENT, 1, basePath, sb);
	return sb.toString();
}

void _toHTML(List<MenuItem> items, boolean[] processeds, int parentId, int level, String basePath, StringBuilder sb) {
	MenuService menuService = MenuServiceImpl.getInstance();
	MenuItem item = null;
	boolean hasChild = false;
	String link = null;
	for (int i = 0; i < processeds.length; i++) {
		if (!processeds[i]) {
			item = items.get(i);
			if (item.getparentId() == parentId) {
				processeds[i] = true;
				hasChild = menuService.hasChild(item, items);
				link = item.getLink() == null? "javascript:void(0);": basePath + item.getLink();
				
				sb.append("<li>");
					sb.append("<a href=\"")
						.append(link).append("\">")
						.append(level == 1? "<i class=\"fa fa-sitemap fa-fw\"></i> ": "")
						.append(item.getName())
						.append(hasChild? "<span class=\"fa arrow\"></span>": "")
						.append("</a>");
					if (hasChild) {
						sb.append("<ul class=\"nav ").append(getClassNameByLevel(level + 1)).append("\">");
						_toHTML(items, processeds, item.getId(), level + 1, basePath, sb);
						sb.append("</ul>");
					}
				sb.append("</li>");
			}
		}
	}
}

String getClassNameByLevel(int level) {
	String className = null;
	switch (level) {
	case 1:
		className = "";
		break;
	case 2:
		className = "nav-second-level";
		break;
	default:
		className = "nav-third-level";
		break;
	}
	return className;
}

%>

<%

List<MenuItem> items = (List<MenuItem>) getParam("menuItems", session);

%>

<%-- ************************************************************ --%>

<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
        	<%= toHTML(items, basePath) %>
        </ul>
    </div>
</div>