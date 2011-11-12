package org.catdroid.encaixat.android.shop.image;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * This class is responsible for executing a JsonHarvester object in
 * asyncrhonous mode and return the HarvestResult to the listener
 */
public class AsyncBitmapLoader extends AsyncTask<String, Void, Bitmap> {
	private OnHarvestFinishedListener onHarvestFinishedListener;
	private String originalUrl;
	private String id;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */

	@Override
	protected Bitmap doInBackground(String... params) {
		originalUrl=params[0];
		id=params[1];
		return BitmapLoader.getBitmap(originalUrl);
	}

	public void setOnHarvestFinishedListener(OnHarvestFinishedListener onHarvestFinishedListener) {
		this.onHarvestFinishedListener = onHarvestFinishedListener;
	}

	protected void onPostExecute(Bitmap bafImage) {
		if (onHarvestFinishedListener != null)
			onHarvestFinishedListener.onHarvestFinished(id, bafImage);
	}

	public interface OnHarvestFinishedListener {

		/**
		 * @param id
		 * @param bafImage
		 */
		void onHarvestFinished(String id, Bitmap bafImage);

	}
}
