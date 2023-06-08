package Model;

import GUI.Position;

import java.awt.*;
import java.io.*;

public class Maze implements Serializable {
    private static final long serialVersionUID = -6432147852365214569L;
    private static final int SIZE = 5;

    public Door[][] myLeftRightDoors;
    public Door[][] myUpDownDoors;

    private Point myPosition;

    private Door myPotentialDoor;

    private String[] myCurrentQA;

    private String myTheme;

    public Maze(String tableName) throws Exception {
        myTheme = tableName;
        myPosition = new Point(0, 0);
        new Door(myTheme);
        myLeftRightDoors = makeMaze(SIZE, SIZE + 1);
        myUpDownDoors = makeMaze(SIZE + 1, SIZE);
    }

    private Door[][] makeMaze(int theRow, int theCol) throws Exception {
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

    public void saveGame() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("file:maze.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in file:maze.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

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
            m = new Maze("JAVA");
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            m = new Maze("Java");
        }
        return m;
    }

    public void setMyPosition(Point thePosition) {
        myPosition = thePosition;
    }

    /**
     * Takes the guess, trims any extra space and then compares it
     * to the answer. Capitalization doesn't affect result.
     *
     * @param theGuess the guess from the player.
     * @return
     */
    public boolean checkAnswer(String theGuess) {
        return myPotentialDoor.checkAnswer(theGuess);
    }

    public String getCurrentQ() {
        return myCurrentQA[0];
    }

    public Point getMyPosition() {
        return new Point(myPosition.y * 50 + 365, myPosition.x * 50 + 465);
    }

    private void setDoorQA(Door theDoor) {
        myPotentialDoor = theDoor;
        myCurrentQA[0] = myPotentialDoor.getQuestion();
        myCurrentQA[1] = myPotentialDoor.getAnswer();
    }

    /**
     * Gets current Question and Answer for Door picked in the room
     */
    public void setIntendedDirection(Point theDirection) {
        myCurrentQA = new String[2];
        if (theDirection.equals(Position.RIGHT)) {
            setDoorQA(myLeftRightDoors[myPosition.x][myPosition.y + 1]);
        } else if (theDirection.equals((Position.DOWN))) {
            setDoorQA(myUpDownDoors[myPosition.x + 1][myPosition.y]);
        } else if (theDirection.equals(Position.LEFT)) {
            setDoorQA(myLeftRightDoors[myPosition.x][myPosition.y]);
        } else if (theDirection.equals(Position.UP)) {
            setDoorQA(myUpDownDoors[myPosition.x][myPosition.y]);
        } else {
            throw new IllegalArgumentException("Invalid Direction");
        }
    }

    public static boolean isThereWayOut(String theMaze) {
        return mazeSolver.solver(theMaze);
    }
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
}