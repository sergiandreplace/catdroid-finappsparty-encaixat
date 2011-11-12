package org.catdroid.encaixat.android.shop.ui;

import org.catdroid.encaixat.android.shop.Constants;
import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.adapter.TransactionAdapter;
import org.catdroid.encaixat.android.shop.manager.TransactionManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageView buttonRefresh;
	private MainFragment mainFragment;

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
		mainFragment = (MainFragment) this.getSupportFragmentManager().findFragmentById(R.id.MainFragment);
	}

	private void setListeners() {
		buttonRefresh.setOnClickListener(this);

	}

	public void onClick(View v) {
		mainFragment.update();

	}
}