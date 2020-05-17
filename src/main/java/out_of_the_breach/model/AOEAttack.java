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
    private List<AttackDirection> possibleDirections;

    public AOEAttack(int damage) {
        super(NONE);
        this.damage = damage;
        this.possibleDirections = new ArrayList<>();
        this.possibleDirections.add( NONE);
    }

    public AOEAttack(int damage, AttackDirection direction) {
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

        if (super.getDirection() != NONE) {
            pos = getDamagedPosition(pos);
            damageMatrix.setDamage(pos, this.damage);
        }

        Position damagedPosition;
        damagedPosition = pos.north();
        if (damagedPosition != null) {
            damageMatrix.setDamage(damagedPosition, this.damage);
        }
        damagedPosition = pos.south();
        if (damagedPosition != null) {
            damageMatrix.setDamage(damagedPosition, this.damage);
        }
        damagedPosition = pos.east();
        if (damagedPosition != null) {
            damageMatrix.setDamage(damagedPosition, this.damage);
        }
        damagedPosition = pos.west();
        if (damagedPosition != null) {
            damageMatrix.setDamage(damagedPosition, this.damage);
        }

        return damageMatrix;
    }
}