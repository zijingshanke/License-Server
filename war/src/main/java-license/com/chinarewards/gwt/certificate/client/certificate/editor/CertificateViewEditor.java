package com.chinarewards.gwt.certificate.client.certificate.editor;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateViewPresenter;
import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class CertificateViewEditor extends AbstractEditor {

	final CertificateViewPresenter licenseViewPresenter;
	Object model;

	@Inject
	protected CertificateViewEditor(CertificateViewEditorDescriptor editorDescriptor,
			CertificateViewPresenter licenseViewPresenter) {
		super(editorDescriptor);
		this.licenseViewPresenter = licenseViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return licenseViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		licenseViewPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(String instanceId, Object model) {
		this.model=model;
		licenseViewPresenter.bind();
		if (model != null) {
			licenseViewPresenter.initInstanceId(instanceId, (CertificateClient) model);

		}
	}
}
