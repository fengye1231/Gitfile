package com.neusoft.dms.permission.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.permission.service.PermissionServiceImpl;
import com.neusoft.dms.util.ServiceException;

@SuppressWarnings("unchecked")
public class PermissionFilter implements Filter {

	private static final String SESSION_PER_PATH_PATTERN = "permissionPathPatterns";
	private static final String SESSION_LASTUSERID = "lastUserIdForPermission";
	
	private static final String ROOTPATH = "*";
	
	// 白名单
	private static final String[] WHITELIST = {"login", "frame", "error", "index.jsp", "css", "js"};


	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Integer userId = getUserId(req);
		
		try {
			if (!canAccess(req, userId)) {
				req.getRequestDispatcher("/error/permission/index.jsp").forward(req, resp);
			} else {
				chain.doFilter(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

	private boolean canAccess(HttpServletRequest request, Integer userId) throws ServiceException {
		
		String uri = request.getServletPath();
		List<Pattern> patterns = getPermissionPathPatterns(request, userId);
		
		for (Pattern pattern : patterns) {
			if (pattern.matcher(uri).matches()) {
				return true;
			}
		}
		return false;
	}

	private List<Pattern> getPermissionPathPatterns(HttpServletRequest request, Integer userId) throws ServiceException {
		HttpSession session = request.getSession();

//		if (userId != null && !isSameUser(request, userId)) {
//			session.invalidate();
//			session = request.getSession();
//		}
		
		List<Pattern> patterns = (List<Pattern>) session.getAttribute(SESSION_PER_PATH_PATTERN);
		
		if (patterns == null) {
			
			List<Permission> permissions = userId == null? new ArrayList<Permission>():
				PermissionServiceImpl.getInstance().listPermissionByEmpId(userId);
			
			Permission whitelistPermission = null;
			for (int i = 0; i < WHITELIST.length; i++) {
				whitelistPermission = new Permission();
				whitelistPermission.setPerPath(WHITELIST[i]);
				permissions.add(whitelistPermission);
			}

			patterns = new ArrayList<Pattern>();
			for (Permission permission : permissions) {
				String path = permission.getPerPath();
				if (path != null && path.length() > 0) {
					String regx = permission.getPerPath().equals(ROOTPATH)? 
							".*": ("^/?" + permission.getPerPath() + "(?:/.*)?$");
					patterns.add(Pattern.compile(regx));
				}
			}
			
			if(userId != null) {
				session.setAttribute(SESSION_LASTUSERID, userId);
				session.setAttribute(SESSION_PER_PATH_PATTERN, patterns);
			}
		}
		return patterns;
	}
	
	private Integer getUserId(HttpServletRequest request) {
		EmployeeVo employee = (EmployeeVo) request.getSession().getAttribute("employee");
		Integer userId = (employee == null? null: employee.getEmpId());
		return userId;
	}
	
	private boolean isSameUser(HttpServletRequest request, Integer userId) {
		HttpSession session = request.getSession();
		Integer lastUserId = (Integer) session.getAttribute(SESSION_LASTUSERID);
		return (lastUserId != null && userId != null && lastUserId.equals(userId));
	}

}
