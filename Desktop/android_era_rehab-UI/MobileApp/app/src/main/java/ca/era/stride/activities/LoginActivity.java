package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.era.stride.R;


public class LoginActivity extends Activity {
    
    LoginButton login_facebook;
    SignInButton login_google;
    
    CallbackManager callbackManager = CallbackManager.Factory.create();
    private static int RC_SIGN_IN = 100;
    
   
    //Google
//	private void handleSignInResult(GoogleSignInResult result) {
//		Log.d(TAG, "handleSignInResult:" + result.isSuccess());
//		if(result.isSuccess()){
//			GoogleSignInAccount account=result.getSignInAccount();
//			updateUI(true);
//
//			Intent I = new Intent(this, RemindYourGoal.class);
//			startActivity(I);
//			finish();
//		}
//		else{
//			updateUI(null);
//		}
//	}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        //Facebook Login
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d("login_facebook", "callback success");
                        
                    }
                    
                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("login_facebook", "callback cancel");
                        
                    }
                    
                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d("login_facebook", "callback onError");
                    }
                });
        final Activity activity = this;
	   
	    //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
	    
        login_facebook = (LoginButton) findViewById(R.id.login_facebook);
        login_facebook.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v) {
            	
                  Log.d("login_facebook", "click");
                  List<String> perm = new ArrayList<String>();
                  perm.add("user_friends");
	              LoginManager.getInstance().logInWithReadPermissions(activity, perm);
                
            }
			
        });
	
	   
	
	    //Google signin
        
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
	   
        
        login_google = findViewById(R.id.login_google);
        login_google.setSize(SignInButton.SIZE_STANDARD);
        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_google:
                        signIn();
                        break;
                }
            }
            
            private void signIn() {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
            
            
        });
    }
	
	@Override
	protected void onActivityResult ( int requestCode, int resultCode, Intent data) {
		callbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
//		GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//		AccessToken accessToken = AccessToken.getCurrentAccessToken();
//		boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//		if((!isLoggedIn) || (account == null)) {
			if (resultCode == RESULT_OK) {
				Intent I = new Intent(this, Intro.class);
				startActivity(I);
			}
			if (requestCode == RC_SIGN_IN) {
				Intent I = new Intent(this, Intro.class);
				startActivity(I);
			}
//		}
//		else if((isLoggedIn) || account != null){
//			login_facebook.setVisibility(View.GONE);
//			login_google.setVisibility(View.GONE);
//			Intent I = new Intent(this, Questionnaire.class);
//			startActivity(I);
//		}
		
	}
}


