package com.project.web.util;

public class StringCheck {

	public static String stringCheck(String str) {
		if(str == null || str.equals("undefined") ) {
			return "";
		}
		return str;
	}
}
