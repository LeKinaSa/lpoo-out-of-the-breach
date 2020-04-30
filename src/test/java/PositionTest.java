import model.OutsideOfTheGrid;
import model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    @Test (expected = OutsideOfTheGrid.class)
    public void lowX() throws OutsideOfTheGrid {
        Position pos = new Position (-1, 3);
    }

    @Test (expected = OutsideOfTheGrid.class)
    public void highX() throws OutsideOfTheGrid {
        Position pos = new Position (8, 3);
    }

    @Test (expected = OutsideOfTheGrid.class)
    public void lowY() throws OutsideOfTheGrid {
        Position pos = new Position (3, -1);
    }

    @Test (expected = OutsideOfTheGrid.class)
    public void highY() throws OutsideOfTheGrid {
        Position pos = new Position (3, 8);
    }

    @Test
    public void test1() {
        Position pos = null;
        try {
            pos = new Position(3, 4);
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            assertEquals(false, true); // TODO
        }
        assertEquals(3, pos.getX());
        assertEquals(4, pos.getY());
        assertEquals(35, pos.getLinearMatrixPosition());
    }

    @Test
    public void test2() {
        Position pos = null;
        try {
            pos = new Position(5, 2);
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            assertEquals(false, true); // TODO
        }
        assertEquals(5, pos.getX());
        assertEquals(2, pos.getY());
        assertEquals(21, pos.getLinearMatrixPosition());
    }

    @Test
    public void test3() {
        Position pos = null;
        try {
            pos = new Position(0, 0);
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            assertEquals(false, true); // TODO
        }
        assertEquals(0, pos.getX());
        assertEquals(0, pos.getY());
        assertEquals(0, pos.getLinearMatrixPosition());
    }

    @Test
    public void test4() {
        Position pos = null;
        try {
            pos = new Position(7, 7);
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            assertEquals(false, true); // TODO
        }
        assertEquals(7, pos.getX());
        assertEquals(7, pos.getY());
        assertEquals(63, pos.getLinearMatrixPosition());
    }

}
