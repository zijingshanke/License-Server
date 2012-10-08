package com.chinarewards.license.service.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Just use for test case.
 * 
 * @author yanxin
 * @since 1.0
 */
public class DateHelper {

	/**
	 * Get date by year, month and day.
	 * 
	 * @param year
	 * @param month
	 *            Notice: January -> 1 ...
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);

		return calendar.getTime();
	}
}
