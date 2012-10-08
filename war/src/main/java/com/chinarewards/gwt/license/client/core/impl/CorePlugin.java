package com.chinarewards.gwt.license.client.core.impl;

import java.util.List;

import com.chinarewards.gwt.license.client.core.AbstractPlugin;
import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.MenuRoleStore;
import com.chinarewards.gwt.license.client.core.Platform;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.license.client.core.ui.MenuItem;
import com.chinarewards.gwt.license.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.license.client.license.plugin.LicenseConstants;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.google.gwt.core.client.GWT;

public class CorePlugin extends AbstractPlugin {

	final MenuProcessor menuProcessor;
	boolean activated = false;

	final SessionManager sessionManager;
	final MenuRoleStore menuRoleStore;

	CorePlugin(PluginDescriptor pluginDesc, MenuProcessor menuProc,
			SessionManager sessionManager, MenuRoleStore menuRoleStore) {
		super(pluginDesc);
		this.menuProcessor = menuProc;
		this.sessionManager = sessionManager;
		this.menuRoleStore = menuRoleStore;
	}

	protected void onActivate() {
		GWT.log("Starting Core...");
		processMenus(getPlatform());
		processEditors(getPlatform());
	}

	private void processMenus(Platform platform) {
		List<Extension> exts = platform.getPluginManager().getExtensions(
				"core.menu");
		// UserRoleVo userRoles[] = sessionManager.getSession().getUserRoles();
		for (Extension e : exts) {
			MenuItem item = (MenuItem) e.getInstance();
			// if (!GWT.isScript()) {
			menuProcessor.add(item);
			continue;
			// }
			// for (UserRoleVo userRole : userRoles) {
			// if (menuRoleStore.isMenuVisibleToRole(item.getMenuId(),
			// userRole.name())) {
			// menuProcessor.add(item);
			// }
			// }
		}
		// --- render menu just set ---
		// set the actual container
		platform.getSiteManager().setMenuProcessor(menuProcessor);
		// menuProcessor.render(platform.getSiteManager().getMenuArea());
	}

	private void processEditors(Platform platform) {
		List<Extension> exts = platform.getPluginManager().getExtensions(
				"core.editor");
		for (Extension e : exts) {
			EditorDescriptor item = (EditorDescriptor) e.getInstance();
			platform.getEditorRegistry().registerEditor(item);
		}

		Platform.getInstance()
				.getEditorRegistry()
				.openEditor(LicenseConstants.EDITOR_LICENSE_LIST,
						"EDITOR_LICENSE_LIST_DO_ID", null);

	}
}
