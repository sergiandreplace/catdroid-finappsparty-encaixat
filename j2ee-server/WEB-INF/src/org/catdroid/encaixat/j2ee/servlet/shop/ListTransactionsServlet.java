package org.catdroid.encaixat.j2ee.servlet.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.catdroid.encaixat.bean.Customer;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Transaction;
import org.catdroid.encaixat.j2ee.bo.SprintBO;
import org.catdroid.encaixat.j2ee.servlet.BaseHttpServlet;

import com.google.gson.Gson;

/** Client first action: "Hi, I'm at your shop!!"
 * 
 * @author Catdroid.org - Roc Boronat
 * @date 11/11/11 20:31
 */
@SuppressWarnings("serial")
public class ListTransactionsServlet extends BaseHttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String idShop = req.getParameter("idShop");
		if (idShop==null){
			throw new ServletException("idShop cannot be null");
		}	
		
		List<Transaction> transactions = null;
		try {
			transactions = SprintBO.listActiveTransactions(idShop);
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		prepareHtmlResponse(resp);
		PrintWriter out = resp.getWriter();
		out.append(new Gson().toJson(transactions));
		out.flush();
		out.close();
	}

}