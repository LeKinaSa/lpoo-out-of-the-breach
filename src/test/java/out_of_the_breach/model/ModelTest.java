package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static out_of_the_breach.model.TerrainTile.*;

public class ModelTest {
    @Test
    public void tileIntransitableTest() {
        GameModel grid = new GameModel();

        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Mockito.when(p1.getLinearMatrixPosition()).thenReturn(5);
        Mockito.when(p2.getLinearMatrixPosition()).thenReturn(21);

        List<TerrainTile> tiles = new ArrayList<>(Collections.nCopies(64, PLAIN));
        grid.setTiles(tiles);

        assertEquals(false, grid.tileIntransitable(p1));
        assertEquals(false, grid.tileIntransitable(p2));

        tiles = new ArrayList<>(Collections.nCopies(64, MOUNTAIN));
        grid.setTiles(tiles);
        assertEquals( true, grid.tileIntransitable(p1));
        assertEquals( true, grid.tileIntransitable(p2));

        tiles.set(5, PLAIN);
        grid.setTiles(tiles);
        assertEquals(false, grid.tileIntransitable(p1));
        assertEquals( true, grid.tileIntransitable(p2));
    }

    @Test
    public void tileOccupiedAndGetEntityAtTest() {
        GameModel grid = new GameModel();

        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);
        Position p5 = Mockito.mock(Position.class);
        Position p6 = Mockito.mock(Position.class);

        Hero  ally1  = Mockito.mock( Hero.class); Mockito.when( ally1.getPosition()).thenReturn(p1);
        Hero  ally2  = Mockito.mock( Hero.class); Mockito.when( ally2.getPosition()).thenReturn(p2);
        City  city1  = Mockito.mock( City.class); Mockito.when( city1.getPosition()).thenReturn(p3);
        City  city2  = Mockito.mock( City.class); Mockito.when( city2.getPosition()).thenReturn(p4);
        Enemy enemy1 = Mockito.mock(Enemy.class); Mockito.when(enemy1.getPosition()).thenReturn(p5);
        Enemy enemy2 = Mockito.mock(Enemy.class); Mockito.when(enemy2.getPosition()).thenReturn(p6);

        List< Hero> allies  = new ArrayList<>(); allies.add ( ally1); allies.add ( ally2);
        List< City> cities  = new ArrayList<>(); cities.add ( city1); cities.add ( city2);
        List<Enemy> enemies = new ArrayList<>(); enemies.add(enemy1); enemies.add(enemy2);

        grid.setEnemies(enemies);
        grid.setAllies ( allies);
        grid.setCities ( cities);

        Position p_1 = Mockito.mock(Position.class);
        Mockito.when(p_1.same(p1)).thenReturn( true);
        Mockito.when(p_1.same(p2)).thenReturn(false);
        Mockito.when(p_1.same(p3)).thenReturn(false);
        Mockito.when(p_1.same(p4)).thenReturn(false);
        Mockito.when(p_1.same(p5)).thenReturn(false);
        Mockito.when(p_1.same(p6)).thenReturn(false);

        Position p_3 = Mockito.mock(Position.class);
        Mockito.when(p_3.same(p1)).thenReturn(false);
        Mockito.when(p_3.same(p2)).thenReturn(false);
        Mockito.when(p_3.same(p3)).thenReturn( true);
        Mockito.when(p_3.same(p4)).thenReturn(false);
        Mockito.when(p_3.same(p5)).thenReturn(false);
        Mockito.when(p_3.same(p6)).thenReturn(false);

        Position p_5 = Mockito.mock(Position.class);
        Mockito.when(p_5.same(p1)).thenReturn(false);
        Mockito.when(p_5.same(p2)).thenReturn(false);
        Mockito.when(p_5.same(p3)).thenReturn(false);
        Mockito.when(p_5.same(p4)).thenReturn(false);
        Mockito.when(p_5.same(p5)).thenReturn( true);
        Mockito.when(p_5.same(p6)).thenReturn(false);

        Position p = Mockito.mock(Position.class);
        Mockito.when(p.same(p1)).thenReturn(false);
        Mockito.when(p.same(p2)).thenReturn(false);
        Mockito.when(p.same(p3)).thenReturn(false);
        Mockito.when(p.same(p4)).thenReturn(false);
        Mockito.when(p.same(p5)).thenReturn(false);
        Mockito.when(p.same(p6)).thenReturn(false);

