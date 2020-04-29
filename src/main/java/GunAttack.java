public class GunAttack implements AttackStrategy {
    private int damage;
    private int range;

    public GunAttack(int damage, int range) {
        this.damage = damage;
        this.range = range;
    }

    @Override
    public void attack(Grid grid, Position pos) {
        for (int x = pos.getX() - this.range; x < pos.getX() + this.range; x++) {
            grid.inflictDamage(new Position(x, pos.getY()), this.damage);
        }
        for (int y = pos.getY() - this.range; y < pos.getY() + this.range; y++) {
            grid.inflictDamage(new Position(pos.getX(), y), this.damage);
        }
    }

    public void upgrade() {
        this.damage += 1;
        if (this.damage % 2 == 0) {
            this.range += 1;
        }
    }
}
