package ca.era.stride.activities;

public class QuestionLibrary {

	public QuestionLibrary(){
		super();
	}
	private String mQuestions[] = {
			"What is the reason for your consultation?",
			"If applies, when did the injury happen?",
			"How did your pain evolve since injury",
			"What is your goal?",
			"On a scale of 1 to 5, how does your pain negatively affect your life (e.g 1: not much; 5: very much)?"
	};
	public int QuestionLength = mQuestions.length;
	
	
	private String mChoices[][] = {
			{"Injury", "Pain", "Function", "Prevention", "Other"},
			{"Within the last 3 days", "Within 6 weeks", "More than 6 weeks", "Does not apply", ""},
			{"Increase", "Decrease", "Constant", "", ""},
			{"Get rid of the pain", "Improve current condition","other","",""},
			{"1","2","3","4","5"}
	};
	
	public String getQuestion(int i){
		String question = mQuestions[i];
		return question;
	}
	
	public String getChoice1(int i){
		String choice1 = mChoices[i][0];
		return choice1;
	}
	public String getChoice2(int i){
		String choice2 = mChoices[i][1];
		return choice2;
	}
	public String getChoice3(int i){
		String choice3 = mChoices[i][2];
		return choice3;
	}
	public String getChoice4(int i){
		String choice4 = mChoices[i][3];
		return choice4;
	}
	public String getChoice5(int i){
		String choice5 = mChoices[i][4];
		return choice5;
	}
}


