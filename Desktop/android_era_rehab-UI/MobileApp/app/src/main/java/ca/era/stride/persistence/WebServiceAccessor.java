package ca.era.stride.persistence;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import ca.era.stride.application.EraApp;

public class WebServiceAccessor {
    public static final String DEFAULT_PROTOCOL = "http";
    public static final String HOST_LOCAL_EMULATOR = "10.0.2.2";
    public static final String HOST_LOCALHOST = "localhost";
    public static final String HOST_HOSTEDNETWORK = "192.168.0.106";//"142.157.101.1";
    private static RequestQueue queue;

    /*
    * This method returns the existing Volley RequestQueue or creates one if nonexistent
    * */
    private static RequestQueue getQueue(){

        if(WebServiceAccessor.queue == null)
            WebServiceAccessor.queue = Volley.newRequestQueue(EraApp.getAppContext());

        return WebServiceAccessor.queue;
    }


    /*
    * This method sends a GET request to the server with the appropriate parameters in the URL
    * */
    public static void getRequest(String baseURL, String path, Map<String, String> params, Response.Listener<String> response){
        try {
            //TODO HTTP should not be default necessarily, also URL and paths might be static fields somewhere
            String requestURL = buildURL(DEFAULT_PROTOCOL, baseURL, path, params);

            Log.d("URL", requestURL);

            getQueue().add(new StringRequest(
                    Request.Method.GET,
                    requestURL,
                    //response -> {
                    //    Toast.makeText(context, response, Toast.LENGTH_LONG).show(); //TODO something with response
                    //},
                    response,
                    error -> {
                        Toast.makeText(
                                EraApp.getActivityContext(),
                                error.networkResponse != null ?
                                        String.valueOf(error.networkResponse.data) +" " +
                                                String.valueOf(error.networkResponse.statusCode) : null,                                Toast.LENGTH_LONG
                        ).show();
                    }
            ));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("Exception", "URISyntaxException");
        }
    }

    /*
    * This method sends a POST request with the appropriate parameters in the form
    * */
    public static void postRequest(String baseURL, String path, Map<String, String> params, Response.Listener<String> response){
        try {

            String requestURL = buildURL(DEFAULT_PROTOCOL, baseURL, path, new HashMap<>());

            Log.d("URL", requestURL);

            getQueue().add(new StringRequest(
                    Request.Method.POST,
                    requestURL,
                    //response -> {
                    //    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    //},
                    response,
                    error -> {
                        Toast.makeText(
                            EraApp.getActivityContext(),
                            error.networkResponse != null ?
                                    String.valueOf(error.networkResponse.data) +" " +
                                            String.valueOf(error.networkResponse.statusCode) : null,
                            Toast.LENGTH_LONG
                        ).show();
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("Exception", "URISyntaxException");
        }
    }

    /*
     * This method takes in a baseURL (or URL route or wtv) and creates a properly formatted URI out of it
     * Useful when there are parameters to include in the URI (for GET requests for e.g.)
     * */
    private static String buildURL(String protocol, String baseURL, String path, Map<String,String> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder().setScheme(protocol).setPath(path).setHost(baseURL);

        for (Map.Entry<String, String> entry : params.entrySet())
            uriBuilder.addParameter(entry.getKey(), entry.getValue());

        return uriBuilder.build().toASCIIString();
    }
}
