package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
public static final String KEY_EXTRA_ANSWER_SHOWN="pb.edu.pl.wi.quiz.answerShown";
private boolean correctAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        correctAnswer=getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);
        Button showCorrectAnswerButton;
        TextView answerTextView;
        answerTextView = findViewById(R.id.answer_text_view);
        showCorrectAnswerButton = findViewById(R.id.answer);
        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = correctAnswer ? R.string.button_true:R.string.button_false;
            answerTextView.setText(answer);
            setAnswerShownResult(true);
            }
        });

    }
    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }


}