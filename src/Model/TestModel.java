package Model;
import GUI.*;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestModel {
    public static Point TOP_RIGHT = new Point(565, 465);
    public static Point MIDDLE  = new Point(465,565);
    public static Point FINISH = new Point(565, 665);
    public static Point LEFT_OF_FINISH = new Point(515, 665);
    public static Point ABOVE_FINISH = new Point(565, 615);
    public static Point BOTTOM_LEFT = new Point(365, 665 );
    public static Point START = new Point(365, 465);
    public static Point RIGHT_OF_START = new Point(415, 465);
    public static Point BELOW_START = new Point(365, 515);
    public static Point ABOVE_MIDDLE  = new Point(465,515);
    public static Point BELOW_MIDDLE  = new Point(465,615);
    public static Point RIGHT_OF_MIDDLE  = new Point(515,565);
    public static Point LEFT_OF_MIDDLE  = new Point(415,565);

    //Testing for boundary Walls
    @Test
    void testBoundariesWallLeftOfStart() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        assertFalse(testMaze.getDoorFromMyRoom(Position.LEFT).canTravel());
    }
    @Test
    void testBoundariesWallAboveStart() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        assertFalse(testMaze.getDoorFromMyRoom(Position.UP).canTravel());
    }
    @Test
    void testBoundariesWallLeftOfBottomLeftCorner() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(BOTTOM_LEFT);
        assertFalse(testMaze.getDoorFromMyRoom(Position.LEFT).canTravel());
    }
    @Test
    void testBoundariesWallBelowBottomLeftCorner() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(BOTTOM_LEFT);
        assertFalse(testMaze.getDoorFromMyRoom(Position.DOWN).canTravel());
    }@Test
    void testBoundariesWallRightOfFinish() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(FINISH);
        assertFalse(testMaze.getDoorFromMyRoom(Position.RIGHT).canTravel());
    }
@Test
    void testBoundariesWallBelowFinish() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(FINISH);
        assertFalse(testMaze.getDoorFromMyRoom(Position.DOWN).canTravel());
    }
    @Test
    void testBoundariesWallAboveRightCorner() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(TOP_RIGHT);
        assertFalse(testMaze.getDoorFromMyRoom(Position.UP).canTravel());
    }
    @Test
    void testBoundariesWallRightOfRightCorner() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(TOP_RIGHT);
        assertFalse(testMaze.getDoorFromMyRoom(Position.RIGHT).canTravel());
    }
    //test position movement
    @Test
    void testMoveRightFromStart() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setDesiredPosition(Position.RIGHT);
        testPosition.movePosition();
        assertEquals(RIGHT_OF_START,testPosition.getPosition());
    }
    @Test
    void testMoveDownFromStart() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setDesiredPosition(Position.DOWN);
        testPosition.movePosition();
        assertEquals(BELOW_START,testPosition.getPosition());
    }
    @Test
    void testMoveLeftToStart() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setPosition(RIGHT_OF_START);
        testPosition.setDesiredPosition(Position.LEFT);
        testPosition.movePosition();
        assertEquals(START,testPosition.getPosition());
    }
    @Test
    void testMoveUpToStart() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setPosition(BELOW_START);
        testPosition.setDesiredPosition(Position.UP);
        testPosition.movePosition();
        assertEquals(START,testPosition.getPosition());
    }
    @Test
    void testMoveUpFromMiddle() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setPosition(MIDDLE);
        testPosition.setDesiredPosition(Position.UP);
        testPosition.movePosition();
        assertEquals(ABOVE_MIDDLE,testPosition.getPosition());
    }
    @Test
    void testMoveDownFromMiddle() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setPosition(MIDDLE);
        testPosition.setDesiredPosition(Position.DOWN);
        testPosition.movePosition();
        assertEquals(BELOW_MIDDLE,testPosition.getPosition());
    }
    @Test
    void testMovRightFromMiddle() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setPosition(MIDDLE);
        testPosition.setDesiredPosition(Position.RIGHT);
        testPosition.movePosition();
        assertEquals(RIGHT_OF_MIDDLE,testPosition.getPosition());
    }
    @Test
    void testMoveLeftFromMiddle() throws Exception {
        Position testPosition = new Position();//new position like a new map
        testPosition.setPosition(MIDDLE);
        testPosition.setDesiredPosition(Position.LEFT);
        testPosition.movePosition();
        assertEquals(LEFT_OF_MIDDLE,testPosition.getPosition());
    }

    //test doors
    // can't set it to illegal state
    @Test
    void testDoorStateTooHigh() throws Exception {
        Door door = new Door(GUIConstants.ONE_PIECE);
        door.setMyDoorState(5);
        assertEquals(0, door.getDoorState());
    }
    @Test
    void testDoorStateTooLow() throws Exception {
        Door door = new Door(GUIConstants.ONE_PIECE);
        door.setMyDoorState(-1);
        assertEquals(0, door.getDoorState());
    }
    //can't travel through locked door or wall
    @Test
    void testCanPassThroughWall() throws Exception {
        Door door = new Door(GUIConstants.ONE_PIECE);
        door.setMyDoorState(Door.WALL);
        assertFalse(door.canTravel());
    }
    @Test
    void testCanPassThroughLockedDoor() throws Exception {
        Door door = new Door(GUIConstants.ONE_PIECE);
        door.setMyDoorState(Door.LOCKED);
        assertFalse(door.canTravel());
    }
    //Can travel through unknown or unlocked door
    @Test
    void testCanPassThroughUnanswered() throws Exception {
        Door door = new Door(GUIConstants.ONE_PIECE);
        door.setMyDoorState(Door.UNKNOWN);
        assertTrue(door.canTravel());
    }
    @Test
    void testCanPassThroughAnswered() throws Exception {
        Door door = new Door(GUIConstants.ONE_PIECE);
        door.setMyDoorState(Door.UNLOCKED);
        assertTrue(door.canTravel());
    }

//    //test can finish maze
//    //valid finish expected
    @Test
    void testCanFinishMazeStart() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        assertTrue(testMaze.isThereWayOut(testMaze.toString()));
    }
    @Test
    void testCanFinishMazeCenter() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(MIDDLE);
        assertTrue(testMaze.isThereWayOut(testMaze.toString()));
    }
    //Locked doors can't finish maze
    @Test
    void testCanFinishLockedOutAtStart() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(ABOVE_FINISH);
        testMaze.lockDoor(testMaze.getDoorFromMyRoom(Position.DOWN));
        testMaze.setPositionMaze(LEFT_OF_FINISH);
        testMaze.lockDoor(testMaze.getDoorFromMyRoom(Position.RIGHT));
        testMaze.setPositionMaze(START);
        assertFalse(testMaze.isThereWayOut(testMaze.toString()));
    }
    @Test
    void testCanFinishLockedOutAtCenter() throws Exception {
        final Maze testMaze = new Maze(GUIConstants.ONE_PIECE);
        testMaze.setPositionMaze(ABOVE_FINISH);
        testMaze.lockDoor(testMaze.getDoorFromMyRoom(Position.DOWN));
        testMaze.setPositionMaze(LEFT_OF_FINISH);
        testMaze.lockDoor(testMaze.getDoorFromMyRoom(Position.RIGHT));
        testMaze.setPositionMaze(MIDDLE);
        assertFalse(testMaze.isThereWayOut(testMaze.toString()));
    }

}