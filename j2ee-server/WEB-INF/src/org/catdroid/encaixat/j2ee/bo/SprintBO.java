package org.catdroid.encaixat.j2ee.bo;

import java.util.List;

import org.catdroid.encaixat.bean.Customer;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;
import org.catdroid.encaixat.bean.Transaction;
import org.catdroid.encaixat.dao.SprintDAO;

public class SprintBO {
	public static Transaction sayHello(String idCustomer, String idShop) throws Exception{
		Customer c = SprintDAO.getCustomer(idCustomer);
		Shop s = SprintDAO.getShop(idShop);
		
		if (c==null){
			throw new Exception("The Client is null");
		}
		if (s==null){
			throw new Exception("The Shop is null");
		}
		
		return SprintDAO.addTransaction(c, null, s);
	}
	
	public static Invoice getInvoice(String idCustomer, String idShop) throws Exception{
		Customer c = SprintDAO.getCustomer(idCustomer);
		Shop s = SprintDAO.getShop(idShop);
		
		if (c==null){
			throw new Exception("The Client is null");
		}
		if (s==null){
			throw new Exception("The Shop is null");
		}
		
		return SprintDAO.getInvoice(c, s);
	}
	
	public static List<Transaction> listActiveTransactions(String idShop) throws Exception{
		Shop s = SprintDAO.getShop(idShop);
		
		if (s==null){
			throw new Exception("The Shop is null");
		}
		
		return SprintDAO.listActiveTransactions(s);
	}
	
	public static Invoice payInvoice(String idCustomer, String idShop, String idInvoice) throws Exception{
		Customer c = SprintDAO.getCustomer(idCustomer);
		Shop s = SprintDAO.getShop(idShop);
		Invoice i = SprintDAO.getInvoice(idInvoice);
		
		if (c==null){
			throw new Exception("The Client is null");
		}
		if (s==null){
			throw new Exception("The Shop is null");
		}
		if (i==null){
			throw new Exception("The Invoice is null");
		}
		
		Transaction t = SprintDAO.getTransaction(c, i, s);
		if (!i.getIdInvoice().equals(t.getInvoice().getIdInvoice())){
			throw new Exception("Coherence security error. Talk with your system Admin... if you aren't it ;)");
		}
		
		return SprintDAO.payInvoice(i);
	}

	public static Invoice sendInvoice(String idCustomer, String idShop,	Double quantity) throws Exception {
		Customer c = SprintDAO.getCustomer(idCustomer);
		Shop s = SprintDAO.getShop(idShop);
		
		if (c==null){
			throw new Exception("The Client is null");
		}
		if (s==null){
			throw new Exception("The Shop is null");
		}
		
		Invoice i = SprintDAO.addInvoice(idShop, idCustomer, quantity);
		Transaction t = SprintDAO.getTransaction(c, s);
		t.setInvoice(i);
		
		return i;
	}
}
