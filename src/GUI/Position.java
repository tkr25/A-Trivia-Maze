package GUI;

import java.awt.*;

public class Position {
    public static Point UP = new Point(0,-50);
    public static Point LEFT = new Point(-50,0);
    public static Point RIGHT = new Point(50,0);
    public static Point DOWN = new Point(0,50);

    Point myPosition;

    Position() {
        myPosition = new Point (365, 465);
    }

    public void changePositions(Point theDirection) {
        myPosition.x += theDirection.x;
        myPosition.y += theDirection.y;
    }

    public Point convertPoisition() {
        return new Point(((myPosition.y - 465) / 50),(myPosition.x - 365) / 50);
        //return new Point(((myPosition.x - 365) / 50), ((myPosition.y - 415) / 50));
    }

//    public Point setPosition(Point theModelUnits) {
//
//    }
}