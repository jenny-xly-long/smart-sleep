package ca.era.stride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ca.era.stride.R;

public class HomePage extends BaseActivity {

    ImageView recordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recordBtn = (ImageView) findViewById(R.id.recordBtn);

        recordBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToRecordPage();
            }
        });
    }

    public void goToRecordPage(){
        Intent I = new Intent(this, Recording.class);
        startActivity(I);
    }
}
