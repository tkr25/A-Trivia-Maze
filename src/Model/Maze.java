package Model;

public class Maze extends Room{
    private Room[][] myMaze;
    private int myRow;
    private int myCol;
    private boolean canWin;
    private final int MIN_SIZE = 4;
    private final int RIGHT = 0;
    private final int DOWN = 1;
    private final  int LEFT = 2;
    private final int UP = 3;
    Maze() {
        myCol = 0;
        myRow = 0;
        canWin = true;
        makeMaze(MIN_SIZE);
    }
    private void makeMaze(final int theSize) {
        myMaze = new Room[theSize][theSize];
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[0].length; j++) {
                myMaze[i][j] = new Room();
            }
        }
    }
    private void move(final int theDir) {
        if (theDir == RIGHT) {
            setMyRow(myRow + 1);
        } else if(theDir == DOWN) {
            setMyCol(myCol + 1);
        } else if(theDir == LEFT) {
            setMyRow(myRow - 1);
        } else if(theDir == UP) {
            setMyCol(myCol - 1);
        } else {
            System.out.println("Shouldn't have got here.");
        }
    }
    private void setCanWin() {

    }
    private void setMyRow(final int theRow) {
        myRow = theRow;
    }
    private void setMyCol(final int theCol) {
        myCol = theCol;
    }
    public int getMyRow() {
        return myRow;
    }
    public int getMyCol() {
        return myCol;
    }
    public Room[][] getMyMaze() {
        return myMaze;
    }
}
