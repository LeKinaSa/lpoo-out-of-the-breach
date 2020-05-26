package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    It will target the closest units.
    If the target is inaccessible, it will try the next nearest entity.
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
        Position attackPosition = null;
        Position targetPosition;
        double mostEnemiesHit = 0;
        double enemiesHit;
        AttackDirection direction = NONE;
        Entity entity;
        Position auxPos;

        // Check what is the position that will cause more damages to entities
        for (int index = 0; (index < targets.size()) && (mostEnemiesHit != this.range * 1.1); index ++) {
            targetPosition = targets.get(index).getPosition();

            Position north = targetPosition.adjacentPos(NORTH);
            Position south = targetPosition.adjacentPos(SOUTH);
            Position east  = targetPosition.adjacentPos( EAST);
            Position west  = targetPosition.adjacentPos( WEST);

            if ((north != null) && ((!grid.tileOccupied(north)) || (north.same(super.getPosition())))) {
                auxPos = north;
                enemiesHit = 0;
                for (int i = 0; i < this.range; i ++) {
                    auxPos = auxPos.adjacentPos(SOUTH); // Check the positions in line of attack
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
                    attackPosition = north;
                    mostEnemiesHit = enemiesHit;
                    direction = SOUTH;
                }
            }
            if ((south != null) && ((!grid.tileOccupied(south)) || (south.same(super.getPosition())))) {
                auxPos = south;
                enemiesHit = 0;
                for (int i = 0; i < this.range; i ++) {
                    auxPos = auxPos.adjacentPos(NORTH); // Check the positions in line of attack
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
                    attackPosition = south;
                    mostEnemiesHit = enemiesHit;
                    direction = NORTH;
                }
            }
            if ((east != null) && ((!grid.tileOccupied(east)) || (east.same(super.getPosition())))) {
                auxPos = east;
                enemiesHit = 0;
                for (int i = 0; i < this.range; i ++) {
                    auxPos = auxPos.adjacentPos(WEST); // Check the positions in line of attack
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
                    attackPosition = east;
                    mostEnemiesHit = enemiesHit;
                    direction = WEST;
                }
            }
            if ((west != null) && ((!grid.tileOccupied(west)) || (west.same(super.getPosition())))) {
                auxPos = west;
                enemiesHit = 0;
                for (int i = 0; i < this.range; i ++) {
                    auxPos = auxPos.adjacentPos(EAST); // Check the positions in line of attack
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
                    attackPosition = west;
                    mostEnemiesHit = enemiesHit;
                    direction = EAST;
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
