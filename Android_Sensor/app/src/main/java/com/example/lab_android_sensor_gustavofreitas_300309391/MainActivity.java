package com.example.lab_android_sensor_gustavofreitas_300309391;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float translationX, float translationY, float translationZ) {
                if(translationX > 0.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                } else if(translationX < 0.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }
        });

        gyroscope.setListener(new Gyroscope.Listener(){
            @Override
            public void onRotation(float rotationX, float rotationY, float rotationZ) {
                if(rotationZ > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                } else if(rotationZ < -1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
        gyroscope.unregister();
    }
}