package model;

public abstract class AttackStrategy {
    public abstract DamageMatrix previewAttack(Position pos);
    public void attack(Model grid, Position pos) {
        //TODO:
    }
}
