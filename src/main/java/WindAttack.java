public class WindAttack implements AttackStrategy {
    private int damage;
    private int range;

    public WindAttack(int damage, int range) {
        this.damage = damage;
        this.range = range;
    }

    @Override
    public void attack(Grid grid, Position pos) {
        for (int x = pos.getX() - this.range; x <= pos.getX() + this.range; x++) {
            for (int y = pos.getY() - this.range; y <= pos.getY() + this.range; y++) {
                Position p = new Position(x, y);
                if ((x == y) && (!p.same(pos))) {
                    grid.inflictDamage(p, this.damage);
                }
            }
        }
    }
}
