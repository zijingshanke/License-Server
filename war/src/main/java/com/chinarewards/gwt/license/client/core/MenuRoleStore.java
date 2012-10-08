/**
 * 
 */
package com.chinarewards.gwt.license.client.core;

/**
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public interface MenuRoleStore {

	/**
	 * 
	 * 
	 * @param role
	 * @param menu
	 */
	public void addMapping(String role, String menu);

	/**
	 * 
	 * 
	 * @param menu
	 * @param roles
	 */
	public void addRolesToMenu(String menu, String... roles);

	/**
	 * 
	 * 
	 * @param menu
	 * @param roles
	 */
	public void addMenusToRole(String role, String... menus);

	/**
	 * Check whether the menu is visible to the specified role.
	 * 
	 * @param menu
	 * @param role
	 */
	public boolean isMenuVisibleToRole(String menu, String role);

}
