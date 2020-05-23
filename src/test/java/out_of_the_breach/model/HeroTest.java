package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HeroTest {
    @Test
    public void tankRangeTest1() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p2)).thenReturn(2);
        Tank tank = new Tank(p1, -10, 0, 1);
        assertEquals(false, tank.withinRange(p2));
    }

    @Test
    public void tankRangeTest2() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p2)).thenReturn(1);
        Tank tank = new Tank(p1, -10, 3, 1);
        assertEquals(true, tank.withinRange(p2));
    }

    @Test
    public void tankRangeTest3() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p2)).thenReturn(1);
        Tank tank = new Tank(p1, -10, 1, 1);
        assertEquals(true, tank.withinRange(p2));
    }

    @Test
    public void rangeTest() {
        Hero hero1 = new Tank(null, 10, 1, 1);
        Hero hero2 = new Tank(null, 31, 1, 9);
        assertEquals(1, hero1.getMovementRange());
        assertEquals(9, hero2.getMovementRange());
        hero1.setMovementRange(4);
        assertEquals(4, hero1.getMovementRange());
    }

    @Test
    public void movementTest() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Mockito.when(p1.distanceTo(p3)).thenReturn(7);
        Mockito.when(p1.distanceTo(p2)).thenReturn(1);
        Mockito.when(p2.distanceTo(p1)).thenReturn(1);

        Hero hero = new Tank(p1, 1, 3, 1);
        assertEquals(false, hero.getHasMoved());
        assertEquals(false, hero.getHasEndedTurn());

        assertEquals(false, hero.moveTo(p3));
        assertEquals(true, hero.moveTo(p2));
        assertEquals(true, hero.getHasMoved());

        assertEquals(false, hero.moveTo(p1));
        assertEquals(false, hero.getHasEndedTurn());

        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        Model grid = Mockito.mock(Model.class);
        hero.attack(grid, strategy);
        assertEquals(true, hero.getHasEndedTurn());
    }

    @Test
    public void attackTest() {
        Position p = Mockito.mock(Position.class);
        Hero ally = new Tank(p, 1, 1, 1);
        List<AttackStrategy> strats = new ArrayList<>();
        AttackStrategy strategy1 = Mockito.mock(AttackStrategy.class);
        AttackStrategy strategy2 = Mockito.mock(AttackStrategy.class);
        strats.add(strategy1);
        strats.add(strategy2);
        ally.setStrategies(strats);

        Model grid = Mockito.mock(Model.class);

        assertEquals(false, ally.getHasEndedTurn());

        ally.attack(grid, 3);
        assertEquals(false, ally.getHasEndedTurn());

        ally.attack(grid, -1);
        assertEquals(false, ally.getHasEndedTurn());

        ally.attack(grid, 1);
        assertEquals(true, ally.getHasEndedTurn());
        verify(strategy2, times(1)).attack(grid, p);

        ally.attack(grid, 0);
        assertEquals(true, ally.getHasEndedTurn());
        verify(strategy1, times(0)).attack(grid, p);


        assertEquals(dmgMatrix, hero.previewAttack(strategy1));
    }
}
