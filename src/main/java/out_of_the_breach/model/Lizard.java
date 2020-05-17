package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

public class Lizard extends Enemy {
    public Lizard(Position pos, int hp, int damage) {
        super(pos, hp, new LineAttack(damage));
    }

    @Override
    public void moveAndPlanAttack(Model grid) {
        // Find the Targets -> (Prioritize the attacks on cities)
        List<Entity> targets = new ArrayList<>();

        for (City city : grid.getCities()) {
            targets.add(city);
        }

        for (Hero ally : grid.getAllies()) {
            targets.add(ally);
        }

        // Find the Position where we can Attack and the Direction of the Attack
        Position pos = super.getPosition();
        Position closerPosition = null;
        Position targetPosition;
        double smallerDistance = 64;
        double distance;
        AttackDirection direction = NONE;

        // Check what's the closest position to the current position of the lizard
        for (int index = 0; index < targets.size(); index ++) {
            targetPosition = targets.get(index).getPosition();

            Position north = targetPosition.north();
            Position south = targetPosition.south();
            Position east  = targetPosition.east ();
            Position west  = targetPosition.west ();

            if ((north != null) && (!grid.tileOccupied(north))) {
                distance = pos.distanceTo(south);
                if (distance < smallerDistance) {
                    closerPosition = north;
                    smallerDistance = distance;
                    direction = SOUTH;
                }
            }
            if ((south != null) && (!grid.tileOccupied(south))) {
                distance = pos.distanceTo(south);
                if (distance < smallerDistance) {
                    closerPosition = south;
                    smallerDistance = distance;
                    direction = NORTH;
                }
            }
            if ((east != null) && (!grid.tileOccupied(east))) {
                distance = pos.distanceTo(east);
                if (distance < smallerDistance) {
                    closerPosition = east;
                    smallerDistance = distance;
                    direction = WEST;
                }
            }
            if ((west != null) && (!grid.tileOccupied(west))) {
                distance = pos.distanceTo(west);
                if (distance < smallerDistance) {
                    closerPosition = west;
                    smallerDistance = distance;
                    direction = EAST;
                }
            }
        }

        if (closerPosition != null) {
            this.setPosition(closerPosition);
            this.getAttackStrategy().setDirection(direction);
        }
        else {
            this.getAttackStrategy().setDirection(NONE);
        }
    }
}
