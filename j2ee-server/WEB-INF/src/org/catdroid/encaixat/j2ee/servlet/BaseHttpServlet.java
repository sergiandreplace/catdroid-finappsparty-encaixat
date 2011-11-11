package org.catdroid.encaixat.j2ee.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/** Generic class to use JSON Servelts
 * 
 * @author Catdroid.org - Roc Boronat
 * @date 11/11/11 17:48
 */
@SuppressWarnings("serial")
public class BaseHttpServlet extends HttpServlet {
	protected void prepareJsonResponse(HttpServletResponse resp) {
		resp.setContentType("application/json; charset=UTF-8");
	}
	protected void prepareHtmlResponse(HttpServletResponse resp) {
		resp.setContentType("text/plain; charset=UTF-8");
	}	
}
