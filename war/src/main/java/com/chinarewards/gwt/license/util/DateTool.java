package com.chinarewards.gwt.license.util;

import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;

public abstract class DateTool {

	/**
	 * 相隔多少天
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 加上N天后是哪一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addSomeDay(Date date, long day) {
		long s1 = date.getTime();
		long s = day * (1000 * 60 * 60 * 24);
		long s2 = s + s1;
		return new Date(s2);
	}

	/**
	 * 加上N月后是哪一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addSomeMonth(Date date, int intervalMonth) {
		// FIXME harry implement it
		DateTimeFormat formatYMD = DateTimeFormat.getFormat("yyyy-MM-dd");
		DateTimeFormat formatM = DateTimeFormat.getFormat("MM");
		DateTimeFormat formatY = DateTimeFormat.getFormat("yyyy");
		DateTimeFormat formatD = DateTimeFormat.getFormat("dd");
		String month = formatM.format(date);
		String year = formatY.format(date);
		String day = formatD.format(date);
		if (intervalMonth > 12) {
			int y = intervalMonth / 12;
			intervalMonth = intervalMonth % 12;
			year = (Integer.parseInt(year) + y) + "";
		}
		int mon = Integer.parseInt(month) + intervalMonth;
		if (mon > 12) {
			mon = mon - 12;
			year = (Integer.parseInt(year) + 1) + "";
		}
		int threeMontyAfterDay = getDaysOfMonth(Integer.parseInt(year), mon);
		if (Integer.parseInt(day) > threeMontyAfterDay) {
			day = threeMontyAfterDay + "";
		}
		month = (mon < 10) ? "0" + mon : mon + "";
		return formatYMD.parse(year + "-" + month + "-" + day);
	}

	/**
	 * 去掉时间,留下年月日
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date stripTimeComponents(Date d) {
		if (d == null) {
			return null;
		}
		return new Date(d.getYear(), d.getMonth(), d.getDate());
	}

	/**
	 * 去掉时间,留下年月日
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dateToString(Date d) {
		if (d == null) {
			return null;
		}
		return (1900 + d.getYear()) + "-" + (d.getMonth() + 1) + "-"
				+ d.getDate();
	}

	/**
	 * 去掉时间,留下年月(中文)
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dateToStringChina(Date d) {
		if (d == null) {
			return null;
		}
		return (1900 + d.getYear()) + "年" + (d.getMonth() + 1) + "月份";
	}
	/**
	 * 去掉时间,留下月(中文)时间
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dateToStringChina2(Date d) {
		if (d == null) {
			return null;
		}
		return  (d.getMonth() + 1) + "月"+d.getDate()+"日 "+d.getHours()+":"+d.getMinutes();
	}
	/**
	 * 精确到半年
	 * 
	 * @return
	 */
	public static Double getIntervalYears(Date startday, Date endday) {
		int intervalDays = getIntervalDays(startday, endday);
		int halfYears = intervalDays * 2 / 365;
		return (double) halfYears / 2;
	}

	/**
	 * 1-12 代表月份，返回当月的总天数
	 * 
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		int day = 31;
		switch (month) {
		// 大月
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		// 小月
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		// 2月
		case 2:
			if ((0 == year % 400) || (0 == year % 4 && 0 != year % 100)) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		}

		return day;
	}

	/**
	 * oh,,, Date.getDay()....就是干这事的。。。<br/>
	 * 算出给定的日子是星期几(1-7),只能计算1900年之后的。
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getWeekDayOfDate(Date date) {
		// Date flagDate = new Date(0, 0, 1); // ---1900-1-1 星期一
		// int intervalDays = DateTool.getIntervalDays(flagDate, date);
		// // Window.alert("gg:" + (intervalDays % 7 + 1));
		// return intervalDays % 7 + 1;
		int day = date.getDay();
		if (day == 0) {
			day = 7;
		}
		return day;
	}

	@SuppressWarnings("deprecation")
	public static int getYearOfDate(Date date) {
		int yaar = date.getYear();
		return yaar;
	}

	@SuppressWarnings("deprecation")
	public static int getMonthOfDate(Date date) {
		int month = date.getMonth();
		return month;
	}

	@SuppressWarnings("deprecation")
	public static int getDayOfDate(Date date) {
		int month = date.getDate();
		return month;
	}

	/**
	 * 得到最小的数
	 * 
	 * @param weekDays
	 * @return
	 */
	public static int getMinNumber(List<Integer> weekDays) {
		int min = weekDays.get(0);
		for (int i : weekDays) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

	/**
	 * 得到最大的数
	 * 
	 * @param weekDays
	 * @return
	 */
	public static int getMaxNumber(List<Integer> weekDays) {
		int max = weekDays.get(0);
		for (int i : weekDays) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public static Date getEndDayByPeriod(Date beginDate, double period) {
		int months = 0;
		if (period == 0.5) {
			months = 6;
		}
		if (period == 1) {
			months = 12;
		}
		if (period == 2) {
			months = 24;
		}
		if (period == 3) {
			months = 36;
		}
		Date tempEndDate = DateTool.addSomeMonth(beginDate, months);
		return tempEndDate;
	}

}
