package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.max;
import static out_of_the_breach.model.AttackDirection.*;

/*
    This enemy will look to damage the most heroes or cities.
    Prioritizes cities.
    Avoids hitting enemies.
 */

public class Dragon extends Enemy {
    static final private double cityPoints = 1.1;
    static final private double heroPoints = 1.0;
    static final private double enemyPenalty = 2.0;
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

    /*
        The dragon movement depends actively on its attack strategy to deal the most possible damage.
     */
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
        Position initialPos = super.getPosition(); // Dragon Position will be temporarily changed to allow us to calculate the efficiency (pontuation) of the attack
        Position attackPosition = null;
        Position targetPosition;
        Position p;
        double mostEnemiesHit = 0;
        double enemiesHit;
        AttackDirection direction = NONE;

        List<AttackDirection> directions = new ArrayList<>();
        List<AttackDirection> opposites = new ArrayList<>();
        directions.add(NORTH); directions.add(SOUTH); directions.add(EAST); directions.add(WEST);
        opposites.add (SOUTH); opposites.add (NORTH); opposites.add (WEST); opposites.add (EAST);

        int watcher;
        // Check what is the position that will cause more damages to entities
        for (int index = 0; (index < targets.size()) && (mostEnemiesHit != this.range * max(cityPoints, heroPoints)); index ++) {
            targetPosition = targets.get(index).getPosition();

            for (int directionIndex = 0; directionIndex < directions.size(); directionIndex ++) {
                p = targetPosition.adjacentPos(directions.get(directionIndex));
                watcher = -1;
                if (p != null) {
                    watcher = p.getLinearMatrixPosition();
                }

                if (canMove(grid, p)) {
                    // Dragon Temporary Position and Attack Direction
                    super.setPosition(p);
                    super.setAttackDirection(opposites.get(directionIndex));

                    // Calculate Pontuation
                    enemiesHit = this.calculatePontuation(grid);

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
            this.setAttackDirection(direction);
        }
        else {
            this.setAttackDirection(NONE);
        }
    }

    private float calculatePontuation(GameModel grid) {
        float pontuation = 0;
        DamageMatrix damage = this.previewAttack();
        Position p;
        Entity entity;
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                try {
                    p = new Position(x, y);
                }
                catch (OutsideOfTheGrid o) {
                    // Impossible to get here
                    o.printStackTrace();
                    continue;
                }
                if (damage.getDamage(p) != 0) {
                    if (grid.tileOccupied(p)) {
                        entity = grid.getEntityAt(p);
                        if (entity instanceof City) {
                            pontuation += cityPoints;
                        }
                        else if (entity instanceof Hero) {
                            pontuation += heroPoints;
                        }
                        else {
                            // It's hitting another enemy
                            pontuation -= enemyPenalty * this.range;
                        }
                    }
                }
            }
        }
        return pontuation;
    }
}
