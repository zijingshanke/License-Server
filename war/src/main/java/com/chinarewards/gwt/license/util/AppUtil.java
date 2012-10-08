/**
 * 
 */
package com.chinarewards.gwt.license.util;

import com.google.gwt.core.client.GWT;

/**
 * 
 * 
 * @author Cream
 * 
 */
public class AppUtil {

	/**
	 * 此方法将通过 {@code GWT#getModuleBaseURL()} 去获得 Context 路径。
	 * <p>
	 * 该方法将尽量保证返回的路径值末位不包含反斜杠'/'。即<strong>不会</strong>返回如下字符串：
	 * <code>http://example.com/context/</code> 而是返回
	 * <code>http://example.com/context</code>
	 * 
	 * @return
	 */
	public static final String getContextPath() {
		String path = GWT.getModuleBaseURL().replace("/" + GWT.getModuleName(), "");
		path = path.replaceAll("/+$", "");
		return path;
	}

}
