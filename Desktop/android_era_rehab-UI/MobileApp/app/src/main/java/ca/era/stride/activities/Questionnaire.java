package ca.era.stride.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.era.stride.R;

public class Questionnaire extends AppCompatActivity {
	
	private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
	private Button choice1, choice2, choice3, choice4, choice5;
	private TextView question, progress;
	private int mQuestionNumber = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire);
		
		choice1 = (Button)findViewById(R.id.choice1);
		choice2 = (Button)findViewById(R.id.choice2);
		choice3 = (Button)findViewById(R.id.choice3);
		choice4 = (Button)findViewById(R.id.choice4);
		choice5 = (Button)findViewById(R.id.choice5);
		question = (TextView)findViewById(R.id.question);
		progress = (TextView)findViewById(R.id.progress);
		
		progress.setText("Progress: " + mQuestionNumber + "/" + mQuestionLibrary.QuestionLength);
		updateQuestion();
		
		//Button listener for button1
		choice1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (mQuestionNumber < mQuestionLibrary.QuestionLength) {
					progress.setText("Progress: " + mQuestionNumber + "/" + mQuestionLibrary.QuestionLength);
					updateQuestion();
				}
				else{
					goToPainRaterPage();
				}
			}
		});
		choice2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(mQuestionNumber < mQuestionLibrary.QuestionLength) {
					progress.setText("Progress: " + mQuestionNumber + "/" + mQuestionLibrary.QuestionLength);
					updateQuestion();
					
				}
				else{
					goToPainRaterPage();
				}
			}
		});
		choice3.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(mQuestionNumber < mQuestionLibrary.QuestionLength) {
					progress.setText("Progress: " + mQuestionNumber + "/" + mQuestionLibrary.QuestionLength);
					updateQuestion();
					
				}
				else{
					goToPainRaterPage();
				}
			}
		});
		choice4.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(mQuestionNumber < mQuestionLibrary.QuestionLength) {
					progress.setText("Progress: " + mQuestionNumber + "/" + mQuestionLibrary.QuestionLength);
					updateQuestion();
				}
				else{
					goToPainRaterPage();
				}
			}
		});
		choice5.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(mQuestionNumber < mQuestionLibrary.QuestionLength) {
					progress.setText("Progress: " + mQuestionNumber + "/" + mQuestionLibrary.QuestionLength);
					updateQuestion();
				}
				else{
					goToPainRaterPage();
				}
			}
		});
	}
	
	private void updateQuestion(){
		question.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
		choice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
		choice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
		choice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
		choice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));
		choice5.setText(mQuestionLibrary.getChoice5(mQuestionNumber));
		mQuestionNumber++;
	}
	private void goToPainRaterPage(){
		Intent I = new Intent(this, PainRater.class);
					startActivity(I);
	}
}
