package com.project.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import com.project.web.service.IExpertService;
import com.project.web.service.impl.ExpertServiceImpl;

/**
 * Servlet implementation class ExpertNameShowServlet
 */
@WebServlet("/expertNameShow")
public class ExpertNameShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IExpertService ies = new ExpertServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpertNameShowServlet() {
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
		
		List<String> nameList = ies.showAllName();
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), nameList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
