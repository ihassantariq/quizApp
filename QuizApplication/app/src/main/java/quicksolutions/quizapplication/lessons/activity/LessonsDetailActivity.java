package quicksolutions.quizapplication.lessons.activity;

import android.content.Intent;
import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Locale;

import quicksolutions.quizapplication.Helpers.DbHandler;
import quicksolutions.quizapplication.Managers.SpeechManager;
import quicksolutions.quizapplication.R;
import quicksolutions.quizapplication.lessons.adapter.RecylerViewAdapter;

public class LessonsDetailActivity extends AppCompatActivity {

    String htmlText;
    TextView txtView;
    WebView webView;
    DbHandler dbHandler;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_detail);
        initialize();
    }

    public void initialize() {

        webView = (WebView) findViewById(R.id.webview);

       /* t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });*/

        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

        dbHandler = new DbHandler(this);

        try {
            dbHandler.createDataBase();
            dbHandler.openDataBase();
            getData(getIntent().getStringExtra("name"));
            dbHandler.close();

            webView.getSettings().setJavaScriptEnabled(true);

            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
            webView.loadData(htmlText, "text/html; charset=UTF-8", null);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lessons_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.speak:
                if (!SpeechManager.getInstance().getT1().isSpeaking()) {
                    item.setTitle("Stop");
                    item.setIcon(getResources().getDrawable(R.drawable.stop));
                    speak(htmlText);
                }
                else {
                    item.setTitle("Speak");
                    item.setIcon(getResources().getDrawable(R.drawable.speak));
                    SpeechManager.getInstance().getT1().stop();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void getData(String name) {
        String selectQuery = "select desc from data where name = '" + name.toLowerCase() + "';";

        try {

            Cursor cursor = dbHandler.getReadableDatabase().rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {

                htmlText = cursor.getString(0);
                System.out.println(htmlText);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void speak(String htmlText) {
        String textToRead = html2text(htmlText);
        Log.i("TTS", textToRead);


        int dividerLimit = 3900;
        if(textToRead.length() >= dividerLimit) {
            int textLength = textToRead.length();
            ArrayList<String> texts = new ArrayList<String>();
            int count = textLength / dividerLimit + ((textLength % dividerLimit == 0) ? 0 : 1);
            int start = 0;
            int end = textToRead.indexOf(" ", dividerLimit);
            for(int i = 1; i<=count; i++) {
                texts.add(textToRead.substring(start, end));
                start = end;
                if((start + dividerLimit) < textLength) {
                    end = textToRead.indexOf(" ", start + dividerLimit);
                } else {
                    end = textLength;
                }
            }
            for(int i=0; i<texts.size(); i++) {
                SpeechManager.getInstance().getT1().speak(texts.get(i), TextToSpeech.QUEUE_ADD, null);
            }
        } else {
            SpeechManager.getInstance().getT1().speak(textToRead, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    @Override
    public void onPause() {
        if (SpeechManager.getInstance().getT1() != null) {
            SpeechManager.getInstance().getT1().stop();
        }
        super.onPause();
    }


    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

}
