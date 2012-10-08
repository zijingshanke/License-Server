/**
 * 
 */
package com.chinarewards.gwt.license.server;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

/**
 * Base class for all GWT RPC command pattern action handler
 * 
 * @author cyril
 * @since 0.0.1
 */
public abstract class BaseActionHandler<A extends Action<R>, R extends Result>
		implements ActionHandler<A, R> {
	
}
