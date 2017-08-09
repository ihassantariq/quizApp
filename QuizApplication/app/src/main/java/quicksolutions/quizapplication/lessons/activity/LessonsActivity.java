package quicksolutions.quizapplication.lessons.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import quicksolutions.quizapplication.Helpers.DbHandler;
import quicksolutions.quizapplication.R;
import quicksolutions.quizapplication.TestActivity;
import quicksolutions.quizapplication.lessons.adapter.RecylerViewAdapter;

/**
 * Created by zeeshan on 8/5/2017.
 */
public class LessonsActivity extends AppCompatActivity {

    RecyclerView listView;
    RecylerViewAdapter adapter;
    Intent intent;
    SharedPreferences prefs;
    String type;

    String[] grammar_arr = {"Articles","Adjectives","Adverbs","Modal Verbs","Participles","Phrasal Verbs","Simple Present","Present Continuous",
            "Present Perfect","Simple Past","Past Continuous", "Past Perfect","Simple Future","Conditional","Gerunds",
             "Active Passive"};
    String[] vocabulary_arr = {"Overview","British and American English","Spelling Rules","Common Errors","Idioms in Letters and E-mails","Idioms in Informal English","Proverbs","Opposites","Synonyms","Abbreviations"};

    String[] comprehension_arr = {"Comprehension Overview", "Reading Skills", "Comprehension Strategies"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        initialize();

    }


    public void initialize() {


        ArrayList<String> list = new ArrayList();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("type", "") != "") {
            type = prefs.getString("type","");
            if (type.equalsIgnoreCase("grammar")) {
                getSupportActionBar().setTitle("Grammar Lessons");
                for (int i = 0; i < grammar_arr.length; i++)
                    list.add(grammar_arr[i]);
            } else if (type.equalsIgnoreCase("vocabulary")) {
                getSupportActionBar().setTitle("Vocabulary Lessons");
                for (int i = 0; i < vocabulary_arr.length; i++)
                    list.add(vocabulary_arr[i]);
            } else if (type.equalsIgnoreCase("comprehension")) {
                getSupportActionBar().setTitle("Comprehension");
                for (int i = 0; i < comprehension_arr.length; i++)
                    list.add(comprehension_arr[i]);
            }
        }


        listView = (RecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecylerViewAdapter(this,list,LessonsActivity.this);
        listView.setAdapter(adapter);

    }


}
