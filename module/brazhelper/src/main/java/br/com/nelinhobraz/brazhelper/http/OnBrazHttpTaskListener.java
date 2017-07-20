package br.com.nelinhobraz.brazhelper.http;

/**
 * Created by emanuelbraz on 3/25/17.
 */

public interface OnBrazHttpTaskListener {
    void onTaskDone(String responseData);
    void onError();
    void showProgressDialog();
    void hideProgressDialog();
    void onNotFound();
    void onServerOrConnectionError(int statusCode, String responseMessage);
}
