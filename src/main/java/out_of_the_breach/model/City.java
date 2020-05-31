package out_of_the_breach.model;

/*
    Cities define the current energy of the grid.
    When the city takes damage, it's hp will lower and the energy of the grid will lower too proportionally.
 */

public class City extends Entity {
    public City(Position pos, int hp) {
        super(pos, hp);
    }

    @Override
    public String getName() {
        return "City";
    }

    @Override
    public String getInitials() {
        return "CTY";
    }
}
