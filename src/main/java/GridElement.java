public abstract class GridElement {
    private Position pos;

    public GridElement(Position pos) {
        this.pos = pos;
    }

    public abstract void draw();

    public boolean insideGrid(int x, int y) {
        return (this.pos.insideRectangle(new Position(0, 0), new Position(x, y)));
    }
}
