package com.mucfc;
/**  
*功能  登录验证
*作者 林炳文（ling20081005@126.com 博客：http://blog.csdn.net/evankaka）  
*时间 2015.4.24 
*/
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");  
        response.setCharacterEncoding("gbk");  
        response.setContentType("text/html;charset=gbk");  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        PrintWriter pw = response.getWriter();  
        pw.write("include包含。");  
        if(username.equals("lin")&&password.equals("123")){  
        //include测试  
           request.getRequestDispatcher("/success").include(request, response);  
       //进行数据的转发
        	//RequestDispatcher dispatcher=request.getRequestDispatcher("/success");
        	//dispatcher.forward(request, response); 
        }else{  
            //在sendRedict中url前必须加上当前web程序的路径名.....  
            response.sendRedirect(request.getContextPath()+"/fail");  
        }  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
	}

}
