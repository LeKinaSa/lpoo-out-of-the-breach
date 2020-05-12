package out_of_the_breach.model;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EnemyTest {
    @Test
    public void strategyTest() {
        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        Mockito.when(strategy.getDirection()).thenReturn(AttackDirection.NORTH);
        Enemy enemy = new Bug(null, 10, 3);

        assertEquals(MeleeAttack.class, enemy.getAttackStrategy().getClass());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());

        enemy.setAttackStrategy(strategy);
        assertEquals(strategy, enemy.getAttackStrategy());
        assertEquals(AttackDirection.NORTH, enemy.getAttackDirection());
    }

    @Test
    public void attackTest() {
        AttackStrategy strategy = Mockito.mock(AttackStrategy.class);
        DamageMatrix dmgMatrix = Mockito.mock(DamageMatrix.class);
        Mockito.when(strategy.previewAttack(null)).thenReturn(dmgMatrix);

        Enemy enemy = new Bug(null, 1, 2);
        enemy.setAttackStrategy(strategy);
        assertEquals(dmgMatrix, enemy.previewAttack());
    }

    @Test
    public void moveAndPlanAttackCityIsCloser_NORTH_Test() {
        Position initialPosition = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class); // Closer Position
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position  east = Mockito.mock(Position.class);
        Position  west = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(p4)).thenReturn(false);
        Mockito.when(p4.north()).thenReturn(north);
        Mockito.when(p4.south()).thenReturn(south);
        Mockito.when(p4.east ()).thenReturn( east);
        Mockito.when(p4.west ()).thenReturn( west);

        Mockito.when(initialPosition.distanceTo(p1)).thenReturn(30);
        Mockito.when(initialPosition.distanceTo(p2)).thenReturn(27);
        Mockito.when(initialPosition.distanceTo(p3)).thenReturn(25);
        Mockito.when(initialPosition.distanceTo(p4)).thenReturn(10); // City is closer
        Mockito.when(initialPosition.distanceTo(north)).thenReturn( 9); // Closer Distance
        Mockito.when(initialPosition.distanceTo(south)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo( east)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo( west)).thenReturn( 9); // Closer Distance
        /* The distance is the same between
            initialPosition and North       and       initialPosition and West
        so the enemy should go for the north position.
        */
        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Model modelGrid = Mockito.mock(Model.class);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);

        Mockito.when(modelGrid.getCities()).thenReturn(cities);
        Mockito.when(modelGrid.getAllies()).thenReturn(allies);

        Mockito.when(modelGrid.tileOccupied(north)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied(south)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( east)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( west)).thenReturn(false);


        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(north, enemy.getPosition());
        assertEquals(AttackDirection.NORTH, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttackCityIsCloser_WEST_Test() {
        Position initialPosition = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class); // Closer Position
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position  east = Mockito.mock(Position.class);
        Position  west = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(p4)).thenReturn(false);
        Mockito.when(p4.north()).thenReturn(north);
        Mockito.when(p4.south()).thenReturn(south);
        Mockito.when(p4.east ()).thenReturn( east);
        Mockito.when(p4.west ()).thenReturn( west);

        Mockito.when(initialPosition.distanceTo(p1)).thenReturn(30);
        Mockito.when(initialPosition.distanceTo(p2)).thenReturn(27);
        Mockito.when(initialPosition.distanceTo(p3)).thenReturn(25);
        Mockito.when(initialPosition.distanceTo(p4)).thenReturn(10); // City is closer
        Mockito.when(initialPosition.distanceTo(north)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo(south)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo( east)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo( west)).thenReturn( 9); // Closer Distance

        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Model modelGrid = Mockito.mock(Model.class);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);

        Mockito.when(modelGrid.getCities()).thenReturn(cities);
        Mockito.when(modelGrid.getAllies()).thenReturn(allies);

        Mockito.when(modelGrid.tileOccupied(north)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied(south)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( east)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( west)).thenReturn(false);


        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(west, enemy.getPosition());
        assertEquals(AttackDirection.WEST, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttackAllyIsCloser_EAST_Test() {
        Position initialPosition = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class); // Closer Position
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position  east = Mockito.mock(Position.class);
        Position  west = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(p1)).thenReturn(false);
        Mockito.when(p1.north()).thenReturn(north);
        Mockito.when(p1.south()).thenReturn(south);
        Mockito.when(p1.east ()).thenReturn( east);
        Mockito.when(p1.west ()).thenReturn( west);

        Mockito.when(initialPosition.distanceTo(p1)).thenReturn(10); // Ally is Closer
        Mockito.when(initialPosition.distanceTo(p2)).thenReturn(27);
        Mockito.when(initialPosition.distanceTo(p3)).thenReturn(25);
        Mockito.when(initialPosition.distanceTo(p4)).thenReturn(30);
        Mockito.when(initialPosition.distanceTo(north)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo(south)).thenReturn( 9); // Closer Distance
        Mockito.when(initialPosition.distanceTo( east)).thenReturn( 9); // Closer Distance
        Mockito.when(initialPosition.distanceTo( west)).thenReturn(11);

        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Model modelGrid = Mockito.mock(Model.class);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);

        Mockito.when(modelGrid.getCities()).thenReturn(cities);
        Mockito.when(modelGrid.getAllies()).thenReturn(allies);

        Mockito.when(modelGrid.tileOccupied(north)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied(south)).thenReturn( true);
        Mockito.when(modelGrid.tileOccupied( east)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( west)).thenReturn(false);


        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(east, enemy.getPosition());
        assertEquals(AttackDirection.EAST, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttackAllyIsCloser_SOUTH_Test() {
        Position initialPosition = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class); // Closer Position
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class);
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position  east = Mockito.mock(Position.class);
        Position  west = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(p1)).thenReturn(false);
        Mockito.when(p1.north()).thenReturn(north);
        Mockito.when(p1.south()).thenReturn(south);
        Mockito.when(p1.east ()).thenReturn( east);
        Mockito.when(p1.west ()).thenReturn( west);

        Mockito.when(initialPosition.distanceTo(p1)).thenReturn(10); // Ally is Closer
        Mockito.when(initialPosition.distanceTo(p2)).thenReturn(27);
        Mockito.when(initialPosition.distanceTo(p3)).thenReturn(25);
        Mockito.when(initialPosition.distanceTo(p4)).thenReturn(30);
        Mockito.when(initialPosition.distanceTo(north)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo(south)).thenReturn(11); // Available Space
        Mockito.when(initialPosition.distanceTo( east)).thenReturn( 9);
        Mockito.when(initialPosition.distanceTo( west)).thenReturn( 9);

        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Model modelGrid = Mockito.mock(Model.class);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);

        Mockito.when(modelGrid.getCities()).thenReturn(cities);
        Mockito.when(modelGrid.getAllies()).thenReturn(allies);

        Mockito.when(modelGrid.tileOccupied(north)).thenReturn( true);
        Mockito.when(modelGrid.tileOccupied(south)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( east)).thenReturn( true);
        Mockito.when(modelGrid.tileOccupied( west)).thenReturn( true);


        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(south, enemy.getPosition());
        assertEquals(AttackDirection.SOUTH, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttackAllyAndCityAreAtSameDistanceTest() {
        Position initialPosition = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class); // Closer Position
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class); // Closer Position -> It should attack the city
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position  east = Mockito.mock(Position.class);
        Position  west = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(p4)).thenReturn(false);
        Mockito.when(p4.north()).thenReturn(north);
        Mockito.when(p4.south()).thenReturn(south);
        Mockito.when(p4.east ()).thenReturn( east);
        Mockito.when(p4.west ()).thenReturn( west);

        Mockito.when(initialPosition.same(p1)).thenReturn(false);
        Mockito.when(p1.north()).thenReturn( null);
        Mockito.when(p1.south()).thenReturn( null);
        Mockito.when(p1.east ()).thenReturn( null);
        Mockito.when(p1.west ()).thenReturn( null);

        Mockito.when(initialPosition.distanceTo(p1)).thenReturn(10); // Same Distance
        Mockito.when(initialPosition.distanceTo(p2)).thenReturn(27);
        Mockito.when(initialPosition.distanceTo(p3)).thenReturn(25);
        Mockito.when(initialPosition.distanceTo(p4)).thenReturn(10); // Same Distance
        Mockito.when(initialPosition.distanceTo(north)).thenReturn( 9); // Not Available Space
        Mockito.when(initialPosition.distanceTo(south)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo( east)).thenReturn(11);
        Mockito.when(initialPosition.distanceTo( west)).thenReturn( 9); // Closer and Available Space
        Mockito.when(initialPosition.distanceTo(null)).thenReturn(0);

        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Model modelGrid = Mockito.mock(Model.class);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);

        Mockito.when(modelGrid.getCities()).thenReturn(cities);
        Mockito.when(modelGrid.getAllies()).thenReturn(allies);

        Mockito.when(modelGrid.tileOccupied(north)).thenReturn( true);
        Mockito.when(modelGrid.tileOccupied(south)).thenReturn(false);
        Mockito.when(modelGrid.tileOccupied( east)).thenReturn( true);
        Mockito.when(modelGrid.tileOccupied( west)).thenReturn(false);


        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(west, enemy.getPosition());
        assertEquals(AttackDirection.WEST, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttackEnemyCantMoveOrAttackTest() {
        Position initialPosition = Mockito.mock(Position.class);
        Position p1 = Mockito.mock(Position.class);
        Position p2 = Mockito.mock(Position.class);
        Position p3 = Mockito.mock(Position.class);
        Position p4 = Mockito.mock(Position.class); // Closer Position
        Position north = Mockito.mock(Position.class);
        Position south = Mockito.mock(Position.class);
        Position  east = Mockito.mock(Position.class);
        Position  west = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(p4)).thenReturn(false);
        Mockito.when(p4.north()).thenReturn(north);
        Mockito.when(p4.south()).thenReturn(south);
        Mockito.when(p4.east ()).thenReturn( east);
        Mockito.when(p4.west ()).thenReturn( west);

        Mockito.when(initialPosition.distanceTo(p1)).thenReturn(30);
        Mockito.when(initialPosition.distanceTo(p2)).thenReturn(27);
        Mockito.when(initialPosition.distanceTo(p3)).thenReturn(25);
        Mockito.when(initialPosition.distanceTo(p4)).thenReturn(10); // Closer Distance

        Mockito.when(initialPosition.distanceTo(north)).thenReturn( 9); // Not Available Space
        Mockito.when(initialPosition.distanceTo(south)).thenReturn(11); // Not Available Space
        Mockito.when(initialPosition.distanceTo( east)).thenReturn(11); // Not Available Space
        Mockito.when(initialPosition.distanceTo( west)).thenReturn( 9); // Not Available Space

        Hero ally1 = Mockito.mock(Hero.class);
        Hero ally2 = Mockito.mock(Hero.class);
        City city1 = Mockito.mock(City.class);
        City city2 = Mockito.mock(City.class);
        Mockito.when(ally1.getPosition()).thenReturn(p1);
        Mockito.when(ally2.getPosition()).thenReturn(p2);
        Mockito.when(city1.getPosition()).thenReturn(p3);
        Mockito.when(city2.getPosition()).thenReturn(p4);

        Model modelGrid = Mockito.mock(Model.class);
        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        List<Hero> allies = new ArrayList<>();
        allies.add(ally1);
        allies.add(ally2);

        Mockito.when(modelGrid.getCities()).thenReturn(cities);
        Mockito.when(modelGrid.getAllies()).thenReturn(allies);

        Mockito.when(modelGrid.tileOccupied(north)).thenReturn(true);
        Mockito.when(modelGrid.tileOccupied(south)).thenReturn(true);
        Mockito.when(modelGrid.tileOccupied( east)).thenReturn(true);
        Mockito.when(modelGrid.tileOccupied( west)).thenReturn(true);


        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(initialPosition, enemy.getPosition());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());
    }

    @Test
    public void moveAndPlanAttackNoAllyOrCityFound() {
        Position initialPosition = Mockito.mock(Position.class);

        Mockito.when(initialPosition.same(initialPosition)).thenReturn(true);

        Model modelGrid = Mockito.mock(Model.class);
        Mockito.when(modelGrid.getCities()).thenReturn(new ArrayList<>());
        Mockito.when(modelGrid.getAllies()).thenReturn(new ArrayList<>());

        Enemy enemy = new Bug(initialPosition, 10,1);
        enemy.moveAndPlanAttack(modelGrid);

        assertEquals(initialPosition, enemy.getPosition());
        assertEquals(AttackDirection.NONE, enemy.getAttackDirection());
    }
}
