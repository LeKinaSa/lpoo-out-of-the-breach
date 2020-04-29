package model;

public class AttackSouth implements AttackStrategy {
    private int damage;

    public AttackSouth(int damage) {
        this.damage = damage;
    }

    @Override
    public DamageMatrix planAttack(Grid grid, Position pos) {
        return new DamageMatrix();
    }

    @Override
    public void attack(Grid grid, Position pos) {
        grid.inflictDamage(new Position(pos.getX(), pos.getY() + 1), this.damage);
    }
}
