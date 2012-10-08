/**
 * 
 */
package com.chinarewards.gwt.license.client.user.plugin;

import com.chinarewards.gwt.license.client.user.editor.UserSearchEditor;
import com.chinarewards.gwt.license.client.user.editor.UserSearchEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author Cream
 * @since 0.0.1
 */
public class UserPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserPluginDescriptor.class).in(Singleton.class);

		bind(UserSearchEditorDescriptor.class).in(Singleton.class);
		bind(UserSearchEditor.class);
	}

}
