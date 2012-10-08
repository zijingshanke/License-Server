/**
 * 
 */
package com.chinarewards.license.common;

import com.chinarewards.license.model.user.UserRole;

/**
 * 
 * 
 * @author cyril
 * @since 0.0.1
 */
public interface Principal {

	/**
	 * Returns the user ID. This is the ID of the HrUser. If no user is
	 * identified, this method returns <code>null</code>.
	 * 
	 * @return
	 */
	public String getUserId();

	/**
	 * Returns the corresponding enterprise ID of the HrUser. If no user is
	 * identified, this method returns <code>null</code>.
	 * <p>
	 * 
	 * Used getCorporationId() next This is CRM's merchant ID.
	 * 
	 * @return
	 */
//	@Deprecated
//	public String getEnterpriseId();

	/**
	 * 查看这个User是那个企业
	 * 
	 * @return
	 */
	public String getCorporationId();

	/**
	 * 是否是部门管理员
	 * 
	 * @return
	 * @deprecated use {@link getUserRoles()} to check if the user had
	 *             department manager role.
	 */
	public boolean isDepartmentManager();

	/**
	 * 查看这个用户的角色
	 * 
	 * @return
	 */
	//public UserType getUserType();

	public UserRole[] getUserRoles();
	
	public String getLoginName();

	public String printTheProperty();

}
