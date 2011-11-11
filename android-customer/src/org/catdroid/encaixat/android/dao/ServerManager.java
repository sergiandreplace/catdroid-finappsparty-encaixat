package org.catdroid.encaixat.android.dao;

import org.catdroid.encaixat.bean.Shop;

public class ServerManager {
	public static Shop sayHello(String idShop){
		return new Shop(idShop, "Cansalada Bona");
	}
}
