package ca.era.stride.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Observable;

public abstract class SensorData extends Observable implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mSensor;

    private long timestamp;
    private float[] values;

    public SensorData(SensorManager pSensorManager, int sensorType) {
        mSensorManager = pSensorManager;
        mSensor = mSensorManager.getDefaultSensor(sensorType);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public SensorData(SensorManager pSensorManager, int sensorType, int sensorDelay) {
        mSensorManager = pSensorManager;
        mSensor = mSensorManager.getDefaultSensor(sensorType);
        mSensorManager.registerListener(this, mSensor, sensorDelay);
    }

    protected Sensor getSensor(){
        return mSensor;
    }

    protected SensorManager getSensorManager(){
        return mSensorManager;
    }

    public void unregister(){
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Do something if sensor value changes
        timestamp = event.timestamp;
        values = event.values;

        this.setChanged();
        this.notifyObservers();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float[] getValues(){
        return values;
    }

    @Override
    public String toString(){
        return "Timestamp: "+timestamp+"; Values: "+values;
    }
}
