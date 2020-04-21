public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean inside(Position minimum, Position maximum) {
        return ((minimum.x < this.x) && (minimum.y < this.y)
                && (this.x <= maximum.x) && (this.y <= maximum.y));
    }
}
