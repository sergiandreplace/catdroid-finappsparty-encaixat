package org.catdroid.encaixat.j2ee.servlet.customer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.catdroid.encaixat.bean.Transaction;
import org.catdroid.encaixat.j2ee.bo.SprintBO;
import org.catdroid.encaixat.j2ee.servlet.BaseHttpServlet;
import org.catdroid.encaixat.util.Log;

import com.google.gson.Gson;

/** Customer first action: "Hi, I'm at your shop!!"
 * 
 * @author Catdroid.org - Roc Boronat
 * @date 11/11/11 17:48
 */
@SuppressWarnings("serial")
public class SayHelloServlet extends BaseHttpServlet {

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
		
		Transaction t = null;
		try {
			if (SprintBO.getWaitingTransaction(idCustomer, idShop)==null){
				t = SprintBO.sayHello(idCustomer, idShop);
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		prepareJsonResponse(resp);
		PrintWriter out = resp.getWriter();
		out.append(new Gson().toJson(t));
		out.flush();
		out.close();
	}

}