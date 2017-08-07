package quicksolutions.quizapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    public void saveLessonType(String type) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putString("type", type);
        editor.commit();

    }

}
