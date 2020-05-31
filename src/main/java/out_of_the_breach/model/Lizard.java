package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    It will target the closest units.
    If the target is inaccessible, it will try the next nearest entity.
    Prioritizes cities.
 */

public class Lizard extends Enemy {
    public Lizard(Position pos, int hp, int damage) {
        super(pos, hp, new AOEAttack(damage));
    }

    @Override
    public String getName() {
        return "Lizard";
    }

    @Override
    public String getInitials() {
        return "LIZ";
    }

    @Override
    public void moveAndPlanAttack(GameModel grid) {
        // Find the Targets -> (Prioritize the attacks on cities)
        List<Entity> targets = new ArrayList<>();
        for (City city : grid.getCities()) {
            targets.add(city);
        }
        for (Hero ally : grid.getAllies()) {
            targets.add(ally);
        }

        // Find the Position where we can Attack
        Position pos = super.getPosition();
        Position closerPosition = null;
        Position targetPosition;
        Position p;
        double smallerDistance = 64;
        double distance;
        List<AttackDirection> directions = new ArrayList<>();
        directions.add(NORTH); directions.add(SOUTH); directions.add( EAST); directions.add( WEST);

        // Check what's the closest position to the current position of the lizard
        for (int index = 0; (index < targets.size()) && (smallerDistance != 0); index ++) {
            targetPosition = targets.get(index).getPosition();

            for (AttackDirection direction : directions) {
                p = targetPosition.adjacentPos(direction);
                if (canMove(grid, p)) {
                    distance = pos.distanceTo(p);
                    if (distance < smallerDistance) {
                        closerPosition = p;
                        smallerDistance = distance;
                    }
                }
            }
        }

        if (closerPosition != null) {
            this.setPosition(closerPosition);
        }
    }
}
