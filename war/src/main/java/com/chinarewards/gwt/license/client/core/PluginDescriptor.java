package com.chinarewards.gwt.license.client.core;

import java.util.Set;

/**
 * Describe the Plugin: The platform will use this to control the activation of
 * the plugin.
 * 
 * @author kmtong
 * 
 */
public interface PluginDescriptor {

	String getPluginId();

	Plugin getInstance();

	/**
	 * Points which allow further extensions by other plugins
	 * 
	 * @return
	 */
	Set<ExtensionPoint> getExtensionPoints();

	/**
	 * Extension to other PluginDescriptor's ExtensionPoint
	 * 
	 * @return
	 */
	Set<Extension> getExtensions();
}
