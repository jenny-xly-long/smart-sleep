package ca.era.stride.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import ca.era.stride.R;
import ca.era.stride.controller.PatientController;
import ca.era.stride.view.CustomAdapter;


public class PatientProfile extends BaseActivity {

    TextView patientNameTxt;
    ListView patientInfoListView;
    ArrayList<String> infosArr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        patientNameTxt = (TextView) findViewById(R.id.patientNameTxt);

        patientInfoListView = (ListView) findViewById(R.id.patientInfoListView);


        // set title textview to the name of the patient
        Bundle PatientListData = getIntent().getExtras();
        if (PatientListData== null){
            return;
        }

        final int patientID = Integer.parseInt(PatientListData.getString("patient_id"));

        PatientController.getPatient(patientID, this);

    }

    public void updateProfile(Map<String, Object> patient){

        patientNameTxt.setText(patient.get("surname") + " " + patient.get("name"));

        infosArr.add("Gender: " + patient.get("gender"));
        infosArr.add("Birthday: " + patient.get("birthdate"));
        infosArr.add("Height: " + patient.get("height"));
        infosArr.add("Weight: " + patient.get("weight"));

        String[] properties = new String[infosArr.size()];
        properties = infosArr.toArray(properties);

        ListAdapter myAdapter = new CustomAdapter(this, properties);

        patientInfoListView.setAdapter(myAdapter);

    }

    public void deletePatient(int pos){

        SharedPreferences loginData = getSharedPreferences("Patients", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();

        String patientNames = (loginData.getString("patientName", ""));
        String patientHeights = (loginData.getString("patientHeight", ""));
        String patientWeights = (loginData.getString("patientWeight", ""));
        String patientGenders = (loginData.getString("patientGender", ""));
        String patientBDays = (loginData.getString("patientBDay", ""));

        editor.putString("patientName", modifyStringForDelete(patientNames, pos));
        editor.putString("patientGender", modifyStringForDelete(patientGenders, pos));
        editor.putString("patientBDay", modifyStringForDelete(patientBDays, pos));
        editor.putString("patientHeight", modifyStringForDelete(patientHeights, pos));
        editor.putString("patientWeight", modifyStringForDelete(patientWeights, pos));
        editor.apply();

        Intent I = new Intent(this, PatientList.class);
        startActivity(I);

        finish();
    }

    public String modifyStringForDelete(String listString, int pos){

        String[] items = listString.split("~");
        String newStr = "";

        if (pos == items.length - 1){
            for (int i = 0; i < items.length - 1; i++) {
                newStr += (i == items.length - 2) ? items[i] : items[i] + "~";
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                if (i == pos) {
                    continue;
                } else {
                    newStr += (i == items.length - 1) ? items[i] : items[i] + "~";
                }
            }
        }

        return newStr;

    }
}
