import model.City;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityTest {
    @Test
    public void powerTest() {
        City city = new City(null, 10, 2);
        assertEquals(2, city.getPower());
    }
}
