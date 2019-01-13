package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ca.era.stride.R;
import ca.era.stride.application.EraApp;
import ca.era.stride.model.RecordingSession;

public class TestResult extends BaseActivity {

    Button deleteTrial, saveTrial;
    ImageView logoutBarBtn, homeBarBtn, viewProfileBarBtn, startTestBarBtn, addPatientBarBtn;
    TextView testNameTxt, patientNameTxt, timeResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        testNameTxt = (TextView) findViewById(R.id.testNameTxt);
        patientNameTxt = (TextView) findViewById(R.id.patientNameTxt);
        timeResultTxt = (TextView) findViewById(R.id.timeResultTxt);

        deleteTrial = (Button) findViewById(R.id.deleteTrialBtn);
        saveTrial =(Button) findViewById(R.id.saveTrialBtn);

        logoutBarBtn = (ImageView) findViewById(R.id.logoutBarBtn);
        homeBarBtn = (ImageView) findViewById(R.id.homeBarBtn);
        viewProfileBarBtn = (ImageView) findViewById(R.id.viewProfileBarBtn);
        startTestBarBtn = (ImageView) findViewById(R.id.startTestBarBtn);
        addPatientBarBtn = (ImageView) findViewById(R.id.addPatientBarBtn);

        // Get last recordingSession created
        RecordingSession recordingSession = EraApp.getEra().getRecordingSession(EraApp.getEra().numberOfRecordingSessions()-1);

        //testNameTxt.setText(recordingSession.getTestName()); //unsure what to do about this test name thing
        patientNameTxt.setText(recordingSession.getPatient().getSurname());

        //TODO set the actual time elapsed in a better way
        timeResultTxt.setText("" + recordingSession.getDataPoint(recordingSession.numberOfDataPoints()-1).getTime());
    }

    public void deleteTrialOnClick (View view){
        //TODO something for deleting the session

        Intent I = new Intent(this, AfterDelete.class);
        startActivity(I);
    }
    public void saveTrialOnClick (View view){
        //TODO something for saving the session

        Intent I = new Intent(this, AfterSave.class);
        startActivity(I);
    }

}
