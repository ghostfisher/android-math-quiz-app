package com.example.quizapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

//question with options and one right answer !!!
public class QuestionWithOptiAndOneAnswer extends QuizQuestion {
	String[] options;
	String rightAnswer;
	RadioGroup group;
	TextView questionTv;
	RadioButton option1, option2, option3;

	public QuestionWithOptiAndOneAnswer(Activity context, String question, String opts, String rightAnswer) {
		// caving data
		this.question = question;
		this.rightAnswer = rightAnswer;
		this.options = opts.split(",");
		// setting up views
		this.questionView = (LinearLayout) context.getLayoutInflater().inflate(R.layout.question_with_op_one_answer,
				questionView);
		this.group = (RadioGroup) questionView.findViewById(R.id.rGroup1);
		option1 = (RadioButton) questionView.findViewById(R.id.r1);
		option2 = (RadioButton) questionView.findViewById(R.id.r2);
		option3 = (RadioButton) questionView.findViewById(R.id.r3);
		;

		questionTv = (TextView) questionView.findViewById(R.id.question_tv);
		// setting up text on views
		questionTv.setText(question);
		option1.setText(options[0]);
		option2.setText(options[1]);
		option3.setText(options[2]);
	}

	@Override
	public String getMessage() {

		return "your question is " + (isAnswerRight() ? "right !" : "wrong !");
	}

	@Override
	public int getDeservedScore() {
		return (isAnswerRight() ? winScore : 0);
	}

	@Override
	public View getView() {

		return questionView;
	}

	private boolean isAnswerRight() {
		int id = group.getCheckedRadioButtonId();
		if (((RadioButton) group.findViewById(id)) == group.getChildAt(Integer.valueOf(rightAnswer)))
			return true;
		return false;

	}
}
