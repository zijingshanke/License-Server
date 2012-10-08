package com.chinarewards.gwt.license.client.core.ui.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.chinarewards.gwt.license.client.core.ui.MenuItem;


public class MenuNode {

	List<MenuNode> children = new LinkedList<MenuNode>();
	MenuItem menuItem;
	MenuNode parent = null;

	/**
	 * For ROOT menu, given MenuItem can be null.
	 * 
	 * @param item
	 */
	public MenuNode(MenuItem item) {
		this.menuItem = item;
	}

	/**
	 * append the menu node recursively in this tree by comparing the
	 * parentMenuId.
	 * 
	 * @param n
	 */
	public void appendChild(MenuNode n) {
		String parentId = n.getValue().getParentMenuId();
		if (parentId == null) {
			parentId = "root";
		}

		if (menuItem == null && "root".equals(parentId)) {
			// root item hit!
			n.setParent(this);
			children.add(n);
		} else if (menuItem != null && parentId != null
				&& parentId.equals(getPath())) {
			// hit
			n.setParent(this);
			children.add(n);
		} else {
			// recursively look at deeper levels
			for (MenuNode i : children) {
				if (i.getPath().equals(parentId)) {
					i.appendChild(n);
					return;
				}
			}
			throw new RuntimeException("Parent Menu Not Found: " + parentId);
		}
	}

	public MenuNode findChild(String menuId) {
		for (MenuNode n : children) {
			if (menuId != null && menuId.equals(n.getValue().getMenuId())) {
				return n;
			}
		}
		return null;
	}

	public MenuItem getValue() {
		return menuItem;
	}

	public MenuNode getParent() {
		return parent;
	}

	public void setParent(MenuNode parent) {
		this.parent = parent;
	}

	public String getPath() {
		String path = (parent == null) ? "root" : parent.getPath() + "."
				+ menuItem.getMenuId();
		return path;
	}

	public List<MenuNode> getChildren() {
		Collections.sort(children, new Comparator<MenuNode>() {
			public int compare(MenuNode paramT1, MenuNode paramT2) {
				return paramT1.getValue().getOrder()
						- paramT2.getValue().getOrder();
			}
		});
		return children;
	}

}
