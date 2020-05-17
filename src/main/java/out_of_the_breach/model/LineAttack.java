package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    This class represents a line attack.
    This attack will affect 3 squares in the chosen direction.
    The possible directions are NONE, NORTH, SOUTH, EAST and WEST.
 */

public class LineAttack extends AttackStrategy {
    private int damage;
    private List<AttackDirection> possibleDirections;

    public LineAttack(int damage) {
        super(NONE);
        this.damage = damage;
        this.possibleDirections = new ArrayList<>();
        this.possibleDirections.add( NONE);
        this.possibleDirections.add(NORTH);
        this.possibleDirections.add(SOUTH);
        this.possibleDirections.add( EAST);
        this.possibleDirections.add( WEST);
    }

    public LineAttack(int damage, AttackDirection direction) {
        super(direction);
        this.damage = damage;
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
    public Position getDamagedPosition(Position pos) {
        switch(super.getDirection()) {
            case NORTH:
                return pos.north();
            case SOUTH:
                return pos.south();
            case EAST:
                return pos.east();
            case WEST:
                return pos.west();
            default:
                return null;
        }
    }

    @Override
    public DamageMatrix previewAttack(Position pos) {
        DamageMatrix damageMatrix = new DamageMatrix();

        Position damagedPosition = this.getDamagedPosition(pos);
        if (damagedPosition == null) {
            return damageMatrix;
        }
        damageMatrix.setDamage(damagedPosition, this.damage);

        damagedPosition = this.getDamagedPosition(damagedPosition);
        if (damagedPosition == null) {
            return damageMatrix;
        }
        damageMatrix.setDamage(damagedPosition, this.damage);

        damagedPosition = this.getDamagedPosition(damagedPosition);
        if (damagedPosition == null) {
            return damageMatrix;
        }
        damageMatrix.setDamage(damagedPosition, this.damage);

        return damageMatrix;
    }
}
