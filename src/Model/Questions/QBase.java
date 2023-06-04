//package Model.Questions;
//
//import org.sqlite.SQLiteDataSource;
//
//import java.sql.*;
//
//public class QBase {
//
//    private static SQLiteDataSource myQuestionSource;
//    private Connection myConnect = null;
//
//    private ResultSet resSet;
//    private boolean tableCreated = false;
//
//
//    //constructor
//    public QBase() throws SQLException {
//        myQuestionSource = new SQLiteDataSource();
//        connectQuestDB();
//    }
//    public void createNewTable ()  {
//
//
//
//
//        try {
//
//            //create a table
//            QuestT();
//            AnswT();
//
//
//            if(!tableCreated) {
//
//                addQuestionAndAnswer("Quest1", "1");
//                addQuestionAndAnswer("Quest2", "2");
//                addQuestionAndAnswer("Quest3", "3");
//                addQuestionAndAnswer("Quest4", "4");
//                addQuestionAndAnswer("Quest5", "5");
//                addQuestionAndAnswer("Quest6", "6");
//
//
//
//               tableCreated = true;
//            }
//
//
//        } catch (SQLException e) {
//            System.err.println("Database error: " + e.getMessage());
//        }
//    }
//
//    void closeDB() throws SQLException
//    {
//
//        if (myConnect != null) {
//            myConnect.close();
//        }
//
//        if (resSet != null) {
//            resSet.close();
//        }
//
//
//        System.out.println("Connections closed");
//    }
//
//
//    private void connectQuestDB () throws SQLException {
//        //  myQuestionSource.setUrl("jdbc:sqlite:A-Trivia_Maze.db");
//        myQuestionSource.setUrl("jdbc:sqlite:New.db");
//        //  myQuestionSource.setUrl("jdbc:sqlite:New1.db");
//        //  myQuestionSource.setUrl("jdbc:sqlite:TRY.db");
//        // Open a connection to the database
//        myConnect = myQuestionSource.getConnection();
//    }
//
//    private void QuestT () throws SQLException {
//        // Create the question table
//        String query = "CREATE TABLE IF NOT EXISTS questions ("
//                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + " question TEXT NOT NULL"
//                + ");";
//
//        try (Statement stmt = myConnect.createStatement()) {
//            stmt.execute(query);
//        }
//    }
//
//    private void AnswT () throws SQLException {
//        // Create the answer table
//        String sql = "CREATE TABLE IF NOT EXISTS answers ("
//                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + " question_id INTEGER NOT NULL,"
//                + " answer TEXT NOT NULL,"
//                + " FOREIGN KEY (question_id) REFERENCES questions(id)"
//                + ");";
//        try (Statement stmt = myConnect.createStatement()) {
//            stmt.execute(sql);
//        }
//    }
//
//    void addQuestionAndAnswer(String question, String answer) throws SQLException {
//        // Insert the question
//        String query1 = "INSERT INTO questions (question) VALUES ('" + question + "')";
//        Statement stmt1 = myConnect.createStatement();
//        stmt1.executeUpdate(query1);
//
//        // Get the ID of the inserted question
//        String query2 = "SELECT last_insert_rowid()";
//        Statement stmt2 = myConnect.createStatement();
//        resSet = stmt2.executeQuery(query2);
//        int questionId = resSet.getInt(1);
//
//        // Insert the answer with the same ID as the question
//        String query3 = "INSERT INTO answers (question_id, answer) VALUES (" + questionId + ", '" + answer + "')";
//        Statement stmt3 = myConnect.createStatement();
//        stmt3.executeUpdate(query3);
//    }
//
//
//     String getQuestId(int questionId) throws SQLException {
//        // Get the question by its ID
//        String query = "SELECT question FROM questions WHERE id = " + questionId;
//        try (Statement stmt = myConnect.createStatement()) {
//            try (ResultSet rs = stmt.executeQuery(query)) {
//                if (rs.next()) {
//                    return rs.getString("question");
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    String getAnswId(int questionId) throws SQLException {
//        // Get the answer for the question by its ID
//        String query = "SELECT answer FROM answers WHERE question_id = "+ questionId;
//        try (Statement stmt = myConnect.createStatement()) {
//            try (ResultSet rs = stmt.executeQuery(query)) {
//                if (rs.next()) {
//
//                    return rs.getString("answer");
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    String getQuestion(int questionId) {
//        try {
//            String question = getQuestId(questionId);
//            return question;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "Error getting question";
//        }
//    }
//
//     String getAnswer(int questionId) {
//        try {
//            String answer = getAnswId(questionId);
//            return answer;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "Error getting answer";
//        }
//    }
//
////       main method used to fill the database with questions
////        public static void main(String[] args) {
////            try {
////                QBase questionBase = new QBase();
////                questionBase.createNewTable();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
//
//}
//
//
//
