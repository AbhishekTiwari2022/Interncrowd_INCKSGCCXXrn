package com.abhimaster.task1quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questions,questionNumber;
    private Button option1,option2,option3,option4;
    private ArrayList<QuizModel> quizModelArrayList;//Storing questions and it's answer
    Random random ; //For accessing data from arraylist
    int currentScore = 0,questionAttempted = 1,currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = findViewById(R.id.idQuestion);
        questionNumber = findViewById(R.id.idQuestionAttempt);
        option1 = findViewById(R.id.idBtnOption1);
        option2 = findViewById(R.id.idBtnOption2);
        option3 = findViewById(R.id.idBtnOption3);
        option4 = findViewById(R.id.idBtnOption4);
        quizModelArrayList = new ArrayList<>();
        random = new Random();
        getQuizQuestion(quizModelArrayList);
        currentPos = random.nextInt(quizModelArrayList.size());
        setDataToViews(currentPos);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase())){
                   currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });
    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idllscore));
        TextView idscore = bottomSheetView.findViewById(R.id.idscore);
        Button btnRestart = bottomSheetView.findViewById(R.id.btnRestart);
        idscore.setText("Your score is \n"+currentScore+"/10");
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
                questionAttempted=1;
                currentScore=0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    private void setDataToViews(int currentPos) {
        questionNumber.setText("Question Attempted : "+questionAttempted+ "/10");

        if(questionAttempted==10){
            showBottomSheet();
        }
        else
        {
            questions.setText(quizModelArrayList.get(currentPos).getQuestion());
            option1.setText(quizModelArrayList.get(currentPos).getOption1());
            option2.setText(quizModelArrayList.get(currentPos).getOption2());
            option3.setText(quizModelArrayList.get(currentPos).getOption3());
            option4.setText(quizModelArrayList.get(currentPos).getOption4());
        }
    }

    private void getQuizQuestion(ArrayList<QuizModel> quizModelArrayList) {
        quizModelArrayList.add(new QuizModel("Programming language COBOL works best for use in?","SIEMENS APPLCATION","STUDENT APPLICATION","SOCIAL APPLICATION","COMMERCIAL APPLICATION","COMMERCIAL APPLICATION"));

        quizModelArrayList.add(new QuizModel("Android is?","an operating system","a web browser","a web server","None of the above","an operating system"));

        quizModelArrayList.add(new QuizModel(" Under which of the following Android is licensed?","OSS","Sourceforge","Apache/MIT","None of the above","Apache/MIT"));

        quizModelArrayList.add(new QuizModel(" For which of the following Android is mainly developed?","Servers","Desktops","Laptops","Mobile devices","Mobile devices"));
    }
}