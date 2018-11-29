package com.example.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	//tree types of questions
	private final int QUESTION_WITH_TEXT_ANSWER=0;
	private final int QUESTION_WITH_OPTIONS_MULTIPLE_SOLLUTIONS=1;
	private int curruntQuestionWithTAnsewerIndex=0;
	private int curruntQuestionWithOptionsIndex=0;
	private int curruntScore=0;
	private int curruntQuestionIndex=0;
	/*TODO: don't forget to change nbr of questions, it can cause some trouble*/
	private final int nbrOfQuestions=6;
	private LinearLayout questionContainner;
	private Button submitBtn;
	private QuizQuestion question;
	private TextView questionNbrTv,curruntScoreTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getting refrence to views
		questionContainner=(LinearLayout) findViewById(R.id.questionCon);
		submitBtn=(Button) findViewById(R.id.submitBtn);
		questionNbrTv=(TextView) findViewById(R.id.questionNbrTv);
		curruntScoreTv=(TextView) findViewById(R.id.curruntScoreTv);
		//setting click listennsrs
		submitBtn.setOnClickListener(this);	
		//setting the question
		this.question=getQuestion();
		questionContainner.addView(question.getView());
		updateQCount();
	}
	
	private int getRandQuestionType(){
		//to make the chances equal
		double n=Math.random();
		return n>0.5d?1:0;
		
	}
	
	

	@Override
	public void onClick(View v) {
    int id=v.getId();
    if(id==R.id.submitBtn){
    	if(curruntQuestionIndex<nbrOfQuestions){
    	Toast.makeText(this, question.getMessage(), Toast.LENGTH_SHORT).show();;
    	curruntScore+=question.getDeservedScore();
    	addToScore(question.getDeservedScore());
    	updateQCount();
    	nextQuestion();
    	}
    	else{
    		Toast.makeText(this, "congratulations you scored "+curruntScore+" points !\n see you next time !", Toast.LENGTH_LONG).show();
    		finish();
    	}
    	
    }
    	 
	}
	private void updateQCount(){
		questionNbrTv.setText((curruntQuestionIndex+1)+"/"+nbrOfQuestions);
	}
	
	
	private void addToScore(int score){
		curruntScore+=score;
		curruntScoreTv.setText(curruntScore+" points");
	}
	private void nextQuestion(){
		question=getQuestion();
		questionContainner.removeAllViews();
		questionContainner.addView(question.getView());
	} 	
	private QuizQuestion getQuestion(){
		QuizQuestion q=null;
		System.out.println("qwop:"+curruntQuestionWithOptionsIndex+"  qwtext"+curruntQuestionWithTAnsewerIndex+"  allq"+curruntQuestionIndex);
		//if we steal have questiosn
		if(curruntQuestionIndex<nbrOfQuestions){
			//if none of the question types is over
			if(curruntQuestionWithOptionsIndex<getResources().getStringArray(R.array.q_with_multiple_op_questions).length&&curruntQuestionWithTAnsewerIndex<getResources().getStringArray(R.array.q_with_text_answer).length)
			{
				
					int type=getRandQuestionType();
					System.out.println("type is "+type);
					if(type==QUESTION_WITH_TEXT_ANSWER){
								q=getQuestionWithTextAnswer();
						}
					else{
						//if a question has one possible answer
						if(geOptionsQNbr()>1)
						q=getQuestionWithMultipleAnswers();
						//if only one answer is possible
						else q=getQuestionWithOneAnswer();
						 	
					}
			}
			else if(curruntQuestionWithOptionsIndex<getResources().getStringArray(R.array.q_with_multiple_op_questions).length){
				//if a question has one possible answer
				if(geOptionsQNbr()>1)
				q=getQuestionWithMultipleAnswers();
				//if only one answer is possible
				else q=getQuestionWithOneAnswer();
				
			}else{
				q=getQuestionWithTextAnswer();
			}
		}else Toast.makeText(this, "if this line is executed then fix it!",Toast.LENGTH_SHORT).show();;
		return q;
	}
	private QuizQuestion getQuestionWithTextAnswer(){
		QuizQuestion q=null;
		q= new QuestionWithATextAnswer(this, getResources().getStringArray(R.array.q_with_text_answer)[curruntQuestionWithTAnsewerIndex],
				getResources().getStringArray(R.array.q_with_text_answer_answers)[curruntQuestionWithTAnsewerIndex]);
			curruntQuestionWithTAnsewerIndex++;
			curruntQuestionIndex++;

		return q;	
	}
	private QuizQuestion getQuestionWithMultipleAnswers(){
		QuizQuestion q=null;

		q= new QuestionWithMultipleAnswers(this, 
				getResources().getStringArray(R.array.q_with_multiple_op_questions)[curruntQuestionWithOptionsIndex],
				getResources().getStringArray(R.array.q_with_multiple_op_options)[curruntQuestionWithOptionsIndex], 
				getResources().getStringArray(R.array.q_with_multiple_op_rightAnswers)[curruntQuestionWithOptionsIndex]);
	 	curruntQuestionWithOptionsIndex++;
		curruntQuestionIndex++;
	
		return q;
	}
	private QuizQuestion getQuestionWithOneAnswer(){
		QuizQuestion q=null;

		q=new QuestionWithOptiAndOneAnswer(this,
				getResources().getStringArray(R.array.q_with_multiple_op_questions)[curruntQuestionWithOptionsIndex],
				getResources().getStringArray(R.array.q_with_multiple_op_options)[curruntQuestionWithOptionsIndex],
				getResources().getStringArray(R.array.q_with_multiple_op_rightAnswers)[curruntQuestionWithOptionsIndex]);
	 	curruntQuestionWithOptionsIndex++;
		curruntQuestionIndex++;
	
		return q;
	}
	
	//to get the number of correct answers for the currunt question
	private int geOptionsQNbr(){
		
		return getResources().getStringArray(R.array.q_with_multiple_op_rightAnswers)[curruntQuestionWithOptionsIndex].split(",").length;
	}
}
