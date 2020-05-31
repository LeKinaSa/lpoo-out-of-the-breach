package out_of_the_breach.model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OverlapTest {
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
        cities.add(new City(new Position(0, 0), 2));

        List<Hero> allies = new ArrayList<>();
        Hero tank = new Tank(new Position(0, 1), 2, 3, 1);
        allies.add(tank);

        List<Enemy> enemies = new ArrayList<>();
        Bug mike = new Bug(new Position(0, 2), 2, 2);
        mike.setAttackDirection(AttackDirection.NORTH);
        enemies.add(mike);

        GameModel grid = new GameModel();
        grid.setTiles(tiles); grid.setEnemies(enemies); grid.setAllies(allies); grid.setCities(cities);

        MovementMatrix m =  tank.displayMove(grid);

        assertFalse(m.getMove(new Position(0, 0)));
        assertFalse(m.getMove(new Position(0, 2)));
    }
}
