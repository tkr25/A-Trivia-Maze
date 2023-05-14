package Model;


public class Room{
    private boolean myLock;
    private boolean myVisited;

      Room()  {
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
    @Override
        public String toString() {
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