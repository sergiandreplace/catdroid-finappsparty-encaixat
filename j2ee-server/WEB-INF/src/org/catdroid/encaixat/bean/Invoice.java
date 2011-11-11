package org.catdroid.encaixat.bean;

public class Invoice {
	String idInvoice;
	String idShop;
	String idCustomer;
	
	public Invoice(String i, String s, String c){
		idInvoice = i;
		idShop = s;
		idCustomer = c;
	}
	
	public String getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(String idInvoice) {
		this.idInvoice = idInvoice;
	}
	public String getIdShop() {
		return idShop;
	}
	public void setIdShop(String idShop) {
		this.idShop = idShop;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
}
