package com.chinarewards.gwt.license.client.plugin;

/**
 * Defined menu constants data here. Please careful the order of constants
 * parameter. It will be the order of menu list.
 * 
 * @author yanrui
 */
public abstract class MenuConstants {

	private static int order = 0;

	// Welcome
	public static final int MENU_ORDER_REWARDS_ENTRY = order++;

	// 账号管理
	public static final int MENU_ORDER_USER_SEARCH = order++;

	// 员工信息维护
	public static final int MENU_ORDER_STAFF_SEARCH = order++;

	// 员工等级
	public static final int MENU_ORDER_STAFF_LEVEL = order++;

	// 员工添加
	public static final int MENU_ORDER_STAFF_CREATE = order++;

	// 员工信息更新
	public static final int MENU_ORDER_STAFF_UPDATE = order++;
	
	public static final int MENU_ORDER_STAFFLIST_EDIT=order++;

	// 授权证书
	public static final int MENU_ORDER_LICENSE_LIST = order++;

	public static final int MENU_ORDER_LICENSE_ADD = order++;
	
	public static final int MENU_ORDER_CERTIFICATE_LIST=order++;
	public static final int MENU_ORDER_CERTIFICATE_ADD=order++;

}
