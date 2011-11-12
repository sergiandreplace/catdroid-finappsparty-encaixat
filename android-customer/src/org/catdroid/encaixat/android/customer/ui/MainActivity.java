package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;
import org.catdroid.encaixat.android.customer.Session;
import org.catdroid.encaixat.bean.Customer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initSession();
		
		Button btnCheckinProviders = (Button) findViewById(R.id.btnCheckin);
		Button btnProfile = (Button) findViewById(R.id.btnProfile);
		Button btnAbout = (Button) findViewById(R.id.btnAbout);
		
		LinearLayout l3 = (LinearLayout) findViewById(R.id.linearLayout3);
		l3.setOnClickListener(this);
		LinearLayout l4 = (LinearLayout) findViewById(R.id.linearLayout4);
		l4.setOnClickListener(this);
		LinearLayout l5 = (LinearLayout) findViewById(R.id.linearLayout5);
		l5.setOnClickListener(this);

		btnCheckinProviders.setOnClickListener(this);
		btnProfile.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
	}

	public void initSession() {
		// get initial data
		Session.customer = new Customer("0", "Roc Boronat", "http://es.wikipedia.org/wiki/Archivo:Bananas_white_background.jpg");
		}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linearLayout3:
			// Show dialog list with checkin providers
			final CharSequence[] CheckinItems = {"Codi QR", "Foursquare", "Google Places", "Facebook Places"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getString(R.string.how_to_check_in));
			// React on list selection
			builder.setItems(CheckinItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if (CheckinItems[item] == "Codi QR") {
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			        startActivityForResult(intent, 0);
				}
			}
			});
			AlertDialog alert = builder.create();
			alert.show();
			break;
		case R.id.linearLayout4:
			// Show profile info
			Intent i = new Intent(this, UserProfileActivity.class);
			startActivity(i);
			break;
		case R.id.linearLayout5:
			// Show about screen
			Intent j = new Intent(this, AboutActivity.class);
			startActivity(j);
			break;
		}
//		if (v.getId() == R.id.btnCheckin) {
//			// Show dialog list with checkin providers
//			final CharSequence[] CheckinItems = {"Codi QR", "Foursquare", "Google Places", "Facebook Places"};
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle(getString(R.string.how_to_check_in));
//			// React on list selection
//			builder.setItems(CheckinItems, new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int item) {
//				if (CheckinItems[item] == "Codi QR") {
//					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//			        startActivityForResult(intent, 0);
//				}
//			}
//			});
//			AlertDialog alert = builder.create();
//			alert.show();
//		}
//		if (v.getId() == R.id.btnProfile) {
//			// Show profile info
//			Intent i = new Intent(this, UserProfileActivity.class);
//			startActivity(i);
//		}
//		if (v.getId() == R.id.btnAbout) {
//			// Show about screen
//			Intent i = new Intent(this, AboutActivity.class);
//			startActivity(i);
//		}

	}

	
	
	  @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		  // handle the scanned ID_Shop code
		  if (requestCode == 0) {
		        if (resultCode == RESULT_OK) {
		            String shopId = intent.getStringExtra("SCAN_RESULT");
		            // Handle successful scan
		            // shopId has the scanned Shop_ID value
//		  		  Toast.makeText(getBaseContext(), shopId, Toast.LENGTH_SHORT).show();
		  		  // Show shop info activity
					Intent i = new Intent(this, ShopActivity.class);
					i.putExtra("SHOP_ID", shopId);
					startActivity(i);
		  		  
		        } else if (resultCode == RESULT_CANCELED) {
		            // Handle cancel
		        }
		    }
	  }
}
