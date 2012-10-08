package com.chinarewards.gwt.license.client.license.editor;

import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 * @since 2012年1月9日 17:25:09
 */
public class LicenseEditorDescriptor implements EditorDescriptor {

	final Provider<LicenseEditor> editProvider;

	@Inject
	LicenseEditorDescriptor(Provider<LicenseEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return LicenseConstants.EDITOR_LICENSE_EDIT;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		LicenseEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("新建授权证书");
		if (model instanceof LicenseClient) {
			if (model != null)
				e.setTitle(((LicenseClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
