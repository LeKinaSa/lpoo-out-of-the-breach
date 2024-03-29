package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static out_of_the_breach.model.AttackDirection.*;

public class AttackTest {
    @Test
    public void LineAttackRange1DirectionsTest() {
        Position p1 = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position east  = Mockito.mock(Position.class);
        Position west  = Mockito.mock(Position.class);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west);
        Mockito.when(p1.adjacentPos( NONE)).thenReturn(   p1);
        Mockito.when(   p1.getLinearMatrixPosition()).thenReturn(30);
        Mockito.when(north.getLinearMatrixPosition()).thenReturn(22);
        Mockito.when(south.getLinearMatrixPosition()).thenReturn(38);
        Mockito.when( east.getLinearMatrixPosition()).thenReturn(29);
        Mockito.when( west.getLinearMatrixPosition()).thenReturn(31);

        AttackStrategy strategy = new LineAttack(2, 1);
        assertEquals(AttackDirection.NONE, strategy.getDirection());
        List<Integer> matrix = new ArrayList<>(Collections.nCopies(64, 0));
        DamageMatrix damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);

        strategy.setDirection(NORTH);
        assertEquals(NORTH, strategy.getDirection());
        matrix.set(22, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(22, 0);

        strategy.setDirection(AttackDirection.SOUTH);
        assertEquals(AttackDirection.SOUTH, strategy.getDirection());
        matrix.set(38, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(38, 0);

        strategy.setDirection(AttackDirection.EAST);
        assertEquals(AttackDirection.EAST, strategy.getDirection());
        matrix.set(29, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(29, 0);

        strategy.setDirection(AttackDirection.WEST);
        assertEquals(AttackDirection.WEST, strategy.getDirection());
        matrix.set(31, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(31, 0);
    }

    @Test
    public void LineAttackRange3DirectionsTest() {
        Position p1 = Mockito.mock(Position.class);
        Position n   = Mockito.mock(Position.class);
        Position nn  = Mockito.mock(Position.class);
        Position s   = Mockito.mock(Position.class);
        Position ss  = Mockito.mock(Position.class);
        Position sss = Mockito.mock(Position.class);
        Position e   = Mockito.mock(Position.class);
        Position ee  = Mockito.mock(Position.class);
        Position w   = Mockito.mock(Position.class);
        Position ww  = Mockito.mock(Position.class);
        Position www = Mockito.mock(Position.class);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(n);
        Mockito.when( n.adjacentPos(NORTH)).thenReturn(nn);
        Mockito.when(nn.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(s);
        Mockito.when( s.adjacentPos(SOUTH)).thenReturn(ss);
        Mockito.when(ss.adjacentPos(SOUTH)).thenReturn(sss);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn(e);
        Mockito.when( e.adjacentPos( EAST)).thenReturn(ee);
        Mockito.when(ee.adjacentPos( EAST)).thenReturn(null);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn(w);
        Mockito.when( w.adjacentPos( WEST)).thenReturn(ww);
        Mockito.when(ww.adjacentPos( WEST)).thenReturn(www);

        Mockito.when( p1.getLinearMatrixPosition()).thenReturn(18);
        Mockito.when(  n.getLinearMatrixPosition()).thenReturn(10);
        Mockito.when( nn.getLinearMatrixPosition()).thenReturn( 2);
        Mockito.when(  s.getLinearMatrixPosition()).thenReturn(26);
        Mockito.when( ss.getLinearMatrixPosition()).thenReturn(34);
        Mockito.when(sss.getLinearMatrixPosition()).thenReturn(42);
        Mockito.when(  e.getLinearMatrixPosition()).thenReturn(17);
        Mockito.when( ee.getLinearMatrixPosition()).thenReturn(16);
        Mockito.when(  w.getLinearMatrixPosition()).thenReturn(19);
        Mockito.when( ww.getLinearMatrixPosition()).thenReturn(20);
        Mockito.when(www.getLinearMatrixPosition()).thenReturn(21);

        AttackStrategy strategy = new LineAttack(2, 3);
        assertEquals(AttackDirection.NONE, strategy.getDirection());
        List<Integer> matrix = new ArrayList<>(Collections.nCopies(64, 0));
        DamageMatrix damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);

        strategy.setDirection(NORTH);
        assertEquals(NORTH, strategy.getDirection());
        matrix.set(10, 2); matrix.set(2, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(10, 0); matrix.set(2, 0);

        strategy.setDirection(AttackDirection.SOUTH);
        assertEquals(AttackDirection.SOUTH, strategy.getDirection());
        matrix.set(26, 2); matrix.set(34, 2); matrix.set(42, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(26, 0); matrix.set(34, 0); matrix.set(42, 0);

        strategy.setDirection(AttackDirection.EAST);
        assertEquals(AttackDirection.EAST, strategy.getDirection());
        matrix.set(17, 2); matrix.set(16, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
        matrix.set(17, 0); matrix.set(16, 0);

        strategy.setDirection(AttackDirection.WEST);
        assertEquals(AttackDirection.WEST, strategy.getDirection());
        matrix.set(19, 2); matrix.set(20, 2); matrix.set(21, 2);
        damageMatrix = strategy.previewAttack(p1);
        assertEquals(matrix, damageMatrix.incomingDamage);
    }

    @Test
    public void AOEAttackDirectionNoneAndNorthTest() {
        Position p = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position east  = Mockito.mock(Position.class);
        Position west  = Mockito.mock(Position.class);

        Mockito.when(p.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p.adjacentPos( EAST)).thenReturn( east);
        Mockito.when(p.adjacentPos( WEST)).thenReturn( west);
        Mockito.when(    p.getLinearMatrixPosition()).thenReturn(36);
        Mockito.when(north.getLinearMatrixPosition()).thenReturn(28);
        Mockito.when(south.getLinearMatrixPosition()).thenReturn(44);
        Mockito.when( east.getLinearMatrixPosition()).thenReturn(35);
        Mockito.when( west.getLinearMatrixPosition()).thenReturn(37);

        Position nn = Mockito.mock(Position.class);
        Position ne = Mockito.mock(Position.class);
        Position nw = Mockito.mock(Position.class);
        Mockito.when(north.adjacentPos(NORTH)).thenReturn(nn);
        Mockito.when(north.adjacentPos(SOUTH)).thenReturn(p);
        Mockito.when(north.adjacentPos( EAST)).thenReturn(ne);
        Mockito.when(north.adjacentPos( WEST)).thenReturn(nw);
        Mockito.when(nn.getLinearMatrixPosition()).thenReturn(20);
        Mockito.when(ne.getLinearMatrixPosition()).thenReturn(27);
        Mockito.when(nw.getLinearMatrixPosition()).thenReturn(29);

        AttackStrategy strategy1 = new AOEAttack(2);
        assertEquals(AttackDirection.NONE, strategy1.getDirection());
        List<Integer> matrix = new ArrayList<>(Collections.nCopies(64, 0));
        matrix.set(28, 2); matrix.set(44, 2); matrix.set(35, 2); matrix.set(37, 2);
        DamageMatrix damageMatrix = strategy1.previewAttack(p);
        assertEquals(matrix, damageMatrix.incomingDamage);

        AttackStrategy strategy2 = new AOEAttack(2, NORTH);
        assertEquals(AttackDirection.NORTH, strategy2.getDirection());
        matrix = new ArrayList<>(Collections.nCopies(64, 0));
        matrix.set(28, 2); matrix.set(20, 2); matrix.set(36, 2); matrix.set(27, 2); matrix.set(29, 2);
        damageMatrix = strategy2.previewAttack(p);
        assertEquals(matrix, damageMatrix.incomingDamage);
    }

    @Test
    public void AOEAttackOutsideOfGridTest() {
        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.adjacentPos(L_SOUTHEAST)).thenReturn(null);
        AttackStrategy strategy = new AOEAttack(2, L_SOUTHEAST);
        assertEquals(L_SOUTHEAST, strategy.getDirection());
        DamageMatrix damageMatrix = strategy.previewAttack(pos);
        assertEquals(new ArrayList<>(Collections.nCopies(64, 0)), damageMatrix.incomingDamage);
    }

    @Test
    public void AttackTest() {
        Position p = Mockito.mock(Position.class);
        AttackStrategy strategy = new LineAttack(2, 1, WEST);

        GameModel grid = Mockito.mock(GameModel.class);
        ArgumentCaptor<Position> positions = ArgumentCaptor.forClass(Position.class);
        ArgumentCaptor<Integer> damage = ArgumentCaptor.forClass(Integer.class);

        strategy.attack(grid, p);
        verify(grid, times(64)).inflictDamage(positions.capture(), damage.capture());
        for (Position pos : positions.getAllValues()) {
            assertEquals(true, (pos.getLinearMatrixPosition() >= 0) && (pos.getLinearMatrixPosition() < 64));
        }
    }
}
