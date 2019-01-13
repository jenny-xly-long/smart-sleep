package ca.era.stride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ca.era.stride.R;

public class CountDown extends AppCompatActivity {
	
	TextView countDown;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_down);
		
		countDown= (TextView) findViewById(R.id.countDown) ;
		
		  new CountDownTimer(5500, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				countDown.setText(""+ millisUntilFinished / 1000);
			}
			@Override
			
			public void onFinish() {
				Intent I = new Intent(CountDown.this, Recording.class);
				startActivity(I);
			}
		}.start();
	}
}
