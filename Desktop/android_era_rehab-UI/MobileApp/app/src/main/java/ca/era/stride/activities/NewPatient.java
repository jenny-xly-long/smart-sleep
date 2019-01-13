package ca.era.stride.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.era.stride.R;
import ca.era.stride.application.EraApp;
import ca.era.stride.controller.PatientController;
import ca.era.stride.model.Patient;

public class NewPatient extends BaseActivity {

    Button registerButton;
    ImageView logoutBarBtn, homeBarBtn, viewProfileBarBtn, startTestBarBtn, addPatientBarBtn;
    TextView nameView, surnameView, bdayView, heightView, weightView;
    RadioGroup radio;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        logoutBarBtn = (ImageView) findViewById(R.id.logoutBarBtn);
        homeBarBtn = (ImageView) findViewById(R.id.homeBarBtn);
        viewProfileBarBtn = (ImageView) findViewById(R.id.viewProfileBarBtn);
        startTestBarBtn = (ImageView) findViewById(R.id.startTestBarBtn);
        addPatientBarBtn = (ImageView) findViewById(R.id.addPatientBarBtn);

        radio = (RadioGroup) findViewById(R.id.radio);

        nameView = (EditText) findViewById(R.id.nameETxt);
        surnameView = (EditText) findViewById(R.id.surnameETxt);
        bdayView = (EditText) findViewById(R.id.bdayETxt);  //TODO change textbox to actual calendar where you can select date
        heightView = (EditText) findViewById(R.id.heightETxt);
        weightView = (EditText) findViewById(R.id.weightETxt);

        EraApp.setActivityContext(this);

        registerButton = (Button) findViewById(R.id.registerPatientBtn);

        //SharedPreferences loginData = getSharedPreferences("Patients", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = loginData.edit();
        //String patientNum = loginData.getString("patientNum", "").equals("") ? "0" : "" + (Integer.parseInt(loginData.getString("patientNum", "")) + 1);

        //Patient newPatient = null;
        /*try {
            newPatient = PatientController.createPatient(surname, name, gender, java.sql.Date.valueOf(bday), height, weight);

        } catch (Exception e) { //TODO make more specific exception (e.g. we could define our own InvalidInputException)
            Toast.makeText(NewPatient.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }*/

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get selected radio button from radioGroup
                int selectedId = radio.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                String gender = radioButton.getText().toString();

                String name = nameView.getText().toString();
                String surname = surnameView.getText().toString();
                String bday = bdayView.getText().toString(); //TODO Enforce YYYY-MM-DD format?

                String heightFromTextView = heightView.getText().toString().length() > 0 ? heightView.getText().toString() : "0";
                float height = Float.parseFloat(heightFromTextView);
                String weightFromTextView = weightView.getText().toString().length() > 0 ? weightView.getText().toString() : "0";
                float weight = Float.parseFloat(weightFromTextView);

                if (!(name.equals("") || surname.equals("") || gender.equals("") || bday.equals("") || heightFromTextView.equals("") || heightFromTextView.equals(""))) {

                    //TODO remove this
                    /*String prefix = loginData.getString("patientName", "").equals("") ? "" : "~";
                    editor.putString("patientName", loginData.getString("patientName", "") + prefix + name);
                    editor.putString("patientSurname", loginData.getString("patientSurname", "") + prefix + surname);
                    editor.putString("patientGender", loginData.getString("patientGender", "") + prefix + gender);
                    editor.putString("patientBDay", loginData.getString("patientBDay", "") + prefix + bday);
                    editor.putString("patientHeight", loginData.getString("patientHeight", "") + prefix + height);
                    editor.putString("patientWeight", loginData.getString("patientWeight", "") + prefix + weight);
                    editor.apply();*/


                    Patient newPatient = null;
                    try {
                        newPatient = PatientController.createPatient(surname, name, gender, java.sql.Date.valueOf(bday), height, weight);
                    } catch (Exception e) { //TODO make more specific exception (e.g. we could define our own InvalidInputException)
                        Toast.makeText(NewPatient.this, "The date of birth above is not valid", Toast.LENGTH_LONG).show();
                        //TODO not all exceptions are date parsing errors
                    }
                    /*if (newPatient != null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Patient added!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }*/
                        //Intent I = new Intent(NewPatient.this, PatientList.class);
                        //startActivity(I);
                    /*} else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Error creating patient. Sorry for the inconvenience!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }*/
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

    }

}
