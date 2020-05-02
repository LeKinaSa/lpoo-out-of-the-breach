package model;

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
        Position p = null;
        try {
            p = new Position(pos.getX() + 1, pos.getY());
        }
        catch (OutsideOfTheGrid o) {
            return;
        }
        grid.inflictDamage(p, this.damage);
    }
}

