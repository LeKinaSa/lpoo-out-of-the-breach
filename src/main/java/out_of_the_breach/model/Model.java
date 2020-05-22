package out_of_the_breach.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<TerrainTile> tiles;
    private List<Enemy> enemies;
    private List<Hero> allies;
    private List<City> cities;

    public Model() {
        this.tiles   = new ArrayList<>(64);
        this.enemies = new ArrayList<>();
        this.allies  = new ArrayList<>();
        this.cities  = new ArrayList<>();
    }

    public Model(List<TerrainTile> tiles) {
        this.tiles   = tiles;
        this.enemies = new ArrayList<>();
        this.allies  = new ArrayList<>();
        this.cities  = new ArrayList<>();
    }

    public Model(List<TerrainTile> tiles, List<Enemy> enemies, List<Hero> allies, List<City> cities) {
        this.tiles   = tiles;
        this.enemies = enemies;
        this.allies  = allies;
        this.cities  = cities;
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

    public void planAttack() {
        for (Enemy enemy : this.enemies) {
            enemy.moveAndPlanAttack(this);
        }
    }

    public void executeAttack() {
        for (Enemy enemy : this.enemies) {
            enemy.attack(this);
        }
    }

    public void resetHeroes() {
        for (Hero h : allies) {
            h.reset();
        }
    }
}
