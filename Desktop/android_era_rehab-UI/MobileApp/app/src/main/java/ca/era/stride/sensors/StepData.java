package ca.era.stride.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;


public class StepData extends SensorData {

    private static final float DEFAULT_THRESHOLD = 10.5f;
    private static final float CUSTOM_THRESHOLD = 10f;

    private long currentTimestamp;
    private long lastTimestamp;
    private long interval;

    private float[] prev = {0f,0f,0f};

    private int stepCount = 0;
    private static final int ABOVE = 1;
    private static final int BELOW = 0;
    private static int CURRENT_STATE = 0;
    private static int PREVIOUS_STATE = BELOW;

    private int rawPoints = 0;
    private int sampleCount = 0;
    private long startTime;
    boolean SAMPLING_ACTIVE = true;
    private long streakStartTime;
    private long streakPrevTime;

    public StepData(SensorManager pSensorManager) {
        super(pSensorManager, Sensor.TYPE_ACCELEROMETER, SensorManager.SENSOR_DELAY_NORMAL);
        startTime = System.currentTimeMillis();
        streakPrevTime = System.currentTimeMillis() - 500;
    }

    private void handleEvent(SensorEvent event) {
        prev = lowPassFilter(event.values, prev);
        Accelerometer data = new Accelerometer(prev);

        if(data.R > CUSTOM_THRESHOLD){
            CURRENT_STATE = ABOVE;
            if(PREVIOUS_STATE != CURRENT_STATE) {
                streakStartTime = System.currentTimeMillis();
                if ((streakStartTime - streakPrevTime) <= 250f) {
                    streakPrevTime = System.currentTimeMillis();
                    return;
                }
                streakPrevTime = streakStartTime;
                Log.d("STATES:", "" + streakPrevTime + " " + streakStartTime);

                if(lastTimestamp == -1){
                    currentTimestamp = event.timestamp;
                    lastTimestamp = currentTimestamp;
                } else {
                    stepCount++;
                    lastTimestamp = currentTimestamp;
                    currentTimestamp = event.timestamp;
                    interval = currentTimestamp - lastTimestamp;
                    setChanged();
                    notifyObservers();
                }
            }
            PREVIOUS_STATE = CURRENT_STATE;
        }
        else if(data.R < DEFAULT_THRESHOLD) {
            CURRENT_STATE = BELOW;
            PREVIOUS_STATE = CURRENT_STATE;
        }
    }

    private float[] lowPassFilter(float[] input, float[] prev) {
        float ALPHA = 0.1f;
        if(input == null || prev == null) {
            return null;
        }
        for (int i=0; i< input.length; i++) {
            prev[i] = prev[i] + ALPHA * (input[i] - prev[i]);
        }
        return prev;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        handleEvent(event);
        if(SAMPLING_ACTIVE) {
            sampleCount++;
            long now = System.currentTimeMillis();
            if (now >= startTime + 5000) {
                double samplingRate = sampleCount / ((now - startTime) / 1000.0);
                SAMPLING_ACTIVE = false;
            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public long getTimestamp() {
        return currentTimestamp;
    }


    @Override
    public String toString(){
        return "Timestamp: "+currentTimestamp+";";
    }

    public long getTimeInterval() {
        return interval;
    }

    private class Accelerometer {
        public float X;
        public float Y;
        public float Z;
        public double R;


        public Accelerometer(float[] event) {
            X = event[0];
            Y = event[1];
            Z = event[2];
            R = Math.sqrt(X*X + Y*Y + Z*Z);
        }

        public Number toNumber() {
            Number number = R;
            return number;
        }
    }
}