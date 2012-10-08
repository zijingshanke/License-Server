package com.chinarewards.gwt.license.client.core.ui.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.license.client.core.ui.EditorRegistry;
import com.chinarewards.gwt.license.client.core.ui.event.EditorCloseEvent;
import com.chinarewards.gwt.license.client.core.ui.event.EditorCloseHandler;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

public class SimpleEditorRegistry implements EditorRegistry, EditorCloseHandler {

	Platform platform;

	Map<String, EditorDescriptor> editors;

	/**
	 * InstanceID -> Editor
	 */
	HashMap<String, Editor> openedEditors;

	final EventBus eventBus;

	@Inject
	public SimpleEditorRegistry(EventBus eventBus) {
		editors = new HashMap<String, EditorDescriptor>();
		openedEditors = new HashMap<String, Editor>();
		this.eventBus = eventBus;
	}

	public void initialize(Platform platform) {
		this.platform = platform;
		eventBus.addHandler(EditorCloseEvent.getType(), this);
	}

	public void openEditor(String editorId, String instanceId, Object model) {
		EditorDescriptor d = editors.get(editorId);
		Editor e;
		if (!openedEditors.containsKey(instanceId)) {
			// create an editor
			Editor editor = d.createEditor(instanceId, model);
			// open it using site manager
			platform.getSiteManager().openEditor(editor);
			// put it in opened list
			// avoid instanceId mismatch, use passed-in parameter
		//  	openedEditors.put(instanceId, editor);
			e = editor;
			// alert to the user for development time correction
			if (instanceId != null
					&& !instanceId.equals(editor.getInstanceId())) {
				Window.alert("Editor '" + editorId + "' Error: InstanceID "
						+ editor.getInstanceId() + " mismatched.  Should be "
						+ instanceId);
			}
		} else {
			e = openedEditors.get(instanceId);

		}
		// focus the tab
		
		platform.getSiteManager().focusEditor(e);
	}

	public void closeEditor(String editorId, String instanceId) {
		Editor e = openedEditors.get(instanceId);
		if (e != null) {
			if (e.beforeClose()) {
				eventBus.fireEvent(new EditorCloseEvent(e.getEditorId(), e
						.getInstanceId()));
			}
		} else {
			Window.alert("Editor '" + editorId + "': InstanceID " + instanceId
					+ " not opened.");
		}
	}

	public void registerEditor(EditorDescriptor editor) {
		editors.put(editor.getEditorId(), editor);
	}

	public List<Editor> getOpenedEditors() {
		return new LinkedList<Editor>(openedEditors.values());
	}

	public void onClose(String editorId, String instanceId) {
		openedEditors.remove(instanceId);
	}

}
