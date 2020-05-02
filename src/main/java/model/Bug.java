package model;

import java.util.List;

public class Bug extends Enemy {

    public Bug(Position pos, int hp, int damage, List<AttackStrategy> strategies) {
        super(pos, hp, damage, strategies);
    }

    @Override
    public void moveAndPlanAttack(Model grid) {}
}
