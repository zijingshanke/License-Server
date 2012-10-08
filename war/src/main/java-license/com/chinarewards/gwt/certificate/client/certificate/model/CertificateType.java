package com.chinarewards.gwt.certificate.client.certificate.model;

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
public class CertificateType {
	public static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("1", "实物");
		map.put("2", "虚拟");
	}

}
