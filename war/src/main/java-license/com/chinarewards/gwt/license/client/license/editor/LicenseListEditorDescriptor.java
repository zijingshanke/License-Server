
package com.chinarewards.gwt.license.client.license.editor;

import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.license.client.license.model.LicenseClient;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 */
public class LicenseListEditorDescriptor implements EditorDescriptor {

	final Provider<LicenseListEditor> editProvider;

	@Inject
	LicenseListEditorDescriptor(Provider<LicenseListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return LicenseConstants.EDITOR_LICENSE_LIST;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		LicenseListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("授权证书列表");
		if (model instanceof LicenseClient) {
			if (model != null)
				e.setTitle(((LicenseClient) model).getTitleName());
		}
		e.setModel(model);
		return e;
	}

}
