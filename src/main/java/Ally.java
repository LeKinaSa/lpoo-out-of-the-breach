public class Ally extends GridElement {
    private int hp;
    private GunAttack gun;

    public Ally(Position pos, int hp, int damage, int range) {
        super(pos);
        this.gun = new GunAttack(damage, range);
    }

    public void upgradeAttack() {
        this.gun.upgrade();
    }

    @Override
    public void draw() {

    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public boolean isDead() {
        return this.hp == 0;
    }
}
