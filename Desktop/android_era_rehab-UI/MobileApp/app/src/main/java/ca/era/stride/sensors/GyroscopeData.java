package ca.era.stride.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class GyroscopeData extends SensorData implements SensorEventListener {

    private long timestamp;
    private float gyroX;
    private float gyroY;
    private float gyroZ;

    public GyroscopeData(SensorManager pSensorManager) {
        super(pSensorManager, Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Do something if sensor value changes
        timestamp = event.timestamp;
        gyroX = event.values[0];
        gyroY = event.values[1];
        gyroZ = event.values[2];

        this.setChanged();
        this.notifyObservers(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float[] getGyros(){
        return new float[]{gyroX, gyroY, gyroZ};
    }

    public float getGyroZ() {
        return gyroZ;
    }

    public float getGyroY() {
        return gyroY;
    }

    public float getGyroX() {
        return gyroX;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        return "Timestamp: "+timestamp+"; Rate of rotation: "+ gyroX +", "+ gyroY +", "+ gyroZ;
    }
}
