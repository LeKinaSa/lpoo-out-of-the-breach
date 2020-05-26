package out_of_the_breach.model;

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

    public AttackDirection getAttackDirection() {
        return this.strategy.getDirection();
    }

    public void setAttackDirection(AttackDirection direction) {
        this.strategy.setDirection(direction);
    }

    public abstract void moveAndPlanAttack(GameModel grid);

    public DamageMatrix previewAttack() {
        return this.strategy.previewAttack(super.getPosition());
    }

    public void attack(GameModel grid) {
        this.strategy.attack(grid, super.getPosition());
    }
}
