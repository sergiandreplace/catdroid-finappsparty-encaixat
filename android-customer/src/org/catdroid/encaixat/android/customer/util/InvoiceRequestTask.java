package org.catdroid.encaixat.android.customer.util;

import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;

import android.os.AsyncTask;

public class InvoiceRequestTask extends AsyncTask<Shop, Integer, Invoice> {

	@Override
	protected Invoice doInBackground(Shop... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Invoice result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
