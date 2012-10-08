package com.chinarewards.gwt.license.client.core.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * This class controls the lifecycle of the Editor Area.
 * 
 * @author kmtong
 * 
 */
public interface SiteManager {

	Panel getMenuArea();

	void initialize(RootLayoutPanel rootPanel);
	void initializeStaff(RootLayoutPanel rootPanel);

	/**
	 * Simply render the editor. To obtain the editors status, use
	 * <code>EditorRegistry</code>.
	 * 
	 * @param editor
	 */
	void openEditor(Editor editor);

	/**
	 * Make the specified editor the active editor. The editor given should be
	 * opened before by calling openEditor().
	 * 
	 * @param e
	 */
	void focusEditor(Editor e);

	/**
	 * Hook with the Menu Registry.
	 * 
	 * @param menuProcessor
	 */
	void setMenuProcessor(MenuProcessor menuProcessor);

	/**
	 * Popup a Dialog object (this method request a dialog object, not by dialog
	 * ID). Popup by a shared dialog ID is not implemented yet which require
	 * plugin registration.
	 * 
	 * @param dialog
	 * @param handler
	 */
	void openDialog(Dialog dialog, DialogCloseListener handler);

	/**
	 * closed the given Dialog.
	 * 
	 * @param dialog
	 */
	void closeDialog(Dialog dialog);

	/**
	 * close all
	 */
	void closeAllDialog();
}
