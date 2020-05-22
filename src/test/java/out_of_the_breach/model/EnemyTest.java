package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static out_of_the_breach.model.AttackDirection.*;

public class EnemyTest {
    @Test
    public void strategyTest() {
        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        Mockito.when(strategy.getDirection()).thenReturn(NORTH);
        Enemy enemy = new Bug(null, 10, 3);

        assertEquals(LineAttack.class, enemy.getAttackStrategy().getClass());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());

        enemy.setAttackStrategy(strategy);
        assertEquals(strategy, enemy.getAttackStrategy());
        assertEquals(NORTH, enemy.getAttackDirection());
    }

    @Test
    public void attackTest() {
        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        DamageMatrix dmgMatrix = Mockito.mock(DamageMatrix.class);
        Position p1 = Mockito.mock(Position.class);
        Mockito.when(strategy.previewAttack(p1)).thenReturn(dmgMatrix);
        Mockito.when(strategy.previewAttack(p1)).thenReturn(dmgMatrix);

        Enemy enemy = new Bug(p1, 1, 2);
        enemy.setAttackStrategy(strategy);
        assertEquals(dmgMatrix, enemy.previewAttack());

        Model grid = Mockito.mock(Model.class);
        enemy.attack(grid);
        verify(strategy, times(1)).attack(grid, p1);
    }

    // ----- BUG -----//
    @Test
    public void moveAndPlanAttack_Bug_CityIsWeaker_SOUTH_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Mockito.when(ally1.getHp()).thenReturn(5);
        Mockito.when(ally2.getHp()).thenReturn(6);
        Mockito.when(city1.getHp()).thenReturn(6);
        Mockito.when(city2.getHp()).thenReturn(4); // Weaker Entity

        Position start = Mockito.mock(Position.class);
        Position p     = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(city2.getPosition()).thenReturn(p);
        Mockito.when(p.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p.adjacentPos( EAST)).thenReturn( null);
        Mockito.when(p.adjacentPos( WEST)).thenReturn( null);

        Mockito.when(start.distanceTo(north)).thenReturn(2);
        Mockito.when(start.distanceTo(south)).thenReturn(4); // Closer Available Position
        Mockito.when(grid.tileOccupied(north)).thenReturn(true);
        Mockito.when(grid.tileOccupied(south)).thenReturn(false);

        Enemy enemy = new Bug(start, 2, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NORTH, enemy.getAttackDirection());
        assertEquals(south, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Bug_CityIsWeaker_EAST_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Mockito.when(ally1.getHp()).thenReturn(5);
        Mockito.when(ally2.getHp()).thenReturn(7);
        Mockito.when(city1.getHp()).thenReturn(3); // Weaker Entity
        Mockito.when(city2.getHp()).thenReturn(4);

        Position start = Mockito.mock(Position.class);
        Position p     = Mockito.mock(Position.class);
        Position east  = Mockito.mock(Position.class);
        Position west  = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(city1.getPosition()).thenReturn(p);
        Mockito.when(p.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(null);
        Mockito.when(p.adjacentPos( EAST)).thenReturn(east);
        Mockito.when(p.adjacentPos( WEST)).thenReturn(west);

        Mockito.when(start.distanceTo(east)).thenReturn(2); // Closer Available Position
        Mockito.when(start.distanceTo(west)).thenReturn(2);
        Mockito.when(grid.tileOccupied(east)).thenReturn(false);
        Mockito.when(grid.tileOccupied(west)).thenReturn(false);

        Enemy enemy = new Bug(start, 2, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(WEST, enemy.getAttackDirection());
        assertEquals(east, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Bug_AllyIsWeaker_WEST_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Mockito.when(ally1.getHp()).thenReturn(5);
        Mockito.when(ally2.getHp()).thenReturn(2); // Weaker Entity
        Mockito.when(city1.getHp()).thenReturn(3);
        Mockito.when(city2.getHp()).thenReturn(4);

        Position start = Mockito.mock(Position.class);
        Position p     = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position east  = Mockito.mock(Position.class);
        Position west  = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(ally2.getPosition()).thenReturn(p);
        Mockito.when(p.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p.adjacentPos( EAST)).thenReturn( east);
        Mockito.when(p.adjacentPos( WEST)).thenReturn( west);

        Mockito.when(start.distanceTo(north)).thenReturn(4);
        Mockito.when(start.distanceTo(south)).thenReturn(4);
        Mockito.when(start.distanceTo( east)).thenReturn(3);
        Mockito.when(start.distanceTo( west)).thenReturn(3); // Closer Available Position
        Mockito.when(grid.tileOccupied(north)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east)).thenReturn( true);
        Mockito.when(grid.tileOccupied( west)).thenReturn(false);

        Enemy enemy = new Bug(start, 2, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(EAST, enemy.getAttackDirection());
        assertEquals(west, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Bug_AllyIsWeaker_NORTH_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Mockito.when(ally1.getHp()).thenReturn(5);
        Mockito.when(ally2.getHp()).thenReturn(2); // Weaker Entity
        Mockito.when(city1.getHp()).thenReturn(3);
        Mockito.when(city2.getHp()).thenReturn(4);

        Position start = Mockito.mock(Position.class);
        Position p     = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(ally2.getPosition()).thenReturn(p);
        Mockito.when(p.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p.adjacentPos( EAST)).thenReturn( null);
        Mockito.when(p.adjacentPos( WEST)).thenReturn( null);

        Mockito.when(start.distanceTo(north)).thenReturn(2); // Closer Available Position
        Mockito.when(start.distanceTo(south)).thenReturn(4);
        Mockito.when(grid.tileOccupied(north)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south)).thenReturn(false);

        Enemy enemy = new Bug(start, 2, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(SOUTH, enemy.getAttackDirection());
        assertEquals(north, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Bug_AllyAndCityAreAtSameHp_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Mockito.when(ally1.getHp()).thenReturn(5);
        Mockito.when(ally2.getHp()).thenReturn(2); // Weaker Entity
        Mockito.when(city1.getHp()).thenReturn(3);
        Mockito.when(city2.getHp()).thenReturn(2); // Weaker Entity

        Position start = Mockito.mock(Position.class);
        Position p     = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(ally2.getPosition()).thenReturn(p);
        Mockito.when(p.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p.adjacentPos( EAST)).thenReturn( null);
        Mockito.when(p.adjacentPos( WEST)).thenReturn( null);

        Mockito.when(start.distanceTo(north)).thenReturn(2); // Closer Available Position
        Mockito.when(start.distanceTo(south)).thenReturn(4);
        Mockito.when(grid.tileOccupied(north)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south)).thenReturn(false);

        Enemy enemy = new Bug(start, 2, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(north, enemy.getPosition());
        assertEquals(SOUTH, enemy.getAttackDirection());
        // Prioritizes the Hero over the Cities
        verify(city2, times(0)).getPosition();
        verify(city1, times(0)).getPosition();
    }

    @Test
    public void moveAndPlanAttack_Bug_EnemyCantMoveOrAttack_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Mockito.when(ally1.getHp()).thenReturn(5);
        Mockito.when(ally2.getHp()).thenReturn(2); // Weaker Entity
        Mockito.when(city1.getHp()).thenReturn(3);
        Mockito.when(city2.getHp()).thenReturn(4);

        Position start = Mockito.mock(Position.class);
        Position p     = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(ally2.getPosition()).thenReturn(p);
        Mockito.when(p.adjacentPos(NORTH)).thenReturn(north);
        Mockito.when(p.adjacentPos(SOUTH)).thenReturn(south);
        Mockito.when(p.adjacentPos( EAST)).thenReturn( null);
        Mockito.when(p.adjacentPos( WEST)).thenReturn( null);

        Mockito.when(start.distanceTo(north)).thenReturn(2);
        Mockito.when(start.distanceTo(south)).thenReturn(4);
        // Tiles are all Unavailable
        Mockito.when(grid.tileOccupied(north)).thenReturn(true);
        Mockito.when(grid.tileOccupied(south)).thenReturn(true);

        Enemy enemy = new Bug(start, 2, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(start, enemy.getPosition());
        assertEquals(NONE, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttack_Bug_NoAllyOrCityFound_Test() {
        Position initialPosition = Mockito.mock(Position.class);

        Model modelGrid = Mockito.mock(Model.class);
        Mockito.when(modelGrid.getCities()).thenReturn(new ArrayList<>());
        Mockito.when(modelGrid.getAllies()).thenReturn(new ArrayList<>());

        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(initialPosition, enemy.getPosition());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());
    }

    // ----- LIZARD -----//
    @Test
    public void moveAndPlanAttack_Lizard_CityIsCloser_SOUTH_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);

        Position north1 = Mockito.mock(Position.class);
        Position south1 = Mockito.mock(Position.class);
        Position  east1 = Mockito.mock(Position.class);
        Position  west1 = Mockito.mock(Position.class);

        Position north2 = Mockito.mock(Position.class);
        Position south2 = Mockito.mock(Position.class);
        Position  east2 = Mockito.mock(Position.class);
        Position  west2 = Mockito.mock(Position.class);

        Position north3 = Mockito.mock(Position.class);
        Position south3 = Mockito.mock(Position.class);
        Position  east3 = Mockito.mock(Position.class);
        Position  west3 = Mockito.mock(Position.class);

        Position north4 = Mockito.mock(Position.class);
        Position south4 = Mockito.mock(Position.class);
        Position  east4 = Mockito.mock(Position.class);
        Position  west4 = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north1);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south1);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east1);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west1);

        Mockito.when(p2.adjacentPos(NORTH)).thenReturn(north2);
        Mockito.when(p2.adjacentPos(SOUTH)).thenReturn(south2);
        Mockito.when(p2.adjacentPos( EAST)).thenReturn( east2);
        Mockito.when(p2.adjacentPos( WEST)).thenReturn( west2);

        Mockito.when(p3.adjacentPos(NORTH)).thenReturn(north3);
        Mockito.when(p3.adjacentPos(SOUTH)).thenReturn(south3);
        Mockito.when(p3.adjacentPos( EAST)).thenReturn( east3);
        Mockito.when(p3.adjacentPos( WEST)).thenReturn( west3);

        Mockito.when(p4.adjacentPos(NORTH)).thenReturn(north4);
        Mockito.when(p4.adjacentPos(SOUTH)).thenReturn(south4);
        Mockito.when(p4.adjacentPos( EAST)).thenReturn( east4);
        Mockito.when(p4.adjacentPos( WEST)).thenReturn( west4);

        Mockito.when(p.distanceTo(north1)).thenReturn(8);
        Mockito.when(p.distanceTo(south1)).thenReturn(8);
        Mockito.when(p.distanceTo( east1)).thenReturn(7);
        Mockito.when(p.distanceTo( west1)).thenReturn(7);

        Mockito.when(p.distanceTo(north2)).thenReturn(6);
        Mockito.when(p.distanceTo(south2)).thenReturn(7);
        Mockito.when(p.distanceTo( east2)).thenReturn(7);
        Mockito.when(p.distanceTo( west2)).thenReturn(6);

        Mockito.when(p.distanceTo(north3)).thenReturn(5);
        Mockito.when(p.distanceTo(south3)).thenReturn(4);
        Mockito.when(p.distanceTo( east3)).thenReturn(4);
        Mockito.when(p.distanceTo( west3)).thenReturn(4);

        Mockito.when(p.distanceTo(north4)).thenReturn(2);
        Mockito.when(p.distanceTo(south4)).thenReturn(1); //Closer Distance
        Mockito.when(p.distanceTo( east4)).thenReturn(2);
        Mockito.when(p.distanceTo( west4)).thenReturn(2);

        Mockito.when(grid.tileOccupied(north1)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west1)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north2)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west2)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north3)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west3)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north4)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west4)).thenReturn(false);

        Enemy enemy = new Lizard(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(south4, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Lizard_CityIsCloser_EAST_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);

        Position north1 = Mockito.mock(Position.class);
        Position south1 = Mockito.mock(Position.class);
        Position  east1 = Mockito.mock(Position.class);
        Position  west1 = Mockito.mock(Position.class);

        Position north2 = Mockito.mock(Position.class);
        Position south2 = Mockito.mock(Position.class);
        Position  east2 = Mockito.mock(Position.class);
        Position  west2 = Mockito.mock(Position.class);

        Position north3 = Mockito.mock(Position.class);
        Position south3 = Mockito.mock(Position.class);
        Position  east3 = Mockito.mock(Position.class);
        Position  west3 = Mockito.mock(Position.class);

        Position north4 = Mockito.mock(Position.class);
        Position south4 = Mockito.mock(Position.class);
        Position  east4 = Mockito.mock(Position.class);
        Position  west4 = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north1);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south1);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east1);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west1);

        Mockito.when(p2.adjacentPos(NORTH)).thenReturn(north2);
        Mockito.when(p2.adjacentPos(SOUTH)).thenReturn(south2);
        Mockito.when(p2.adjacentPos( EAST)).thenReturn( east2);
        Mockito.when(p2.adjacentPos( WEST)).thenReturn( west2);

        Mockito.when(p3.adjacentPos(NORTH)).thenReturn(north3);
        Mockito.when(p3.adjacentPos(SOUTH)).thenReturn(south3);
        Mockito.when(p3.adjacentPos( EAST)).thenReturn( east3);
        Mockito.when(p3.adjacentPos( WEST)).thenReturn( west3);

        Mockito.when(p4.adjacentPos(NORTH)).thenReturn(north4);
        Mockito.when(p4.adjacentPos(SOUTH)).thenReturn(south4);
        Mockito.when(p4.adjacentPos( EAST)).thenReturn( east4);
        Mockito.when(p4.adjacentPos( WEST)).thenReturn( west4);

        Mockito.when(p.distanceTo(north1)).thenReturn(8);
        Mockito.when(p.distanceTo(south1)).thenReturn(8);
        Mockito.when(p.distanceTo( east1)).thenReturn(7);
        Mockito.when(p.distanceTo( west1)).thenReturn(7);

        Mockito.when(p.distanceTo(north2)).thenReturn(6);
        Mockito.when(p.distanceTo(south2)).thenReturn(7);
        Mockito.when(p.distanceTo( east2)).thenReturn(7);
        Mockito.when(p.distanceTo( west2)).thenReturn(6);

        Mockito.when(p.distanceTo(north3)).thenReturn(5);
        Mockito.when(p.distanceTo(south3)).thenReturn(4);
        Mockito.when(p.distanceTo( east3)).thenReturn(4); //Closer Distance
        Mockito.when(p.distanceTo( west3)).thenReturn(4);

        Mockito.when(p.distanceTo(north4)).thenReturn(9);
        Mockito.when(p.distanceTo(south4)).thenReturn(9);
        Mockito.when(p.distanceTo( east4)).thenReturn(9);
        Mockito.when(p.distanceTo( west4)).thenReturn(8);

        Mockito.when(grid.tileOccupied(north1)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west1)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north2)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west2)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north3)).thenReturn( true);
        Mockito.when(grid.tileOccupied(south3)).thenReturn( true);
        Mockito.when(grid.tileOccupied( east3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west3)).thenReturn( true);

        Mockito.when(grid.tileOccupied(north4)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west4)).thenReturn(false);

        Enemy enemy = new Lizard(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(east3, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Lizard_AllyIsCloser_WEST_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);

        Position north1 = Mockito.mock(Position.class);
        Position south1 = Mockito.mock(Position.class);
        Position  east1 = Mockito.mock(Position.class);
        Position  west1 = Mockito.mock(Position.class);

        Position north2 = Mockito.mock(Position.class);
        Position south2 = Mockito.mock(Position.class);
        Position  east2 = Mockito.mock(Position.class);
        Position  west2 = Mockito.mock(Position.class);

        Position north3 = Mockito.mock(Position.class);
        Position south3 = Mockito.mock(Position.class);
        Position  east3 = Mockito.mock(Position.class);
        Position  west3 = Mockito.mock(Position.class);

        Position north4 = Mockito.mock(Position.class);
        Position south4 = Mockito.mock(Position.class);
        Position  east4 = Mockito.mock(Position.class);
        Position  west4 = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north1);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south1);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east1);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west1);

        Mockito.when(p2.adjacentPos(NORTH)).thenReturn(north2);
        Mockito.when(p2.adjacentPos(SOUTH)).thenReturn(south2);
        Mockito.when(p2.adjacentPos( EAST)).thenReturn( east2);
        Mockito.when(p2.adjacentPos( WEST)).thenReturn( west2);

        Mockito.when(p3.adjacentPos(NORTH)).thenReturn(north3);
        Mockito.when(p3.adjacentPos(SOUTH)).thenReturn(south3);
        Mockito.when(p3.adjacentPos( EAST)).thenReturn( east3);
        Mockito.when(p3.adjacentPos( WEST)).thenReturn( west3);

        Mockito.when(p4.adjacentPos(NORTH)).thenReturn(north4);
        Mockito.when(p4.adjacentPos(SOUTH)).thenReturn(south4);
        Mockito.when(p4.adjacentPos( EAST)).thenReturn( east4);
        Mockito.when(p4.adjacentPos( WEST)).thenReturn( west4);

        Mockito.when(p.distanceTo(north1)).thenReturn(4);
        Mockito.when(p.distanceTo(south1)).thenReturn(3);
        Mockito.when(p.distanceTo( east1)).thenReturn(4);
        Mockito.when(p.distanceTo( west1)).thenReturn(3); // Closer Available Distance

        Mockito.when(p.distanceTo(north2)).thenReturn(2);
        Mockito.when(p.distanceTo(south2)).thenReturn(2);
        Mockito.when(p.distanceTo( east2)).thenReturn(3);
        Mockito.when(p.distanceTo( west2)).thenReturn(3);

        Mockito.when(p.distanceTo(north3)).thenReturn(5);
        Mockito.when(p.distanceTo(south3)).thenReturn(4);
        Mockito.when(p.distanceTo( east3)).thenReturn(4);
        Mockito.when(p.distanceTo( west3)).thenReturn(4);

        Mockito.when(p.distanceTo(north4)).thenReturn(5);
        Mockito.when(p.distanceTo(south4)).thenReturn(5);
        Mockito.when(p.distanceTo( east4)).thenReturn(4);
        Mockito.when(p.distanceTo( west4)).thenReturn(4);

        Mockito.when(grid.tileOccupied(north1)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south1)).thenReturn( true);
        Mockito.when(grid.tileOccupied( east1)).thenReturn( true);
        Mockito.when(grid.tileOccupied( west1)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north2)).thenReturn( true);
        Mockito.when(grid.tileOccupied(south2)).thenReturn( true);
        Mockito.when(grid.tileOccupied( east2)).thenReturn( true);
        Mockito.when(grid.tileOccupied( west2)).thenReturn( true);

        Mockito.when(grid.tileOccupied(north3)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west3)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north4)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west4)).thenReturn(false);

        Enemy enemy = new Lizard(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(west1, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Lizard_AllyIsCloser_NORTH_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);

        Position north1 = Mockito.mock(Position.class);
        Position south1 = Mockito.mock(Position.class);
        Position  east1 = Mockito.mock(Position.class);
        Position  west1 = Mockito.mock(Position.class);

        Position north2 = Mockito.mock(Position.class);
        Position south2 = Mockito.mock(Position.class);
        Position  east2 = Mockito.mock(Position.class);
        Position  west2 = Mockito.mock(Position.class);

        Position north3 = Mockito.mock(Position.class);
        Position south3 = Mockito.mock(Position.class);
        Position  east3 = Mockito.mock(Position.class);
        Position  west3 = Mockito.mock(Position.class);

        Position north4 = Mockito.mock(Position.class);
        Position south4 = Mockito.mock(Position.class);
        Position  east4 = Mockito.mock(Position.class);
        Position  west4 = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north1);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south1);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east1);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west1);

        Mockito.when(p2.adjacentPos(NORTH)).thenReturn(north2);
        Mockito.when(p2.adjacentPos(SOUTH)).thenReturn(south2);
        Mockito.when(p2.adjacentPos( EAST)).thenReturn( east2);
        Mockito.when(p2.adjacentPos( WEST)).thenReturn( west2);

        Mockito.when(p3.adjacentPos(NORTH)).thenReturn(north3);
        Mockito.when(p3.adjacentPos(SOUTH)).thenReturn(south3);
        Mockito.when(p3.adjacentPos( EAST)).thenReturn( east3);
        Mockito.when(p3.adjacentPos( WEST)).thenReturn( west3);

        Mockito.when(p4.adjacentPos(NORTH)).thenReturn(north4);
        Mockito.when(p4.adjacentPos(SOUTH)).thenReturn(south4);
        Mockito.when(p4.adjacentPos( EAST)).thenReturn( east4);
        Mockito.when(p4.adjacentPos( WEST)).thenReturn( west4);

        Mockito.when(p.distanceTo(north1)).thenReturn(8);
        Mockito.when(p.distanceTo(south1)).thenReturn(8);
        Mockito.when(p.distanceTo( east1)).thenReturn(7);
        Mockito.when(p.distanceTo( west1)).thenReturn(7);

        Mockito.when(p.distanceTo(north2)).thenReturn(2); //Closer Distance
        Mockito.when(p.distanceTo(south2)).thenReturn(3);
        Mockito.when(p.distanceTo( east2)).thenReturn(3);
        Mockito.when(p.distanceTo( west2)).thenReturn(2);

        Mockito.when(p.distanceTo(north3)).thenReturn(5);
        Mockito.when(p.distanceTo(south3)).thenReturn(4);
        Mockito.when(p.distanceTo( east3)).thenReturn(4);
        Mockito.when(p.distanceTo( west3)).thenReturn(4);

        Mockito.when(p.distanceTo(north4)).thenReturn(5);
        Mockito.when(p.distanceTo(south4)).thenReturn(5);
        Mockito.when(p.distanceTo( east4)).thenReturn(6);
        Mockito.when(p.distanceTo( west4)).thenReturn(6);

        Mockito.when(grid.tileOccupied(north1)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west1)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north2)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west2)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north3)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west3)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north4)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west4)).thenReturn(false);

        Enemy enemy = new Lizard(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(north2, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Lizard_AllyAndCityAreAtSameDistance_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);

        Position north1 = Mockito.mock(Position.class);
        Position south1 = Mockito.mock(Position.class);
        Position  east1 = Mockito.mock(Position.class);
        Position  west1 = Mockito.mock(Position.class);

        Position north2 = Mockito.mock(Position.class);
        Position south2 = Mockito.mock(Position.class);
        Position  east2 = Mockito.mock(Position.class);
        Position  west2 = Mockito.mock(Position.class);

        Position north3 = Mockito.mock(Position.class);
        Position south3 = Mockito.mock(Position.class);
        Position  east3 = Mockito.mock(Position.class);
        Position  west3 = Mockito.mock(Position.class);

        Position north4 = Mockito.mock(Position.class);
        Position south4 = Mockito.mock(Position.class);
        Position  east4 = Mockito.mock(Position.class);
        Position  west4 = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north1);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south1);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east1);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west1);

        Mockito.when(p2.adjacentPos(NORTH)).thenReturn(north2);
        Mockito.when(p2.adjacentPos(SOUTH)).thenReturn(south2);
        Mockito.when(p2.adjacentPos( EAST)).thenReturn( east2);
        Mockito.when(p2.adjacentPos( WEST)).thenReturn( west2);

        Mockito.when(p3.adjacentPos(NORTH)).thenReturn(north3);
        Mockito.when(p3.adjacentPos(SOUTH)).thenReturn(south3);
        Mockito.when(p3.adjacentPos( EAST)).thenReturn( east3);
        Mockito.when(p3.adjacentPos( WEST)).thenReturn( west3);

        Mockito.when(p4.adjacentPos(NORTH)).thenReturn(north4);
        Mockito.when(p4.adjacentPos(SOUTH)).thenReturn(south4);
        Mockito.when(p4.adjacentPos( EAST)).thenReturn( east4);
        Mockito.when(p4.adjacentPos( WEST)).thenReturn( west4);

        Mockito.when(p.distanceTo(north1)).thenReturn(2);
        Mockito.when(p.distanceTo(south1)).thenReturn(2);
        Mockito.when(p.distanceTo( east1)).thenReturn(1); // Closer Distance
        Mockito.when(p.distanceTo( west1)).thenReturn(2);

        Mockito.when(p.distanceTo(north2)).thenReturn(6);
        Mockito.when(p.distanceTo(south2)).thenReturn(7);
        Mockito.when(p.distanceTo( east2)).thenReturn(7);
        Mockito.when(p.distanceTo( west2)).thenReturn(6);

        Mockito.when(p.distanceTo(north3)).thenReturn(5);
        Mockito.when(p.distanceTo(south3)).thenReturn(4);
        Mockito.when(p.distanceTo( east3)).thenReturn(4);
        Mockito.when(p.distanceTo( west3)).thenReturn(4);

        Mockito.when(p.distanceTo(north4)).thenReturn(2);
        Mockito.when(p.distanceTo(south4)).thenReturn(2);
        Mockito.when(p.distanceTo( east4)).thenReturn(2);
        Mockito.when(p.distanceTo( west4)).thenReturn(1); //Closer Distance

        Mockito.when(grid.tileOccupied(north1)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east1)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west1)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north2)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east2)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west2)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north3)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east3)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west3)).thenReturn(false);

        Mockito.when(grid.tileOccupied(north4)).thenReturn(false);
        Mockito.when(grid.tileOccupied(south4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( east4)).thenReturn(false);
        Mockito.when(grid.tileOccupied( west4)).thenReturn(false);

        Enemy enemy = new Lizard(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(west4, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Lizard_EnemyCantMoveOrAttack_Test() {
        Model grid = Mockito.mock(Model.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);

        Position north1 = Mockito.mock(Position.class);
        Position south1 = Mockito.mock(Position.class);
        Position  east1 = Mockito.mock(Position.class);
        Position  west1 = Mockito.mock(Position.class);

        Position north2 = Mockito.mock(Position.class);
        Position south2 = Mockito.mock(Position.class);
        Position  east2 = Mockito.mock(Position.class);
        Position  west2 = Mockito.mock(Position.class);

        Position north3 = Mockito.mock(Position.class);
        Position south3 = Mockito.mock(Position.class);
        Position  east3 = Mockito.mock(Position.class);
        Position  west3 = Mockito.mock(Position.class);

        Position north4 = Mockito.mock(Position.class);
        Position south4 = Mockito.mock(Position.class);
        Position  east4 = Mockito.mock(Position.class);
        Position  west4 = Mockito.mock(Position.class);

        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Mockito.when(p1.adjacentPos(NORTH)).thenReturn(north1);
        Mockito.when(p1.adjacentPos(SOUTH)).thenReturn(south1);
        Mockito.when(p1.adjacentPos( EAST)).thenReturn( east1);
        Mockito.when(p1.adjacentPos( WEST)).thenReturn( west1);

        Mockito.when(p2.adjacentPos(NORTH)).thenReturn(north2);
        Mockito.when(p2.adjacentPos(SOUTH)).thenReturn(south2);
        Mockito.when(p2.adjacentPos( EAST)).thenReturn( east2);
        Mockito.when(p2.adjacentPos( WEST)).thenReturn( west2);

        Mockito.when(p3.adjacentPos(NORTH)).thenReturn(north3);
        Mockito.when(p3.adjacentPos(SOUTH)).thenReturn(south3);
        Mockito.when(p3.adjacentPos( EAST)).thenReturn( east3);
        Mockito.when(p3.adjacentPos( WEST)).thenReturn( west3);

        Mockito.when(p4.adjacentPos(NORTH)).thenReturn(north4);
        Mockito.when(p4.adjacentPos(SOUTH)).thenReturn(south4);
        Mockito.when(p4.adjacentPos( EAST)).thenReturn( east4);
        Mockito.when(p4.adjacentPos( WEST)).thenReturn( west4);

        Mockito.when(p.distanceTo(north1)).thenReturn(8);
        Mockito.when(p.distanceTo(south1)).thenReturn(8);
        Mockito.when(p.distanceTo( east1)).thenReturn(7);
        Mockito.when(p.distanceTo( west1)).thenReturn(7);

        Mockito.when(p.distanceTo(north2)).thenReturn(6);
        Mockito.when(p.distanceTo(south2)).thenReturn(7);
        Mockito.when(p.distanceTo( east2)).thenReturn(7);
        Mockito.when(p.distanceTo( west2)).thenReturn(6);

        Mockito.when(p.distanceTo(north3)).thenReturn(5);
        Mockito.when(p.distanceTo(south3)).thenReturn(4);
        Mockito.when(p.distanceTo( east3)).thenReturn(4);
        Mockito.when(p.distanceTo( west3)).thenReturn(4);

        Mockito.when(p.distanceTo(north4)).thenReturn(2);
        Mockito.when(p.distanceTo(south4)).thenReturn(1); //Closer Distance
        Mockito.when(p.distanceTo( east4)).thenReturn(2);
        Mockito.when(p.distanceTo( west4)).thenReturn(2);

        Mockito.when(grid.tileOccupied(north1)).thenReturn(true);
        Mockito.when(grid.tileOccupied(south1)).thenReturn(true);
        Mockito.when(grid.tileOccupied( east1)).thenReturn(true);
        Mockito.when(grid.tileOccupied( west1)).thenReturn(true);

        Mockito.when(grid.tileOccupied(north2)).thenReturn(true);
        Mockito.when(grid.tileOccupied(south2)).thenReturn(true);
        Mockito.when(grid.tileOccupied( east2)).thenReturn(true);
        Mockito.when(grid.tileOccupied( west2)).thenReturn(true);

        Mockito.when(grid.tileOccupied(north3)).thenReturn(true);
        Mockito.when(grid.tileOccupied(south3)).thenReturn(true);
        Mockito.when(grid.tileOccupied( east3)).thenReturn(true);
        Mockito.when(grid.tileOccupied( west3)).thenReturn(true);

        Mockito.when(grid.tileOccupied(north4)).thenReturn(true);
        Mockito.when(grid.tileOccupied(south4)).thenReturn(true);
        Mockito.when(grid.tileOccupied( east4)).thenReturn(true);
        Mockito.when(grid.tileOccupied( west4)).thenReturn(true);

        Enemy enemy = new Lizard(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(p, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Lizard_NoAllyOrCityFound_Test() {
        Position initialPosition = Mockito.mock(Position.class);

        Model modelGrid = Mockito.mock(Model.class);
        Mockito.when(modelGrid.getCities()).thenReturn(new ArrayList<>());
        Mockito.when(modelGrid.getAllies()).thenReturn(new ArrayList<>());

        Enemy enemy = new Lizard(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(initialPosition, enemy.getPosition());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());
    }

    // ----- DRAGON -----//
    @Test
    public void moveAndPlanAttack_Dragon_3City_NORTH_Test() {
        Model grid = Mockito.mock(Model.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        City city3 = Mockito.mock(City.class);

        List<Hero> allies = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p  = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);

        //TODO
        assertEquals(false, true);
    }

    @Test
    public void moveAndPlanAttack_Dragon_2City1Ally_EAST_Test() {
        //TODO
        assertEquals(false, true);
    }

    @Test
    public void moveAndPlanAttack_Dragon_2City1Ally_SOUTH_Test() {
        //TODO
        assertEquals(false, true);
    }

    @Test
    public void moveAndPlanAttack_Dragon_1AllyAvoidsEnemy_WEST_SOUTH_Test() {
        //TODO
        assertEquals(false, true);
    }

    @Test
    public void moveAndPlanAttack_Dragon_2AllyAttackItself_Test() {
        //TODO
        assertEquals(false, true);
    }

    @Test
    public void moveAndPlanAttack_Dragon_EnemyCantMoveOrAttack_Test() {
        Model grid = Mockito.mock(Model.class);
        City city = Mockito.mock(City.class);
        Hero ally = Mockito.mock(Hero.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally);
        List<City> cities = new ArrayList<>();
        cities.add(city);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p      = Mockito.mock(Position.class);
        Position p_ally = Mockito.mock(Position.class);
        Position p_city = Mockito.mock(Position.class);
        Position p_c_n  = Mockito.mock(Position.class);
        Position p_c_e  = Mockito.mock(Position.class);
        Position p_a_n  = Mockito.mock(Position.class);
        Position p_a_w  = Mockito.mock(Position.class);

        Mockito.when(ally.getPosition()).thenReturn(p_ally);
        Mockito.when(city.getPosition()).thenReturn(p_city);

        Mockito.when(p_ally.adjacentPos(NORTH)).thenReturn(p_a_n);
        Mockito.when(p_ally.adjacentPos(SOUTH)).thenReturn( null);
        Mockito.when(p_ally.adjacentPos( EAST)).thenReturn( null);
        Mockito.when(p_ally.adjacentPos( WEST)).thenReturn(p_a_w);
        Mockito.when(p_city.adjacentPos(NORTH)).thenReturn(p_c_n);
        Mockito.when(p_city.adjacentPos(SOUTH)).thenReturn( null);
        Mockito.when(p_city.adjacentPos( EAST)).thenReturn(p_c_e);
        Mockito.when(p_city.adjacentPos( WEST)).thenReturn( null);

        Mockito.when(grid.tileOccupied(p_c_n)).thenReturn(true);
        Mockito.when(grid.tileOccupied(p_c_e)).thenReturn(true);
        Mockito.when(grid.tileOccupied(p_a_n)).thenReturn(true);
        Mockito.when(grid.tileOccupied(p_a_w)).thenReturn(true);

        Enemy enemy = new Dragon(p, 11, 3);
        enemy.moveAndPlanAttack(grid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(p, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Dragon_NoAllyOrCityFound_Test() {
        Position initialPosition = Mockito.mock(Position.class);

        Model modelGrid = Mockito.mock(Model.class);
        Mockito.when(modelGrid.getCities()).thenReturn(new ArrayList<>());
        Mockito.when(modelGrid.getAllies()).thenReturn(new ArrayList<>());

        Enemy enemy = new Dragon(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(initialPosition, enemy.getPosition());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());
    }
}
