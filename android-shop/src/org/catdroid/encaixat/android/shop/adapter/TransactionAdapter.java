package org.catdroid.encaixat.android.shop.adapter;

import java.util.ArrayList;

import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.bean.Transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

	private Context context;
	public static int FORMAT_HELLO = 0;
	public static int FORMAT_WAITING = 1;
	public static int FORMAT_FINISHED = 2;
	private ArrayList<Transaction> transactions;
	public TransactionAdapter(Context context,
			ArrayList<Transaction> transactions, int listFormat ) {
		super(context, R.layout.transaction_item, transactions);
		this.context = context;
		this.transactions = transactions;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder vh;

		Transaction transaction;
		if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
			v = vi.inflate(R.layout.transaction_item, null);
		}
		if (v.getTag() == null) {
			vh = new ViewHolder();
			vh.name = (TextView) v.findViewById(R.id.TransactionName);
			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
		}

		transaction = transactions.get(position);

		vh.name.setText(transaction.getCustomer().getName());

		return v;
	}

	private class ViewHolder {
		TextView name;
	}

}
