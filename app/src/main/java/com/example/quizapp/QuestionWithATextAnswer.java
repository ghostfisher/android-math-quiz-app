package com.example.quizapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionWithATextAnswer extends QuizQuestion{
    String rightAnswer,givenAnswer;
    private final int winScore=2;
    LinearLayout questionView;
    private TextView questionTv;
    private EditText answer_editText;
	public QuestionWithATextAnswer(Activity context,String question,String rightAnswer) {
		
		this.question=question;
		this.rightAnswer=rightAnswer;
		this.questionView=(LinearLayout) context.getLayoutInflater().inflate(R.layout.question_with_text_answer, null);
		//getting refrence to some views
		questionTv=(TextView) questionView.findViewById(R.id.question_tv);
		answer_editText=(EditText) questionView.findViewById(R.id.answer_text_field);
		//setting text
		questionTv.setText(question);
		
	}
	
	
	@Override
	public String getMessage() {
		givenAnswer=answer_editText.getText().toString();
		Log.v("TextQuestion","givenAnsswer :"+givenAnswer+" right one: "+rightAnswer);
		//if no answer is given
		if(givenAnswer==null ||givenAnswer=="")
			return "no answer given yet";
		//and if a question is given then we see if it's correct
		else {return "your answer is "+(givenAnswer.equals(rightAnswer)? "right":"wrong");}
		
	}

	@Override
	public int getDeservedScore() {
		givenAnswer=answer_editText.getText().toString();
		if(rightAnswer.contentEquals(givenAnswer)) return winScore;
		else return 0;
	}
	public View getView(){
		return this.questionView;
	}
}
