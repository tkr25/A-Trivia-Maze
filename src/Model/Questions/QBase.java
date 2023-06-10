package Model.Questions;

import GUI.GUIConstants;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QBase {

    private static final String OneP = GUIConstants.ONE_PIECE.substring(0,4);
    private static final String Java = GUIConstants.JAVA;

    private static final String Harry = GUIConstants.HARRY_POTTER.substring(0, 5);


    private static SQLiteDataSource myQuestionSource;
    private Connection myConnect = null;
    private ResultSet myResSet;
    private boolean myTableCreated = false;

    // Constructor
    public QBase() throws SQLException {
        myQuestionSource = new SQLiteDataSource();
        connectQuestDB();
    }



    public void createNewTable() {
        try {
            // Create tables for OnePiece, Harry, and Java
            OneP();
            Harry();
            Java();

            if (!myTableCreated) {
                // Add questions and answers to the tables

                addQuestionAnswer(OneP, "question", "answer", "What color is Zoro's hair?\n", "green\n");
                addQuestionAnswer(OneP, "question", "answer", "Who is Captain of the Straw Hats?\n", "Luffy\n");
                addQuestionAnswer(OneP, "question", "answer", "Who is Shanks Captain of?\n", "Red Hair Pirates\n");
                addQuestionAnswer(OneP, "question", "answer", "What animal is Chopper?\n", "Reindeer\n");
                addQuestionAnswer(OneP, "question", "answer", "What is Luffy's body made of?\n", "Rubber\n");
                addQuestionAnswer(OneP, "question", "answer", "Beside the Captain, who was the first to join the Straw Hats crew?\n", "Zoro\n");
                addQuestionAnswer(OneP, "question", "answer", "What is Luffy's brother's name?\n", "Ace\n");
                addQuestionAnswer(OneP, "question", "answer", "Who is the cook of the Straw Hats Crew?\n", "Sanji\n");
                addQuestionAnswer(OneP, "question", "answer", "Who gave Luffy his straw hat?\n", "Shanks\n");
                addQuestionAnswer(OneP, "question", "answer", "Who did Ace call 'pops'?\n", "Whitebeard\n");
                addQuestionAnswer(OneP, "question", "answer", "Who was the son of Gold D. Roger?\n", "Ace\n");
                addQuestionAnswer(OneP, "question", "answer", "What color is Namis hair?\n", "Orange\n");
                addQuestionAnswer(OneP, "question", "answer", "What Straw Hat member is a cyborg?\n", "Franky\n");
                addQuestionAnswer(OneP, "question", "answer", "Which Star Hat member is made of bones?\n", "Brook\n");
                addQuestionAnswer(OneP, "question", "answer", "Which Straw Hat member cares the most about money?\n", "Nami\n");
                addQuestionAnswer(OneP, "question", "answer", "How many Warlords were there before being disbanded?\n", "Seven\n");
                addQuestionAnswer(OneP, "question", "answer", "How many Emperors are there?\n", "Four\n");
                addQuestionAnswer(OneP, "question", "answer", "Whos first bounty was 30,000,000 berries?\n", "Luffy\n");
                addQuestionAnswer(OneP, "question", "answer", "Those who eat Devil fruits loose the ability to...\n", "Swim\n");
                addQuestionAnswer(OneP, "question", "answer", "Which member of the Straw Hats eat the most?\n", "Luffy\n");
                addQuestionAnswer(OneP, "question", "answer", "Which member of the Straw Hats is looking for the all blue?\n", "Sanji\n");
                addQuestionAnswer(OneP, "question", "answer", "Which member of the Straw Hats wants to be the greatest swordsmen?\n", "Zoro\n");
                addQuestionAnswer(OneP, "question", "answer", "Which member of the Straw Hats has the smallest bounty?\n", "Chopper\n");
                addQuestionAnswer(OneP, "question", "answer", "How many years did the Straw Hats take a break to train?\n", "Two\n");
                addQuestionAnswer(OneP, "question", "answer", "Whos death cause the Straw Hats to take a break to train?\n", "Ace\n");
                addQuestionAnswer(OneP, "question", "answer", "What color band is on Luffys Straw Hat\n", "Red\n");
                addQuestionAnswer(OneP, "question", "answer", "Who lost an arm saving Luffy?\n", "Shanks\n");
                addQuestionAnswer(OneP, "question", "answer", "Who wants to be the next King of the PIrates\n", "Luffy\n");
                addQuestionAnswer(OneP, "question", "answer", "Who ate the Chop-Chop fruit?\n", "Buggy\n");
                addQuestionAnswer(OneP, "question", "answer", "Who does Sanji disagree with the most?\n", "Zoro\n");
                addQuestionAnswer(OneP, "question", "answer", "What does Brook soak in to repair his body?\n", "Milk\n");
                addQuestionAnswer(OneP, "question", "answer", "What drink does Franky use to fuel his attacks?\n", "Cola\n");
                addQuestionAnswer(OneP, "question", "answer", "What weapon does Brook use?\n", "Sword\n");
                addQuestionAnswer(OneP, "question", "answer", "Who is the Navigator of the Straw Hats crew?\n", "Nami\n");
                addQuestionAnswer(OneP, "question", "answer", "Which of the Straw Hats have had a high bounty since they were a kid?\n", "Robin\n");
                addQuestionAnswer(OneP, "question", "answer", "What color is Luffy's hair?\n", "black\n");
                addQuestionAnswer(OneP, "question", "answer", "Which member of the Straw Hats favorite fruit is tangerines?\n", "Nami\n");
                addQuestionAnswer(OneP, "question", "answer", "Who does Buggy blame his devil fruit powers on?\n", "Shanks\n");
                addQuestionAnswer(OneP, "question", "answer", "What color hair does Robin have?\n", "Black\n");
                addQuestionAnswer(OneP, "question", "answer", "What color hair does Franky have?\n", "Blue\n");







                addQuestionAnswer(Harry, "question", "answer", "___ Potter was Harry’s mom's name.\n", "Lily \n");
                addQuestionAnswer(Harry, "question", "answer", "Under the ___ was where Harry's original room was. \n", "Stairs\n");
                addQuestionAnswer(Harry, "question", "answer", "___ was the house Harry was in at Hogwarts.\n", "Gryffindor\n");
                addQuestionAnswer(Harry, "question", "answer", "___ Weasley and Hermione Granger are Harry's best friends. \n", "Ron \n");
                addQuestionAnswer(Harry, "question", "answer", "The Sorting ___ sorted the students into their houses.\n", "Hat\n");
                addQuestionAnswer(Harry, "question", "answer", "Harry was supposed to take platform nine and three quarters in the King’s Cross Station to ___.\n", "Hogwarts\n");
                addQuestionAnswer(Harry, "question", "answer", "Harry can speak to ___(animal).\n", "Snakes\n");
                addQuestionAnswer(Harry, "question", "answer", "Albus ___ brought Harry to the Dursleys.\n", "Dumbledore\n");
                addQuestionAnswer(Harry, "question", "answer", "Lord ___ put the lightning bolt scar on Harry'sforehead.\n", "Voldemort\n");
                addQuestionAnswer(Harry, "question", "answer", "Remus Lupin turns into a ___.\n", "Werewolf\n");
                addQuestionAnswer(Harry, "question", "answer", "Sirius ___ was James’ friends that escaped the Azkaban, who also turns out to be Harry’s godfather.\n", "Black\n");
                addQuestionAnswer(Harry, "question", "answer", "Ron Weasley has a fear of ___(insect).\n", "Spiders\n");
                addQuestionAnswer(Harry, "question", "answer", "Hagrid’s pet dragon, Norberta, was a Norwegian ___.\n", "Ridgeback\n");
                addQuestionAnswer(Harry, "question", "answer", "Harry’s position in the wizarding sport Quidditch was ___.\n", "Seeker\n");
                addQuestionAnswer(Harry, "question", "answer", "In order to close the Marauder’s Map you say \"Mischief ___.\"\n", "Managed\n");
                addQuestionAnswer(Harry, "question", "answer", "Harry’s Patronus was a ___.\n", "Stag\n");
                addQuestionAnswer(Harry, "question", "answer", "___ Malfoy was a school-aged nemesis of Harry’s throughout his time at Hogwarts \n", "Draco\n");
                addQuestionAnswer(Harry, "question", "answer", "The ___ of Magic makes laws for the magical world.\n", "Ministry\n");
                addQuestionAnswer(Harry, "question", "answer", "Professor Minerva McGonagall could shape shift into a ___(animal)\n", "Cat\n");
                addQuestionAnswer(Harry, "question", "answer", "Fluffy was a three headed ___(animal).\n", "Dog\n");
                addQuestionAnswer(Harry, "question", "answer", "___ was the name of the male house-elf who served the Malfoy family.\n", "Dobby\n");
                addQuestionAnswer(Harry, "question", "answer", "___ are the creatures that guard Azkaban.\n", "Dementors\n");
                addQuestionAnswer(Harry, "question", "answer", "Harry Potter had a pet ___.\n", "Owl\n");
                addQuestionAnswer(Harry, "question", "answer", "The Leaky ___ was a pub.\n", "Cauldron\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of Ron’s sister.\n", "Ginni\n");
                addQuestionAnswer(Harry, "question", "answer", "The Hogwarts ___ is the name of the magical train that takes students to Hogwarts.\n", "Express\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of the magical village near Hogwarts.\n", "Hogsmead\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of Harry Potter’s pet owl.\n", "Hedwig\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of Ron Weasley’s rat.\n", "Scabbers\n");
                addQuestionAnswer(Harry, "question", "answer", "Severus ___ teaches Potions at Hogwarts during most of the series.\n", "Snape\n");
                addQuestionAnswer(Harry, "question", "answer", "The Daily ___ is the name of the magical newspaper.\n", "Prophet\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of Hermione’s cat.\n", "Crookshanks\n");
                addQuestionAnswer(Harry, "question", "answer", "The ___ Lady is a magical guard to the entrance to the Gryffindor common room.\n", "Fat\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of the magical prison in the Harry Potter world.\n", "Azkaban\n");
                addQuestionAnswer(Harry, "question", "answer", "Moaning ___ is the name of the ghost who haunts the girls’ bathroom at Hogwarts.\n", "Myrtle\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of the magical plant that can be used to breathe underwater.\n", "Gillyweed\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of the magical creature can only be seen by those who have witnessed death?\n", "Thestral\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the screaming magical plant that’s used as an antidote to the Basilisk’s venom?\n", "Mandrake\n");
                addQuestionAnswer(Harry, "question", "answer", "___ is the name of the creature that can change its shape to resemble a person’s worst fear?\n", "Boggart\n");
                addQuestionAnswer(Harry, "question", "answer", "The ___ Tournament is the name of the annual Quidditch tournament between Hogwarts, Beauxbatons, and Durmstrang?\n", "Triwizard\n");



                addQuestionAnswer(Java, "question", "answer", "Which component is used to compile, debug and execute the java programs?\n", "JDK\n");
                addQuestionAnswer(Java, "question", "answer", "What is the extension of java code files? .___\n", "Java\n");
                addQuestionAnswer(Java, "question", "answer", "What is the extension of compiled java classes? .___\n", "Class\n");
                addQuestionAnswer(Java, "question", "answer", "Which of these keywords is used to define interfaces in Java?\n", "Interface\n");
                addQuestionAnswer(Java, "question", "answer", "Is string mutable in Java?\n", "No\n");
                addQuestionAnswer(Java, "question", "answer", "Which concept of Java is a way of converting real world objects in terms of class?\n", "Abstraction\n");
                addQuestionAnswer(Java, "question", "answer", "Can a private method be overridden in Java?\n", "No\n");
                addQuestionAnswer(Java, "question", "answer", "Can overriding a method be prevented without using the final modifier?\n", "Yes\n");
                addQuestionAnswer(Java, "question", "answer", "Can an interface extend more than one interface in Java?\n", "Yes\n");
                addQuestionAnswer(Java, "question", "answer", "Can a class extend more than one class in Java?\n", "No\n");
                addQuestionAnswer(Java, "question", "answer", "Which constructor has no parameters?\n", "Default\n");
                addQuestionAnswer(Java, "question", "answer", "Which OOPS concept exposes only necessary information to the calling functions?\n", "Encapsulation\n");
                addQuestionAnswer(Java, "question", "answer", "Under which pillar of OOPS do base class and derived class relationships come?\n", "Inheritance\n");
                addQuestionAnswer(Java, "question", "answer", "Which access specifier makes the class member accessible outside the class but can be accessed by any subclass of that class?\n", "Protected\n");
                addQuestionAnswer(Java, "question", "answer", "Which access specifiers have strict access control?\n", "Private\n");
                addQuestionAnswer(Java, "question", "answer", "Can we create an object of abstract class type?\n", "No\n");
                addQuestionAnswer(Java, "question", "answer", "Can the main() method be overloaded in Java?\n", "Yes\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword in java is used for exception handling?\n", "throw\n");
                addQuestionAnswer(Java, "question", "answer", "Which class in Java is used to take input from the user?\n", "Scanner\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword is used to inherit classes in Java?\n", "Extends\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword is used to inherit an interface in Java?\n", "Implements\n");
                addQuestionAnswer(Java, "question", "answer", "The ability of an object to take many forms in Java is called ___\n", "Polymorphism\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword is used to store only two possible values, either true or false.?\n", "Boolean\n");
                addQuestionAnswer(Java, "question", "answer", "Which built-in function is used to eliminate leading and trailing spaces? ___()\n", "Trim\n");
                addQuestionAnswer(Java, "question", "answer", "Which Java keyword is used to access features of a package?\n", "Import\n");
                addQuestionAnswer(Java, "question", "answer", "What is the return type of a method that does not return any value?\n", "Void\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword is used by the method to refer to the object that invoked it?\n", "This\n");
                addQuestionAnswer(Java, "question", "answer", "All the variables of class should be ideally declared as?\n", "Private\n");
                addQuestionAnswer(Java, "question", "answer", "Can a class be declared with a protected modifier?\n", "No\n");
                addQuestionAnswer(Java, "question", "answer", "Which is the modifier when there is none mentioned explicitly?\n", "Default\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword is used to prevent content of a variable from being modified?\n", "Final\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword can be used in a subclass to call the constructor of superclass?\n", "Super\n");
                addQuestionAnswer(Java, "question", "answer", "Which method of Object class can generate duplicate copy of the object on which it is called? ___()\n", "Clone\n");
                addQuestionAnswer(Java, "question", "answer", "Which process is a process of writing the state of an object to a byte stream?\n", "Serialization\n");
                addQuestionAnswer(Java, "question", "answer", "Can a static variable be Serialised?\n", "No\n");
                addQuestionAnswer(Java, "question", "answer", "Which process is a process of extracting/removing the state of an object from a stream?\n", "Deserialization\n");
                addQuestionAnswer(Java, "question", "answer", "Which access specifier can be used for a class so that its members can be accessed by a different class in the different package?\n", "Public\n");
                addQuestionAnswer(Java, "question", "answer", "Which keyword is used to make a class?\n", "Class\n");
                addQuestionAnswer(Java, "question", "answer", "Which design pattern ensures that only one object of particular class gets created?\n", "Singleton\n");
                addQuestionAnswer(Java, "question", "answer", "Can we create an instance of Enum outside of Enum itself??\n", "No\n");


                myTableCreated = true;
            }

            closeDB();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    void closeDB() throws SQLException {
        // Close the ResultSet and Connection
        if (myResSet != null) {
            myResSet.close();
        }

        if (myConnect != null) {
            myConnect.close();
            //for debugging purposes, comment out later
            //  System.out.println("Connections closed");
        }
    }

    private void connectQuestDB() throws SQLException {
        // Set the URL for the SQLite database connection
        //   myQuestionSource.setUrl("jdbc:sqlite:DB.db");
        myQuestionSource.setUrl("jdbc:sqlite:MazeAB.db");
        myConnect = myQuestionSource.getConnection();
    }

    private void OneP() throws SQLException {
        // Create the question and answer table for OnePiece
        String query = "CREATE TABLE IF NOT EXISTS OneP ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " question TEXT NOT NULL,"
                + " answer TEXT NOT NULL"
                + ")";

        try (Statement stmt = myConnect.createStatement()) {
            stmt.execute(query);
        }
    }

    private void Harry() throws SQLException {
        // Create the question and answer table for Harry
        String query = "CREATE TABLE IF NOT EXISTS Harry ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " question TEXT NOT NULL,"
                + " answer TEXT NOT NULL"
                + ")";

        try (Statement stmt = myConnect.createStatement()) {
            stmt.execute(query);
        }
    }

    private void Java() throws SQLException {
        // Create the question and answer table for Java
        String query = "CREATE TABLE IF NOT EXISTS Java ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " question TEXT NOT NULL,"
                + " answer TEXT NOT NULL"
                + ")";

        try (Statement stmt = myConnect.createStatement()) {
            stmt.execute(query);
        }
    }

    private void addQuestionAnswer(String tableName, String questionColumn, String answerColumn, String question, String answer) throws SQLException {
        // Insert the question and answer into the specified table
        String query = "INSERT INTO " + tableName + " (" + questionColumn + ", " + answerColumn + ") VALUES (?, ?)";

        try (PreparedStatement pstmt = myConnect.prepareStatement(query)) {
            pstmt.setString(1, question);
            pstmt.setString(2, answer);
            pstmt.executeUpdate();
        }
    }

    public List<String> getAllQuestionsFromTable(String tableName) {
        // Retrieve all questions from the specified table
        List<String> allQuestions = new ArrayList<>();

        try {
            String query = "SELECT question FROM " + tableName;
            try (Statement stmt = myConnect.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String question = rs.getString("question");
                    allQuestions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allQuestions;
    }

    public List<String> getAllAnswersFromTable(String tableName) {
        // Retrieve all answers from the specified table
        List<String> allAnswers = new ArrayList<>();

        try {
            String query = "SELECT answer FROM " + tableName;
            try (Statement stmt = myConnect.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String answer = rs.getString("answer");
                    allAnswers.add(answer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAnswers;
    }


    //main method to fill out the tables
    public static void main(String[] args) {
        try {
            QBase questionBase = new QBase();
            questionBase.createNewTable();
            //for debugging purposes, comment out later
            System.out.println("Tables created and sample data inserted.");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

}