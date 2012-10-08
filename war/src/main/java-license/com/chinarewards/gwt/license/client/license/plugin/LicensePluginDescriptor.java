package com.chinarewards.gwt.license.client.license.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.ExtensionPoint;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.Plugin;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.license.editor.LicenseEditorDescriptor;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.plugin.MenuConstants;
import com.chinarewards.gwt.license.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since
 */
public class LicensePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final LicensePlugin LicensePlugin;
	final LicenseEditorDescriptor licenseEditorDescriptor;

	@Inject
	public LicensePluginDescriptor(final LicenseEditorDescriptor licenseEditorDescriptor) {
		this.licenseEditorDescriptor = licenseEditorDescriptor;
		LicensePlugin = new LicensePlugin(this);

		/**
		 * 授权列表
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
						return MenuConstants.MENU_ORDER_LICENSE_ADD;
					}

					@Override
					public String getMenuId() {
						return LicenseConstants.MENU_LICENSE_ADD;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "颁发授权";
					}

					@Override
					public void execute() {
						LicenseClient licenseClient = new LicenseClient();
						licenseClient.setThisAction(LicenseConstants.ACTION_LICENSE_ADD);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(LicenseConstants.EDITOR_LICENSE_EDIT,
										LicenseConstants.ACTION_LICENSE_ADD,
										licenseClient);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return LicensePluginDescriptor.this;
			}
		});

		
		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return licenseEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return LicensePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return LicenseConstants.PLUGIN_LICENSE;
	}

	@Override
	public Plugin getInstance() {
		return LicensePlugin;
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
