import model.DamageMatrix;

public class AttackEast implements AttackStrategy {
    private int damage;

    public AttackEast(int damage) {
        this.damage = damage;
    }

    @Override
    public DamageMatrix planAttack(Grid grid, Position pos) {
        return new DamageMatrix();
    }

    @Override
    public void attack(Grid grid, Position pos) {
        grid.inflictDamage(new Position(pos.getX() + 1, pos.getY()), damage);
    }
}

