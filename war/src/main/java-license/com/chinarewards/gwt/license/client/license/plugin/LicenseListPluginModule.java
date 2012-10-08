package com.chinarewards.gwt.license.client.license.plugin;

import com.chinarewards.gwt.license.client.license.editor.LicenseListEditor;
import com.chinarewards.gwt.license.client.license.editor.LicenseListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 */
public class LicenseListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LicenseListPluginDescriptor.class).in(Singleton.class);

		bind(LicenseListEditorDescriptor.class).in(Singleton.class);
		bind(LicenseListEditor.class);
	}

}
