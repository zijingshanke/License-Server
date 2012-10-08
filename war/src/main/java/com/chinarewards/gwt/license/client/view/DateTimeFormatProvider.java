/**
 * 
 */
package com.chinarewards.gwt.license.client.view;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public interface DateTimeFormatProvider {

	/**
	 * Returns a short date format， which is <code>yyyy-mm-dd</code>.
	 * 
	 * @return
	 */
	public DateTimeFormat getShortDate();

	/**
	 * Returns a long date format， which is <code>yyyy-mm-dd HH:ii:ss</code>.
	 * 
	 * @return
	 */
	public DateTimeFormat getLongDate();

}
