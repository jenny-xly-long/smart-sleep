package ca.era.stride.persistence;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import ca.era.stride.activities.Recording;
import ca.era.stride.application.EraApp;
import ca.era.stride.controller.PatientController;
import ca.era.stride.model.Patient;
import ca.era.stride.model.RecordingSession;

import static ca.era.stride.persistence.WebServiceAccessor.HOST_HOSTEDNETWORK;

public class DataBuffer {
    private static String ip = WebServiceAccessor.HOST_HOSTEDNETWORK; //TODO change to proper ip

    public static void saveDataLocally(){
        //TODO stuff on phone
    }

    //TODO need something to take the locally saved stuff and push it to db

    public static void save(Object o){

        String objectType = o.getClass().getName();

        String json = JsonPackager.packData(o);

        Log.v("OBJECTTYPE", objectType);

        Map<String,String> params = new HashMap<String, String>();
        params.put("json", json);


        switch(objectType){
            //TODO Find way of doing something like Class.nameOf(Patient)
            //TODO Replace switch/case with a mapping betweem class name string and handlers
            case "ca.era.stride.model.Patient":
                //params.put("moreParams", "something");

                WebServiceAccessor.postRequest(ip, "/patient", params, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EraApp.getActivityContext(), response, Toast.LENGTH_LONG).show();
                        //TODO something if not success
                        ((Patient) o).setPatientID(Integer.valueOf(response));
                        Toast.makeText(EraApp.getActivityContext(), "Successfully added patient " +
                                ((Patient) o).getPatientID(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "ca.era.stride.model.RecordingSession":
                params.put("patientID", "" + ((RecordingSession) o).getPatient().getPatientID());

                WebServiceAccessor.postRequest(ip, "/recording", params, response -> {
                    Toast.makeText(EraApp.getActivityContext(), response, Toast.LENGTH_LONG).show();
                    //TODO something if not success
                    ((RecordingSession) o).setSessionID(Integer.valueOf(response));
                    Toast.makeText(EraApp.getActivityContext(), "Successfully added session " +
                            ((RecordingSession) o).getSessionID(), Toast.LENGTH_LONG).show();
                });
                break;
        }

        //TODO rethink what this is supposed to do here or if it has to be called separately
        //depending on reponse
        saveDataLocally(); //wtv that does
    }
}
