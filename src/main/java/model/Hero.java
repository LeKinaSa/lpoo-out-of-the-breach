package model;

import com.googlecode.lanterna.TextColor;

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

    public abstract boolean withinRange(Position pos);

    public MovementMatrix displayMove() {
        MovementMatrix canMove = new MovementMatrix();
        Position p;
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                try {
                    p = new Position(x, y);
                }
                catch (OutsideOfTheGrid o) {
                    continue;
                }
                canMove.setMove(p, this.withinRange(p));
            }
        }
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
        hasEndedTurn = true;
    }

    public void attack(Model grid, int strategyIndex) {
        attack(grid, strategies.get(strategyIndex));
    }

    public int getMovementRange() {
        return movementRange;
    }
  
    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public boolean getHasEndedTurn() {
        return hasEndedTurn;
    }
}
