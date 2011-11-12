package org.catdroid.encaixat.android.shop.ui;

import org.catdroid.encaixat.android.shop.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
	  ViewGroup container, Bundle savedInstanceState) {
	return inflater.inflate(R.layout.info_fragment, container,false);
	}
}
