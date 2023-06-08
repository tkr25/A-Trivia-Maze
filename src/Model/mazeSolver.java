package Model;

public class mazeSolver {
    public static void main(String theArgs) {
    }

    public static boolean solver(String theMazeInStringFormat) {
        int size = 11;
        char[][] maze = new char[size][size];
        String theMaze = theMazeInStringFormat;
        int row = 0;
        while (theMaze.length() > size) {
            maze[row] = theMaze.substring(0, size).toCharArray();
            theMaze = theMaze.substring(size + 1);
            row++;
        }
        return doMaze(maze, 1, 1);
    }

    /**
     * This method used to print the maze to the screen
     *
     *@param theMaze is a 2D list to hold the maze
     */
    private static void printMaze(char[][] theMaze) {
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                System.out.print(theMaze[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * recursive method that calls itself
     * This method is used to move search the maze
     *
     *@param theMaze is a 2D list to hold the maze
     *@param theRow is the index for the row to be searched
     *@param theCol is the index for the col to be searched
     *
     *@return a boolean for if the maze is/can be solved
     */
    private static boolean doMaze(char[][] theMaze,
                                  int theRow, int theCol) {
        boolean found = false;
        int north = theRow - 1;
        int south = theRow + 1;
        int east = theCol + 1;
        int west = theCol - 1;

        if (theMaze[theRow][theCol] == 'w') {
            theMaze[theRow][theCol] = '*';
            found = true;
        } else {
            theMaze[theRow][theCol] = '*';
            if (canMove(theMaze, north, theCol)) {
                if (doMaze(theMaze, north, theCol)) {
                    found = true;
                } else {
                    theMaze[north][theCol] = '-';
                }
            }
            if (canMove(theMaze, theRow, east) && !found) {
                if (doMaze(theMaze, theRow, east)) {
                    found = true;
                } else {
                    theMaze[theRow][east] = '-';
                }
            }
            if (canMove(theMaze, south, theCol) && !found) {
                if (doMaze(theMaze, south, theCol)) {
                    found = true;
                } else {
                    theMaze[south][theCol] = '-';
                }
            }
            if (canMove(theMaze, theRow, west) && !found) {
                if (doMaze(theMaze, theRow, west)) {
                    found = true;
                } else {
                    theMaze[theRow][west] = '-';
                }
            }
        }
        return found;
    }

    /**
     * This method is used to check if the next move is open
     *
     *@param theMaze is a 2D list to hold the maze
     *@param theRow is the index for the row to be searched
     *@param theCol is the index for the col to be searched
     *
     *@return a boolean for if the move is avaiable
     */
    private static boolean canMove(char[][] theMaze,
                                   int theRow, int theCol) {
        char spot = theMaze[theRow][theCol];
        return spot == '0' || spot == '1' || spot == ' ' || spot == 'w';
    }
}