package com.chinarewards.gwt.license.client.core.impl;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.ExtensionPoint;
import com.chinarewards.gwt.license.client.core.MenuRoleStore;
import com.chinarewards.gwt.license.client.core.Plugin;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.license.client.support.SessionManager;
import com.google.inject.Inject;

public class CorePluginDescriptor implements PluginDescriptor {

	final static String PLUGIN_ID = "core";

	final CorePlugin p;
	final Set<ExtensionPoint> coreExtensionPoints;

	/**
	 * // XXX remove the MenuInRole
	 * 
	 * @param menuProc
	 * @param sessionManager
	 * @param menuInRole
	 * @param menuRoleStore
	 */
	@Inject
	public CorePluginDescriptor(MenuProcessor menuProc,
			SessionManager sessionManager, MenuRoleStore menuRoleStore) {
		
		// XXX remove the MenuInRole 
		this.p = new CorePlugin(this, menuProc, sessionManager, menuRoleStore);
		coreExtensionPoints = new HashSet<ExtensionPoint>();
		coreExtensionPoints.add(new ExtensionPoint() {

			public String getExtensionId() {
				return PLUGIN_ID + ".menu";
			}

			public PluginDescriptor getPluginDescriptor() {
				return CorePluginDescriptor.this;
			}

		});
		coreExtensionPoints.add(new ExtensionPoint() {

			public String getExtensionId() {
				return PLUGIN_ID + ".editor";
			}

			public PluginDescriptor getPluginDescriptor() {
				return CorePluginDescriptor.this;
			}

		});
	}

	public Set<ExtensionPoint> getExtensionPoints() {
		return coreExtensionPoints;
	}

	public Set<Extension> getExtensions() {
		return null;
	}

	public Plugin getInstance() {
		return p;
	}

	public String getPluginId() {
		return PLUGIN_ID;
	}

}
