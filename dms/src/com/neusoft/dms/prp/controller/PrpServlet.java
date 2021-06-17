package com.neusoft.dms.prp.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.prp.domain.Prp;
import com.neusoft.dms.prp.domain.PrpVo;
import com.neusoft.dms.prp.service.PrpService;
import com.neusoft.dms.prp.service.PrpServiceImpl;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;

public class PrpServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service=request.getParameter("service");
		
		//PRP列表
//		if("listPrp".equals(service))
//			listPrp(request,response);
//		if("prpList".equals(service))
//			prpList(request,response);
		if("queryPrp".equals(service))
			queryPrp(request,response);
	
	
	
		//添加prp
		if("add".equals(service)){
			String name=request.getParameter("prpName");
			String abbr=request.getParameter("prpAbbr");
			String remark=request.getParameter("remark");
			//信息封装
			Prp prp=new Prp();
			prp.setPrpName(name);
			prp.setPrpAbbr(abbr);
			prp.setRemark(remark);
			try {
				PrpService prpService=PrpServiceImpl.getInstance();
				boolean isSuccess=prpService.addPrp(prp);
				if(isSuccess){
					request.setAttribute("tip","prp阶段添加成功");
				}
				else{
					request.setAttribute("tip", "prp阶段重复,添加失败");
				}
				request.getRequestDispatcher("/daily/setting/prp/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
	   }
		//删除prp
		if("del".equals(service)){
			String n=request.getParameter("prpName");
			String name=new String(n.getBytes("iso-8859-1"),"utf-8");
			PrpService prpService=PrpServiceImpl.getInstance();
			try {
				boolean isSuccess=prpService.deletePrp(name);
				if(isSuccess){
					request.setAttribute("tip", "prp阶段删除成功");
				}
				else{
					request.setAttribute("tip", "prp阶段删除失败");
				}
				request.getRequestDispatcher("/daily/setting/prp/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		//查询prp
		if("search".equals(service)){
			String name=request.getParameter("prpName");
			String abbr=request.getParameter("prpAbbr");
			PrpService prpService=PrpServiceImpl.getInstance();
			PrpVo prpVo=null;
			try {
				prpVo=prpService.getPrpByName(name, abbr);
				request.setAttribute("prp", prpVo);
				request.getRequestDispatcher("/dms/daily/setting/prp/index.jsp").forward(request, response);	
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		//更新prp
		if("update".equals(service)){
			String name=request.getParameter("prpName");
			String abbr=request.getParameter("prpAbbr");
			String remark=request.getParameter("remark");
			String prpId=request.getParameter("prpId");
			Prp prp=new Prp();
			prp.setPrpName(name);
			prp.setPrpAbbr(abbr);
			prp.setRemark(remark);
			prp.setPrpId(Integer.parseInt(prpId));
			PrpService prpService=PrpServiceImpl.getInstance();
			try {
				boolean isSuccess=prpService.updatePrp(prp);
				if(isSuccess){
					request.setAttribute("tip", "prp更新成功");
				}
				else{
					request.setAttribute("tip", "prp更新失败");
				}
				request.getRequestDispatcher("/daily/setting/prp/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		//PRP列表
//		if("listPrp".equals(service)){
//			String name=request.getParameter("prpName");
//			String abbr=request.getParameter("prpAbbr");
//			String remark=request.getParameter("remark");
//			String pNum=request.getParameter("pageNum");
//			int pageNum=1;
//			if(pNum!=null){
//				pageNum=Integer.parseInt(pNum);
//			}
//			Prp prp=new Prp();
//			prp.setPrpName(name);
//			prp.setPrpAbbr(abbr);
//			prp.setRemark(remark);
//			PrpService prpService=PrpServiceImpl.getInstance();
//			try{
//				Page page=prpService.listPrp(prp, pageNum);
//				request.setAttribute("page", page);
//				request.setAttribute("condition", new String[]{prp.getPrpName(),prp.getPrpAbbr()});
//				request.getRequestDispatcher("/daily/setting/prp/prp_query.jsp").forward(request, response);
//			}catch (ServiceException e) {
//				request.setAttribute("error", e.getMessage());
//				request.getRequestDispatcher("/dms/error.jsp").forward(request, response);
//			}
//			
//		}
	}
	
//	private void listPrp(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String name=request.getParameter("prpName");
//		String abbr=request.getParameter("prpAbbr");
//		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
//		HttpSession session = request.getSession();
//		session.setAttribute("prpName", name);
//		session.setAttribute("prpAbbr", abbr);
//		session.setAttribute("pageNum", pageNum);
//		Prp prp=new Prp();
//		prp.setPrpName(name);
//		prp.setPrpAbbr(abbr);
//		PrpService prpService=PrpServiceImpl.getInstance();
//		try{
//			Page page=prpService.listPrp(prp, pageNum);
//			ServletUtil.output(response, page);
//			
//		}catch (ServiceException e) {
//			request.setAttribute("error", e.getMessage());
//			request.getRequestDispatcher("/dms/error.jsp").forward(request, response);
//		}
//		request.getRequestDispatcher("/daily/setting/prp/prp_query.jsp").forward(request, response);
//	}
	
//	private void prpList(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		
//		String name=(String) session.getAttribute("prpName");
//		String abbr=(String) session.getAttribute("prpAbbr");
//		Integer pageNum = (Integer) session.getAttribute("pageNum");
//		//System.out.println("prpName:"+name);
//		int pageNumber = pageNum.intValue();
//		
//		Prp prp=new Prp();
//		prp.setPrpName(name);
//		prp.setPrpAbbr(abbr);
//		PrpService prpService=PrpServiceImpl.getInstance();
//		try{
//			Page page=prpService.listPrp(prp, pageNumber);
//			
//			ServletUtil.output(response, page);
//		}catch (ServiceException e) {
//			request.setAttribute("error", e.getMessage());
//			request.getRequestDispatcher("/dms/error.jsp").forward(request, response);
//		}
//	}
	
	private void queryPrp(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String p = ParameterUtil.get(request, "prpName", null, ParameterUtil.EMPTY);
		String prpName=null;
		if(p!=null){
			prpName=URLDecoder.decode(p, "utf-8");
		}
		String prpAbbr = ParameterUtil.get(request, "prpAbbr", null, ParameterUtil.EMPTY);
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		PageQuery query = new PageQuery(pageNum,pageSize);
		query.setParam("prpName", prpName);
		query.setParam("prpAbbr", prpAbbr);
		
	    try {
			Table table = PrpServiceImpl.getInstance().queryPrpByName(query);
			ServletUtil.output(response, table);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	
}


