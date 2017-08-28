package quicksolutions.quizapplication.Helpers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quicksolutions.quizapplication.Models.QuestionModel;
import quicksolutions.quizapplication.R;


public class ResourceHelper {
    private Context mContext;
    String[] mQuestionsArray;
    String[] mAnswersArray;
    String[] mExactAnswers;
    public ResourceHelper(Context context)
    {
        mContext=context;
    }

    void addVocabularySets(List<QuestionModel> questionsList)
    {
        //vocabulary sets
        //first set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set1);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set1);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set1);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //second set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set2);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set2);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set2);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //third set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set3);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set3);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set3);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));


        //fourth set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set4);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set4);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set4);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //fifth set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_english_vocab_set5);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_english_vocab_set5);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_english_vocab_set5);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));
    }
    void addSpellingPracticeSets(List<QuestionModel> questionsList)
    {
        //Spelling practice
        //first set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set1);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set1);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set1);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //second set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set2);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set2);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set2);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //third set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set3);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set3);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set3);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));


        //fourth set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set4);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set4);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set4);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //fifth set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set5);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set5);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set5);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //sixth set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set6);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set6);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set6);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //seventh set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set7);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set7);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set7);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //8th set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set8);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set8);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set8);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //9th set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set9);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set9);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set9);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //10th set
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_spelling_practice_set10);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_spelling_practice_set10);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_spelling_practice_set10);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

    }
    void addWordSubstitutionPracticeSets(List<QuestionModel> questionsList)
    {
        //word substitution
        //1st
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set1);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set1);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set1);

        //2nd
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set2);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set2);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set2);

        //3rd
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set3);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set3);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set3);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //4rth
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set4);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set4);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set4);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //5rth
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set5);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set5);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set5);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //6rth
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set6);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set6);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set6);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //7th
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set7);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set7);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set7);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //8th
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set8);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set8);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set8);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //9th
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set9);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set9);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set9);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));

        //10th
        mQuestionsArray = mContext.getResources().getStringArray(R.array.question_one_word_susbtitution_practice_set10);
        mAnswersArray = mContext.getResources().getStringArray(R.array.answer_one_word_susbtitution_practice_set10);
        mExactAnswers = mContext.getResources().getStringArray(R.array.exact_answer_one_word_susbtitution_practice_set10);
        questionsList.addAll(getQuestionsAsModeled(mQuestionsArray,mAnswersArray,mExactAnswers));
    }

    public List<QuestionModel> getVocabularyQuestions()
    {
        List<QuestionModel> questionsList=new ArrayList<>();

        addVocabularySets(questionsList);
        addSpellingPracticeSets(questionsList);
        addWordSubstitutionPracticeSets(questionsList);

        return questionsList;
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
