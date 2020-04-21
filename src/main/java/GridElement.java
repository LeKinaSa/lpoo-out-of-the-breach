public abstract class GridElement {
    private Position pos;

    public GridElement(Position pos) {
        this.pos = pos;
    }

    public abstract void draw();
}
