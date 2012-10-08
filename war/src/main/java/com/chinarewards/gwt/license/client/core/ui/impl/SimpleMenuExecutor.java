package com.chinarewards.gwt.license.client.core.ui.impl;

import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.core.ui.SiteManager;
import com.chinarewards.gwt.license.client.core.ui.event.MenuClickHandler;


public class SimpleMenuExecutor implements MenuClickHandler {

	final SiteManager siteManager;

	protected SimpleMenuExecutor(SiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public void onClick(MenuItem menuItem) {
		menuItem.execute();
	}

}
