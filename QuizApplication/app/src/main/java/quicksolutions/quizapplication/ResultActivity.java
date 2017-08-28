package quicksolutions.quizapplication;

import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import quicksolutions.quizapplication.Managers.QuestionManager;
import quicksolutions.quizapplication.Managers.TestManager;
import quicksolutions.quizapplication.Models.QuestionModel;

public class ResultActivity extends AppCompatActivity {

    private TextView mSimpleResultTextView;
    private Button mDetailButton;
    private TextView mDetailResultTextView;
    private boolean toggleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mSimpleResultTextView=(TextView) findViewById(R.id.simple_result_textview);
        mDetailResultTextView=(TextView) findViewById(R.id.detail_result_text_view);
        mDetailButton=(Button) findViewById(R.id.detail_button);
        setupSimpleResult();
        setupDetailResult();
    }
    void setupSimpleResult()
    {
        StringBuilder simpleResult=new StringBuilder();


        TestManager testManager= QuestionManager.getQuestionManager(this).getmCurrentTestManager();
        simpleResult.append(getResources().getString(R.string.result_total_questions)+ " "+testManager.getTotalCount()+System.getProperty("line.separator")+System.getProperty("line.separator"));
        simpleResult.append( getResources().getString(R.string.result_correct)+ " "+ testManager.getCorrectCount()+System.getProperty("line.separator")+System.getProperty("line.separator"));
        simpleResult.append(getResources().getString(R.string.result_wrong)+  " "+testManager.getWrongCount()+System.getProperty("line.separator")+System.getProperty("line.separator"));
        mSimpleResultTextView.setText(simpleResult);
    }
    void setupDetailResult()
    {
        TestManager testManager =QuestionManager.getQuestionManager(this).getmCurrentTestManager();
        StringBuilder detailResult=new StringBuilder();
        List<QuestionModel> questions=testManager.getCurrentQuestions();
        int i=1;
        for(QuestionModel question:questions)
        {
            detailResult.append(i+". "+question.get_Question()+System.getProperty("line.separator")+System.getProperty("line.separator"));
            detailResult.append(getResources().getString(R.string.result_a)+ " "+question.get_Answer1()+System.getProperty("line.separator"));
            detailResult.append(getResources().getString(R.string.result_b)+  " " + question.get_Answer2()+System.getProperty("line.separator"));
            detailResult.append(getResources().getString(R.string.result_c)+ " " + question.get_Answer3()+System.getProperty("line.separator"));
            detailResult.append(getResources().getString(R.string.result_d) + " "+ question.get_Answer4()+System.getProperty("line.separator")+System.getProperty("line.separator"));
            detailResult.append(getResources().getString(R.string.result_your_answer)+ " "+ question.get_Ref()+System.getProperty("line.separator"));
            detailResult.append(getResources().getString(R.string.result_correct_answer)+ " "+ question.get_Correct()+System.getProperty("line.separator"));
            detailResult.append(System.getProperty("line.separator"));
            i++;
        }
        mDetailResultTextView.setText(detailResult);
    }
    public void OnDetailClick(View view)
    {
        toggleText=!toggleText;
        if(toggleText) {
            mDetailButton.setText(getResources().getString(R.string.result_hide_detail));
            mDetailResultTextView.setVisibility(View.VISIBLE);
        }else
        {
            mDetailButton.setText(getResources().getString(R.string.result_show_detail));
            mDetailResultTextView.setVisibility(View.GONE);
        }
    }
}
