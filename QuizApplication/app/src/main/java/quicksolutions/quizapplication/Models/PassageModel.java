package quicksolutions.quizapplication.Models;

import java.util.List;

public class PassageModel {

    public StringBuilder getPassage() {
        return passage;
    }

    public void setPassage(StringBuilder passage) {
        this.passage = passage;
    }

    private StringBuilder passage;

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }

    private List<QuestionModel>questions;

}
