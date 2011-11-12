package org.catdroid.encaixat.android.customer.ui;

import org.catdroid.encaixat.android.customer.R;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class PreferencesActivity extends PreferenceActivity {
	
	public static final String idCustomerPreference = "idCustomerPreference";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setPreferenceScreen(createPreferenceHierarchy());
	}
	
	private PreferenceScreen createPreferenceHierarchy() {
		PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);
			
		EditTextPreference idCustomer = new EditTextPreference(this);
		idCustomer.setKey(idCustomerPreference);
		idCustomer.setTitle(R.string.preference_customer_title);
		idCustomer.setSummary(R.string.preference_customer_summary);
		idCustomer.setDefaultValue(0);
		
		root.addPreference(idCustomer);
		
		return root;
	}
    
}
