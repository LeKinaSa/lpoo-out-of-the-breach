package out_of_the_breach.model;

public class City extends Entity {
    private int power;

    public City(Position pos, int hp, int power) {
        super(pos, hp);
        this.power = 0;
        if (power > 0) {
            this.power = power;
        }
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        if (power >= 0) {
            this.power = power;
        }
    }

}
