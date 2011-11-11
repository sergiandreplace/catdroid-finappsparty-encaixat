package org.catdroid.encaixat.j2ee.servlet.customer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Transaction;
import org.catdroid.encaixat.j2ee.bo.SprintBO;
import org.catdroid.encaixat.j2ee.servlet.BaseHttpServlet;
import org.catdroid.encaixat.util.Log;

import com.google.gson.Gson;

/** Customer second action: "Hey, what I have to pay today?"
 * 
 * @author Catdroid.org - Roc Boronat
 * @date 11/11/11 19:40
 */
@SuppressWarnings("serial")
public class GetInvoiceServlet extends BaseHttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String idCustomer = req.getParameter("idCustomer");
		String idShop = req.getParameter("idShop");
		
		if (idCustomer==null){
			throw new ServletException("idCustomer cannot be null");
		}
		if (idShop==null){
			throw new ServletException("idShop cannot be null");
		}		
		
		Invoice i = null;
		try {
			i = SprintBO.getInvoice(idCustomer, idShop);
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		prepareHtmlResponse(resp);
		PrintWriter out = resp.getWriter();
		out.append(new Gson().toJson(i));
		out.flush();
		out.close();
	}

}