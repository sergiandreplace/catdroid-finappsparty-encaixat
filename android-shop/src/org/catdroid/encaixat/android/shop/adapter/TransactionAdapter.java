package org.catdroid.encaixat.android.shop.adapter;

import java.util.ArrayList;

import org.catdroid.encaixat.android.shop.R;
import org.catdroid.encaixat.android.shop.image.BitmapLoader;
import org.catdroid.encaixat.bean.Transaction;

import android.content.Context;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

	private Context context;
	public static int FORMAT_HELLO = 0;
	public static int FORMAT_WAITING = 1;
	public static int FORMAT_FINISHED = 2;
	private ArrayList<Transaction> transactions;
	private int listFormat;

	public TransactionAdapter(Context context,
			ArrayList<Transaction> transactions, int listFormat) {
		super(context, R.layout.transaction_item, transactions);
		this.context = context;
		this.transactions = transactions;
		this.listFormat = listFormat;
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
			vh.background = (LinearLayout) v
					.findViewById(R.id.TransactionBackground);
			vh.amount = (TextView) v.findViewById(R.id.TransactionAmount);
			//vh.picture = (ImageView) v.findViewById(R.id.TransactionPicture);
			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
		}

		transaction = transactions.get(position);
		if (transaction.getInvoice() == null) {
			vh.name.setText(transaction.getCustomer().getName());
		} else {
			vh.name.setText(transaction.getCustomer().getName() + " ("
					+ transaction.getInvoice().getIdInvoice() + ")");

		}
		// vh.picture.setImageBitmap(BitmapLoader.getBitmap(transaction.getCustomer().getPicture()));
		if (listFormat != FORMAT_HELLO) {
			vh.amount.setText(String.format("%.2f €", transaction.getInvoice()
					.getQuantity()));
			if (listFormat == FORMAT_FINISHED) {
				vh.background
						.setBackgroundResource(R.drawable.button_green_normal);
			}
			if (listFormat == FORMAT_WAITING) {
				vh.background.startAnimation(getWaitingAnimation());
			}
		}
		return v;
	}

	private class ViewHolder {
		TextView name;
		LinearLayout background;
		TextView amount;
	}

	private Animation getWaitingAnimation() {
		AlphaAnimation anim = new AlphaAnimation(1f, 0.4f);
		anim.setDuration(1000);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(Animation.INFINITE);
		anim.setInterpolator(new LinearInterpolator());
		return anim;
	}

}
