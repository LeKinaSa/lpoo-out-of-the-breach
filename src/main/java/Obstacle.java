public class Obstacle extends GridElement {

    public Obstacle(Position pos) {
        super(pos);
    }

    @Override
    public void draw() {}

    @Override
    public void takeDamage(int damage) {}

    @Override
    public boolean isDead() {
        return false;
    }
}
