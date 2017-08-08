package quicksolutions.quizapplication.application;

import android.app.Application;

import quicksolutions.quizapplication.Managers.SpeechManager;

/**
 * Created by zawan on 8/8/17.
 */

public class Initializer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SpeechManager.getInstance().init(getApplicationContext());
        SpeechManager.getInstance().initSpeech();
    }
}
