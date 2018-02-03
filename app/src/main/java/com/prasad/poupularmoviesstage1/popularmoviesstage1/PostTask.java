package com.prasad.poupularmoviesstage1.popularmoviesstage1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostTask extends AsyncTask<String, Void, String> {

	private Context context;
	private WebserviceCallback callback;
	private ProgressDialog progDialog;


	public PostTask(Context context,WebserviceCallback callback) {

		this.context = context;
		this.callback = callback;

	}

	private void showProgressDialog() {
		progDialog = new ProgressDialog(context);
		progDialog.setMessage("Loading Please Wait...");
		progDialog.setIndeterminate(true);
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setCancelable(false);
		progDialog.show();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		showProgressDialog();
	}

	@Override
	protected String doInBackground(String... params) {

		String movieUrl = params[0];


		HttpURLConnection httpURLConnection = null;
		InputStream inputStream = null;
		String postResult = null;
		try {



			URL url = new URL(movieUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setUseCaches(false);
			/* optional request header */
			httpURLConnection.setRequestProperty(Constants.ACCEPT,
					Constants.CONTENT);
			httpURLConnection.setRequestProperty(Constants.CONTENT_TYPE,
					Constants.CONTENT);
			httpURLConnection.setRequestMethod("GET");
			// optional
			httpURLConnection.connect();


			// getting response
			inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

			postResult = convertStreamToString(inputStream);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			Logger.log("response code is::catch1" + e);
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("response code is::catch2" + e);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log("response code is::catch3" + e);
		} finally {
			try {
				/* Close Stream */
				inputStream.close();
				httpURLConnection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return postResult;

	}



	@Override
	protected void onPostExecute(String postResult) {
		super.onPostExecute(postResult);
		dismissProgressDialog();
		
		if (postResult != null) {
			callback.postResult(postResult);
		} else {
			Toast.makeText(context, Constants.TIME_OUT, Toast.LENGTH_LONG)
					.show();
		}
	}
	
	private void dismissProgressDialog() {
		if ((progDialog != null) && progDialog.isShowing()) {
			try {
				progDialog.dismiss();
				progDialog = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String convertStreamToString(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();
	}
	
}
