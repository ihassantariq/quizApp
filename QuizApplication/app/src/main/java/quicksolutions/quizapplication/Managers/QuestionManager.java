package quicksolutions.quizapplication.Managers;


import android.content.Context;
import android.database.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import quicksolutions.quizapplication.Helpers.DataBaseHelper;
import quicksolutions.quizapplication.Helpers.ResourceHelper;
import quicksolutions.quizapplication.Models.QuestionModel;

public class QuestionManager {

    private DataBaseHelper myDbHelper;
    private Context mContext;
    private ResourceHelper mVocabularyHelper;
    private List<QuestionModel> mQuestions;
    private TestManager mBasicTest;
    private final int mBasicTestTotalQuestion=35;
    private TestManager mIntermediateTest;
    private final int mIntermediateTestTotalQuestion=35;
    private TestManager mAdvanceTest;
    private final int mAdvanceTestTotalQuestion=40;
    private static QuestionManager mQuestionManager;
    private TestManager mCurrentTestManager;


    //Getters
    public TestManager getBasicTest() {
        mCurrentTestManager=mBasicTest;
        return mBasicTest;
    }
    public TestManager getIntermediateTest() {
        mCurrentTestManager=mIntermediateTest;
        return mIntermediateTest;
    }
    public TestManager getAdvanceTest() {
        mCurrentTestManager=mAdvanceTest;
        return mAdvanceTest;
    }
    public TestManager getmCurrentTestManager() {
        return mCurrentTestManager;
    }
    private QuestionManager(Context context)
    {

        mContext=context;
        //initialization of Db
        InitializeDb();
        //initialization of Resources
        InitializeResourceHelper();
        //populating the data
        populateData();
        //reseting question managers
        resetQuestionManager();
    }
    public void resetQuestionManager()
    {
        // initializing basic Test Manager
        InitializeBasicTestManager();
        //initialize Intermediate Test Manager
        InitializeIntermediateTestManager();
        //initialize Advance Test Manager
        InitializeAdvanceTestManager();
    }
    public static QuestionManager getQuestionManager(Context mContext)
    {
        if(mQuestionManager==null)
        {
            mQuestionManager=new QuestionManager(mContext);
        }
        return  mQuestionManager;
    }
    void InitializeBasicTestManager()
    {
        List<QuestionModel> basicQuestions=new ArrayList<>();
        for(QuestionModel question:mQuestions)
        {
            if(question.get_Type()==1)
            {
                basicQuestions.add(question);
            }
        }
        mBasicTest=new TestManager(basicQuestions,mBasicTestTotalQuestion);
    }
    void InitializeIntermediateTestManager()
    {
        List<QuestionModel> intermediateQuestions=new ArrayList<>();
        for(QuestionModel question:mQuestions)
        {
            if(question.get_Type()==2)
            {
                intermediateQuestions.add(question);
            }
        }
        mIntermediateTest=new TestManager(intermediateQuestions,mIntermediateTestTotalQuestion);
    }
    void InitializeAdvanceTestManager()
    {
        List<QuestionModel> advanceQuestions=new ArrayList<>();
        for(QuestionModel question:mQuestions)
        {
            if(question.get_Type()==3)
            {
                advanceQuestions.add(question);
            }
        }
        mAdvanceTest=new TestManager(advanceQuestions,mAdvanceTestTotalQuestion);
    }
    void populateData()
    {
        mQuestions=myDbHelper.getAllQuestions();
        int databaseQuestionsLastId=0;

        if( mQuestions!=null && mQuestions.size()>0) {
            databaseQuestionsLastId=mQuestions.get(mQuestions.size()-1).get_id();
        }

        databaseQuestionsLastId++;

        List<QuestionModel> vocabularyQuestions=mVocabularyHelper.getVocabularyQuestions();
        if(vocabularyQuestions!=null && vocabularyQuestions.size()>0)
        {
            for(int i=0;i<vocabularyQuestions.size();i++)
            {
                QuestionModel vocabularyQuestion=vocabularyQuestions.get(i);
                vocabularyQuestion.set_id(databaseQuestionsLastId);
                mQuestions.add(vocabularyQuestions.get(i));
                databaseQuestionsLastId++;
            }
        }
    }

    void InitializeResourceHelper()
    {
        mVocabularyHelper=new ResourceHelper(mContext);
    }

    void InitializeDb()
    {
        try {

            myDbHelper=new DataBaseHelper(mContext);
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
