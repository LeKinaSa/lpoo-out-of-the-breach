package model;

public class NoAttack implements AttackStrategy {
    public NoAttack() {}

    @Override
    public DamageMatrix planAttack(Model grid, Position pos) {
        return new DamageMatrix();
    }

    @Override
    public void attack(Model grid, Position pos) {}
}
