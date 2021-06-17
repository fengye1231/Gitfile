package com.neusoft.dms.proj.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.proj.service.ProjectLoadServiceImpl;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class ProjectLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String service = request.getParameter("service");
		
		if ("prp".equals(service)) {
			
			queryByPrp(request, response);
			
		} else if ("dept".equals(service)) {
			
			queryByDepartment(request, response);
			
		} else if ("summary".equals(service)) {
			
			summary(request, response);
			
		}
	}

	/**
	 * 按里程碑查询
	 * @param request
	 * @param response
	 */
	private void queryByPrp(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		PageQuery query;
		try {
			query = getQuery(request);
			Table table = ProjectLoadServiceImpl.getInstance().queryByPrp(query);
			ServletUtil.output(response, table);			
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 按部门查询
	 * @param request
	 * @param response
	 */
	private void queryByDepartment(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		PageQuery query;
		try {
			query = getQuery(request);
			Table table = ProjectLoadServiceImpl.getInstance().queryByDepartment(query);
			ServletUtil.output(response, table);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 项目投入汇总
	 * @param request
	 * @param response
	 */
	private void summary(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		PageQuery query;
		try {
			query = getQuery(request);
			Table table = ProjectLoadServiceImpl.getInstance().summary(query);
			ServletUtil.output(response, table);			
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 封装请求参数
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private PageQuery getQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {
		
		int projId = ParameterUtil.getInt(request, "projectId");
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		Date startDate = ParameterUtil.getDateFloor(request, "startDate");
		Date endDate = ParameterUtil.getDateFloor(request, "endDate");
		
		if (endDate.before(startDate)) {
			throw new InvaliedParamException("开始日期不能晚于结束日期");
		}
		
		PageQuery query = new PageQuery(pageNum, pageSize)
			.setParam("startDate", startDate)
			.setParam("endDate", endDate)
			.setParam("projectId", projId);
		return query;
	}

}
