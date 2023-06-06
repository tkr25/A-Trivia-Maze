package GUI;

import java.awt.*;
import java.io.Serializable;

public class Position implements Serializable {
    public static Point UP = new Point(0,-50);
    public static Point LEFT = new Point(-50,0);
    public static Point RIGHT = new Point(50,0);
    public static Point DOWN = new Point(0,50);

    Point myPosition;

    Position() {
        myPosition = new Point (365, 465);
    }

    Position(Point thePos) {
        myPosition = thePos;
    }

    public void changePositions(Point theDirection) {
        myPosition.x += theDirection.x;
        myPosition.y += theDirection.y;
    }

    public Point convertPoisition() {
        return new Point(((myPosition.y - 465) / 50),(myPosition.x - 365) / 50);
    }

}