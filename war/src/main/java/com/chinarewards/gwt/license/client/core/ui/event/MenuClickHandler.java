package com.chinarewards.gwt.license.client.core.ui.event;

import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.google.gwt.event.shared.EventHandler;

public interface MenuClickHandler extends EventHandler {

	void onClick(MenuItem menuItem);

}
