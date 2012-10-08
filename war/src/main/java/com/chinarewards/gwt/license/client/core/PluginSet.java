package com.chinarewards.gwt.license.client.core;

import java.util.Set;

/**
 * Gin Limitation: Gin does not have a Multi-binder, so need to work-around.
 * 
 * @author kmtong
 * 
 */
public interface PluginSet {
	Set<PluginDescriptor> getPlugins();
}
