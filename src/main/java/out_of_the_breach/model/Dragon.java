package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    This enemy will look to damage the most heros or cities.
    Prioritizes cities.
    Avoids hitting enemies.
 */

public class Dragon extends Enemy {
    private int range;

    public Dragon(Position pos, int hp, int damage) {
        super(pos, hp, new LineAttack(damage, 3));
        this.range = 3;
    }

    @Override
    public String getName() {
        return "Dragon";
    }

    @Override
    public String getInitials() {
        return "DRG";
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

        // Find the Position where we can Attack and the Direction of the Attack
        Position initialPos = super.getPosition(); // Dragon Position will be temporarily null so it doesn't attack itself
        Position attackPosition = null;
        Position targetPosition;
        Position p;
        double mostEnemiesHit = 0;
        double enemiesHit;
        AttackDirection direction = NONE;
        Entity entity;
        Position auxPos;

        // Check what is the position that will cause more damages to entities
        for (int index = 0; (index < targets.size()) && (mostEnemiesHit != this.range * 1.1); index ++) {
            targetPosition = targets.get(index).getPosition();

            List<AttackDirection> directions = new ArrayList<>();
            List<AttackDirection> opposites = new ArrayList<>();
            directions.add(NORTH); opposites.add(SOUTH);
            directions.add(SOUTH); opposites.add(NORTH);
            directions.add( EAST); opposites.add( WEST);
            directions.add( WEST); opposites.add( EAST);

            for (int directionIndex = 0; directionIndex < directions.size(); directionIndex ++) {
                p = targetPosition.adjacentPos(directions.get(directionIndex));

                if (canMove(grid, p)) {
                    // Dragon Temporary Position is null
                    super.setPosition(null);

                    auxPos = p;
                    enemiesHit = 0;
                    for (int i = 0; i < this.range; i ++) {
                        auxPos = auxPos.adjacentPos(opposites.get(directionIndex)); // Check the positions in line of attack
                        if (auxPos == null) {
                            break;
                        }
                        else if (grid.tileOccupied(auxPos)) {
                            entity = grid.getEntityAt(auxPos);
                            if (entity instanceof City) {
                                enemiesHit += 1.1;
                            }
                            else if (entity instanceof Hero) {
                                enemiesHit += 1;
                            }
                            else {
                                // It's hitting another enemy
                                enemiesHit -= 2 * this.range;
                            }
                        }
                    }

                    if (enemiesHit > mostEnemiesHit) {
                        attackPosition = p;
                        mostEnemiesHit = enemiesHit;
                        // The direction of attack is the opposite to the direction in which the dragon had to move to prepare the attack
                        direction = opposites.get(directionIndex);
                    }

                    // Reset Dragon Position
                    super.setPosition(initialPos);
                }
            }
        }

        if (attackPosition != null) {
            this.setPosition(attackPosition);
            this.getAttackStrategy().setDirection(direction);
        }
        else {
            this.getAttackStrategy().setDirection(NONE);
        }
    }
}
