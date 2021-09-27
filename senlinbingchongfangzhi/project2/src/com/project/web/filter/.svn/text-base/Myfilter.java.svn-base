//package com.project.web.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.project.web.bean.UserBean;
//
//
///**
// * Servlet Filter implementation class Myfilter
// */
//@WebFilter(
//		urlPatterns= {"/*"},
//		initParams = {@WebInitParam(name="charset",value="utf-8"),
//				@WebInitParam(name="contentType",value="text/html;charset=utf-8")}
//		
//		)
//public class Myfilter implements Filter {
//
//    /**
//     * Default constructor. 
//     */
//    public Myfilter() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req=(HttpServletRequest)request;
//		HttpServletResponse resp=(HttpServletResponse)response;
//		
//		HttpSession session=req.getSession();
//		UserBean user=(UserBean)session.getAttribute("user");
//		String[] strArr=new String[] {"fail.jsp","login.jsp","login","code","shop.jsp","findOne.jsp",
//				"enrol.jsp","viewCommodity.jsp"};
//		boolean flag=false;
//		for (String string : strArr) {
//			if((req.getRequestURL()+"").contains(string)) {
//				flag=true;
//				chain.doFilter(request, response);
//				return;
//			}
//		}
//		if(!flag) {
//			if(user!=null) {
//				chain.doFilter(request, response);
//			}else {
//				resp.sendRedirect("/project2/jsp/fail.jsp");
//			}
//		}
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
