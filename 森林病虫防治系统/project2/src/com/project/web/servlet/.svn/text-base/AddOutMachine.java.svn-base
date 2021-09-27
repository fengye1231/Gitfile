package com.project.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.MachineBean;
import com.project.web.bean.OutMachineBean;

/**
 * Servlet implementation class AddOutMachine
 */
@WebServlet("/addOutMachine")
public class AddOutMachine extends HttpServlet {
	private static final long serialVersionUID = 1L;
     /**添加物品的没页显示的数量*/
	static final int EACH_PAGE_NUM = 4;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOutMachine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		List<OutMachineBean>machineList = (List<OutMachineBean>) session.getAttribute("machineList");
		List<OutMachineBean>list = new ArrayList<OutMachineBean>();
		CutPageBean<OutMachineBean> cut = new CutPageBean<OutMachineBean>();
		
		int num = Integer.parseInt(request.getParameter("num"));
		//得到分页的对象
		int index = (num-1)*EACH_PAGE_NUM;
		
//		System.out.println(num);
		
	//查出指定页数的list
		for(int i=index;i<(index+4);i++) {
//			System.out.println("i="+i+"machineList.size()="+machineList.size());
		
				if(i>=machineList.size()) {
//						System.out.println("跳出去了");
						break;
					}
		
			
			
//			System.out.println(machineList.get(i));
			OutMachineBean bean = machineList.get(i);
		
			list.add(bean);
//			System.out.println(list);
		}
		
		//得到添加物品的分页对象
		
	
		cut.setList(list);
		//得到总条数
		int totalNum = machineList.size();
		if(totalNum%EACH_PAGE_NUM==0) {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM);
		}else {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM+1);
		}
//			System.out.println("cut="+cut);
			
			ObjectMapper om = new ObjectMapper();
			om.writeValue(response.getWriter(), cut);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
