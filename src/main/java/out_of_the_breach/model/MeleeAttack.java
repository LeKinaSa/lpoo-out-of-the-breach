package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

import static out_of_the_breach.model.AttackDirection.*;

/*
    This class represents a melee attack.
    This attack will affect 1 square in the chosen direction.
    The possible directions are NONE, NORTH, SOUTH, EAST and WEST.
 */

public class MeleeAttack extends AttackStrategy {
    private int damage;
    private List<AttackDirection> possibleDirections;

    public MeleeAttack(int damage) {
        super(NONE);
        this.damage = damage;
        this.possibleDirections = new ArrayList<>();
        this.possibleDirections.add( NONE);
        this.possibleDirections.add(NORTH);
        this.possibleDirections.add(SOUTH);
        this.possibleDirections.add( EAST);
        this.possibleDirections.add( WEST);
    }

    public MeleeAttack(int damage, AttackDirection direction) {
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
    public DamageMatrix previewAttack(Position pos) {
        DamageMatrix damageMatrix = new DamageMatrix();
        Position damagedPosition = pos.adjacentPos(super.getDirection());
        if (damagedPosition == null) {
            return damageMatrix;
        }
        damageMatrix.setDamage(damagedPosition, this.damage);
        return damageMatrix;
    }
}

