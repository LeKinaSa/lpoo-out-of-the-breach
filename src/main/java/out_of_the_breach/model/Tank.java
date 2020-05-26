package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

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
    public boolean withinRange(Position pos) {
        int range = super.getMovementRange();
        int distance = super.getPosition().distanceTo(pos);
        return distance <= range;
    }
}
