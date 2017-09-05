package quicksolutions.quizapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import quicksolutions.quizapplication.Managers.SpeechManager;
import quicksolutions.quizapplication.lessons.activity.LessonsActivity;

import static java.security.AccessController.getContext;

public class WelcomeActivity extends AppCompatActivity {

    Intent intent;
    Button grammarLessons, vocabularyLessons, comprehensionLessons;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupTitle();
        initialize();
        setClickListeners();

        Button quizButton = (Button) findViewById(R.id.quizButton);
        quizButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TestSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
    void setupTitle()
    {
        setTitle(getResources().getString(R.string.welcome_page_title));
    }

    public void initialize() {

        grammarLessons = (Button) findViewById(R.id.grammarLessons);
        vocabularyLessons = (Button) findViewById(R.id.vocabularyButton);
        comprehensionLessons = (Button) findViewById(R.id.comprehensionLessons);
    }


    public void setClickListeners() {
        grammarLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WelcomeActivity.this, LessonsActivity.class);
                saveLessonType("grammar");
                startActivity(intent);
            }
        });

        vocabularyLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WelcomeActivity.this, LessonsActivity.class);
                saveLessonType("vocabulary");
                startActivity(intent);
            }
        });

        comprehensionLessons.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent = new Intent(WelcomeActivity.this, LessonsActivity.class);
                saveLessonType("comprehension");
                startActivity(intent);
            }
        });
    }

    public void saveLessonType(String type) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putString("type", type);
        editor.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                startAboutActivity();
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    void startAboutActivity()
    {
        Intent intent=new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

}
