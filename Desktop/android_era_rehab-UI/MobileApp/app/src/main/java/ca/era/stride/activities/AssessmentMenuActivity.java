package ca.era.stride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ca.era.stride.R;

public class AssessmentMenuActivity extends AppCompatActivity {

    //ArrayAdapter<String> adapter;
    private View aWalkingButton;
    private MenuItem analyticsButton;
    private DrawerLayout mDrawerlayout;
    private NavigationView mNavigationView;
    private Menu mNavigationMenu;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_menu);
        aWalkingButton = findViewById(R.id.buttonGait);
        //aWalkingButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), GaitActivity.class)));

        View stepCounterButton = findViewById(R.id.buttonBalance);
        //stepCounterButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), StepTestActivity.class)));

        View allSensorsButton = findViewById(R.id.buttonEMG);
        allSensorsButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SensorsActivity.class)));

        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.closed);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView = findViewById(R.id.navmenu);
        mNavigationMenu = mNavigationView.getMenu();
        analyticsButton = mNavigationMenu.findItem(R.id.analysis);
        analyticsButton.setOnMenuItemClickListener((menuItem)->{
            //startActivity(new Intent(getApplicationContext(), AnalysisActivity.class));
            mDrawerlayout.closeDrawers();
            return false;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
   
    
}


