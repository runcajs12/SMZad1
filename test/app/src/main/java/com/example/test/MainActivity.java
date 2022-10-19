package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ANSWER = "pl.edu.pb.wi.quiz.correctAnswer";
private static final String KEY_CURRENT_INDEX ="currentIndex";
private static final int REQUEST_CODE_HINT =0;
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("QUIZ_TAG", "Wywołałem metode kurde: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }
private Button trueButton;
private Button falseButton;
private Button nextButton;
private TextView hintButton;
private TextView questionTextView;
boolean answerWasShown;

    private Question[] questions = new Question[]{
        new Question(R.string.q1, false),
        new Question(R.string.q2, false),
        new Question(R.string.q3, false),
        new Question(R.string.q4, true),
        new Question(R.string.q5, true),
};
    private int currentIndex=0;
    private void checkAnswer(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].trueAnswer;
        int resultMessageId = 0;
        if(answerWasShown)
        {
            resultMessageId=R.string.answer_was_shown;

        }else{
        if(userAnswer== correctAnswer){
            resultMessageId =R.string.correct_answer;
        }
        else {
            resultMessageId = R.string.incorrect_answer;
        }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("QUIZ_TAG","Wywołano metodę onStart!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("QUIZ_TAG","Wywołano metodę onStop!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("QUIZ_TAG","Wywołano metodę onResume!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("QUIZ_TAG","Wywołano metodę onPause!!!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("QUIZ_TAG","Wywołano metodę onDestroy!!!");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("QUIZ_TAG","Wywołano metodę onRestart!!!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("QUIZ_TAG", "Wywołano metodę onCreate!!!");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            currentIndex=savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        hintButton = findViewById(R.id.button_podpowiedz);
        questionTextView = findViewById(R.id.question);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex+1)%questions.length;
                answerWasShown=false;
                setNextQuestion();
            }
        });
        setNextQuestion();


        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HintActivity.class);
                boolean correctAnswer = questions[currentIndex].gettrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_HINT);
                //registerForActivityResult(intent, REQUEST_CODE_HINT);
            }
        });

    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].questionId);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){return;}
        if(requestCode==REQUEST_CODE_HINT)
        {
            if(data==null){return;}
            answerWasShown = data.getBooleanExtra(HintActivity.KEY_EXTRA_ANSWER_SHOWN, false);

        }

    }


}
