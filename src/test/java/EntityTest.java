import model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EntityTest {
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
    public void positionTest() {
        Position p = null;
        Position pos = null;
        try {
            p = new Position(2, 2);
            pos = new Position(3, 4);
        }
        catch (OutsideOfTheGrid o) {
            assertEquals(false, true);
        }

        Entity entity1 = new City(p, 2, 1);
        Entity entity2 = new Bug(p, 2,3);
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

}
