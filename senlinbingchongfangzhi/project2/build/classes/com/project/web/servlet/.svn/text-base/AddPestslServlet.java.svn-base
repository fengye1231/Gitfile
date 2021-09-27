package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.project.web.bean.DiseaseBean;
import com.project.web.bean.PestslBean;
import com.project.web.service.IPestslService;
import com.project.web.service.impl.PestslServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class AddPestslServlet
 */
@WebServlet("/AddPestsl")
@MultipartConfig
public class AddPestslServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IPestslService  ips = new PestslServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPestslServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = StringCheck.stringCheck(request.getParameter("pestslName"));
		String host = StringCheck.stringCheck(request.getParameter("host"));
		String duction = StringCheck.stringCheck(request.getParameter("duction"));
		String enemy = StringCheck.stringCheck(request.getParameter("enemy"));
		String harm = StringCheck.stringCheck(request.getParameter("harm"));
		String prevention = StringCheck.stringCheck(request.getParameter("prevention"));
	
		Part p = request.getPart("fileImg");
		
		String str =p.getHeader("content-disposition");
		String[] strArr = str.split("[.]");
		String path = strArr[strArr.length-1].replaceAll("\"", "");
		path = System.currentTimeMillis()+"."+path;
		System.out.println(path);
		String finalPath = this.getServletContext().getRealPath("/img/"+path);
		p.write(finalPath);
		
		Part p1 = request.getPart("fileImg1");
		
		String str1 =p1.getHeader("content-disposition");
		String[] strArr1 = str1.split("[.]");
		String path1 = strArr1[strArr1.length-1].replaceAll("\"", "");
		path1 = System.currentTimeMillis()+"."+path1;
		System.out.println(path1);
		String finalPath1 = this.getServletContext().getRealPath("/img/"+path1);
		System.out.println(finalPath1);
		p1.write(finalPath1);
		

		PestslBean pb = new PestslBean();
        pb.setAdultImg(path1);
        pb.setDuction(duction);
        pb.setEnemy(enemy);
        pb.setHarm(harm);
        pb.setHost(host);
        pb.setLarvaImg(path);
        pb.setName(name);
        pb.setPrevention(prevention);
		
		ips.addPestsl(pb);
		
		response.sendRedirect("/project2/jsp/dataManagement/pestsl/pestsl.jsp");	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
