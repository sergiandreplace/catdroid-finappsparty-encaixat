package org.catdroid.encaixat.bean;

public class Customer {
	String idCustomer;
	String name;
	String picture;

	public Customer(){
		
	}
	public Customer(String i, String n, String p){
		idCustomer = i;
		name = n;
		picture = p;
	}
	
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
