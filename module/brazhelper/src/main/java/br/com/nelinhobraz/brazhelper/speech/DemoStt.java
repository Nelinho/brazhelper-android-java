package br.com.nelinhobraz.brazhelper.speech;

import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import br.com.nelinhobraz.brazhelper.R;

/**
 * Created by emanuelbraz on 20/07/17.
 */

public class DemoStt extends AppCompatActivity {


    private BrazEngineSTT brazStt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        brazStt = new BrazEngineSTT(DemoStt.this);
        brazStt.listen(new BrazListenerSTT(){
            @Override
            public void onResults(Bundle data) {
                super.onResults(data);


                ArrayList matches = data.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                String best = matches.get(0).toString();
                Log.d("TAG", best);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (brazStt != null) brazStt.stop();
    }
}