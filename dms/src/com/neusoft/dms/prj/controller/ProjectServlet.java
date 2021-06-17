package com.neusoft.dms.prj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.daily.entry.service.DailyService;
import com.neusoft.dms.daily.entry.service.DailyServiceImpl;
import com.neusoft.dms.dept.dao.Nodes;
import com.neusoft.dms.dept.service.DeptService;
import com.neusoft.dms.dept.service.DeptServiceImpl;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Prp;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.prj.domain.Prj;
import com.neusoft.dms.prj.domain.PrjDept;
import com.neusoft.dms.prj.domain.PrjDeptVo;
import com.neusoft.dms.prj.domain.PrjEmp;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.prj.domain.PrjPrp;
import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.prj.service.PrjService;
import com.neusoft.dms.prj.service.PrjServiceImpl;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service = request.getParameter("service");
		System.out.println("service:"+service);
		
		if("getAllPrj".equals(service)){
			getAllprj(request,response);
		}else if ("addPrj".equals(service)) {
			addProject(request, response);
		}
		
		else if("modifyProject".equals(service)){
			modifyProject(request,response);
		}
		
		else if ("updatePrj".equals(service)) {
			updateProject(request,response);
		}
		
		else if("deletePrj".equals(service)){
			deltPrj(request, response);
		}
		
		else if("searchPrj".equals(service)){
			searchProject(request,response);
		}
		
		else if("setPRP".equals(service)){
			setPrp(request,response);
		}
		else if("getAllPrp".equals(service)){
			getAllPrp(request, response);
		}
		else if("updatePRP".equals(service)){
			updatePRP(request,response);
		}
		
		else if("setDept".equals(service)){
			setDept(request,response);
			System.out.println("correct dept");
		}
		
		else if("getCheckBoxTree".equals(service)){
			getCheckBoxTree(request,response);
		}
		
		else if("updateDept".equals(service)){
			updateDept(request,response);
		}
		
		else if("setStaff".equals(service)){
			setStaff(request,response);
		}
		
		else if("choseStaff".equals(service)){
			try {
				choseStaff(request,response);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		else if("getStaff".equals(service)){
			try {
				getStaff(request, response);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		else if("updateStaff".equals(service)){
			updateStaff(request,response);
		}
		
		else if("getDeptID".equals(service)){
			getDeptID(request,response);
		}
	
	}

	//默认获得所有项目信息
	private void getAllprj(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		PageQuery query;
		try {
			query = getPrpQuery(request);
			Table table = PrjServiceImpl.getInstance().getAllPrp(query);
			ServletUtil.output(response, table);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	//给项目配置人员
	private void updateStaff(HttpServletRequest request,
			HttpServletResponse response) {
			List<Integer> empsID = new ArrayList<Integer>();
			PrjEmp pe = null;
			try {
				int prjID = ParameterUtil.getInt(request, "prjID");
				System.out.println("prjID:"+prjID);
				int prID = ParameterUtil.getInt(request, "prID");
				System.out.println("prID:"+prID);
				PrjService ps = PrjServiceImpl.getInstance();
				empsID = splitID(ParameterUtil.get(request, "ids"));
				ps.deltPrjEmp(prjID, prID);
				for(int e:empsID){
					pe = new PrjEmp();
					pe.setPrjID(prjID);
					pe.setpRID(prID);
					pe.seteID(e);
					ps.addPrjEmp(pe);
				}
			} catch (InvaliedParamException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
	}

	//将获取的String类型的id转化成int型
	private List<Integer> splitID(String id){
		System.out.println(id);
		List<Integer> idInts = new ArrayList<Integer>();
		String[] ids = id.split(",");
		for(int i=0;i<ids.length;i++){
			idInts.add(Integer.parseInt(ids[i]));
		}
		return idInts;
	}
	
	//重新设置项目下的部门
	private void updateDept(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		int prjID = 0;
		PrjService pService = PrjServiceImpl.getInstance();
		boolean deltSuccess = false;
		String jsonDeptIds = request.getParameter("0");
		System.out.println("jsonString jsonDeptIds:"+jsonDeptIds);
		HashMap<Integer, Integer> jMap = new HashMap<Integer, Integer>();
		JSONObject jsonObject = JSONObject.fromObject(jsonDeptIds);
		Map<String, Object> jsonMap = JSONObject.fromObject(jsonObject);
        for(Entry<String,Object> entry : jsonMap.entrySet()){
            jMap.put(new Integer(Integer.parseInt(entry.getKey())), (Integer)entry.getValue());
            System.out.println("KEY:"+entry.getKey()+"  -->  Value:"+entry.getValue()+"\n"); 
        }
		if(jMap != null && jMap.size() > 0){
			for(int i=0;i<jMap.size();i++){
				System.out.println(jMap.get(new Integer(i)).intValue());
			}
		}
		
		try {
			prjID = ParameterUtil.getInt(request,"prjID" );
			deltSuccess = pService.delPrjDept(prjID);
			if(deltSuccess==true){
				for (Map.Entry<Integer, Integer> dept : jMap.entrySet()) {
					pService.addDept(prjID, dept.getValue());
				}
				ServletUtil.stateJson(response, State.OK);
			}
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.FAILED);
		}
	}

	//进入部门设置界面
	private void setDept(HttpServletRequest request,
			HttpServletResponse response) {
		int prjID = 0;
		List<PrjDeptVo> pDepts = null;
		List<Integer> deptID = null;
		PrjService pService = PrjServiceImpl.getInstance();
		try {
			prjID = ParameterUtil.getInt(request, "prjID");
			System.out.println("prjID:"+prjID);
			pDepts = pService.getPrjDeptBypID(prjID);
			if(pDepts!=null){
				deptID = new ArrayList<Integer>();
				for(PrjDept pd:pDepts){
					deptID.add(pd.getDeptID());
				}
			}
			for(Integer id:deptID){
				System.out.println(id);
			}
			request.setAttribute("prjID", prjID);
			HttpSession session = request.getSession();
			session.setAttribute("deptID", deptID);
			request.getRequestDispatcher("/daily/setting/proj/setDepartment/index.jsp").forward(request, response);
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
	
	//获得选择好的部门树
	private void getCheckBoxTree(HttpServletRequest request,
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

	//重新设置项目下的prp阶段
	private void updatePRP(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		boolean deltSuccess = false;
		boolean addSuccess = false;
		PrjPrp pp = null;
		int prjID;
		try {
			prjID = ParameterUtil.getInt(request, "prjID");
			String prpIdString = ParameterUtil.get(request, "ids");
			List<Integer> prpIDs = splitID(prpIdString);
			PrjService ps = PrjServiceImpl.getInstance();
			deltSuccess = ps.deltAllPrjPrp(prjID);
			for(int prp:prpIDs){
				pp = new PrjPrp();
				pp.setPrjID(prjID);
				pp.setPrpID(prp);
				addSuccess = ps.addPRP(pp);
			}
			ServletUtil.stateJson(response, State.OK);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.FAILED);
		};
	}

	//修改已有项目信息
	private void updateProject(HttpServletRequest request,
			HttpServletResponse response) {
		int prjID=0;
		String prjCode = null;
		String prjName = null;
		Date sDate = null;
		Date eDate = null;
		String remark = null;
		String status = null;
		try {
			//获取请求报文中信息
			prjID = ParameterUtil.getInt(request, "prjID");
			prjCode = ParameterUtil.get(request,"projectCode");
			prjName = ParameterUtil.get(request, "projectName");
			sDate = ParameterUtil.getDateFloor(request, "start-Date");
			eDate = ParameterUtil.getDateFloor(request, "end-Date");
			remark = ParameterUtil.get(request, "remark");
			status = ParameterUtil.get(request, "projectStatus");
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		}
		//将信息封装到project中
		Prj prj = new Prj(prjID,prjCode,prjName,sDate,eDate,remark,status);
		//调用业务层处理请求
		try{
			PrjService ps = PrjServiceImpl.getInstance();
			boolean isSuccess = ps.updatePrj(prj);
			if(isSuccess){
				 request.setAttribute("tip", "修改项目成功");
			}else{
				request.setAttribute("tip", "项目编码或名称已存在，请重置");
			}
			request.setAttribute("prj", prj);
			request.setAttribute("prjStatus", prj.getStatus());
			request.getRequestDispatcher("/daily/setting/proj/modifyProject/index.jsp").forward(request, response);
		}catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "dms/daily/setting/proj/index.jsp");
			try {
				request.getRequestDispatcher("/mms/error.jsp").forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//删除选中的项目
	private void deltPrj(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String ids = ParameterUtil.get(request, "ids");
		List<Integer> prjIDs = splitID(ids);
		PrjService ps = PrjServiceImpl.getInstance();
		try {
			for(Integer p:prjIDs){
				ps.delPrj(p);
			}
			ServletUtil.stateJson(response, State.OK);
	    } catch (ServiceException e) {
				e.printStackTrace();
				ServletUtil.stateJson(response, State.FAILED);
		}
	}
	
	//进入人员选择界面
	private void choseStaff(HttpServletRequest request,HttpServletResponse response) throws ServiceException{
		try {
			int prjID = ParameterUtil.getInt(request, "prjID");
			int prID = ParameterUtil.getInt(request, "prID");
			System.out.println("c prID:"+prID);
			request.setAttribute("prjID",prjID);
			List<PrjDeptVo> pds = new ArrayList<PrjDeptVo>();
			PrjService ps = PrjServiceImpl.getInstance();
			pds = ps.getPrjDeptBypID(prjID);
			request.setAttribute("pds", pds);
			request.setAttribute("prID", ParameterUtil.getInt(request, "prID"));
			request.getRequestDispatcher("/daily/setting/proj/chooseStaff/index.jsp").forward(request, response);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//获得项目中部门下人员
	private void getStaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ServiceException{
		PageQuery query;
		try {
			query = getStaffQuery(request);
			System.out.println("get staff");
			Table table = PrjServiceImpl.getInstance().getNullEmpByDept(query);
			ServletUtil.output(response, table);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		}
	
	}
	
	
	//进入人员配置界面
		private void setStaff(HttpServletRequest request,
				HttpServletResponse response) {
			int prjID;
			int prID;
			List<PrjEmpVo> pes = new ArrayList<PrjEmpVo>();
			try {
				prjID = ParameterUtil.getInt(request, "prjID");
				PrjService ps = PrjServiceImpl.getInstance();
				pes = ps.getPrjEmp(prjID);
				for(PrjEmpVo p:pes){
					System.out.println("prID:"+p.getprID());
				}
				request.setAttribute("pes",pes);
				request.setAttribute("prjID", prjID);
				request.getRequestDispatcher("/daily/setting/proj/setStaff/index.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvaliedParamException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
	//进入prp配置界面
	public void setPrp(HttpServletRequest request, HttpServletResponse response){
		try {
			int prjID = ParameterUtil.getInt(request, "prjID");
			request.setAttribute("prjID", prjID);
			PrjService ps = PrjServiceImpl.getInstance();
			List<Integer> prps = ps.getPrpByPrjID(prjID);
			request.setAttribute("prps", prps);
			request.getRequestDispatcher("/daily/setting/proj/setPRP/index.jsp").forward(request, response);
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
		
	//获得所有prp
	private void getAllPrp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PageQuery query;
		try {
			query = getPrpQuery(request);
			Table table = PrjServiceImpl.getInstance().getAllPrp(query);
			ServletUtil.output(response, table);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
	//根据条件查询页面信息
	private void searchProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PageQuery query;
		try {
			query = getPrjQuery(request);
			Table table = PrjServiceImpl.getInstance().searchProj(query);
			ServletUtil.output(response, table);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
	}

	//进如项目修改界面
	private void modifyProject(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException {
		PrjVo pVo= null;
		int prjID;
		try {
			prjID = ParameterUtil.getInt(request, "prjID");
			//调用业务层处理请求
			PrjService ps = PrjServiceImpl.getInstance();
			pVo = ps.getPrjByID(prjID);
			request.setAttribute("prj", pVo);
			request.setAttribute("prjStatus", pVo.getStatus());
			request.getRequestDispatcher("/daily/setting/proj/modifyProject/index.jsp").forward(request, response);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	//添加新的项目
	private void addProject(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String prjCode = null;
		String prjName = null;
		Date sDate = null;
		Date eDate = null;
		String remark = null;
		try{
			//获取请求报文中信息
			prjCode = ParameterUtil.get(request,"projectCode");
			prjName = ParameterUtil.get(request, "projectName");
			sDate = ParameterUtil.getDateFloor(request, "start-Date");
			eDate = ParameterUtil.getDateFloor(request, "end-Date");
			remark = ParameterUtil.get(request, "remark");
		} catch (InvaliedParamException e) {
			ServletUtil.stateJson(response, State.BAD_REQUEST);
			return;
		}

			//将信息封装到project中
			Prj prj = new Prj(prjCode,prjName,sDate,eDate,remark);
			//调用业务层处理请求
			try{
				PrjService ps = PrjServiceImpl.getInstance();
				boolean isSuccess = ps.addPrj(prj);
				if(isSuccess){
					 request.setAttribute("tip", "添加项目成功");
				}else{
					request.setAttribute("tip", "项目编码或名称已存在，请重置");
				}
				request.getRequestDispatcher("/daily/setting/proj/addProject/index.jsp").forward(request, response);
			}catch (ServiceException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.setAttribute("url", "dms/daily/setting/proj/index.jsp");
				request.getRequestDispatcher("/mms/error.jsp").forward(request, response);
			}
	}
	
	//封装查询项目请求参数
	private PageQuery getPrjQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {
		
		String projCode = ParameterUtil.get(request, "projectCode", null, ParameterUtil.EMPTY);
		String projName = ParameterUtil.get(request, "projectName", null, ParameterUtil.EMPTY);
		Date sDate = ParameterUtil.getDateFloor(request, "start-date", null);
		Date eDate = (Date)ParameterUtil.getDateFloor(request, "end-date", null);
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
	    if(eDate != null && sDate != null){
	    	if (eDate.before(sDate)) {
	    		throw new InvaliedParamException("开始日期不能晚于结束日期");
	    	}
	    }
	    
	    if (projCode != null) projCode = URLDecoder.decode(projCode, "UTF-8");
	    if (projName != null) projName = URLDecoder.decode(projName, "UTF-8");
	    
		PageQuery query = new PageQuery(pageNum, pageSize)
			.setParam("projCode", projCode)
			.setParam("projName", projName)
			.setParam("sDate", sDate)
			.setParam("eDate", eDate);
		return query;
	}
	//封装prp请求参数
	private PageQuery getPrpQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		PageQuery query = new PageQuery(pageNum, pageSize);
		return query;
	}
	//封装人员请求参数
	private PageQuery getStaffQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {
		int deptID = ParameterUtil.getInt(request, "seltdept");
		System.out.println("seltdept:"+deptID);
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		    
		PageQuery query = new PageQuery(pageNum, pageSize)
				.setParam("deptID", deptID);
			return query;
		}
	
	private void getDeptID(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		List<Integer> deptID = (List<Integer>) session.getAttribute("deptID");
		
		System.out.println("deptID.size():"+deptID.size());
		
		DeptService deptService = new DeptServiceImpl();
		String jsonString = deptService.objectToJson(deptID);
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.flush();
		out.close();
	}
}
