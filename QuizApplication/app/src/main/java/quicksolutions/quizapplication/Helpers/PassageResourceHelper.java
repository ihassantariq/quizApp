package quicksolutions.quizapplication.Helpers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quicksolutions.quizapplication.Models.PassageModel;
import quicksolutions.quizapplication.Models.QuestionModel;
import quicksolutions.quizapplication.R;


public class PassageResourceHelper {
    private Context mContext;
    private List<PassageModel> passageList;
    String[] mQuestionsArray;
    String[] mAnswersArray;
    String[] mExactAnswers;


    public List<PassageModel> getPassageList() {
        return passageList;
    }

    public void setPassageList(List<PassageModel> passageList) {
        this.passageList = passageList;
    }

    public PassageResourceHelper(Context context)
    {
        mContext=context;
        passageList=new ArrayList<>();
        addPassageSets(passageList);
    }
    void addPassageSets(List<PassageModel> passageList)
    {
        //first set
        PassageModel passageModel=null;
        passageModel=new PassageModel();
        passageModel.setPassage(new StringBuilder(mContext.getResources().getStringArray(R.array.passage_practice_set1)[0]));
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_passage_practice_set1);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_passage_practice_set1);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_passage_practice_set1);
        passageModel.setQuestions(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));
        passageList.add(passageModel);

        passageModel=new PassageModel();
        passageModel.setPassage(new StringBuilder(mContext.getResources().getStringArray(R.array.passage_practice_set2)[0]));
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_passage_practice_set2);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_passage_practice_set2);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_passage_practice_set2);
        passageModel.setQuestions(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        passageModel=new PassageModel();
        passageModel.setPassage(new StringBuilder(mContext.getResources().getStringArray(R.array.passage_practice_set3)[0]));
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_passage_practice_set3);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_passage_practice_set3);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_passage_practice_set3);
        passageModel.setQuestions(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));
        passageList.add(passageModel);

        passageModel=new PassageModel();
        passageModel.setPassage(new StringBuilder(mContext.getResources().getStringArray(R.array.passage_practice_set4)[0]));
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_passage_practice_set4);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_passage_practice_set4);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_passage_practice_set4);
        passageModel.setQuestions(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));
        passageList.add(passageModel);

        passageModel=new PassageModel();
        passageModel.setPassage(new StringBuilder(mContext.getResources().getStringArray(R.array.passage_practice_set5)[0]));
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_passage_practice_set5);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_passage_practice_set5);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_passage_practice_set5);
        passageModel.setQuestions(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));
        passageList.add(passageModel);

    }
    private List<QuestionModel> getQuestionsAsModeled(String[] questions, String[] options, String[] answers)
    {
        List<QuestionModel> modeledQuestions=new ArrayList<>();
        int questionsIndex=0;
        int optionsIndex=0;
        while(questionsIndex<questions.length)
        {
            if(optionsIndex >= options.length)
            {
                break;
            }
            QuestionModel question=new QuestionModel();
            question.set_Question(questions[questionsIndex]);
            question.set_Correct(answers[questionsIndex]);

            question.set_Answer1(options[optionsIndex++]);
            question.set_Answer2(options[optionsIndex++]);
            question.set_Answer3(options[optionsIndex++]);
            question.set_Answer4(options[optionsIndex++]);
            question.set_Type(getRandomType());
            modeledQuestions.add(question);
            questionsIndex++;

        }
        return modeledQuestions;
    }
    int getRandomType()
    {
        Random random=new Random();
        final int min = 1;
        final int max = 4;
        return random.nextInt(max - min) + min;
    }
}


