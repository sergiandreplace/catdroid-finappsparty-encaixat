package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;
import org.catdroid.encaixat.android.customer.Session;
import org.catdroid.encaixat.android.customer.util.InvoiceRequestTask;
import org.catdroid.encaixat.android.dao.ServerManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShopActivity extends Activity {

	public InvoiceRequestTask task; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
		
		// receive shop data from scanner
		Intent shopData = getIntent();
		String idShop = shopData.getExtras().getString("SHOP_ID");
		
		// request shop data from server
		// Metode que ataca a la BBDD. Això és fake
		Session.shop = ServerManager.sayHello(this, idShop, Session.customer.getIdCustomer()).getShop();
		// Metode que ataca a la BBDD. Això és fake
		
		
		// show recovered data in views
		TextView txtShopId = (TextView) findViewById(R.id.txtShopID);
		txtShopId.setText(Session.shop.getName());
				
//		TextView txtEncaixada = (TextView) findViewById(R.id.txtEncaixada);
//		txtEncaixada.setText(R.id.txtEncaixada);
		
		// start polling for incoming invoice
		task = new InvoiceRequestTask(this);
		task.execute();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// cancel the running async task
		task.cancel(true);
		super.onBackPressed();
	}



}
