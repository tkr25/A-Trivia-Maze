package Model.Questions;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.SQLException;



public class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = -6432147852065234569L;

    // Instance variables
    private final Random myRand = new Random(); // Random number generator
    private static List<String> myQuestions; // List to store questions
    private static List<String> myAnswers; // List to store answers
    private static List<Integer> myUsedQuestions; // List to track used questions

    // Constructor
//    public Question(String tableName) throws SQLException {
//        try {
//            // Create a new instance of the QBase class to access the database
//           // QBase questionsDB = new QBase();
//
//            // Retrieve all questions and answers from the specified table in the database
//            //myQuestions = questionsDB.getAllQuestionsFromTable(tableName);
//           // myAnswers = questionsDB.getAllAnswersFromTable(tableName);
//
//            // Close the database connection
//            //questionsDB.closeDB();
//
//            // Initialize the list to track used questions
//            myUsedQuestions = new ArrayList<>();
//
//        } catch (SQLException e) {
//            System.out.println("Database error: " + e.getMessage());
//        }
//    }

    /**
     * Generates a random index for an unused question.
     *
     * @return The random index or -1 if no more questions are available.
     */
    private int generateRandUnusedInd() {
        // Check if all questions have been used
        if (myQuestions.size() == myUsedQuestions.size()) {
            return -1; // No more available questions
        }

        // Generate a random index
        int randIndex = myRand.nextInt(myQuestions.size());

        // Ensure the generated index is not already used
        while (myUsedQuestions.contains(randIndex)) {
            randIndex = myRand.nextInt(myQuestions.size());
        }

        // Add the generated index to the list of used questions
        myUsedQuestions.add(randIndex);

        // Return the generated index
        return randIndex;
    }

    /**
     * Gets the current question.
     *
     * @return The current question or a message if no more questions are available.
     */
    public String getMyQuestion() {
        // Generate a random index for the current question
        int randIndex = generateRandUnusedInd();

        // Check if there are still questions available
        if (randIndex != -1) {
            // Return the question at the generated index
            return myQuestions.get(randIndex);
        }

        // Return a message indicating no more questions are available
        return "No questions available";
    }

    /**
     * Gets the answer for the current question.
     *
     * @return The answer or an empty string if no more questions are available.
     */
    public String getMyAnswer() {
        // Get the index of the last used question
        int lastIndex = myUsedQuestions.size() - 1;

        // Check if there are any used questions
        if (lastIndex >= 0 && lastIndex < myAnswers.size()) {
            // Get the answer for the last used question
            return myAnswers.get(myUsedQuestions.get(lastIndex));
        }

        // Return an empty string if no more questions are available
        return "";
    }

//    /**
//     * Checks if the user's answer matches the correct answer.
//     *
//     * @param theAnswer The user's answer.
//     * @param myAnswer  The correct answer.
//     * @return true if the answers match, false otherwise.
//     */
//    public boolean checkIfCorrect(String theAnswer, String myAnswer) {
//        // Print the user's answer and the correct answer for testing purposes,comment out later
//        System.out.println(theAnswer + " " + myAnswer);
//
//        // Compare the trimmed user's answer and the trimmed correct answer (case-insensitive)
//        return myAnswer.trim().equalsIgnoreCase(theAnswer.trim());
//    }

    @Override
    public String toString() {
        // Return a string representation of the current question
        //   return "Question: " + getMyQuestion() + "\n";
        return getMyQuestion() + "\n";
    }

    //for debugging purposes
    public boolean isEmpty() {

        return myUsedQuestions.size() == myQuestions.size();
    }


}
