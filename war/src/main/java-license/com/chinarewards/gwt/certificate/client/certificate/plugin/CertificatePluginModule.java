
package com.chinarewards.gwt.certificate.client.certificate.plugin;

import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateEditor;
import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateEditorDescriptor;
import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateViewEditor;
import com.chinarewards.gwt.certificate.client.certificate.editor.CertificateViewEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author yanrui
 * @since
 */
public class CertificatePluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CertificatePluginDescriptor.class).in(Singleton.class);

		bind(CertificateEditorDescriptor.class).in(Singleton.class);		
		bind(CertificateEditor.class);
		
		bind(CertificateViewEditorDescriptor.class).in(Singleton.class);
		bind(CertificateViewEditor.class);
	}

}
