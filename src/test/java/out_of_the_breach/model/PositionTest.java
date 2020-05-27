package out_of_the_breach.model;

import org.junit.Test;

import static org.junit.Assert.*;

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

        assertNull(north);
        assertNull(west);

        assertEquals(0, south.getX());
        assertEquals(1, south.getY());

        assertEquals(1,  east.getX());
        assertEquals(0,  east.getY());

        assertTrue(south.same(pos.adjacentPos(AttackDirection.SOUTH)));
        assertTrue(east.same (pos.adjacentPos(AttackDirection.EAST )));
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

    @Test
    public void adjacentPosTest() {
        Position p = null;
        try {
            p = new Position(3, 3);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        Position north = p.adjacentPos(AttackDirection.NORTH);
        Position south = p.adjacentPos(AttackDirection.SOUTH);
        Position east  = p.adjacentPos(AttackDirection.EAST);
        Position west  = p.adjacentPos(AttackDirection.WEST);
        Position l_ne  = p.adjacentPos(AttackDirection.L_NORTHEAST);
        Position l_nw  = p.adjacentPos(AttackDirection.L_NORTHWEST);
        Position l_se  = p.adjacentPos(AttackDirection.L_SOUTHEAST);
        Position l_sw  = p.adjacentPos(AttackDirection.L_SOUTHWEST);
        Position l_en  = p.adjacentPos(AttackDirection.L_EASTNORTH);
        Position l_es  = p.adjacentPos(AttackDirection.L_EASTSOUTH);
        Position l_wn  = p.adjacentPos(AttackDirection.L_WESTNORTH);
        Position l_ws  = p.adjacentPos(AttackDirection.L_WESTSOUTH);
        Position none  = p.adjacentPos(AttackDirection.NONE);


        assertEquals(3, p.getX());
        assertEquals(3, p.getY());

        assertEquals(3, north.getX());
        assertEquals(2, north.getY());

        assertEquals(3, south.getX());
        assertEquals(4, south.getY());

        assertEquals(4, east.getX());
        assertEquals(3, east.getY());

        assertEquals(2, west.getX());
        assertEquals(3, west.getY());

        assertEquals(4, l_ne.getX());
        assertEquals(1, l_ne.getY());

        assertEquals(2, l_nw.getX());
        assertEquals(1, l_nw.getY());

        assertEquals(4, l_se.getX());
        assertEquals(5, l_se.getY());

        assertEquals(2, l_sw.getX());
        assertEquals(5, l_sw.getY());

        assertEquals(5, l_en.getX());
        assertEquals(2, l_en.getY());

        assertEquals(5, l_es.getX());
        assertEquals(4, l_es.getY());

        assertEquals(1, l_wn.getX());
        assertEquals(2, l_wn.getY());

        assertEquals(1, l_ws.getX());
        assertEquals(4, l_ws.getY());

        assertEquals(3, none.getX());
        assertEquals(3, none.getY());
    }

    @Test
    public void adjacentPosOutsideOfGridTest() {
        Position p1 = null, p2 = null;
        try {
            p1 = new Position(7, 7);
            p2 = new Position(0, 0);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        Position l_se = null, l_sw = null, l_wn = null, l_ws = null;
        Position l_ne = null, l_nw = null, l_en = null, l_es = null;

        try {
            l_se  = p1.adjacentPos(AttackDirection.L_SOUTHEAST);
            l_sw  = p1.adjacentPos(AttackDirection.L_SOUTHWEST);
            l_wn  = p1.adjacentPos(AttackDirection.L_WESTNORTH);
            l_ws  = p1.adjacentPos(AttackDirection.L_WESTSOUTH);

            l_ne  = p2.adjacentPos(AttackDirection.L_NORTHEAST);
            l_nw  = p2.adjacentPos(AttackDirection.L_NORTHWEST);
            l_en  = p2.adjacentPos(AttackDirection.L_EASTNORTH);
            l_es  = p2.adjacentPos(AttackDirection.L_EASTSOUTH);
        }
        catch (NullPointerException n) {
            assertEquals(false, true);
        }

        assertEquals(7, p1.getX());
        assertEquals(7, p1.getY());

        assertEquals(0, p2.getX());
        assertEquals(0, p2.getY());

        assertEquals(null, l_se);
        assertEquals(null, l_sw);
        assertEquals(null, l_wn);
        assertEquals(null, l_ws);

        assertEquals(null, l_ne);
        assertEquals(null, l_nw);
        assertEquals(null, l_en);
        assertEquals(null, l_es);
    }
}
