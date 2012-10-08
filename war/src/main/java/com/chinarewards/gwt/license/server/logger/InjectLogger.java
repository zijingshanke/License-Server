/**
 * 
 */
package com.chinarewards.gwt.license.server.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates an instance of logger should be injected to the specified
 * parameter, field, or getter/setter.
 * 
 * @author cyril
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface InjectLogger {
}
