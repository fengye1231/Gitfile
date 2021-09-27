package com.project.web.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.project.web.bean.ExpertBean;
import com.project.web.service.IExpertService;
import com.project.web.service.impl.ExpertServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class ExpertAddServlet
 */
@WebServlet("/addExpert")
@MultipartConfig
public class ExpertAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IExpertService ies = new ExpertServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpertAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = StringCheck.stringCheck(request.getParameter("name"));
		String gender = StringCheck.stringCheck(request.getParameter("gender"));
		Date birthday = Date.valueOf(request.getParameter("date"));
		String special = StringCheck.stringCheck(request.getParameter("special"));
		String position = StringCheck.stringCheck(request.getParameter("position"));
		String phoneNum = StringCheck.stringCheck(request.getParameter("phoneNum"));
		String workPlace = StringCheck.stringCheck(request.getParameter("workPlace"));
		String address = StringCheck.stringCheck(request.getParameter("address"));
		String email = StringCheck.stringCheck(request.getParameter("email"));
		
		Part p = request.getPart("fileImg");
		
		System.out.println("获得的值：" + name + gender + birthday + special + position + phoneNum + workPlace + address + email);
		
		String str = p.getHeader("content-disposition");
		String[] strArr = str.split("[.]");
		String path = strArr[strArr.length-1].replaceAll("\"", "");
		path = System.currentTimeMillis()+"."+path;
		String finalPath = this.getServletContext().getRealPath("/img/"+path);
		p.write(finalPath);
	
		
		ies.addExpert(new ExpertBean(name, gender, birthday, path, workPlace, phoneNum, special, position, address, email));
		response.sendRedirect("/project2/jsp/expertConsultation/expert/main.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
