package ca.era.stride.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import ca.era.stride.R;

public class WebTestActivity extends Activity {

    RequestQueue queue;
    StringRequest requestGet;
    StringRequest requestPost;

    Button buttonPost;
    Button buttonGet;
    EditText textURL;

    String requestURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);

        textURL = findViewById(R.id.editTextURL);
        buttonGet = findViewById(R.id.buttonGet);
        buttonPost = findViewById(R.id.buttonPost);

        queue = Volley.newRequestQueue(getApplicationContext());

//        requestGet = new StringRequest(
//            Request.Method.GET,
//            requestURL,//textURL.getText().toString(),
//            response -> {
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//            },
//            error -> {
//                Toast.makeText(
//                    getApplicationContext(),
//                    new String(error.networkResponse.data) + " " + String.valueOf(error.networkResponse.statusCode),
//                    Toast.LENGTH_LONG
//                ).show();
//            }
//        );

//        requestPost = new StringRequest(
//            Request.Method.POST,
//            requestURL,//textURL.getText().toString(),
//            response -> {
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//            },
//            error -> {
//                Toast.makeText(
//                    getApplicationContext(),
//                    String.valueOf(error.networkResponse.statusCode),
//                    Toast.LENGTH_LONG
//                ).show();
//            }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("data", "{\"key\": \"value\"}");
//                return params;
//            }
//        };

        buttonGet.setOnClickListener(view -> {
            try {
                URIBuilder getBuilder = new URIBuilder(textURL.getText().toString());
                getBuilder.addParameter("data", "{\"key\": \"value\"}");
                requestURL = getBuilder.build().toASCIIString();
                Log.d("URL", requestURL);
                queue.add(new StringRequest(
                        Request.Method.GET,
                        requestURL,//textURL.getText().toString(),
                        response -> {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        },
                        error -> {
                            Toast.makeText(
                                    getApplicationContext(),
                                    new String(error.networkResponse.data) + " " + String.valueOf(error.networkResponse.statusCode),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                ));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                Log.e("Exception", "URISyntaxException");
            }
        });
        buttonPost.setOnClickListener(view -> {
            try {
                URIBuilder getBuilder = new URIBuilder(textURL.getText().toString());
                requestURL = getBuilder.build().toASCIIString();
                Log.d("URL", requestURL);
                queue.add(new StringRequest(
                        Request.Method.POST,
                        requestURL,//textURL.getText().toString(),
                        response -> {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        },
                        error -> {
                            Toast.makeText(
                                    getApplicationContext(),
                                    String.valueOf(error.networkResponse.statusCode),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("data", "{\"key\": \"value\"}");
                        return params;
                    }
                });
            } catch (URISyntaxException e) {
                e.printStackTrace();
                Log.e("Exception", "URISyntaxException");
            }
        });
    }


    /*
    * This method takes in a baseURL (or URL route or wtv) and creates a properly formatted URI out of it
    * Useful when there are parameters to include in the URI (for GET requests for e.g.)
    * */
    private String buildURL(String baseURL, Map<String,String> params) throws URISyntaxException{
        URIBuilder uriBuilder = new URIBuilder(baseURL);

        for (Map.Entry<String, String> entry : params.entrySet())
            uriBuilder.addParameter(entry.getKey(), entry.getValue());

        return uriBuilder.build().toASCIIString();
    }
}
