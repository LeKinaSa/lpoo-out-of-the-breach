package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HeroTest {
    @Test
    public void tankRangeTest1() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p2)).thenReturn(2);
        Tank tank = new Tank(p1, -10, 0, new ArrayList<>());
        assertEquals(false, tank.withinRange(p2));
    }

    @Test
    public void tankRangeTest2() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p2)).thenReturn(1);
        Tank tank = new Tank(p1, -10, 3, new ArrayList<>());
        assertEquals(true, tank.withinRange(p2));
    }

    @Test
    public void tankRangeTest3() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p2)).thenReturn(1);
        Tank tank = new Tank(p1, -10, 1, new ArrayList<>());
        assertEquals(true, tank.withinRange(p2));
    }
}
