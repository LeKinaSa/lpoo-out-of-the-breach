package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

/*
    Class Representing the 8x8 grid.
    Contains all tiles, enemies, heroes and cities in the grid at the moment.
    Can calculate the energy left and the game status.
    Allows the heroes to start new turns and the enemies to both prepare attacks and execute them.
 */

public class GameModel {
    private List<TerrainTile> tiles;
    private List<Enemy> enemies;
    private List<Hero> allies;
    private List<City> cities;
    private int initialEnergy;
    int turns;

    public GameModel() {
        this.tiles   = new ArrayList<>(64);
        this.enemies = new ArrayList<>();
        this.allies  = new ArrayList<>();
        this.cities  = new ArrayList<>();
        setInitialEnergy();
        this.turns   = 4;
    }

    public void setTiles(List<TerrainTile> tiles) {
        this.tiles = tiles;
    }

    public List<TerrainTile> getTiles() {
        return this.tiles;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public void setAllies(List<Hero> allies) {
        this.allies = allies;
    }

    public List<Hero> getAllies() {
        return this.allies;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        setInitialEnergy();
    }

    public List<City> getCities() {
        return this.cities;
    }

    protected void setInitialEnergy() {
        this.initialEnergy = getCurrentEnergy();
    }

    public int getCurrentEnergy() {
        int res = 0;

        for (City i : cities) {
            res += i.getHp();
        }

        return res;
    }

    public int getEnergy() {
        if (initialEnergy != 0) {
            return (int) ((getCurrentEnergy() * 10.0) / initialEnergy);
        }
        return getCurrentEnergy();
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getTurns() {
        return turns;
    }

    public GameStatus getGameStatus() {
        if (enemies.size() == 0 || turns == 0) {
            return GameStatus.PLAYER_WINS;
        } else if (allies.size() == 0 || getEnergy() == 0) {
            return GameStatus.PLAYER_LOSES;
        } else {
            return GameStatus.GAME_IN_PROGRESS;
        }
    }


    /*
        Determines if an entity can move to that tile.
     */
    public boolean tileIntransitable(Position pos) {
        return this.tiles.get(pos.getLinearMatrixPosition()) == TerrainTile.MOUNTAIN;
    }

    /*
        Determines if the tile has an entity on it.
     */
    public boolean tileOccupied(Position pos) {
        return getEntityAt(pos) != null;
    }

    /*
        Obatins the entity standing on the tile.
     */
    public Entity getEntityAt(Position pos) {
        for (Enemy enemy : this.enemies) {
            if (pos.same(enemy.getPosition())) {
                return enemy;
            }
        }
        for (Hero ally : this.allies) {
            if (pos.same(ally.getPosition())) {
                return ally;
            }
        }
        for (City city : this.cities) {
            if (pos.same(city.getPosition())) {
                return city;
            }
        }
        return null;
    }

    /*
        Removes a dead entity.
     */
    public void removeEntity(Entity entity) {
        if (!entity.isDead()) {
            return;
        }

        if      (entity instanceof Hero) {
            this.allies.remove(entity);
        }
        else if (entity instanceof Enemy) {
            enemies.remove(entity);
        }
        else if (entity instanceof City) {
            cities.remove(entity);
        }
        return;
    }

    /*
        Inflicts damage in a tile inside the grid.
        If the entities dies, remove it.
     */
    public void inflictDamage(Position pos, int damage) {
        if (this.tileOccupied(pos)) {
            Entity entity = this.getEntityAt(pos);
            entity.takeDamage(damage);
            if (entity.isDead()) {
                this.removeEntity(entity);
            }
        }
    }

    /*
        Starts new turn for all the heroes.
     */
    public void resetHeroes() {
        for (Hero h : allies) {
            h.reset();
        }
    }

    /*
        All the enemies move and prepare its attacks.
     */
    public void planAttack() {
        for (Enemy enemy : this.enemies) {
            enemy.moveAndPlanAttack(this);
        }
    }

    /*
        All the enemies will attack.
     */
    public void executeAttack() {
        for (Enemy enemy : this.enemies) {
            enemy.attack(this);
        }
        turns--;
    }
}
