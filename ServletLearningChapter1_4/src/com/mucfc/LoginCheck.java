package com.mucfc;
/**  
*����  ��¼��֤
*���� �ֱ��ģ�ling20081005@126.com ���ͣ�http://blog.csdn.net/evankaka��  
*ʱ�� 2015.4.24 
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
        pw.write("include������");  
        if(username.equals("lin")&&password.equals("123")){  
        //include����  
           request.getRequestDispatcher("/success").include(request, response);  
       //�������ݵ�ת��
        	//RequestDispatcher dispatcher=request.getRequestDispatcher("/success");
        	//dispatcher.forward(request, response); 
        }else{  
            //��sendRedict��urlǰ������ϵ�ǰweb�����·����.....  
            response.sendRedirect(request.getContextPath()+"/fail");  
        }  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
	}

}
