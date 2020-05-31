package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    This class represents a line attack.
    This attack will affect 4-5 squares in the chosen direction.
    The possible directions are NONE, NORTH, SOUTH, EAST and WEST.
    If the direction is NONE, it is a melee aoe attack and it doesn't affect the target position (affecting 4 squares).
    If the direction isn't NONE, it is a ranged attack and it will affect the target position (affecting 5 squares).
 */

public class AOEAttack extends AttackStrategy {
    private int damage;

    public AOEAttack(int damage) {
        super(NONE);
        this.damage = damage;
    }

    public AOEAttack(int damage, AttackDirection direction) {
        super(direction);
        this.damage = damage;
    }

    @Override
    public void setDirection(AttackDirection direction) {
        return;
    }

    @Override
    public DamageMatrix previewAttack(Position pos) {
        DamageMatrix damageMatrix = new DamageMatrix();

        if (super.getDirection() != NONE) {
            pos = pos.adjacentPos(super.getDirection());
            if (pos == null) {
                return damageMatrix;
            }
            damageMatrix.setDamage(pos, this.damage);
        }

        List<Position> positions = new ArrayList<>();
        positions.add(pos.adjacentPos(NORTH));
        positions.add(pos.adjacentPos(SOUTH));
        positions.add(pos.adjacentPos( EAST));
        positions.add(pos.adjacentPos( WEST));

        for (Position damagedPosition : positions) {
            if (damagedPosition != null) {
                damageMatrix.setDamage(damagedPosition, this.damage);
            }
        }

        return damageMatrix;
    }
}