package model;

public class AttackNorth implements AttackStrategy {
    private int damage;

    public AttackNorth(int damage) {
        this.damage = damage;
    }

    @Override
    public DamageMatrix planAttack(Model grid, Position pos) {
        return new DamageMatrix();
    }

    @Override
    public void attack(Model grid, Position pos) {
        Position p = null;
        try {
            p = new Position(pos.getX(), pos.getY() - 1);
        }
        catch (OutsideOfTheGrid o) {
            return;
        }
        grid.inflictDamage(p, this.damage);
    }
}
