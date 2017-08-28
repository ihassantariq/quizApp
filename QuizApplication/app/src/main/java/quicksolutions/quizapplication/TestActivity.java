package quicksolutions.quizapplication;

import android.content.Intent;
import android.database.SQLException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.OrientationHelper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import quicksolutions.quizapplication.Helpers.DataBaseHelper;
import quicksolutions.quizapplication.Helpers.ResourceHelper;
import quicksolutions.quizapplication.Managers.PassageManager;
import quicksolutions.quizapplication.Managers.QuestionManager;
import quicksolutions.quizapplication.Managers.TestManager;
import quicksolutions.quizapplication.Models.PassageModel;
import quicksolutions.quizapplication.Models.QuestionModel;

public class TestActivity extends AppCompatActivity {

    private int mTestType;
    private TestManager mTestManager;
    private ScrollView mParentScrollView;
    private LinearLayout mSimpleQuestion;
    private TextView mQuestionTextView;
    private RadioGroup mOptionsGroup;
    private RadioButton mOption_1;
    private RadioButton mOption_2;
    private RadioButton mOption_3;
    private RadioButton mOption_4;
    private PassageManager mPassageManager;
    private int mPassagePlacement;
    private int[] questionRadioGroupIds;
    private PassageModel mCurrentPassage;
    private boolean skipToastForPassage = false;
    private LinearLayout passageLayout;
    private Button mNextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mParentScrollView = (ScrollView) findViewById(R.id.parent_scroll_view);
        mSimpleQuestion = (LinearLayout) findViewById(R.id.simple_question);
        mOptionsGroup = (RadioGroup) findViewById(R.id.option_radio_group);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mOption_1 = (RadioButton) findViewById(R.id.first_option_rb);
        mOption_2 = (RadioButton) findViewById(R.id.second_option_rb);
        mOption_3 = (RadioButton) findViewById(R.id.third_option_rb);
        mOption_4 = (RadioButton) findViewById(R.id.fourth_option_rb);
        mNextButton = (Button) findViewById(R.id.next_button);

        //  mPassagePlacement=getPassageRandomPlacement();
        mPassagePlacement = 1;

