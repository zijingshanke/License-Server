/**
 * 
 */
package com.chinarewards.gwt.certificate.client.certificate.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateListEditorDescriptor;
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
 * @author YANRUI
 */
public class CertificateListPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final CertificateListPlugin CertificateListPlugin;
	final CertificateListEditorDescriptor licenseListEditorDescriptor;

	@Inject
	public CertificateListPluginDescriptor(
			final CertificateListEditorDescriptor licenseListEditorDescriptor) {
		this.licenseListEditorDescriptor = licenseListEditorDescriptor;
		CertificateListPlugin = new CertificateListPlugin(this);

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
						return MenuConstants.MENU_ORDER_CERTIFICATE_LIST;
					}

					@Override
					public String getMenuId() {
						return CertificateConstants.MENU_CERTIFICATE_LIST;
					}

					@Override
					public String getParentMenuId() {
						return null;
					}

					@Override
					public String getTitle() {
						return "授权列表";
					}

					@Override
					public void execute() {

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										CertificateConstants.EDITOR_CERTIFICATE_LIST,
										"EDITOR_CERTIFICATE_LIST_DO_ID", null);
					}

					@Override
					public Image getIcon() {
						return null;
					}

				};
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CertificateListPluginDescriptor.this;
			}

		});


		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return licenseListEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CertificateListPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return CertificateConstants.PLUGIN_CERTIFICATE_LIST;
	}

	@Override
	public Plugin getInstance() {
		return CertificateListPlugin;
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
