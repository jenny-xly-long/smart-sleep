package ca.era.stride.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class AccelerometerData extends SensorData implements SensorEventListener {
    //private Observable aObservable = new Observable();

    private long timestamp;
    private float accelerationX;
    private float accelerationY;
    private float accelerationZ;

    public AccelerometerData(SensorManager pSensorManager) {
        super(pSensorManager, Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Do something if sensor value changes
        timestamp = event.timestamp;
        accelerationX = event.values[0];
        accelerationY = event.values[1];
        accelerationZ = event.values[2];

        this.setChanged();
        this.notifyObservers(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float[] getAccelerations(){
        return new float[]{accelerationX, accelerationY, accelerationZ};
    }

    public float getAccelerationZ() {
        return accelerationZ;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public float getAccelerationX() {
        return accelerationX;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void test(){
        timestamp = 1010;
        accelerationX = 123;
        accelerationY = 456;
        accelerationZ = 789;

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString(){
        return "Timestamp: "+timestamp+"; Acceleration: "+accelerationX+", "+accelerationY+", "+accelerationZ;
    }
}
