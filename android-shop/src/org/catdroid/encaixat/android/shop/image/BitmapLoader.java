package org.catdroid.encaixat.android.shop.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLoader {
	private static final String IMAGES_FOLDER = "encaixa";
	private static String sdcardPath;

	public static Bitmap getBitmap(String uri)
	{
		Bitmap bm=null;
		if (isBitmapInCache(uri))
			bm=getBitmapFromCache(uri);
		else {
			bm=getBitmapFromUri(uri);
			if (bm!=null)
				putBitmapInCache(bm, uri);
		}
		return bm;
	}
	
	public static Bitmap getBitmapFromUri(String uri) {
		try {

			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Bitmap getBitmapFromCache(String uri) {
		Bitmap result = null;
		if (isBitmapInCache(uri)) {
			String bitmapFile = getBitmapFullPath(uri);
			new File(bitmapFile).setLastModified(System.currentTimeMillis());
			result = BitmapFactory.decodeFile(bitmapFile);
		}
		return result;
	}

	public static void putBitmapInCache(Bitmap bm, String uri) {
		OutputStream outStream;
		String filePath = getBitmapFullPath(uri);
		try {
			outStream = new FileOutputStream(filePath);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean isBitmapInCache(String uri) {

		return new File(getBitmapFullPath(uri)).exists();
	}

	public static String getBitmapFullPath(String uri) {
		String cacheFolder = getCacheFolder();
		String filename = URLEncoder.encode(uri);
		String bitmapFile = cacheFolder;
		if (!bitmapFile.matches(".*/"))
			bitmapFile += "/";
		bitmapFile += filename;
		return bitmapFile;
	}

	public static String getCacheFolder() {
		File workingFolder;
		if (sdcardPath == null) {
			StringBuilder pathBuilder = new StringBuilder();
			pathBuilder.append(getSdPath());
			if (!pathBuilder.toString().matches(".*\\/"))
				pathBuilder.append("/");
			pathBuilder.append(IMAGES_FOLDER);
			if (!pathBuilder.toString().matches(".*\\/"))
				pathBuilder.append("/");
			workingFolder = new File(pathBuilder.toString());
			sdcardPath = workingFolder.toString();
		} else {
			workingFolder = new File(sdcardPath);
		}

		
			createCacheFolder(workingFolder);
			
		return sdcardPath;

	}

	private static void createCacheFolder(File workingFolder) {
		if (!workingFolder.exists())
			workingFolder.mkdirs();
		String nomedia=workingFolder.toString();
		if (!nomedia.matches(".*/")) nomedia +="/";
		nomedia+=".nomedia";
		try {
			new File (nomedia).createNewFile();
		} catch (IOException e) {

		}
		
	}

	public static boolean isSdPresent() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	public static String getSdPath() {
		return android.os.Environment.getExternalStorageDirectory().toString();
	}

}
