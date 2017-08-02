package quicksolutions.quizapplication.Helpers;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import quicksolutions.quizapplication.Models.QuestionModel;
import quicksolutions.quizapplication.R;

/**
 * Created by Hassan on 8/2/17.
 */

public class ResourceHelper {
    private Context mContext;
    String[] mQuestionsArray;
    String[] mAnswersArray;
    String[] mExactAnswers;

    public ResourceHelper(Context context)
    {
        mContext=context;
    }
    public List<QuestionModel> getVocabularyQuestions()
    {
        List<QuestionModel> questionsList=new ArrayList<>();

        //first set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set1);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set1);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set1);
        questionsList.addAll(getQuestionAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //second set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set2);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set2);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set2);
        questionsList.addAll(getQuestionAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //third set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set3);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set3);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set3);
        questionsList.addAll(getQuestionAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));


        //fourth set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set4);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set4);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set4);
        questionsList.addAll(getQuestionAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //five set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set5);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set5);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set5);
        questionsList.addAll(getQuestionAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        return questionsList;
    }

    private List<QuestionModel> getQuestionAsModeled( String[] questions, String[] options,String[] answers)
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
            modeledQuestions.add(question);
            questionsIndex++;

        }
        return modeledQuestions;
    }
}
