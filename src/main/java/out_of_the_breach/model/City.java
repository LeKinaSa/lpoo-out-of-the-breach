package out_of_the_breach.model;

public class City extends Entity {
    public City(Position pos, int hp) {
        super(pos, hp);
    }

    @Override
    public String getName() {
        return "City";
    }
}
