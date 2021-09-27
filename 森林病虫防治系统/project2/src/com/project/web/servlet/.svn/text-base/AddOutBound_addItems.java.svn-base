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

import com.project.web.bean.MachineBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.service.IMachineService;
import com.project.web.service.impl.MachineServiceImpl;

/**
 * Servlet implementation class AddOutBound_addItems
 */
@WebServlet("/addOutBound_addItems")
public class AddOutBound_addItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMachineService ms = new MachineServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOutBound_addItems() {
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
		HttpSession s = request.getSession();
		List<OutMachineBean>list = (List<OutMachineBean>) s.getAttribute("machineList");
		IMachineService ms = new MachineServiceImpl();
		
		
		
		String id =  request.getParameter("id");
		String[] arrId = id.split("j");
		
//		System.out.println(list);
		
		if(list.size()==0) {
			for(int i=0;i<arrId.length;i++) {
				int machineId = Integer.parseInt(arrId[i]);
				MachineBean mBean = ms.findById(machineId);
				OutMachineBean bean = new OutMachineBean();
//				System.out.println("我进来了list没有的判断");
				bean.setMachineId(machineId);
				bean.setName(mBean.getName());
				bean.setType(mBean.getKind());
				bean.setPrevent(mBean.getDefeat());
				bean.setNum(1);
				
				list.add(bean);
			}
		}else {
//			System.out.println("我进来了有list判断");
			for(int i=0;i<arrId.length;i++) {
				for(int ii=0;ii<list.size();ii++) {
					int machineId = Integer.parseInt(arrId[i]);
					int machineIds = list.get(ii).getMachineId();
//					System.out.println("新加的id="+machineId+"页面已存在的id="+machineIds);
					if(machineIds==machineId) {
//						System.out.println("我进来了数量加一的判断");
						 int num = list.get(ii).getNum() +1;
						 list.get(ii).setNum(num);
						 break;
					}
//					System.out.println("ii="+ii);
//					System.out.println("list="+list.size());
					if(ii>=(list.size()-1)){
//						System.out.println("我进来了新加物品的判断的判断");
						MachineBean mBean = ms.findById(machineId);
						OutMachineBean bean = new OutMachineBean();
						bean.setMachineId(machineId);
						bean.setName(mBean.getName());
						bean.setType(mBean.getKind());
						bean.setPrevent(mBean.getDefeat());
						bean.setNum(1);
				
						list.add(bean);
						break;
					}
					
					
				}
		}
		}
		
		

		s.setAttribute("machineList", list);
		
		response.sendRedirect("/project2/jsp/drugDeliveryManagement/outBound/addOutBound.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
