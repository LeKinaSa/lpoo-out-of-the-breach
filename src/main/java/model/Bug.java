package model;

import static model.AttackDirection.*;

public class Bug extends Enemy {

    public Bug(Position pos, int hp, int damage) {
        super(pos, hp, new MeleeAttack(damage));
    }

    @Override
    public void moveAndPlanAttack(Model grid) {}
}
