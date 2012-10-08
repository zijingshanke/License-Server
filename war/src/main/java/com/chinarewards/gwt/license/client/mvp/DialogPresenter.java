package com.chinarewards.gwt.license.client.mvp;

import com.chinarewards.gwt.license.client.core.ui.Dialog;

public interface DialogPresenter<D extends Display> extends Presenter<D> {

	/**
	 * Set me if we use this presenter as a dialog
	 * 
	 * @param parent
	 */
	void setDialog(Dialog parent);

}