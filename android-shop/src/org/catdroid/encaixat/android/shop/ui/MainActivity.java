package org.catdroid.encaixat.android.shop.ui;

import org.catdroid.encaixat.android.shop.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
	public static Activity activity= null;
	public static String idShop = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	activity = this;
    	idShop = "0";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}