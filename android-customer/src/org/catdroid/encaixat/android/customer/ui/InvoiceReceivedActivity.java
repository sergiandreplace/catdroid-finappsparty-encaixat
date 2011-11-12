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
import android.widget.Toast;

public class InvoiceReceivedActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invoicereceived);
		
		Button btnOK = (Button) findViewById(R.id.btnOK);
		Button btnCancel = (Button) findViewById(R.id.btnCancel);

		btnOK.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		
		// get invoice data
		Intent data = getIntent();
		String invoiceID = data.getExtras().getString("INVOICE_ID");
		// check that the invoice from the intent is same as the one in Session
		if (invoiceID.equals(Session.invoice.getIdInvoice())) {
			TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
			String amount = String.format("%.2f €", Session.invoice.getQuantity());
			txtAmount.setText(amount);	
		} else {
			// something weird happened
			Toast.makeText(this, "Something weird happened...", Toast.LENGTH_SHORT);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btnOK ) {
			// accept payment
		}
		if (v.getId() == R.id.btnCancel ) {
			// cancel payment
		}
	}

}
