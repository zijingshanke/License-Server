
package com.chinarewards.gwt.license.client.core.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.chinarewards.gwt.license.client.core.MenuRoleStore;

/**
 * @author cyril
 * @since 0.2.0
 */
public class InMemoryMenuRoleStore implements MenuRoleStore {

	Map<String/* role */, Set<String> /* Menu */> roleMenus = new HashMap<String, Set<String>>();

	/**
	 * 
	 * 
	 * @param role
	 * @param menu
	 */
	public void addMapping(String role, String menu) {

		if (role == null)
			throw new IllegalArgumentException("role cannot be null");
		if (menu == null)
			throw new IllegalArgumentException("menu cannot be null");

		Set<String> menus = getMenusForRole(role);
		if (menus == null) {
			menus = new HashSet<String>();
			roleMenus.put(role, menus);
		}
		menus.add(menu);
	}

	public void addRolesToMenu(String menu, String... roles) {

		if (roles == null)
			return;
		for (String role : roles) {
			addMapping(role, menu);
		}

	}

	public void addMenusToRole(String role, String... menus) {
		
		Set<String> rm = this.getMenusForRole(role);
		if (rm == null) {
			rm = new HashSet<String>();
			roleMenus.put(role, rm);
			
			rm.addAll(Arrays.asList(menus));
		}

	}

	/**
	 * Check whether the menu is visible to the specified role.
	 * 
	 * @param menu
	 * @param role
	 */
	public boolean isMenuVisibleToRole(String menu, String role) {

		if (role == null)
			throw new IllegalArgumentException("role cannot be null");
		if (menu == null)
			throw new IllegalArgumentException("menu cannot be null");

		Set<String> menus = getMenusForRole(role);
		if (menus == null)
			return false;
		return menus.contains(menu);
	}

	protected Set<String> getMenusForRole(String role) {
		return roleMenus.get(role);
	}

}
