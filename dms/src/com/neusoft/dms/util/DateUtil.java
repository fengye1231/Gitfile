package com.neusoft.dms.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final int[][] WORKDAYS = {
		{0, 5, 4, 3, 2, 1, 0},
		{1, 1, 5, 4, 3, 2, 1},
		{2, 2, 1, 5, 4, 3, 2},
		{3, 3, 2, 1, 5, 4, 3},
		{4, 4, 3, 2, 1, 5, 4},
		{5, 5, 4, 3, 2, 1, 5},
		{5, 5, 4, 3, 2, 1, 0}
	};
	
	
	/**
	 * 获取指定日期是周几（周日 = 1）
	 * @param date
	 * @return
	 */
	public static int dayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 向下取整（天）
	 * @param date
	 * @return
	 */
	public static Date floor(Date date) {
		long day = 24 * 3600 * 1000;
		return new Date(date.getTime() / day * day);
	}
	
	/**
	 * 是否是同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) return false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int date1Comp = calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(date2);
		int date2Comp = calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR);
		return (date1Comp == date2Comp);
	}
	
	/**
	 * 指定日期是否在一定的时期内(精确到天)
	 * @param date
	 * @param StartDate
	 * @param endDate
	 * @return boolean
	 */
	public static boolean isInThePeriod(Date date, Date startDate, Date endDate) {
		if (date == null || startDate == null || endDate == null) return false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dateComp = calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(startDate);
		int startDateComp = calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(endDate);
		int endDateComp = calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR);
		return (dateComp >= startDateComp && dateComp <= endDateComp);
	}
	
	/**
	 * 获取两个日期相差的天数（绝对值）
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDiffDays(Date date1, Date date2) {
		if (date1 == null || date2 == null) return -1;
		long day = 24 * 3600 * 1000;
		long time1 = date1.getTime() / day * day;
		long time2 = date2.getTime() / day * day;
		int diff = (int) (Math.abs(time1 - time2) / day);
		return diff;
	}
	
	/**
	 * 获取两个日期间包含的工作日数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getWorkdayCount(Date date1, Date date2) {
		if (date1 == null || date2 == null) return -1;
		int diffDays = getDiffDays(date1, date2);
		int dayOfWeek1 = dayOfWeek(date1);
		int dayOfWeek2 = dayOfWeek(date2);
		int week = diffDays / 7;
		int workday = week * 5 + WORKDAYS[dayOfWeek2 - 1][dayOfWeek1 - 1];
		return workday;
	}
}
