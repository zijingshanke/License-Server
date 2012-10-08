package com.chinarewards.gwt.license.client.mvp;


public interface ErrorHandler {

	void alert(String message);

	void alert(String message, Throwable e);

}
