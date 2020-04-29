public abstract class GridElement extends GridComponent {
    public GridElement(Position pos) {
        super(pos);
    }

    public abstract void takeDamage(int damage);

    public abstract boolean isDead();
}
