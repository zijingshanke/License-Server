/**
 * 
 */
package com.chinarewards.gwt.license.client.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.chinarewards.gwt.license.client.core.view.constant.ViewConstants;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * 
 * 
 * @author dengrenwen
 * @since 0.2.0
 */
public class SimpleDateTimeFormatProvider implements DateTimeFormatProvider {

	private Map<String /* format as key */, DateTimeFormat> formats;

	public SimpleDateTimeFormatProvider() {
		
	}

	public DateTimeFormat getShortDate() {
		
		return buildFormat(ViewConstants.date_format);
		
	}

	public DateTimeFormat getLongDate() {
		return buildFormat(ViewConstants.date_format_all);
	}
	
	protected DateTimeFormat buildFormat(String pattern) {
		
		if(formats==null){
			formats=new HashMap<String, DateTimeFormat>();			
		}
		for(Entry<String ,DateTimeFormat> entry : formats.entrySet()){
			if(entry.getKey().equals(pattern)){
				return entry.getValue();
			}
		}
		DateTimeFormat format = DateTimeFormat.getFormat(pattern);
		formats.put(pattern, format);
		return format;
		
	}

}
