//package com.chinarewards.license.service.common;
//
//import com.chinarewards.license.guice.CoreModule;
//import com.google.inject.Guice;
//import com.google.inject.Inject;
//import com.google.inject.Injector;
//import com.google.inject.Provider;
//
//public class TestInjectorProvider implements Provider<Injector> {
//
//	Injector injector;
//
//	@Inject
//	public TestInjectorProvider() {
//		injector = Guice.createInjector(new CoreModule());
//	}
//
//	public Injector get() {
//		return injector;
//	}
//}
