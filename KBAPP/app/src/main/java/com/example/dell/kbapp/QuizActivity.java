package com.example.dell.kbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitButton;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelArraylist;

    int currentPosition = 0;
    int numberOfCorrectAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionCountLabel = findViewById(R.id.noQuestion);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);

        answerEdt = findViewById(R.id.answer);
        submitButton = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);


        questionModelArraylist = new ArrayList<>();

        setUpQuestion();

        setData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();
            }
        });

    }


    public void setUpQuestion(){


        questionModelArraylist.add(new QuestionModel("The keyword used to transfer control from a function back to the calling function is ? ","return"));
        questionModelArraylist.add(new QuestionModel("In the following code, the P2 is Integer Pointer or Integer?\n" + "\n" + "typedef int *ptr;\n" + "ptr p1, p2;","64"));
        questionModelArraylist.add(new QuestionModel("In the following code what is 'P'?\n" + "\n" + "typedef char *charp;\n" + "const charp P; ","Integer pointer"));
        questionModelArraylist.add(new QuestionModel("In the following code what is 'P'?\n" + "\n" + "typedef char *charp;\n" + "const charp P; ","P is a constant"));
        questionModelArraylist.add(new QuestionModel("What is x in the following program?\n" + "\n" + "#include<stdio.h>\n" + "\n" + "int main()\n" + "{\n" + "    typedef char (*(*arrfptr[3])())[10];\n" + "    arrfptr x;\n" + "    return 0;\n" + "} ","x is an array of three function pointers"));

    }

    public void checkAnswer(){
        String answerString  = answerEdt.getText().toString().trim();




        if(answerString.equalsIgnoreCase(questionModelArraylist.get(currentPosition).getAnswer())){
            numberOfCorrectAnswer ++;
            Toast.makeText(this,"Right answer",Toast.LENGTH_SHORT).show();

            currentPosition++;
            setData();
            answerEdt.setText("");

        }
        else {

            Toast.makeText(this,"Wrong answer",Toast.LENGTH_SHORT).show();

            currentPosition++;
            setData();
            answerEdt.setText("");
        }

        int x = ((currentPosition+1) * 100) / questionModelArraylist.size();

        progressBar.setProgress(x);
    }

    public void setData(){


        questionLabel.setText(questionModelArraylist.get(currentPosition).getQuestionString());

        scoreLabel.setText("Score :" + numberOfCorrectAnswer + "/" + questionModelArraylist.size());
        questionCountLabel.setText("Question No : " + (currentPosition + 1));

    }
}
