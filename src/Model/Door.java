package Model;

import GUI.GUIConstants;
import Model.Questions.Question;

import java.io.Serializable;
import java.sql.SQLException;


public class Door implements Serializable {

    public static final int UNKNOWN = 0;

    public static final int UNLOCKED = 1;

    public static final int LOCKED = 2;

    public static final int WALL = 3;

    private final Question myQuestion ;

    private static int count = 0;
    // represent door status, 0 if not tired, 1 if answered correctly, 2 if locked.
    private int myDoorState;


    /**
     * Constructor for the Door class.
     *
     * @param tableName The name of the database table containing the questions.
     * @throws Exception if there is an error while creating the Door object.
     */
    public Door(String tableName) throws Exception {
        if (tableName.equalsIgnoreCase(GUIConstants.ONE_PIECE)) {
            tableName = tableName.substring(0, 5);
        } else {
            tableName = tableName.substring(0, 4);
        }
        myQuestion = new Question(tableName);
        myDoorState = UNKNOWN; // door is not attempted yet
    }

    public Door(boolean isWall) throws SQLException {
        if (isWall) {
            myQuestion = new Question(true);
            myDoorState = WALL; // door is not attempted yet
        } else {
            myQuestion = new Question(false);
            myDoorState = UNKNOWN; // door is not attempted yet
        }
    }

    public String getQuestion() {
        return myQuestion.getQuestion();
    }

    public String getAnswer() {
        return myQuestion.getAnswer();
    }

    /**
     * Retrieves the state of the door.
     *
     * @return The door state (0 = not attempted, 1 = answered correctly, 2 = locked).
     */
    public int getDoorState() {
        return myDoorState;
    }

    public boolean checkAnswer(String answer) {
        boolean isCorrect = getAnswer().trim().equalsIgnoreCase(answer.trim());
        if(isCorrect) {
            myDoorState = UNLOCKED; // Correct answer
            myQuestion.setQuestion(" ");
        } else {
            myDoorState = LOCKED; // Incorrect answer
        }
        return isCorrect;
    }
}