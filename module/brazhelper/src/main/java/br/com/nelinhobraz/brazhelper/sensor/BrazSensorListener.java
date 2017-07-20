package br.com.nelinhobraz.brazhelper.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Created by emanuelbraz on 3/25/17.
 */

public class BrazSensorListener implements SensorEventListener {

    private String tag = "BrazSensorListener";

    public void onNear(){
        Log.d(tag, "onNear");
    }

    public void onFar(){
        Log.d(tag, "onFar");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -BrazSensor.SENSOR_SENSITIVITY && event.values[0] <= BrazSensor.SENSOR_SENSITIVITY) {
//		    if (event.values[0] < mProximity.getMaximumRange()) {
                onNear();
            } else {
                onFar();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}