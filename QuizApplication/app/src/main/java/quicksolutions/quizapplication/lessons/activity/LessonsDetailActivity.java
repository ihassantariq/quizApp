package quicksolutions.quizapplication.lessons.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;

import quicksolutions.quizapplication.Helpers.DbHandler;
import quicksolutions.quizapplication.R;
import quicksolutions.quizapplication.lessons.adapter.RecylerViewAdapter;

public class LessonsDetailActivity extends AppCompatActivity {

    String htmlText;
    TextView txtView;
    WebView webView;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_detail);
        initialize();
    }

    public void initialize() {

        webView = (WebView) findViewById(R.id.webview);

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


}
