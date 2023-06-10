package Model;

import GUI.GUIConstants;

import java.awt.*;
import java.io.Serializable;

public class Position implements Serializable {

    final public static int LEFT = 0;

    final public static int UP = 1;

    final public static int RIGHT = 2;

    final public static int DOWN = 3;

    private Point myPosition;

    private Point myDesiredPos = GUIConstants.GUI_ORGIN;


    public Position() {
        myPosition = GUIConstants.GUI_ORGIN;
    }


    public Point getGUIPosition() {
        return myPosition;
    }

    /**
     * Sets the position to the desired position.
     */
    public void movePosition() {
        myPosition = myDesiredPos;
    }

    /**
     * Converts Position GUI Position into the Models Position
     *
     * @return Point Coordinates of the models position.
     */
    public Point getModelPosition(){
        return new Point(((myPosition.y - 465) / 50),(myPosition.x - 365) / 50);
    }


    /**
     * Accepts a direction and sets the Position that direction would lead too.
     *
     * @param theDirection he user intends to go.
     */
    public void setDesiredPosition(int theDirection) {
        myDesiredPos = getDesiredPosition(theDirection);
    }

    /**
     * Accepts a direction and finds the Position that direction would lead too.
     *
     * @param  theDirection the user intends to go
     * @return the coordinates of the intended position.
     */
    private Point getDesiredPosition(int theDirection) {
        Point desiredPos;
        if (theDirection == LEFT) {
            desiredPos = posToLeft();
        } else if (theDirection == UP) {
            desiredPos = posToUp();
        } else if (theDirection == RIGHT) {
            desiredPos = posToRight();
        } else if (theDirection == DOWN) {
            desiredPos = posToDown();
        } else {
            throw new IllegalArgumentException("INVALID DIRECTION");
        }
        return desiredPos;
    }

    /**
     * Moves the position of the
     *
     * @return
     */
    public Point posToLeft() {
        return new Point(myPosition.x - 50, myPosition.y);
    }

    public Point posToUp() {
        return new Point(myPosition.x, myPosition.y - 50);
    }

    public Point posToRight() {
        return new Point(myPosition.x + 50, myPosition.y);
    }

    public Point posToDown() {
        return new Point(myPosition.x, myPosition.y + 50);
    }

}
