package com.chinarewards.gwt.license.client.core;

/**
 * Class implementing this interface should be a concrete class of what the
 * extensionPoint expects.
 * 
 * @author kmtong
 * 
 */
public interface Extension {

	String getExtensionPointId();

	Object getInstance();

	PluginDescriptor getPluginDescriptor();

}
