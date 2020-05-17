package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

/*
    This enemy will look to damage the most possible heros or cities.
    Prioritizes cities.
 */

public class Lizard extends Enemy {
    public Lizard(Position pos, int hp, int damage) {
        super(pos, hp, new AOEAttack(damage));
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

        // Find the Position where we can Attack
        Position pos = super.getPosition();
        Position closerPosition = null;
        Position targetPosition;
        double smallerDistance = 64;
        double distance;

        // Check what's the closest position to the current position of the lizard
        for (int index = 0; (index < targets.size()) && (smallerDistance != 0); index ++) {
            targetPosition = targets.get(index).getPosition();

            Position north = targetPosition.north();
            Position south = targetPosition.south();
            Position east  = targetPosition.east ();
            Position west  = targetPosition.west ();

            if ((north != null) && (!grid.tileOccupied(north))) {
                distance = pos.distanceTo(north);
                if (distance < smallerDistance) {
                    closerPosition = north;
                    smallerDistance = distance;
                }
            }
            if ((south != null) && (!grid.tileOccupied(south))) {
                distance = pos.distanceTo(south);
                if (distance < smallerDistance) {
                    closerPosition = south;
                    smallerDistance = distance;
                }
            }
            if ((east != null) && (!grid.tileOccupied(east))) {
                distance = pos.distanceTo(east);
                if (distance < smallerDistance) {
                    closerPosition = east;
                    smallerDistance = distance;
                }
            }
            if ((west != null) && (!grid.tileOccupied(west))) {
                distance = pos.distanceTo(west);
                if (distance < smallerDistance) {
                    closerPosition = west;
                    smallerDistance = distance;
                }
            }
        }

        if (closerPosition != null) {
            this.setPosition(closerPosition);
        }
    }
}
