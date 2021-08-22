package com.example.lab_android_sensor_gustavofreitas_300309391;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {

    public interface Listener{
        void onTranslation(float translationX, float translationY, float translationZ);
    }

    private Listener listener;

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    public Accelerometer(Context context){

        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener != null){
                    listener.onTranslation(event.values[0],event.values[1],event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    public void setListener(Listener newListener){
        this.listener = newListener;
    }

    public void register(){
        this.sensorManager.registerListener(
                this.sensorEventListener,
                this.sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void unregister(){
        this.sensorManager.unregisterListener(
                this.sensorEventListener
        );
    }
}
