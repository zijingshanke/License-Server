package com.chinarewards.license.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author cyril
 * @since 0.0.1
 */
public class UserContextProvider {

	static Logger log = LoggerFactory.getLogger(UserContextProvider.class);

	protected static InheritableThreadLocal<LogicContext> logicContextTL = new InheritableThreadLocal<LogicContext>() {
		protected synchronized LogicContext initialValue() {
			return null;
		}
	};

	/**
	 * Obtains the <code>LogicContext</code> at the current environment. This
	 * method never returns <code>null</code>.
	 * 
	 * @return
	 */
	public static LogicContext get() {
		LogicContext ctx = logicContextTL.get();
		log.debug("Getting UserContext: " + ctx);
		return ctx;
	}

}
