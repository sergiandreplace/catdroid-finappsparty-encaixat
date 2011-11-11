package org.catdroid.encaixat.bean;

public class Shop {
	String idShop;
	String name;
	
	public Shop(){
		
	}
	public Shop(String i, String n){
		idShop = i;
		name = n;
	}
	
	public String getIdShop() {
		return idShop;
	}
	public void setIdShop(String idShop) {
		this.idShop = idShop;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
