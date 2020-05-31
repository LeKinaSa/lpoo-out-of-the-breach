package out_of_the_breach.model;

/*
    Strategies for the Attacks.
    Both heros and enemies can use this attack strategies.
    The strategy chosen will impact which tiles will or won't take damage.
 */

public abstract class AttackStrategy {
    private AttackDirection direction;

    protected AttackStrategy(AttackDirection direction) {
        this.direction = direction;
    }

    protected void setDirection(AttackDirection direction) {
        this.direction = direction;
    }

    public AttackDirection getDirection() {
        return this.direction;
    }

    /*
        Obtain the damage to be inflicted on the tiles on the attack.
     */
    public abstract DamageMatrix previewAttack(Position pos);

    /*
        Attack and inflict damage to the targeted tiles.
     */
    public void attack(GameModel grid, Position pos) {
        DamageMatrix damageMatrix = this.previewAttack(pos);
        Position p;
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                try {
                    p = new Position(x, y);
                }
                catch (OutsideOfTheGrid o) {
                    // Impossible to get here
                    o.printStackTrace();
                    continue;
                }
                grid.inflictDamage(p, damageMatrix.getDamage(p));
            }
        }
    }
}
