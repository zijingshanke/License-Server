package com.chinarewards.gwt.certificate.client.certificate.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateEditorDescriptor;
import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.ExtensionPoint;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.Plugin;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.plugin.MenuConstants;
import com.chinarewards.gwt.license.client.plugin.PluginConstants;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since
 */
public class CertificatePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final CertificatePlugin CertificatePlugin;
	final CertificateEditorDescriptor licenseEditorDescriptor;

	@Inject
	public CertificatePluginDescriptor(final CertificateEditorDescriptor licenseEditorDescriptor) {
		this.licenseEditorDescriptor = licenseEditorDescriptor;
		CertificatePlugin = new CertificatePlugin(this);

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
						return MenuConstants.MENU_ORDER_CERTIFICATE_ADD;
					}

					@Override
					public String getMenuId() {
						return CertificateConstants.MENU_CERTIFICATE_ADD;
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
						CertificateClient licenseClient = new CertificateClient();
						licenseClient.setThisAction(CertificateConstants.ACTION_CERTIFICATE_ADD);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(CertificateConstants.EDITOR_CERTIFICATE_EDIT,
										CertificateConstants.ACTION_CERTIFICATE_ADD,
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
				return CertificatePluginDescriptor.this;
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
				return CertificatePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return CertificateConstants.PLUGIN_CERTIFICATE;
	}

	@Override
	public Plugin getInstance() {
		return CertificatePlugin;
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
