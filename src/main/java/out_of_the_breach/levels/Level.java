package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.List;

/*
    Represents a Level that can be played.
 */

public abstract class Level extends GameModel {
    public Level() {
        super();
        start();
    }

    /*
        To start the Level.
        Can be used to Restart the Level too.
     */
    public void start() {
        setTiles(getLevelTiles());
        setAllies(getLevelAllies());
        setCities(getLevelCities());
        setEnemies(getLevelEnemies());
        setTurns(4);
    }

    /*
        Obtain the tiles for the level.
     */
    protected abstract List<TerrainTile> getLevelTiles();

    /*
        Obtain the cities for the level.
     */
    protected abstract List<City> getLevelCities();

    /*
        Obtain the heros for the level.
     */
    protected abstract List<Hero> getLevelAllies();

    /*
        Obtain the enemies for the level.
     */
    protected abstract List<Enemy> getLevelEnemies();
}
