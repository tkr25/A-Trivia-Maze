package Model;



public enum Direction {

    /**
     * UP on the screen.
     */
    UP('3'),

    /**
     * Left on the screen.
     */
    LEFT('2'),

    /**
     * Right on the screen.
     */
    RIGHT('0'),

    /**
     * Down on the screen.
     */
    DOWN('1');


    /**
     * The number corresponding to a particular value of the enumeration.
     */
    private final int myDirectionNumber;

    // Constructor

    /**
     * Constructs a new Direction with the specified number.
     *
     * @param theNumber The number.
     */
    Direction(final int theNumber) {
        myDirectionNumber = theNumber;
    }
    public int getMyDirectionNumber() {
        return myDirectionNumber;
    }
}
