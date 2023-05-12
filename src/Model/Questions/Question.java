package Model.Questions;

import java.util.Random;
import java.sql.SQLException;
import java.util.Scanner;

public class Question {




 //   private static Question myQuestion;
    private String myQuestion;
    private String myAnswer;

  //  private String myQuestionAndAnswer;
    protected Random myRand = new Random();

    private int randQ;
    public Question() throws SQLException {

         QBase questionsDB = new QBase();
     //   questionsDB.createNewTable();

        //1 to 50 number of question
        randQ = myRand.nextInt(6-1) + 1;
    //    randQ = 5;
        //get question from database randomly
        myQuestion = questionsDB.getQuestion(randQ);
        myAnswer = questionsDB.getAnswer(randQ);


//        double probability = myRand.nextDouble();
//        if (probability <= .50) {
//            new TrueFalse();
//        } else {
//            new ShortAnswer();
//        }
    }



    public String getQuestion()
    {
    return myQuestion;

    }
    public void setQuestion(String theQuestion)
    {
        myQuestion =theQuestion;
    }
    public String getAnswer()
    {
        return myAnswer;

    }
    public void setAnswer(String theAnswer)
    {
        myAnswer =theAnswer;
    }

    //method to check if the answer is ture
    public boolean checkIfCorrect (String theAnswer)
    {
        boolean check = false;

        if((myAnswer.toLowerCase().trim()).equals(theAnswer.toLowerCase().trim()))
        {

            check = true;
        }

        return check;
    }

// string representation of the  question
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n" + myQuestion + "\n");
        return sb.toString();
    }



    public static void main(String[] args) throws SQLException {


        boolean myGameOverWin = false;
        int winTimes = 0;


        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            Question question = new Question();
            System.out.println(question);

            String answer = scanner.nextLine();

            if (question.checkIfCorrect(answer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect.");
            }

            System.out.println("Play again? (y/n)");
            String playAgainAnswer = scanner.nextLine();

            if (playAgainAnswer.toLowerCase().equals("n")) {
                playAgain = false;
            }
        }

        System.out.println("GAME OVER");
    }
}
//}
