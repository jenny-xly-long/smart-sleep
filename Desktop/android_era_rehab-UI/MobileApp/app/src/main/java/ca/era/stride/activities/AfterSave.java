package ca.era.stride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ca.era.stride.R;
import ca.era.stride.application.EraApp;
import ca.era.stride.model.RecordingSession;
import ca.era.stride.persistence.DataBuffer;

public class AfterSave extends AppCompatActivity {

    Button cancelConfirmBtn, saveConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_save);

        cancelConfirmBtn =(Button) findViewById(R.id.cancelConfirmBtn);
        saveConfirmBtn =(Button) findViewById(R.id.saveConfirmBtn);
    }
    public void cancelConfirmOnClick(View view){
        //Intent I = new Intent(this, TestResult.class);
        //startActivity(I);
        finish();
    }

    public void saveConfirmOnClick(View view){
        RecordingSession recordingSession = EraApp.getEra().getRecordingSession(EraApp.getEra().getRecordingSessions().size()-1);
        DataBuffer.saveDataLocally(); //TODO something about this
        DataBuffer.save(recordingSession);

        Intent I = new Intent(this, PatientList.class);
        startActivity(I);
        finish();
    }
}
