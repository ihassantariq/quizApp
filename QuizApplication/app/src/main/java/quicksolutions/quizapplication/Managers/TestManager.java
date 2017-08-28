package quicksolutions.quizapplication.Managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quicksolutions.quizapplication.Models.QuestionModel;


public class TestManager {

    private List<QuestionModel> mQuestions;
    private int mTotalQuestions;
    private List<QuestionModel> mCurrentQuestions=new ArrayList<>();
    public int mCount =0;
    private QuestionModel mCurrentQuestion;

    //Getters
    public QuestionModel getCurrentQuestion() {
        return mCurrentQuestion;
    }
    public int getCount() {
        return mCount;
    }
    public List<QuestionModel> getCurrentQuestions() {
        return mCurrentQuestions;
    }
    public TestManager(List<QuestionModel> questions, int totalQuestions)
    {
        this.mQuestions=questions;
        this.mTotalQuestions=totalQuestions;
    }
    public boolean checkAnswer(String answer)
    {
        Boolean isCorrect=false;
        if(mCurrentQuestion.get_Correct().equals(answer))
        {
            mCurrentQuestions.get(mCount -1).set_isCorrect(true);
            isCorrect=true;
        }else
        {
            mCurrentQuestions.get(mCount -1).set_isCorrect(false);
            isCorrect=false;
        }
        mCurrentQuestions.get(mCount -1).set_Ref(answer);
        return isCorrect;
    }
    public int getCorrectCount()
    {
        int i=0;
        for (QuestionModel question:mCurrentQuestions)
        {
            if(question.get_isCorrect())
            {
                i++;
            }
        }
        return i;
    }
    public int getWrongCount()
    {
        int i=0;
        for (QuestionModel question:mCurrentQuestions)
        {
            if(!question.get_isCorrect())
            {
                i++;
            }
        }
        return i;
    }
    public int getTotalCount()
    {
        return mCount;
    }

    public QuestionModel getNextQuestion()
    {
        Boolean isNotAddedBefore=false;
        QuestionModel randomQuestion=null;
        if(mCount >=mTotalQuestions)
        {
            return  null;
        }
        while (!isNotAddedBefore)
        {
            Random random=new Random();
            int randomQuestionIndex=random.nextInt(mQuestions.size());
            randomQuestion=mQuestions.get(randomQuestionIndex);
            Boolean isAddedBefore=false;
            for(QuestionModel question:mCurrentQuestions)
            {
                if(randomQuestion.get_id()==question.get_id())
                {
                    isAddedBefore=true;
                    break;
                }

            }
            if(!isAddedBefore)
            {
                isNotAddedBefore=true;
            }
        }
        mCurrentQuestion=randomQuestion;
        mCurrentQuestions.add(randomQuestion);
        mCount++;
        return randomQuestion;
    }

}
