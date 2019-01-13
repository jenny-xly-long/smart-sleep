package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class BaseActivity extends Activity{

    // Footer Bar

    public void  homeBarOnClick (View view) {
        Intent I = new Intent(this, HomePage.class);
        // clear all previous activities
        I.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(I);
    }

    public void sessionsBarOnClick(View view){
        Intent I = new Intent(this, PatientProfile.class);
        startActivity(I);
    }

    public void viewProfileOnClick(View view){
        Intent I = new Intent(this, UserProfile.class);
        startActivity(I);
    }
}


