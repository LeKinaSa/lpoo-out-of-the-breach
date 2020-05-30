package out_of_the_breach.model;

/*
    Enemies will move and attack automatically without any need of interaction with the user.
 */

public abstract class Enemy extends Entity {
    private AttackStrategy strategy;

    public Enemy(Position pos, int hp, AttackStrategy strategy) {
        super(pos, hp);
        this.strategy = strategy;
    }

    public AttackStrategy getAttackStrategy() {
        return this.strategy;
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.strategy = strategy;
    }

    public void setAttackDirection(AttackDirection direction) {
        this.strategy.setDirection(direction);
    }

    public AttackDirection getAttackDirection() {
        return this.strategy.getDirection();
    }

    /*
        Determines if the enemy can move to the desired position.
     */
    protected boolean canMove(GameModel grid, Position pos) {
        return ((pos != null) && ((!grid.tileOccupied(pos)) || (pos.same(super.getPosition()))) && (!grid.tileIntransitable(pos)));
    }

    /*
        Prepare the attack for the next turn.
        It will move and plan an attack on cities and/or heros.
     */
    public abstract void moveAndPlanAttack(GameModel grid);

    /*
        Obtain the matrix of attack for the next attack.
     */
    public DamageMatrix previewAttack() {
        return this.strategy.previewAttack(super.getPosition());
    }

    /*
        Attack with the chosen strategy.
     */
    public void attack(GameModel grid) {
        this.strategy.attack(grid, super.getPosition());
    }
}
