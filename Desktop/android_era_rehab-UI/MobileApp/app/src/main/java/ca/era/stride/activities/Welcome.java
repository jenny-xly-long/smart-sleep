package ca.era.stride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import ca.era.stride.R;
import ca.era.stride.application.EraApp;


public class Welcome extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        EraApp.setAppContext(getApplicationContext());

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(Welcome.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//		AccessToken accessToken = AccessToken.getCurrentAccessToken();
//		boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        if((isLoggedIn) || account != null){
//			Intent I = new Intent(this, Questionnaire.class);
//			startActivity(I);
//		}
//    }
}