package model;

import java.util.List;

public abstract class Hero extends Entity {
    private int movementRange;
    private boolean hasMoved;
    private boolean hasEndedTurn;
    private List<AttackStrategy> strategies;

    public Hero(Position pos, int hp, int movementRange, List<AttackStrategy> strategies) {
        super(pos, hp);
        this.movementRange = movementRange;
        this.hasMoved     = false;
        this.hasEndedTurn = false;
        this.strategies = strategies;
    }

    protected abstract boolean withinRange(Position pos);

    public MovementMatrix displayMove() {
        MovementMatrix canMove = new MovementMatrix();
        //TODO: for loop, use withinRange
        return canMove;
    }

    public boolean moveTo(Position pos) {
        if (this.hasMoved || this.hasEndedTurn || (!this.withinRange(pos))) {
            return false;
        }
        else {
            super.setPosition(pos);
            this.hasMoved = true;
            return true;
        }
    }

    public List<AttackStrategy> getStrategies() {
        return this.strategies;
    }

    public DamageMatrix previewAttack(AttackStrategy strategy) {
        return strategy.previewAttack(super.getPosition());
    }

    public void attack(Model grid, AttackStrategy strategy) {
        strategy.attack(grid, super.getPosition());
    }

}
