package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class DamageMatrixTest {
    @Test
    public void damageTest() {
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Mockito.when(p1.getLinearMatrixPosition()).thenReturn(23);
        Mockito.when(p2.getLinearMatrixPosition()).thenReturn(0);
        Mockito.when(p3.getLinearMatrixPosition()).thenReturn(15);

        DamageMatrix dmgMatrix = new DamageMatrix();
        for (int index = 0; index <= 63; index ++) {
            assertEquals(Integer.valueOf(0), dmgMatrix.incomingDamage.get(index));
        }

        dmgMatrix.setDamage(p1, 5);
        dmgMatrix.setDamage(p2, 3);
        dmgMatrix.setDamage(p3, -10);

        assertEquals(5, dmgMatrix.getDamage(p1));
        assertEquals(3, dmgMatrix.getDamage(p2));
        assertEquals(0, dmgMatrix.getDamage(p3));

        for (int index = 0; index <= 63; index ++) {
            if (index == 23) {
                assertEquals(Integer.valueOf(5), dmgMatrix.incomingDamage.get(index)); // p1
            }
            else if (index == 0) {
                assertEquals(Integer.valueOf(3), dmgMatrix.incomingDamage.get(index)); // p2
            }
            else {
                assertEquals(Integer.valueOf(0), dmgMatrix.incomingDamage.get(index)); // p3 + others
            }
        }

        dmgMatrix.setDamage(p2, 0);
        assertEquals(0, dmgMatrix.getDamage(p2));
    }
}
