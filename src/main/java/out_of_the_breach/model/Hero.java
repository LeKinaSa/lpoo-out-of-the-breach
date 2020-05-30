package out_of_the_breach.model;

import java.util.List;

/*
    The player can move and attack with the hero.
 */

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

    public List<AttackStrategy> getStrategies() {
        return this.strategies;
    }

    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public int getMovementRange() {
        return this.movementRange;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public boolean getHasEndedTurn() {
        return this.hasEndedTurn;
    }

    /*
        Starts a new turn for the hero.
     */
    public void reset() {
        hasMoved = false;
        hasEndedTurn = false;
    }

    /*
        Determines if the position is within the range of the hero.
     */
    public abstract boolean withinRange(Position pos);

    /*
        Obtain the matrix of possible movements for the hero.
     */
    public MovementMatrix displayMove(GameModel grid) {
        MovementMatrix moveMatrix = new MovementMatrix();
        Position p;
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                try {
                    p = new Position(x, y);
                } catch (OutsideOfTheGrid o) {
                    // Impossible to get here
                    o.printStackTrace();
                    continue;
                }
                boolean canMove = !grid.tileOccupied(p) && !grid.tileIntransitable(p) && this.withinRange(p);

                moveMatrix.setMove(p, canMove);
            }
        }
        return moveMatrix;
    }

    /*
        Moves the hero to the desired position, if possible.
        Returns whether or not was possible to move the hero.
     */
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

    /*
        Obtain the matrix of attack for a certain hero's strategy.
     */
    public DamageMatrix previewAttack(AttackStrategy strategy) {
        return strategy.previewAttack(super.getPosition());
    }

    /*
        Attack with the chosen hero's strategy.
     */
    public void attack(GameModel grid, AttackStrategy strategy) {
        if (!hasEndedTurn) {
            strategy.attack(grid, super.getPosition());
            hasEndedTurn = true;
        }
    }

    /*
        Attack using the strategy on the chosen index.
     */
    public void attack(GameModel grid, int strategyIndex) {
        if ((strategyIndex >= 0) && (strategyIndex < this.strategies.size())) {
            attack(grid, this.strategies.get(strategyIndex));
        }
    }
}
