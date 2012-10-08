
package com.chinarewards.gwt.certificate.client.certificate.editor;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.plugin.CertificateConstants;
import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class CertificateListEditorDescriptor implements EditorDescriptor {

	final Provider<CertificateListEditor> editProvider;

	@Inject
	CertificateListEditorDescriptor(Provider<CertificateListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CertificateConstants.EDITOR_CERTIFICATE_LIST;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CertificateListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("授权证书列表");
		if (model instanceof CertificateClient) {
			if (model != null)
				e.setTitle(((CertificateClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
