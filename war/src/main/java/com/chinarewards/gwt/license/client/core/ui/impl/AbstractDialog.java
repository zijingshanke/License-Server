package com.chinarewards.gwt.license.client.core.ui.impl;

import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.ui.Dialog;


public abstract class AbstractDialog implements Dialog {

	final String instanceId;
	final String dialogId;
	String title;

	protected AbstractDialog(String dialogId, String instanceId, String title) {
		this.dialogId = dialogId;
		this.instanceId = instanceId;
		this.title = title;
	}

	protected AbstractDialog(String dialogId, String instanceId) {
		this(dialogId, instanceId, null);
	}

	public String getDialogId() {
		return dialogId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void close() {
		Platform.getInstance().getSiteManager().closeDialog(this);
	}
}
