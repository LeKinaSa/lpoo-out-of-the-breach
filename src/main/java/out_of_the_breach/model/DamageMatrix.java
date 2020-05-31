package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Stores a matrix that determines what tiles will be taking damage and how much damage they will take.
 */

public class DamageMatrix {
    List<Integer> incomingDamage;

    public DamageMatrix() {
        this.incomingDamage = new ArrayList<Integer>(Collections.nCopies(64, 0));
    }

    public void setDamage(Position pos, int damage) {
        if (damage >= 0) {
            this.incomingDamage.set(pos.getLinearMatrixPosition(), damage);
        }
    }

    public int getDamage(Position pos) {
        return this.incomingDamage.get(pos.getLinearMatrixPosition());
    }
}
