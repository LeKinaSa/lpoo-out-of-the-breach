package out_of_the_breach.model;

import static out_of_the_breach.model.AttackDirection.*;

public class Bug extends Enemy {

    public Bug(Position pos, int hp, int damage) {
        super(pos, hp, new MeleeAttack(damage));
    }

    @Override
    public void moveAndPlanAttack(Model grid) {
        Position closerPosition = super.getPosition();
        int smallerDistance = 64;

        Position pos = super.getPosition();
        int distance;

        // Find the Nearest Target (Priority : City then Hero)
        for (City city : grid.getCities()) {
            distance = pos.distanceTo(city.getPosition());
            if (distance < smallerDistance) {
                closerPosition = city.getPosition();
                smallerDistance = distance;
            }
        }

        for (Hero ally : grid.getAllies()) {
            distance = pos.distanceTo(ally.getPosition());
            if (distance < smallerDistance) {
                closerPosition = ally.getPosition();
                smallerDistance = distance;
            }
        }

        // Find the Position where we can Attack and the Direction of the Attack
        Position north = closerPosition.north();
        Position south = closerPosition.south();
        Position east  = closerPosition.east ();
        Position west  = closerPosition.west ();
        AttackDirection direction = NONE;

        closerPosition = null; // To make sure the enemy doesn't go to the tile of its target
        smallerDistance = 64;
        if ((north != null) && (!grid.tileOccupied(north))) {
            closerPosition = north;
            smallerDistance = pos.distanceTo(north);
            direction = NORTH;
        }
        if ((south != null) && (!grid.tileOccupied(south))) {
            distance = pos.distanceTo(south);
            if (distance < smallerDistance) {
                closerPosition = south;
                smallerDistance = distance;
                direction = SOUTH;
            }
        }
        if ((east != null) && (!grid.tileOccupied(east))) {
            distance = pos.distanceTo(east);
            if (distance < smallerDistance) {
                closerPosition = east;
                smallerDistance = distance;
                direction = EAST;
            }
        }
        if ((west != null) && (!grid.tileOccupied(west))) {
            distance = pos.distanceTo(west);
            if (distance < smallerDistance) {
                closerPosition = west;
                smallerDistance = distance;
                direction = WEST;
            }
        }

        if (closerPosition != null) {
            this.setPosition(closerPosition);
            this.getAttackStrategy().setDirection(direction);
        }
    }
}
