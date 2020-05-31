package out_of_the_breach.model;

/*
    Exception used when the Position is generated outside of the grid.
    If x or y are out of the grid, this exception will be thrown.
 */

public class OutsideOfTheGrid extends Exception {
    public OutsideOfTheGrid() {}
}
