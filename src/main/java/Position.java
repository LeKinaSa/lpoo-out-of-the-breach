public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean insideSquare(Position minimum, Position maximum) {
        return ((minimum.x < this.x) && (minimum.y < this.y)
                && (this.x <= maximum.x) && (this.y <= maximum.y));
    }

    public boolean insideCircle(Position center, int radius) {
        if ((this.x == center.x) && (this.y == center.y)) {
            return false;
        }
        float distance = (this.x - center.x)^2 + (this.y - center.y)^2;
        float radius_pow = (float)(radius) * (float)(radius);
        return distance < radius_pow;
    }
}
