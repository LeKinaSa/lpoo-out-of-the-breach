package out_of_the_breach.model;

import java.util.List;

public abstract class Hero extends Entity {
    private int movementRange;
    private boolean hasMoved;
    private boolean hasEndedTurn;
    private List<AttackStrategy> strategies;

    public Hero(Position pos, int hp, int movementRange) {
        super(pos, hp);
        this.movementRange = movementRange;
        this.hasMoved     = false;
        this.hasEndedTurn = false;
    }

    protected void setStrategies(List<AttackStrategy> strategies) {
        this.strategies = strategies;
    }

    public abstract boolean withinRange(Position pos);

    public MovementMatrix displayMove(GameModel grid) {
        MovementMatrix moveMatrix = new MovementMatrix();
        Position p;
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                try {
                    p = new Position(x, y);
                }
                catch (OutsideOfTheGrid o) {
                    continue;
                }
                boolean canMove = !grid.tileOccupied(p) && !grid.tileIntransitable(p) && this.withinRange(p);

                moveMatrix.setMove(p, canMove);
            }
        }
        return moveMatrix;
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

    public void attack(GameModel grid, AttackStrategy strategy) {
        if (!hasEndedTurn) {
            strategy.attack(grid, super.getPosition());
            hasEndedTurn = true;
        }
    }

    public void attack(GameModel grid, int strategyIndex) {
        if ((strategyIndex >= 0) && (strategyIndex < this.strategies.size())) {
            attack(grid, this.strategies.get(strategyIndex));
        }
    }

    public int getMovementRange() {
        return this.movementRange;
    }
  
    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public boolean getHasEndedTurn() {
        return this.hasEndedTurn;
    }

    public void reset() {
        hasMoved = false;
        hasEndedTurn = false;
    }
}
