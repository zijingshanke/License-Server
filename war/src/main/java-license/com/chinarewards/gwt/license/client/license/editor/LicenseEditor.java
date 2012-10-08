package com.chinarewards.gwt.license.client.license.editor;

import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.presenter.LicensePresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class LicenseEditor extends AbstractEditor {

	final LicensePresenter licensePresenter;
	Object model;

	@Inject
	protected LicenseEditor(LicenseEditorDescriptor editorDescriptor,
			LicensePresenter licensePresenter) {
		super(editorDescriptor);
		this.licensePresenter = licensePresenter;
	}

	@Override
	public Widget asWidget() {
		return licensePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		licensePresenter.unbind();
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
		LicenseClient licenseClient = (LicenseClient) model;
		licensePresenter
				.initEditor(licenseClient.getId(), licenseClient.getThisAction());
		licensePresenter.bind();
	}
}
