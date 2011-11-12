package org.catdroid.encaixat.android.shop.listener;

import org.catdroid.encaixat.android.shop.ui.EnterPriceActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class OkClickListener implements OnClickListener{
	
	private EnterPriceActivity activity = null;
	
	public OkClickListener(EnterPriceActivity a){
		activity = a;
	}

	public void onClick(View v) {
		activity.ok();
	}
}
