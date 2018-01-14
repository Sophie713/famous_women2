package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String QUESTIONS_ARRAY_KEY = "questionsArrayKey";
    private final static String CURRENT_QUESTION = "currentQuestion";
    private final static String WRONG_ANSWERS = "wrongQuestions";
    float score = 0;
    ArrayList<QuizQuestion> allQuestions = new ArrayList<QuizQuestion>();    // ArrayList of all quiz questions
    ArrayList<QuizQuestion> questions;
    int currentQuestion;
    ArrayList<Integer> wrongAnswers = new ArrayList<Integer>();
    HashMap<Integer, RadioGroup> rgHmap;
    HashMap<Integer, TextView> questionHmap;
    HashMap<Integer, TextView> submitHmap;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // Temporary -> use array adapt or show 5 questions by default?

        // this is for the arrow in the menu bar to go back to parent activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Find views
        // Question 1
        final TextView question1 = (TextView) findViewById(R.id.tv_question1);
        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg_question1);
        final TextView ans1_1 = (TextView) findViewById(R.id.rb_answer1_1);
        final TextView ans1_2 = (TextView) findViewById(R.id.rb_answer1_2);
        final TextView ans1_3 = (TextView) findViewById(R.id.rb_answer1_3);
        final TextView submit1 = (TextView) findViewById(R.id.tv_submit_1);
        // Question 2
        final TextView question2 = (TextView) findViewById(R.id.tv_question2);
        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.rg_question2);
        final TextView ans2_1 = (TextView) findViewById(R.id.rb_answer2_1);
        final TextView ans2_2 = (TextView) findViewById(R.id.rb_answer2_2);
        final TextView ans2_3 = (TextView) findViewById(R.id.rb_answer2_3);
        final TextView submit2 = (TextView) findViewById(R.id.tv_submit_2);
        // Question 3
        final TextView question3 = (TextView) findViewById(R.id.tv_question3);
        final RadioGroup rg3 = (RadioGroup) findViewById(R.id.rg_question3);
        final TextView ans3_1 = (TextView) findViewById(R.id.rb_answer3_1);
        final TextView ans3_2 = (TextView) findViewById(R.id.rb_answer3_2);
        final TextView ans3_3 = (TextView) findViewById(R.id.rb_answer3_3);
        final TextView submit3 = (TextView) findViewById(R.id.tv_submit_3);
        // Question 4
        final TextView question4 = (TextView) findViewById(R.id.tv_question4);
        final RadioGroup rg4 = (RadioGroup) findViewById(R.id.rg_question4);
        final TextView ans4_1 = (TextView) findViewById(R.id.rb_answer4_1);
        final TextView ans4_2 = (TextView) findViewById(R.id.rb_answer4_2);
        final TextView ans4_3 = (TextView) findViewById(R.id.rb_answer4_3);
        final TextView submit4 = (TextView) findViewById(R.id.tv_submit_4);
        // Question 5
        final TextView question5 = (TextView) findViewById(R.id.tv_question5);
        final RadioGroup rg5 = (RadioGroup) findViewById(R.id.rg_question5);
        final TextView ans5_1 = (TextView) findViewById(R.id.rb_answer5_1);
        final TextView ans5_2 = (TextView) findViewById(R.id.rb_answer5_2);
        final TextView ans5_3 = (TextView) findViewById(R.id.rb_answer5_3);
        final TextView submit5 = (TextView) findViewById(R.id.tv_submit_5);

        // Result
        result = (TextView) findViewById(R.id.tv_result);

        rgHmap = new HashMap<Integer, RadioGroup>();
        rgHmap.put(0, rg1);
        rgHmap.put(1, rg2);
        rgHmap.put(2, rg3);
        rgHmap.put(3, rg4);
        rgHmap.put(4, rg5);

        questionHmap = new HashMap<Integer, TextView>();
        questionHmap.put(0,question1);
        questionHmap.put(1,question2);
        questionHmap.put(2,question3);
        questionHmap.put(3,question4);
        questionHmap.put(4,question5);

        submitHmap = new HashMap<Integer, TextView>();
        submitHmap.put(0,submit1);
        submitHmap.put(1,submit2);
        submitHmap.put(2,submit3);
        submitHmap.put(3,submit4);
        submitHmap.put(4,submit5);

        if (savedInstanceState == null) {
            allQuestions.add(new QuizQuestion(R.string.question1, R.string.answer1_1, R.string.answer1_2, R.string.answer1_3, 2));
            allQuestions.add(new QuizQuestion(R.string.question2, R.string.answer2_1, R.string.answer2_2, R.string.answer2_3, 3));
            allQuestions.add(new QuizQuestion(R.string.question3, R.string.answer3_1, R.string.answer3_2, R.string.answer3_3, 2));
            allQuestions.add(new QuizQuestion(R.string.question4, R.string.answer4_1, R.string.answer4_2, R.string.answer4_3, 1));
            allQuestions.add(new QuizQuestion(R.string.question5, R.string.answer5_1, R.string.answer5_2, R.string.answer5_3, 2));
            allQuestions.add(new QuizQuestion(R.string.question6, R.string.answer6_1, R.string.answer6_2, R.string.answer6_3, 1));
            allQuestions.add(new QuizQuestion(R.string.question7, R.string.answer7_1, R.string.answer7_2, R.string.answer7_3, 2 ));
            allQuestions.add(new QuizQuestion(R.string.question8, R.string.answer8_1, R.string.answer8_2, R.string.answer8_3, 3));
            allQuestions.add(new QuizQuestion(R.string.question9, R.string.answer9_1, R.string.answer9_2, R.string.answer9_3, 3));
            // Randomized questions
            Collections.shuffle(allQuestions);
            questions = new ArrayList<QuizQuestion>(allQuestions.subList(0,5));
            currentQuestion = 0;
            question2.setVisibility(View.INVISIBLE);
            rg2.setVisibility(View.INVISIBLE);
            submit2.setVisibility(View.INVISIBLE);
            question3.setVisibility(View.INVISIBLE);
            rg3.setVisibility(View.INVISIBLE);
            submit3.setVisibility(View.INVISIBLE);
            question4.setVisibility(View.INVISIBLE);
            rg4.setVisibility(View.INVISIBLE);
            submit4.setVisibility(View.INVISIBLE);
            question5.setVisibility(View.INVISIBLE);
            rg5.setVisibility(View.INVISIBLE);
            submit5.setVisibility(View.INVISIBLE);
            //int index = new Random().nextInt(questions.size());
            //QuizQuestion currQuestion = questions.remove(index);
        } else {
            questions = savedInstanceState.getParcelableArrayList(QUESTIONS_ARRAY_KEY);
            currentQuestion = savedInstanceState.getInt(CURRENT_QUESTION);
            wrongAnswers = (ArrayList<Integer>)savedInstanceState.getSerializable(WRONG_ANSWERS);
            for(int j = 0; j < currentQuestion ; j++){
                for (int i = 0; i < rgHmap.get(j).getChildCount(); i++) {
                    rgHmap.get(j).getChildAt(i).setEnabled(false);
                }
                correctAnswerCheck(rgHmap.get(j), j);
                if(wrongAnswers.get(j) != 0){
                    RadioButton selectedAnswer = findViewById(wrongAnswers.get(j));
                    selectedAnswer.setButtonDrawable(R.drawable.ic_cancel);
                }

            }
            for(int i = currentQuestion+1 ; i<5 ; i++){
                rgHmap.get(i).setVisibility(View.INVISIBLE);
                questionHmap.get(i).setVisibility(View.INVISIBLE);
                submitHmap.get(i).setVisibility(View.INVISIBLE);
            }
        }

        // Display questions and answers
        question1.setText(questions.get(0).getQuestion());
        ans1_1.setText(questions.get(0).getAnswer1());
        ans1_2.setText(questions.get(0).getAnswer2());
        ans1_3.setText(questions.get(0).getAnswer3());

        question2.setText(questions.get(1).getQuestion());
        ans2_1.setText(questions.get(1).getAnswer1());
        ans2_2.setText(questions.get(1).getAnswer2());
        ans2_3.setText(questions.get(1).getAnswer3());

        question3.setText(questions.get(2).getQuestion());
        ans3_1.setText(questions.get(2).getAnswer1());
        ans3_2.setText(questions.get(2).getAnswer2());
        ans3_3.setText(questions.get(2).getAnswer3());

        question4.setText(questions.get(3).getQuestion());
        ans4_1.setText(questions.get(3).getAnswer1());
        ans4_2.setText(questions.get(3).getAnswer2());
        ans4_3.setText(questions.get(3).getAnswer3());

        question5.setText(questions.get(4).getQuestion());
        ans5_1.setText(questions.get(4).getAnswer1());
        ans5_2.setText(questions.get(4).getAnswer2());
        ans5_3.setText(questions.get(4).getAnswer3());

        // Set a click listeners on submit buttons
        if (submit1 != null) submit1.setOnClickListener(this);
        if (submit2 != null) submit2.setOnClickListener(this);
        if (submit3 != null) submit3.setOnClickListener(this);
        if (submit4 != null) submit4.setOnClickListener(this);
        if (submit5 != null) submit5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.tv_submit_1:{
                submit(0);
                break;
            }
            case R.id.tv_submit_2:{
                submit(1);
                break;
            }
            case R.id.tv_submit_3:{
                submit(2);
                break;
            }
            case R.id.tv_submit_4: {
                submit(3);
                break;
            }
            case R.id.tv_submit_5: {
                submit(4);
                score = score / 5 * 100;
                result.setText("Your score is: " + (int) score + "%");
                break;
            }
        }
    }

    public void submit(int currentQuestion) {
        if (rgHmap.get(currentQuestion).getCheckedRadioButtonId() == -1) {
            Toast.makeText(getBaseContext(), "Select answer!", Toast.LENGTH_SHORT).show();
        } else {
            int selectedRadioButtonID = rgHmap.get(currentQuestion).indexOfChild(findViewById(rgHmap.get(currentQuestion).getCheckedRadioButtonId()));
            correctAnswerCheck(rgHmap.get(currentQuestion), currentQuestion);
            if (questions.get(currentQuestion).getCorrectAnswer() != selectedRadioButtonID) {
                incorrectAnswerCheck(rgHmap.get(currentQuestion));
                wrongAnswers.add(rgHmap.get(currentQuestion).getCheckedRadioButtonId());
            } else {
                score++;
                wrongAnswers.add(currentQuestion);
            }
            for (int i = 0; i < rgHmap.get(currentQuestion).getChildCount(); i++) {
                rgHmap.get(currentQuestion).getChildAt(i).setEnabled(false);
            }
            currentQuestion++;
            if(currentQuestion<questions.size()){
                questionHmap.get(currentQuestion).setVisibility(View.VISIBLE);
                rgHmap.get(currentQuestion).setVisibility(View.VISIBLE);
                submitHmap.get(currentQuestion).setVisibility(View.VISIBLE);
            }
        }
    }

    public void correctAnswerCheck(RadioGroup rg, int numberOfQuestion) {
        RadioButton correctAnswer = (RadioButton) findViewById(rg.getChildAt(questions.get(numberOfQuestion).getCorrectAnswer()).getId());
        correctAnswer.setButtonDrawable(R.drawable.ic_check);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) correctAnswer.getLayoutParams();
        params1.setMargins(16, 0, 0, 0);
        correctAnswer.setLayoutParams(params1);
        correctAnswer.setPadding(16, 0, 0, 0);

    }

    public void incorrectAnswerCheck(RadioGroup rg) {

        RadioButton selectedAnswer = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        selectedAnswer.setButtonDrawable(R.drawable.ic_cancel);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) selectedAnswer.getLayoutParams();
        params.setMargins(16, 0, 0, 0);
        selectedAnswer.setLayoutParams(params);
        selectedAnswer.setPadding(16, 0, 0, 0);

    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save questions ArrayList when changing state
        outState.putParcelableArrayList(QUESTIONS_ARRAY_KEY, questions);
        outState.putInt(CURRENT_QUESTION, currentQuestion);
        outState.putSerializable(WRONG_ANSWERS, wrongAnswers);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }
}
