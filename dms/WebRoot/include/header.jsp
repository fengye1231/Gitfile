<%@page import="com.neusoft.dms.employee.domain.EmployeeVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.menu.dao.*, com.neusoft.dms.menu.domain.*, com.neusoft.dms.domain.*" %>
<%@ page import="com.neusoft.dms.menu.service.*" %>
<%@ page import="com.neusoft.dms.role.service.*" %>
<%@ page import="com.neusoft.dms.permission.service.*" %>
<%@ page import="com.neusoft.dms.permission.domain.*" %>
<%@ page import="com.neusoft.dms.util.*" %>
<%@ page import="java.util.regex.*" %>
<%@ include file="/include/base.jsp"  %>


<%!

private static final String SESSION_PER_PATH_PATTERN = "permissionPathPatterns";

List<MenuItem> getMenuItems(int empId, HttpSession session) throws ServiceException {
	List<Pattern> patterns = (List<Pattern>) session.getAttribute(SESSION_PER_PATH_PATTERN);
	List<Menu> items = MenuServiceImpl.getInstance().listAllMenu();
	List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	if (patterns != null) {
		for (Menu item : items) {
			int parentId = item.getParentMenuId() == 0? MenuItem.NO_PARENT: item.getParentMenuId();

			if (item.getMenuPath() == null) {
				menuItems.add(new MenuItem(
						item.getMenuId(), item.getMenuName(), 
						item.getMenuPath(), parentId));
			} else {
				for (Pattern pattern : patterns) {
					
					if (pattern.matcher(item.getMenuPath()).matches()) {
						menuItems.add(new MenuItem(
								item.getMenuId(), item.getMenuName(), 
								item.getMenuPath(), parentId));
						break;
					}
				}
			}
		}
	}

	return menuItems;
}

%>

<%

/*
getMenuItems(1, session);

MenuDao menuDao = new MenuDaoImpl();
List<MenuItem> items = menuDao.getItems();
setParam("menuItems", items, session);
*/

EmployeeVo employee = (EmployeeVo) session.getAttribute("employee");
List<MenuItem> items = new ArrayList<MenuItem>();

/*
if (employee != null) {
	items = getMenuItems(employee.getEmpId(), session);
}
*/

if (employee == null) {
	response.sendRedirect(response.encodeRedirectURL("/dms/login"));
} else {
	items = getMenuItems(employee.getEmpId(), session);
}

setParam("menuItems", items, session);

MenuService menuService = MenuServiceImpl.getInstance();
MenuItem currentMenuItem = menuService.getBestMatchMenuItem(items, uriWithQuery);
String title = (currentMenuItem == null? "": currentMenuItem.getName());

%>

<html>
<head>

	<jsp:include page="/frame/header/get.jsp">
		<jsp:param value="<%= title %>" name="title"/>
	</jsp:include>

</head>

<body>

    <div id="wrapper">
    
    	<jsp:include page="/frame/copyright/get.jsp"></jsp:include>
		
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			
			<jsp:include page="/frame/nav/get.jsp"></jsp:include>
			
			<jsp:include page="/frame/menu/get.jsp"></jsp:include>
			
		</nav>
		
        <div id="page-wrapper">
        
			<jsp:include page="/frame/breadcrumb/get.jsp"></jsp:include>