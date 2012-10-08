package com.chinarewards.gwt.license.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Initialize servlet.
 * 
 * @author yanxin
 * @since 1.0
 */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1703637354573522229L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void init() throws ServletException {
		// this must be invoked.
		super.init();

		logger.info("System initialization started");

		try {
			logger.info("System initialization completed successfully");

		} catch (Throwable t) {
			logger.error(
					"An exception is thrown during the initialization phrase",
					t);
		}

	}

}
