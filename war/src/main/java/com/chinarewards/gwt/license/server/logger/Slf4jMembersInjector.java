/**
 * 
 */
package com.chinarewards.gwt.license.server.logger;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.MembersInjector;

/**
 * Google Guice facility. Finally, we implement the Slf4jMembersInjector to set
 * the logger. In this example, we always set the field to the same instance. In
 * your application you may need to compute a value or request one from a
 * provider.
 * 
 * @author cyril
 * @since 0.0.1
 */
public class Slf4jMembersInjector<T> implements MembersInjector<T> {

	private final Field field;

	Slf4jMembersInjector(Field field) {
		this.field = field;
		field.setAccessible(true);
	}

	@Override
	public void injectMembers(T t) {
		Logger logger = LoggerFactory.getLogger(field.getDeclaringClass());
		try {
			field.set(t, logger);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
