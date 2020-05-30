package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

/*
    This hero represents a lowly mobile hero.
    It will use the LineAttack and can attack in 4 different directions, affecting 1 tile at a time.
    It can move to any tile in a range.
    However, it can't move if the tile is already occupied by another entity or there is a mountain on that tile.
 */

public class Tank extends Hero {
    public Tank(Position pos, int hp, int damage, int movementRange) {
        super(pos, hp, movementRange);
        List<AttackStrategy> strategies = new ArrayList<>();
        strategies.add(new LineAttack(damage, 1, AttackDirection.NORTH));
        strategies.add(new LineAttack(damage, 1, AttackDirection.SOUTH));
        strategies.add(new LineAttack(damage, 1, AttackDirection.EAST ));
        strategies.add(new LineAttack(damage, 1, AttackDirection.WEST ));
        super.setStrategies(strategies);
    }

    @Override
    public String getName() {
        return "Tank";
    }

    @Override
    public String getInitials() {
        return "TNK";
    }

    @Override
    public boolean withinRange(Position pos) {
        int range = super.getMovementRange();
        int distance = pos.distanceTo(super.getPosition());
        return distance <= range;
    }
}
