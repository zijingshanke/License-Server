package com.chinarewards.gwt.license.client.mvp;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

public interface EventBus {

	<H extends EventHandler> HandlerRegistration addHandler(Type<H> type,
			H handler);

	void fireEvent(GwtEvent<?> event);

	<H extends EventHandler> H getHandler(Type<H> type, int index);

	int getHandlerCount(Type<?> type);

	boolean isEventHandled(Type<?> e);

}
