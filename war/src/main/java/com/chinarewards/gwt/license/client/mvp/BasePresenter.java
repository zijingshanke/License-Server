package com.chinarewards.gwt.license.client.mvp;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;

public abstract class BasePresenter<D extends Display> implements Presenter<D> {

	protected final EventBus eventBus;
	protected final D display;
	protected final List<HandlerRegistration> handlerRegistrations;

	public BasePresenter(final EventBus eventBus, final D display) {

		this.eventBus = eventBus;
		this.display = display;

		handlerRegistrations = new ArrayList<HandlerRegistration>();

	}

	public void bind() {
	}

	public void unbind() {

		for (HandlerRegistration reg : handlerRegistrations) {
			reg.removeHandler();
		}
		handlerRegistrations.clear();

	}

	public D getDisplay() {
		return this.display;
	}

	protected void registerHandler(HandlerRegistration handlerRegistration) {
		handlerRegistrations.add(handlerRegistration);
	}

	/**
	 * @param departmentId
	 */
	public void initEditor(String departmentId) {
		// TODO Auto-generated method stub
		
	}
}