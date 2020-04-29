package model;

public class NoAttack implements AttackStrategy {
    private int damage;

    public NoAttack() {}

    @Override
    public DamageMatrix planAttack(Grid grid, Position pos) {
        return new DamageMatrix();
    }

    @Override
    public void attack(Grid grid, Position pos) {}
}
