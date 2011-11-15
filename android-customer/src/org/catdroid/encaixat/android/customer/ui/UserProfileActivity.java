package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;
import org.catdroid.encaixat.android.customer.Session;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		TextView txtUserName = (TextView) findViewById(R.id.txtUserName);
		txtUserName.setText(Session.customer.getName());
	}

}
