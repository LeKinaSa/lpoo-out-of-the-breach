package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.List;

public abstract class Level extends GameModel {
    public Level() {
        super();
        start();
    }

    public void start() {
        setTiles(getLevelTiles());
        setAllies(getLevelAllies());
        setCities(getLevelCities());
        setEnemies(getLevelEnemies());
    }

    protected abstract List<TerrainTile> getLevelTiles();

    protected abstract List<City> getLevelCities();

    protected abstract List<Hero> getLevelAllies();

    protected abstract List<Enemy> getLevelEnemies();


}