        setTestType();
        setupTitle();
        setTestManager();
        getNextQuestionAndPopulateView();
        initializePassageManager();
    }

    void initializePassageManager() {
        mPassageManager = new PassageManager(this);
    }

    void setTestType() {
        int type = getIntent().getIntExtra("test_type", 1);
        mTestType = type;
    }

    void setupTitle() {
        if (mTestType == 1) {
            setTitle(getResources().getString(R.string.basic_test));
        } else if (mTestType == 2) {
            setTitle(getResources().getString(R.string.intermediate_test));
        } else if (mTestType == 3) {
            setTitle(getResources().getString(R.string.advance_test));
        }
    }

    void setTestManager() {
        if (mTestType == 1) {
            mTestManager = QuestionManager.getQuestionManager(this).getBasicTest();
        } else if (mTestType == 2) {
            mTestManager = QuestionManager.getQuestionManager(this).getIntermediateTest();
        } else if (mTestType == 3) {
            mTestManager = QuestionManager.getQuestionManager(this).getAdvanceTest();
        }
    }

    void getNextQuestionAndPopulateView() {
        QuestionModel question = mTestManager.getNextQuestion();
        if (question != null) {
            mQuestionTextView.setText(mTestManager.getCount() + ". " + question.get_Question());
            mOption_1.setChecked(true);
            mOption_1.setText(question.get_Answer1());
            mOption_2.setText(question.get_Answer2());
            mOption_3.setText(question.get_Answer3());
            mOption_4.setText(question.get_Answer4());
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }

    }

    int getPassageRandomPlacement() {
        Random random = new Random();
        final int min = 10;
        final int max = 20;
        return random.nextInt(max - min) + min;
    }

    public void onNextClick(View view) {
        String answer = "";

        //only show toast if it is simple question
        if (!skipToastForPassage) {

            RadioButton selectedAnswer = (RadioButton) findViewById(mOptionsGroup.getCheckedRadioButtonId());
            boolean isCorrect = mTestManager.checkAnswer(selectedAnswer.getText().toString());
            Toast toast = null;
            Snackbar mySnackbar = null;

            if (isCorrect) {
                mySnackbar = Snackbar.make(findViewById(R.id.activity_test),
                        R.string.message_correct, Snackbar.LENGTH_SHORT);
            } else {
                mySnackbar = Snackbar.make(findViewById(R.id.activity_test),
                        R.string.message_wrong, Snackbar.LENGTH_SHORT);
            }
            mySnackbar.show();
        }
        //decide whether to place simple question or passage
        if (mTestManager.getCount() == mPassagePlacement) {
            skipToastForPassage = true;
            hideSimpleQuestionLayout();
            PassageModel randomPassage = mPassageManager.getRandomPassage();
            PopulateViewWithPassage(randomPassage);

            mTestManager.mCount = mTestManager.getCount() + randomPassage.getQuestions().size();
            mTestManager.getCurrentQuestions().addAll(randomPassage.getQuestions());
        } else {
            if (skipToastForPassage) {
                skipToastForPassage = false;
                removePassageLayout();
                showSimpleQuestionLayout();
            }
            getNextQuestionAndPopulateView();
        }

    }

    void removePassageLayout() {
        if (passageLayout != null) {
            mParentScrollView.removeView(passageLayout);
        }
    }

    void PopulateViewWithPassage(PassageModel passageModel) {
        mCurrentPassage = passageModel;

        passageLayout = new LinearLayout(this);
        passageLayout.setOrientation(OrientationHelper.VERTICAL);
        passageLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView passageTextView = new TextView(this);
        passageTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        passageTextView.setText(passageModel.getPassage());
        passageTextView.setTextColor(getResources().getColor(R.color.black));
        passageLayout.addView(passageTextView);

        questionRadioGroupIds = new int[passageModel.getQuestions().size()];
        int count = 0;
        int questionCount=mTestManager.getCount()+1;
        for (QuestionModel question : passageModel.getQuestions()) {

            LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.question_layout, null);
            TextView textView = (TextView) layout.getChildAt(0);

            textView.setText(questionCount+". "+question.get_Question());

            RadioGroup radioGroup = (RadioGroup) layout.getChildAt(1);
            questionRadioGroupIds[count] = findId();
            radioGroup.setId(questionRadioGroupIds[count]);

            RadioButton firstOption = (RadioButton) radioGroup.getChildAt(0);
            firstOption.setText(question.get_Answer1());


            RadioButton secondOption = (RadioButton) radioGroup.getChildAt(1);
            secondOption.setText(question.get_Answer2());

            RadioButton thirdOption = (RadioButton) radioGroup.getChildAt(2);
            thirdOption.setText(question.get_Answer3());

            RadioButton fourthOption = (RadioButton) radioGroup.getChildAt(3);
            fourthOption.setText(question.get_Answer4());

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    onAnswerChanged(group, checkedId);
                }
            });
            firstOption.setChecked(true);
            questionCount++;
            count++;
            passageLayout.addView(layout);
        }
        Button nextButton = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        nextButton.setLayoutParams(params);
        nextButton.setText(getResources().getString(R.string.next_button_title));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick(v);
            }
        });
        passageLayout.addView(nextButton);
        mParentScrollView.addView(passageLayout);

    }

    public void onAnswerChanged(RadioGroup group, int checkedId) {
        RadioButton selectedAnswer = (RadioButton) group.findViewById(group.getCheckedRadioButtonId());
        checkAndUpdateQuestion(group.getId(), selectedAnswer.getText().toString());
    }

    private void checkAndUpdateQuestion(int id, String answer) {
        int index = 0;
        for (int i = 0; i < questionRadioGroupIds.length; i++) {
            if (questionRadioGroupIds[i] == id) {
                index = i;
                break;
            }
        }
        checkAnswer(mCurrentPassage.getQuestions().get(index), answer);
    }

    static int id = 1;

    // Returns a valid id that isn't in use
    public int findId() {
        View v = findViewById(++id);
        while (v != null) {
            v = findViewById(++id);
        }
        return id++;
    }

    public boolean checkAnswer(QuestionModel question, String answer) {
        Boolean isCorrect = false;
        if (question.get_Correct().equals(answer)) {
            question.set_isCorrect(true);
            isCorrect = true;
        } else {
            question.set_isCorrect(false);
            isCorrect = false;
        }
        question.set_Ref(answer);

        return isCorrect;
    }

    void hideSimpleQuestionLayout() {
        mParentScrollView.removeView(mSimpleQuestion);
    }

    void showSimpleQuestionLayout() {

        if(mParentScrollView.getChildCount()>0)
        {
            if(mParentScrollView.getChildAt(0).getId()!=R.id.simple_question)
            {
                mParentScrollView.addView(mSimpleQuestion);
            }

        }else
        {
            mParentScrollView.addView(mSimpleQuestion);
        }
    }


}