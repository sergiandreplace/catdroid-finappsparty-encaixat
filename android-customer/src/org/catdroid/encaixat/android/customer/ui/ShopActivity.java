package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;

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
		String shopId = shopData.getExtras().getString("SHOP_ID");
		
		// request shop data from server
		
		// show recovered data in views
		TextView txtShopId = (TextView) findViewById(R.id.txtShopID);
		txtShopId.setText(shopId);
		
		TextView txtEncaixada = (TextView) findViewById(R.id.txtEncaixada);
		
	}



}
