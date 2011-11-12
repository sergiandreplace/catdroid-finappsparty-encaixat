package org.catdroid.encaixat.android.shop.ui;

import org.catdroid.encaixat.android.shop.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageView buttonRefresh;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		getViews();
		setListeners();

	}

	private void getViews() {
		buttonRefresh = (ImageView) findViewById(R.id.ButtonRefresh);
	}

	private void setListeners() {
		buttonRefresh.setOnClickListener(this);
		

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}