package com.example.quizapp;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public abstract class QuizQuestion {
	String question;
	protected int winScore=2;
    LinearLayout questionView;
    
 
  public abstract String getMessage();
  public abstract int getDeservedScore();
  public abstract View getView();
}
