package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.project.web.bean.RatDamageBean;
import com.project.web.service.IRatDamageService;
import com.project.web.service.impl.RatDamageServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class AddRatMamageServlet
 */
@WebServlet("/AddRatMamage")
@MultipartConfig
public class AddRatMamageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IRatDamageService ird = new RatDamageServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRatMamageServlet() {
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
		
		String name = StringCheck.stringCheck(request.getParameter("ratDamageName"));
		String food = StringCheck.stringCheck(request.getParameter("food"));
		String duction = StringCheck.stringCheck(request.getParameter("duction"));
		String enemy = StringCheck.stringCheck(request.getParameter("enemy"));
		String harm = StringCheck.stringCheck(request.getParameter("harm"));
		String prevention = StringCheck.stringCheck(request.getParameter("prevention"));
		Part p = request.getPart("fileImg");
		String str =p.getHeader("content-disposition");
		String[] strArr = str.split("[.]");
		String path = strArr[strArr.length-1].replaceAll("\"", "");
		path = System.currentTimeMillis()+"."+path;
		String finalPath = this.getServletContext().getRealPath("/img/"+path);
		p.write(finalPath);
		
		RatDamageBean db = new RatDamageBean();
        db.setDuction(duction);
        db.setEnemy(enemy);
        db.setFood(food);
        db.setHarm(harm);
        db.setImgPath(path);
        db.setName(name);
        db.setPrevention(prevention);
		
		ird.addRatDamage(db);
		
		response.sendRedirect("/project2/jsp/dataManagement/ratDamage/ratDamage.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
