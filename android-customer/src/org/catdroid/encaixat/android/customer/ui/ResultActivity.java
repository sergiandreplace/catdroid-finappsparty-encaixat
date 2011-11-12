package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		Intent data = getIntent();
		String invoiceResult = data.getExtras().getString("MESSAGE");
		
		TextView txtResult = (TextView) findViewById(R.id.txtResult);
		txtResult.setText(invoiceResult);
	}

}
