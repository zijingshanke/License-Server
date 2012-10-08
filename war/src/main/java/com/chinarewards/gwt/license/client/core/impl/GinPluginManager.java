package com.chinarewards.gwt.license.client.core.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.chinarewards.gwt.license.client.core.Extension;
import com.chinarewards.gwt.license.client.core.ExtensionPoint;
import com.chinarewards.gwt.license.client.core.Plugin;
import com.chinarewards.gwt.license.client.core.PluginDescriptor;
import com.chinarewards.gwt.license.client.core.PluginManager;
import com.chinarewards.gwt.license.client.core.PluginSet;
import com.google.gwt.core.client.GWT;

/**
 * Controlling the lifecycle of Plugins
 * 
 * @author kmtong
 * 
 */
public class GinPluginManager implements PluginManager {

	PluginSet universe;

	Map<String, ExtensionPoint> extensionPoints = new TreeMap<String, ExtensionPoint>();
	Map<String, List<Extension>> extensions = new TreeMap<String, List<Extension>>();

	public void initialize(PluginSet universe) {
		this.universe = universe;
		for (PluginDescriptor p : universe.getPlugins()) {
			// register extension points
			Set<ExtensionPoint> ex = p.getExtensionPoints();
			if (ex != null) {
				for (ExtensionPoint e : ex) {
					extensionPoints.put(e.getExtensionId(), e);
				}
			}
			// register extensions
			registerExtensions(p.getExtensions());
		}
	}

	public Plugin getPlugin(String pluginId) {
		return getPluginDescriptor(pluginId).getInstance();
	}

	public PluginSet getUniverse() {
		return universe;
	}

	public PluginDescriptor getPluginDescriptor(String pluginId) {
		for (PluginDescriptor d : universe.getPlugins()) {
			if (d.getPluginId().equals(pluginId)) {
				return d;
			}
		}
		return null;
	}

	public void activatePlugin(String pluginId) {
		GWT.log("Activating Plugin: " + pluginId);
		PluginDescriptor d = getPluginDescriptor(pluginId);
		if (d != null) {
			// make sure that the depending plugin is activated
			List<PluginDescriptor> ds = calculateDependencies(d);
			for (PluginDescriptor p : ds) {
				if (!p.getInstance().isActivated()) {
					// recursively activate the depending plugins
					activatePlugin(p.getPluginId());
				}
			}
			// activate myself
			d.getInstance().activate();
		}
	}

	public List<PluginDescriptor> calculateDependencies(PluginDescriptor plugin) {
		List<PluginDescriptor> res = new LinkedList<PluginDescriptor>();
		Set<Extension> es = plugin.getExtensions();
		if (es != null) {
			for (Extension e : es) {
				PluginDescriptor pd = getPluginDescriptorByExtensionPoint(e
						.getExtensionPointId());
				res.add(pd);
			}
		}
		return res;
	}

	public PluginDescriptor getPluginDescriptorByExtensionPoint(
			String extensionPointId) {
		if (extensionPoints.containsKey(extensionPointId)) {
			return extensionPoints.get(extensionPointId).getPluginDescriptor();
		}
		return null;
	}

	protected void registerExtensions(Set<Extension> exts) {
		if (exts != null) {
			for (Extension e : exts) {
				e.getPluginDescriptor();
				List<Extension> le = null;
				if (!extensions.containsKey(e.getExtensionPointId())) {
					le = new LinkedList<Extension>();
					extensions.put(e.getExtensionPointId(), le);
				}
				le = extensions.get(e.getExtensionPointId());
				le.add(e);
			}
		}
	}

	public List<Extension> getExtensions(String extensionPointId) {
		List<Extension> e = extensions.get(extensionPointId);
		if (e != null) {
			// make sure every extensions plugin is activated before returning
			for (Extension ex : e) {
				if (!ex.getPluginDescriptor().getInstance().isActivated()) {
					activatePlugin(ex.getPluginDescriptor().getPluginId());
				}
			}
			return e;
		}
		return new LinkedList<Extension>();
	}


}
