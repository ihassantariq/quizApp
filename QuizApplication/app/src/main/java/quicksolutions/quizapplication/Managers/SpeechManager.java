package quicksolutions.quizapplication.Managers;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by zawan on 8/7/17.
 */

public class SpeechManager {

    public TextToSpeech getT1() {
        return t1;
    }

    private TextToSpeech t1;
    private Context appContext;

    private SpeechManager(){}

    public void init(Context context){
        if(appContext == null){
            appContext = context;
        }
    }

    private Context getContext(){
        return appContext;
    }

    public static Context get(){
        return getInstance().getContext();
    }

    private static SpeechManager instance;

    public static SpeechManager getInstance(){
        return instance == null ?
                (instance = new SpeechManager()):
                instance;
    }

    public void initSpeech(){

        t1=new TextToSpeech(getInstance().getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
    }
}