package out_of_the_breach.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AttackTest {
    @Test
    public void directionTestNoneAndSet() {
        AttackStrategy strategy = new MeleeAttack(2);
        assertEquals(AttackDirection.NONE, strategy.getDirection());

        strategy.setDirection(AttackDirection.NORTH);
        assertEquals(AttackDirection.NORTH, strategy.getDirection());

        strategy.setDirection(AttackDirection.SOUTH);
        assertEquals(AttackDirection.SOUTH, strategy.getDirection());

        strategy.setDirection(AttackDirection.EAST);
        assertEquals(AttackDirection.EAST, strategy.getDirection());

        strategy.setDirection(AttackDirection.WEST);
        assertEquals(AttackDirection.WEST, strategy.getDirection());

        strategy.setDirection(AttackDirection.NONE);
        assertEquals(AttackDirection.NONE, strategy.getDirection());
    }

    @Test
    public void directionTestConstructors() {
        AttackStrategy strategyNone  = new MeleeAttack(1, AttackDirection.NONE );
        AttackStrategy strategyNorth = new MeleeAttack(1, AttackDirection.NORTH);
        AttackStrategy strategySouth = new MeleeAttack(1, AttackDirection.SOUTH);
        AttackStrategy strategyEast  = new MeleeAttack(1, AttackDirection.EAST );
        AttackStrategy strategyWest  = new MeleeAttack(1, AttackDirection.WEST );

        assertEquals(AttackDirection.NONE ,  strategyNone.getDirection());
        assertEquals(AttackDirection.NORTH, strategyNorth.getDirection());
        assertEquals(AttackDirection.SOUTH, strategySouth.getDirection());
        assertEquals(AttackDirection.EAST ,  strategyEast.getDirection());
        assertEquals(AttackDirection.WEST ,  strategyWest.getDirection());
    }

    @Test
    public void damagedPositionTest1() {
        AttackStrategy strategyNone  = new MeleeAttack(1, AttackDirection.NONE );
        AttackStrategy strategyNorth = new MeleeAttack(1, AttackDirection.NORTH);
        AttackStrategy strategySouth = new MeleeAttack(1, AttackDirection.SOUTH);
        AttackStrategy strategyEast  = new MeleeAttack(1, AttackDirection.EAST );
        AttackStrategy strategyWest  = new MeleeAttack(1, AttackDirection.WEST );

        Position p = null;
        try {
            p = new Position (3, 3);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        assertEquals(null, strategyNone.getDamagedPosition(p));
        assertEquals(true, p.north().same(strategyNorth.getDamagedPosition(p)));
        assertEquals(true, p.south().same(strategySouth.getDamagedPosition(p)));
        assertEquals(true,  p.east().same( strategyEast.getDamagedPosition(p)));
        assertEquals(true,  p.west().same( strategyWest.getDamagedPosition(p)));

    }

    @Test
    public void damagedPositionTest2() {
        AttackStrategy strategyNorth = new MeleeAttack(1, AttackDirection.NORTH);
        AttackStrategy strategySouth = new MeleeAttack(1, AttackDirection.SOUTH);
        AttackStrategy strategyEast  = new MeleeAttack(1, AttackDirection.EAST );
        AttackStrategy strategyWest  = new MeleeAttack(1, AttackDirection.WEST );

        Position p = null;
        try {
            p = new Position (7, 0);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        assertEquals(null, strategyNorth.getDamagedPosition(p));
        assertEquals(null,  strategyEast.getDamagedPosition(p));
        assertEquals(true, p.south().same(strategySouth.getDamagedPosition(p)));
        assertEquals(true,  p.west().same( strategyWest.getDamagedPosition(p)));

    }

    @Test
    public void damagedPositionTest3() {
        AttackStrategy strategyNorth = new MeleeAttack(1, AttackDirection.NORTH);
        AttackStrategy strategySouth = new MeleeAttack(1, AttackDirection.SOUTH);
        AttackStrategy strategyEast  = new MeleeAttack(1, AttackDirection.EAST );
        AttackStrategy strategyWest  = new MeleeAttack(1, AttackDirection.WEST );

        Position p = null;
        try {
            p = new Position (0, 7);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        assertEquals(null, strategySouth.getDamagedPosition(p));
        assertEquals(null,  strategyWest.getDamagedPosition(p));
        assertEquals(true, p.north().same(strategyNorth.getDamagedPosition(p)));
        assertEquals(true,  p.east().same( strategyEast.getDamagedPosition(p)));

    }

}
