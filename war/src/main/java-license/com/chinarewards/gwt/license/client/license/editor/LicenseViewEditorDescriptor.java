
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
public class LicenseViewEditorDescriptor implements EditorDescriptor {

	final Provider<LicenseViewEditor> editProvider;

	@Inject
	LicenseViewEditorDescriptor(Provider<LicenseViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return LicenseConstants.EDITOR_LICENSE_VIEW;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		LicenseViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		
		String name = ((LicenseClient) model).getCorporationName();
		String subName = name.length() > 6 ? name.substring(0, 6) : name;
		String title =subName+ "-详细" ;
		e.setTitle(title);
	
		e.setModel(instanceId,model);
		return e;
	}

}
