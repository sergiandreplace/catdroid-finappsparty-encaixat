package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;
import org.catdroid.encaixat.android.dao.ServerManager;
import org.catdroid.encaixat.bean.Shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShopActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
		
		// receive shop data from scanner
		Intent shopData = getIntent();
		String idShop = shopData.getExtras().getString("SHOP_ID");
		
		// request shop data from server
		Shop s = null;
		// Metode que ataca a la BBDD. Això és fake
		s = ServerManager.sayHello(this, idShop);
		// Metode que ataca a la BBDD. Això és fake
		
		
		// show recovered data in views
		TextView txtShopId = (TextView) findViewById(R.id.txtShopID);
		txtShopId.setText(s.getName());
				
		TextView txtEncaixada = (TextView) findViewById(R.id.txtEncaixada);
		txtEncaixada.setText(R.id.txtEncaixada);
		
	}



}
