package com.chinarewards.gwt.license.client.core.view.constant;

/**
 * This class is designed to store the configuration in the view.For example,
 * how many records in a page when using pagination. etc
 * 
 * @author yanxin
 * 
 */
public abstract class ViewConstants {

	/**
	 * The number of records in per page in pagination.
	 */
	public static final int per_page_number = 15;

	public static final int per_page_number_in_dialog = 10;
	public static final int per_page_number_in_staff = 8;
	public static final int per_page_number_in_9 = 9;
	public static final int per_page_number_in_12 = 12;

	public static final int per_page_number_in_entry = 5;
	public static final int per_page_number_in_StaffWin = 4;
	/**
	 * Width of the page.
	 */
	public static final String page_width = "100%";

	/**
	 * The layout of the date.
	 */
	public static final String date_format = "yyyy-MM-dd";

	/**
	 * The layout of the date.
	 */
	public static final String date_format_all = "yyyy-MM-dd HH:mm:ss";

	public static final String date_format_chinese = "yyyy年MM月dd日";

	/**
	 * Limit of suggest box.
	 */
	public static final int suggest_box_limit = 10;
}
