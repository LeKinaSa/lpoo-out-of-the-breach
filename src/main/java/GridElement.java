public abstract class GridElement {
    private Position pos;

    public GridElement(Position pos) {
        this.pos = pos;
    }

    public abstract void draw();

    public abstract void takeDamage(int damage);

    public boolean insideGrid(int x, int y) {
        return (this.pos.inside(new Position(0, 0), new Position(x, y)));
    }
}
