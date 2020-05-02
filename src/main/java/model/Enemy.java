package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Entity {
    private int damage;
    private AttackStrategy currentStrategy;
    private List<AttackStrategy> strategies;

    public Enemy(Position pos, int hp, int damage, List<AttackStrategy> strategies) {
        super(pos, hp);
        this.damage = damage;
        this.currentStrategy = null;
        this.strategies = strategies;
    }

    public AttackStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentStrategy(AttackStrategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    public abstract void moveAndPlanAttack(Model grid);

    public DamageMatrix previewAttack() {
        return this.currentStrategy.previewAttack(super.getPosition());
    }

    public void attack(Model grid) {
        this.currentStrategy.attack(grid, super.getPosition());
    }
}
