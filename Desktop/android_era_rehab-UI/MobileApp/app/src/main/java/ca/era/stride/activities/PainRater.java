package ca.era.stride.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.PopupMenu;

import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import ca.era.stride.R;

public class PainRater extends Activity {
	
	private static final String DEBUGTAG = "JL";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pain_rater);
		
		addTouchListener();
	}
	private void addTouchListener(){
		ImageView body = (ImageView) findViewById(R.id.FrontBackBody);
		body.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float x = event.getX();
				float y = event.getY();
				String coordinate = String.format("Coordinates (%.2f, %.2f)", x, y);
				Log.d(DEBUGTAG, coordinate);
				return false;
			}
		});
	}
}