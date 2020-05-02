package model;

public abstract class AttackStrategy {
    public abstract Position getDamagedPosition(Position pos);
    public abstract DamageMatrix previewAttack(Position pos);

    public void attack(Model grid, Position pos) {
        DamageMatrix damageMatrix = this.previewAttack(pos);
        Position p = null;
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
