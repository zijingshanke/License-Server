package com.chinarewards.gwt.license.client.core.ui;

import com.google.gwt.user.client.ui.Panel;

public interface MenuProcessor {

	void add(MenuItem item);

	MenuItem getMenuItem(String menuId);

	void render(Panel container);
	void initrender(Panel container,String name);
	void changItemColor(String menuName);
	

}