        assertEquals(        ally1, grid.getEntityAt(p_1));
        assertEquals(        city1, grid.getEntityAt(p_3));
        assertEquals(       enemy1, grid.getEntityAt(p_5));
        assertEquals(null, grid.getEntityAt(  p));

        assertEquals( true, grid.tileOccupied(p_1));
        assertEquals( true, grid.tileOccupied(p_3));
        assertEquals( true, grid.tileOccupied(p_5));
        assertEquals(false, grid.tileOccupied(  p));
    }

    @Test
    public void energyTest() {
        City  city1  = Mockito.mock( City.class); Mockito.when(city1.getHp()).thenReturn(3);
        City  city2  = Mockito.mock( City.class); Mockito.when(city2.getHp()).thenReturn(5);

        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        GameModel grid = new GameModel();

        assertEquals(0, grid.getEnergy());

        grid.setCities(cities);

        assertEquals( 8, grid.getCurrentEnergy());
        assertEquals(10, grid.getEnergy());

        Mockito.when(city1.getHp()).thenReturn(2);
        Mockito.when(city2.getHp()).thenReturn(2);

        assertEquals(4, grid.getCurrentEnergy());
        assertEquals(5, grid.getEnergy());
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
            @Override
            public String getName() {
                return null;
            }
            @Override
            public String getInitials() {
                return null;
            }
        }

        class EnemyStub extends Enemy {
            public EnemyStub(int hp) {
                super(null, hp, null);
            }
            @Override
            public void moveAndPlanAttack(GameModel grid) {
                return;
            }
            @Override
            public String getName() {
                return null;
            }
            @Override
            public String getInitials() {
                return null;
            }
        }

        Enemy enemy1 = new EnemyStub(0);
        Enemy enemy2 = new EnemyStub(1);
        Hero  hero   = new  HeroStub();
        City  city   = new  City(null, 0);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        List<Hero > allies  = new ArrayList<>();
        allies.add (hero );
        List<City > cities  = new ArrayList<>();
        cities.add (city );

        GameModel grid = new GameModel();
        grid.setEnemies(enemies); grid.setAllies(allies); grid.setCities(cities);

        assertEquals(2, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(1,  grid.getCities().size());

        grid.removeEntity(enemy2);

        assertEquals(2, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(1,  grid.getCities().size());

        grid.removeEntity(city);

        assertEquals(2, grid.getEnemies().size());
        assertEquals(1,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());

        grid.removeEntity(hero);

        assertEquals(2, grid.getEnemies().size());
        assertEquals(0,  grid.getAllies().size());
        assertEquals(0,  grid.getCities().size());

        grid.removeEntity(enemy1);

        assertEquals(1, grid.getEnemies().size());
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
            @Override
            public String getName() {
                return null;
            }
            @Override
            public String getInitials() {
                return null;
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
            @Override
            public String getName() {
                return null;
            }
            @Override
            public String getInitials() {
                return null;
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
        City  city  = new City     (p3, 10);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        List<Hero > allies  = new ArrayList<>();
        allies.add (hero );
        List<City > cities  = new ArrayList<>();
        cities.add (city );

        GameModel grid = new GameModel();
        grid.setEnemies(enemies); grid.setAllies(allies); grid.setCities(cities);

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
    public void resetHerosTest() {
        Hero hero1 = Mockito.mock(Hero.class);
        Hero hero2 = Mockito.mock(Hero.class);
        Hero hero3 = Mockito.mock(Hero.class);
        List<Hero> allies = new ArrayList<>();
        allies.add(hero1);
        allies.add(hero2);
        allies.add(hero3);

        GameModel grid = new GameModel();
        grid.setAllies(allies);

        verify(hero1, times(0)).reset();
        verify(hero2, times(0)).reset();
        verify(hero3, times(0)).reset();

        grid.resetHeroes();

        verify(hero1, times(1)).reset();
        verify(hero2, times(1)).reset();
        verify(hero3, times(1)).reset();
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

        GameModel grid = new GameModel();
        grid.setEnemies(enemies);

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

        GameModel grid = new GameModel();
        assertEquals(4, grid.getTurns());

        grid.setEnemies(enemies);
        grid.setTurns(3);

        verify(enemy1, times(0)).attack(grid);
        verify(enemy2, times(0)).attack(grid);
        verify(enemy3, times(0)).attack(grid);
        assertEquals(3, grid.getTurns());

        grid.executeAttack();

        verify(enemy1, times(1)).attack(grid);
        verify(enemy2, times(1)).attack(grid);
        verify(enemy3, times(1)).attack(grid);
        assertEquals(2, grid.getTurns());
    }
}
