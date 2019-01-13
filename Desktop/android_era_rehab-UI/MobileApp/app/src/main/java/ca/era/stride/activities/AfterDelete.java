package ca.era.stride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ca.era.stride.R;

public class AfterDelete extends AppCompatActivity {

    Button cancelConfirmBtn, deleteConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_delete);
        cancelConfirmBtn =(Button) findViewById(R.id.cancelConfirmBtn);
        deleteConfirmBtn =(Button) findViewById(R.id.deleteConfirmBtn);
    }

    public void cancelConfirmOnClick(View view){
        //Intent I = new Intent(this, TestResult.class);
        //startActivity(I);
        finish();
    }

    public void deleteConfirmOnClick(View view){
        Intent I = new Intent(this, PatientList.class);
        startActivity(I);
        finish();
    }
}
