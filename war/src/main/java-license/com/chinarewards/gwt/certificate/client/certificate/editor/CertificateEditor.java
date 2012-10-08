package com.chinarewards.gwt.certificate.client.certificate.editor;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateClient;
import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificatePresenter;
import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class CertificateEditor extends AbstractEditor {

	final CertificatePresenter CertificatePresenter;
	Object model;

	@Inject
	protected CertificateEditor(CertificateEditorDescriptor editorDescriptor,
			CertificatePresenter CertificatePresenter) {
		super(editorDescriptor);
		this.CertificatePresenter = CertificatePresenter;
	}

	@Override
	public Widget asWidget() {
		return CertificatePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		CertificatePresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model) {
		this.model = model;
		CertificateClient CertificateClient = (CertificateClient) model;
		CertificatePresenter
				.initEditor(CertificateClient.getId(), CertificateClient.getThisAction());
		CertificatePresenter.bind();
	}
}
