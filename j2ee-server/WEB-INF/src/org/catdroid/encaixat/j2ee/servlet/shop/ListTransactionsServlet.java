package org.catdroid.encaixat.j2ee.servlet.shop;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.catdroid.encaixat.bean.Transaction;
import org.catdroid.encaixat.j2ee.bo.SprintBO;
import org.catdroid.encaixat.j2ee.servlet.BaseHttpServlet;

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
		
		ServletOutputStream sos = resp.getOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(sos);
		ObjectOutputStream oos = new ObjectOutputStream(gos);
		oos.writeObject(transactions);
		oos.flush();
		oos.close();
	}

}