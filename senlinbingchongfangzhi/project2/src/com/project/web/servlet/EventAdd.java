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

import com.project.web.bean.EventBean;
import com.project.web.service.IEventService;
import com.project.web.service.impl.EventServiceImpl;
import com.project.web.util.DateChange;

/**
 * Servlet implementation class EventAdd
 */
@WebServlet("/EventAdd")
@MultipartConfig
public class EventAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IEventService service=new EventServiceImpl();
		  EventBean bean=new EventBean();
		  String name= request.getParameter("name");
		  String  date=request.getParameter("date");
		  String phase=request.getParameter("phase");
		  String findPath=request.getParameter("findPath");
		  String affect=request.getParameter("affectArea");
		  String area=request.getParameter("area");
		  String lose=request.getParameter("lose");
		  String describe=request.getParameter("describe");
		  String type=request.getParameter("type");
		  String plan=request.getParameter("plan");
		   Part p= request.getPart("img");
		   String str=p.getHeader("content-disposition");
		   System.out.println(str);
			String [] arr=str.split("=");
			String fileName1=arr[arr.length-1].replaceAll("\"", "");
			String path=this.getServletContext().getRealPath("/img/"+fileName1);
//使用part中的输出流来输出文件所在的位置
			p.write(path);
			
			
			
			bean.setAffectArea(affect);
			bean.setType(type);
			bean.setName(name);
			bean.setDate(date);
			bean.setFindPath(findPath);
			bean.setLose(lose);
			bean.setDescribe(describe);
			bean.setArea(area);
			bean.setImgPath(fileName1);
			bean.setPhase(phase);
			bean.setPlan(plan);
			service.addEvent(bean);
			
			System.out.println(bean);
			response.sendRedirect("/project2/jsp/disasterPrevention/event/Event.jsp");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
