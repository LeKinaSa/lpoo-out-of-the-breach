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

    public Position north() {
        Position p;
        try {
            p = new Position(this.getX(), this.getY() - 1);
        }
        catch (OutsideOfTheGrid o) {
            return null;
        }
        return p;
    }

    public Position south() {
        Position p;
        try {
            p = new Position(this.getX(), this.getY() + 1);
        }
        catch (OutsideOfTheGrid o) {
            return null;
        }
        return p;
    }

    public Position east() {
        Position p;
        try {
            p = new Position(this.getX() + 1, this.getY());
        }
        catch (OutsideOfTheGrid o) {
            return null;
        }
        return p;
    }

    public Position west() {
        Position p;
        try {
            p = new Position(this.getX() - 1, this.getY());
        }
        catch (OutsideOfTheGrid o) {
            return null;
        }
        return p;
    }

}
