package Model;

import Model.Questions.Question;

import java.sql.SQLException;

public class Room{
    private boolean myLock;
    private boolean myVisited;
    private Question myQuestion;
    Room() throws SQLException {
        myLock = false;
        myVisited = false;

      //  myQuestion = Question.getInstance();
    }

    public boolean getMyLock() {

        return myLock;
    }
    public boolean getMyVisited() {

        return myVisited;
    }
    public void setMyVisited(final boolean theVisit) {

        myVisited = theVisit;
    }
    public void setMyLock(final boolean theLock) {

        myLock = theLock;
    }


    //the string representation of room
        public String roomToString() {
        final StringBuilder strb = new StringBuilder();
        strb.append("Room is ");
        if (myLock) {
            strb.append("Locked");
        } else {
            strb.append("Unlocked");
        }
        strb.append(" and ");
        if (myVisited) {
            strb.append("Visited");
        } else {
            strb.append("Not Visited");
        }
        strb.append("\n ");
        return strb.toString();
    }
}