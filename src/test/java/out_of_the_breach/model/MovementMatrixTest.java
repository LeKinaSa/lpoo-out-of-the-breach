package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class MovementMatrixTest {
    @Test
    public void movementTest() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Mockito.when(p1.getLinearMatrixPosition()).thenReturn(23);
        Mockito.when(p2.getLinearMatrixPosition()).thenReturn(0);
        Mockito.when(p3.getLinearMatrixPosition()).thenReturn(15);

        MovementMatrix movMatrix = new MovementMatrix();
        for (int index = 0; index <= 63; index ++) {
            assertEquals(false, movMatrix.possibleMoves.get(index));
        }

        movMatrix.setMove(p1, true);
        movMatrix.setMove(p2, true);
        movMatrix.setMove(p3, false);

        assertEquals(true, movMatrix.getMove(p1));
        assertEquals(true, movMatrix.getMove(p2));
        assertEquals(false, movMatrix.getMove(p3));

        for (int index = 0; index <= 63; index ++) {
            if (index == 23) {
                assertEquals( true, movMatrix.possibleMoves.get(index)); // p1
            }
            else if (index == 0) {
                assertEquals( true, movMatrix.possibleMoves.get(index)); // p2
            }
            else {
                assertEquals(false, movMatrix.possibleMoves.get(index)); // p3 + others
            }
        }
    }
}
