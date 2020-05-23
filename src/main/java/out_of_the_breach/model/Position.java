package out_of_the_breach.model;

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

    public int getLinearMatrixPosition() {
        return this.x + this.y * 8;
    }

    public void setPosition(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public boolean same(Position pos) {
        return ((this.x == pos.x) && (this.y == pos.y));
    }

    public int distanceTo(Position pos) {
        return Math.abs(pos.x - this.x) + Math.abs(pos.y - this.y);
    }

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

    public Position north() {
        return this.adjacentPos(0, -1);
    }

    public Position south() {
        return this.adjacentPos(0, 1);
    }

    public Position east() {
        return this.adjacentPos(1, 0);
    }

    public Position west() {
        return this.adjacentPos(-1, 0);
    }

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
                return this.north().north().east();
            case L_NORTHWEST:
                return this.north().north().west();
            case L_SOUTHEAST:
                return this.south().south().east();
            case L_SOUTHWEST:
                return this.south().south().west();
            case L_EASTNORTH:
                return this.east().east().north();
            case L_EASTSOUTH:
                return this.east().east().south();
            case L_WESTNORTH:
                return this.west().west().north();
            case L_WESTSOUTH:
                return this.west().west().south();

            default:
                return this;
        }
    }

}
