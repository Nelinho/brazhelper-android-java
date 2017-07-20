package br.com.nelinhobraz.brazhelper.sensor;

/**
 * Created by emanuelbraz on 3/25/17.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import br.com.nelinhobraz.brazhelper.R;

public class DemoSensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BrazSensor.registerSensorProximity(this, new BrazSensorListener(){

            @Override
            public void onNear() {
                super.onNear();
                Toast.makeText(getBaseContext(), "Perto", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFar() {
                super.onFar();
                Toast.makeText(getBaseContext(), "Longe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}