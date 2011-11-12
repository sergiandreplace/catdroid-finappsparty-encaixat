package org.catdroid.encaixat.android.customer.util;

import org.catdroid.encaixat.android.customer.Session;
import org.catdroid.encaixat.android.customer.ui.InvoiceReceivedActivity;
import org.catdroid.encaixat.android.dao.ServerManager;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class InvoiceRequestTask extends AsyncTask<Shop, Integer, Invoice> {

	Activity a;
	public InvoiceRequestTask(Activity activity){
		a = activity;
	}
	
	@Override
	protected Invoice doInBackground(Shop... arg0) {
		// TODO Auto-generated method stub
		Invoice inv = null;
		do {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inv = ServerManager.getInvoices(a, Session.shop.getIdShop(), Session.customer.getIdCustomer());
		} while (inv == null && isCancelled() != true);
		
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
		// update Session data
		Session.invoice = result;
		// pass data on to next activity
		Intent i = new Intent(a, InvoiceReceivedActivity.class);
		i.putExtra("INVOICE_ID", result.getIdInvoice());
		a.startActivity(i);
		a.finish();
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
