package quicksolutions.quizapplication;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import quicksolutions.quizapplication.Helpers.DataBaseHelper;
import quicksolutions.quizapplication.Helpers.ResourceHelper;
import quicksolutions.quizapplication.Models.QuestionModel;

public class TestActivity extends AppCompatActivity {

    private DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //initialization of Db
        InitializeDb();
        List<QuestionModel> items=myDbHelper.getAllQuestions();
        StringBuilder longText=new StringBuilder();
        longText.append("----------------Grammer Questions--------------"+System.getProperty("line.separator"));
        for(int i=0;i< items.size();i++)
        {
            longText.append("Question:"+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Question());
            longText.append(items.get(i).get_Answer1()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer2()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer3()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer4()+System.getProperty("line.separator"));
            longText.append("Answer:"+items.get(i).get_Correct()+System.getProperty("line.separator"));
            longText.append(System.getProperty("line.separator"));

        }
        ResourceHelper vocabularyHelper=new ResourceHelper(this);
        items=vocabularyHelper.getVocabularyQuestions();
        longText.append("----------------Vocabulary Questions--------------"+System.getProperty("line.separator"));
        for(int i=0;i< items.size();i++)
        {
            longText.append("Question:"+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Question()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer1()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer2()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer3()+System.getProperty("line.separator"));
            longText.append(items.get(i).get_Answer4()+System.getProperty("line.separator"));
            longText.append("Answer:"+items.get(i).get_Correct()+System.getProperty("line.separator"));
            longText.append(System.getProperty("line.separator"));

        }


        TextView textView=(TextView) findViewById(R.id.testTextView);
        textView.setText(longText);

    }
    void InitializeDb()
    {

        try {

            myDbHelper=new DataBaseHelper(this);
            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
    }
}
