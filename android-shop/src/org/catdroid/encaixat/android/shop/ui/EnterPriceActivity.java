package org.catdroid.encaixat.android.shop.ui;

import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.listener.CancelClickListener;
import org.catdroid.encaixat.android.shop.listener.NumberClickListener;
import org.catdroid.encaixat.android.shop.listener.OkClickListener;
import org.catdroid.encaixat.android.shop.listener.ResetClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class EnterPriceActivity extends Activity {
	
	public static final String EXTRA_ID_CUSTOMER = "name";
	public static final String EXTRA_AMOUNT = "amount";
	
	private final static String CURRENCY_CHAR = "€";
	public Double amount = 0d;
	
	TextView screen = null;
	
	private String idCustomer;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_quantity);
		
		idCustomer = getIntent().getExtras().getString(EXTRA_ID_CUSTOMER);
		
		screen = (TextView)findViewById(R.id.screenView);
//		screen.set
		
		View button1 = findViewById(R.id.number_1);
		View button2 = findViewById(R.id.number_2);
		View button3 = findViewById(R.id.number_3);
		View button4 = findViewById(R.id.number_4);
		View button5 = findViewById(R.id.number_5);
		View button6 = findViewById(R.id.number_6);
		View button7 = findViewById(R.id.number_7);
		View button8 = findViewById(R.id.number_8);
		View button9 = findViewById(R.id.number_9);
		button1.setOnClickListener(new NumberClickListener(this, 1));
		button2.setOnClickListener(new NumberClickListener(this, 2));
		button3.setOnClickListener(new NumberClickListener(this, 3));
		button4.setOnClickListener(new NumberClickListener(this, 4));
		button5.setOnClickListener(new NumberClickListener(this, 5));
		button6.setOnClickListener(new NumberClickListener(this, 6));
		button7.setOnClickListener(new NumberClickListener(this, 7));
		button8.setOnClickListener(new NumberClickListener(this, 8));
		button9.setOnClickListener(new NumberClickListener(this, 9));
		
		View resetButton = findViewById(R.id.number_c);
		resetButton.setOnClickListener(new ResetClickListener(this));
		
		View cancelButton = findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new CancelClickListener(this));
		View okButton = findViewById(R.id.okButton);
		okButton.setOnClickListener(new OkClickListener(this));
		
		
		reset();
		updateScreen();
	}
	
	public void updateScreen(){
		screen.setText(String.format("%.2f "+CURRENCY_CHAR, amount));
	}
	
	public void reset(){
		amount = 0d;
		updateScreen();
	}
	
	public void cancel(){
		setResult(RESULT_CANCELED);
		finish();
	}
	public void ok(){
		Intent i = new Intent();
		i.putExtra(EXTRA_AMOUNT, amount);
		i.putExtra(EXTRA_ID_CUSTOMER, idCustomer);
		setResult(RESULT_OK, i);
		finish();
	}
}
