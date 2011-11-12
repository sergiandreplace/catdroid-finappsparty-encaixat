package org.catdroid.encaixat.android.shop.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.catdroid.encaixat.android.shop.Constants;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Transaction;

import android.util.Log;

import com.google.gson.Gson;

public class ServerManager {
	
	/** @return a DefaultHttpClient that timeouts after 30s/10s. */
	private static DefaultHttpClient getNewHttpClient(){
		//Parametres de Timeout:
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
		// Set the default socket timeout (SO_TIMEOUT) in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, 10000);
		// Per les linies d'amunt, check http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
		
		// Turn off stale checking. Our connections break all the time anyway, and it's not worth it to pay the penalty of checking every time.
		HttpConnectionParams.setStaleCheckingEnabled(httpParameters, false);
		//@see: http://hc.apache.org/httpclient-3.x/apidocs/org/apache/commons/httpclient/params/HttpConnectionParams.html#setStaleCheckingEnabled%28boolean%29
        
		return new DefaultHttpClient(httpParameters);
	}
	
	private static String processContent(HttpEntity entity) throws IllegalStateException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		StringBuilder result = new StringBuilder();
		String readLine;
		while (((readLine = br.readLine()) != null)) {
			result.append(readLine);
		}
		br.close();
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Transaction> listTransactions(String idShop){
		
		DefaultHttpClient httpclient = getNewHttpClient();
		
		// Prepare a request object
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.BASEURL);
		sb.append("listTransactions");
		sb.append("?idShop="+idShop);
		
		HttpResponse response;
		ArrayList<Transaction> l = new ArrayList<Transaction>(); 

		try {
			response = httpclient.execute(new HttpGet(sb.toString()));
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String json = processContent(entity);

				l= (ArrayList<Transaction>) new Gson().fromJson(json, l.getClass());
			}

		} catch (Exception e) { 
			e.printStackTrace();
		}

		return l;
	}
	
public static Invoice sendInvoice(String idShop, String idCustomer, Double quantity){
		
		DefaultHttpClient httpclient = getNewHttpClient();
		
		// Prepare a request object
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.BASEURL);
		sb.append("sendInvoice");
		sb.append("?idShop="+idShop);
		sb.append("&idCustomer="+idCustomer);
		sb.append("&quantity="+quantity);
		
		HttpResponse response;
		Invoice i = null;

		try {
			response = httpclient.execute(new HttpGet(sb.toString()));
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String json = processContent(entity);
				i = new Gson().fromJson(json, Invoice.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
}
