package model;

import java.util.ArrayList;
import java.util.List;

public class MovementMatrix {
    List<Boolean> possibleMoves;

    public MovementMatrix() {
        possibleMoves = new ArrayList<>(64);
    }

    public void setMove(int x, int y, boolean canMove) {
        possibleMoves.set(x + y * 8, canMove);
    }

    public boolean getmove(int x, int y) {
        return possibleMoves.get(x + y * 8);
    }
}
