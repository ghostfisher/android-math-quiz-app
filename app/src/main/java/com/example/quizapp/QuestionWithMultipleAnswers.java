package com.example.quizapp;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionWithMultipleAnswers extends QuizQuestion {

	
	protected String[] options;
	private String rightAnswers;
	private CheckBox firstOption,secondOption,thirdOption;
	private TextView questionTv;
	public QuestionWithMultipleAnswers(Activity context,String question, String options,String rightAnsweres/*splite by a coma*/) {
		this.question=question;
		this.options=options.split(",");
		this.rightAnswers=rightAnsweres;
		this.questionView=(LinearLayout) context.getLayoutInflater().inflate(R.layout.question_with_multiple_opt, null);
		//getting refrence to some vies
		this.questionTv=(TextView) questionView.findViewById(R.id.question_tv);
		this.firstOption=(CheckBox) questionView.findViewById(R.id.option1);
		this.secondOption=(CheckBox) questionView.findViewById(R.id.option2);
		this.thirdOption=(CheckBox) questionView.findViewById(R.id.option3);
		//changing on textvies and checkboxes
		questionTv.setText(question);
		firstOption.setText(this.options[0]);
		secondOption.setText(this.options[1]);
		thirdOption.setText(this.options[2]);
	}

	@Override
	public String getMessage() {
		if(getDeservedScore()==winScore)
			return "good job, you are right !";
		return "you are wrong my friend !";
	}

	@Override
	public int getDeservedScore() {
		boolean isCorrectAnswer=false;
		//checking all checkboxes
		for(int i=1;i<4;i++){
			if(((CheckBox)questionView.getChildAt(i)).isChecked()){
				if(rightAnswers.contains(String.valueOf(i-1))){
					isCorrectAnswer=true;
				}else{isCorrectAnswer=false;break;}
			}
			else{
				if(!rightAnswers.contains(String.valueOf(i-1))){
					isCorrectAnswer=true;
				}else{isCorrectAnswer=false;break;}
			}
			
		}
		
		
		
		return isCorrectAnswer?winScore:0;
	}

	@Override
	public View getView() {
			
		return this.questionView;
	}
	
	

}
