package model;

import java.util.List;

public class Tank extends Hero {
    public Tank(Position pos, int hp, int movementRange, List<AttackStrategy> strategies) {
        super(pos, hp, movementRange, strategies);
    }

    @Override
    public boolean withinRange(Position pos) {
        return pos.getY() == 7;//TODO
    }
}
