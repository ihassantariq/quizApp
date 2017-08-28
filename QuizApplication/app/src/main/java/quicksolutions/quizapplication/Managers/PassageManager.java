package quicksolutions.quizapplication.Managers;

import android.content.Context;

import java.util.Random;

import quicksolutions.quizapplication.Helpers.PassageResourceHelper;
import quicksolutions.quizapplication.Models.PassageModel;
import quicksolutions.quizapplication.Models.QuestionModel;


public class PassageManager {
    private PassageResourceHelper helper;
    private Context mContext;

    public PassageManager(Context context)
    {
        mContext=context;
        helper=new PassageResourceHelper(mContext);
    }

    public PassageModel getRandomPassage()
    {
       int randomNumber=getRandomNumber(helper.getPassageList().size());
       return helper.getPassageList().get(randomNumber);
    }
    int getRandomNumber(int max)
    {
        Random random=new Random();
        final int min = 0;
        return random.nextInt(max - min) + min;
    }
    public int getCorrectCount(PassageModel passage)
    {
        int count=0;
        for (QuestionModel question:passage.getQuestions())
        {
            if(question.get_isCorrect())
            {
                count++;
            }
        }
        return count;
    }


}
