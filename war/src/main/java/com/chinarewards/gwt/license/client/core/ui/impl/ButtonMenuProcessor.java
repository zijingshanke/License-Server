package com.chinarewards.gwt.license.client.core.ui.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.chinarewards.gwt.certificate.client.certificate.plugin.CertificateConstants;
import com.chinarewards.gwt.license.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.license.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.chinarewards.gwt.license.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ButtonMenuProcessor implements MenuProcessor {

	final EventBus eventBus;
	final BreadCrumbsMenu breadCrumbsMenu;
	final SessionManager sessionManager;
	List<MenuItem> items = new LinkedList<MenuItem>();
	MenuNode root = new MenuNode(null);
	VerticalPanel grid;

	@Inject
	public ButtonMenuProcessor(EventBus eventBus,
			BreadCrumbsMenu breadCrumbsMenu, SessionManager sessionManager) {
		this.eventBus = eventBus;
		this.breadCrumbsMenu = breadCrumbsMenu;
		this.sessionManager = sessionManager;

	}

	public void add(MenuItem item) {
		items.add(item);
	}

	public MenuItem getMenuItem(String menuId) {
		if (menuId != null) {
			for (MenuItem i : items) {
				if (menuId.endsWith(i.getMenuId())) {
					return i;
				}
			}
		}
		return null;
	}

	public void render(Panel container) {
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		for (MenuItem m : items) {
			root.appendChild(new MenuNode(m));
		}

		String indexMenu = "";
		if (sessionManager.getSession().getLastLoginRole() == UserRoleVo.CORP_ADMIN) {
			indexMenu = "License";
		}

		ScrollPanel menuWrapper = new ScrollPanel(
				createButtonMenuWidget(indexMenu));
		container.add(menuWrapper);
	}

	/**
	 * 
	 * 
	 * @param parent
	 * @param node
	 */
	private Widget createButtonMenuWidget(String name) {
		breadCrumbsMenu.cleanBreadCrumbsItemAll();
		grid = new VerticalPanel();
		grid.setWidth("100%");
		// int i = 0;
		for (MenuNode node : root.getChildren()) {
			final Anchor button = new Anchor();
			final MenuItem menuItem = node.getValue();
			if (name != null) {
				List<String> items = getMenuItemName(name);
				if (!items.contains(menuItem.getMenuId()))
					continue;
			} else {
				break;
			}

			button.setText(menuItem.getTitle());
			button.setStyleName("menu-link");

			// 判断第一个进入默认样式
			String menuId = menuItem.getMenuId();
			if (menuId.equals(LicenseConstants.MENU_LICENSE_LIST)) {

				button.setStyleName("menu-link menu-selected");
				breadCrumbsMenu.cleanBreadCrumbsItemTop();
				if (menuId.equals(LicenseConstants.MENU_LICENSE_LIST)){
					breadCrumbsMenu.addBreadCrumbsItemTop("授权", null);
				}
				// else if
				// (menuId.equals(StaffListConstants.MENU_STAFFLIST_SEARCH))
				// breadCrumbsMenu.addBreadCrumbsItemTop("员工数据", null);
				// else if
				// (menuId.equals(EnterpriseConstants.MENU_ENTERPRISE_EDIT))
				// breadCrumbsMenu.addBreadCrumbsItemTop("设置", null);
			}

			button.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent paramClickEvent) {
					button.setStyleName("menu-link menu-selected");
					breadCrumbsMenu.cleanBreadCrumbsItem();
					breadCrumbsMenu.addBreadCrumbsItem(menuItem.getTitle(),
							menuItem);
					eventBus.fireEvent(new MenuClickEvent(menuItem));
					for (int i = 0; i < grid.getWidgetCount(); i++) {
						if (grid.getWidget(i) instanceof Anchor) {
							if (!button.getText().equals(
									((Anchor) grid.getWidget(i)).getText())) {
								grid.getWidget(i).setStyleName("menu-link");
							}
						}
					}
				}
			});
			grid.add(button);
			// i++;
		}

		return grid;
	}

	private List<String> getMenuItemName(String keyname) {
		List<String> items = new ArrayList<String>();
//		if ("License".equals(keyname)) {
			items.add(LicenseConstants.MENU_LICENSE_LIST);
			items.add(CertificateConstants.MENU_CERTIFICATE_LIST);
			
//		}
//		else if ("Setting".equals(keyname)) {
//			items.add(EnterpriseConstants.MENU_ENTERPRISE_EDIT);
//			//items.add(EnterpriseConstants.MENU_INTEGRAL_PRICE_EDIT);
//			//items.add(EnterpriseConstants.MENU_PERIOD_EDIT);
//			items.add(LeadTimeConstants.MENU_LEADTIME_SEARCH);
//			items.add(EnterpriseConstants.MENU_MAILSET_EDIT);
//		}
		return items;
	}

	@Override
	public void initrender(Panel container, String name) {
		// organize tree
		// sort according to menuID string length
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		// pack into the MenuNode structure
		// for (MenuItem m : items) {
		// // append children recursively
		// root.appendChild(new MenuNode(m));
		// }

		ScrollPanel menuWrapper = new ScrollPanel(createButtonMenuWidget(name));
		container.clear();
		container.add(menuWrapper);

	}

	@Override
	public void changItemColor(String menuName) {
		for (int i = 0; i < grid.getWidgetCount(); i++) {
			if (grid.getWidget(i) instanceof Anchor) {
				System.out.println(((Anchor) grid.getWidget(i)).getText() + "");
				if (!menuName.equals(((Anchor) grid.getWidget(i)).getText())) {
					grid.getWidget(i).setStyleName("menu-link");
				} else {
					grid.getWidget(i).setStyleName("menu-link menu-selected");
				}
			}
		}

	}

}
