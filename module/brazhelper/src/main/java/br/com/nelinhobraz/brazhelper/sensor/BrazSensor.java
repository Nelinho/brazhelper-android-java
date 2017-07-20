package br.com.nelinhobraz.brazhelper.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by emanuelbraz on 3/25/17.
 */

public class BrazSensor {

    private static SensorManager mSensorManager;
    private static Sensor mProximity;
    private static SensorEventListener listener;
    public static final int SENSOR_SENSITIVITY = 4;

    public static void registerSensorProximity(Context context, SensorEventListener sensorEventListener) {

        listener = sensorEventListener;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(listener, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public static void unRegisterSensorProximity() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(listener);
        }
    }

    public void onResume () {
        if (mSensorManager != null){
            mSensorManager.registerListener(listener, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void onPause() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(listener);
        }
    }

    public void onDestroy() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(listener);
            listener = null;
            mProximity = null;
            mSensorManager = null;
        }
    }

}