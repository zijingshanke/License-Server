package com.chinarewards.gwt.license.client.core.ui;

import com.google.gwt.user.client.ui.Image;

/**
 * Extension Point: "core.menu" which should return this interface during
 * Extension.getInstance().
 * 
 * @author kmtong
 * 
 */
public interface MenuItem {

	/**
	 * Natural ordering of the menu item, comparing between the same level of
	 * menu items.
	 * 
	 * @return
	 */
	int getOrder();

	/**
	 * Dotted notation of menu item, "root" is the root-level menu item, and if
	 * you want to describe menu ID = "root.member", the parentMenuId should be
	 * "root" and the menuId should be simply "member".
	 * 
	 * @return
	 */
	String getMenuId();

	/**
	 * Dotted notation of parent menu item, "root" is the root-level menu item,
	 * and if parentMenuId = "root.member", then the menu associated should be
	 * under "Member", and "root.member" must exist beforehand.
	 * 
	 * @return "root" if it is attached to the root menu
	 */
	String getParentMenuId();

	/**
	 * Text to be display in the menu item
	 * 
	 * @return
	 */
	String getTitle();

	/**
	 * When the menu item is clicked, this would be called.
	 */
	void execute();

	/**
	 * Return the Icon of the Menu, NULL to use a default icon.
	 * 
	 * @return
	 */
	Image getIcon();
}
