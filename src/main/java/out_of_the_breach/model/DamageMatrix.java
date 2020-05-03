package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DamageMatrix {
    List<Integer> incomingDamage;

    public DamageMatrix() {
        this.incomingDamage = new ArrayList<Integer>(Collections.nCopies(64, 0));
    }

    public void setDamage(Position pos, int damage) {
        this.incomingDamage.set(pos.getLinearMatrixPosition(), damage);
    }

    public int getDamage(Position pos) {
        return this.incomingDamage.get(pos.getLinearMatrixPosition());
    }
}
