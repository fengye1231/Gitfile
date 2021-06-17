package com.mucfc;
/**  
*功能  登录失败
*作者 林炳文（ling20081005@126.com 博客：http://blog.csdn.net/evankaka）  
*时间 2015.4.24 
*/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fail")
public class fail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public fail() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式  
        response.setContentType("text/html;charset=GBK");           
        //返回html页面  
        response.getWriter().println("<html>");  
        response.getWriter().println("<head>");     
        response.getWriter().println("<title>登录信息</title>");      
        response.getWriter().println("</head>");    
        response.getWriter().println("<body algin=center>");     
        response.getWriter().println("登录失败！！！");    
        response.getWriter().println("</body>");    
        response.getWriter().println("</html>");  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
