
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
public class CertificateViewEditorDescriptor implements EditorDescriptor {

	final Provider<CertificateViewEditor> editProvider;

	@Inject
	CertificateViewEditorDescriptor(Provider<CertificateViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return CertificateConstants.EDITOR_CERTIFICATE_VIEW;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		CertificateViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		
		String name = ((CertificateClient) model).getName();
		String subName = name.length() > 6 ? name.substring(0, 6) : name;
		String title =subName+ "-详细" ;
		e.setTitle(title);
	
		e.setModel(instanceId,model);
		return e;
	}

}
