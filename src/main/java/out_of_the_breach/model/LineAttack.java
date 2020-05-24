package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    This class represents a line attack.
    This attack will affect <range> squares in the chosen direction.
    The possible directions are NONE, NORTH, SOUTH, EAST and WEST.
 */

public class LineAttack extends AttackStrategy {
    private int damage;
    private int range;
    private List<AttackDirection> possibleDirections;

    public LineAttack(int damage, int range) {
        super(NONE);
        this.damage = damage;
        this.range = range;
        this.possibleDirections = new ArrayList<>();
        this.possibleDirections.add( NONE);
        this.possibleDirections.add(NORTH);
        this.possibleDirections.add(SOUTH);
        this.possibleDirections.add( EAST);
        this.possibleDirections.add( WEST);
    }

    public LineAttack(int damage, int range, AttackDirection direction) {
        super(direction);
        this.damage = damage;
        this.range = range;
        this.possibleDirections = new ArrayList<>();
        this.possibleDirections.add(direction);
    }

    @Override
    public void setDirection(AttackDirection direction) {
        if (this.possibleDirections.contains(direction)) {
            super.setDirection(direction);
        }
    }

    @Override
    public DamageMatrix previewAttack(Position pos) {
        DamageMatrix damageMatrix = new DamageMatrix();

        if (super.getDirection() == NONE) {
            return damageMatrix;
        }

        Position damagedPosition = pos;
        for (int i = 0; i < this.range; i ++) {
            damagedPosition = damagedPosition.adjacentPos(super.getDirection());
            if (damagedPosition == null) {
                return damageMatrix;
            }
            damageMatrix.setDamage(damagedPosition, this.damage);
        }

        return damageMatrix;
    }
}
