package com.chinarewards.gwt.license.client.license.model;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author yanrui 
 * @version 创建时间：2012-2-8 下午12:35:51 
 * 类说明 
 */
/**
 * @author Administrator
 * 
 */
public class LicenseType {
	public static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("TRIAL", "试用版");
		map.put("OFFICIAL", "正式版");
	}

}
