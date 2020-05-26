package out_of_the_breach.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityTest {
    @Test
    public void powerTest() {
        City city = new City(null, 10, 2);
        assertEquals(2, city.getPower());
        city.setPower(-1);
        assertEquals(2, city.getPower());
        city.setPower(3);
        assertEquals(3, city.getPower());
        city.setPower(0);
        assertEquals(0, city.getPower());
        city = new City(null, 2, -1);
        assertEquals(0, city.getPower());

        City alternateCity = new City(null, 10, -1);
        assertEquals(0, city.getPower());
    }
}
