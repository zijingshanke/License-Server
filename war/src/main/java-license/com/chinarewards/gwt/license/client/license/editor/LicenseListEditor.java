package com.chinarewards.gwt.license.client.license.editor;

import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.license.client.license.presenter.LicenseListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class LicenseListEditor extends AbstractEditor {

	final LicenseListPresenter licenseListPresenter;
	Object model;

	@Inject
	protected LicenseListEditor(LicenseListEditorDescriptor editorDescriptor,
			LicenseListPresenter licenseListPresenter) {
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
