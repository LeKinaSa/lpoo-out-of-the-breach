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
        enemy.setAttackDirection(SOUTH);
        assertEquals(SOUTH, enemy.getAttackDirection());

        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(NONE, enemy.getAttackDirection());
        assertEquals(initialPosition, enemy.getPosition());
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

        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());
        assertEquals(initialPosition, enemy.getPosition());
    }

    // ----- DRAGON -----//
    @Test
    public void moveAndPlanAttack_Dragon_3City_NORTH_Test() {
        /*
        The best attack is 3 cities.
        | | |E| | |A|A| |
        | | |E|O| | | | |
        |D| |E|C| | | | |
        | | | |C|E|E|C| |
        | |A|E|C| | |C| |
        | |A| | | |A|A|A|
        | |C|C| | |C| |E|
        | | | | | | | | |
        */
        Model grid = Mockito.mock(Model.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        City city3 = Mockito.mock(City.class);
        City city4 = Mockito.mock(City.class);
        City city5 = Mockito.mock(City.class);
        City city6 = Mockito.mock(City.class);
        City city7 = Mockito.mock(City.class);
        City city8 = Mockito.mock(City.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        Hero ally3 = Mockito.mock(Hero.class);
        Hero ally4 = Mockito.mock(Hero.class);
        Hero ally5 = Mockito.mock(Hero.class);
        Hero ally6 = Mockito.mock(Hero.class);
        Hero ally7 = Mockito.mock(Hero.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        allies.add(ally3);
        allies.add(ally4);
        allies.add(ally5);
        allies.add(ally6);
        allies.add(ally7);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        cities.add(city7);
        cities.add(city8);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p02 = Mockito.mock(Position.class);
        Position p04 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p04)).thenReturn(false); Mockito.when(p04.same(p02)).thenReturn(false);
        Position p05 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p05)).thenReturn(false); Mockito.when(p05.same(p02)).thenReturn(false);
        Position p06 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p06)).thenReturn(false); Mockito.when(p06.same(p02)).thenReturn(false);
        Position p12 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p12)).thenReturn(false); Mockito.when(p12.same(p02)).thenReturn(false);
        Position p13 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p13)).thenReturn(false); Mockito.when(p13.same(p02)).thenReturn(false);
        Position p14 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p14)).thenReturn(true); Mockito.when(grid.getEntityAt(p14)).thenReturn(ally1);
        Position p15 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p15)).thenReturn(true); Mockito.when(grid.getEntityAt(p15)).thenReturn(ally2);
        Position p16 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p16)).thenReturn(true); Mockito.when(grid.getEntityAt(p16)).thenReturn(city1);
        Position p17 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p17)).thenReturn(false); Mockito.when(p17.same(p02)).thenReturn(false);
        Position p22 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p22)).thenReturn(true); Mockito.when(grid.getEntityAt(p22)).thenReturn(Mockito.mock(Enemy.class));
        Position p23 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p23)).thenReturn(false); Mockito.when(p23.same(p02)).thenReturn(false);
        Position p24 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p24)).thenReturn(true); Mockito.when(grid.getEntityAt(p24)).thenReturn(Mockito.mock(Entity.class));
        Position p25 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p25)).thenReturn(false); Mockito.when(p25.same(p02)).thenReturn(false);
        Position p26 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p26)).thenReturn(true); Mockito.when(grid.getEntityAt(p26)).thenReturn(city2);
        Position p27 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p27)).thenReturn(false); Mockito.when(p27.same(p02)).thenReturn(false);
        Position p31 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p31)).thenReturn(false); Mockito.when(p31.same(p02)).thenReturn(false);
        Position p32 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p32)).thenReturn(true); Mockito.when(grid.getEntityAt(p32)).thenReturn(city6);
        Position p33 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p33)).thenReturn(true); Mockito.when(grid.getEntityAt(p33)).thenReturn(city7);
        Position p34 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p34)).thenReturn(true); Mockito.when(grid.getEntityAt(p34)).thenReturn(city8);
        Position p35 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p35)).thenReturn(false); Mockito.when(p35.same(p02)).thenReturn(false);
        Position p36 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p36)).thenReturn(false); Mockito.when(p36.same(p02)).thenReturn(false);
        Position p40 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p40)).thenReturn(false); Mockito.when(p40.same(p02)).thenReturn(false);
        Position p42 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p42)).thenReturn(false); Mockito.when(p42.same(p02)).thenReturn(false);
        Position p43 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p43)).thenReturn(true); Mockito.when(grid.getEntityAt(p43)).thenReturn(Mockito.mock(Entity.class));
        Position p44 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p44)).thenReturn(false); Mockito.when(p44.same(p02)).thenReturn(false);
        Position p45 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p45)).thenReturn(false); Mockito.when(p45.same(p02)).thenReturn(false);
        Position p46 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p46)).thenReturn(false); Mockito.when(p46.same(p02)).thenReturn(false);
        Position p50 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p50)).thenReturn(true); Mockito.when(grid.getEntityAt(p50)).thenReturn(ally3);
        Position p51 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p51)).thenReturn(false); Mockito.when(p51.same(p02)).thenReturn(false);
        Position p53 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p53)).thenReturn(true); Mockito.when(grid.getEntityAt(p53)).thenReturn(Mockito.mock(Entity.class));
        Position p54 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p54)).thenReturn(false); Mockito.when(p54.same(p02)).thenReturn(false);
        Position p55 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p55)).thenReturn(true); Mockito.when(grid.getEntityAt(p55)).thenReturn(ally5);
        Position p56 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p56)).thenReturn(true); Mockito.when(grid.getEntityAt(p56)).thenReturn(city3);
        Position p57 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p57)).thenReturn(false); Mockito.when(p17.same(p57)).thenReturn(false);
        Position p60 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p60)).thenReturn(true); Mockito.when(grid.getEntityAt(p60)).thenReturn(ally4);
        Position p61 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p61)).thenReturn(false); Mockito.when(p61.same(p02)).thenReturn(false);
        Position p62 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p62)).thenReturn(false); Mockito.when(p62.same(p02)).thenReturn(false);
        Position p63 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p63)).thenReturn(true); Mockito.when(grid.getEntityAt(p63)).thenReturn(city5);
        Position p64 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p64)).thenReturn(true); Mockito.when(grid.getEntityAt(p64)).thenReturn(city4);
        Position p65 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p65)).thenReturn(true); Mockito.when(grid.getEntityAt(p65)).thenReturn(ally6);
        Position p66 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p66)).thenReturn(false); Mockito.when(p66.same(p02)).thenReturn(false);
        Position p70 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p70)).thenReturn(false); Mockito.when(p70.same(p02)).thenReturn(false);
        Position p73 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p73)).thenReturn(false); Mockito.when(p73.same(p02)).thenReturn(false);
        Position p74 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p74)).thenReturn(false); Mockito.when(p74.same(p02)).thenReturn(false);
        Position p75 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p75)).thenReturn(true); Mockito.when(grid.getEntityAt(p75)).thenReturn(ally7);
        Position p76 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p76)).thenReturn(true); Mockito.when(grid.getEntityAt(p76)).thenReturn(Mockito.mock(Entity.class));
        Position p77 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p77)).thenReturn(false); Mockito.when(p77.same(p02)).thenReturn(false);

        Mockito.when(ally1.getPosition()).thenReturn(p14);
        Mockito.when(ally2.getPosition()).thenReturn(p15);
        Mockito.when(ally3.getPosition()).thenReturn(p50);
        Mockito.when(ally4.getPosition()).thenReturn(p60);
        Mockito.when(ally5.getPosition()).thenReturn(p55);
        Mockito.when(ally6.getPosition()).thenReturn(p65);
        Mockito.when(ally7.getPosition()).thenReturn(p75);

        Mockito.when(city1.getPosition()).thenReturn(p16);
        Mockito.when(city2.getPosition()).thenReturn(p26);
        Mockito.when(city3.getPosition()).thenReturn(p56);
        Mockito.when(city4.getPosition()).thenReturn(p64);
        Mockito.when(city5.getPosition()).thenReturn(p63);
        Mockito.when(city6.getPosition()).thenReturn(p32);
        Mockito.when(city7.getPosition()).thenReturn(p33);
        Mockito.when(city8.getPosition()).thenReturn(p34);

        Mockito.when(p14.adjacentPos(NORTH)).thenReturn(p13);
        Mockito.when(p15.adjacentPos(NORTH)).thenReturn(p14);
        Mockito.when(p16.adjacentPos(NORTH)).thenReturn(p15);
        Mockito.when(p17.adjacentPos(NORTH)).thenReturn(p16);
        Mockito.when(p25.adjacentPos(NORTH)).thenReturn(p24);
        Mockito.when(p26.adjacentPos(NORTH)).thenReturn(p25);
        Mockito.when(p27.adjacentPos(NORTH)).thenReturn(p26);
        Mockito.when(p32.adjacentPos(NORTH)).thenReturn(p31);
        Mockito.when(p33.adjacentPos(NORTH)).thenReturn(p32);
        Mockito.when(p34.adjacentPos(NORTH)).thenReturn(p33);
        Mockito.when(p35.adjacentPos(NORTH)).thenReturn(p34);
        Mockito.when(p50.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p51.adjacentPos(NORTH)).thenReturn(p50);
        Mockito.when(p55.adjacentPos(NORTH)).thenReturn(p54);
        Mockito.when(p56.adjacentPos(NORTH)).thenReturn(p55);
        Mockito.when(p57.adjacentPos(NORTH)).thenReturn(p56);
        Mockito.when(p60.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p61.adjacentPos(NORTH)).thenReturn(p60);
        Mockito.when(p63.adjacentPos(NORTH)).thenReturn(p62);
        Mockito.when(p64.adjacentPos(NORTH)).thenReturn(p63);
        Mockito.when(p65.adjacentPos(NORTH)).thenReturn(p64);
        Mockito.when(p66.adjacentPos(NORTH)).thenReturn(p65);
        Mockito.when(p75.adjacentPos(NORTH)).thenReturn(p74);
        Mockito.when(p76.adjacentPos(NORTH)).thenReturn(p75);
        Mockito.when(p77.adjacentPos(NORTH)).thenReturn(p76);

        Mockito.when(p13.adjacentPos(SOUTH)).thenReturn(p14);
        Mockito.when(p14.adjacentPos(SOUTH)).thenReturn(p15);
        Mockito.when(p15.adjacentPos(SOUTH)).thenReturn(p16);
        Mockito.when(p16.adjacentPos(SOUTH)).thenReturn(p17);
        Mockito.when(p25.adjacentPos(SOUTH)).thenReturn(p26);
        Mockito.when(p26.adjacentPos(SOUTH)).thenReturn(p27);
        Mockito.when(p27.adjacentPos(SOUTH)).thenReturn(null);
        Mockito.when(p31.adjacentPos(SOUTH)).thenReturn(p32);
        Mockito.when(p32.adjacentPos(SOUTH)).thenReturn(p33);
        Mockito.when(p33.adjacentPos(SOUTH)).thenReturn(p34);
        Mockito.when(p34.adjacentPos(SOUTH)).thenReturn(p35);
        Mockito.when(p50.adjacentPos(SOUTH)).thenReturn(p51);
        Mockito.when(p54.adjacentPos(SOUTH)).thenReturn(p55);
        Mockito.when(p55.adjacentPos(SOUTH)).thenReturn(p56);
        Mockito.when(p56.adjacentPos(SOUTH)).thenReturn(p57);
        Mockito.when(p60.adjacentPos(SOUTH)).thenReturn(p61);
        Mockito.when(p62.adjacentPos(SOUTH)).thenReturn(p63);
        Mockito.when(p63.adjacentPos(SOUTH)).thenReturn(p64);
        Mockito.when(p64.adjacentPos(SOUTH)).thenReturn(p65);
        Mockito.when(p65.adjacentPos(SOUTH)).thenReturn(p66);
        Mockito.when(p74.adjacentPos(SOUTH)).thenReturn(p75);
        Mockito.when(p75.adjacentPos(SOUTH)).thenReturn(p76);
        Mockito.when(p76.adjacentPos(SOUTH)).thenReturn(p77);
        
        Mockito.when(p05.adjacentPos( EAST)).thenReturn(null);
        Mockito.when(p14.adjacentPos( EAST)).thenReturn(p04);
        Mockito.when(p15.adjacentPos( EAST)).thenReturn(p05);
        Mockito.when(p16.adjacentPos( EAST)).thenReturn(p06);
        Mockito.when(p22.adjacentPos( EAST)).thenReturn(p12);
        Mockito.when(p24.adjacentPos( EAST)).thenReturn(p14);
        Mockito.when(p25.adjacentPos( EAST)).thenReturn(p15);
        Mockito.when(p26.adjacentPos( EAST)).thenReturn(p16);
        Mockito.when(p32.adjacentPos( EAST)).thenReturn(p22);
        Mockito.when(p33.adjacentPos( EAST)).thenReturn(p23);
        Mockito.when(p34.adjacentPos( EAST)).thenReturn(p24);
        Mockito.when(p36.adjacentPos( EAST)).thenReturn(p26);
        Mockito.when(p42.adjacentPos( EAST)).thenReturn(p32);
        Mockito.when(p44.adjacentPos( EAST)).thenReturn(p34);
        Mockito.when(p46.adjacentPos( EAST)).thenReturn(p36);
        Mockito.when(p50.adjacentPos( EAST)).thenReturn(p40);
        Mockito.when(p53.adjacentPos( EAST)).thenReturn(p43);
        Mockito.when(p54.adjacentPos( EAST)).thenReturn(p44);
        Mockito.when(p55.adjacentPos( EAST)).thenReturn(p45);
        Mockito.when(p56.adjacentPos( EAST)).thenReturn(p46);
        Mockito.when(p60.adjacentPos( EAST)).thenReturn(p50);
        Mockito.when(p63.adjacentPos( EAST)).thenReturn(p53);
        Mockito.when(p64.adjacentPos( EAST)).thenReturn(p54);
        Mockito.when(p65.adjacentPos( EAST)).thenReturn(p55);
        Mockito.when(p66.adjacentPos( EAST)).thenReturn(p56);
        Mockito.when(p70.adjacentPos( EAST)).thenReturn(p60);
        Mockito.when(p73.adjacentPos( EAST)).thenReturn(p63);
        Mockito.when(p74.adjacentPos( EAST)).thenReturn(p64);
        Mockito.when(p75.adjacentPos( EAST)).thenReturn(p65);
        Mockito.when(p76.adjacentPos( EAST)).thenReturn(p66);

        Mockito.when(p04.adjacentPos( WEST)).thenReturn(p14);
        Mockito.when(p05.adjacentPos( WEST)).thenReturn(p15);
        Mockito.when(p06.adjacentPos( WEST)).thenReturn(p16);
        Mockito.when(p14.adjacentPos( WEST)).thenReturn(p24);
        Mockito.when(p15.adjacentPos( WEST)).thenReturn(p25);
        Mockito.when(p16.adjacentPos( WEST)).thenReturn(p26);
        Mockito.when(p23.adjacentPos( WEST)).thenReturn(p33);
        Mockito.when(p24.adjacentPos( WEST)).thenReturn(p34);
        Mockito.when(p25.adjacentPos( WEST)).thenReturn(p35);
        Mockito.when(p26.adjacentPos( WEST)).thenReturn(p36);
        Mockito.when(p32.adjacentPos( WEST)).thenReturn(p42);
        Mockito.when(p33.adjacentPos( WEST)).thenReturn(p43);
        Mockito.when(p34.adjacentPos( WEST)).thenReturn(p44);
        Mockito.when(p40.adjacentPos( WEST)).thenReturn(p50);
        Mockito.when(p43.adjacentPos( WEST)).thenReturn(p53);
        Mockito.when(p45.adjacentPos( WEST)).thenReturn(p55);
        Mockito.when(p46.adjacentPos( WEST)).thenReturn(p56);
        Mockito.when(p50.adjacentPos( WEST)).thenReturn(p60);
        Mockito.when(p54.adjacentPos( WEST)).thenReturn(p64);
        Mockito.when(p55.adjacentPos( WEST)).thenReturn(p65);
        Mockito.when(p56.adjacentPos( WEST)).thenReturn(p66);
        Mockito.when(p60.adjacentPos( WEST)).thenReturn(p70);
        Mockito.when(p63.adjacentPos( WEST)).thenReturn(p73);
        Mockito.when(p64.adjacentPos( WEST)).thenReturn(p74);
        Mockito.when(p65.adjacentPos( WEST)).thenReturn(p75);
        Mockito.when(p66.adjacentPos( WEST)).thenReturn(p76);
        Mockito.when(p74.adjacentPos( WEST)).thenReturn(null);
        Mockito.when(p75.adjacentPos( WEST)).thenReturn(null);
        Mockito.when(p76.adjacentPos( WEST)).thenReturn(null);

        Enemy enemy = new Dragon(p02, 10, 2);
        enemy.moveAndPlanAttack(grid);

        assertEquals(AttackDirection.SOUTH, enemy.getAttackDirection());
        assertEquals(p31, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Dragon_2City1Ally_EAST_Test() {
        /*
        It should prefer 2 cities 1 ally to 1 city 2 allies.
        | | | | | | | | |
        | | | | | | | | |
        | |O|C|C|A| | | |
        | | | | | | | | |
        | | | | | |A|A|C|
        |D| | | | | | | |
        | | | | | | | | |
        | | | | | | | | |
        */
        Model grid = Mockito.mock(Model.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        City city3 = Mockito.mock(City.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        Hero ally3 = Mockito.mock(Hero.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        allies.add(ally3);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p05 = Mockito.mock(Position.class);
        Position p12 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p12)).thenReturn(false); Mockito.when(p12.same(p05)).thenReturn(false);
        Position p20 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p20)).thenReturn(false); Mockito.when(p20.same(p05)).thenReturn(false);
        Position p21 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p21)).thenReturn(false); Mockito.when(p21.same(p05)).thenReturn(false);
        Position p22 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p22)).thenReturn(true); Mockito.when(grid.getEntityAt(p22)).thenReturn(city2);
        Position p23 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p23)).thenReturn(false); Mockito.when(p23.same(p05)).thenReturn(false);
        Position p24 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p24)).thenReturn(false); Mockito.when(p24.same(p05)).thenReturn(false);
        Position p30 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p30)).thenReturn(false); Mockito.when(p30.same(p05)).thenReturn(false);
        Position p31 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p31)).thenReturn(false); Mockito.when(p31.same(p05)).thenReturn(false);
        Position p32 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p32)).thenReturn(true); Mockito.when(grid.getEntityAt(p32)).thenReturn(city3);
        Position p33 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p33)).thenReturn(false); Mockito.when(p33.same(p05)).thenReturn(false);
        Position p34 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p34)).thenReturn(false); Mockito.when(p34.same(p05)).thenReturn(false);
        Position p40 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p40)).thenReturn(false); Mockito.when(p40.same(p05)).thenReturn(false);
        Position p41 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p41)).thenReturn(false); Mockito.when(p41.same(p05)).thenReturn(false);
        Position p42 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p42)).thenReturn(true); Mockito.when(grid.getEntityAt(p42)).thenReturn(ally3);
        Position p43 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p43)).thenReturn(false); Mockito.when(p43.same(p05)).thenReturn(false);
        Position p44 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p44)).thenReturn(false); Mockito.when(p44.same(p05)).thenReturn(false);
        Position p52 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p52)).thenReturn(false); Mockito.when(p52.same(p05)).thenReturn(false);
        Position p53 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p53)).thenReturn(false); Mockito.when(p53.same(p05)).thenReturn(false);
        Position p54 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p54)).thenReturn(true); Mockito.when(grid.getEntityAt(p54)).thenReturn(ally2);
        Position p55 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p55)).thenReturn(false); Mockito.when(p55.same(p05)).thenReturn(false);
        Position p56 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p56)).thenReturn(false); Mockito.when(p56.same(p05)).thenReturn(false);
        Position p62 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p62)).thenReturn(false); Mockito.when(p62.same(p05)).thenReturn(false);
        Position p63 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p63)).thenReturn(false); Mockito.when(p63.same(p05)).thenReturn(false);
        Position p64 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p64)).thenReturn(true); Mockito.when(grid.getEntityAt(p64)).thenReturn(ally1);
        Position p65 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p65)).thenReturn(false); Mockito.when(p65.same(p05)).thenReturn(false);
        Position p66 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p66)).thenReturn(false); Mockito.when(p66.same(p05)).thenReturn(false);
        Position p72 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p72)).thenReturn(false); Mockito.when(p72.same(p05)).thenReturn(false);
        Position p73 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p73)).thenReturn(false); Mockito.when(p73.same(p05)).thenReturn(false);
        Position p74 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p74)).thenReturn(true); Mockito.when(grid.getEntityAt(p74)).thenReturn(city1);
        Position p75 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p75)).thenReturn(false); Mockito.when(p75.same(p05)).thenReturn(false);
        Position p76 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p76)).thenReturn(false); Mockito.when(p76.same(p05)).thenReturn(false);

        Mockito.when(p21.adjacentPos(NORTH)).thenReturn(p20);
        Mockito.when(p22.adjacentPos(NORTH)).thenReturn(p21);
        Mockito.when(p23.adjacentPos(NORTH)).thenReturn(p22);
        Mockito.when(p31.adjacentPos(NORTH)).thenReturn(p30);
        Mockito.when(p32.adjacentPos(NORTH)).thenReturn(p31);
        Mockito.when(p33.adjacentPos(NORTH)).thenReturn(p32);
        Mockito.when(p41.adjacentPos(NORTH)).thenReturn(p40);
        Mockito.when(p42.adjacentPos(NORTH)).thenReturn(p41);
        Mockito.when(p43.adjacentPos(NORTH)).thenReturn(p42);
        Mockito.when(p53.adjacentPos(NORTH)).thenReturn(p52);
        Mockito.when(p54.adjacentPos(NORTH)).thenReturn(p53);
        Mockito.when(p55.adjacentPos(NORTH)).thenReturn(p54);
        Mockito.when(p63.adjacentPos(NORTH)).thenReturn(p62);
        Mockito.when(p64.adjacentPos(NORTH)).thenReturn(p63);
        Mockito.when(p65.adjacentPos(NORTH)).thenReturn(p64);
        Mockito.when(p73.adjacentPos(NORTH)).thenReturn(p72);
        Mockito.when(p74.adjacentPos(NORTH)).thenReturn(p73);
        Mockito.when(p75.adjacentPos(NORTH)).thenReturn(p74);

        Mockito.when(p21.adjacentPos(SOUTH)).thenReturn(p22);
        Mockito.when(p22.adjacentPos(SOUTH)).thenReturn(p23);
        Mockito.when(p23.adjacentPos(SOUTH)).thenReturn(p24);
        Mockito.when(p31.adjacentPos(SOUTH)).thenReturn(p32);
        Mockito.when(p32.adjacentPos(SOUTH)).thenReturn(p33);
        Mockito.when(p33.adjacentPos(SOUTH)).thenReturn(p34);
        Mockito.when(p41.adjacentPos(SOUTH)).thenReturn(p42);
        Mockito.when(p42.adjacentPos(SOUTH)).thenReturn(p43);
        Mockito.when(p43.adjacentPos(SOUTH)).thenReturn(p44);
        Mockito.when(p53.adjacentPos(SOUTH)).thenReturn(p54);
        Mockito.when(p54.adjacentPos(SOUTH)).thenReturn(p55);
        Mockito.when(p55.adjacentPos(SOUTH)).thenReturn(p56);
        Mockito.when(p63.adjacentPos(SOUTH)).thenReturn(p64);
        Mockito.when(p64.adjacentPos(SOUTH)).thenReturn(p65);
        Mockito.when(p65.adjacentPos(SOUTH)).thenReturn(p66);
        Mockito.when(p73.adjacentPos(SOUTH)).thenReturn(p74);
        Mockito.when(p74.adjacentPos(SOUTH)).thenReturn(p75);
        Mockito.when(p75.adjacentPos(SOUTH)).thenReturn(p76);

        Mockito.when(p22.adjacentPos( EAST)).thenReturn(p12);
        Mockito.when(p32.adjacentPos( EAST)).thenReturn(p22);
        Mockito.when(p42.adjacentPos( EAST)).thenReturn(p32);
        Mockito.when(p52.adjacentPos( EAST)).thenReturn(p42);
        Mockito.when(p54.adjacentPos( EAST)).thenReturn(p44);
        Mockito.when(p64.adjacentPos( EAST)).thenReturn(p54);
        Mockito.when(p74.adjacentPos( EAST)).thenReturn(p64);

        Mockito.when(p12.adjacentPos( WEST)).thenReturn(p22);
        Mockito.when(p22.adjacentPos( WEST)).thenReturn(p32);
        Mockito.when(p32.adjacentPos( WEST)).thenReturn(p42);
        Mockito.when(p42.adjacentPos( WEST)).thenReturn(p52);
        Mockito.when(p44.adjacentPos( WEST)).thenReturn(p54);
        Mockito.when(p54.adjacentPos( WEST)).thenReturn(p64);
        Mockito.when(p64.adjacentPos( WEST)).thenReturn(p74);
        Mockito.when(p74.adjacentPos( WEST)).thenReturn(null);

        Mockito.when(ally1.getPosition()).thenReturn(p64);
        Mockito.when(ally2.getPosition()).thenReturn(p54);
        Mockito.when(ally3.getPosition()).thenReturn(p42);
        Mockito.when(city1.getPosition()).thenReturn(p74);
        Mockito.when(city2.getPosition()).thenReturn(p22);
        Mockito.when(city3.getPosition()).thenReturn(p32);

        Enemy enemy = new Dragon(p05, 10, 2);
        enemy.moveAndPlanAttack(grid);

        assertEquals(AttackDirection.WEST, enemy.getAttackDirection());
        assertEquals(p12, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Dragon_2City1Ally_SOUTH_Test() {
        /*
        It should prefer 2 cities to 1 city 1 ally or 2 allies.
        | | | |A|C|E| | |
        | | | |A|C| | | |
        | | | | |O| | | |
        | | | | | | | | |
        | | | | | | | | |
        | | | |D| | | | |
        | | | | | | | | |
        | | | | | | | | |
        */
        Model grid = Mockito.mock(Model.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p05 = Mockito.mock(Position.class);
        Position p20 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p20)).thenReturn(false); Mockito.when(p20.same(p05)).thenReturn(false);
        Position p21 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p21)).thenReturn(false); Mockito.when(p21.same(p05)).thenReturn(false);
        Position p30 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p30)).thenReturn(true); Mockito.when(grid.getEntityAt(p30)).thenReturn(ally1);
        Position p31 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p31)).thenReturn(true); Mockito.when(grid.getEntityAt(p31)).thenReturn(ally2);
        Position p32 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p32)).thenReturn(false); Mockito.when(p32.same(p05)).thenReturn(false);
        Position p40 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p40)).thenReturn(true); Mockito.when(grid.getEntityAt(p40)).thenReturn(city1);
        Position p41 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p41)).thenReturn(true); Mockito.when(grid.getEntityAt(p41)).thenReturn(city2);
        Position p42 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p42)).thenReturn(false); Mockito.when(p42.same(p05)).thenReturn(false);
        Position p50 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p50)).thenReturn(true); Mockito.when(grid.getEntityAt(p50)).thenReturn(Mockito.mock(Enemy.class));
        Position p51 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p51)).thenReturn(false); Mockito.when(p51.same(p05)).thenReturn(false);

        Mockito.when(p30.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p31.adjacentPos(NORTH)).thenReturn(p30);
        Mockito.when(p32.adjacentPos(NORTH)).thenReturn(p31);
        Mockito.when(p40.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p41.adjacentPos(NORTH)).thenReturn(p40);
        Mockito.when(p42.adjacentPos(NORTH)).thenReturn(p41);

        Mockito.when(p30.adjacentPos(SOUTH)).thenReturn(p31);
        Mockito.when(p31.adjacentPos(SOUTH)).thenReturn(p32);
        Mockito.when(p40.adjacentPos(SOUTH)).thenReturn(p41);
        Mockito.when(p41.adjacentPos(SOUTH)).thenReturn(p42);

        Mockito.when(p30.adjacentPos( EAST)).thenReturn(p20);
        Mockito.when(p31.adjacentPos( EAST)).thenReturn(p21);
        Mockito.when(p40.adjacentPos( EAST)).thenReturn(p30);
        Mockito.when(p41.adjacentPos( EAST)).thenReturn(p31);
        Mockito.when(p51.adjacentPos( EAST)).thenReturn(p41);

        Mockito.when(p20.adjacentPos( WEST)).thenReturn(p30);
        Mockito.when(p21.adjacentPos( WEST)).thenReturn(p31);
        Mockito.when(p30.adjacentPos( WEST)).thenReturn(p40);
        Mockito.when(p31.adjacentPos( WEST)).thenReturn(p41);
        Mockito.when(p40.adjacentPos( WEST)).thenReturn(p50);
        Mockito.when(p41.adjacentPos( WEST)).thenReturn(p51);

        Mockito.when(ally1.getPosition()).thenReturn(p30);
        Mockito.when(ally2.getPosition()).thenReturn(p31);
        Mockito.when(city1.getPosition()).thenReturn(p40);
        Mockito.when(city2.getPosition()).thenReturn(p41);

        Enemy enemy = new Dragon(p05, 10, 2);
        enemy.moveAndPlanAttack(grid);

        assertEquals(AttackDirection.NORTH, enemy.getAttackDirection());
        assertEquals(p42, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Dragon_1AllyAvoidsEnemy_WEST_Test() {
        /*
        Avoids attacking other enemies.
        | | | | | | | | |
        |E| | | | | | | |
        |A|O| | | | | | |
        | | | | | | | | |
        | | | | | | | | |
        |D| | | |C|C|E| |
        | | | | |E|E| | |
        | | | | | | | | |
        */
        Model grid = Mockito.mock(Model.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Hero ally1 = Mockito.mock(Hero.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        Position p05 = Mockito.mock(Position.class);
        Position p00 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p00)).thenReturn(false); Mockito.when(p00.same(p05)).thenReturn(false);
        Position p01 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p01)).thenReturn(true); Mockito.when(grid.getEntityAt(p01)).thenReturn(Mockito.mock(Enemy.class));
        Position p02 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p02)).thenReturn(true); Mockito.when(grid.getEntityAt(p02)).thenReturn(ally1);
        Position p03 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p03)).thenReturn(false); Mockito.when(p03.same(p05)).thenReturn(false);
        Position p12 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p12)).thenReturn(false); Mockito.when(p12.same(p05)).thenReturn(false);
        Position p35 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p35)).thenReturn(false); Mockito.when(p35.same(p05)).thenReturn(false);
        Position p44 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p44)).thenReturn(false); Mockito.when(p44.same(p05)).thenReturn(false);
        Position p45 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p45)).thenReturn(true); Mockito.when(grid.getEntityAt(p45)).thenReturn(city1);
        Position p46 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p46)).thenReturn(true); Mockito.when(grid.getEntityAt(p46)).thenReturn(Mockito.mock(Enemy.class));
        Position p47 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p47)).thenReturn(false); Mockito.when(p47.same(p05)).thenReturn(false);
        Position p54 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p54)).thenReturn(false); Mockito.when(p54.same(p05)).thenReturn(false);
        Position p55 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p55)).thenReturn(true); Mockito.when(grid.getEntityAt(p55)).thenReturn(city2);
        Position p56 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p56)).thenReturn(true); Mockito.when(grid.getEntityAt(p56)).thenReturn(Mockito.mock(Enemy.class));
        Position p57 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p57)).thenReturn(false); Mockito.when(p57.same(p05)).thenReturn(false);
        Position p65 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p65)).thenReturn(true); Mockito.when(grid.getEntityAt(p65)).thenReturn(Mockito.mock(Enemy.class));

        Mockito.when(p01.adjacentPos(NORTH)).thenReturn(p00);
        Mockito.when(p02.adjacentPos(NORTH)).thenReturn(p01);
        Mockito.when(p03.adjacentPos(NORTH)).thenReturn(p02);
        Mockito.when(p45.adjacentPos(NORTH)).thenReturn(p44);
        Mockito.when(p55.adjacentPos(NORTH)).thenReturn(p54);

        Mockito.when(p02.adjacentPos(SOUTH)).thenReturn(p03);
        Mockito.when(p44.adjacentPos(SOUTH)).thenReturn(p45);
        Mockito.when(p45.adjacentPos(SOUTH)).thenReturn(p46);
        Mockito.when(p46.adjacentPos(SOUTH)).thenReturn(p47);
        Mockito.when(p54.adjacentPos(SOUTH)).thenReturn(p55);
        Mockito.when(p55.adjacentPos(SOUTH)).thenReturn(p56);
        Mockito.when(p56.adjacentPos(SOUTH)).thenReturn(p57);

        Mockito.when(p02.adjacentPos( EAST)).thenReturn(null);
        Mockito.when(p12.adjacentPos( EAST)).thenReturn(p02);
        Mockito.when(p45.adjacentPos( EAST)).thenReturn(p35);
        Mockito.when(p55.adjacentPos( EAST)).thenReturn(p45);

        Mockito.when(p02.adjacentPos( WEST)).thenReturn(p12);
        Mockito.when(p35.adjacentPos( WEST)).thenReturn(p45);
        Mockito.when(p45.adjacentPos( WEST)).thenReturn(p55);
        Mockito.when(p55.adjacentPos( WEST)).thenReturn(p65);

        Mockito.when(ally1.getPosition()).thenReturn(p02);
        Mockito.when(city1.getPosition()).thenReturn(p45);
        Mockito.when(city2.getPosition()).thenReturn(p55);

        Enemy enemy = new Dragon(p05, 10, 2);
        enemy.moveAndPlanAttack(grid);

        assertEquals(AttackDirection.EAST, enemy.getAttackDirection());
        assertEquals(p12, enemy.getPosition());
    }

    @Test
    public void moveAndPlanAttack_Dragon_2AllyAttackItself_Test() {
        /*
        It should prefer 2 ally to 1 city or 1 ally.
        The enemy in between is the one attacking so it will move and wont suffer damage from the damage.
        | | | | |A| | | |
        | | | | | | | | |
        | | | | | | | | |
        | | |O|A|D|A| | |
        | | | | | | | | |
        | | | | | | | | |
        | |E| | | | | |C|
        | | | | | | | | |
        */
        Model grid = Mockito.mock(Model.class);
        City city1 = Mockito.mock(City.class);
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        Hero ally3 = Mockito.mock(Hero.class);

        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);
        allies.add(ally3);
        List<City> cities = new ArrayList<>();
        cities.add(city1);

        Mockito.when(grid.getAllies()).thenReturn(allies);
        Mockito.when(grid.getCities()).thenReturn(cities);

        // Initial Dragon Position
        Position p43 = Mockito.mock(Position.class);
        Enemy enemy = new Dragon(p43, 2, 2);
        Mockito.when(p43.same(p43)).thenReturn(true);
        Mockito.when(grid.tileOccupied(p43)).thenReturn(true);
        Mockito.when(grid.getEntityAt(p43)).thenReturn(enemy);

        Position p13 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p13)).thenReturn(false); Mockito.when(p13.same(p43)).thenReturn(false);
        Position p20 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p20)).thenReturn(false); Mockito.when(p20.same(p43)).thenReturn(false);
        Position p23 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p23)).thenReturn(false); Mockito.when(p23.same(p43)).thenReturn(false);
        Position p30 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p30)).thenReturn(false); Mockito.when(p30.same(p43)).thenReturn(false);
        Position p31 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p31)).thenReturn(false); Mockito.when(p31.same(p43)).thenReturn(false);
        Position p32 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p32)).thenReturn(false); Mockito.when(p32.same(p43)).thenReturn(false);
        Position p33 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p33)).thenReturn(true); Mockito.when(grid.getEntityAt(p33)).thenReturn(ally3);
        Position p34 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p34)).thenReturn(false); Mockito.when(p34.same(p43)).thenReturn(false);
        Position p35 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p35)).thenReturn(false); Mockito.when(p35.same(p43)).thenReturn(false);
        Position p40 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p40)).thenReturn(true); Mockito.when(grid.getEntityAt(p40)).thenReturn(ally1);
        Position p41 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p41)).thenReturn(false); Mockito.when(p41.same(p43)).thenReturn(false);
        Position p50 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p50)).thenReturn(false); Mockito.when(p50.same(p43)).thenReturn(false);
        Position p51 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p51)).thenReturn(false); Mockito.when(p51.same(p43)).thenReturn(false);
        Position p52 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p52)).thenReturn(false); Mockito.when(p52.same(p43)).thenReturn(false);
        Position p53 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p53)).thenReturn(true); Mockito.when(grid.getEntityAt(p53)).thenReturn(ally2);
        Position p54 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p54)).thenReturn(false); Mockito.when(p54.same(p43)).thenReturn(false);
        Position p55 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p55)).thenReturn(false); Mockito.when(p55.same(p43)).thenReturn(false);
        Position p60 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p60)).thenReturn(false); Mockito.when(p60.same(p43)).thenReturn(false);
        Position p63 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p63)).thenReturn(false); Mockito.when(p63.same(p43)).thenReturn(false);
        Position p66 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p66)).thenReturn(false); Mockito.when(p66.same(p43)).thenReturn(false);
        Position p73 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p73)).thenReturn(false); Mockito.when(p73.same(p43)).thenReturn(false);
        Position p74 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p74)).thenReturn(false); Mockito.when(p74.same(p43)).thenReturn(false);
        Position p75 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p75)).thenReturn(false); Mockito.when(p75.same(p43)).thenReturn(false);
        Position p76 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p76)).thenReturn(true); Mockito.when(grid.getEntityAt(p76)).thenReturn(city1);
        Position p77 = Mockito.mock(Position.class); Mockito.when(grid.tileOccupied(p77)).thenReturn(false); Mockito.when(p77.same(p43)).thenReturn(false);

        Mockito.when(p32.adjacentPos(NORTH)).thenReturn(p31);
        Mockito.when(p33.adjacentPos(NORTH)).thenReturn(p32);
        Mockito.when(p34.adjacentPos(NORTH)).thenReturn(p33);
        Mockito.when(p40.adjacentPos(NORTH)).thenReturn(null);
        Mockito.when(p41.adjacentPos(NORTH)).thenReturn(p40);
        Mockito.when(p52.adjacentPos(NORTH)).thenReturn(p51);
        Mockito.when(p53.adjacentPos(NORTH)).thenReturn(p52);
        Mockito.when(p54.adjacentPos(NORTH)).thenReturn(p53);
        Mockito.when(p75.adjacentPos(NORTH)).thenReturn(p74);
        Mockito.when(p76.adjacentPos(NORTH)).thenReturn(p75);
        Mockito.when(p77.adjacentPos(NORTH)).thenReturn(p76);

        Mockito.when(p32.adjacentPos(SOUTH)).thenReturn(p33);
        Mockito.when(p33.adjacentPos(SOUTH)).thenReturn(p34);
        Mockito.when(p34.adjacentPos(SOUTH)).thenReturn(p35);
        Mockito.when(p40.adjacentPos(SOUTH)).thenReturn(p41);
        Mockito.when(p52.adjacentPos(SOUTH)).thenReturn(p53);
        Mockito.when(p53.adjacentPos(SOUTH)).thenReturn(p54);
        Mockito.when(p54.adjacentPos(SOUTH)).thenReturn(p55);
        Mockito.when(p75.adjacentPos(SOUTH)).thenReturn(p76);
        Mockito.when(p76.adjacentPos(SOUTH)).thenReturn(p77);
        Mockito.when(p77.adjacentPos(SOUTH)).thenReturn(null);

        Mockito.when(p30.adjacentPos( EAST)).thenReturn(p20);
        Mockito.when(p40.adjacentPos( EAST)).thenReturn(p30);
        Mockito.when(p50.adjacentPos( EAST)).thenReturn(p40);
        Mockito.when(p23.adjacentPos( EAST)).thenReturn(p13);
        Mockito.when(p33.adjacentPos( EAST)).thenReturn(p23);
        Mockito.when(p43.adjacentPos( EAST)).thenReturn(p33);
        Mockito.when(p53.adjacentPos( EAST)).thenReturn(p43);
        Mockito.when(p63.adjacentPos( EAST)).thenReturn(p53);
        Mockito.when(p76.adjacentPos( EAST)).thenReturn(p66);

        Mockito.when(p30.adjacentPos( EAST)).thenReturn(p40);
        Mockito.when(p40.adjacentPos( EAST)).thenReturn(p50);
        Mockito.when(p50.adjacentPos( EAST)).thenReturn(p60);
        Mockito.when(p23.adjacentPos( EAST)).thenReturn(p33);
        Mockito.when(p33.adjacentPos( EAST)).thenReturn(p43);
        Mockito.when(p43.adjacentPos( EAST)).thenReturn(p53);
        Mockito.when(p53.adjacentPos( EAST)).thenReturn(p63);
        Mockito.when(p63.adjacentPos( EAST)).thenReturn(p73);
        Mockito.when(p66.adjacentPos( EAST)).thenReturn(p76);
        Mockito.when(p76.adjacentPos( EAST)).thenReturn(null);

        Mockito.when(ally1.getPosition()).thenReturn(p40);
        Mockito.when(ally2.getPosition()).thenReturn(p53);
        Mockito.when(ally3.getPosition()).thenReturn(p33);
        Mockito.when(city1.getPosition()).thenReturn(p76);

        enemy.moveAndPlanAttack(grid);

        //TODO : verify if the enemy is itself
        //assertEquals(AttackDirection.EAST, enemy.getAttackDirection());
        //assertEquals(p63, enemy.getPosition());
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
        enemy.setAttackDirection(NORTH);
        assertEquals(NORTH, enemy.getAttackDirection());

        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(initialPosition, enemy.getPosition());
        assertEquals(NONE, enemy.getAttackDirection());
    }
}
