package com.chinarewards.gwt.license.client.core.ui;

import com.google.gwt.user.client.ui.Widget;

public interface Editor {

	String getEditorId();

	String getInstanceId();

	String getTitle();

	boolean isDirty(); 

	void save();

	Widget asWidget();

	/**
	 * this editor should be notified before closing it by the Site.
	 * 
	 * @return true if the editor is willing to be closed, or false if the
	 *         editor should not be closed.
	 */
	boolean beforeClose();

	/**
	 * Actively close the current editor, will trigger an EditorCloseEvent that
	 * goes thru the SiteManager to help to close the editor.
	 */
	void close();
}
