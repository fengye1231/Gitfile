package com.neusoft.dms.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {
	
	/**
	 * 当目标参数未设置时返回defaultValue
	 */
	public final static int UNSET = 0;
	
	/**
	 * 当目标参数的值为空或目标参数未设置时返回defaultValue
	 */
	public final static int EMPTY = 1;

	
	/**
	 * 获取请求中的参数值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String get(HttpServletRequest request, String name) {
		return get(request, name, null);
	}
	
	/**
	 * 获取请求中的参数值
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String get(HttpServletRequest request, String name, String defaultValue) {
		return get(request, name, defaultValue, UNSET);
	}
	
	/**
	 * 获取请求中的参数值
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @param mode
	 * @return
	 */
	public static String get(HttpServletRequest request, String name, String defaultValue, int mode) {
		String value = request.getParameter(name);
		if (mode == EMPTY) {
			if ("".equals(value)) {
				value = defaultValue;
			}
		}
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}

	public static int getInt(HttpServletRequest request, String name) 
			throws InvaliedParamException {
		try {
			String value = request.getParameter(name);
			return Integer.parseInt(value);
		} catch (Exception e) {
			throw new InvaliedParamException(e.getMessage(), e);
		}
	}

	public static int getInt(HttpServletRequest request, String name, int defaultValue) {
		try {
			return getInt(request, name);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long getLong(HttpServletRequest request, String name) 
			throws InvaliedParamException {
		try {
			String value = request.getParameter(name);
			return Long.parseLong(value);
		} catch (Exception e) {
			throw new InvaliedParamException(e.getMessage(), e);
		}
	}

	public static long getLong(HttpServletRequest request, String name, long defaultValue) {
		try {
			return getLong(request, name);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Date getDate(HttpServletRequest request, String name) 
			throws InvaliedParamException {
		try {
			String value = request.getParameter(name);
			return new Date(Long.parseLong(value));
		} catch (Exception e) {
			throw new InvaliedParamException(e.getMessage(), e);
		}
	}
	
	public static Date getDate(HttpServletRequest request, String name, Date defaultValue) {
		try {
			return getDate(request, name);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 获取向下取整的日期
	 * @param request
	 * @param name
	 * @return
	 * @throws InvaliedParamException
	 */
	public static Date getDateFloor(HttpServletRequest request, String name) 
			throws InvaliedParamException {
		try {
			int day = 24 * 3600 * 1000;
			String value = request.getParameter(name);
			return new Date(Long.parseLong(value) / day * day);
		} catch (Exception e) {
			throw new InvaliedParamException(e.getMessage(), e);
		}
	}
	
	/**
	 * 获取向下取整的日期
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Date getDateFloor(HttpServletRequest request, String name, Date defaultValue) {
		try {
			return getDateFloor(request, name);
		} catch (Exception e) {
			return defaultValue;
	}
}

}
