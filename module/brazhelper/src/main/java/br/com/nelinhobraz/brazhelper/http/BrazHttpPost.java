package br.com.nelinhobraz.brazhelper.http;

/**
 * Created by emanuelbraz on 3/12/17.
 */
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuelbraz on 3/10/17.
 */

public class BrazHttpPost extends AsyncTask<String, Void, String> {

    private String TAG = BrazHttpPost.class.getSimpleName();
    public static String ERROR_RESPONSE_CODE = "error_response_code";

    private OnBrazHttpTaskListener onTaskDoneListener;
    private String urlStr = "";
    private HashMap<String, String> postDataParams;

    public BrazHttpPost() {

    }

    public BrazHttpPost prepare(String url, HashMap<String, String> postDataParams, OnBrazHttpTaskListener onTaskDoneListener) {
        this.onTaskDoneListener = onTaskDoneListener;
        this.urlStr = url;
        this.postDataParams = postDataParams;
        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.onTaskDoneListener.showProgressDialog();
    }

    @Override
    public String doInBackground(String... params) {

        try {

            URL url = new URL(urlStr);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setReadTimeout(15000);
            httpConnection.setConnectTimeout(15000);
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);


            OutputStream os = httpConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=httpConnection.getResponseCode();

            Log.d("Status Code: ", String.valueOf(responseCode));

            this.onTaskDoneListener.hideProgressDialog();

            if (responseCode != HttpURLConnection.HTTP_OK) {

                onTaskDoneListener.onServerOrConnectionError(httpConnection.getResponseCode(), httpConnection.getResponseMessage());
                return BrazHttpPost.ERROR_RESPONSE_CODE;

            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (IOException e) {
            this.onTaskDoneListener.hideProgressDialog();
            e.printStackTrace();
        } catch (Exception ex) {
            this.onTaskDoneListener.hideProgressDialog();
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s != null) {
            if (s != BrazHttpPost.ERROR_RESPONSE_CODE) {
                if (onTaskDoneListener != null) {
                    onTaskDoneListener.onTaskDone(s);
                }
            }
        } else {
            onTaskDoneListener.onError();
        }
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}