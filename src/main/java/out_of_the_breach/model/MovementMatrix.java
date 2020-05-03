package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovementMatrix {
    List<Boolean> possibleMoves;

    public MovementMatrix() {
        this.possibleMoves = new ArrayList<Boolean>(Collections.nCopies(64, false));
    }

    public void setMove(Position pos, boolean canMove) {
        this.possibleMoves.set(pos.getLinearMatrixPosition(), canMove);
    }

    public boolean getMove(Position pos) {
        return this.possibleMoves.get(pos.getLinearMatrixPosition());
    }
}
