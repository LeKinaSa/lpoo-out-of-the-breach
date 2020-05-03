package out_of_the_breach.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EntityTest {
    @Test
    public void positionTest() {
        Position p = null;
        Position pos = null;
        try {
            p   = new Position(2, 2);
            pos = new Position(3, 4);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        Entity entity1 = new City(p, 2, 1);
        Entity entity2 = new Bug(p, 3,3);
        Entity entity3 = new Tank(p, 10, 3, new ArrayList<>());

        assertEquals(p, entity1.getPosition());
        assertEquals(p, entity2.getPosition());
        assertEquals(p, entity3.getPosition());

        entity1.setPosition(pos);
        entity3.setPosition(pos);
        assertEquals(pos, entity1.getPosition());
        assertEquals(p  , entity2.getPosition());
        assertEquals(pos, entity3.getPosition());
    }

    @Test
    public void hpTest() {
        Entity entity1 = new City(null, -10, 2);
        Entity entity2 = new  Bug(null, 0,3);
        Entity entity3 = new Tank(null, 5, 3, new ArrayList<>());

        assertEquals(0, entity1.getHp());
        assertEquals(0, entity2.getHp());
        assertEquals(5, entity3.getHp());
    }

    @Test
    public void cityTest() {
        Entity entity = new City(null, 10, 3);
        assertEquals(10, entity.getHp());
        assertEquals(false, entity.isDead());

        entity.takeDamage(2);
        assertEquals(8, entity.getHp());
        assertEquals(false, entity.isDead());

        entity.takeDamage(8);
        assertEquals(0, entity.getHp());
        assertEquals(true, entity.isDead());

        entity.setHp(20);
        assertEquals(20, entity.getHp());

        entity.takeDamage(21);
        assertEquals(0, entity.getHp());
        assertEquals(true, entity.isDead());
    }

    @Test
    public void enemyTest() {
        Entity entity = new Bug(null, 0, 2);
        assertEquals(0, entity.getHp());

        entity.setHp(-10);
        assertEquals(0, entity.getHp());

        entity.setHp(20);
        assertEquals(20, entity.getHp());

        entity.takeDamage(-10);
        assertEquals(20, entity.getHp());

        entity.takeDamage(2);
        assertEquals(18, entity.getHp());
    }

    @Test
    public void heroTest() {
        Entity entity = new Tank(null, 10, 2, new ArrayList<>());
        assertEquals(null, entity.getPosition());
        assertEquals(10, entity.getHp());
        assertEquals(false, entity.isDead());
        entity.takeDamage(30);
        assertEquals(true, entity.isDead());
    }
}
