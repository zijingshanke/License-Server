package com.chinarewards.gwt.license.client.core.ui;

public interface EditorDescriptor {

	String getEditorId();

	Editor createEditor(String instanceId, Object model);
}
