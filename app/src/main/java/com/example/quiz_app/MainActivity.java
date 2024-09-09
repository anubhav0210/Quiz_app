package com.example.quiz_app;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalquestionTextView;
    Button ans_a,ans_b,ans_c,ans_d;
    Button sub_btn;

    int score=0;

    int totalQuestion=ques_ans.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_main);

        totalquestionTextView = findViewById(R.id.totalquestion);
        questionTextView = findViewById(R.id.question);
        ans_a = findViewById(R.id.ans_a);
        ans_b = findViewById(R.id.ans_b);
        ans_c = findViewById(R.id.ans_c);
        ans_d = findViewById(R.id.ans_d);
        sub_btn = findViewById(R.id.sub_btn);

        ans_a.setOnClickListener(this);
        ans_b.setOnClickListener(this);
        ans_c.setOnClickListener(this);
        ans_d.setOnClickListener(this);
        sub_btn.setOnClickListener(this);



        totalquestionTextView.setText("Total question :" + totalQuestion);

        loadNewQuestion();
    }
    private void loadNewQuestion(){
            if (currentQuestionIndex == totalQuestion) {
                finishQuiz();
                return;
            }
            questionTextView.setText(ques_ans.question[currentQuestionIndex]);
            ans_a.setText(ques_ans.choices[currentQuestionIndex][0]);
            ans_b.setText(ques_ans.choices[currentQuestionIndex][1]);
            ans_c.setText(ques_ans.choices[currentQuestionIndex][2]);
            ans_d.setText(ques_ans.choices[currentQuestionIndex][3]);

            selectedAnswer = "";

        }

    private void finishQuiz () {
            String passStatus;
            if (score >= totalQuestion * 0.6) {
                passStatus = "passed";

            } else {
                passStatus = "failed";
            }
            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("your score is " + score + " set of " + totalQuestion)
                    .setPositiveButton("restart", ((dialog, i) -> restartQuiz()))
                    .setCancelable(false)
                    .show();
        }

        private void restartQuiz () {
            score = 0;
            currentQuestionIndex = 0;
            loadNewQuestion();
        }
     @Override
     public void onClick (View view){
            ans_a.setBackgroundColor(Color.WHITE);
            ans_b.setBackgroundColor(Color.WHITE);
            ans_c.setBackgroundColor(Color.WHITE);
            ans_d.setBackgroundColor(Color.WHITE);

            Button clickedButton = (Button) view;
            if (clickedButton.getId() == R.id.sub_btn) {
                if (!selectedAnswer.isEmpty()) {
                    if (selectedAnswer.equals(ques_ans.correct_ans[currentQuestionIndex])) {
                        score++;
                    } else {

                    }
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else {
                }
                }else{
                    selectedAnswer = clickedButton.getText().toString();
                    clickedButton.setBackgroundColor(Color.YELLOW);
                }
            }
        }
