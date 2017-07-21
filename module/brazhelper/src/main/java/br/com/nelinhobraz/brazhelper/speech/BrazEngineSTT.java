package br.com.nelinhobraz.brazhelper.speech;

/**
 * Created by emanuelbraz on 20/07/17.
 */

    import android.app.Activity;
    import android.content.Intent;

    import android.os.Build;
    import android.speech.RecognizerIntent;
    import android.speech.SpeechRecognizer;
    import android.util.Log;
    import android.widget.Toast;

    import java.lang.Runnable;

public class BrazEngineSTT {

    public static String LANG_EN_US = "en-US";
    public static String LANG_PT_BR = "pt-BR";

    private Activity activity;

    private static String TAG = "BrazEngineSTT";

    //stt
    private static SpeechRecognizer sr;

    /* Constructor
    @param activity Activity instance
    * */
    public BrazEngineSTT(Activity activity) {

        this.activity = activity;

        Log.d(TAG, "Engine started!");

//        if (Build.VERSION.SDK_INT >= 23) {
//            // Pain in A$$ Marshmallow+ Permission APIs
//            requestForPermission();
//        }
    }

    /* Starts the service */
    public void listen(final BrazListenerSTT callback) {

//        this.callbackReference = callback;

        Log.d(TAG, "start listening...");
        try {
            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    if (sr == null) {
                        sr = SpeechRecognizer.createSpeechRecognizer(activity.getBaseContext());
                        sr.setRecognitionListener(callback);
                    }

                    if (!SpeechRecognizer.isRecognitionAvailable(activity.getBaseContext())) {
                        Toast.makeText(activity.getBaseContext(), "Speech Recognition is not available", Toast.LENGTH_LONG).show();
                        //TODO enviar callback  de erro, nao possui stt
                    }

                    final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                    //TODO : Usar ingles, se nao informar, usará o que for reconhecido, e ai??
                    String language =  LANG_PT_BR;
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, language);
                    intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, language);
                    intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, language);
                    intent.putExtra(RecognizerIntent.EXTRA_RESULTS, language);
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 6);


                    if (!intent.hasExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE)) {
                        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                                activity.getPackageName());
                    }

                    sr.startListening(intent);
                }
            });

            Log.d(TAG, "started listening sem erro");
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    /* Stops the service */
    public void stop() {
        if (sr != null) {
            sr.stopListening();
            sr.cancel();
            sr.destroy();
        }
        sr = null;
    }

}
