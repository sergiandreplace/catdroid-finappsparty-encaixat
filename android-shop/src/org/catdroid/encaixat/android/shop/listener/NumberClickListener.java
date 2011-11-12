package org.catdroid.encaixat.android.shop.listener;

import org.catdroid.encaixat.android.shop.ui.EnterPriceActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NumberClickListener implements OnClickListener{
	
	private EnterPriceActivity activity = null;
	private Integer value = null;
	
	public NumberClickListener(EnterPriceActivity a, Integer v){
		activity = a;
		value = v;
	}

	public void onClick(View v) {
		if (activity.amount<999999999){
			activity.amount = activity.amount*10;
			activity.amount += value*0.01;
			activity.updateScreen();
		}
	}
}
