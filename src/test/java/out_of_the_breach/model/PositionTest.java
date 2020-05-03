package out_of_the_breach.model;

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
            assertEquals(false, true);
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
            assertEquals(false, true);
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
            assertEquals(false, true);
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
            assertEquals(false, true);
        }
        assertEquals(7, pos.getX());
        assertEquals(7, pos.getY());
        assertEquals(63, pos.getLinearMatrixPosition());
    }

    @Test
    public void sameTest() {
        Position pos = null;
        try {
            pos = new Position(2, 3);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }
        assertEquals(2, pos.getX());
        assertEquals(3, pos.getY());
        Position p = null;
        try {
            p = new Position(2, 3);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }
        assertEquals(true, pos.same(p));
        assertEquals(26, pos.getLinearMatrixPosition());
    }

    @Test
    public void distanceToAndSetTest() {
        Position pos = null;
        Position p = null;
        try {
            pos = new Position(1, 2);
            p   = new Position(3, 6);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
        assertEquals(3,   p.getX());
        assertEquals(6,   p.getY());

        assertEquals(6, pos.distanceTo(p));

        pos.setPosition(p);
        assertEquals(3, pos.getX());
        assertEquals(6, pos.getY());
    }

    @Test
    public void directionsTest1() {
        Position pos = null;
        try {
            pos = new Position(0, 0);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }
        Position north = pos.north();
        Position south = pos.south();
        Position  east = pos.east ();
        Position  west = pos.west ();

        assertEquals(null, north);
        assertEquals(null,  west);

        assertEquals(0, south.getX());
        assertEquals(1, south.getY());

        assertEquals(1,  east.getX());
        assertEquals(0,  east.getY());
    }

    @Test
    public void directionsTest2() {
        Position pos = null;
        try {
            pos = new Position(7, 7);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }
        Position north = pos.north();
        Position south = pos.south();
        Position  east = pos.east ();
        Position  west = pos.west ();

        assertEquals(null, south);
        assertEquals(null,  east);

        assertEquals(7, north.getX());
        assertEquals(6, north.getY());

        assertEquals(6,  west.getX());
        assertEquals(7,  west.getY());
    }

    @Test
    public void directionsTest3() {
        Position pos = null;
        try {
            pos = new Position(3, 5);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }
        Position north = pos.north();
        Position south = pos.south();
        Position  east = pos.east ();
        Position  west = pos.west ();

        assertEquals(3, north.getX());
        assertEquals(4, north.getY());

        assertEquals(3, south.getX());
        assertEquals(6, south.getY());

        assertEquals(4,  east.getX());
        assertEquals(5,  east.getY());

        assertEquals(2,  west.getX());
        assertEquals(5,  west.getY());

    }
}
