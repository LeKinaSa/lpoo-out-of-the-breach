package model;

public class MeleeAttack extends AttackStrategy {
    private int damage;
    private AttackDirection direction;

    public MeleeAttack(int damage, AttackDirection direction) {
        this.damage = damage;
        this.direction = direction;
    }

    @Override
    public Position getDamagedPosition(Position pos) {
        Position p;
        switch(this.direction) {
            case NORTH:
                try {
                    p = new Position(pos.getX(), pos.getY() - 1);
                }
                catch (OutsideOfTheGrid o) {
                    return null;
                }
                return p;
            case SOUTH:
                try {
                    p = new Position(pos.getX(), pos.getY() + 1);
                }
                catch (OutsideOfTheGrid o) {
                    return null;
                }
                return p;
            case EAST:
                try {
                    p = new Position(pos.getX() + 1, pos.getY());
                }
                catch (OutsideOfTheGrid o) {
                    return null;
                }
                return p;
            case WEST:
                try {
                    p = new Position(pos.getX() - 1, pos.getY());
                }
                catch (OutsideOfTheGrid o) {
                    return null;
                }
                return p;
            default:
                return null;
        }
    }

    @Override
    public DamageMatrix previewAttack(Position pos) {
        DamageMatrix damageMatrix = new DamageMatrix();
        Position damagedPosition = this.getDamagedPosition(pos);
        if (damagedPosition == null) {
            return damageMatrix;
        }
        damageMatrix.setDamage(damagedPosition, this.damage);
        return damageMatrix;
    }
}

