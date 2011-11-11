package com.actuanceconsulting.perflog;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.catdroid.encaixat.util.Log;

/** @see http://blog.actuanceconsulting.com/servlet-filter-java-performance-logging/
 * 
 * A simple filter to log the time taken to execute a request. Logging is
 * carried out via log4j to give the flexibility to add other data (such as
 * current time) and format the log as required.
 * */

/*
 * Copyright John Patrick, Actuance Consulting Limited 2010
 * http://www.actuanceconsulting.com
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * For a copy of the GNU Lesser General Public License, see
 * <http://www.gnu.org/licenses/>.
 */
public class PerformanceLogFilter implements Filter {

	/**
	 * An optional regular expression to use as a filter for the servlet patch.
	 * Gives more flexibility than the standard servlet url-filter. All requests
	 * are logged if not specified.
	 * */
	private static final String URL_FILTER_PARAM = "url-filter";

	/**
	 * An optional log4j category to use. The fully qualified class name of the
	 * filter will be used if not specified.
	 */
	private static final String LOG_CATEGORY_PARAM = "log-category";

	private String urlFilter;

	public void init(FilterConfig config) throws ServletException {
		String logCategory = config.getInitParameter(LOG_CATEGORY_PARAM);
		if (logCategory == null) {
			this.getClass().getName();
		}
		urlFilter = config.getInitParameter(URL_FILTER_PARAM);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		long startTime;
		long endTime;
		String path = ((HttpServletRequest) request).getServletPath();

		if (urlFilter == null || path.matches(urlFilter)) {
			startTime = System.currentTimeMillis();
			chain.doFilter(request, response);
			endTime = System.currentTimeMillis();

			// Log the servlet path and time taken
			Log.i(path + "," + (endTime - startTime));
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		// Nothing to see here
	}
}