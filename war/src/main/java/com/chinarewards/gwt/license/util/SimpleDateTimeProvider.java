/**
 * 
 */
package com.chinarewards.gwt.license.util;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;



/**
 * 
 * 
 * @author yanxin
 * 
 */
public class SimpleDateTimeProvider implements DateTimeProvider {

	public Date getTime() {
		return new Date();
	}
	public static String formatData(String partten, Date date) {
		if (StringUtil.isEmpty(partten)) {
			partten = "yyyy-MM-dd HH:mm:ss";
		}
		DateTimeFormat formatYMD = DateTimeFormat.getFormat(partten);
		return formatYMD.format(date);
	}
}
