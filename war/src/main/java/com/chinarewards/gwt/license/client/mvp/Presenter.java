package com.chinarewards.gwt.license.client.mvp;

public interface Presenter<D extends Display> {

	void bind();

	void unbind();

	D getDisplay();

}