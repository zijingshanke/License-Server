/**
 * 
 */
package com.chinarewards.gwt.license.util;

import java.util.Date;

/**
 * 
 * 
 * @author yanxin
 * @since 0.2.0
 */
public interface DateTimeProvider {

	/**
	 * Returns the current system date and time. This method should never return
	 * <code>null</code>.
	 * 
	 * @return
	 */
	public Date getTime();

}
