package org.catdroid.encaixat.android.dao;

import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;

public class ServerManager {
	public static Shop sayHello(String idShop) {
		return new Shop(idShop, "Cansalada Bona");
	}
	
	public static Invoice getInvoices(String idCustomer, String idShop) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Invoice("0", idShop, idCustomer, 0.0);
	}
}
