package Model.Questions;

import java.util.Random;


public class Question {
    private static Question myQuestion;
    private String myQuestionAndAnswer;
    protected Random myRand = new Random();
    Question() {
        double probability = myRand.nextDouble();
        if (probability <= .50) {
            new TrueFalse();
        } else {
            new ShortAnswer();
        }
    }

    void setMyQuestionAndAnswer(final String theQuestionAndAnswer) {
        myQuestionAndAnswer = theQuestionAndAnswer;
    }
    public static synchronized Question getMyQuestion() {
        myQuestion = new Question();
        return myQuestion;
    }

}
