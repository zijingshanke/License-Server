/**
 * 
 */
package com.chinarewards.gwt.license.client.user.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.ExtensionPoint;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.Plugin;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.plugin.MenuConstants;
import com.chinarewards.gwt.license.client.plugin.PluginConstants;
import com.chinarewards.gwt.license.client.user.editor.UserSearchEditorDescriptor;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class UserPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final UserPlugin userPlugin;
	final UserSearchEditorDescriptor userSearchEditorDescriptor;

	@Inject
	public UserPluginDescriptor(
			UserSearchEditorDescriptor userSearchEditorDescriptorVo) {
		this.userSearchEditorDescriptor = userSearchEditorDescriptorVo;
		userPlugin = new UserPlugin(this);

		/**
		 * Search user menu
		 */
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.MENU;
			}

			@Override
			public Object getInstance() {
				return new MenuItem() {

					@Override
					public int getOrder() {
						return MenuConstants.MENU_ORDER_USER_SEARCH;
					}

					@Override
					public String getMenuId() {
						return UserConstants.MENU_USER_SEARCH;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "账号管理";
					}

					@Override
					public void execute() {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(UserConstants.EDITOR_USER_SEARCH,
										"EDITOR_USER_SEARCH_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserPluginDescriptor.this;
			}

		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return userSearchEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return UserPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return UserConstants.PLUGIN_USER;
	}

	@Override
	public Plugin getInstance() {
		return userPlugin;
	}

	@Override
	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	@Override
	public Set<Extension> getExtensions() {
		return ext;
	}

}
