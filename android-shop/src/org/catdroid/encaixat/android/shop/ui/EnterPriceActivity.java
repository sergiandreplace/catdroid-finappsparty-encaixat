package org.catdroid.encaixat.android.shop.ui;

import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.listener.NumberClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class EnterPriceActivity extends Activity {
	
	public static final String EXTRA_ID_CUSTOMER = "name";
	public static final String EXTRA_AMOUNT = "amount";
	
	private final static String CURRENCY_CHAR = "€";
	public Double amount = 0d;
	public int multiplicador = 1;
	
	TextView screen = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_quantity);
		
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
		button1.setOnClickListener(new NumberClickListener(this, 1, screen));
		button2.setOnClickListener(new NumberClickListener(this, 2, screen));
		button3.setOnClickListener(new NumberClickListener(this, 3, screen));
		button4.setOnClickListener(new NumberClickListener(this, 4, screen));
		button5.setOnClickListener(new NumberClickListener(this, 5, screen));
		button6.setOnClickListener(new NumberClickListener(this, 6, screen));
		button7.setOnClickListener(new NumberClickListener(this, 7, screen));
		button8.setOnClickListener(new NumberClickListener(this, 8, screen));
		button9.setOnClickListener(new NumberClickListener(this, 9, screen));
		
		updateScreen();
	}
	
	public void updateScreen(){
		screen.setText(String.format("%.2f "+CURRENCY_CHAR, amount));
	}
}
