package quicksolutions.quizapplication;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import quicksolutions.quizapplication.Helpers.DataBaseHelper;
import quicksolutions.quizapplication.Helpers.ResourceHelper;
import quicksolutions.quizapplication.Managers.QuestionManager;
import quicksolutions.quizapplication.Managers.TestManager;
import quicksolutions.quizapplication.Models.QuestionModel;

public class TestActivity extends AppCompatActivity {

    private int mTestType;
    private TestManager mTestManager;
    private TextView mQuestionTextView;
    private RadioGroup mOptionsGroup;
    private RadioButton mOption_1;
    private RadioButton mOption_2;
    private RadioButton mOption_3;
    private RadioButton mOption_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mOptionsGroup= (RadioGroup)findViewById(R.id.option_radio_group);
        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);
        mOption_1=(RadioButton) findViewById(R.id.first_option_rb);
        mOption_2=(RadioButton) findViewById(R.id.second_option_rb);
        mOption_3=(RadioButton) findViewById(R.id.third_option_rb);
        mOption_4=(RadioButton) findViewById(R.id.fourth_option_rb);

        setTestType();
        setupTitle();
        setTestManager();
        getNextQuestionAndPopulateView();
    }
    void setTestType()
    {
        int type=getIntent().getIntExtra("test_type",1);
        mTestType=type;
    }
    void setupTitle()
    {
        if(mTestType==1)
        {
            setTitle(getResources().getString(R.string.basic_test));
        }
        else if(mTestType==2)
        {
            setTitle(getResources().getString(R.string.intermediate_test));
        }
        else if(mTestType==3)
        {
            setTitle(getResources().getString(R.string.advance_test));
        }
    }
    void setTestManager()
    {
        if(mTestType==1)
        {
            mTestManager=QuestionManager.getQuestionManager(this).getBasicTest();
        }
        else if(mTestType==2)
        {
            mTestManager=QuestionManager.getQuestionManager(this).getIntermediateTest();
        }
        else if(mTestType==3)
        {
            mTestManager=QuestionManager.getQuestionManager(this).getAdvanceTest();
        }
    }
    void getNextQuestionAndPopulateView()
    {
        QuestionModel question=mTestManager.getNextQuestion();

        if(question!=null) {
            mQuestionTextView.setText(mTestManager.getCount()+". "+question.get_Question());
            mOption_1.setChecked(true);
            mOption_1.setText(question.get_Answer1());
            mOption_2.setText(question.get_Answer2());
            mOption_3.setText(question.get_Answer3());
            mOption_4.setText(question.get_Answer4());
        }else
        {
            Intent intent=new Intent(this,ResultActivity.class);
            startActivity(intent);
        }

    }
    public void OnNextClick(View view)
    {
        String answer="";
        RadioButton selectedAnswer=(RadioButton)findViewById(mOptionsGroup.getCheckedRadioButtonId());
        boolean isCorrect=mTestManager.checkAnswer(selectedAnswer.getText().toString());
        Toast toast=null;
        if(isCorrect) {
            toast = Toast.makeText(this, "Correct Answer",Toast.LENGTH_SHORT);

        }else
        {
            toast=Toast.makeText(this, "Wrong Answer",Toast.LENGTH_SHORT);
        }
        toast.show();
        getNextQuestionAndPopulateView();

    }

}

//        StringBuilder longText=new StringBuilder();
//        longText.append("----------------Baisc Questions--------------"+System.getProperty("line.separator"));
//        QuestionManager questionManager=QuestionManager.getQuestionManager(this);
//        TestManager basicTest =questionManager.getBasicTest();
//        QuestionModel question=basicTest.getNextQuestion();
//        int i=1;
//        while(question!=null)
//        {
//            longText.append(i+".Question:"+System.getProperty("line.separator"));
//            longText.append("ID: "+question.get_id()+System.getProperty("line.separator"));
//            longText.append(question.get_Question()+System.getProperty("line.separator"));
//            longText.append("a: "+question.get_Answer1()+System.getProperty("line.separator"));
//            longText.append("b: "+question.get_Answer2()+System.getProperty("line.separator"));
//            longText.append("c: "+question.get_Answer3()+System.getProperty("line.separator"));
//            longText.append("d: "+question.get_Answer4()+System.getProperty("line.separator"));
//            longText.append("Answer: "+question.get_Correct()+System.getProperty("line.separator"));
//            longText.append(System.getProperty("line.separator"));
//            question=basicTest.getNextQuestion();
//            i++;
//        }
//
//        longText.append("---------------- Advance Questions--------------"+System.getProperty("line.separator"));
//
//        TestManager advanceTest =questionManager.getAdvanceTest();
//        question=advanceTest.getNextQuestion();
//        i=1;
//        while(question!=null)
//        {
//            longText.append(i+".Question:"+System.getProperty("line.separator"));
//            longText.append(question.get_Question()+System.getProperty("line.separator"));
//            longText.append("a: "+question.get_Answer1()+System.getProperty("line.separator"));
//            longText.append("b: "+question.get_Answer2()+System.getProperty("line.separator"));
//            longText.append("c: "+question.get_Answer3()+System.getProperty("line.separator"));
//            longText.append("d: "+question.get_Answer4()+System.getProperty("line.separator"));
//            longText.append("Answer:"+question.get_Correct()+System.getProperty("line.separator"));
//            longText.append(System.getProperty("line.separator"));
//            question=advanceTest.getNextQuestion();
//            i++;
//        }
