package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

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
    public String getName() {
        return "Bug";
    }

    @Override
    public String getInitials() {
        return "BUG";
    }

    @Override
    public void moveAndPlanAttack(GameModel grid) {
        this.setAttackDirection(NONE);
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
        AttackDirection direction = NONE;
        Position pos = super.getPosition();
        Position attackPosition = null;
        Position p;
        int smallerDistance = 64;
        int distance;

        List<AttackDirection> directions = new ArrayList<>();
        List<AttackDirection> opposites = new ArrayList<>();
        directions.add(NORTH); directions.add(SOUTH); directions.add(EAST); directions.add(WEST);
        opposites.add (SOUTH); opposites.add (NORTH); opposites.add (WEST); opposites.add (EAST);

        for (int directionIndex = 0; directionIndex < directions.size(); directionIndex ++) {
            p = targetedPosition.adjacentPos(directions.get(directionIndex));
            if (canMove(grid, p)) {
                distance = pos.distanceTo(p);
                if (distance < smallerDistance) {
                    attackPosition = p;
                    smallerDistance = distance;
                    direction = opposites.get(directionIndex);
                }
            }
        }

        if (attackPosition != null) {
            this.setPosition(attackPosition);
            this.setAttackDirection(direction);
        }
    }
}
