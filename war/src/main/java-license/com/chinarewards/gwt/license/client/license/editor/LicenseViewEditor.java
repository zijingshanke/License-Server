package com.chinarewards.gwt.license.client.license.editor;

import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.presenter.LicenseViewPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class LicenseViewEditor extends AbstractEditor {

	final LicenseViewPresenter licenseViewPresenter;
	Object model;

	@Inject
	protected LicenseViewEditor(LicenseViewEditorDescriptor editorDescriptor,
			LicenseViewPresenter licenseViewPresenter) {
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
			licenseViewPresenter.initInstanceId(instanceId, (LicenseClient) model);

		}
	}
}
