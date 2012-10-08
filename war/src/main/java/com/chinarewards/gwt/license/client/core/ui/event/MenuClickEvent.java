package com.chinarewards.gwt.license.client.core.ui.event;

import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.google.gwt.event.shared.GwtEvent;

public class MenuClickEvent extends GwtEvent<MenuClickHandler> {

	private static Type<MenuClickHandler> TYPE = new Type<MenuClickHandler>();

	final MenuItem menuItem;

	public static Type<MenuClickHandler> getType() {
		return TYPE;
	}

	public MenuClickEvent(MenuItem item) {
		this.menuItem = item;
	}

	@Override
	protected void dispatch(MenuClickHandler handler) {
		handler.onClick(menuItem);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MenuClickHandler> getAssociatedType() {
		return TYPE;
	}

}
