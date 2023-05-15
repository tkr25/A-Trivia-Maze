package GUI;

import java.awt.*;

public class Position {
    public static Point UP = new Point(0,-50);
    public static Point LEFT = new Point(-50,0);
    public static Point RIGHT = new Point(50,0);
    public static Point DOWN = new Point(0,50);

    Point myPosition;

    Position() {
        myPosition = new Point (475, 475);
    }

    public Point changePositions(Point theDirection) {
        myPosition.x += theDirection.x;
        myPosition.y += theDirection.y;
        return myPosition;
    }
}
