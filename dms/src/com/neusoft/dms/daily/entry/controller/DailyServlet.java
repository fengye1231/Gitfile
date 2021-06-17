package com.neusoft.dms.daily.entry.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.daily.entry.domain.DailyCon;
import com.neusoft.dms.daily.entry.domain.DailyVo;
import com.neusoft.dms.daily.entry.service.DailyService;
import com.neusoft.dms.daily.entry.service.DailyServiceImpl;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class DailyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户请求的服务
		String service = request.getParameter("service");
		String s = request.getParameter("s");
		// 添加日报信息
		if ("addDaily".equals(service)) {
			addDaily(request, response);
		}
		if ("changeDaily".equals(service)) {// 修改日报
			changeDaily(request, response);
		}
		if ("reasonDaily".equals(service)) {
			Daily daily = new Daily();
			try {
				// 获取请求报文中的参数信息
				HttpSession session = request.getSession();
				EmployeeVo emp = (EmployeeVo) session.getAttribute("employee");
				int DailyID = Integer.valueOf(request.getParameter("DailyID"));
				String status = request.getParameter("status");
				String reason = request.getParameter("reason");
				// 将信息封装到Daily
				daily.setDailyId(DailyID);
				daily.setReviewEmpId(emp.getEmpId());
				daily.setStatus(status);
				daily.setReason(reason);
				// 调用业务层方法处理请求
				DailyService ds = DailyServiceImpl.getInstance();
				boolean isSuccess = ds.checkDaily(daily);
				if (isSuccess) {
					request.setAttribute("msg", "审核成功");
					request.setAttribute("daily", daily);
					request.getRequestDispatcher("/daily/entry/check/")
							.forward(request, response);
				} else {
					request.setAttribute("msg", "审核失败 ");
					request.getRequestDispatcher("/daily/check/reason.jsp")
							.forward(request, response);
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		if ("Check".equals(service)) {
			if ("reason".equals(s)) {// 审核日报
				reasonDaily(request, response);
			}
			if ("listCheckDaily".equals(s)) {// submit列出日报列表
				listCheckDaily(request, response);
			}
			if ("showDaily".equals(s)) {// 查看要修改的日报信息
				showDaily(request, response);
			}
			if ("detailCheckDaily".equals(s)) {// 查看要修改的日报信息
				detailCheckDaily(request, response);
			}
			if ("passDaily".equals(s)) {// 批量通过日报
				passDaily(request, response);
			}
			if ("failDaily".equals(s)) {// 批量不通过日报
				failDaily(request, response);
			}
		}
		if ("Daily".equals(service)) {
			if ("getDaily".equals(s)) {// 按id获取单个日报信息
				getDaily(request, response);
			}
			if ("detailDaily".equals(s)) {// 按id获取单个日报的详细信息
				detailDaily(request, response);
			}
			if ("listDaily".equals(s)) {// 日报列表
				listDaily(request, response);
			}
			if ("delDaily".equals(s)) {// 删除日报
				delDaily(request, response);
			}
		}
		// 按id获取单个日报信息
		if ("getDaily".equals(service)) {
			// 获取请求报文中的参数信息
			int DailyId = 0;
			if (request.getParameter("DailyId") != null) {
				DailyId = Integer.valueOf(request.getParameter("DailyId"));
			} else {
				request.setAttribute("msg", "查看失败 ");
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
				return;
			}
			// 调用业务层方法处理请求
			DailyService ds = DailyServiceImpl.getInstance();
			try {
				DailyVo dailyVo = ds.getDailyById(DailyId);
				// DailyVo.changeIdToName();
				request.setAttribute("daily", dailyVo);
				// TODO System.out.print(dailyVo.getDailyId());
				request.getRequestDispatcher("/daily/entry/manage/change.jsp")
						.forward(request, response);
			} catch (ServiceException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.setAttribute("url", "/daily/entry/manage/change.jsp");
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
			}
		}
		if ("showDaily".equals(service)) {
			showDaily(request, response);
		}
	}

	private void addDaily(HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 获取请求报文中的参数信息
			Date date = df.parse(request.getParameter("date"));
			int empId = Integer.valueOf(request.getParameter("writor"));
			String test = request.getParameter("test");
			float workload = Float.valueOf(request.getParameter("workload"));
			float overtime = Float.valueOf(request.getParameter("overtime"));
			int prjId = Integer.valueOf(request.getParameter("item"));
			int prpId = Integer.valueOf(request.getParameter("model"));
			String tomorrow = request.getParameter("tomorrow");

			// 将信息封装到Daily
			Daily daily = new Daily();
			daily.setEmpId(empId);
			daily.setTotalWorkload(workload);
			daily.setOverTimeLoad(overtime);
			daily.setPrjId(prjId);
			daily.setPrpId(prpId);
			daily.setSubmitDate(date);
			daily.setDesc(test);
			daily.setTomorrowPlan(tomorrow);
			// 调用业务层方法处理请求
			DailyService os = DailyServiceImpl.getInstance();
			boolean isSuccess = os.addDaily(daily);
			if (isSuccess) {
				request.setAttribute("msg", "日报添加成功");
				request.setAttribute("daily", daily);
			} else {
				request.setAttribute("msg", "日报重复，日报添加失败");
			}
			request.getRequestDispatcher("/daily/entry/manage/add.jsp")
					.forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "cadd.jsp");
			try {
				request.getRequestDispatcher("/dms/error.jsp").forward(request,
						response);
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

	private void changeDaily(HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		Daily daily = new Daily();
		try {
			// 获取请求报文中的参数信息
			int DailyID = Integer.valueOf(request.getParameter("DailyID"));
			date = df.parse(request.getParameter("date"));
			// int empId = int.valueOf(request.getParameter("writor"));
			String test = request.getParameter("test");
			float workload = Float.valueOf(request.getParameter("workload"));
			float overtime = Float.valueOf(request.getParameter("overtime"));
			int prjId = Integer.valueOf(request.getParameter("item"));
			int prpId = Integer.valueOf(request.getParameter("model"));
			// String tomorrow = request.getParameter("tomorrow");
			// 将信息封装到Daily
			daily.setDailyId(DailyID);
			// daily.setEmpId(empId);
			daily.setTotalWorkload(workload);
			daily.setOverTimeLoad(overtime);
			daily.setPrjId(prjId);
			daily.setPrpId(prpId);
			daily.setSubmitDate(date);
			daily.setDesc(test);
			// daily.setTomorrowPlan(tomorrow);
			// 调用业务层方法处理请求
			DailyService ds = DailyServiceImpl.getInstance();
			boolean isSuccess = ds.updateDaily(daily);
			if (isSuccess) {
				request.setAttribute("msg", "修改成功");
//				request.setAttribute("daily", daily);
				// request.getRequestDispatcher(
				// "/daily/entry/servlet.DailyServlet?service=getDaily&DailyId="
				// + daily.getDailyId())
				// .forward(request, response);
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
			} else {
				request.setAttribute("msg", "修改失败 ");
				request.getRequestDispatcher("/daily/entry/manage/change.jsp")
						.forward(request, response);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 删除选中的项目
	private void delDaily(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取请求报文中的参数信息
		String ids = ParameterUtil.get(request, "ids");
		List<Integer> dIds = splitID(ids);
		boolean isSuccess = false;
		for (Integer d : dIds) {
			// 调用业务层方法处理请求
			DailyService os = DailyServiceImpl.getInstance();
			try {
				isSuccess = os.delDaily(d);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		if (isSuccess) {
			request.setAttribute("msg", "删除成功");
		} else {
			request.setAttribute("msg", "删除失败");
		}
		try {
			request.getRequestDispatcher(
					"/daily/entry/servlet.DailyServlet?service=listDaily")
					.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listCheckDaily(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 获取请求报文中的参数信息
			HttpSession session = request.getSession();
			EmployeeVo emp = (EmployeeVo) session.getAttribute("employee");
			Date startDate = ParameterUtil.getDate(request, "startDate");
			Date endDate = ParameterUtil.getDate(request, "endDate");
			int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
			int pageSize = ParameterUtil.getInt(request, "pageSize",
					Constant.PAGE_SIZE);
			int empId = ParameterUtil.getInt(request, "empId", 0);
			String status = ParameterUtil.get(request, "status", null,
					ParameterUtil.EMPTY);
			if (status != null) {
				try {
					status = URLDecoder.decode(status, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			// 将信息封装到Organization
			Daily daily = new Daily();
			daily.setEmpId(empId);
			daily.setStatus(status);
			daily.setReviewEmpId(emp.getEmpId());
			// 调用业务层方法处理请求
			Page page;
			page = DailyServiceImpl.getInstance().listCheckDaily(daily,
					startDate, endDate, pageNum, pageSize);
			ServletUtil.output(response, page);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvaliedParamException e) {
			e.printStackTrace();
		}

	}

	private void listDaily(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		EmployeeVo emp = (EmployeeVo) session.getAttribute("employee");
		DailyCon dailyCon = (DailyCon) session.getAttribute("dailyCon");
		try {
			Date startDate = ParameterUtil.getDate(request, "startDate");
			if(startDate!=null){
				Date endDate = ParameterUtil.getDate(request, "endDate");
				int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
				int pageSize = ParameterUtil.getInt(request, "pageSize",
						Constant.PAGE_SIZE);
				int projId = ParameterUtil.getInt(request, "projId", 0);
				int prpId = ParameterUtil.getInt(request, "prpId", 0);
				String status = ParameterUtil.get(request, "status", null,
						ParameterUtil.EMPTY);
				String desc = ParameterUtil.get(request, "desc");
				if (status != null) {
					try {
						status = URLDecoder.decode(status, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				if (desc != null) {
					try {
						desc = URLDecoder.decode(desc, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				dailyCon.setPrjId(projId);
				dailyCon.setPrpId(prpId);
				dailyCon.setStatus(status);
				dailyCon.setDesc(desc);
				dailyCon.setEmpId(emp.getEmpId());
				dailyCon.setEndDate(endDate);
				dailyCon.setPageNum(pageNum);
				dailyCon.setPageSize(pageSize);
				dailyCon.setStartDate(startDate);
			}
			Page page = DailyServiceImpl.getInstance().listDaily(dailyCon);

			ServletUtil.output(response, page);
		} catch (InvaliedParamException e) {
			try {
				ServletUtil.stateJson(response, State.BAD_REQUEST);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			try {
				ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 按id获取单个日报信息
	private void getDaily(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取请求报文中的参数信息
		String ids = ParameterUtil.get(request, "ids");
		List<Integer> dIds = splitID(ids);
		int DailyId = 0;
		if (dIds != null && 1 == dIds.size()) {
			DailyId = dIds.get(0);
		} else {
			request.setAttribute("msg", "查看失败 ");
			try {
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 调用业务层方法处理请求
		DailyService ds = DailyServiceImpl.getInstance();
		try {
			DailyVo dailyVo = ds.getDailyById(DailyId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			dailyVo.setSubmitD(df.format(dailyVo.getSubmitDate()));
			if (dailyVo.getReviewDate() != null) {
				dailyVo.setReviewD(df.format(dailyVo.getReviewDate()));
			}
			request.setAttribute("daily", dailyVo);
			request.getRequestDispatcher("/daily/entry/manage/change.jsp")
					.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "/daily/entry/manage/change.jsp");
			try {
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
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

	private void showDaily(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取请求报文中的参数信息
		int DailyId = 0;
		if (request.getParameter("DailyId") != null) {
			DailyId = Integer.valueOf(request.getParameter("DailyId"));
		} else {
			request.setAttribute("msg", "查看失败 ");
			try {
				request.getRequestDispatcher("/daily/entry/check/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// 调用业务层方法处理请求
		DailyService ds = DailyServiceImpl.getInstance();
		try {
			DailyVo dailyVo = ds.getDailyById(DailyId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			dailyVo.setSubmitD(df.format(dailyVo.getSubmitDate()));
			if (dailyVo.getReviewDate() != null) {
				dailyVo.setReviewD(df.format(dailyVo.getReviewDate()));
			}
			request.setAttribute("daily", dailyVo);
			// TODO System.out.print(dailyVo.getDailyId());
			request.getRequestDispatcher("/daily/entry/check/reason.jsp")
					.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "/daily/entry/check/reason.jsp");
			try {
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
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

	private void passDaily(HttpServletRequest request,
			HttpServletResponse response) {
		Daily daily = new Daily();
		String ids = ParameterUtil.get(request, "ids");
		List<Integer> dIds = splitID(ids);
		HttpSession session = request.getSession();
		EmployeeVo emp = (EmployeeVo) session.getAttribute("employee");
		daily.setReviewEmpId(emp.getEmpId());
		boolean isSuccess = false;
		for(Integer d : dIds){
			// 将信息封装到Daily
			daily.setDailyId(Integer.valueOf(d));
			// 调用业务层方法处理请求
			DailyService ds = DailyServiceImpl.getInstance();
			try {
				isSuccess = ds.passDaily(daily);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		if (isSuccess) {
			request.setAttribute("msg", "审核成功");
			// request.setAttribute("daily", daily);
			try {
				request.getRequestDispatcher("/daily/entry/check/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("msg", "审核失败 ");
			try {
				request.getRequestDispatcher("/daily/entry/check/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void failDaily(HttpServletRequest request,
			HttpServletResponse response) {
		Daily daily = new Daily();
		String ids = ParameterUtil.get(request, "ids");
		List<Integer> dIds = splitID(ids);
		HttpSession session = request.getSession();
		EmployeeVo emp = (EmployeeVo) session.getAttribute("employee");
		daily.setReviewEmpId(emp.getEmpId());
		boolean isSuccess = false;
		for(Integer d : dIds){
			// 将信息封装到Daily
			daily.setDailyId(Integer.valueOf(d));
			// 调用业务层方法处理请求
			DailyService ds = DailyServiceImpl.getInstance();
			try {
				isSuccess = ds.failDaily(daily);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		if (isSuccess) {
			request.setAttribute("msg", "审核成功");
			// request.setAttribute("daily", daily);
			try {
				request.getRequestDispatcher("/daily/entry/check/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("msg", "审核失败 ");
			try {
				request.getRequestDispatcher("/daily/entry/check/reason.jsp")
						.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void reasonDaily(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取请求报文中的参数信息
		int DailyId = 0;
		String[] dIds = request.getParameterValues("dailyId");
		if (dIds != null && 1 == dIds.length) {
			DailyId = Integer.valueOf(dIds[0]);
		} else {
			request.setAttribute("msg", "查看失败 ");
			try {
				request.getRequestDispatcher("/daily/check/").forward(request,
						response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// 调用业务层方法处理请求
		DailyService ds = DailyServiceImpl.getInstance();
		try {
			DailyVo dailyVo = ds.getDailyById(DailyId);
			// DailyVo.changeIdToName();
			request.setAttribute("daily", dailyVo);
			request.getRequestDispatcher("/daily/entry/check/reason.jsp")
					.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "/daily/entry/check/reason.jsp");
			try {
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
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

	private void detailDaily(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取请求报文中的参数信息
		int DailyId = 0;
		if (request.getParameter("DailyId") != null) {
			DailyId = Integer.valueOf(request.getParameter("DailyId"));
		} else {
			request.setAttribute("msg", "查看失败 ");
			try {
				request.getRequestDispatcher("/daily/entry/manage/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// 调用业务层方法处理请求
		DailyService ds = DailyServiceImpl.getInstance();
		try {
			DailyVo dailyVo = ds.getDailyById(DailyId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			dailyVo.setSubmitD(df.format(dailyVo.getSubmitDate()));
			if (dailyVo.getReviewDate() != null) {
				dailyVo.setReviewD(df.format(dailyVo.getReviewDate()));
			}
			// DailyVo.changeIdToName();
			request.setAttribute("daily", dailyVo);
			request.getRequestDispatcher("/daily/entry/manage/detail.jsp")
					.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "detail.jsp");
			try {
				request.getRequestDispatcher("/daily/manage/").forward(request,
						response);
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

	private void detailCheckDaily(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取请求报文中的参数信息
		int DailyId = 0;
		if (request.getParameter("DailyId") != null) {
			DailyId = Integer.valueOf(request.getParameter("DailyId"));
		} else {
			request.setAttribute("msg", "查看失败 ");
			try {
				request.getRequestDispatcher("/daily/entry/check/").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// 调用业务层方法处理请求
		DailyService ds = DailyServiceImpl.getInstance();
		try {
			DailyVo dailyVo = ds.getDailyById(DailyId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			dailyVo.setSubmitD(df.format(dailyVo.getSubmitDate()));
			if (dailyVo.getReviewDate() != null) {
				dailyVo.setReviewD(df.format(dailyVo.getReviewDate()));
			}
			// DailyVo.changeIdToName();
			request.setAttribute("daily", dailyVo);
			request.getRequestDispatcher("/daily/entry/check/detail.jsp")
					.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "detail.jsp");
			try {
				request.getRequestDispatcher("/daily/check/").forward(request,
						response);
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

	// 将获取的String类型的id转化成int型
	private List<Integer> splitID(String id) {
		List<Integer> idInts = new ArrayList<Integer>();
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			idInts.add(Integer.parseInt(ids[i]));
		}
		return idInts;
	}
}
