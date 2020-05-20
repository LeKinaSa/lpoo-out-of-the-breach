package out_of_the_breach.model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OverlapTest {
    @Test
    public void overlapInitialization() throws OutsideOfTheGrid {
        List<TerrainTile> terrain = new ArrayList<>();
        for (int index = 0; index <= 63; index ++) {
            terrain.add(TerrainTile.PLAIN);
        }

        Hero  ally1  = new Hero(new Position(0, 0), 2, 3, new ArrayList<>()) {
            @Override
            public boolean withinRange(Position pos) {
                return false;
            }
        };
        City city1 = new City(new Position(0, 0), 2, 2);

        List<Enemy> enemies = new ArrayList<>();

        List<Hero > allies  = new ArrayList<>();
        allies.add(ally1);
        List<City > cities  = new ArrayList<>();
        cities.add(city1);

        Model grid = new Model(terrain, enemies, allies, cities);

        fail(); // The city and the hero occupy the same tile, (this should be impossible)
    }

    @Test
    public void overlapMove() throws OutsideOfTheGrid {
        List<TerrainTile> terrain = new ArrayList<>();
        for (int index = 0; index <= 63; index ++) {
            terrain.add(TerrainTile.PLAIN);
        }

        Hero  ally1  = new Hero(new Position(0, 0), 2, 3, new ArrayList<>()) {
            @Override
            public boolean withinRange(Position pos) {
                return true;
            }
        };
        City city1 = new City(new Position(1, 1), 2, 2);

        List<Enemy> enemies = new ArrayList<>();

        List<Hero > allies  = new ArrayList<>();
        allies.add(ally1);
        List<City > cities  = new ArrayList<>();
        cities.add(city1);

        Model grid = new Model(terrain, enemies, allies, cities);

        assertFalse(ally1.moveTo(new Position(1, 1)));
        // Perhaps this only should be done via the Model object
        // Or, the hero can check against the model whether or not it can move to the position
    }

    @Test
    public void overlapMovementGrid() throws OutsideOfTheGrid {
        List<TerrainTile> tiles = new ArrayList<>();
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.MOUNTAIN); tiles.add(TerrainTile.MOUNTAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.MOUNTAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        List<City> cities = new ArrayList<>();
        cities.add(new City(new Position(0, 0), 2, 2));

        List<Hero> allies = new ArrayList<>();
        List<AttackStrategy> strats = new ArrayList<>();
        Hero tank = new Tank(new Position(0, 1), 2, 3, strats);
        allies.add(tank);

        List<Enemy> enemies = new ArrayList<>();
        Bug mike = new Bug(new Position(0, 2), 2, 2);
        mike.setAttackDirection(AttackDirection.NORTH);
        enemies.add(mike);

        Model model = new Model(tiles, enemies, allies, cities);

        MovementMatrix m =  tank.displayMove(model);

        assertFalse(m.getMove(new Position(0, 0)));
        assertFalse(m.getMove(new Position(0, 2)));
    }
}
