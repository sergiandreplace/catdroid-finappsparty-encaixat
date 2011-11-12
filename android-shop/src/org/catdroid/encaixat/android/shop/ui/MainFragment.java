package org.catdroid.encaixat.android.shop.ui;

import java.util.ArrayList;

import org.catdroid.encaixat.android.shop.Constants;
import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.adapter.TransactionAdapter;
import org.catdroid.encaixat.android.shop.dao.ServerManager;
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
		update();
		return me;
	}

	private void getViews() {
		helloList = (HorizontalListView) me.findViewById(R.id.HelloList);
		waitingList = (HorizontalListView) me.findViewById(R.id.WaitingList);
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

		helloAdapter = new TransactionAdapter(getActivity(), helloTransactions,
				TransactionAdapter.FORMAT_HELLO);
		helloList.setAdapter(helloAdapter);
		helloAdapter.notifyDataSetChanged();

		waitingAdapter = new TransactionAdapter(getActivity(),
				waitingTransactions, TransactionAdapter.FORMAT_WAITING);
		waitingList.setAdapter(waitingAdapter);
		waitingAdapter.notifyDataSetChanged();

		finishedAdapter = new TransactionAdapter(getActivity(),
				finishedTransactions, TransactionAdapter.FORMAT_FINISHED);
		finishedList.setAdapter(finishedAdapter);
		finishedAdapter.notifyDataSetChanged();
	}

	public void update() {
		getData();
		setData();
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Transaction transaction = TransactionManager.getTransactions().get(
				position);
		Intent i = new Intent(getActivity(), EnterPriceActivity.class);
		i.putExtra(EnterPriceActivity.EXTRA_ID_CUSTOMER, transaction
				.getCustomer().getIdCustomer());
		startActivityForResult(i, 0);

		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == EnterPriceActivity.RESULT_OK) {
			String idCustomer = data.getExtras().getString(
					EnterPriceActivity.EXTRA_ID_CUSTOMER);
			Double amount = data.getExtras().getDouble(
					EnterPriceActivity.EXTRA_AMOUNT);

			ServerManager.sendInvoice(Constants.ID_SHOP, idCustomer, amount);
			update();
		}
	}
}
