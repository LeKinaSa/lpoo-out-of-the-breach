package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HeroTest {
    @Test
    public void tankRangeTest() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);
        Mockito.when(p2.distanceTo(p1)).thenReturn(1);
        Mockito.when(p3.distanceTo(p1)).thenReturn(2);
        Mockito.when(p4.distanceTo(p1)).thenReturn(3);
        Tank tank = new Tank(p1, -10, 0, 2);
        assertEquals( true, tank.withinRange(p2));
        assertEquals( true, tank.withinRange(p3));
        assertEquals(false, tank.withinRange(p4));
    }

    @Test
    public void archerRangeTest() {
        Hero ally = new Archer(null, -10, -1);
        assertEquals(true, ally.withinRange(Mockito.mock(Position.class)));
        assertEquals(0, ally.getHp());
        assertEquals(8, ally.getStrategies().size());
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
        Mockito.when(p3.distanceTo(p1)).thenReturn(7);
        Mockito.when(p2.distanceTo(p1)).thenReturn(1);
        Mockito.when(p1.distanceTo(p2)).thenReturn(1);

        Hero hero = new Tank(p1, 1, 3, 1);
        assertEquals(false, hero.getHasMoved());
        assertEquals(false, hero.getHasEndedTurn());

        assertEquals(false, hero.moveTo(p3));
        assertEquals(true, hero.moveTo(p2));
        assertEquals(p2, hero.getPosition());
        assertEquals(true, hero.getHasMoved());

        assertEquals(false, hero.moveTo(p1));
        assertEquals(false, hero.getHasEndedTurn());

        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        GameModel grid = Mockito.mock(GameModel.class);
        hero.attack(grid, strategy);
        assertEquals(true, hero.getHasEndedTurn());

        hero.reset();
        assertEquals(false, hero.getHasMoved());
        assertEquals(false, hero.getHasEndedTurn());
    }

    @Test
    public void attackTest() {
        Position p = Mockito.mock(Position.class);
        Hero ally = new Tank(p, 1, 1, 1);
        assertEquals(4, ally.getStrategies().size());
        List<AttackStrategy> strats = new ArrayList<>();
        AttackStrategy strategy1 = Mockito.mock(AttackStrategy.class);
        AttackStrategy strategy2 = Mockito.mock(AttackStrategy.class);
        strats.add(strategy1);
        strats.add(strategy2);
        ally.setStrategies(strats);

        GameModel grid = Mockito.mock(GameModel.class);

        assertEquals(2, ally.getStrategies().size());

        assertEquals(false, ally.getHasEndedTurn());

        ally.attack(grid, 2);
        assertEquals(false, ally.getHasEndedTurn());

        ally.attack(grid, -1);
        assertEquals(false, ally.getHasEndedTurn());

        ally.attack(grid, 1);
        assertEquals(true, ally.getHasEndedTurn());
        verify(strategy2, times(1)).attack(grid, p);

        ally.attack(grid, 0);
        assertEquals(true, ally.getHasEndedTurn());
        verify(strategy1, times(0)).attack(grid, p);
    }

    @Test
    public void displayMoveTest() {
        class GameModelStub extends GameModel {
            @Override
            public boolean tileOccupied(Position p) {
                return false;
            }
            @Override
            public boolean tileIntransitable(Position p) {
                return false;
            }
        }
        GameModel grid = new GameModelStub();
        Position p = Mockito.mock(Position.class);
        Hero ally1 = new Tank  (p, 1, 1, 3);
        Hero ally2 = new Archer(p, 1, 1);

        Mockito.when(p.getX()).thenReturn(3);
        Mockito.when(p.getY()).thenReturn(3);
        Mockito.when(p.getLinearMatrixPosition()).thenReturn(27);

        List<Boolean> matrix = new ArrayList<>(Collections.nCopies(64, false));
        matrix.set( 3, true);
        matrix.set(10, true); matrix.set(11, true); matrix.set(12, true);
        matrix.set(17, true); matrix.set(18, true); matrix.set(19, true); matrix.set(20, true); matrix.set(21, true);
        matrix.set(24, true); matrix.set(25, true); matrix.set(26, true); matrix.set(27, true); matrix.set(28, true); matrix.set(29, true); matrix.set(30, true);
        matrix.set(33, true); matrix.set(34, true); matrix.set(35, true); matrix.set(36, true); matrix.set(37, true);
        matrix.set(42, true); matrix.set(43, true); matrix.set(44, true);
        matrix.set(51, true);
        MovementMatrix mov1 = ally1.displayMove(grid);
        MovementMatrix mov2 = ally2.displayMove(grid);

        assertEquals(matrix, mov1.possibleMoves);
        assertEquals(new ArrayList<>(Collections.nCopies(64, true)), mov2.possibleMoves);
    }
}
