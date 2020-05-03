package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.assertEquals;

public class EnemyTest {
    @Test
    public void strategyTest() {
        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        Mockito.when(strategy.getDirection()).thenReturn(AttackDirection.NORTH);
        Enemy enemy = new Bug(null, 10, 3);

        assertEquals(MeleeAttack.class, enemy.getAttackStrategy().getClass());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());

        enemy.setAttackStrategy(strategy);
        assertEquals(strategy, enemy.getAttackStrategy());
        assertEquals(AttackDirection.NORTH, enemy.getAttackDirection());
    }

    @Test
    public void attackTest() {
        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        DamageMatrix dmgMatrix = Mockito.mock(DamageMatrix.class);
        Mockito.when(strategy.previewAttack(null)).thenReturn(dmgMatrix);

        Enemy enemy = new Bug(null, 1, 2);
        enemy.setAttackStrategy(strategy);
        assertEquals(dmgMatrix, enemy.previewAttack());
    }
}
