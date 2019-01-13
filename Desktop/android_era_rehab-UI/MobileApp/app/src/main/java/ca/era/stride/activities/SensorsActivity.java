package ca.era.stride.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ca.era.stride.R;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

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

public class SensorsActivity extends AppCompatActivity implements Observer {

    SensorManager mSensorManager;

    SensorData aAccelerometerData, aGyroscopeData, aMagneticFieldData;

    DataPointLogger sessionLogger;

    Button aStartMeasureButton;
    TextView aTimerTextBox, aAccTextBox, aGyroTextBox, aMagTextBox;

    Timer aTimer;
    long aTimerSeconds;

    private boolean aLogging = false;
    long aInitialTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
    }

    @Override
    protected void onStart(){
        super.onStart();

        aAccTextBox = findViewById(R.id.accTextBox);
        aGyroTextBox = findViewById(R.id.gyroTextBox);
        aMagTextBox = findViewById(R.id.magTextBox);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Try to get sensorData object for each sensor and if existing, add current activity as observer of sensorData object
        aAccelerometerData = SensorController.getSensor(Sensor.TYPE_ACCELEROMETER, mSensorManager);
        if(aAccelerometerData == null)  aAccTextBox.setText("Off");
        else    aAccelerometerData.addObserver(this);

        aGyroscopeData = SensorController.getSensor(Sensor.TYPE_GYROSCOPE, mSensorManager);
        if(aGyroscopeData == null)  aGyroTextBox.setText("Off");
        else    aGyroscopeData.addObserver(this);

        aMagneticFieldData = SensorController.getSensor(Sensor.TYPE_MAGNETIC_FIELD, mSensorManager);
        if(aMagneticFieldData == null)  aMagTextBox.setText("Off");
        else    aMagneticFieldData.addObserver(this);

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
                        if(aLogging) {
                            aTimerSeconds++;
                            aTimerTextBox.setText(convertSecondsToHMmSs(aTimerSeconds));
                        }
                    }
                });
            }
        }, 0, 1000);

        aStartMeasureButton = findViewById(R.id.startMeasureButton);
        aStartMeasureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Button btn = (Button) view;
                    if (aLogging) { //To stop
                        aLogging = false;

                        btn.setText("Start");

                        aTimerSeconds = 0;
                        aTimerTextBox.setText(convertSecondsToHMmSs(aTimerSeconds));

                        sessionLogger.save();

                    } else {    //To start
                        aInitialTime = aAccelerometerData.getTimestamp();
                        btn.setText("Stop");
                        aLogging = true;

                        if (sessionLogger == null) {
                            //TODO change to actual patient
                            Patient patient = PatientController.createPatient(
                                    "surname", "name", "gender",
                                    new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                                    0.0f, 0.0f);
                            //Patient patient = new Patient(
                            // 0, "name", new java.sql.Date(
                            // Calendar.getInstance().getTime().getTime()),
                            // 0.0f, 0.0f, EraApp.getEra());
                            RecordingSession recordingSession =
                                    RecordingSessionController.createRecordingSession(
                                            "", "", patient);
                            sessionLogger = new DataPointLogger(recordingSession);
                        }
                    }
                } catch(Exception e){
                    Toast.makeText(getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG);
                }
            }
        });
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
                if (aLogging) {
                    aAccTextBox.setText(aData.toString());
                    sessionLogger.logDataPoint(aData.getTimestamp() - aInitialTime, aData.getAccelerationX(),
                            aData.getAccelerationY(), aData.getAccelerationZ(), SensorController.accSensor);
                }
                break;
            case Sensor.TYPE_GYROSCOPE:
                GyroscopeData gData = (GyroscopeData) observable;
                if (aLogging) {
                    aGyroTextBox.setText(gData.toString());
                    sessionLogger.logDataPoint(gData.getTimestamp() - aInitialTime, gData.getGyroX(),
                            gData.getGyroY(), gData.getGyroZ(), SensorController.gyroSensor);
                }
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                MagneticFieldData mData = (MagneticFieldData) observable;
                if (aLogging) {
                    aMagTextBox.setText(mData.toString());
                    sessionLogger.logDataPoint(mData.getTimestamp() - aInitialTime, mData.getMagneticFieldX(),
                            mData.getMagneticFieldY(), mData.getMagneticFieldZ(), SensorController.magSensor);
                }
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
