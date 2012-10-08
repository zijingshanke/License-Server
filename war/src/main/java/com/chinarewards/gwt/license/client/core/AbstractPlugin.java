package com.chinarewards.gwt.license.client.core;

public abstract class AbstractPlugin implements Plugin {

	final PluginDescriptor descriptor;
	boolean activated = false;

	/**
	 * Implement me!
	 */
	protected abstract void onActivate();

	protected AbstractPlugin(PluginDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public PluginDescriptor getPluginDescriptor() {
		return descriptor;
	}

	public String getPluginId() {
		return descriptor.getPluginId();
	}

	public void activate() {
		if (!activated) {
			activated = true;
			onActivate();
		}
	}

	public boolean isActivated() {
		return activated;
	}

	protected Platform getPlatform() {
		return Platform.getInstance();
	}

}
