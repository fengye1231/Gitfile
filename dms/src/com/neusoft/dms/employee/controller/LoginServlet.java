package com.neusoft.dms.employee.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.employee.service.EmployeeService;
import com.neusoft.dms.employee.service.EmployeeServiceImpl;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.employee.controller.Check;;


public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service = request.getParameter("service");
		if("login".equals(service)){
			login(request,response);
		}
		else if("forgetPassword".equals(service)){
			forgetPassword(request, response);
		}
		else if("resetPassword".equals(service)){
			resetPassword(request, response);
		}
		else if("logout".equals(service)){
			logout(request, response);
		}
	}
	
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{

		try {
	    	/*获取请求报文的信息*/
	   	    String username = request.getParameter("username");
	   	    String password = request.getParameter("password");
	   	    String rand = request.getParameter("rand");
   	        // System.out.println(rand);
   	          	     	   
	   	    HttpSession session = request.getSession();
	   	    String sRand = (String) session.getAttribute("rand");
	   	    
		    //System.out.println(sRand);  	    
		    if(rand.equalsIgnoreCase(sRand)){
	   	    /*调用service层方法处理请求*/
	   	    EmployeeService emps = EmployeeServiceImpl.getInstance();       	    
	   	    boolean isSuccess = emps.login(username, password);
		    
	   	    
	   	    /*生成响应*/
	   	    if(isSuccess){
	   	    	   //request.setAttribute("username", username);	   	    	   	   	       	 
		   	       EmployeeVo emp = emps.search(username);
		   	  
		   	     
		   	       session.setAttribute("employee", emp);		        
	               response.sendRedirect(response.encodeRedirectURL("/dms/daily/entry/manage/index.jsp"));
	   	    }
	   	    else{
	   	    	session.setAttribute("error", "登录失败");
	   	    	request.getRequestDispatcher("/login/index.jsp").forward(request, response);
	   	    }
		    }
		    else{
		    	session.setAttribute("error", "验证码不正确");
		    	request.getRequestDispatcher("/login/index.jsp").forward(request, response);
		    }
		} catch (ServiceException e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/error");
			rd.forward(request, response);
		}
	}
	//登出
	public void logout(HttpServletRequest request, HttpServletResponse response)
		     throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/login/index.jsp").forward(request, response);
	}
	//忘记密码
	public void forgetPassword(HttpServletRequest request, HttpServletResponse response)
	     throws ServletException, IOException{	
		
			try {
		    	/*获取请求报文的信息*/
		   	    String username = request.getParameter("username");
		   	    String question = request.getParameter("question");
		   	    String answer = request.getParameter("answer");
		   	    
		   	    /*调用service层方法处理请求*/
		   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
		   	    boolean isSuccess = emps.forgetPassword(username, question,answer);
		   	    
		   	    HttpSession session = request.getSession();
		   	    session.setAttribute("username", username);
					    
		   	    /*生成响应*/
		   	    if(isSuccess){
		   	    	   request.setAttribute("username", username);
		               request.getRequestDispatcher("/login/password/resetpassword/index.jsp").forward(request, response);
		   	    }
		   	    else{
		   	    	request.getRequestDispatcher("/login/password/index.jsp").forward(request, response);
		   	    }
				
			} catch (ServiceException e){
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/error");
				rd.forward(request, response);
			}
	}
	
	//重置密码
	public void resetPassword(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{

			try {
				 HttpSession session = request.getSession();
			   	 String username  = (String) session.getAttribute("username");
			   	
		    	/*获取请求报文的信息*/
		   	    String password1 = request.getParameter("password1");
		   	    String password2 = request.getParameter("password2");
		   	    	   	    	   	    
		   	    /*调用service层方法处理请求*/
		   	    EmployeeService emps = EmployeeServiceImpl.getInstance();
		   	    boolean isSuccess = emps.resetPassword(username,password1,password2);
		   	    
		   	    
		   	    
		   	    /*生成响应*/
		   	    if(isSuccess){
		   	    	   request.setAttribute("username",username);
		               request.getRequestDispatcher("/login/index.jsp").forward(request, response);
		   	    }
		   	    else{
		   	    	request.getRequestDispatcher("/login/password/resetpassword/index.jsp").forward(request, response);
		   	    }
				
			} catch (ServiceException e){
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/error");
				rd.forward(request, response);
			}
	}
	
}

