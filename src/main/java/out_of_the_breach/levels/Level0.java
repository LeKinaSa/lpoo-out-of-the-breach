package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static out_of_the_breach.model.TerrainTile.MOUNTAIN;
import static out_of_the_breach.model.TerrainTile.PLAIN;

public class Level0 extends Level {
    @Override
    protected List<TerrainTile> getLevelTiles() {
        List<TerrainTile> tiles = new ArrayList<>(Collections.nCopies(64, PLAIN));
        tiles.set(41, MOUNTAIN);
        tiles.set(42, MOUNTAIN);
        tiles.set(49, MOUNTAIN);
        return tiles;
    }

    @Override
    protected List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(0, 0), 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return cities;
    }

    @Override
    protected List<Hero> getLevelAllies() {
        List<Hero> allies = new ArrayList<>();
        try {
            allies.add(new Tank(new Position(0, 1), 6, 3, 1));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            // Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return allies;
    }

    @Override
    protected List<Enemy> getLevelEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        Bug mike = null;
        try {
            mike = new Bug(new Position(0, 2), 2, 2);
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            // Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        mike.setAttackDirection(AttackDirection.NORTH);
        enemies.add(mike);

        return enemies;
    }
}
