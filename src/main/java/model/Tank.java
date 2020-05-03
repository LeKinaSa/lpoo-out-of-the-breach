package model;

import java.util.List;

public class Tank extends Hero {
    public Tank(Position pos, int hp, int movementRange, List<AttackStrategy> strategies) {
        super(pos, hp, movementRange, strategies);
    }

    @Override
    protected boolean withinRange(Position pos) {
        int range = super.getMovementRange();
        int distance = super.getPosition().distanceTo(pos);
        return distance <= range;
    }
}
