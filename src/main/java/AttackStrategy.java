import model.DamageMatrix;

public interface AttackStrategy {
    DamageMatrix planAttack(Grid grid, Position pos);
    void attack(Grid grid, Position pos);
}
