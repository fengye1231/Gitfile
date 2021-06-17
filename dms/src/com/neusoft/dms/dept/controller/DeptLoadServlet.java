package com.neusoft.dms.dept.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.neusoft.dms.dept.service.DepartmentDailyWarningServiceImpl;
import com.neusoft.dms.dept.service.DepartmentLoadServiceImpl;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class DeptLoadServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			this.doGet(request, response);
		}
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			String service = request.getParameter("service");
			
			if ("project".equals(service)) {
				
				queryByProject(request, response);
				
			} else if ("summary".equals(service)) {
				
				summary(request, response);
				
			} else if ("deptLoad".equals(service)) {
				
				queryByDepartment(request, response);
				
			} else if ("exportDeptLoad".equals(service)) {
				
				exportDepartmentLoad(request, response);
				
			} else if ("unsubmit".equals(service)) {
				
				queryUnsubmit(request, response);
				
			} else if ("uncheck".equals(service)) {
				
				queryUncheck(request, response);
				
			} else if ("warnUnsubmit".equals(service)) {
				
				warnUnsubmit(request, response);
				
			} else if ("warnUncheck".equals(service)) {
				
				warnUncheck(request, response);
				
			} else if ("clearUnsubmitWarning".equals(service)) {
				
				clearUnsubmitWarning(request, response);
				
			} else if ("clearUncheckWarning".equals(service)) {
				
				clearUncheckWarning(request, response);
				
			}
			
		}
		
		/**
		 * 按项目查询
		 * @param request
		 * @param response
		 */
		private void queryByProject(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {

			PageQuery query;
			try {
				query = getQuery(request);
				Table table = DepartmentLoadServiceImpl.getInstance().queryByProject(query);
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
				Table table = DepartmentLoadServiceImpl.getInstance().getDepartmentLoads(query);
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
				Table table = DepartmentLoadServiceImpl.getInstance().summary(query);
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
		 * 未写日报查询
		 * @param request
		 * @param response
		 */
		private void queryUnsubmit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			PageQuery query;
			try {
				query = getQuery(request);
				Table table = DepartmentDailyWarningServiceImpl.getInstance().queryUnsubmit(query);
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
		 * 未审核日报查询
		 * @param request
		 * @param response
		 */
		private void queryUncheck(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			PageQuery query;
			try {
				query = getQuery(request);
				Table table = DepartmentDailyWarningServiceImpl.getInstance().queryUncheck(query);
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
		 * 未提交日报提醒
		 * @param request
		 * @param response
		 */
		private void warnUnsubmit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			try {
				int deptId = ParameterUtil.getInt(request, "deptId");
				DepartmentDailyWarningServiceImpl.getInstance().warnUnsubmit(deptId);
				ServletUtil.stateJson(response, State.OK);
			} catch (InvaliedParamException e) {
				e.printStackTrace();
				ServletUtil.stateJson(response, State.BAD_REQUEST);
			} catch (ServiceException e) {
				e.printStackTrace();
				ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
			}
		}
		
		/**
		 * 未审核日报提醒
		 * @param request
		 * @param response
		 */
		private void warnUncheck(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			try {
				int deptId = ParameterUtil.getInt(request, "deptId");
				DepartmentDailyWarningServiceImpl.getInstance().warnUncheck(deptId);
				ServletUtil.stateJson(response, State.OK);
			} catch (InvaliedParamException e) {
				e.printStackTrace();
				ServletUtil.stateJson(response, State.BAD_REQUEST);
			} catch (ServiceException e) {
				e.printStackTrace();
				ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
			}
		}
		
		/**
		 * 清除当前用户的未审核日报提醒
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void clearUncheckWarning(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

			try {
				EmployeeVo employee = (EmployeeVo) request.getSession().getAttribute("employee");
				if (employee != null) {
					int empId = employee.getEmpId();
					DepartmentDailyWarningServiceImpl.getInstance().clearUncheckWarning(empId);
					response.sendRedirect(response.encodeRedirectURL("/dms/daily/entry/check"));
				} else {
					response.sendRedirect(response.encodeRedirectURL("/dms/login"));					
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 清除当前用户的未提交日报提醒
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void clearUnsubmitWarning(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

			try {
				EmployeeVo employee = (EmployeeVo) request.getSession().getAttribute("employee");
				if (employee != null) {
					int empId = employee.getEmpId();
					DepartmentDailyWarningServiceImpl.getInstance().clearUnsubmitWarning(empId);
					response.sendRedirect(response.encodeRedirectURL("/dms/daily/query/personal/unsubmit"));
				} else {
					response.sendRedirect(response.encodeRedirectURL("/dms/login"));
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 部门工作量导出
		 * @param request
		 * @param response
		 */
		private void exportDepartmentLoad(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			try {
				int deptId = ParameterUtil.getInt(request, "deptId");
				Date startDate = ParameterUtil.getDate(request, "startDate");
				Date endDate = ParameterUtil.getDate(request, "endDate");
				
				Workbook workbook = DepartmentLoadServiceImpl.getInstance()
						.getDepartmentLoadsWorkBook(deptId, startDate, endDate);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				String filename = "部门工作量[ID=" + deptId + "](" 
								+ format.format(startDate) + "至"
								+ format.format(endDate) + ").xls";
				outputWorkbook(response, filename, workbook);
			} catch (InvaliedParamException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
				ServletUtil.errorPage(request, response, null, "生成表格时出错，请重试。");
			}
		}
		
		/**
		 * 输出表格
		 * @param response
		 * @param filename
		 * @param workbook
		 * @throws ServletException
		 * @throws IOException
		 */
		private void outputWorkbook(HttpServletResponse response, String filename, Workbook workbook)
				throws ServletException, IOException {
			
			filename = new String((filename).getBytes("UTF-8"), "ISO-8859-1");
			response.setContentType("application/msexcel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
		}
		
		/**
		 * 封装请求参数
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private PageQuery getQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {
			
			int deptId = ParameterUtil.getInt(request, "deptId");
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
				.setParam("deptId", deptId);
			return query;
		}
}
