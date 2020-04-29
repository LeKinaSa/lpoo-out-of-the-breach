package model;

import java.util.ArrayList;
import java.util.List;

public class DamageMatrix {
    List<Integer> incomingDamage;

    public DamageMatrix() {
        incomingDamage = new ArrayList<>(64);
    }

    public void setDamage(int x, int y, int damage) {
        incomingDamage.set(x + y * 8, damage);
    }

    public int getDamage(int x, int y) {
        return incomingDamage.get(x + y * 8);
    }
}
