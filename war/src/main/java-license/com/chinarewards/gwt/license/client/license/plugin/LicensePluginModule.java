
package com.chinarewards.gwt.license.client.license.plugin;

import com.chinarewards.gwt.license.client.license.editor.LicenseEditor;
import com.chinarewards.gwt.license.client.license.editor.LicenseEditorDescriptor;
import com.chinarewards.gwt.license.client.license.editor.LicenseViewEditor;
import com.chinarewards.gwt.license.client.license.editor.LicenseViewEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 * @since
 */
public class LicensePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LicensePluginDescriptor.class).in(Singleton.class);

		bind(LicenseEditorDescriptor.class).in(Singleton.class);		
		bind(LicenseEditor.class);
		
		bind(LicenseViewEditorDescriptor.class).in(Singleton.class);
		bind(LicenseViewEditor.class);
	}

}
