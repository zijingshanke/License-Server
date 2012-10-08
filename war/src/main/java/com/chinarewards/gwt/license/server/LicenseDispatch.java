/**
 * 
 */
package com.chinarewards.gwt.license.server;

import java.util.Date;

import net.customware.gwt.dispatch.server.ActionHandlerRegistry;
import net.customware.gwt.dispatch.server.guice.GuiceDispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import org.slf4j.Logger;

import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * 
 * 
 * @author cyril
 * @since 0.0.1
 */
public class LicenseDispatch extends GuiceDispatch {

	@InjectLogger
	Logger logger;

	@Inject
	public LicenseDispatch(ActionHandlerRegistry handlerRegistry) {
		super(handlerRegistry);
	}

	@Override
	public <A extends Action<R>, R extends Result> R execute(A action)
			throws DispatchException {
		logger.trace("To execute action {}", new Object[] { action });
		
		
		// security?
		// if (!(action instanceof LoginRequest || action instanceof
		// AppLoginEnvRequest)
		// && UserContextProvider.get() == null) {
		// logger.trace("Invalid session");
		// throw new InvalidSessionException();
		// }
		// logging?
		
		// caching?
		Date start = new Date();
		
		R result = super.execute(action);
		
		Date end = new Date();
		
		// in seconds
		double timeElapsed = (double)(end.getTime() - start.getTime()) / 1000;
		
		logger.trace("Time elapsed for action {}: {} seconds", new Object[] { action, Double.toString(timeElapsed) });
		logger.trace("Action {}'s result: {}", new Object[] { action, result });
		
		return result;
	}
	
}
