package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static out_of_the_breach.model.TerrainTile.*;

public class Level5 extends Level {
    @Override
    protected List<TerrainTile> getLevelTiles() {
        List<TerrainTile> tiles = new ArrayList<>(Collections.nCopies(64, PLAIN));
        tiles.set(28, MOUNTAIN);
        tiles.set(29, MOUNTAIN);
        tiles.set(36, MOUNTAIN);
        tiles.set(44, MOUNTAIN);
        tiles.set(46, MOUNTAIN);
        tiles.set(53, MOUNTAIN);
        tiles.set(54, MOUNTAIN);
        tiles.set(48, MOUNTAIN);
        tiles.set(56, MOUNTAIN);
        return tiles;
    }

    @Override
    protected List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(2, 0), 2));
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
            allies.add(new Tank(new Position(1, 5), 4, 1, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(0, 2), 1, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return allies;
    }

    @Override
    protected List<Enemy> getLevelEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        try {
            enemies.add(new Lizard(new Position(1, 7), 4, 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return enemies;
    }
}
