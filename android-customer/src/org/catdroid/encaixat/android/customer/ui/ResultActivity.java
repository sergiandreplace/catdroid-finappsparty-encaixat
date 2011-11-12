package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;
import org.catdroid.encaixat.android.customer.Session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		Intent data = getIntent();
		int invoiceResult = data.getExtras().getInt("MESSAGE");
		TextView txtResult = (TextView) findViewById(R.id.txtResult);
		switch (invoiceResult) {
		case 0:
			txtResult.setText(R.string.invoiceReceivedOK);
			break;
		case 1:
			txtResult.setText(R.string.invoiceReceivedCancelled);
			break;
		}
		
		TextView txtShopName = (TextView) findViewById(R.id.txtShopName);
		TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
		String amount = String.format("%.2f €", Session.invoice.getQuantity());
		txtAmount.setText(amount);	
		txtShopName.setText(Session.shop.getName());
		
		Button btnNewInvoice = (Button) findViewById(R.id.btnNewInvoice);
		Button btnExit = (Button) findViewById(R.id.btnExit);
		
		btnNewInvoice.setOnClickListener(this);
		btnExit.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btnNewInvoice:
			Intent i = new Intent(this, ShopActivity.class);
			i.putExtra("SHOP_ID", Session.shop.getIdShop());
			startActivity(i);
			this.finish();
			break;
		case R.id.btnExit:
			this.finish();
			break;
		}
	}

}
