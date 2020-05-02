package model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        if ((0 <= x) && (x < 8) && (0 <= y) && (y < 8)) {
            this.x = x;
            this.y = y;
        }
        else {
            // exception
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
}
