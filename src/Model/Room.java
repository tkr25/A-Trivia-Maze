package Model;

import Model.Questions.Question;

public class Room{
    private boolean myLock;
    private boolean myVisited;
    private Question myQuestion;
    Room() {
        myLock = false;
        myVisited = false;
        myQuestion = Question.getInstance();
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


}