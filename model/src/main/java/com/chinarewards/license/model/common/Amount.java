/**
 * 
 */
package com.chinarewards.license.model.common;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public interface Amount {

	/**
	 * Returns the amount.
	 * 
	 * @return
	 */
	public BigDecimal getAmount();

	/**
	 * Returns the unique currency symbol.
	 * 
	 * @return
	 */
	public String getCurrency();

}
