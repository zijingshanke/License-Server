/**
 * 
 */
package com.chinarewards.gwt.license.client.user.editor;

import com.chinarewards.gwt.license.client.core.ui.Editor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.license.client.user.plugin.UserConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class UserSearchEditorDescriptor implements EditorDescriptor {

	final Provider<UserSearchEditor> editProvider;

	@Inject
	UserSearchEditorDescriptor(Provider<UserSearchEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return UserConstants.EDITOR_USER_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		UserSearchEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("账号管理");
		e.setModel(model);
		return e;
	}

}
