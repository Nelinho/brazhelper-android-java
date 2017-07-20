package br.com.nelinhobraz.brazhelper;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.nelinhobraz.brazhelper.sensor.BrazSensor;
import br.com.nelinhobraz.brazhelper.sensor.BrazSensorListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BrazSensor.registerSensorProximity(this, new BrazSensorListener(){

            @Override
            public void onSensorChanged(SensorEvent event) {
                super.onSensorChanged(event);

                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] >= -BrazSensor.SENSOR_SENSITIVITY && event.values[0] <= BrazSensor.SENSOR_SENSITIVITY) {
//		            if (event.values[0] < mProximity.getMaximumRange()) {

                        Toast.makeText(getBaseContext(), "Over", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Out", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                super.onAccuracyChanged(sensor, accuracy);
            }
        });
    }
}
