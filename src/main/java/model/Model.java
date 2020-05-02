package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<TerrainTile> terrain;
    private List<Enemy> enemies;
    private List<Ally> allies;
    private List<City> cities;

    public Model() {
        this.terrain = new ArrayList<>(64);
        this.enemies = new ArrayList<>();
        this.allies = new ArrayList<>();
        this.cities = new ArrayList<>();
    }

    public Model(List<TerrainTile> terrain, List<Enemy> enemies, List<Ally> allies, List<City> cities) {
        this.terrain = terrain;
        this.enemies = enemies;
        this.allies = allies;
        this.cities = cities;
    }

    public boolean hasTerrain(Position pos) {
        return this.terrain.get(pos.getLinearMatrixPosition()) != null;
    }

    public TerrainTile getTerrainAt(Position pos) {
        return this.terrain.get(pos.getLinearMatrixPosition());
    }

    public boolean hasEntity(Position pos) {
        for (Enemy enemy : this.enemies) {
            if (enemy.getPosition().same(pos)) {
                return true;
            }
        }
        for (Ally ally : this.allies) {
            if (ally.getPosition().same(pos)) {
                return true;
            }
        }
        for (City city : this.cities) {
            if (city.getPosition().same(pos)) {
                return true;
            }
        }
        return false;
    }

    public Entity getEntityAt(Position pos) {
        for (Enemy enemy : this.enemies) {
            if (enemy.getPosition().same(pos)) {
                return enemy;
            }
        }
        for (Ally ally : this.allies) {
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

    public void addTerrain(TerrainTile terrainTile, Position pos) {
        if (!this.hasTerrain(pos)) {
            this.terrain.set(pos.getLinearMatrixPosition(), terrainTile);
        }
        else {
            // exception
        }
    }

    public void addEnemy(Enemy enemy) {
        if (!this.hasEntity(enemy.getPosition())) {
            this.enemies.add(enemy);
        }
        else {
            // exception
        }
    }

    public void addAlly(Ally ally) {
        if (!this.hasEntity(ally.getPosition())) {
            this.allies.add(ally);
        }
        else {
            // exception
        }
    }

    public void addCity(City city) {
        if (!this.hasEntity(city.getPosition())) {
            this.cities.add(city);
        }
        else {
            // exception
        }
    }

    public void inflictDamage(Position pos, int damage) {
        if (this.hasEntity(pos)) {
            Entity entity = this.getEntityAt(pos);
            entity.takeDamage(damage);
            if (entity.isDead()) {
                //this.entities.remove(entity);
            }
        }
    }
}
