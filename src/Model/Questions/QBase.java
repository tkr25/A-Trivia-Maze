package Model.Questions;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class QBase {



    private static SQLiteDataSource myQuestionSource;
    private Connection myConnect = null;

    private ResultSet resSet;


    public void createNewTable () throws ClassNotFoundException {

        myQuestionSource = new SQLiteDataSource();
        try {
            //connection to DB
            connectQuestDB();
            //create a table
            QuestT();
            AnswT();
            // Add data
            addQuestionAndAnswer("Are you ready kids?", "Aye-aye capitan");
            addQuestionAndAnswer("Who lives in the pineapple under the sea??", "Sponge Bob Square pants");


            // Get a question and matching answer
            int questId =2;

            String question = getQuestId(questId);
            String answer = getAnswId(questId);
            System.out.println("Q: " + question);
            System.out.println("A: " + answer);

            // Close the connection
            closeDB();

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void closeDB () throws SQLException
    {
        myConnect.close();

        resSet.close();

        System.out.println("Connections closed");
    }


    private void connectQuestDB () throws SQLException {
        myQuestionSource.setUrl("jdbc:sqlite:A-Trivia_Maze.db");
        // Open a connection to the database
        myConnect = myQuestionSource.getConnection();
    }

    private void QuestT () throws SQLException {
        // Create the question table
        String query = "CREATE TABLE IF NOT EXISTS questions ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " question TEXT NOT NULL"
                + ");";

        try (Statement stmt = myConnect.createStatement()) {
            stmt.execute(query);
        }
    }

    private void AnswT () throws SQLException {
        // Create the answer table
        String sql = "CREATE TABLE IF NOT EXISTS answers ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " question_id INTEGER NOT NULL,"
                + " answer TEXT NOT NULL,"
                + " FOREIGN KEY (question_id) REFERENCES questions(id)"
                + ");";
        try (Statement stmt = myConnect.createStatement()) {
            stmt.execute(sql);
        }
    }

    private void addQuestionAndAnswer (String question, String answer) throws SQLException {
        // Insert the question
        String query1 = "INSERT INTO questions (question) VALUES ('" + question + "')";
        Statement stmt1 = myConnect.createStatement();
        stmt1.executeUpdate(query1);

        // Get the ID of the inserted question
        String query2 = "SELECT last_insert_rowid()";
        Statement stmt2 = myConnect.createStatement();
        resSet = stmt2.executeQuery(query2);
        int questionId = resSet.getInt(1);

        // Insert the answer with the same ID as the question
        String query3 = "INSERT INTO answers (question_id, answer) VALUES (" + questionId + ", '" + answer + "')";
        Statement stmt3 = myConnect.createStatement();
        stmt3.executeUpdate(query3);
    }


    private String getQuestId(int questionId) throws SQLException {
        // Get the question by its ID
        String query = "SELECT question FROM questions WHERE id = " + questionId;
        try (Statement stmt = myConnect.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                if (rs.next()) {
                    return rs.getString("question");
                } else {
                    return null;
                }
            }
        }
    }

    private String getAnswId(int questionId) throws SQLException {
        // Get the answer for the question by its ID
        String query = "SELECT answer FROM answers WHERE question_id = "+ questionId;
        try (Statement stmt = myConnect.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                if (rs.next()) {

                    return rs.getString("answer");
                } else {
                    return null;
                }
            }
        }
    }
}



