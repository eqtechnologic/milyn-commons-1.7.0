/*
 Milyn - Copyright (C) 2006 - 2010

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License (version 2.1) as published by the Free Software 
 Foundation.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 
 See the GNU Lesser General Public License for more details:    
 http://www.gnu.org/licenses/lgpl.txt
 */

package org.milyn.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Container Timing Filter. <p/> Simple utility filter for logging
 * request/response timings on a filter chain.
 * 
 * @author tfennelly
 */
public class ServletContainerTimingFilter implements Filter {

	/**
	 * Label.
	 */
	private String label;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jakarta.servlet.Filter#init(jakarta.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		label = config.getInitParameter("label");
		System.out.println("**** ServletFilter [" + config.getFilterName()
				+ "] initialised [" + label + "].");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jakarta.servlet.Filter#doFilter(jakarta.servlet.ServletRequest,
	 *      jakarta.servlet.ServletResponse, jakarta.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		long startTime = 0L;

		startTime = System.currentTimeMillis();
		filterChain.doFilter(request, response);
		if (label != null) {
			System.out.println("**** Request time: "
					+ (System.currentTimeMillis() - startTime) + "ms [" + label
					+ "]");
		} else {
			System.out.println("**** Request time: "
					+ (System.currentTimeMillis() - startTime) + "ms");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jakarta.servlet.Filter#destroy()
	 */
	public void destroy() {
	}
}
