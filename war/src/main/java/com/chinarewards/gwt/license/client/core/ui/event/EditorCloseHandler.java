package com.chinarewards.gwt.license.client.core.ui.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditorCloseHandler extends EventHandler {

	void onClose(String editorId, String instanceId);

}
