package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ModelTest {
    @Test
    public void emptyTest() {
        GameModel grid = new GameModel();
        assertEquals(0,   grid.getTiles().size());
        assertEquals(0, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());
    }

    @Test
    public void tilesTest() {
        GameModel grid = new GameModel(new ArrayList<>());

        assertEquals(0,   grid.getTiles().size());
        assertEquals(0, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());

        List<TerrainTile> terrain = new ArrayList<>();
        for (int index = 0; index <= 63; index ++) {
            terrain.add(TerrainTile.PLAIN);
        }

        grid.setTiles(terrain);
        assertEquals(terrain, grid.getTiles());
        assertEquals(64, grid.getTiles().size());

        GameModel grid2 = new GameModel(terrain);
        assertEquals(terrain, grid2.getTiles());
        assertEquals(64, grid.getTiles().size());
    }

    @Test
    public void entitiesTest() {
        Hero  ally1  = Mockito.mock( Hero.class);
        Hero  ally2  = Mockito.mock( Hero.class);
        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        Enemy enemy3 = Mockito.mock(Enemy.class);
        City  city1  = Mockito.mock( City.class);

        List<TerrainTile> terrain = new ArrayList<>();
        for (int index = 0; index <= 63; index ++) {
            terrain.add(TerrainTile.PLAIN);
        }
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        List<Hero > allies  = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City > cities  = new ArrayList<>();
        cities.add(city1);

        GameModel grid = new GameModel(terrain, enemies, allies, cities);
        assertEquals(terrain,   grid.getTiles());
        assertEquals(enemies, grid.getEnemies());
        assertEquals( allies,  grid.getAllies());
        assertEquals( cities,  grid.getCities());

        grid.setEnemies(new ArrayList<>());
        grid.setAllies( new ArrayList<>());
        grid.setCities( new ArrayList<>());
        assertEquals(0, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());
    }

    @Test
    public void addingFailTest() {
        Hero  ally1  = Mockito.mock( Hero.class);
        Hero  ally2  = Mockito.mock( Hero.class);
        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        City  city1  = Mockito.mock( City.class);
        City  city2  = Mockito.mock( City.class);

        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);
        Position p5 = Mockito.mock(Position.class);
        Position p6 = Mockito.mock(Position.class);

        Mockito.when(enemy1.getPosition()).thenReturn(p1);
        Mockito.when( ally1.getPosition()).thenReturn(p2);
        Mockito.when( city1.getPosition()).thenReturn(p3);
        Mockito.when(enemy2.getPosition()).thenReturn(p4);
        Mockito.when( ally2.getPosition()).thenReturn(p5);
        Mockito.when( city2.getPosition()).thenReturn(p6);

        Mockito.when(p1.same(p4)).thenReturn(false);
        Mockito.when(p1.same(p5)).thenReturn(true);
        Mockito.when(p1.same(p6)).thenReturn(false);

        Mockito.when(p2.same(p4)).thenReturn(false);
        Mockito.when(p2.same(p5)).thenReturn(false);
        Mockito.when(p2.same(p6)).thenReturn(true);

        Mockito.when(p3.same(p4)).thenReturn(true);
        Mockito.when(p3.same(p5)).thenReturn(false);
        Mockito.when(p3.same(p6)).thenReturn(false);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        List<Hero > allies  = new ArrayList<>();
        allies.add (ally1 );
        List<City > cities  = new ArrayList<>();
        cities.add (city1 );

        GameModel grid = new GameModel(new ArrayList<>(), enemies, allies, cities);

        boolean exception = false;
        try {
            grid.addEnemy(enemy2);
        }
        catch (OccupiedTile o) {
            exception = true;
        }
        assertEquals(true, exception);

        exception = false;
        try {
            grid.addAlly(ally2);
        }
        catch (OccupiedTile o) {
            exception = true;
        }
        assertEquals(true, exception);

        exception = false;
        try {
            grid.addCity(city2);
        }
        catch (OccupiedTile o) {
            exception = true;
        }
        assertEquals(true, exception);
    }

    @Test
    public void addingSuccessTest() {
        Enemy enemy = Mockito.mock(Enemy.class);
        Hero  hero  = Mockito.mock( Hero.class);
        City  city  = Mockito.mock( City.class);

        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);

        Mockito.when(enemy.getPosition()).thenReturn(p1);
        Mockito.when( hero.getPosition()).thenReturn(p2);
        Mockito.when( city.getPosition()).thenReturn(p3);

        Mockito.when(p1.same(p1)).thenReturn( true);
        Mockito.when(p1.same(p2)).thenReturn(false);
        Mockito.when(p1.same(p3)).thenReturn(false);

        Mockito.when(p2.same(p1)).thenReturn(false);
        Mockito.when(p2.same(p2)).thenReturn( true);
        Mockito.when(p2.same(p3)).thenReturn(false);

        Mockito.when(p3.same(p1)).thenReturn(false);
        Mockito.when(p3.same(p2)).thenReturn(false);
        Mockito.when(p3.same(p3)).thenReturn( true);

        GameModel grid = new GameModel();

        assertEquals(null, grid.getEntityAt(p1));
        assertEquals(false, grid.tileOccupied(p1));

        try {
            grid.addEnemy(enemy);
        }
        catch (OccupiedTile o) {
            assertEquals(false, true);
        }

        try {
            grid.addAlly(hero);
        }
        catch (OccupiedTile o) {
            assertEquals(false, true);
        }

        try {
            grid.addCity(city);
        }
        catch (OccupiedTile o) {
            assertEquals(false, true);
        }

        assertEquals(enemy, grid.getEntityAt(p1));
        assertEquals(true, grid.tileOccupied(p1));

        assertEquals( hero, grid.getEntityAt(p2));
        assertEquals(true, grid.tileOccupied(p2));

        assertEquals( city, grid.getEntityAt(p3));
        assertEquals(true, grid.tileOccupied(p3));

        assertEquals(1, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(1,  grid.getCities().size());
    }

    @Test
    public void removingTest() {
        class HeroStub extends Hero {
            public HeroStub() {
                super(null, 0, 0);
            }
            @Override
            public boolean withinRange(Position pos) {
                return true;
            }
        }

        class EnemyStub extends Enemy {
            public EnemyStub() {
                super(null, 0, null);
            }
            @Override
            public void moveAndPlanAttack(GameModel grid) {
                return;
            }
        }

        Enemy enemy = new EnemyStub();
        Hero  hero  = new  HeroStub();
        City  city  = new  City(null, 0, 0);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        List<Hero > allies  = new ArrayList<>();
        allies.add (hero );
        List<City > cities  = new ArrayList<>();
        cities.add (city );

        GameModel grid = new GameModel(new ArrayList<>(), enemies, allies, cities);

        assertEquals(1, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(1,  grid.getCities().size());

        grid.removeEntity(city);

        assertEquals(1, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());

        grid.removeEntity(hero);

        assertEquals(1, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());

        grid.removeEntity(enemy);

        assertEquals(0, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());
    }

    @Test
    public void inflictDamageTest() {
        class HeroStub extends Hero {
            public HeroStub(Position pos, int hp, int movementRange) {
                super(pos, hp, movementRange);
            }
            @Override
            public boolean withinRange(Position pos) {
                return true;
            }
        }

        class EnemyStub extends Enemy {
            public EnemyStub(Position pos, int hp, AttackStrategy strategy) {
                super(pos, hp, strategy);
            }
            @Override
            public void moveAndPlanAttack(GameModel grid) {
                return;
            }
        }

        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);

        Mockito.when(p1.same(p1)).thenReturn( true);
        Mockito.when(p1.same(p2)).thenReturn(false);
        Mockito.when(p1.same(p3)).thenReturn(false);

        Mockito.when(p2.same(p1)).thenReturn(false);
        Mockito.when(p2.same(p2)).thenReturn( true);
        Mockito.when(p2.same(p3)).thenReturn(false);

        Mockito.when(p3.same(p1)).thenReturn(false);
        Mockito.when(p3.same(p2)).thenReturn(false);
        Mockito.when(p3.same(p3)).thenReturn( true);

        Enemy enemy = new EnemyStub(p1, 12, null);
        Hero  hero  = new HeroStub (p2, 11, 2);
        City  city  = new City     (p3, 10, 2);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        List<Hero > allies  = new ArrayList<>();
        allies.add (hero );
        List<City > cities  = new ArrayList<>();
        cities.add (city );

        GameModel grid = new GameModel(null, enemies, allies, cities);

        assertEquals(1, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(1,  grid.getCities().size());

        grid.inflictDamage(p1, 5);
        grid.inflictDamage(p2, 5);
        grid.inflictDamage(p3, 5);

        assertEquals(1, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(1,  grid.getCities().size());

        assertEquals(7, grid.getEnemies().get(0).getHp());
        assertEquals(6,  grid.getAllies().get(0).getHp());
        assertEquals(5,  grid.getCities().get(0).getHp());

        grid.inflictDamage(p1, 10);
        grid.inflictDamage(p2, 10);
        grid.inflictDamage(p3, 10);

        assertEquals(0, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());
    }

    @Test
    public void planAttackTest() {
        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        Enemy enemy3 = Mockito.mock(Enemy.class);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        GameModel grid = new GameModel(new ArrayList<>(), enemies, new ArrayList<>(), new ArrayList<>());

        verify(enemy1, times(0)).moveAndPlanAttack(grid);
        verify(enemy2, times(0)).moveAndPlanAttack(grid);
        verify(enemy3, times(0)).moveAndPlanAttack(grid);

        grid.planAttack();

        verify(enemy1, times(1)).moveAndPlanAttack(grid);
        verify(enemy2, times(1)).moveAndPlanAttack(grid);
        verify(enemy3, times(1)).moveAndPlanAttack(grid);
    }

    @Test
    public void executeAttackTest() {
        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        Enemy enemy3 = Mockito.mock(Enemy.class);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        GameModel grid = new GameModel(new ArrayList<>(), enemies, new ArrayList<>(), new ArrayList<>());

        verify(enemy1, times(0)).attack(grid);
        verify(enemy2, times(0)).attack(grid);
        verify(enemy3, times(0)).attack(grid);

        grid.executeAttack();

        verify(enemy1, times(1)).attack(grid);
        verify(enemy2, times(1)).attack(grid);
        verify(enemy3, times(1)).attack(grid);
    }
}
