package com.chinarewards.gwt.license.client.core.ui;

import com.google.gwt.user.client.ui.Widget;

public interface Dialog {

	String getDialogId();

	String getInstanceId();

	String getTitle();

	Widget asWidget();

	/**
	 * this dialog should be notified before closing it by the Site.
	 * 
	 * @return true if the dialog is willing to be closed, or false if the
	 *         editor should not be closed.
	 */
	boolean beforeClose();

	/**
	 * Actively close the current dialog, will trigger an DialogCloseEvent that
	 * goes thru the SiteManager to help to close the dialog.
	 * 
	 */
	void close();

}
