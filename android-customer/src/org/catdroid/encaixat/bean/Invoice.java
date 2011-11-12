package org.catdroid.encaixat.bean;

public class Invoice {
	public static final boolean WAITING = false;
	public static final boolean FINISHED = true;
	
	String idInvoice;
	String idShop;
	String idCustomer;
	Double quantity;

	boolean status;  
	
	public Invoice(){
		
	}
	public Invoice(String i, String s, String c, Double q){
		idInvoice = i;
		idShop = s;
		idCustomer = c;
		quantity = q;
		this.status = WAITING;
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double q) {
		this.quantity = q;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nidInvoice: " + idInvoice);
		sb.append("\nidShop: " + idShop);
		sb.append("\nidCustomers: " + idCustomer);
		sb.append("\nquantity: " + quantity.toString());
		sb.append("\nstatus: " + status);
		
		return sb.toString();
	}
	
	
}
