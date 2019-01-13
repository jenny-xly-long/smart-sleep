package ca.era.stride.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import ca.era.stride.R;
import ca.era.stride.application.EraApp;
import ca.era.stride.controller.PatientController;

public class PatientList extends BaseActivity {
    String[] patientNames;
    ArrayList<String> listItems, referenceList;
    ArrayList<Integer> idList, idListSearch;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    ImageView logoutBarBtn, homeBarBtn, viewProfileBarBtn, startTestBarBtn, addPatientBarBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EraApp.setActivityContext(this); //TODO: Does it need to be in onCreate, onResume, or onStart?

        setContentView(R.layout.activity_patient_list);

        homeBarBtn = (ImageView) findViewById(R.id.homeBarBtn);
        viewProfileBarBtn = (ImageView) findViewById(R.id.viewProfileBarBtn);
        addPatientBarBtn = (ImageView) findViewById(R.id.addPatientBarBtn);
        startTestBarBtn = (ImageView) findViewById(R.id.startTestBarBtn);
        logoutBarBtn = (ImageView) findViewById(R.id.logoutBarBtn);
        
    
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.txtsearch);
        //initList();
        toPatientArr();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    
            }
    
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
    
            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getText().toString().equals("")) {
                    initList();
                } else {
                    try{
                        searchItem(editText.getText().toString());
                    }catch(Exception e){
                        Toast toast = Toast.makeText(EraApp.getActivityContext(), ""+ Log.getStackTraceString(e), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // SET SELECTED ITEM
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                try{
                    changePage(position);
                }catch(Exception e){
                    Toast toast = Toast.makeText(EraApp.getActivityContext(), ""+ Log.getStackTraceString(e), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    
    }

    public void searchItem(String textToSearch) {
        if(listItems == null)return; //TODO something

        if(idListSearch == null) idListSearch = new ArrayList<Integer>();

        listItems.clear();
        idListSearch.clear();

        int i = 0;

        for (String item : referenceList) {
            if (item.contains(textToSearch)) {
                listItems.add(item);
                idListSearch.add(idList.get(i));
            }
            i ++;
        }

        adapter.notifyDataSetChanged();
    }
        
    
    public void initList(){
        listItems = toPatientArr();
        //adapter = new ArrayAdapter<String>(this, R.layout.patient_names, R.id.txtitem, listItems);
        //listView.setAdapter(adapter);
    }

    public void changePage(int pos){

        if(pos > -1) {
            Intent I = new Intent(this, PatientProfile.class);
            // pass user input in the intent
            I.putExtra("patient_id", "" + idListSearch.get(pos));

            startActivity(I);
        }
        else{
            Toast toast = Toast.makeText(EraApp.getActivityContext(), idListSearch.get(pos).toString(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    // Get patient list in the form of a string array from PatientController.java
    public ArrayList<String> toPatientArr(){
        Map<Integer, String> map = null;
        try {
            map = PatientController.getPatientNameList(this);
        }catch(Exception e){
            Toast toast = Toast.makeText(EraApp.getActivityContext(), ""+ Log.getStackTraceString(e), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        ArrayList<String> myArr = new ArrayList<String>();

        if(map == null) return myArr;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            myArr.add(entry.getValue());
            //TODO include patient id somehow
        }

        return myArr;
    }

    public void updatePatientList(Map<Integer, String> patientsMap){
        listItems = new ArrayList<String>();
        idList = new ArrayList<Integer>();
        for (Map.Entry<Integer, String> entry : patientsMap.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            listItems.add(entry.getValue());
            idList.add(entry.getKey());
            //TODO include patient id somehow
        }

        referenceList = (ArrayList<String>) listItems.clone();
        idListSearch = (ArrayList<Integer>) idList.clone();

        Toast.makeText(EraApp.getActivityContext(), "listItems is " + listItems.size() +" - "+ listItems.toString(), Toast.LENGTH_LONG).show();

        adapter = new ArrayAdapter<String>(this, R.layout.patient_names, R.id.txtitem, listItems);
        listView.setAdapter(adapter);

        //adapter.notifyDataSetChanged();
    }
}
