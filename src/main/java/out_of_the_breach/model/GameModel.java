package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

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

    public GameModel(List<TerrainTile> tiles) {
        this.tiles   = tiles;
        this.enemies = new ArrayList<>();
        this.allies  = new ArrayList<>();
        this.cities  = new ArrayList<>();
        setInitialEnergy();
    }

    public GameModel(List<TerrainTile> tiles, List<Enemy> enemies, List<Hero> allies, List<City> cities) {
        this.tiles   = tiles;
        this.enemies = enemies;
        this.allies  = allies;
        this.cities  = cities;
        setInitialEnergy();
    }

    public boolean tileIntransitable(Position pos) {
        return this.tiles.get(pos.getLinearMatrixPosition()) == TerrainTile.MOUNTAIN;
    }

    public boolean tileOccupied(Position pos) {
        return getEntityAt(pos) != null;
    }

    public Entity getEntityAt(Position pos) {
        for (Enemy enemy : this.enemies) {
            if (enemy.getPosition().same(pos)) {
                return enemy;
            }
        }
        for (Hero ally : this.allies) {
            if (ally.getPosition().same(pos)) {
                return ally;
            }
        }
        for (City city : this.cities) {
            if (city.getPosition().same(pos)) {
                return city;
            }
        }
        return null;
    }

    public void addEnemy(Enemy enemy) throws OccupiedTile {
        if (!this.tileOccupied(enemy.getPosition())) {
            this.enemies.add(enemy);
        }
        else {
            throw new OccupiedTile();
        }
    }

    public void addAlly(Hero ally) throws OccupiedTile {
        if (!this.tileOccupied(ally.getPosition())) {
            this.allies.add(ally);
        }
        else {
            throw new OccupiedTile();
        }

    }

    public void addCity(City city) throws OccupiedTile {
        if (!this.tileOccupied(city.getPosition())) {
            this.cities.add(city);
        }
        else {
            throw new OccupiedTile();
        }
    }

    public void removeEntity(Entity entity) {
        if (entity instanceof Hero) {
            this.allies.remove(entity);
            return;
        }
        if (entity instanceof Enemy) {
            enemies.remove(entity);
            return;
        }
        if (entity instanceof City) {
            cities.remove(entity);
            return;
        }
        return;
    }

    public void inflictDamage(Position pos, int damage) {
        if (this.tileOccupied(pos)) {
            Entity entity = this.getEntityAt(pos);
            entity.takeDamage(damage);
            if (entity.isDead()) {
                this.removeEntity(entity);
            }
        }
    }

    public List<TerrainTile> getTiles() {
        return this.tiles;
    }

    public void setTiles(List<TerrainTile> tiles) {
        this.tiles = tiles;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Hero> getAllies() {
        return this.allies;
    }

    public void setAllies(List<Hero> allies) {
        this.allies = allies;
    }

    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
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
            return (int) ((getCurrentEnergy() * 1.0 / initialEnergy) * 10);
        }
        return getCurrentEnergy();
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
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

    public void planAttack() {
        for (Enemy enemy : this.enemies) {
            enemy.moveAndPlanAttack(this);
        }
    }

    public void executeAttack() {
        for (Enemy enemy : this.enemies) {
            enemy.attack(this);
        }
        turns--;
    }

    public void resetHeroes() {
        for (Hero h : allies) {
            h.reset();
        }
    }

}
