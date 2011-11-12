package org.catdroid.encaixat.android.shop.listener;

import org.catdroid.encaixat.android.shop.ui.EnterPriceActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NumberClickListener implements OnClickListener{
	
	private EnterPriceActivity activity = null;
	private Integer value = null;
	private TextView text = null;
	
	public NumberClickListener(EnterPriceActivity a, Integer v, TextView t){
		activity = a;
		value = v;
		text = t;
	}

	public void onClick(View v) {
		activity.amount += value*activity.multiplicador;
		activity.multiplicador*=10;
		activity.updateScreen();
	}
}
