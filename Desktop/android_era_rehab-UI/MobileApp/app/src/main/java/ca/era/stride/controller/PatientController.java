package ca.era.stride.controller;

import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

import ca.era.stride.activities.PatientList;
import ca.era.stride.activities.PatientProfile;
import ca.era.stride.application.EraApp;
import ca.era.stride.model.ERA;
import ca.era.stride.model.Patient;
import ca.era.stride.persistence.DataBuffer;
import ca.era.stride.persistence.JsonPackager;
import ca.era.stride.persistence.WebServiceAccessor;

public class PatientController {

    /*
    * This method allows you to create a patient object and save it to the database
    * @returns Patient object with assigned id
    * */
    public static Patient createPatient(String surname, String name, String gender, Date birthDate, float height, float weight){

        //TODO insert validation check here

        ERA era = EraApp.getEra();

        Patient p = era.addPatient(surname, name, gender, birthDate, height, weight);

        DataBuffer.save(p);

        return p;
    }

    public static void editPatient(){
        //TODO allows editing various parameters of patient
    }

    public static void deletePatient(){
        //TODO allows deleting a patient (if created by mistake or wtv)
    }

    /*
    * This method allows to retrieve a patient by name from the currently loaded patients
    * or from the database
    * @returns Desired patient, returns null if patient not found //TODO should instead throw exception
    * */
    public static Patient getPatient(String name){
        ERA era = EraApp.getEra();
        Patient patient;

        for(Patient p : era.getPatients()){
            if(p.getName().equals(name)){
                return p;
            }
        }



        return null;
    }

    /*
     * This method allows to retrieve a patient by id from the currently loaded patients
     * or from the database
     * @returns Desired patient, returns null if patient not found //TODO should instead throw exception
     * */
    public static Patient getPatient(int id, PatientProfile activity){

        Patient patient = Patient.getWithPatientID(id);
        if(patient == null){
            //TODO try to get patient from db

            Map<Integer, String> patients = new HashMap<Integer, String>();

            Map<String, String> params = new HashMap<>();

            params.put("json", "{\"id\": \"" + id + "\"}"); //TODO for yves: clean that shit up

            //TODO do not hardcode ip and path as strings but use predefined shenanigans in webserviceaccessor
            WebServiceAccessor.getRequest(WebServiceAccessor.HOST_HOSTEDNETWORK, "/patient", params, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //TODO for debugging
                    Toast.makeText(EraApp.getActivityContext(), response, Toast.LENGTH_LONG).show();

                    Map<String, Object> patient = JsonPackager.unpackDataAsMap(response);

                    //TODO create patient object instead of map and pass the patient object
                    activity.updateProfile(patient);
                }
            });

        } else
            return patient;

        return null;
    }

    /*
    * This method allows to retrieve the list of patient names from the db
    * @returns List of patient names as strings
    * */
    public static Map<Integer, String> getPatientNameList(PatientList activity){
        Map<Integer, String> patients = new HashMap<Integer, String>();

        Map<String, String> params = new HashMap<>();

        params.put("json", "{\"isFetchingAll\": \"True\"}"); //TODO for yves: clean that shit up

        //TODO do not hardcode ip and path as strings but use predefined shenanigans in webserviceaccessor
        WebServiceAccessor.getRequest(WebServiceAccessor.HOST_HOSTEDNETWORK, "/patient", params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO for debugging
                Toast.makeText(EraApp.getActivityContext(), response, Toast.LENGTH_LONG).show();

                Map<Integer, String> patientsMap = new HashMap<Integer, String>();
                for (Map.Entry<String, Object> entry : JsonPackager.unpackDataAsMap(response).entrySet())
                    patientsMap.put(Integer.valueOf(entry.getKey()), entry.getValue().toString());

                activity.updatePatientList(patientsMap);
            }
        });
        //TODO useless to return this
        return patients;
    }
}
