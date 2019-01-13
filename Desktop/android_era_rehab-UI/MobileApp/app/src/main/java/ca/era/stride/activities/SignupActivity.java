package ca.era.stride.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.era.stride.R;



public class SignupActivity extends Activity {

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    
       final EditText email = (EditText) findViewById(R.id.emailETxt);
       final EditText password = (EditText) findViewById(R.id.passwordETxt);
       final EditText  confirmPassword = (EditText) findViewById(R.id.confirmPasswordETxt);
       final EditText createPassword = (EditText) findViewById(R.id.createPasswordETxt);

       signup = (Button) findViewById(R.id.googleLoginBtn);
        
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(email.getText().toString().isEmpty()||
                        password.getText().toString().isEmpty()||
                        confirmPassword.getText().toString().isEmpty()||
                        createPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please fill any empty fields.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignupActivity.this, "Sign up successful!", Toast.LENGTH_LONG).show();
                    Intent I = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(I);
                }
            }
        });
    }

}
