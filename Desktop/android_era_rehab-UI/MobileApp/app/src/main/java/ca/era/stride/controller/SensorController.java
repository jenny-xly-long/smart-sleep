package ca.era.stride.controller;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;

import ca.era.stride.application.EraApp;
import ca.era.stride.model.Sensor;
import ca.era.stride.sensors.AccelerometerData;
import ca.era.stride.sensors.GyroscopeData;
import ca.era.stride.sensors.MagneticFieldData;
import ca.era.stride.sensors.SensorData;


public class SensorController {
    public static Sensor accSensor, gyroSensor, magSensor;

    public static void unregisterSensor(SensorData sensor){
        if(sensor != null)  sensor.unregister();
    }

    public static void checkPhoneSensors(SensorManager mSensorManager){

        android.hardware.Sensor sensor = mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        if(sensor != null){
            int sampleRate = 0;
            accSensor = new Sensor(sensor.getStringType(), sensor.getVendor() + "   " + sensor.getVersion(), sampleRate, EraApp.getEra());
        }

        sensor = mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_GYROSCOPE);
        if(sensor != null){
            int sampleRate = 0;
            gyroSensor = new Sensor(sensor.getStringType(), sensor.getVendor() + "   " + sensor.getVersion(), sampleRate, EraApp.getEra());
        }

        sensor = mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD);
        if(sensor != null){
            int sampleRate = 0;
            magSensor = new Sensor(sensor.getStringType(), sensor.getVendor() + "   " + sensor.getVersion(), sampleRate, EraApp.getEra());
        }
    }

    //Checks for existence of sensor, if existing it returns a SensorData instance
    public static SensorData getSensor(int sensorType, SensorManager mSensorManager){
        SensorData sensor = null;

        switch(sensorType){
            case android.hardware.Sensor.TYPE_ACCELEROMETER:
                if (mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER) != null)
                    sensor = new AccelerometerData(mSensorManager);
                break;
            case android.hardware.Sensor.TYPE_GYROSCOPE:
                if (mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_GYROSCOPE) != null)
                    sensor = new GyroscopeData(mSensorManager);
                break;
            case android.hardware.Sensor.TYPE_MAGNETIC_FIELD:
                if (mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD) != null)
                    sensor = new MagneticFieldData(mSensorManager);
                break;
        }

        return sensor;
    }

    /* Check if phone is ready to record a session
        -> battery percentage > 5%
        -> connected to internet == true
        -> phone has accelerometer, gyroscope, magnetometer
     */
    public static boolean requirementsCheckupList(SensorManager mSensorManager, Context context) {

        android.hardware.Sensor accelerometer =
                mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        android.hardware.Sensor gyroscope =
                mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_GYROSCOPE);
        android.hardware.Sensor magnetometer =
                mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD);
        int batLevel = getBatteryPercentage(context);

        return accelerometer != null && gyroscope != null && magnetometer != null && batLevel > 5;
    }


    /*
    check phone connectivity to internet
     */
    public static boolean isPhoneConenctedToInternet(Context context) {
        ConnectivityManager cm = (
                ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    /*
    return the battery level in percentage,
    used to do checkup list before experiment
     */
    public static int getBatteryPercentage(Context context) {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);

        int level = batteryStatus != null ?
                batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ?
                batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

        float batteryPct = level / (float) scale;

        return (int) (batteryPct * 100);
    }
}
