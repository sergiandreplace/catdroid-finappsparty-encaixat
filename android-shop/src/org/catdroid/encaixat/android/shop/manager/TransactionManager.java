package org.catdroid.encaixat.android.shop.manager;

import java.util.ArrayList;

import org.catdroid.encaixat.bean.Customer;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Transaction;

public  class TransactionManager {

	public static int STATUS_HELLO = 0;
	public static int STATUS_WAITING = 1;
	public static int STATUS_FINISHED = 2;

	private static ArrayList<Transaction> transactions=new ArrayList<Transaction>();;



	public static TransactionManager TransactionManagerFactory() {
		return new TransactionManager();
	}

	public static ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public static ArrayList<Transaction> updateTransactions() {
		transactions.clear();
		Customer c=new Customer("1", "Sr. Dit", "");
		Transaction t=new Transaction(c, null, null);
		transactions.add(t);
		transactions.add(t);
		
		return transactions;
	}

	public static ArrayList<Transaction> getStatusTransactions(int status) {
		ArrayList<Transaction> filteredTransactions=new ArrayList<Transaction>();
		for (Transaction transaction:transactions) {
			if (getTransactionStatus(transaction).equals(status)) {
				filteredTransactions.add(transaction);
			}
		}
		return filteredTransactions;
	}

	private static Integer getTransactionStatus(Transaction transaction) {

		if (isStatusHello(transaction)) {
			return STATUS_HELLO;
		} else if (isStatusWaiting(transaction)) {
			return STATUS_WAITING;
		} else if (isStatusFinished(transaction)) {
			return STATUS_FINISHED;
		}
		return null;
	}

	private static boolean isStatusHello(Transaction transaction) {
		return transaction.getInvoice() == null;
	}

	private static boolean isStatusWaiting(Transaction transaction) {
		return transaction.getInvoice().getStatus() == Invoice.WAITING;
	}

	private static boolean isStatusFinished(Transaction transaction) {
		return transaction.getInvoice().getStatus() == Invoice.FINISHED;
	}

}
