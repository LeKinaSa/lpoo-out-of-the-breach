package out_of_the_breach.model;

/*
    Represents the position of the entity in the grid.
 */

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) throws OutsideOfTheGrid {
        if ((0 <= x) && (x < 8) && (0 <= y) && (y < 8)) {
            this.x = x;
            this.y = y;
        }
        else {
            throw(new OutsideOfTheGrid());
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /*
        Get the index for accessing a linear matrix with the position.
     */
    public int getLinearMatrixPosition() {
        return this.x + this.y * 8;
    }

    public void setPosition(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    /*
        Determine whether two positions are located in the same tile in the grid.
     */
    public boolean same(Position pos) {
        return ((this.x == pos.x) && (this.y == pos.y));
    }

    /*
        Obtain the distance between two positions.
     */
    public int distanceTo(Position pos) {
        return Math.abs(pos.getX() - this.x) + Math.abs(pos.getY() - this.y);
    }

    /*
        Obtain the position from the current one using offsets on x and y.
     */
    public Position adjacentPos(int offsetX, int offsetY) {
        Position p;
        try {
            p = new Position(this.getX() + offsetX, this.getY() + offsetY);
        }
        catch (OutsideOfTheGrid o) {
            return null;
        }
        return p;
    }

    /*
        Obtain the position north from the current one.
     */
    public Position north() {
        return this.adjacentPos(0, -1);
    }

    /*
        Obtain the position south from the current one.
     */
    public Position south() {
        return this.adjacentPos(0, 1);
    }

    /*
        Obtain the position east from the current one.
     */
    public Position east() {
        return this.adjacentPos(1, 0);
    }

    /*
        Obtain the position west from the current one.
     */
    public Position west() {
        return this.adjacentPos(-1, 0);
    }

    /*
        Obtain the position in the chosen direction from the current one.
     */
    public Position adjacentPos(AttackDirection d) {
        switch (d) {
            case NORTH:
                return this.north();
            case SOUTH:
                return this.south();
            case EAST:
                return this.east();
            case WEST:
                return this.west();

            case L_NORTHEAST:
                try {
                    return this.north().north().east();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_NORTHWEST:
                try {
                    return this.north().north().west();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_SOUTHEAST:
                try {
                    return this.south().south().east();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_SOUTHWEST:
                try {
                    return this.south().south().west();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_EASTNORTH:
                try {
                    return this.east().east().north();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_EASTSOUTH:
                try {
                    return this.east().east().south();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_WESTNORTH:
                try {
                    return this.west().west().north();
                }
                catch (NullPointerException n) {
                    return null;
                }
            case L_WESTSOUTH:
                try {
                    return this.west().west().south();
                }
                catch (NullPointerException n) {
                    return null;
                }
            default:
                return this;
        }
    }
}
