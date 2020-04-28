public class TailAttack implements AttackStrategy {
    private int damage;
    private int range;

    public TailAttack(int damage, int range) {
        this.damage = damage;
        this.range = range;
    }

    @Override
    public void attack(Grid grid, Position pos) {
        for (int x = pos.getX() - range; x <= pos.getX() + range; x++) {
            for (int y = pos.getY() - range; y <= pos.getY() + range; y++) {
                Position p = new Position(x, y);
                if ((p.insideCircle(pos, range)) && (!p.same(pos))) {
                    grid.inflictDamage(p, damage);
                }
            }
        }
    }
}
