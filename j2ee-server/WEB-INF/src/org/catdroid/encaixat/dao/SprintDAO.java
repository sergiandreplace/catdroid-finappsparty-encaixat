package org.catdroid.encaixat.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.catdroid.encaixat.bean.Customer;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;
import org.catdroid.encaixat.bean.Transaction;

public class SprintDAO {
	private static Map<String, Invoice> invoices = new HashMap<String, Invoice>();
	private static Map<String, Customer> customers = new HashMap<String, Customer>();
	private static Map<String, Shop> shops = new HashMap<String, Shop>();
	
	private static List<Transaction> transactions = new ArrayList<Transaction>();
	
	public static Invoice addInvoice(String idShop, String idCustomer){
		String size = ""+invoices.size();
		Invoice i = new Invoice(size, idShop, idCustomer);
		invoices.put(size, i);
		return i;
	}
	public static Customer addCustomer(String name, String picture){
		String size = ""+customers.size();
		Customer c = new Customer(size, name, picture);
		customers.put(size, c);
		return c;
	}
	public static Shop addShop(String name){
		String size = ""+shops.size();
		Shop s = new Shop(size, name);
		shops.put(size, s);
		return s;
	}
	public static Transaction addTransaction(Customer c, Invoice i, Shop s){
		Transaction t = new Transaction(c, i, s);
		transactions.add(t);
		return t;
	}
	public static Invoice getInvoice(String idInvoice){
		return invoices.get(idInvoice);
	}
	public static Customer getCustomer(String idCustomer){
		return customers.get(idCustomer);
	}
	public static Shop getShop(String idShop){
		return shops.get(idShop);
	}
	public static Transaction getTransaction(Customer c, Invoice i, Shop s){
		for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
			Transaction t = iterator.next();
			if (t.getInvoice().getIdInvoice().equals(i.getIdInvoice()) &&
				t.getCustomer().getIdCustomer().equals(c.getIdCustomer()) &&
				t.getShop().getIdShop().equals(s.getIdShop())
			){
				return t;
			}
		}
		return null;
	}
	public static List<Customer> listActiveCustomers(Shop s){
		List<Customer> customers = new ArrayList<Customer>();
		for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
			Transaction t = iterator.next();
			if (t.getShop().getIdShop().equals(s.getIdShop())){
				customers.add(t.getCustomer());
			}
		}
		return customers;
	}
}
