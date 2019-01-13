package ca.era.stride.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import ca.era.stride.R;
import ca.era.stride.application.EraApp;
import ca.era.stride.controller.DataPointLogger;
import ca.era.stride.controller.PatientController;
import ca.era.stride.controller.RecordingSessionController;
import ca.era.stride.controller.SensorController;
import ca.era.stride.model.Patient;
import ca.era.stride.model.RecordingSession;
import ca.era.stride.sensors.AccelerometerData;
import ca.era.stride.sensors.GyroscopeData;
import ca.era.stride.sensors.MagneticFieldData;
import ca.era.stride.sensors.SensorData;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import ca.era.stride.R;
import pl.droidsonroids.gif.GifImageView;

public class Recording extends BaseActivity {

    public Integer imageId_start_recording = R.drawable.record;
    public Integer[] imageId = {R.drawable.pause, R.drawable.play_button};

    GifImageView recordingImg;
    ImageView playPauseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        recordingImg = (GifImageView) findViewById(R.id.recordingImg);
        playPauseBtn = (ImageView) findViewById(R.id.playPauseBtn);


        recordingImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startRecording();
            }
        });

        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayStartingImg();
            }
        });
    }

    public void startRecording(){
        playPauseBtn.setImageResource(imageId[0]);
        recordingImg.setImageResource(imageId_start_recording);
    }

    public void displayStartingImg(){
        playPauseBtn.setImageResource(imageId[1]);
    }
}


/*
public class Recording extends Activity implements Observer {

    Button pause, stopTestBtn;
    private SensorManager mSensorManager;
    private SensorData aAccelerometerData, aGyroscopeData, aMagneticFieldData;
    private DataPointLogger sessionLogger;
    private long aInitialTime;
    private TextView aTimerTextBox;
    private int aTimerSeconds;
    private Timer aTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        stopTestBtn = (Button) findViewById(R.id.stopTestBtn);
    }

    public void stopTestOnClick(View view){
        try {
            RecordingSessionController.stopRecording();
        } catch (Exception e){

        }

        //sessionLogger.save();
        //TODO the save must actually be made in the activity after this one, not here
        //TODO maybe do a local save and then after, can do a db save?

        SensorController.unregisterSensor(aAccelerometerData);
        SensorController.unregisterSensor(aGyroscopeData);
        SensorController.unregisterSensor(aMagneticFieldData);

        Intent I = new Intent(this, TestResult.class);
        startActivity(I);
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Try to get sensorData object for each sensor and if existing, add current activity as observer of sensorData object
        aAccelerometerData = SensorController.getSensor(Sensor.TYPE_ACCELEROMETER, mSensorManager);
        if(aAccelerometerData != null)  aAccelerometerData.addObserver(this);

        aGyroscopeData = SensorController.getSensor(Sensor.TYPE_GYROSCOPE, mSensorManager);
        if(aGyroscopeData != null)  aGyroscopeData.addObserver(this);

        aMagneticFieldData = SensorController.getSensor(Sensor.TYPE_MAGNETIC_FIELD, mSensorManager);
        if(aMagneticFieldData != null)  aMagneticFieldData.addObserver(this);

        // Initialize the sensor objects in SensorController (to be used for logging points)
        SensorController.checkPhoneSensors(mSensorManager);

        aTimerTextBox = findViewById(R.id.timerTextBox);
        aTimerSeconds = 0;
        aTimerTextBox.setText(convertSecondsToHMmSs(aTimerSeconds));

        aTimer = new Timer();
        aTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        aTimerSeconds++;
                        aTimerTextBox.setText(convertSecondsToHMmSs(aTimerSeconds));
                    }
                });
            }
        }, 0, 1000);

        try {
            aInitialTime = aAccelerometerData.getTimestamp();

            if (sessionLogger == null) {
                //TODO to be removed
                Patient patient = PatientController.createPatient(
                        "surname", "name", "gender",
                        new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                        0.0f, 0.0f);
                //TODO retrieve previously created session
                RecordingSession recordingSession =
                        RecordingSessionController.createRecordingSession(
                                "", "", patient);

                // Get last recordingSession created
                //RecordingSession recordingSession = EraApp.getEra().getRecordingSession(EraApp.getEra().getRecordingSessions().size()-1);

                sessionLogger = new DataPointLogger(recordingSession);

                RecordingSessionController.startRecording(recordingSession);
            }
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        SensorController.unregisterSensor(aAccelerometerData);
        SensorController.unregisterSensor(aGyroscopeData);
        SensorController.unregisterSensor(aMagneticFieldData);
    }

    @Override
    public void update(Observable observable, Object o) {

        switch((int) o){
            case Sensor.TYPE_ACCELEROMETER:
                AccelerometerData aData = (AccelerometerData) observable;
                sessionLogger.logDataPoint(aData.getTimestamp() - aInitialTime, aData.getAccelerationX(),
                        aData.getAccelerationY(), aData.getAccelerationZ(), SensorController.accSensor);
                break;
            case Sensor.TYPE_GYROSCOPE:
                GyroscopeData gData = (GyroscopeData) observable;
                sessionLogger.logDataPoint(gData.getTimestamp() - aInitialTime, gData.getGyroX(),
                        gData.getGyroY(), gData.getGyroZ(), SensorController.gyroSensor);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                MagneticFieldData mData = (MagneticFieldData) observable;
                sessionLogger.logDataPoint(mData.getTimestamp() - aInitialTime, mData.getMagneticFieldX(),
                        mData.getMagneticFieldY(), mData.getMagneticFieldZ(), SensorController.magSensor);
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();

    }

    private static String convertSecondsToHMmSs(long seconds) {
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%d:%02d:%02d", h,m,s);
    }
}
*/