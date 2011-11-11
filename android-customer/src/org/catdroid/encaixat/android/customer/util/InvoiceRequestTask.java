package org.catdroid.encaixat.android.customer.util;

import org.catdroid.encaixat.android.customer.Session;
import org.catdroid.encaixat.android.dao.ServerManager;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;

import android.app.Activity;
import android.os.AsyncTask;

public class InvoiceRequestTask extends AsyncTask<Shop, Integer, Invoice> {

	Activity a = null;
	public InvoiceRequestTask(Activity activity){
		a = activity;
	}
	
	@Override
	protected Invoice doInBackground(Shop... arg0) {
		// TODO Auto-generated method stub
		Invoice inv = null;
		do {
			inv = ServerManager.getInvoices(a, Session.shop.getIdShop(), Session.customer.getIdCustomer());
		} while (inv == null);
		
		return inv;
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
