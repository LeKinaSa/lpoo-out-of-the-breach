package model;

public abstract class AttackStrategy {
    private AttackDirection direction;

    protected AttackStrategy(AttackDirection direction) {
        this.direction = direction;
    }

    public AttackDirection getDirection() {
        return this.direction;
    }
    protected void setDirection(AttackDirection direction) {
        this.direction = direction;
    }

    public abstract Position getDamagedPosition(Position pos);
    public abstract DamageMatrix previewAttack(Position pos);

    public void attack(Model grid, Position pos) {
        DamageMatrix damageMatrix = this.previewAttack(pos);
        Position p;
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                try {
                    p = new Position(x, y);
                }
                catch (OutsideOfTheGrid o) {
                    continue;
                }
                grid.inflictDamage(p, damageMatrix.getDamage(p));
            }
        }
    }
}
