package org.catdroid.encaixat.android.shop.ui;

import java.util.ArrayList;

import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.adapter.TransactionAdapter;
import org.catdroid.encaixat.android.shop.manager.TransactionManager;
import org.catdroid.encaixat.android.shop.view.HorizontalListView;
import org.catdroid.encaixat.bean.Transaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
	private View me;
	private HorizontalListView helloHorizontalListView;
	private ArrayList<Transaction> helloTransactions;
	private TransactionAdapter helloAdapter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		me=inflater.inflate(R.layout.main_fragment, container, false);
		
		getViews();
		setListeners();
		getData();
		setData();
		return me;
	}
	
	private void getViews() {
		helloHorizontalListView=(HorizontalListView) me.findViewById(R.id.HelloList);
	}
	
	private void setListeners() {
		
	}
	
	private void getData() {
		
		TransactionManager.updateTransactions();
		helloTransactions=TransactionManager.getStatusTransactions(TransactionManager.STATUS_HELLO);
	}
	
	private void setData() {
		helloAdapter=new TransactionAdapter(getActivity(), helloTransactions);
		helloHorizontalListView.setAdapter(helloAdapter);
	}
}
