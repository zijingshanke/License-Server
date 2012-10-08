package com.chinarewards.gwt.license.client.core.ui;

import java.util.List;

import com.chinarewards.gwt.license.client.core.Platform;

public interface EditorRegistry {

	void initialize(Platform platform);

	void registerEditor(EditorDescriptor item);

	/**
	 * Entry point to open an editor.
	 * 
	 * @param editorId
	 * @param instanceId
	 * @param model
	 */
	void openEditor(String editorId, String instanceId, Object model);

	/**
	 * Actively Close the editor
	 * 
	 * @param editorId
	 * @param instanceId
	 */
	void closeEditor(String editorId, String instanceId);

	/**
	 * Return a list of opened editors.
	 * 
	 * @return
	 */
	List<Editor> getOpenedEditors();
}
