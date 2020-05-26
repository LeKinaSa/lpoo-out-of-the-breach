package out_of_the_breach.model;

import static out_of_the_breach.model.AttackDirection.*;

/*
    Very Stupid Enemy
    It only targets the weakest hero or city. (The one with less hp)
    If that targeted unit is inaccessible, it won't move.
    Prioritizes heros.
 */

public class Bug extends Enemy {

    public Bug(Position pos, int hp, int damage) {
        super(pos, hp, new LineAttack(damage, 1));
    }

    @Override
    public void moveAndPlanAttack(GameModel grid) {
        this.getAttackStrategy().setDirection(NONE);
        Position targetedPosition = null;
        int lowerHp = -1;
        int hp;

        // Find the Weakest Target -> (Prioritize the attacks on heros)
        for (Hero ally : grid.getAllies()) {
            hp = ally.getHp();
            if (hp < lowerHp) {
                lowerHp = hp;
                targetedPosition = ally.getPosition();
            }
            else if (lowerHp == -1) {
                lowerHp = hp;
                targetedPosition = ally.getPosition();
            }
        }

        for (City city : grid.getCities()) {
            hp = city.getHp();
            if (hp < lowerHp) {
                lowerHp = hp;
                targetedPosition = city.getPosition();
            }
            else if (lowerHp == -1) {
                lowerHp = hp;
                targetedPosition = city.getPosition();
            }
        }

        if (targetedPosition == null) {
            return;
        }

        // Find the Position where we can Attack and the Direction of the Attack
        Position north = targetedPosition.adjacentPos(NORTH);
        Position south = targetedPosition.adjacentPos(SOUTH);
        Position east  = targetedPosition.adjacentPos( EAST);
        Position west  = targetedPosition.adjacentPos( WEST);
        AttackDirection direction = NONE;

        targetedPosition = null; // To make sure the enemy doesn't go to the tile of its target
        Position pos = super.getPosition();
        int smallerDistance = 64;
        int distance;

        if (canMove(grid, north)) {
            distance = pos.distanceTo(north);
            targetedPosition = north;
            smallerDistance = distance;
            direction = SOUTH;
        }
        if (canMove(grid, south)) {
            distance = pos.distanceTo(south);
            if (distance < smallerDistance) {
                targetedPosition = south;
                smallerDistance = distance;
                direction = NORTH;
            }
        }
        if (canMove(grid, east)) {
            distance = pos.distanceTo(east);
            if (distance < smallerDistance) {
                targetedPosition = east;
                smallerDistance = distance;
                direction = WEST;
            }
        }
        if (canMove(grid, west)) {
            distance = pos.distanceTo(west);
            if (distance < smallerDistance) {
                targetedPosition = west;
                direction = EAST;
            }
        }

        if (targetedPosition != null) {
            this.setPosition(targetedPosition);
            this.getAttackStrategy().setDirection(direction);
        }
    }
}
