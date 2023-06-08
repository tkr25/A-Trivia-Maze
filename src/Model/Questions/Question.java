package Model.Questions;


import java.io.Serializable;
import java.util.*;
import java.sql.SQLException;


public class Question implements Serializable {

    // Instance variables
    private static List<String> myQuestions; // List to store questions

    private String myQuestion;

    private String myAnswer;
    private static List<String> myAnswers; // List to store answers
    private static List<Integer> myOrderForQuestions; // List to track used questions

    // Constructor
    public Question(String tableName) throws SQLException {
        try {
            // Create a new instance of the QBase class to access the database
            QBase questionsDB = new QBase();

            // Retrieve all questions and answers from the specified table in the database
            myQuestions = questionsDB.getAllQuestionsFromTable(tableName.substring(0, 4));
            myAnswers = questionsDB.getAllAnswersFromTable(tableName.substring(0, 4));

            // Close the database connection
            questionsDB.closeDB();

            // Initialize the list to track used questions
            myOrderForQuestions = new ArrayList<>();

            for (int i = 0; i < 40; i++) {
                myOrderForQuestions.add(i);
            }
            Collections.shuffle(myOrderForQuestions);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public Question(boolean isWall) {
        if (isWall) {
            myQuestion = "This is wall";
            myAnswer = "This is wall";
        } else {
            int index = getIndexQA();
            myQuestion = myQuestions.get(index);
            myAnswer = myAnswers.get(index);
        }
    }

    public void setQuestion(String theQ) {
        myQuestion = theQ;
    }


    /**
     * Gets the current question.
     *
     * @return The current question or a message if no more questions are available.
     */
    public int getIndexQA() {
        // Generate a random index for the current question
        int randIndex = myOrderForQuestions.get(0);
        myOrderForQuestions.remove(0);
        return randIndex;
    }

    public String getQuestion() {
        return myQuestion;
    }


    public String getAnswer() {
        return myAnswer;
    }


    @Override
    public String toString() {
        // Return a string representation of the current question

        return getQuestion() + " \n" + getAnswer() + "\n";
    }

    //for debugging purposes
}