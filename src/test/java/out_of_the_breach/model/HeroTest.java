package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        Hero hero2 = new Tank(null, 31, 9, 1);
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
        //TODO
        Position p = Mockito.mock(Position.class);
        AttackStrategy strategy1 = Mockito.mock(AttackStrategy.class);
        AttackStrategy strategy2 = Mockito.mock(AttackStrategy.class);
        Model grid = Mockito.mock(Model.class);
        DamageMatrix dmgMatrix = Mockito.mock(DamageMatrix.class);
        Mockito.when(strategy1.previewAttack(p)).thenReturn(dmgMatrix);

        Hero hero = new Tank(p, 1, 3, 1);

        // Exception Zone
        hero.attack(grid, 3);
        hero.attack(grid, -1);
        hero.attack(grid, 1);

        assertEquals(dmgMatrix, hero.previewAttack(strategy1));
    }
}
