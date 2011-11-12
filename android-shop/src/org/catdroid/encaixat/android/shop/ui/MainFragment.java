package org.catdroid.encaixat.android.shop.ui;

import java.util.ArrayList;

import org.catdroid.encaixat.android.shop.Constants;
import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.adapter.TransactionAdapter;
import org.catdroid.encaixat.android.shop.manager.TransactionManager;
import org.catdroid.encaixat.android.shop.view.HorizontalListView;
import org.catdroid.encaixat.bean.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainFragment extends Fragment implements OnItemClickListener {
	private View me;
	private HorizontalListView helloList;
	private HorizontalListView waitingList;
	private HorizontalListView finishedList;

	private ArrayList<Transaction> helloTransactions;
	private ArrayList<Transaction> waitingTransactions;
	private ArrayList<Transaction> finishedTransactions;

	private TransactionAdapter helloAdapter;
	private TransactionAdapter finishedAdapter;
	private TransactionAdapter waitingAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		me = inflater.inflate(R.layout.main_fragment, container, false);

		getViews();
		setListeners();
		getData();
		setData();
		update();
		return me;
	}

	private void getViews() {
		helloList = (HorizontalListView) me.findViewById(R.id.HelloList);
		waitingList = (HorizontalListView) me.findViewById(R.id.HelloList);
		finishedList = (HorizontalListView) me.findViewById(R.id.FinishedList);

	}

	private void setListeners() {
		helloList.setOnItemClickListener(this);
	}

	private void getData() {

		TransactionManager.updateTransactions(Constants.ID_SHOP);
		helloTransactions = TransactionManager
				.getStatusTransactions(TransactionManager.STATUS_HELLO);
		waitingTransactions = TransactionManager
				.getStatusTransactions(TransactionManager.STATUS_WAITING);
		finishedTransactions = TransactionManager
				.getStatusTransactions(TransactionManager.STATUS_FINISHED);
	}

	private void setData() {

		helloAdapter = new TransactionAdapter(getActivity(), helloTransactions);
		helloList.setAdapter(helloAdapter);
		helloAdapter.notifyDataSetChanged();

		waitingAdapter = new TransactionAdapter(getActivity(),
				waitingTransactions);
		waitingList.setAdapter(waitingAdapter);
		waitingAdapter.notifyDataSetChanged();

		finishedAdapter = new TransactionAdapter(getActivity(),
				finishedTransactions);
		finishedList.setAdapter(finishedAdapter);
		finishedAdapter.notifyDataSetChanged();
	}

	public void update() {
		getData();
		setData();
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(getActivity(), EnterPriceActivity.class);
		startActivity(i);
		
	}
}
