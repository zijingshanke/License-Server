package com.chinarewards.gwt.certificate.client.certificate.editor;

import com.chinarewards.gwt.certificate.client.certificate.presenter.CertificateListPresenter;
import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class CertificateListEditor extends AbstractEditor {

	final CertificateListPresenter licenseListPresenter;
	Object model;

	@Inject
	protected CertificateListEditor(CertificateListEditorDescriptor editorDescriptor,
			CertificateListPresenter licenseListPresenter) {
		super(editorDescriptor);
		this.licenseListPresenter = licenseListPresenter;
	}

	@Override
	public Widget asWidget() {
		return licenseListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		licenseListPresenter.unbind();
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
		licenseListPresenter.bind();
	}
}
