package com.chinarewards.gwt.certificate.client.certificate.plugin;

import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateListEditor;
import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateListEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 */
public class CertificateListPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CertificateListPluginDescriptor.class).in(Singleton.class);

		bind(CertificateListEditorDescriptor.class).in(Singleton.class);
		bind(CertificateListEditor.class);
	}

}
