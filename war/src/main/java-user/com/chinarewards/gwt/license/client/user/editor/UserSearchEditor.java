package com.chinarewards.gwt.license.client.user.editor;

import com.chinarewards.gwt.license.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.license.client.user.presenter.UserSearchPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanxin
 * @since 0.0.1 2010-09-26
 */
public class UserSearchEditor extends AbstractEditor {

	final UserSearchPresenter userPresenter;
	Object model;

	@Inject
	protected UserSearchEditor(UserSearchEditorDescriptor editorDescriptor,
			UserSearchPresenter userPresenter) {
		super(editorDescriptor);
		this.userPresenter = userPresenter;
	}

	@Override
	public Widget asWidget() {
		return userPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		userPresenter.unbind();
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
		userPresenter.bind();
	}
}
