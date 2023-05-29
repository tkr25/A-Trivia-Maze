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
}