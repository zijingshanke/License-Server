package com.chinarewards.gwt.license.client.win.confirm;


import com.google.gwt.event.shared.GwtEvent;

/**
 * Chosen staff event.
 * 
 * @author yanxin
 * 
 */
public class WinEvent extends GwtEvent<WinHandler> {

	private static Type<WinHandler> TYPE = new Type<WinHandler>();

	public WinEvent() {

	}

	public static Type<WinHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(WinHandler handler) {
		handler.confirm();
	}

	@Override
	public Type<WinHandler> getAssociatedType() {
		return getType();
	}

}
