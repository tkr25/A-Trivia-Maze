package Model;

import GUI.GUIConstants;

import java.awt.*;
import java.io.Serializable;

public class Position implements Serializable {
    /** int for direction Left **/

    final public static int LEFT = 0;
    /** int for direction up **/
    final public static int UP = 1;
    /** int for direction Right **/
    final public static int RIGHT = 2;
    /** int for direction Down **/
    final public static int DOWN = 3;
    /** Point to store Position **/
    private Point myPosition;
    /** stores point of where you want to go **/
    private Point myDesiredPos = new Point (365, 465);

    /**
     * Position constructor iniates to top left position of map.
     */
    public Position() {
        //    myPosition = GUIConstants.GUI_ORGIN;
        myPosition = new Point (365, 465);
    }

    /**
     * constructs a position to any point passed in.
     * @param thePoint the point to be created.
     */
    public Position(final Point thePoint) {
        //    myPosition = GUIConstants.GUI_ORGIN;
        myPosition = new Point (thePoint);
    }

    /**
     * returns current position.
     * @return myPosition.
     */
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
     * Converts Position GUI Position into the Models Position.
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
     * @param  theDirection the user intends to go.
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
     * Moves the position to the left.
     *
     * @return a point to the left of current position.
     */
    public Point posToLeft() {
        return new Point(myPosition.x - 50, myPosition.y);
    }
    /**
     * Moves the position up.
     *
     * @return a point to the above current position.
     */
    public Point posToUp() {
        return new Point(myPosition.x, myPosition.y - 50);
    }
    /**
     * Moves the position of the right.
     *
     * @return a point to the right of current position.
     */
    public Point posToRight() {
        return new Point(myPosition.x + 50, myPosition.y);
    }
    /**
     * Moves the position down.
     *
     * @return a point to the below current position.
     */
    public Point posToDown() {
        return new Point(myPosition.x, myPosition.y + 50);
    }
    /**
     * for testing sets the position to the point passed in.
     *
     */
    public void setPosition(final Point thePosition) {
        myPosition = thePosition;
    }

    /**
     * for testing returns current position.
     * @return point of current position.
     */
    //for testing
    public Point getPosition() {
        return myPosition;
    }
}
