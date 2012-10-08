package com.chinarewards.gwt.license.client.core;

import java.util.List;

public interface PluginManager {

	/**
	 * Initialize the universe plugin set.
	 * 
	 * @param universe
	 */
	void initialize(PluginSet universe);

	/**
	 * Get all plugins in the system. Plugins are defined in
	 * <code>GinPluginSetProvider</code>
	 * 
	 * @return
	 */
	PluginSet getUniverse();

	/**
	 * Activate the Plugin with the given ID.
	 * 
	 * @param pluginId
	 */
	void activatePlugin(String pluginId);

	/**
	 * Get the plugin by ID. The plugin need not be activated.
	 * 
	 * @param pluginId
	 * @return
	 */
	Plugin getPlugin(String pluginId);

	/**
	 * Get the plugin descriptor by ID.
	 * 
	 * @param pluginId
	 * @return
	 */
	PluginDescriptor getPluginDescriptor(String pluginId);

	List<PluginDescriptor> calculateDependencies(PluginDescriptor plugin);

	PluginDescriptor getPluginDescriptorByExtensionPoint(String extensionPointId);

	List<Extension> getExtensions(String extensionPointId);
}
