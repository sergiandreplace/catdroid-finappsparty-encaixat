package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button btnAbout = (Button) findViewById(R.id.btnAbout);

		btnAbout.setOnClickListener(new onClickListener {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(this, AboutActivity.class);
				startActivity(i);
			}
			
		});
	}

	
}
