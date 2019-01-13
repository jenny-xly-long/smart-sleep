package ca.era.stride.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MagneticFieldData extends SensorData implements SensorEventListener {

    private long timestamp;
    private float mFieldX;
    private float mFieldY;
    private float mFieldZ;

    public MagneticFieldData(SensorManager pSensorManager) {
        super(pSensorManager, Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Do something if sensor value changes
        timestamp = event.timestamp;
        mFieldX = event.values[0];
        mFieldY = event.values[1];
        mFieldZ = event.values[2];

        this.setChanged();
        this.notifyObservers(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float[] getMagneticFields(){
        return new float[]{mFieldX, mFieldY, mFieldZ};
    }

    public float getMagneticFieldZ() {
        return mFieldZ;
    }

    public float getMagneticFieldY() {
        return mFieldY;
    }

    public float getMagneticFieldX() {
        return mFieldX;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        return "Timestamp: "+timestamp+"; Magnetic Fields: "+ mFieldX +", "+ mFieldY +", "+ mFieldZ;
    }
}
