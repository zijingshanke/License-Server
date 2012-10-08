package com.chinarewards.gwt.license.client.core;

import com.chinarewards.gwt.license.client.core.ui.EditorRegistry;
import com.chinarewards.gwt.license.client.core.ui.SiteManager;
import com.chinarewards.gwt.license.client.mvp.EventBus;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

/**
 * A central place for all the UI extensions information. What does a Platform
 * have?
 * 
 * <ul>
 * <li>PluginManager: Handles plugin registrations and runtime status</li>
 * <li>SiteManager: the controlling root of UI, which contains toolbar (menu),
 * editor (main area)</li>
 * </ul>
 * 
 * Flows: Get the System Plugin (Initialization). Give the System Plugin the
 * control.
 * 
 * @author kmtong
 * 
 */
public class Platform {

	final PluginManager pluginManager;
	final SiteManager siteManager;
	final EditorRegistry editorRegistry;

	// final PluginDescriptor initPlugin;
	final static String STARTUP_PLUGIN_ID = "core";
	final EventBus eventBus;

	boolean initialized = false;

	/**
	 * This will only be valid after
	 * {@link #initialize(PluginSet, RootLayoutPanel)} is invoked
	 */
	RootLayoutPanel rootLayoutPanel;

	static Platform STATIC_PLATFORM = null;

	@Inject
	Platform(PluginManager pluginManager, SiteManager siteManager,
			EditorRegistry editorRegistry, EventBus eventBus) {
		this.pluginManager = pluginManager;
		this.siteManager = siteManager;
		this.editorRegistry = editorRegistry;
		this.eventBus = eventBus;
	}

	public void initialize(PluginSet pluginSet, RootLayoutPanel rootPanel) {
		if (initialized) {
			throw new RuntimeException("Platform already initialized");
		}
		STATIC_PLATFORM = this;
		GWT.log("Initializing Platform");
		pluginManager.initialize(pluginSet);
		rootLayoutPanel = rootPanel;
		siteManager.initialize(rootPanel);
		editorRegistry.initialize(this);
		pluginManager.activatePlugin(STARTUP_PLUGIN_ID);
		initialized = true;
	}
	public void initializeStaff(PluginSet pluginSet, RootLayoutPanel rootPanel) {
		if (initialized) {
			throw new RuntimeException("Platform already initialized");
		}
		STATIC_PLATFORM = this;
		GWT.log("Initializing Platform");
		pluginManager.initialize(pluginSet);
		rootLayoutPanel = rootPanel;
		siteManager.initializeStaff(rootPanel);
		editorRegistry.initialize(this);
		pluginManager.activatePlugin(STARTUP_PLUGIN_ID);
		initialized = true;
	}

	public PluginManager getPluginManager() {
		return pluginManager;
	}

	public SiteManager getSiteManager() {
		return siteManager;
	}

	public EditorRegistry getEditorRegistry() {
		return editorRegistry;
	}

	public PluginDescriptor getInitPlugin() {
		return getPluginManager().getPluginDescriptor(STARTUP_PLUGIN_ID);
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public RootLayoutPanel getRootLayoutPanel() {
		return rootLayoutPanel;
	}

	public static Platform getInstance() {
		return STATIC_PLATFORM;
	}
}
