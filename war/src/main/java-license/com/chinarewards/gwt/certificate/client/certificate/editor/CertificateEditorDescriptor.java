package com.chinarewards.gwt.certificate.client.certificate.editor;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.plugin.CertificateConstants;
import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 * @since 2012年1月9日 17:25:09
 */
public class CertificateEditorDescriptor implements EditorDescriptor {

	final Provider<CertificateEditor> editProvider;

	@Inject
	CertificateEditorDescriptor(Provider<CertificateEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CertificateConstants.EDITOR_CERTIFICATE_EDIT;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CertificateEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("新建授权证书");
		if (model instanceof CertificateClient) {
			if (model != null)
				e.setTitle(((CertificateClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
