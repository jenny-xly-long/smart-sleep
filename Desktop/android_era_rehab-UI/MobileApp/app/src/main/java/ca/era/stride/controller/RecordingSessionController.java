package ca.era.stride.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import ca.era.stride.activities.Recording;
import ca.era.stride.application.EraApp;
import ca.era.stride.model.ERA;
import ca.era.stride.model.Patient;
import ca.era.stride.model.RecordingSession;
import ca.era.stride.sensors.SensorData;
import android.widget.Toast;


public class RecordingSessionController {

    private static boolean isRecording;
    private static Context mcontext;
    private static SensorManager mSensorManager;
    private static SensorData aAccelerometerData, aGyroscopeData, aMagneticFieldData;

    public static RecordingSession createRecordingSession(String comment, String phoneStatus, Patient patient){
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());

        ERA era = EraApp.getEra();
        return era.addRecordingSession(date, time, comment, phoneStatus, patient);
    }

    public static void addNoteToSession(int sessionID, Time startTime, Time endTime, String comment){
        RecordingSession session = RecordingSession.getWithSessionID(sessionID);
        session.addNote(startTime, endTime, comment);
    }

    public static void initiateSession(int sessionID) {
        //Include timer stuff here (or maybe in the activity?)
        mSensorManager = (SensorManager) mcontext.getSystemService(Context.SENSOR_SERVICE);
        Boolean sensorAvailable = checkupPhoneSensorAvailability();

        //After timer, do some audio signal and start recording
        //startRecording(sessionID);
    }

    public static Boolean checkupPhoneSensorAvailability() {
        aAccelerometerData = SensorController.getSensor(Sensor.TYPE_ACCELEROMETER, mSensorManager);
        aGyroscopeData = SensorController.getSensor(Sensor.TYPE_GYROSCOPE, mSensorManager);
        aMagneticFieldData = SensorController.getSensor(Sensor.TYPE_MAGNETIC_FIELD, mSensorManager);

        if (aAccelerometerData == null || aGyroscopeData == null || aMagneticFieldData == null)
            return false;
        else
            return true;
    }

    public static void startRecording(RecordingSession session){
        isRecording = true;

        //RecordingSession session = RecordingSession.getWithSessionID(sessionID);

        // Set start time
        session.setTime(new java.sql.Time(Calendar.getInstance().getTime().getTime()));

    }


    public static void stopRecording() throws Exception{
        if(!isRecording){
            throw new Exception("Not recording!"); //TODO define our own exception type
        }

        isRecording = false;
    }

}
