package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static out_of_the_breach.model.TerrainTile.*;

public class Level2 extends Level {
    @Override
    protected List<TerrainTile> getLevelTiles() {
        List<TerrainTile> tiles = new ArrayList<>(Collections.nCopies(64, PLAIN));
        tiles.set(32, MOUNTAIN);
        tiles.set(52, MOUNTAIN);
        tiles.set(59, MOUNTAIN);
        tiles.set(60, MOUNTAIN);
        tiles.set(61, MOUNTAIN);
        return tiles;
    }

    @Override
    protected List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(6, 1), 6));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(5, 5), 6));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(1, 6), 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(2, 6), 4));
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
            allies.add(new Tank(new Position(1, 4), 5, 1, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(7, 7), 3, 3));
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
            enemies.add(new Bug(new Position(2, 1), 4, 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            enemies.add(new Bug(new Position(5, 3), 4, 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return enemies;
    }
}
