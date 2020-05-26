package out_of_the_breach;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends Model {
    private List<TerrainTile> getLevelTiles() {
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

        return tiles;
    }

    private List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(0, 0), 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return cities;
    }

    List<Hero> getLevelAllies() {
        List<Hero> allies = new ArrayList<>();
        try {
            allies.add(new Tank(new Position(0, 1), 6, 3, 1));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            outsideOfTheGrid.printStackTrace();
        }

        return allies;
    }

    List<Enemy> getLevelEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        Bug mike = null;
        try {
            mike = new Bug(new Position(0, 2), 2, 2);
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            outsideOfTheGrid.printStackTrace();
        }
        mike.setAttackDirection(AttackDirection.NORTH);
        enemies.add(mike);

        return enemies;
    }

    public Level1() {
        super();

        setTiles(getLevelTiles());
        setAllies(getLevelAllies());
        setCities(getLevelCities());
        setEnemies(getLevelEnemies());
    }
}
