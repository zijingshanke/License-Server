/**
 * 
 */
package com.chinarewards.gwt.license.server.logger;

import java.lang.reflect.Field;

import org.slf4j.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Google Guice facility for custom injection. This implementation will 
 * scan through a type's fields, looking for SLF4J loggers. For each logger
 * encountered, we register a <code>Slf4jMembersInjector</code>. Only logger
 * annotated with <code>@InjectLogger</code> is injected.
 * <p>
 * 
 * See http://code.google.com/p/google-guice/wiki/CustomInjections
 * 
 * @author cyril
 * @since 0.0.1
 */
public class Slf4jTypeListener implements TypeListener {

	public <T> void hear(TypeLiteral<T> typeLiteral,
			TypeEncounter<T> typeEncounter) {
		for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
			if (field.getType() == Logger.class
					&& field.isAnnotationPresent(InjectLogger.class)) {
				typeEncounter.register(new Slf4jMembersInjector<T>(field));
			}
		}
	}

}
