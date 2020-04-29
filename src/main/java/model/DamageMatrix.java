package model;

import java.util.ArrayList;
import java.util.List;

public class DamageMatrix {
    List<Integer> incomingDamage;

    public DamageMatrix() {
        this.incomingDamage = new ArrayList<>(64);
    }

    public void setDamage(Position pos, int damage) {
        this.incomingDamage.set(pos.getLinearMatrixPosition(), damage);
    }

    public int getDamage(Position pos) {
        return this.incomingDamage.get(pos.getLinearMatrixPosition());
    }
}
