package br.com.nelinhobraz.brazhelper.speech;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by emanuelbraz on 13/06/17.
 */

public class BrazTTS {

    public static String LANG_EN_US = "en-US";
    public static String LANG_PT_BR = "pt-BR";

    private static String TAG = "BrazTTS";

    //tts
    private static TextToSpeech tts;


    /*
   Static method -> text to speech
   param texto Text string to speech
   return void
   */
    public void startTTS(Context context, final String texto){
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.getDefault());

                    if (Build.VERSION.SDK_INT >= 21) { //Lollipop, todo
                        tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
                    } else {
                        tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                }
            }
        });
    }
}
