package com.mucfc;
/**  
*����  ��¼�ɹ�
*���� �ֱ��ģ�ling20081005@126.com ���ͣ�http://blog.csdn.net/evankaka��  
*ʱ�� 2015.4.24 
*/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/success")
public class success extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public success() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		//���ñ����ʽ  
        response.setContentType("text/html;charset=GBK");           
        //����htmlҳ��  
        response.getWriter().println("<html>");  
        response.getWriter().println("<head>");     
        response.getWriter().println("<title>��¼��Ϣ</title>");      
        response.getWriter().println("</head>");    
        response.getWriter().println("<body algin=center>");     
        response.getWriter().println("��ӭ��¼�ɹ�������");    
        response.getWriter().println("</body>");    
        response.getWriter().println("</html>");  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}

}