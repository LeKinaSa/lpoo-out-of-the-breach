package model;

public abstract class Enemy extends Entity {
    private int damage;
    private AttackStrategy strategy;

    public Enemy(Position pos, int hp, int damage) {
        super(pos, hp);
        this.damage = damage;
        this.strategy = new NoAttack();
    }

    @Override
    public void draw() {}

    public DamageMatrix planAttack(Grid grid) {
        this.strategy = new AttackNorth(this.damage);
        return this.strategy.planAttack(grid, super.getPosition());
    }

    public void attack(Grid grid) {
        this.strategy.attack(grid, super.getPosition());
    }
}
