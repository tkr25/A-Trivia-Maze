package Model;

import java.io.Serial;
import java.io.Serializable;

public class Maze implements Serializable{
    @Serial
    private static final long serialVersionUID = -6432147852365214569L;
    private Door[][] myDoors; // 2D array to store the doors in the maze
    private static final int SIZE = 5;
    private int myPosRow; //  current row position
    private int myPosCol; // current column position

    Maze() throws Exception {
        myPosCol = 0;
        myPosRow = 0;
        makeMaze();
    }
    private void makeMaze() throws Exception {
        myDoors = new Door[SIZE][SIZE]; // 5x5 array of doors
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (row == 0 && col == 0 || row == SIZE  - 1 && col == SIZE - 1) {
                    myDoors[row][col] = new Door();
                    myDoors[row][col].setDoorState(1);// entrance and exit doors to open
                } else {
                    myDoors[row][col] = new Door(); // new Door object for other positions
                }
            }
        }
        myPosRow = 0; // Set initial player row position
        myPosCol = 0; // Set initial player column position
    }
    public boolean validateSelection(final int theRow, final int theCol) {
        return ((theRow >= 0 && theCol >= 0) &&
                (theCol <= SIZE - 1 && theRow <= SIZE - 1)) &&
                myDoors[theRow][theCol].getDoorState() < 2;
    }

    public boolean canMove(final int theDir) {
        boolean result  = false;
        if (theDir == Direction.RIGHT.getMyDirectionNumber()) {
            result = validateSelection(myPosRow, moveRight());
        } else if(theDir == Direction.DOWN.getMyDirectionNumber()) {
            result = validateSelection(moveDown(), myPosCol);
        } else if(theDir == Direction.LEFT.getMyDirectionNumber()) {
            result = validateSelection(myPosRow, moveLeft());
        } else if(theDir == Direction.UP.getMyDirectionNumber()) {
            result = validateSelection(moveUp(), myPosCol);
        }
        return result;

    }
    private int moveRight() {
        return myPosCol + 1;
    }
    private int moveLeft() {
        return myPosCol - 1;
    }
    int moveDown() {
        return myPosRow + 1;
    }
    int moveUp() {
        return myPosRow - 1;
    }
    public void movePosition(final int theDir) {
        if (canMove(theDir)) {
            if (theDir == Direction.RIGHT.getMyDirectionNumber()) {
                setMyCol(moveRight());
            } else if (theDir == Direction.DOWN.getMyDirectionNumber()) {
                setMyRow(moveDown());
            } else if (theDir == Direction.LEFT.getMyDirectionNumber()) {
                setMyCol(moveLeft());
            } else if (theDir == Direction.UP.getMyDirectionNumber()) {
                setMyRow(moveUp());
            } else {
                System.out.println("Shouldn't have got here.");
                System.exit(0);
            }
        }
    }
    public static boolean atFinish(final int theRow, final int theCol) {
        return theRow == SIZE - 1 && theCol == SIZE - 1;
    }
    public static boolean canFinishMaze(final Maze theMaze, final int theRow, final int theCol) {
        boolean success = false;
        if (theMaze.validateSelection(theRow, theCol)) {
            markVisited(theMaze, theRow, theCol); //drop a bread crumb to track we've been here
            if (atFinish(theRow, theCol)) {
                return true;
            }
            //not at exit so need to try other directions
            success = canFinishMaze(theMaze, theMaze.moveDown(), theCol); //down
            if (!success) {
                success = canFinishMaze(theMaze, theRow, theMaze.moveRight()); //right
            }
            if(!success) {
                success = canFinishMaze(theMaze, theMaze.moveUp(), theCol); //up
            }
            if(!success) {
                success = canFinishMaze(theMaze, theRow, theMaze.moveLeft()); //left
            }
        }
        return success;
    }
    private static void markVisited(final Maze theMaze, final int theRow, final int theCol) {
        theMaze.myDoors[theRow][theCol].setDoorState(3);
    }
    void setMyRow(final int theRow) {
            myPosRow = theRow;
    }
    void setMyCol(final int theCol) {
            myPosCol = theCol;
    }
    public int getMyRow() {
        return myPosRow;
    }
    public int getMyCol() {
        return myPosCol;
    }
    public Door getMyDoor() {
        return myDoors[myPosRow][myPosCol];
    }
    public Door getAnyDoor(final int theRow, final int theCol) {
        return myDoors[theRow][theCol];
    }
}
