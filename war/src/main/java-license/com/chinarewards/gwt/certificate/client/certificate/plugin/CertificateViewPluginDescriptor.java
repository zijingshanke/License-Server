/**
 * 
 */
package com.chinarewards.gwt.certificate.client.certificate.plugin;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateViewEditorDescriptor;
import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.ExtensionPoint;
import com.chinarewards.gwt.license.client.core.Plugin;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.plugin.PluginConstants;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since 2012-1-16
 */
public class CertificateViewPluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final CertificateViewPlugin licenseViewPlugin;
	final CertificateViewEditorDescriptor licenseViewEditorDescriptor;

	@Inject
	public CertificateViewPluginDescriptor(
			final CertificateViewEditorDescriptor licenseViewEditorDescriptor) {
		this.licenseViewEditorDescriptor = licenseViewEditorDescriptor;
		licenseViewPlugin = new CertificateViewPlugin(this);

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return licenseViewEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return CertificateViewPluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return CertificateConstants.PLUGIN_CERTIFICATE;
	}

	@Override
	public Plugin getInstance() {
		return licenseViewPlugin;
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
