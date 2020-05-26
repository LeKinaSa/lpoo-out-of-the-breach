package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static out_of_the_breach.model.TerrainTile.*;

public class Level7 extends Level {
    @Override
    protected List<TerrainTile> getLevelTiles() {
        return new ArrayList<>(Collections.nCopies(64, PLAIN));
    }

    @Override
    protected List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(0, 5), 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(0, 6), 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(0, 7), 4));
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
            allies.add(new Tank(new Position(2, 1), 5, 1, 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Tank(new Position(4, 5), 5, 1, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(0, 3), 1, 4));
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
            enemies.add(new Dragon(new Position(5, 2), 5, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return enemies;
    }
}
