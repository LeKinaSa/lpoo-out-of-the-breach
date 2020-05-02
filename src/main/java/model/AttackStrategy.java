package model;

public interface AttackStrategy {
    DamageMatrix planAttack(Model grid, Position pos);
    void attack(Model grid, Position pos);
}
