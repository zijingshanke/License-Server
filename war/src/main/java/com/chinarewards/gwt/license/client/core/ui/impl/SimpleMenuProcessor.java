package com.chinarewards.gwt.license.client.core.ui.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.license.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.inject.Inject;

public class SimpleMenuProcessor implements MenuProcessor {

	final EventBus eventBus;
	final MenuIconResources resources;

	MenuNode root = new MenuNode(null);

	List<MenuItem> items = new LinkedList<MenuItem>();

	@Inject
	public SimpleMenuProcessor(EventBus bus, MenuIconResources resources) {
		this.eventBus = bus;
		this.resources = resources;
	}

	public void add(MenuItem item) {
		items.add(item);
	}

	public void render(Panel container) {
		// organize tree

		// sort according to menuID string length
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		// pack into the MenuNode structure
		for (MenuItem m : items) {
			// append children recursively
			root.appendChild(new MenuNode(m));
		}
		
		Tree tree = new Tree();
		tree.addStyleName("hihi");
		TreeItem rootItem = new TreeItem("HR SYSTEM");
		for (MenuNode n : root.getChildren()) {
			addSubTreeNode(rootItem, n);
		}
		
		boolean addToRoot = true;
		if (addToRoot) {
			// re-root child elements to the root of tree
			while (rootItem.getChildCount() > 0) {
			//for (int i = 0; i < rootItem.getChildCount(); i++) {
				TreeItem item = rootItem.getChild(0);
				tree.addItem(item);
				item.setState(true);
			}
		} else {
			for (int i = 0; i < rootItem.getChildCount(); i++) {
				TreeItem item = rootItem.getChild(i);
				item.setState(true);
	//			tree.addItem(item);
			}
			rootItem.setState(true);
			tree.addItem(rootItem);
		}
		
		ScrollPanel menuWrapper = new ScrollPanel(tree);
		container.add(menuWrapper);
	}

	/**
	 * 
	 * 
	 * @param parent
	 * @param node
	 */
	private void addSubTreeNode(TreeItem parent, final MenuNode node) {

		TreeItem item = new TreeItem();
		Grid grid = new Grid(1, 2);
//		if (node.getValue().getIcon() != null) {
//	//		grid.setWidget(0, 0, node.getValue().getIcon());
//		} else {
//			// default menu icon
//	//		grid.setWidget(0, 0, new Image(resources.getDefaultMenuIcon()));
//		}

		// setup handler to fire menu click event
		Label text = new Label(node.getValue().getTitle());
		text.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent paramClickEvent) {
				eventBus.fireEvent(new MenuClickEvent(node.getValue()));
			}
		});
		grid.setWidget(0, 1, text);

		// make it visible
		item.setWidget(grid);
		item.setVisible(true);
		item.setState(true);
		parent.addItem(item);

		// continue processing child menus if any.
		for (MenuNode i : node.getChildren()) {
			addSubTreeNode(item, i);
		}
	}

	public MenuItem getMenuItem(String menuId) {
		if (menuId != null) {
			// XXX use smarter data structures
			for (MenuItem i : items) {
				if (menuId.endsWith(i.getMenuId())) {
					return i;
				}
			}
		}
		return null;
	}

	public void initrender(Panel container, String name) {
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

//		// pack into the MenuNode structure
//		for (MenuItem m : items) {
//			// append children recursively
//			root.appendChild(new MenuNode(m));
//		}
		
		Tree tree = new Tree();
		tree.addStyleName("hihi");
		TreeItem rootItem = new TreeItem("收件箱");
		for (MenuNode n : root.getChildren()) {
			System.out.println(n.getValue().getTitle());
			addSubTreeNode(rootItem, n);
		}

			// re-root child elements to the root of tree
			while (rootItem.getChildCount() > 0) {
			//for (int i = 0; i < rootItem.getChildCount(); i++) {
				TreeItem item = rootItem.getChild(0);
				tree.addItem(item);
				item.setState(true);
			}
	
		
		ScrollPanel menuWrapper = new ScrollPanel(tree);
		container.clear();
		container.add(menuWrapper);
		
	}

	@Override
	public void changItemColor(String menuName) {
		// TODO Auto-generated method stub
		
	}
}
