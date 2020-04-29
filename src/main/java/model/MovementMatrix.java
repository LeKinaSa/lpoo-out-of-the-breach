package model;

import java.util.ArrayList;
import java.util.List;

public class MovementMatrix {
    List<Boolean> possibleMoves;

    public MovementMatrix() {
        this.possibleMoves = new ArrayList<>(64);
    }

    public void setMove(Position pos, boolean canMove) {
        this.possibleMoves.set(pos.getLinearMatrixPosition(), canMove);
    }

    public boolean getMove(Position pos) {
        return this.possibleMoves.get(pos.getLinearMatrixPosition());
    }
}
