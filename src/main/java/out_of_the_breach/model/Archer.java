package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

public class Archer extends Hero {
    public Archer(Position pos, int hp, int damage) {
        super(pos, hp, 0);
        List<AttackStrategy> strategies = new ArrayList<>();
        strategies.add(new AOEAttack(damage, AttackDirection.L_NORTHEAST));
        strategies.add(new AOEAttack(damage, AttackDirection.L_NORTHWEST));
        strategies.add(new AOEAttack(damage, AttackDirection.L_SOUTHEAST));
        strategies.add(new AOEAttack(damage, AttackDirection.L_SOUTHWEST));
        strategies.add(new AOEAttack(damage, AttackDirection.L_EASTNORTH));
        strategies.add(new AOEAttack(damage, AttackDirection.L_EASTSOUTH));
        strategies.add(new AOEAttack(damage, AttackDirection.L_WESTNORTH));
        strategies.add(new AOEAttack(damage, AttackDirection.L_WESTSOUTH));
        super.setStrategies(strategies);
    }

    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public String getInitials() {
        return "ARC";
    }

    @Override
    public boolean withinRange(Position pos) {
        return true;
    }
}