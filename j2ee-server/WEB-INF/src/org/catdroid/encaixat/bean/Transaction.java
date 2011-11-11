package org.catdroid.encaixat.bean;

public class Transaction {
	
	Customer customer;
	Invoice invoice;
	Shop shop;
		
	public Transaction(Customer c, Invoice i, Shop s) {
		super();
		this.customer = c;
		this.invoice = i;
		this.shop = s;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}