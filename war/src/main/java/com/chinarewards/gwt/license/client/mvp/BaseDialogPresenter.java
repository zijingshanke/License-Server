package com.chinarewards.gwt.license.client.mvp;

import com.chinarewards.gwt.license.client.core.ui.Dialog;

public abstract class BaseDialogPresenter<D extends Display> extends
		BasePresenter<D> implements DialogPresenter<D> {

	public BaseDialogPresenter(EventBus eventBus, D display) {
		super(eventBus, display);
	}

	Dialog parent = null;

	@Override
	public void setDialog(Dialog parent) {
		this.parent = parent;
	}

	protected void closeDialog() {
		if (parent != null) {
			parent.close();
		}
	}
}
