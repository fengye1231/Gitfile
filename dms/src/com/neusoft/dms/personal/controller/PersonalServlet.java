package com.neusoft.dms.personal.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.personal.domain.DailyVo;
import com.neusoft.dms.personal.domain.DateLoad;
import com.neusoft.dms.personal.domain.Employee;
import com.neusoft.dms.personal.service.PersonalServiceImpl;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class PersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			this.doGet(request,response);
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String service = request.getParameter("service");

		if("submitDate".equals(service)){
			
			queryBySubmitDate(request,response);
		
		}else if("dailyReport".equals(service)){
			
			showDailyReport(request,response);
		
		}else if("prj".equals(service)){
			
			queryByProject(request,response);
		
		}else if("weekDaily".equals(service)){
			
			queryWeekDaily(request,response);
			
		}else if("exportWeekDaily".equals(service)){
			
			exportWeekDaily(request,response);
			
		}else if("unsubmit".equals(service)){
			
			unsubmit(request,response);
			
		}	
	}

		//展示日报信息
		private void showDailyReport(HttpServletRequest request,HttpServletResponse response)
				throws ServletException,IOException{
			
			try {
				int empId = ParameterUtil.getInt(request, "empId");
				Date date = ParameterUtil.getDate(request, "date");
				ArrayList<DailyVo> showDaily = (ArrayList<DailyVo>) PersonalServiceImpl.getInstance().showDailyReport(empId,date);
				request.setAttribute("EmpId", empId);
				request.setAttribute("date", date);
				request.setAttribute("dailys", showDaily);
				request.getRequestDispatcher("/daily/query/personal/date/result.jsp").forward(request, response);
				} catch (ServiceException e) {
					e.printStackTrace();
				} catch (InvaliedParamException e) {
					e.printStackTrace();
				}
			
			
		}

	//查询个人周报
	private void queryWeekDaily(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
			HttpSession session=request.getSession();
			Employee logoner = (Employee) session.getAttribute("logoner");

		try{
				int empId = ParameterUtil.getInt(request, "id");
				Date startDate = ParameterUtil.getDate(request, "startDate");
				int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
				int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
				
				PageQuery query = new PageQuery(pageNum, pageSize);
				query.setParam("submitDate", startDate);
				query.setParam("empId", empId);
				
				Table table = PersonalServiceImpl.getInstance().getWeekDaily(query);
				ServletUtil.output(response, table);
		}catch(InvaliedParamException e){
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	//导出个人周报
	private void exportWeekDaily(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		// TODO Auto-generated method stub
		try {
			int empId = ParameterUtil.getInt(request, "id");
			Date submitDate = ParameterUtil.getDate(request, "startDate");
			
			Workbook workbook = PersonalServiceImpl.getInstance()
					.getWeekDailyWorkBook(empId, submitDate);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String filename = "个人周报[ID=" + empId + "](" 
							+ format.format(submitDate) + ").xls";
			outputWorkbook(response, filename, workbook);
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.errorPage(request, response, null, "导出失败");
		}
	}
	
	
	//输出个人周报excel
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

	//按日期查询
	private void queryBySubmitDate(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
	HttpSession session=request.getSession();
	Employee logoner = (Employee) session.getAttribute("logonor");
	
	PageQuery query;
	try {
		query = getQuery(request);
		Table table = PersonalServiceImpl.getInstance().queryBySubmitDate(query);
		ServletUtil.output(response, table);			
	} catch (InvaliedParamException e) {
		e.printStackTrace();
		ServletUtil.stateJson(response, State.BAD_REQUEST);
	}
	
}
	
	//按项目查询
	private void queryByProject(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Employee logoner = (Employee) session.getAttribute("logoner");
		
		PageQuery query;
		try {
			query = getQuery(request);
			Table table = PersonalServiceImpl.getInstance().queryByProject(query);
			ServletUtil.output(response, table);			
		} catch (InvaliedParamException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.BAD_REQUEST);
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	//封装请求参数
	private PageQuery getQuery(HttpServletRequest request) throws ServletException, IOException, InvaliedParamException {
		
		int EmpId = ParameterUtil.getInt(request, "id");
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		Date startDate = ParameterUtil.getDate(request, "startDate");
		Date endDate = ParameterUtil.getDate(request, "endDate");
		
		if (endDate.before(startDate)) {
			throw new InvaliedParamException("开始日期不能晚于结束日期");
		}
		System.out.println("EmpId:"+EmpId);
		PageQuery query = new PageQuery(pageNum, pageSize)
			.setParam("startDate", startDate)
			.setParam("endDate", endDate)
			.setParam("EmpId", EmpId);
		return query;
	}
	
	//个人未写日报查询
	private void unsubmit(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		HttpSession session=request.getSession();
		EmployeeVo employeeVo = (EmployeeVo)session.getAttribute("employee");
		
		Date startDate;
		Date date = new Date();
		int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day=Integer.parseInt(new SimpleDateFormat("dd").format(date))-14;
		String df = this.lastWeek(year,month,day);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = sdf.parse(df);
			ArrayList<DailyVo> daily = PersonalServiceImpl.getInstance().queryUnsubmitDaily(startDate);
			request.setAttribute("daily", daily);
			request.setAttribute("name", employeeVo.getEmpName());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/daily/query/personal/unsubmit/result.jsp").forward(request, response);
	}
	
	//获取上一周日期
	private String lastWeek(int year,int month,int day){
		   if(day<1){
			   month-=1;
			   if(month==0){
				   year-=1;
				   month=12;
			   }
			   if(month==4||month==6||month==9||month==11){
				   day=30+day;
			   }
			   else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				   day=31+day;
			   }
			   else if(month==2){
				   if(year%400==0||(year %4==0&&year%100!=0))
					   day=29+day;
				   else
					   day=28+day;
			   }
		   }
		   String y = year+"";
		   String m ="";
		   String d ="";
		   if(month<10)
			   m = "0"+month;
		   else
			   m=month+"";
		   if(day<10)
			   d = "0"+day;
		   else
			   d = day+"";
		  
		   return y+"-"+m+"-"+d;
		}
	
}
