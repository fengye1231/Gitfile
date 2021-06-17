package com.neusoft.dms.dept.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.dms.dept.dao.Nodes;
import com.neusoft.dms.prj.domain.PrjDept;
import com.neusoft.dms.prj.domain.PrjDeptVo;
import com.neusoft.dms.prj.service.PrjService;
import com.neusoft.dms.prj.service.PrjServiceImpl;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;

public class getSelectedTree {
	
	public void jffj(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		List<Integer> deptID = (List<Integer>) session.getAttribute("deptID");
		
		DeptService deptService = new DeptServiceImpl();
		ArrayList<Nodes> nodeList = deptService.getAllDept();
		
		Nodes node = nodeList.get(0);
		node = deptService.setCheckedNodes(deptID,node);
		nodeList.set(0, node);
		
		String jsonString = deptService.objectToJson(nodeList);
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.flush();
		out.close();
	}
	
	public void setDept(HttpServletRequest request,
			HttpServletResponse response) {
		int prjID = 0;
		List<PrjDeptVo> pDepts = null;
		List<Integer> deptID = null;
		PrjService pService = PrjServiceImpl.getInstance();
		try {
			prjID = ParameterUtil.getInt(request, "prjID");
			pDepts = pService.getPrjDeptBypID(prjID);
			if(pDepts!=null){
				deptID = new ArrayList<Integer>();
				for(PrjDept pd:pDepts){
					deptID.add(pd.getDeptID());
				}
				HttpSession session = request.getSession();
				
				session.setAttribute("deptID", deptID);
				request.setAttribute("prjID", prjID);
				request.getRequestDispatcher("/daily/setting/proj/setDeptartment/index.jsp").forward(request, response);
			}
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
