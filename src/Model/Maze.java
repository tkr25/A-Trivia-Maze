package Model;

import GUI.GUIConstants;

import java.awt.*;
import java.io.*;

public class Maze implements Serializable {
    @Serial
    private static final long serialVersionUID = -6432147852365214569L;

    /** The set size for the two arrays. */
    public static final int SIZE = 5;

    /** All the doors that take the player left and right through the maze */
    public Door[][] myLeftRightDoors;

    /** All the doors that take the player up and down through the maze */
    public Door[][] myUpDownDoors;

    /** The current Position of the player */
    private Position myPosition;

    /** The door the player wants to go through next */
    private Door mySelectedDoor;

    /** The Doors in a given room */
    private Door[] myRoom;

    /** The current Q and A attached to the door the player wants to enter */
    private String[] myCurrentQA;

    /** The Theme for the Trivia Questions. */
    private String myTheme;

    /**
     * Constructs a Maze Game based off the theme passed in.
     *
     * @param tableName the Theme selected for the game.
     */
    public Maze(final String tableName) throws Exception {
        myTheme = tableName;
        myRoom = new Door[4];
        myPosition =  new Position();
        new Door(myTheme);
        myLeftRightDoors = makeMaze(SIZE, SIZE + 1);
        myUpDownDoors = makeMaze(SIZE + 1, SIZE);
        setRooms();
    }

    /**
     * Constructs a structure to hold the doors for the maze
     *
     * @param theRow the desired row length.
     * @param theCol the desired column length.
     * @return the structure of doors.
     */
    private Door[][] makeMaze(final int theRow,final int theCol) throws Exception {
        Door[][] doors = new Door[theRow][theCol];

        for (int row = 0; row < theRow; row++) {
            for (int col = 0; col < theCol; col++) {
                if (theRow < theCol && (col == 0 || col == theCol - 1)) {
                    doors[row][col] = new Door(true);
                } else if (theRow > theCol && (row == 0 || row == theRow - 1)) {
                    doors[row][col] = new Door(true);
                }else {
                    doors[row][col] = new Door(false);
                }
            }
        }
        return doors;
    }

    /**
     * Takes the guess, trims any extra space and then compares it
     * to the answer. Capitalization doesn't affect result.
     *
     * @param theGuess the guess from the player.
     * @return a boolean stating if the answer is correct or not.
     */
    public boolean checkAnswer(final String theGuess) {
        return mySelectedDoor.checkAnswer(theGuess);
    }

    /** Grabs current Q for the selected door */
    public String getCurrentQ() {
        return myCurrentQA[0];
    }

    /** Grabs current Position for the game */
    public Position getPosition() {
        return myPosition;
    }

    /** Grabs all the doors available in the current room */
    public Door[] getRoom() {
        return myRoom;
    }

    /** Updates the doors available in the newly entered room. */
    public void setRooms() {
        int x = myPosition.getModelPosition().x;
        int y = myPosition.getModelPosition().y;
        myRoom[Position.LEFT] = myLeftRightDoors[x][y];
        myRoom[Position.UP] = myUpDownDoors[x][y];
        myRoom[Position.RIGHT] = myLeftRightDoors[x][y + 1];
        myRoom[Position.DOWN] = myUpDownDoors[x + 1][y];
    }
    public Door getDoorInDirection(final int theDirection) {
        return myRoom[theDirection];
    }

    /** Sets up the QA of the selected door and updates intended direction */
    public void doorSelected(final int theDirection) {
        mySelectedDoor = myRoom[theDirection];
        myPosition.setDesiredPosition(theDirection);

        myCurrentQA = new String[2];
        myCurrentQA[0] = mySelectedDoor.getQuestion();
        myCurrentQA[1] = mySelectedDoor.getAnswer();
    }

    /**
     *  Finds out if there is still a way out of the Maze
     *
     * @param theMaze the Maze to be explored.
     * @return a boolean that states if the game still has a way out or not.
     */
    public static boolean isThereWayOut(String theMaze) {
        return mazeSolver.solver(theMaze);
    }

    /**
     * Saves the current state of the game.
     */
    public void saveGame() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("file:maze.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * loads the last saved state of the game.
     *
     * @return the saved Maze Game
     * @throws Exception if the game to be loaded does exist.
     */
    public static Maze loadGame() throws Exception {
        Maze m;
        try {
            FileInputStream fileIn = new FileInputStream("file:maze.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            m = (Maze) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            System.exit(1);
            m = new Maze("JAVA");
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            System.exit(1);
            m = new Maze("Java");
        }
        return m;
    }

    /**
     * An easy to read visual representation of the maze with all its doors states displayed
     * 3 -> wall , 2 -> locked , 1 -> unlocked , 0 -> not attempted.
     *
     * @return a String representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder maze = new StringBuilder();
        StringBuilder leftRightRow = new StringBuilder();
        StringBuilder upDownRow = new StringBuilder();
        for(int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++){
                upDownRow.append("x").append(myUpDownDoors[row][col].getDoorState());
                leftRightRow.append(myLeftRightDoors[row][col].getDoorState()).append(" ");
            }
            maze.append(upDownRow.append("x\n"));
            maze.append(leftRightRow.append("3\n"));
            upDownRow = new StringBuilder();
            leftRightRow = new StringBuilder();
        }
        maze.replace(maze.length() - 3, maze.length() - 2, "w");
        return maze.append("x3x3x3x3x3x ").toString();
    }
    public Door getDoorFromMyRoom(final int theDirection) {
        return myRoom[theDirection];
    }


    // for tests
    public void lockDoor(final Door theDoor) {
        theDoor.setMyDoorState(Door.LOCKED);
    }
    //for testing
    public void setPositionMaze(final Point thePosition) {
            myPosition = new Position(thePosition);

    }
}