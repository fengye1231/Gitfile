package com.neusoft.dms.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.Table;

public class ServletUtil {

	/**
	 * 以Json格式输出状态信息
	 * @param response
	 * @param stateCode
	 * @param msg
	 * @throws IOException
	 */
	public static void stateJson(HttpServletResponse response, int stateCode, String msg) throws IOException {
		outputJson(response, null, stateCode, msg);
	}

	public static void stateJson(HttpServletResponse response, int stateCode) throws IOException {
		stateJson(response, stateCode, null);
	}
	
	/**
	 * 输出Json
	 * @param response
	 * @param result
	 * @param stateCode
	 * @param msg
	 * @throws IOException
	 */
	public static void outputJson(HttpServletResponse response, JSONObject result,  int stateCode, String msg) throws IOException {
		JSONObject json = new JSONObject();
		
		json.put("sc", stateCode);
		if (msg != null) {
			result.put("msg", msg);
		}
		if (result != null) {
			json.put("result", result);
		}

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	
	/**
	 * 以json格式输出Page对象
	 * @param page
	 * @param response
	 * @throws IOException
	 */
	public static void output(HttpServletResponse response, Page page) throws IOException {
		outputJson(response, JSONObject.fromObject(page), State.OK, null);
	}
	
	/**
	 * 以json格式输出Table对象
	 * @param response
	 * @param table
	 * @throws IOException
	 */
	public static void output(HttpServletResponse response, Table table) throws IOException {
		table = (table == null? new Table(new Page()): table);
		Page page = (table.getPage() == null? new Page(): table.getPage());
		JSONObject jsonResult = JSONObject.fromObject(page);

		jsonResult.put("columns", table.getHeader());
		jsonResult.put("footer", table.getFooter());
		
		outputJson(response, jsonResult, State.OK, null);
	}
	
	/**
	 * 转到错误页面
	 * @param request
	 * @param response
	 * @param url
	 * @param msg
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void errorPage(HttpServletRequest request, HttpServletResponse response, 
			String url, String msg) throws IOException, ServletException {
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.getRequestDispatcher("/error/index.jsp").forward(request, response);
	}
	
}
