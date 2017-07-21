package br.com.nelinhobraz.brazhelper.speech;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by emanuelbraz on 20/07/17.
 */

public class BrazListenerSTT implements RecognitionListener {

    private static String TAG = BrazListenerSTT.class.getSimpleName();

    public void onResults(Bundle data) {
        Log.d(TAG, "*** ONRESULTS");

        ArrayList matches = data.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] output = new String[matches.size()];

        for (int i = 0 ; i < matches.size() ; i++){
            output[i] = matches.get(i).toString();

            Log.d(TAG, matches.get(i).toString());
        }

    }

    // User starts speaking
    public void onBeginningOfSpeech() {
        Log.d(TAG,"Starting to listen");
    }

    public void onBufferReceived(byte[] buffer) { }

    // User finished speaking
    public void onEndOfSpeech() {
        Log.d(TAG,"Waiting for result...");
    }

    public void onError(int error) {

        String mError = "";

        switch (error) {
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                mError = "network timeout";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                mError = "network" ;
                break;
            case SpeechRecognizer.ERROR_AUDIO:
                mError = "audio";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                mError = "server";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                mError = "client";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                mError = "speech time out" ;
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                mError = "no match" ;
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                mError = "recogniser busy" ;
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                mError = "insufficient permissions" ;
                break;

        }

        Log.e(TAG,  "Error: " +  error + " - " + mError);
    }

    public void onEvent(int eventType, Bundle params) { }

    public void onPartialResults(Bundle partialResults) { }

    public void onReadyForSpeech(Bundle params) { }

    public void onRmsChanged(float rmsdB) { }


}
