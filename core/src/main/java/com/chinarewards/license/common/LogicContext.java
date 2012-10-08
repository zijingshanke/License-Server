/**
 * 
 */
package com.chinarewards.license.common;

import com.chinarewards.license.model.user.UserRole;

/**
 * Defines the context which a logic executes within. It is not supposed that
 * the context is mutable.
 * 
 * @author cyril
 * @since 0.0.1
 */
public interface LogicContext {

	/**
	 * Returns the principal of the caller. This method never return
	 * <code>null</code>.
	 * 
	 * @return the principal which invokes this operation.
	 */
	public Principal getPrincipal();

	/**
	 * Check whether this use does belong to the specified role.
	 * 
	 * @param role
	 * @return
	 */
	public boolean isCallerInRole(UserRole role);

}
