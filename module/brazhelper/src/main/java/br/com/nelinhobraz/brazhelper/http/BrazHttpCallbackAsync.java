package br.com.nelinhobraz.brazhelper.http;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by emanuelbraz on 3/11/17.
 */

public class BrazHttpCallbackAsync extends Application implements OnBrazHttpTaskListener {

    String TAG = BrazHttpCallbackAsync.class.getSimpleName();
    ProgressDialog pDialog;
    Context mContext;

    public BrazHttpCallbackAsync(){
        this.mContext = null;
    }

    public BrazHttpCallbackAsync(Context context){
        this.mContext = context;
    }

    @Override public void onTaskDone(String responseData){Log.i(TAG, "Connection Success!"); this.hideProgressDialog();};
    @Override public void onError(){Log.e(TAG, "On Genreral Error. eg. no connection to the server"); this.hideProgressDialog();};
    @Override public void onNotFound() {}
    @Override public void onServerOrConnectionError(int statusCode, String responseMessage) {Log.e(TAG, "Error responseCode: " + String.valueOf(statusCode) + " - Error message: " + responseMessage); this.hideProgressDialog();}


    public void setMyContext(Context context){
        this.mContext = context;
    }

    public void showProgressDialog(){
        // Showing progress dialog

        if (this.mContext != null){
            if (pDialog == null){
                pDialog = new ProgressDialog(this.mContext);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
            }

            pDialog.show();
        }

    }

    public void hideProgressDialog(){

        if (this.mContext != null){
            if (pDialog != null)
            {
                if (pDialog.isShowing()){
                    pDialog.dismiss();
                }
            }
        }

    }

}
