package com.unimelb.npd.games.balanceball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerSensor implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private WorldView worldView;
    private Context context;
    private long lastUpdate;
    
    public AccelerometerSensor(WorldView worldView, Context context) {
    	this.worldView = worldView;
    	this.context = context;
    	
    	lastUpdate = System.currentTimeMillis();
    	startSensor();
    }
    
    public void startSensor() {
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        mSensorManager.registerListener(this,
        		mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);  	
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	// Accuracy is given in onSensorChaged
    }

    public void onSensorChanged(SensorEvent event) {
    	int accuracy = event.accuracy;
    	long timestamp = event.timestamp;
    	float values[] = event.values;

    	try {
    		long curTime = System.currentTimeMillis();
    		
    		if ((curTime - lastUpdate) > 100) {
    	        lastUpdate = curTime;

    	        worldView.ball.setXSpeed(worldView.ball.getXSpeed()+((-1*values[0])/30));
    	        worldView.ball.setYSpeed(worldView.ball.getYSpeed()+(values[1]/30));
    		}
    		
    		//Log.d("X: ", String.valueOf(values[0]));
    		//Log.d("Y: ", String.valueOf(values[1]));

    	} catch(Exception e) {
    		//Log.d("Error", e.toString());
    	}
    }
}
