package com.chinarewards.gwt.license.client.core.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditorCloseEvent extends GwtEvent<EditorCloseHandler> {

	private static Type<EditorCloseHandler> TYPE = new Type<EditorCloseHandler>();

	final String editorId;
	final String instanceId;

	public static Type<EditorCloseHandler> getType() {
		return TYPE;
	}

	public EditorCloseEvent(String editorId, String instanceId) {
		this.editorId = editorId;
		this.instanceId = instanceId;
	}

	@Override
	protected void dispatch(EditorCloseHandler handler) {
		handler.onClose(editorId, instanceId);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditorCloseHandler> getAssociatedType() {
		return TYPE;
	}

}
