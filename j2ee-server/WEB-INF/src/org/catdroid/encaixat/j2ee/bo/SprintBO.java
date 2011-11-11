package org.catdroid.encaixat.j2ee.bo;

import org.catdroid.encaixat.bean.Customer;
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
}
