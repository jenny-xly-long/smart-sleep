package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.era.stride.R;
import ca.era.stride.application.EraApp;

public class Intro extends Activity {
	private static int SPLASH_TIME_OUT = 4000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				Intent I = new Intent(Intro.this, Questionnaire.class);
				startActivity(I);
			}
		}, SPLASH_TIME_OUT);
	}
}
