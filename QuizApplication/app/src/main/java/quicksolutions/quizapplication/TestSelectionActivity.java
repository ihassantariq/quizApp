package quicksolutions.quizapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import quicksolutions.quizapplication.Managers.QuestionManager;

public class TestSelectionActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);
        setContext();

        Button basicButton=(Button)findViewById(R.id.basic_button);
        basicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QuestionManager.getQuestionManager(mContext).resetQuestionManager();
                Intent intent=new Intent(v.getContext(),TestActivity.class);
                intent.putExtra("test_type",1);
                startActivity(intent);
            }
        });

        Button intermediateButton=(Button)findViewById(R.id.intermediate_button);
        intermediateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QuestionManager.getQuestionManager(mContext).resetQuestionManager();
                Intent intent= new Intent(v.getContext(),TestActivity.class);
                intent.putExtra("test_type",2);
                startActivity(intent);
            }
        });

        Button advanceButton=(Button)findViewById(R.id.advance_button);
        advanceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QuestionManager.getQuestionManager(mContext).resetQuestionManager();
                Intent intent=new Intent(v.getContext(),TestActivity.class);
                intent.putExtra("test_type",3);
                startActivity(intent);
            }
        });
    }
    void setContext()
    {
        mContext=this;
    }
}
