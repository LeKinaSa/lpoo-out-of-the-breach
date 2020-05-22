package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static out_of_the_breach.model.AttackDirection.*;

public class AttackTest {
    @Test
    public void LineAttackRange1Directions() {
        Position p1 = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position east  = Mockito.mock(Position.class);
        Position west  = Mockito.mock(Position.class);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west);
        Mockito.when(   p1.getLinearMatrixPosition()).thenReturn(30);
        Mockito.when(north.getLinearMatrixPosition()).thenReturn(22);
        Mockito.when(south.getLinearMatrixPosition()).thenReturn(38);
        Mockito.when( east.getLinearMatrixPosition()).thenReturn(29);
        Mockito.when( west.getLinearMatrixPosition()).thenReturn(31);

        AttackStrategy strategy = new LineAttack(2, 1);
        assertEquals(AttackDirection.NONE, strategy.getDirection());
        List<Integer> matrix = new ArrayList<Integer>(Collections.nCopies(64, 0));
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
    public void LineAttackRange3Directions() {
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
        List<Integer> matrix = new ArrayList<Integer>(Collections.nCopies(64, 0));
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
}
