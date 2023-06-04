package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMaze{
    //test moving validator
    //invalid selections
    @Test
    void testFalseMoveLeftWhenAtStart() throws Exception {
        final Maze testMaze = new Maze();
        assertFalse(testMaze.canMove(Direction.LEFT.getMyDirectionNumber()));
    }
    @Test
    void testFalseMoveLeftWhenAtLeftEdgeBottom() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow(4);
        assertFalse(testMaze.canMove(Direction.LEFT.getMyDirectionNumber()));
    }
    @Test
    void testFalseMoveRightOnRightEdgeTop() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyCol( 4);
        assertFalse(testMaze.canMove(Direction.RIGHT.getMyDirectionNumber()));
    }
    @Test
    void testFalseMoveRightAtFinish() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyCol( 4);
        testMaze.setMyRow( 4);
        assertFalse(testMaze.canMove(Direction.RIGHT.getMyDirectionNumber()));
    }
    @Test
    void testFalseMoveUpAtTopLeft() throws Exception {
        final Maze testMaze = new Maze();
        assertFalse(testMaze.canMove(Direction.LEFT.getMyDirectionNumber()));
    }
    @Test
    void testFalseMoveUpAtTopRight() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyCol( 4);
        assertFalse(testMaze.canMove(testMaze.moveUp()));
    }
    @Test
    void testFalseMoveDownBottomLeft() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 4);
        assertFalse(testMaze.canMove(testMaze.moveDown()));
    }@Test
    void testFalseMoveDownAtFinish() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 4);
        testMaze.setMyCol( 4);
        assertFalse(testMaze.canMove( testMaze.moveDown()));
    }
    //valid selections
    @Test
    void testTrueMoveLeft() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyCol( 1);
        assertTrue(testMaze.canMove( Direction.LEFT.getMyDirectionNumber()));
    }
    @Test
    void testTrueMoveRight() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.canMove( Direction.RIGHT.getMyDirectionNumber()));
    }
    @Test
    void testTrueMoveUp() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 1);
        assertTrue(testMaze.canMove( Direction.UP.getMyDirectionNumber()));
    }
    @Test
    void testTrueMoveDown() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.canMove( Direction.DOWN.getMyDirectionNumber()));
    }
    //test selection validator
    //valid selections
    @Test
    void testValidSelectionFromStartToFinish() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.validateSelection( 4,4));
    }
    @Test
    void testValidSelectionFromStartToMiddle() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.validateSelection( 2,3));
    }
    @Test
    void testValidSelectionFromStartToMiddleAgain() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.validateSelection( 3,2));
    }
    @Test
    void testValidSelectionFromStartToTopRight() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.validateSelection( 0, 4));
    }
    @Test
    void testValidSelectionFromStartToBottomLeft() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(testMaze.validateSelection( 4, 0));
    }
    @Test
    void testValidSelectionFromMiddleToStart() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 2);
        testMaze.setMyCol( 3);
        assertTrue(testMaze.validateSelection( 0, 0));
    }
    @Test
    void testValidSelectionFromMiddleToFinish() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 3);
        testMaze.setMyCol( 2);
        assertTrue(testMaze.validateSelection( 4, 4));
    }
    @Test
    void testValidSelectionFromMiddleToTopRight() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 2);
        testMaze.setMyCol( 3);
        assertTrue(testMaze.validateSelection( 0, 4));
    }
    @Test
    void testValidSelectionFromMiddleToBottomLeft() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 3);
        testMaze.setMyCol( 2);
        assertTrue(testMaze.validateSelection( 4, 0));
    }
    //invalid selections
    @Test
    void testInvalidSelectionFromMiddleTooFarUp() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 3);
        testMaze.setMyCol( 2);
        assertFalse(testMaze.validateSelection( -1, 0));
    }
    @Test
    void testInvalidSelectionFromMiddleTooFarDown() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 3);
        testMaze.setMyCol( 2);
        assertFalse(testMaze.validateSelection( 5, 0));
    }
    @Test
    void testInvalidSelectionFromMiddleTooFarRight() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 3);
        testMaze.setMyCol( 2);
        assertFalse(testMaze.validateSelection( 0, 5));
    }
    @Test
    void testInvalidSelectionFromMiddleTooFarLeft() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow( 3);
        testMaze.setMyCol( 2);
        assertFalse(testMaze.validateSelection( 4, -1));
    }
    @Test
    void testInvalidSelectionFromStartTooFarUp() throws Exception {
        final Maze testMaze = new Maze();
        assertFalse(testMaze.validateSelection( -1, 0));
    }
    @Test
    void testInvalidSelectionFromStartTooFarDown() throws Exception {
        final Maze testMaze = new Maze();
        assertFalse(testMaze.validateSelection( 6, 0));
    }
    @Test
    void testInvalidSelectionFromStartTooFarRight() throws Exception {
        final Maze testMaze = new Maze();
        assertFalse(testMaze.validateSelection( 4, 6));
    }
    @Test
    void testInvalidSelectionFromStartTooFarLeft() throws Exception {
        final Maze testMaze = new Maze();
        assertFalse(testMaze.validateSelection( 4, -1));
    }
    //Test Moving
    //valid selections for position
    @Test
    void testMoveRight() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.movePosition( Direction.RIGHT.getMyDirectionNumber());
        assertEquals(1, testMaze.getMyCol());
    }
    @Test
    void testMoveLeft() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyCol(1);
        testMaze.movePosition(Direction.LEFT.getMyDirectionNumber());
        assertEquals(0, testMaze.getMyCol());
    }
    @Test
    void testMoveUp() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow(1);
        testMaze.movePosition( Direction.UP.getMyDirectionNumber());
        assertEquals(0, testMaze.getMyRow());
    }
    @Test
    void testMoveDown() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.movePosition( Direction.DOWN.getMyDirectionNumber());
        assertEquals(1, testMaze.getMyRow());
    }
    @Test
    void testMoveFromCenterUp() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow(2);
        testMaze.setMyCol(3);
        testMaze.movePosition( Direction.UP.getMyDirectionNumber());
        assertEquals(1, testMaze.getMyRow());
    }
    @Test
    void testMoveFromCenterDown() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow(2);
        testMaze.setMyCol(3);
        testMaze.movePosition( Direction.DOWN.getMyDirectionNumber());
        assertEquals(3, testMaze.getMyRow());
    }
    @Test
    void testMoveFromCenterLeft() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow(2);
        testMaze.setMyCol(3);
        testMaze.movePosition( Direction.LEFT.getMyDirectionNumber());
        assertEquals(2, testMaze.getMyCol());
    }
    @Test
    void testMoveFromCenterRight() throws Exception {
        final Maze testMaze = new Maze();
        testMaze.setMyRow(2);
        testMaze.setMyCol(3);
        testMaze.movePosition( Direction.RIGHT.getMyDirectionNumber());
        assertEquals(4, testMaze.getMyCol());
    }
    //test move with valid door state
    @Test
    void testMoveValidDoorStateZero() throws Exception {
        final Maze testMaze = new Maze();
        final Door door = testMaze.getAnyDoor(0, 1);
        door.setDoorState(0);
        testMaze.movePosition( Direction.RIGHT.getMyDirectionNumber());
        assertEquals(1, testMaze.getMyCol());
    }
    @Test
    void testMoveValidDoorState() throws Exception {
        final Maze testMaze = new Maze();
        final Door door = testMaze.getAnyDoor(0, 1);
        door.setDoorState(1);
        testMaze.movePosition( Direction.RIGHT.getMyDirectionNumber());
        assertEquals(1, testMaze.getMyCol());
    }
    // test move with invalid door state
    @Test
    void testMoveInValidDoorStateTwo() throws Exception {
        final Maze testMaze = new Maze();
        final Door door = testMaze.getAnyDoor(0, 1);
        door.setDoorState(2);
        testMaze.movePosition( Direction.RIGHT.getMyDirectionNumber());
        assertEquals(0, testMaze.getMyCol());
    }
    @Test
    void testMoveInValidDoorStateThree() throws Exception {
        final Maze testMaze = new Maze();
        final Door door = testMaze.getAnyDoor(0, 1);
        door.setDoorState(3);
        testMaze.movePosition( Direction.RIGHT.getMyDirectionNumber());
        assertEquals(0, testMaze.getMyCol());
    }

    //test can finish maze
    //valid finish expected
    @Test
    void testCanFinishMazeStart() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(Maze.canFinishMaze(testMaze, 0, 0));
    }
    @Test
    void testCanFinishMazeCenter() throws Exception {
        final Maze testMaze = new Maze();
        assertTrue(Maze.canFinishMaze(testMaze, 2, 3));
    }
    //invalid finish maze
    @Test
    void testCanNotFinishMazeCenter() throws Exception {
        final Maze testMaze = new Maze();
        final Door door = testMaze.getAnyDoor(3, 4);
        door.setDoorState(2);
        final Door door2 = testMaze.getAnyDoor(4, 3);
        door2.setDoorState(2);
        assertFalse(Maze.canFinishMaze(testMaze, 2, 3));
    }
    @Test
    void testCanNotFinishMazeStart() throws Exception {
        final Maze testMaze = new Maze();
        final Door door = testMaze.getAnyDoor(3, 4);
        door.setDoorState(2);
        final Door door2 = testMaze.getAnyDoor(4, 3);
        door2.setDoorState(2);
        assertFalse(Maze.canFinishMaze(testMaze, 0, 0));
    }
}