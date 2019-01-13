package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.era.stride.R;


public class CreateSession extends Activity {

    Button startTestBtn;
    TextView testNameTxt, patientNameTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
        testNameTxt = (TextView) findViewById(R.id.testNameTxt); //TODO make this editable
        patientNameTxt = (TextView) findViewById(R.id.patientNameTxt); //TODO change this for drop down list
        startTestBtn = (Button) findViewById(R.id.startTestBtn);

    }

        public void startTestOnClick(View view){
            //TODO create session object here after choosing patient

            Intent I = new Intent(this, CountDown.class);
            startActivity(I);
        }

}
