package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.era.stride.R;
import ca.era.stride.view.CustomAdapter;

public class UserProfile extends BaseActivity {

    ListView userListView;
    ImageView logoutBarBtn, homeBarBtn, viewProfileBarBtn, startTestBarBtn, addPatientBarBtn;

    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        homeBarBtn = (ImageView) findViewById(R.id.homeBarBtn);
        viewProfileBarBtn = (ImageView) findViewById(R.id.viewProfileBarBtn);
        addPatientBarBtn = (ImageView) findViewById(R.id.addPatientBarBtn);
        startTestBarBtn = (ImageView) findViewById(R.id.startTestBarBtn);
        logoutBarBtn = (ImageView) findViewById(R.id.logoutBarBtn);


        userListView = (ListView) findViewById(R.id.userListView);

        names.add("Clinic location");
        names.add("Phone number");
        names.add("Next appointment");
        names.add("More info");
        names.add("More info");
        names.add("More info");

        String[] properties = new String[names.size()];
        properties = names.toArray(properties);

        ListAdapter myAdapter = new CustomAdapter(this, properties);

        userListView.setAdapter(myAdapter);

    }

}
